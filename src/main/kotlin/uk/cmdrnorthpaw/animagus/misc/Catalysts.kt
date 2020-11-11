package uk.cmdrnorthpaw.animagus.misc

import net.minecraft.entity.EntityType
import net.minecraft.entity.passive.AnimalEntity
import net.minecraft.item.Item
import net.minecraft.item.Items
import uk.cmdrnorthpaw.animagus.items.AnimagusItems

enum class Catalysts(item: Item, type: EntityType<out AnimalEntity>) {
    CAT(AnimagusItems.CAT_HAIR, EntityType.CAT),
    DOG(Items.BONE, EntityType.WOLF)
}