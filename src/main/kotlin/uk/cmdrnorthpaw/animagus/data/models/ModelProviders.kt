package uk.cmdrnorthpaw.animagus.data.models

import net.minecraft.data.DataGenerator
import net.minecraftforge.client.model.generators.ItemModelBuilder
import net.minecraftforge.client.model.generators.ItemModelProvider
import net.minecraftforge.common.data.ExistingFileHelper
import uk.cmdrnorthpaw.animagus.Animagus

class AnimagusItemModelProvider(generator: DataGenerator, helper: ExistingFileHelper) : ItemModelProvider(generator, Animagus.MODID, helper) {
    private val itemGenerated = getExistingFile(mcLoc("item/generated"))

    override fun registerModels() {
        itemModelBuilder("crystal_phial")
        itemModelBuilder("dew_phial")
    }

    private fun itemModelBuilder(name: String): ItemModelBuilder = getBuilder(name).parent(itemGenerated).texture("layer0", "item/$name")
}