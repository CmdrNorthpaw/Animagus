package uk.cmdrnorthpaw.animagus.data.tags

import net.minecraft.data.BlockTagsProvider
import net.minecraft.data.DataGenerator
import net.minecraft.data.ItemTagsProvider
import net.minecraftforge.common.data.ExistingFileHelper
import uk.cmdrnorthpaw.animagus.Animagus
import uk.cmdrnorthpaw.animagus.misc.Catalysts

class BlockTagProvider(generator: DataGenerator, fileHelper: ExistingFileHelper) : BlockTagsProvider(generator, Animagus.MODID, fileHelper) {
    override fun registerTags() {

    }
}

class ItemTagProvider(generator: DataGenerator, helper: ExistingFileHelper) : ItemTagsProvider(generator, BlockTagProvider(generator, helper), Animagus.MODID, helper,) {
    override fun registerTags() {
        val catalystBuilder = getOrCreateBuilder(AnimagusTags.Items.CATALYST)
        for (value in Catalysts.Static.values()) {
            value.items.forEach { catalystBuilder.add(it) }
        }

        for (value in Catalysts.Tagged.values()) {
            value.tags.forEach { tag -> catalystBuilder.addTag(tag) }
        }
    }
}