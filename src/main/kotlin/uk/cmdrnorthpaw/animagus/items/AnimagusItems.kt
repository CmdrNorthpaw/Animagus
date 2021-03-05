package uk.cmdrnorthpaw.animagus.items

import net.minecraft.item.Food
import net.minecraft.item.Item
import net.minecraft.item.ItemGroup
import net.minecraft.item.Items
import net.minecraftforge.fml.RegistryObject
import net.minecraftforge.registries.DeferredRegister
import net.minecraftforge.registries.ForgeRegistries
import uk.cmdrnorthpaw.animagus.Animagus
import uk.cmdrnorthpaw.animagus.misc.AnimagusCreativeTab

object AnimagusItems {
    internal val registry: DeferredRegister<Item> = DeferredRegister.create(ForgeRegistries.ITEMS, Animagus.MODID)
    private fun registerItem(name: String, item: Item): RegistryObject<Item> = registry.register(name) {item}

    val CAT_HAIR = registerItem("cat_hair", Item(Item.Properties().group(AnimagusCreativeTab)))
    val CHRYSALIS = registerItem("chrysalis", Item(Item.Properties().group(AnimagusCreativeTab)))
    val PLAYER_HAIR = registerItem("hair", Item(Item.Properties().group(AnimagusCreativeTab)))
    val MANDRAKE_LEAF = registerItem("mandrake_leaf", MandrakeLeaf())

    val CRYSTAL_PHIAL = registerItem("crystal_phial", CrystalPhial())
    val DEW_PHIAL = registerItem("dew_phial", Item(Item.Properties().group(AnimagusCreativeTab)))
}