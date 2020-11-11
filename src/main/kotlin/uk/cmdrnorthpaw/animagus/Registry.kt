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
    private fun registerBlock(block: Block, event: RegistryEvent.Register<Block>) = event.registry.register(block)
    private fun registerItem(item: Item, event: RegistryEvent.Register<Item>) = event.registry.register(item)

    @SubscribeEvent
    fun registerItems(event: RegistryEvent.Register<Item>) {
        for (item in AnimagusItems::class.memberProperties) {
            if (item.returnType == Item::class.createType()) registerItem(item.get(AnimagusItems) as Item, event)
        }
    }

    @SubscribeEvent
    fun registerBlocks(event: RegistryEvent.Register<Block>) { TODO("Need to wait until I create any blocks") }
}