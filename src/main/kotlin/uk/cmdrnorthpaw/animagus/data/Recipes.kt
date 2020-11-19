package uk.cmdrnorthpaw.animagus.data

import net.minecraft.data.*
import net.minecraft.data.DataGenerator
import net.minecraftforge.common.Tags
import uk.cmdrnorthpaw.animagus.Animagus
import uk.cmdrnorthpaw.animagus.items.AnimagusItems
import java.util.function.Consumer
import kotlin.reflect.full.createType
import kotlin.reflect.full.hasAnnotation
import kotlin.reflect.full.memberProperties

class AnimagusRecipeHandler(generator: DataGenerator) : RecipeProvider(generator) {
    override fun registerRecipes(consumer: Consumer<IFinishedRecipe>) {
        Recipes.crystalPhial.build(consumer)
    }

    object Recipes {
        private const val modid = Animagus.MODID
        val crystalPhial: ShapedRecipeBuilder = ShapedRecipeBuilder.shapedRecipe(AnimagusItems.PHIAL.get())
                .patternLine(" x ")
                .patternLine(" x ")
                .patternLine(" x ")
                .key('x', Tags.Items.GLASS)
                .setGroup(modid)
    }
}