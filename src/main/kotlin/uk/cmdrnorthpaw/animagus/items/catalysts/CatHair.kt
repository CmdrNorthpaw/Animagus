package uk.cmdrnorthpaw.animagus.items.catalysts

import net.minecraft.entity.CreatureEntity
import net.minecraft.entity.Entity
import net.minecraft.entity.EntityType
import net.minecraft.entity.passive.CatEntity
import net.minecraft.item.Item

class CatHair: AnimagusCatalyst, Item(Properties()) {
    override val targetEntity: EntityType<out CreatureEntity> = EntityType.CAT
}
