package uk.cmdrnorthpaw.animagus.gui

import net.minecraft.entity.player.PlayerEntity
import net.minecraft.entity.player.PlayerInventory
import net.minecraft.inventory.container.Container
import net.minecraft.inventory.container.INamedContainerProvider
import net.minecraft.item.ItemStack
import net.minecraft.util.text.ITextComponent
import net.minecraft.util.text.TranslationTextComponent
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

    companion object {
        val provider = object : INamedContainerProvider {
            override fun createMenu(p_createMenu_1_: Int, p_createMenu_2_: PlayerInventory, p_createMenu_3_: PlayerEntity): Container {
                return AnimagusPotionContainer(p_createMenu_1_, p_createMenu_2_)
            }
            override fun getDisplayName(): ITextComponent = TranslationTextComponent("animagus.gui.potion_container")
        }
    }
}