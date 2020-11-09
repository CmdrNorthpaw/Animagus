package uk.cmdrnorthpaw.animagus.items.catalysts

import net.minecraft.entity.Entity
import net.minecraft.entity.EntityType

interface AnimagusCatalyst {
    fun <T: Entity> getTargetEntity(): EntityType<T>
}