package uk.cmdrnorthpaw.animagus.gui

import net.minecraft.entity.player.PlayerEntity
import net.minecraft.entity.player.PlayerInventory
import net.minecraft.inventory.container.Container
import net.minecraft.item.ItemStack
import net.minecraftforge.items.wrapper.InvWrapper

class AnimagusPotionContainer(windowId: Int, playerInventory: PlayerInventory) : Container(AnimagusContainers.POTION.get(), windowId) {
    override fun canInteractWith(playerIn: PlayerEntity): Boolean {
        return true
    }

    val playerInventory = InvWrapper(playerInventory)


    private object Slots {
        const val MANDRAKE = 0
        const val HAIR = 1
        const val DEW = 2
        const val CHRYSALIS = 3
        const val CATALYST = 4

        val PLAYER_INVENTORY = (5..40)

    }
}