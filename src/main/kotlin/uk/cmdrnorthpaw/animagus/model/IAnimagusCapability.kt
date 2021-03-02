package uk.cmdrnorthpaw.animagus.model

import net.minecraft.entity.EntityType
import net.minecraft.entity.LivingEntity
import net.minecraft.entity.MobEntity

interface IAnimagusCapability {
    var isInAnimagusForm: Boolean

    var isAnimagus: Boolean

    var animagusForm: EntityType<*>?
}