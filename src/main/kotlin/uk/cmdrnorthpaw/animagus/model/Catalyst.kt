package uk.cmdrnorthpaw.animagus.model

import net.minecraft.entity.EntityType
import net.minecraft.entity.LivingEntity
import net.minecraft.entity.MobEntity
import net.minecraft.item.Item
import net.minecraft.tags.ITag

interface Catalyst {
    val entity: EntityType<out MobEntity>
    val items: Array<Item>

    companion object {
        @JvmStatic
        fun Item.asCatalystFor(entity: MobEntity): Catalyst = object: Catalyst {
            override val entity = entity.type as EntityType<out MobEntity>
            override val items = arrayOf(this@asCatalystFor)
        }

        @JvmStatic
        fun Array<Item>.asCatalystFor(entity: MobEntity) = object : Catalyst {
            override val entity: EntityType<out MobEntity> = entity.type as EntityType<out MobEntity>
            override val items = this@asCatalystFor
        }

        @JvmStatic
        fun ITag.INamedTag<Item>.asCatalystFor(entity: MobEntity) = object : Catalyst {
            override val entity = entity.type as EntityType<out MobEntity>
            override val items: Array<Item> = this@asCatalystFor.allElements.toTypedArray()
        }
    }
}