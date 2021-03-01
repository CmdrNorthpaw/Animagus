package uk.cmdrnorthpaw.animagus.misc

import net.minecraftforge.common.capabilities.Capability
import net.minecraftforge.common.capabilities.CapabilityInject
import uk.cmdrnorthpaw.animagus.model.IAnimagusCapability
import uk.cmdrnorthpaw.animagus.capability.AnimagusCapability
import uk.cmdrnorthpaw.animagus.capability.CatalystCapability

object Capabilities {
    @CapabilityInject(IAnimagusCapability::class)
    lateinit var ANIMAGUS: Capability<AnimagusCapability>

    @CapabilityInject(IAnimagusCapability::class)
    lateinit var CATALYST: Capability<CatalystCapability>
}