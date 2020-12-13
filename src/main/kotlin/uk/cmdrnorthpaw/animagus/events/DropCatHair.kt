package uk.cmdrnorthpaw.animagus.events

import net.minecraft.entity.EntityType
import net.minecraftforge.event.TickEvent
import net.minecraftforge.event.entity.living.LivingEvent
import net.minecraftforge.eventbus.api.SubscribeEvent
import net.minecraftforge.fml.common.Mod
import kotlin.random.Random

@Mod.EventBusSubscriber
object DropCatHair {
    @JvmStatic
    @SubscribeEvent
    fun dropCatHair(event: LivingEvent.LivingUpdateEvent) {
        if (event.entity.type != EntityType.CAT) return
        val entity = event.entity
        val tag = entity.persistentData
        if (tag.getInt("untilDrop") == 1)
            tag.putInt("untilDrop", Random.nextInt(6000))
    }
}