package uk.cmdrnorthpaw.animagus.misc

import net.minecraft.item.ItemGroup
import net.minecraft.item.ItemStack
import uk.cmdrnorthpaw.animagus.items.AnimagusItems

object AnimagusCreativeTab: ItemGroup("Animagus") {
    override fun createIcon(): ItemStack = ItemStack(AnimagusItems.MANDRAKE_LEAF.get(), 1)
}