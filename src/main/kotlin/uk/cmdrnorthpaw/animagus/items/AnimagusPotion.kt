package uk.cmdrnorthpaw.animagus.items

import com.mojang.blaze3d.matrix.MatrixStack
import com.mojang.blaze3d.systems.RenderSystem
import net.minecraft.client.gui.screen.inventory.ContainerScreen
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.entity.player.PlayerInventory
import net.minecraft.inventory.container.Container
import net.minecraft.item.Item
import net.minecraft.util.ResourceLocation
import net.minecraft.util.text.ITextComponent
import net.minecraft.world.World
import uk.cmdrnorthpaw.animagus.Animagus
import uk.cmdrnorthpaw.animagus.misc.AnimagusContainers
import uk.cmdrnorthpaw.animagus.misc.AnimagusCreativeTab

class AnimagusPotion : Item(Properties()
        .group(AnimagusCreativeTab)
        .maxStackSize(1))


class PhialContainer(windowID: Int, player: PlayerEntity, world: World, inventory: PlayerInventory) : Container(AnimagusContainers.CRYSTAL_PHIAL.get(), windowID) {
    override fun canInteractWith(playerIn: PlayerEntity): Boolean = true
}

class PhialScreen(container: PhialContainer, inventory: PlayerInventory, name: ITextComponent) : ContainerScreen<PhialContainer>(container, inventory, name) {
    override fun drawGuiContainerBackgroundLayer(matrixStack: MatrixStack, partialTicks: Float, x: Int, y: Int) {
        TODO()
    }
}