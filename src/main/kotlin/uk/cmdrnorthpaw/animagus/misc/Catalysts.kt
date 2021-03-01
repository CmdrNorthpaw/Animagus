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
import uk.cmdrnorthpaw.animagus.items.AnimagusItems
import uk.cmdrnorthpaw.animagus.model.Catalyst
import java.util.function.Predicate

private val internalCatalysts: MutableList<Catalyst> = mutableListOf()

object Catalysts : List<Catalyst> by internalCatalysts {
    fun register(element: Catalyst) = internalCatalysts.add(element)
    fun registerAll(vararg elements: Catalyst) = internalCatalysts.addAll(elements)
}