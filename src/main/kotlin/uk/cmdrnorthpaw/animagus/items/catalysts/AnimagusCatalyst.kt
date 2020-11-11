package uk.cmdrnorthpaw.animagus.items.catalysts

import net.minecraft.entity.CreatureEntity
import net.minecraft.entity.Entity
import net.minecraft.entity.EntityType

interface AnimagusCatalyst {
    val targetEntity: EntityType<out CreatureEntity>
}