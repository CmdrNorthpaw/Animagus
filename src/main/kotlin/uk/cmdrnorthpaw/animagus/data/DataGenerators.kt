package uk.cmdrnorthpaw.animagus.data

import net.minecraft.data.DataGenerator
import net.minecraftforge.common.data.ExistingFileHelper
import net.minecraftforge.eventbus.api.SubscribeEvent
import net.minecraftforge.fml.common.Mod
import net.minecraftforge.fml.event.lifecycle.GatherDataEvent
import thedarkcolour.kotlinforforge.forge.MOD_BUS
import uk.cmdrnorthpaw.animagus.Animagus
import uk.cmdrnorthpaw.animagus.data.models.AnimagusItemModelProvider
import uk.cmdrnorthpaw.animagus.data.tags.ItemTagProvider


object DataGenerators {

    private fun registerClient(generator: DataGenerator, helper: ExistingFileHelper) {
        Animagus.logger.debug("Generating client-side data")
        generator.addProvider(AnimagusItemModelProvider(generator, helper))
    }

    private fun registerServer(generator: DataGenerator, helper: ExistingFileHelper) {
        Animagus.logger.debug("Registering server-side data")
        generator.addProvider(AnimagusRecipeHandler(generator))
        generator.addProvider(ItemTagProvider(generator, helper))
    }

    @JvmStatic
    @SubscribeEvent
    fun gatherData(event: GatherDataEvent) {
        if (event.includeServer()) registerServer(event.generator, event.existingFileHelper)
        if (event.includeClient()) registerClient(event.generator, event.existingFileHelper)
    }
}