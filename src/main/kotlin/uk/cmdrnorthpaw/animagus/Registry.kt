package uk.cmdrnorthpaw.animagus

import net.minecraft.block.Block
import net.minecraft.item.Item
import net.minecraftforge.fml.RegistryObject
import net.minecraftforge.registries.DeferredRegister
import net.minecraftforge.registries.ForgeRegistries
import uk.cmdrnorthpaw.animagus.items.AnimagusItems


object Registry {
    object Blocks {
        val registry: DeferredRegister<Block> = DeferredRegister.create(ForgeRegistries.BLOCKS, Animagus.MODID)
    }

    object Items {
        val registry: DeferredRegister<Item> = DeferredRegister.create(ForgeRegistries.ITEMS, Animagus.MODID)
        private fun registerItem(name: String, item: Item): RegistryObject<Item> = registry.register(name) {item}

        val CAT_HAIR = registerItem("cat_hair", AnimagusItems.CAT_HAIR)
        val CHRYSALIS = registerItem("chrysalis", AnimagusItems.CHRYSALIS)
        val MANDRAKE_LEAF = registerItem("mandrake_leaf", AnimagusItems.MANDRAKE_LEAF)
        val PHIAL = registerItem("crystal_phial", AnimagusItems.PHIAL)
    }
}