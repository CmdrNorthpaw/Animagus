package uk.cmdrnorthpaw.animagus

import net.minecraft.block.Block
import net.minecraft.item.Item
import net.minecraft.util.registry.Registry
import net.minecraftforge.event.RegistryEvent
import net.minecraftforge.eventbus.api.SubscribeEvent
import net.minecraftforge.fml.common.Mod
import uk.cmdrnorthpaw.animagus.items.AnimagusItems
import kotlin.reflect.full.createType
import kotlin.reflect.full.hasAnnotation
import kotlin.reflect.full.memberProperties

@Mod.EventBusSubscriber
object Registry {
    @SubscribeEvent
    fun registerItems(event: RegistryEvent.Register<Item>) = AnimagusItems::class.memberProperties.forEach { item ->
        if (item.returnType == Item::class.createType()) event.registry.register(item.get(AnimagusItems) as Item)
    }
}