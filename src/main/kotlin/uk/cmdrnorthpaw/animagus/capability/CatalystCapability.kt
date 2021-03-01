package uk.cmdrnorthpaw.animagus.capability

import net.minecraft.entity.EntityType
import net.minecraft.item.Item
import net.minecraft.nbt.CompoundNBT
import net.minecraft.nbt.INBT
import net.minecraft.util.Direction
import net.minecraft.util.ResourceLocation
import net.minecraftforge.common.capabilities.Capability
import net.minecraftforge.common.capabilities.ICapabilitySerializable
import net.minecraftforge.common.util.LazyOptional
import net.minecraftforge.event.AttachCapabilitiesEvent
import net.minecraftforge.eventbus.api.SubscribeEvent
import uk.cmdrnorthpaw.animagus.Animagus
import uk.cmdrnorthpaw.animagus.misc.Capabilities
import uk.cmdrnorthpaw.animagus.misc.Catalysts
import uk.cmdrnorthpaw.animagus.model.ICatalystCapability
import java.util.concurrent.Callable

class CatalystCapability() : ICatalystCapability {
    override var entity: EntityType<*>? = null
        set(value) = if (field == null) field = value else Unit

    object Storage : Capability.IStorage<ICatalystCapability> {
        override fun readNBT(capability: Capability<ICatalystCapability>?, instance: ICatalystCapability?, side: Direction?, nbt: INBT?) {
            nbt as CompoundNBT
            val entityOptional = EntityType.byKey(nbt.getString("catalystFor"))

            if (entityOptional.isPresent) instance?.entity = entityOptional.get() else instance?.entity = null
        }

        override fun writeNBT(capability: Capability<ICatalystCapability>?, instance: ICatalystCapability?, side: Direction?): INBT? {
            if (instance == null) return null
            val nbt = CompoundNBT()

            nbt.putString("catalystFor", EntityType.getKey(instance.entity).toString())
            return nbt
        }
    }

    class Provider : ICapabilitySerializable<INBT> {
        private val capability = CatalystCapability()
        private val capabilityOptional = LazyOptional.of { CatalystCapability() }

        fun invalidate() = capabilityOptional.invalidate()

        override fun deserializeNBT(nbt: INBT?) {
            if (nbt?.id != CompoundNBT().id) {
                Animagus.logger.error("NBT is not compound, bad read!")
            }

            nbt as CompoundNBT
            Capabilities.CATALYST.readNBT(capability, null, nbt.get("catalyst_capability"))
        }

        override fun serializeNBT(): INBT {
            return CompoundNBT().put("catalyst_capability", Capabilities.CATALYST.writeNBT(capability, null,)!!)!!
        }

        override fun <T : Any?> getCapability(cap: Capability<T>, side: Direction?): LazyOptional<T> {
            return if (cap == Capabilities.CATALYST) capabilityOptional.cast() else LazyOptional.empty()
        }
    }

    companion object : Callable<CatalystCapability> {
        override fun call(): CatalystCapability = CatalystCapability()

        @JvmStatic
        @SubscribeEvent
        fun addCapabilities(event: AttachCapabilitiesEvent<Item>) {
            val item = event.`object`
            val capabilityProvider = Provider()

            Catalysts.map { it.items }.forEach {
                if (it.contains(item)) event.addCapability(ResourceLocation(Animagus.MODID, "catalyst_capability"), capabilityProvider)
            }
            event.addListener(capabilityProvider::invalidate)
        }
    }
}