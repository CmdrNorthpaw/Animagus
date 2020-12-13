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

object Catalysts {
    enum class Tagged(val type: EntityType<out LivingEntity>, vararg val tags: ITag.INamedTag<Item>) {
        DOG(EntityType.WOLF, Tags.Items.BONES),

        COW(EntityType.COW, Tags.Items.LEATHER),
        SHEEP(EntityType.SHEEP, ItemTags.WOOL),
        CHICKEN(EntityType.CHICKEN, Tags.Items.FEATHERS),

        IRON_GOLEM(EntityType.IRON_GOLEM, Tags.Items.STORAGE_BLOCKS_IRON),

        RABBIT(EntityType.RABBIT, Tags.Items.CROPS_CARROT),
        LLAMA(EntityType.LLAMA, ItemTags.CARPETS),

    }

    enum class Static(val type: EntityType<out LivingEntity>, vararg val items: Item) {
        CAT(EntityType.CAT, AnimagusItems.CAT_HAIR.get()),

        SALMON(EntityType.SALMON, Items.SALMON),
        COD(EntityType.COD, Items.COD),
        TROPICAL_FISH(EntityType.TROPICAL_FISH, Items.TROPICAL_FISH),
        SQUID(EntityType.SQUID, Items.INK_SAC),
        TURTLE(EntityType.TURTLE, Items.SCUTE),

        PIG(EntityType.PIG, Items.APPLE, Items.GOLDEN_APPLE, Items.ENCHANTED_GOLDEN_APPLE),
        CHICKEN(EntityType.CHICKEN, Items.EGG),
        HORSE(EntityType.HORSE, Items.SADDLE),

        PARROT(EntityType.PARROT, Items.COOKIE),
        PANDA(EntityType.PANDA, Items.BAMBOO, Items.CAKE),

        SNOW_GOLEM(EntityType.SNOW_GOLEM, Items.SNOW, Items.SNOW_BLOCK, Items.SNOWBALL),

        FOX(EntityType.FOX, Items.MUTTON),
        BEE(EntityType.BEE, Items.HONEYCOMB, Items.HONEY_BOTTLE, Items.HONEYCOMB_BLOCK, Items.HONEY_BLOCK),

        VILLAGER(EntityType.VILLAGER, Items.EMERALD)
    }
}