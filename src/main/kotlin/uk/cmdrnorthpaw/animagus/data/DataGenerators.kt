package uk.cmdrnorthpaw.animagus.data

import net.minecraft.data.DataGenerator
import net.minecraftforge.eventbus.api.SubscribeEvent
import net.minecraftforge.fml.common.Mod
import net.minecraftforge.fml.event.lifecycle.GatherDataEvent
import uk.cmdrnorthpaw.animagus.Animagus

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, modid = Animagus.MODID)
object DataGenerators {

    private fun registerClient(generator: DataGenerator) {

    }

    private fun registerServer(generator: DataGenerator) {
        generator.addProvider(AnimagusRecipeHandler(generator))
    }

    @JvmStatic
    @SubscribeEvent
    fun gatherData(event: GatherDataEvent) {
        if (event.includeServer()) registerServer(event.generator)
        if (event.includeClient()) registerClient(event.generator)
    }
}