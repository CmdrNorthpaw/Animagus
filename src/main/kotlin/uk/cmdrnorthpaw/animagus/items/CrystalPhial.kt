package uk.cmdrnorthpaw.animagus.items

import net.minecraft.block.Blocks
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.item.ItemUseContext
import net.minecraft.util.*
import net.minecraft.world.World
import uk.cmdrnorthpaw.animagus.misc.AnimagusCreativeTab

class CrystalPhial : Item(Item.Properties()
    .group(AnimagusCreativeTab)) {

    override fun onItemUse(context: ItemUseContext): ActionResultType {
        val stack = context.item
        val world = context.world
        val block = world.getBlockState(context.pos).block

        if (world.dayTime in 23000..24000 && (block.matchesBlock(Blocks.TALL_GRASS) || block.matchesBlock(Blocks.GRASS))) {
            context.player?.playSound(SoundEvents.ITEM_BOTTLE_FILL, 1F, 1F)
            stack.count--
            context.player?.inventory?.addItemStackToInventory(ItemStack(AnimagusItems.DEW_PHIAL.get(), 1))
            return ActionResultType.SUCCESS
        }
        return ActionResultType.PASS
    }
}