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
    override fun registerRecipes(consumer: Consumer<IFinishedRecipe>) = AnimagusRecipes::class.memberProperties.forEach { recipe ->
        if (recipe.hasAnnotation<Recipe>() && recipe.returnType == ShapedRecipeBuilder::class.createType()) {
            val recipeBuilder = recipe.get(AnimagusRecipes) as ShapelessRecipeBuilder
            recipeBuilder.build(consumer)
        } else if (recipe.hasAnnotation<Recipe>() && recipe.returnType == ShapelessRecipeBuilder::class.createType()) {
            val recipeBuilder = recipe.get(AnimagusRecipes) as ShapelessRecipeBuilder
            recipeBuilder.build(consumer)
        }

    }
}

private object AnimagusRecipes {
    private val id = Animagus.MODID
    @Recipe
    val crystalPhial: ShapedRecipeBuilder = ShapedRecipeBuilder.shapedRecipe(AnimagusItems.PHIAL)
            .patternLine(" x ")
            .patternLine(" x ")
            .patternLine(" x ")
            .key('x', Tags.Items.GLASS)
            .setGroup(id)
}

@Target(AnnotationTarget.PROPERTY)
private annotation class Recipe