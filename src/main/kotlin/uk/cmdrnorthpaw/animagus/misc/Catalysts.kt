package uk.cmdrnorthpaw.animagus.misc

import net.minecraft.entity.EntityType
import net.minecraft.entity.LivingEntity
import net.minecraft.entity.passive.AnimalEntity
import net.minecraft.item.Item
import net.minecraft.item.Items
import uk.cmdrnorthpaw.animagus.items.AnimagusItems

enum class Catalysts(type: EntityType<out LivingEntity>, vararg items: Item) {
    CAT(EntityType.CAT, AnimagusItems.CAT_HAIR),
    DOG(EntityType.WOLF, Items.BONE),

    SALMON(EntityType.SALMON, Items.SALMON),
    COD(EntityType.COD, Items.COD),

    CHICKEN(EntityType.CHICKEN, Items.EGG),
    COW(EntityType.COW, Items.LEATHER),
    PIG(EntityType.PIG, Items.APPLE),
    SHEEP(EntityType.SHEEP, Items.WHITE_WOOL, Items.BROWN_WOOL, Items.BLUE_WOOL, Items.BLUE_WOOL, Items.LIGHT_BLUE_WOOL, Items.GRAY_WOOL, Items.LIGHT_GRAY_WOOL, Items.BLACK_WOOL, Items.CYAN_WOOL, Items.GREEN_WOOL, Items.ORANGE_WOOL, Items.LIME_WOOL, Items.RED_WOOL, Items.YELLOW_WOOL, Items.PURPLE_WOOL, Items.MAGENTA_WOOL, Items.PINK_WOOL)

}