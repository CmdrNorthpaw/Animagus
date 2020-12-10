package uk.cmdrnorthpaw.animagus.data

import net.minecraft.data.DataGenerator
import net.minecraftforge.common.data.ExistingFileHelper
import net.minecraftforge.eventbus.api.SubscribeEvent
import net.minecraftforge.fml.common.Mod
import net.minecraftforge.fml.event.lifecycle.GatherDataEvent
import uk.cmdrnorthpaw.animagus.Animagus
import uk.cmdrnorthpaw.animagus.data.tags.ItemTagProvider

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, modid = Animagus.MODID)
object DataGenerators {

    private fun registerClient(generator: DataGenerator) {

    }

    private fun registerServer(generator: DataGenerator, helper: ExistingFileHelper) {
        generator.addProvider(AnimagusRecipeHandler(generator))
        generator.addProvider(ItemTagProvider(generator, helper))
    }

    @JvmStatic
    @SubscribeEvent
    fun gatherData(event: GatherDataEvent) {
        if (event.includeServer()) registerServer(event.generator, event.existingFileHelper)
        if (event.includeClient()) registerClient(event.generator)
    }
}