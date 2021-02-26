package uk.cmdrnorthpaw.animagus.model

import net.minecraft.entity.LivingEntity
import net.minecraft.item.Item
import net.minecraft.tags.ITag

interface Catalyst {
    val entity: LivingEntity
    val items: Array<Item>

    companion object {
        @JvmStatic
        fun Item.asCatalystFor(entity: LivingEntity): Catalyst = object: Catalyst {
            override val entity = entity
            override val items = arrayOf(this@asCatalystFor)
        }

        @JvmStatic
        fun ITag.INamedTag<Item>.asCatalystFor(entity: LivingEntity) = object : Catalyst {
            override val entity = entity
            override val items: Array<Item> = this@asCatalystFor.allElements.toTypedArray()
        }
    }
}