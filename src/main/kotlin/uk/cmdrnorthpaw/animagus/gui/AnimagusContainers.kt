package uk.cmdrnorthpaw.animagus.gui

import net.minecraftforge.common.extensions.IForgeContainerType
import net.minecraftforge.registries.DeferredRegister
import net.minecraftforge.registries.ForgeRegistries
import uk.cmdrnorthpaw.animagus.Animagus

object AnimagusContainers {
    internal val containerRegistry = DeferredRegister.create(ForgeRegistries.CONTAINERS, Animagus.MODID)

    val POTION = containerRegistry.register("") { IForgeContainerType.create {windowId, inv, _ ->
        return@create AnimagusPotionContainer(windowId, inv)
    }}
}