package uk.cmdrnorthpaw.animagus.items

import net.minecraft.client.util.ITooltipFlag
import net.minecraft.entity.Entity
import net.minecraft.entity.item.ItemEntity
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.item.Food
import net.minecraft.item.Item
import net.minecraft.item.ItemGroup
import net.minecraft.item.ItemStack
import net.minecraft.nbt.CompoundNBT
import net.minecraft.nbt.INBT
import net.minecraft.util.text.*
import net.minecraft.world.World
import java.io.DataOutputStream

class MandrakeLeaf : Item(Properties()
        .group(ItemGroup.FOOD)
        .food(Food.Builder()
                .fastToEat()
                .hunger(1)
                .saturation(3F)
                .build())
        .maxStackSize(16)
) {
    override fun inventoryTick(stack: ItemStack, world: World, entity: Entity, p_77663_4_: Int, p_77663_5_: Boolean) {
        val tag = stack.tag ?: CompoundNBT()
        val time = tag.getInt("age")
        if (entity !is PlayerEntity || tag.getString("currentOwner") == entity.cachedUniqueIdString) { tag.putInt("age", 0); return }
        if (time < 192000) {
            tag.putInt("age", time+1)
            tag.putString("currentOwner", entity.cachedUniqueIdString)
            stack.tag = tag
        }
    }

    override fun onEntityItemUpdate(stack: ItemStack?, entity: ItemEntity?): Boolean {
        if (entity == null || stack == null) return false
        else if (entity.isAddedToWorld) {
            val tag = stack.tag ?: CompoundNBT()
            tag.putInt("age", 0)
            stack.tag = tag
        }
        return false
    }

    override fun addInformation(stack: ItemStack, worldIn: World?, tooltip: MutableList<ITextComponent>, flagIn: ITooltipFlag) {
        super.addInformation(stack, worldIn, tooltip, flagIn)
        val tag = stack.tag ?: CompoundNBT()
        if (tag.getInt("age") >= 192000 && worldIn?.moonPhase == 8) tooltip.add(StringTextComponent("Leaf is ready for use!").mergeStyle(TextFormatting.GREEN))
        else tooltip.add(StringTextComponent("Leaf cannot be used in a potion!").mergeStyle(TextFormatting.RED))
    }
}