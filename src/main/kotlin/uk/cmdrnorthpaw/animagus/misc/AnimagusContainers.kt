package uk.cmdrnorthpaw.animagus.misc

import net.minecraft.inventory.container.ContainerType
import net.minecraftforge.common.extensions.IForgeContainerType
import net.minecraftforge.fml.RegistryObject
import net.minecraftforge.registries.DeferredRegister
import net.minecraftforge.registries.ForgeRegistries
import uk.cmdrnorthpaw.animagus.Animagus
import uk.cmdrnorthpaw.animagus.items.PhialContainer

object AnimagusContainers {
    internal val registry = DeferredRegister.create(ForgeRegistries.CONTAINERS, Animagus.MODID)

    val CRYSTAL_PHIAL: RegistryObject<ContainerType<PhialContainer>> = registry.register("crystal_phial") { IForgeContainerType.create { windowId, inv, _ ->
        return@create PhialContainer(windowId, inv.player, inv.player.world, inv)
    }}
}