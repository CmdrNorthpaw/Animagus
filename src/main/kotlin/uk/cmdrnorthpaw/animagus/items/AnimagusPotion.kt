package uk.cmdrnorthpaw.animagus.items

import net.minecraft.client.util.ITooltipFlag
import net.minecraft.entity.LivingEntity
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.potion.PotionUtils
import net.minecraft.util.ActionResult
import net.minecraft.util.DrinkHelper
import net.minecraft.util.Hand
import net.minecraft.util.text.Color
import net.minecraft.util.text.ITextComponent
import net.minecraft.util.text.Style
import net.minecraft.util.text.TranslationTextComponent
import net.minecraft.world.World
import net.minecraftforge.event.world.NoteBlockEvent
import uk.cmdrnorthpaw.animagus.misc.AnimagusCreativeTab
import uk.cmdrnorthpaw.animagus.misc.Capabilities

class AnimagusPotion : Item(Properties()
    .maxStackSize(1)
    .group(AnimagusCreativeTab))
{
    override fun onEntitySwing(stack: ItemStack?, entity: LivingEntity?): Boolean {
        if (entity !is PlayerEntity || stack == null) return false
        if (entity.isSneaking) {
            stack.count--
            entity.inventory.addItemStackToInventory(ItemStack(AnimagusItems.CRYSTAL_PHIAL.get(), 1))
        }
        return false
    }

    override fun onItemRightClick(worldIn: World, playerIn: PlayerEntity, handIn: Hand): ActionResult<ItemStack> {
        return DrinkHelper.startDrinking(worldIn, playerIn, handIn)
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

    override fun addInformation(stack: ItemStack, worldIn: World?, tooltip: MutableList<ITextComponent>, flagIn: ITooltipFlag) {
        val nbt = stack.serializeNBT()
        if (nbt.getBoolean("hasMandrake")) {
            tooltip.add(TranslationTextComponent(AnimagusItems.MANDRAKE_LEAF.get().translationKey).mergeStyle(coloredText(Color.fromHex("4dc322")!!)))
        }
    }
}