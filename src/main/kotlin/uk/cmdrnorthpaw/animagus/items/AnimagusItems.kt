package uk.cmdrnorthpaw.animagus.items

import net.minecraft.item.Food
import net.minecraft.item.Item
import net.minecraft.item.ItemGroup

object AnimagusItems {
    val MANDRAKE_LEAF = Item(Item.Properties()
            .group(ItemGroup.FOOD)
            .food(Food.Builder()
                    .fastToEat()
                    .hunger(1)
                    .saturation(1F)
                    .build())
            .maxStackSize(16)
    )
}