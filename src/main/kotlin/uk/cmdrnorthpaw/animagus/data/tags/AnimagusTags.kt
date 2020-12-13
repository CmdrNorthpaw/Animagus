package uk.cmdrnorthpaw.animagus.data.tags

import net.minecraft.item.Item
import net.minecraft.tags.BlockTags
import net.minecraft.tags.ITag
import net.minecraft.tags.ItemTags
import net.minecraft.util.ResourceLocation
import uk.cmdrnorthpaw.animagus.Animagus

object AnimagusTags {

    object Items {
        private fun forgeTag(path: String) = ItemTags.makeWrapperTag(ResourceLocation("forge", path).toString())
        private fun animagusTag(path: String) = ItemTags.makeWrapperTag(ResourceLocation(Animagus.MODID, path).toString())

        val CATALYST = animagusTag("catalyst")


    }
    object Blocks {
        private fun forgeTag(path: String) = BlockTags.makeWrapperTag(ResourceLocation("forge", path).toString())
        private fun animagusTag(path: String) = BlockTags.makeWrapperTag(ResourceLocation(Animagus.MODID, path).toString())


    }
}