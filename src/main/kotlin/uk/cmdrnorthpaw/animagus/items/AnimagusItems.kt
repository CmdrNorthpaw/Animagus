package uk.cmdrnorthpaw.animagus.items

import net.minecraft.item.Food
import net.minecraft.item.Item
import net.minecraft.item.ItemGroup
import net.minecraft.item.Items
import uk.cmdrnorthpaw.animagus.misc.AnimagusCreativeTab

object AnimagusItems {
    val MANDRAKE_LEAF = MandrakeLeaf()
    val CAT_HAIR = Item(Item.Properties().group(AnimagusCreativeTab))
    val CHRYSALIS = Item(Item.Properties().group(AnimagusCreativeTab))
    val PHIAL = CrystalPhial()
}