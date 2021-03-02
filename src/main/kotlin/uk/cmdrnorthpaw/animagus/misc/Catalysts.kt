package uk.cmdrnorthpaw.animagus.misc

import net.minecraft.entity.EntityType
import net.minecraft.entity.LivingEntity
import net.minecraft.entity.passive.AnimalEntity
import net.minecraft.item.Item
import net.minecraft.item.Items
import net.minecraft.tags.ITag
import net.minecraft.tags.ItemTags
import net.minecraft.tags.Tag
import net.minecraftforge.common.Tags
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent
import uk.cmdrnorthpaw.animagus.items.AnimagusItems
import uk.cmdrnorthpaw.animagus.model.Catalyst
import uk.cmdrnorthpaw.animagus.model.Catalyst.Companion.asCatalystFor
import java.util.function.Predicate

private val internalCatalysts: MutableList<Catalyst> = mutableListOf()

object Catalysts : List<Catalyst> by internalCatalysts {
    fun register(element: Catalyst) = internalCatalysts.add(element)
    fun registerAll(vararg elements: Catalyst) = internalCatalysts.addAll(elements)

    @JvmStatic
    internal fun registerInternalCatalysts(event: FMLClientSetupEvent) {
        register(Tags.Items.BONES.asCatalystFor(EntityType.WOLF))
        register(AnimagusItems.CAT_HAIR.get().asCatalystFor(EntityType.CAT))

        register(ItemTags.CARPETS.asCatalystFor(EntityType.LLAMA))
        register(Tags.Items.CROPS_CARROT.asCatalystFor(EntityType.RABBIT))
        register(Items.COOKIE.asCatalystFor(EntityType.PARROT))
        register(Items.BAMBOO.asCatalystFor(EntityType.PANDA))
        register(arrayOf(Items.HONEYCOMB, Items.HONEY_BLOCK, Items.HONEYCOMB_BLOCK, Items.HONEY_BOTTLE).asCatalystFor(EntityType.BEE))

        register(arrayOf(Items.COD, Items.COOKED_COD).asCatalystFor(EntityType.COD))
        register(arrayOf(Items.SALMON, Items.COOKED_SALMON).asCatalystFor(EntityType.COD))


        register(ItemTags.WOOL.asCatalystFor(EntityType.SHEEP))
        register(Tags.Items.LEATHER.asCatalystFor(EntityType.COW))
        register(Tags.Items.FEATHERS.asCatalystFor(EntityType.CHICKEN))
        register(Items.EGG.asCatalystFor(EntityType.CHICKEN))
        register(Items.APPLE.asCatalystFor(EntityType.PIG))
        register(Items.SADDLE.asCatalystFor(EntityType.HORSE))

        register(Tags.Items.STORAGE_BLOCKS_IRON.asCatalystFor(EntityType.IRON_GOLEM))
        register(Items.SNOWBALL.asCatalystFor(EntityType.SNOW_GOLEM))

    }
}