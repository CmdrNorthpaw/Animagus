package uk.cmdrnorthpaw.animagus.items

import net.minecraft.client.util.ITooltipFlag
import net.minecraft.entity.LivingEntity
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.entity.player.ServerPlayerEntity
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.potion.PotionUtils
import net.minecraft.util.ActionResult
import net.minecraft.util.DrinkHelper
import net.minecraft.util.Hand
import net.minecraft.util.text.*
import net.minecraft.world.World
import net.minecraftforge.common.util.LazyOptional
import net.minecraftforge.event.world.NoteBlockEvent
import net.minecraftforge.fml.network.NetworkHooks
import net.minecraftforge.items.CapabilityItemHandler
import net.minecraftforge.items.IItemHandler
import net.minecraftforge.items.ItemStackHandler
import uk.cmdrnorthpaw.animagus.gui.AnimagusPotionContainer
import uk.cmdrnorthpaw.animagus.misc.AnimagusCreativeTab
import uk.cmdrnorthpaw.animagus.misc.Capabilities

class AnimagusPotion : Item(Properties()
    .maxStackSize(1)
    .group(AnimagusCreativeTab))
{
    val itemHandler = object : ItemStackHandler(41) {
        override fun isItemValid(slot: Int, stack: ItemStack): Boolean {
            if (slot == AnimagusPotionContainer.Slots.MANDRAKE && stack.item == AnimagusItems.MANDRAKE_LEAF.get()) return true
            if (slot == AnimagusPotionContainer.Slots.DEW && stack.item == AnimagusItems.MANDRAKE_LEAF.get()) return true
            if (slot == AnimagusPotionContainer.Slots.HAIR && stack.item == AnimagusItems.PLAYER_HAIR.get()) return true
            if (slot == AnimagusPotionContainer.Slots.CHRYSALIS && stack.item == AnimagusItems.CHRYSALIS.get()) return true
            if (slot == AnimagusPotionContainer.Slots.CATALYST && stack.getCapability(Capabilities.CATALYST).isPresent) return true

            return false
        }
    }

    val itemHandlerOptional: LazyOptional<ItemStackHandler> = LazyOptional.of { itemHandler }

    override fun onEntitySwing(stack: ItemStack?, entity: LivingEntity?): Boolean {
        if (entity !is PlayerEntity || stack == null) return false
        if (entity.isSneaking) {
            stack.count--
            entity.inventory.addItemStackToInventory(ItemStack(AnimagusItems.CRYSTAL_PHIAL.get(), 1))
            return true
        }
        return false
    }

    override fun onItemRightClick(worldIn: World, playerIn: PlayerEntity, handIn: Hand): ActionResult<ItemStack> {
        if (!playerIn.isSneaking) return DrinkHelper.startDrinking(worldIn, playerIn, handIn)

        return if (!worldIn.isRemote) {
            NetworkHooks.openGui(playerIn as ServerPlayerEntity, AnimagusPotionContainer.getProvider(playerIn.getHeldItem(handIn)))
            ActionResult.resultSuccess(playerIn.getHeldItem(handIn))
        } else ActionResult.resultPass(playerIn.getHeldItem(handIn))
    }

    override fun onItemUseFinish(stack: ItemStack, worldIn: World, entityLiving: LivingEntity): ItemStack {
        val player = entityLiving as? PlayerEntity ?: return stack

        stack.getCapability(Capabilities.CATALYST).ifPresent { catalystCapability ->
            player.getCapability(Capabilities.ANIMAGUS).ifPresent {
                it.isAnimagus = true
                it.animagusForm = catalystCapability.entity
            }
        }

        player.sendMessage(TranslationTextComponent("animagus.animagus_activate"), player.uniqueID)
        return ItemStack(AnimagusItems.CRYSTAL_PHIAL.get())
    }

    private fun coloredText(color: Color): Style {
        val style = Style.EMPTY
        style.color = color
        return style
    }

    private fun coloredTooltip(key: String, hex: String): IFormattableTextComponent {
        val style = Style.EMPTY
        style.color = Color.fromHex(hex)
        return TranslationTextComponent(item.translationKey).mergeStyle(style)
    }

    override fun addInformation(stack: ItemStack, worldIn: World?, tooltip: MutableList<ITextComponent>, flagIn: ITooltipFlag) {
        stack.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY).ifPresent { handler ->
            val hasStack = { slot: Int -> handler.getStackInSlot(slot).isEmpty }

            if (hasStack(AnimagusPotionContainer.Slots.MANDRAKE)) tooltip.add(coloredTooltip(AnimagusItems.MANDRAKE_LEAF.get().translationKey, "4dc322"))
            if (hasStack(AnimagusPotionContainer.Slots.HAIR)) tooltip.add(coloredTooltip(AnimagusItems.PLAYER_HAIR.get().translationKey, "753b00"))
            if (hasStack(AnimagusPotionContainer.Slots.DEW)) tooltip.add(coloredTooltip(AnimagusItems.DEW_PHIAL.get().translationKey, "74e2da"))
            if (hasStack(AnimagusPotionContainer.Slots.CHRYSALIS)) tooltip.add(coloredTooltip(AnimagusItems.CHRYSALIS.get().translationKey, "e03c00"))
            if (hasStack(AnimagusPotionContainer.Slots.CATALYST)) tooltip.add(coloredTooltip("animagus.catalyst", "d1000e"))
        }
    }
}