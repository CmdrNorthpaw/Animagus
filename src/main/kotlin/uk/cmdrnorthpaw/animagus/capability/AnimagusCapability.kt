package uk.cmdrnorthpaw.animagus.capability

import net.minecraft.entity.*
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.nbt.CompoundNBT
import net.minecraft.nbt.INBT
import net.minecraft.util.Direction
import net.minecraft.util.ResourceLocation
import net.minecraftforge.common.capabilities.Capability
import net.minecraftforge.common.capabilities.ICapabilitySerializable
import net.minecraftforge.common.util.LazyOptional
import net.minecraftforge.event.AttachCapabilitiesEvent
import net.minecraftforge.event.entity.player.PlayerEvent
import net.minecraftforge.eventbus.api.SubscribeEvent
import uk.cmdrnorthpaw.animagus.Animagus
import uk.cmdrnorthpaw.animagus.misc.Capabilities
import uk.cmdrnorthpaw.animagus.model.IAnimagusCapability
import java.util.concurrent.Callable

class AnimagusCapability : IAnimagusCapability {
    override var isInAnimagusForm: Boolean = false
    override var animagusForm: EntityType<out MobEntity>? = null
        set(value) = if (animagusForm == null) field = value else Unit
    override var isAnimagus: Boolean = false
        set(value) = if (!isAnimagus && value) field = value else Unit

    object Storage : Capability.IStorage<IAnimagusCapability> {
        override fun readNBT(capability: Capability<IAnimagusCapability>?, instance: IAnimagusCapability?, side: Direction?, nbt: INBT?) {
           val compoundNBT = nbt as CompoundNBT

            val isInAnimagusForm = nbt.getBoolean("isInAnimagusForm")
            val isAnimagus = nbt.getBoolean("isAnimagus")
            val animagusFormOptional = EntityType.byKey(nbt.getString("animagusForm"))

            if (animagusFormOptional.isPresent && animagusFormOptional.get().isType<EntityType<out MobEntity>>()) {
                instance?.isInAnimagusForm = isInAnimagusForm
                instance?.isAnimagus = isAnimagus
                instance?.animagusForm = animagusFormOptional.get() as EntityType<out MobEntity>
            } else Animagus.logger.error("Bad EntityType read from NBT!")
        }

        private inline fun <reified T> Any.isType(): Boolean = this is T

        override fun writeNBT(capability: Capability<IAnimagusCapability>?, instance: IAnimagusCapability?, side: Direction?): INBT? {
            val nbt = CompoundNBT()
            if (instance == null) return null

            nbt.putBoolean("isInAnimagusForm", instance.isInAnimagusForm)
            nbt.putBoolean("isAnimagus", instance.isAnimagus)
            nbt.putString("animagusForm", EntityType.getKey(instance.animagusForm).toString())

            return nbt
        }
    }

    class Provider : ICapabilitySerializable<INBT> {
        private val capability = AnimagusCapability()
        private val capabilityOptional = LazyOptional.of { capability }

        fun invalidate() = capabilityOptional.invalidate()

        override fun deserializeNBT(nbt: INBT?) {
            if (nbt?.id != CompoundNBT().id) {
                Animagus.logger.warn("Got unexpected NBT $nbt")
                return
            }

            val compoundNBT = nbt as CompoundNBT
            Capabilities.ANIMAGUS?.readNBT(capability, null, compoundNBT.get("animagus_capability"))
        }

        override fun <T : Any?> getCapability(cap: Capability<T>, side: Direction?): LazyOptional<T> {
            return if (cap == Capabilities.ANIMAGUS) capabilityOptional.cast() else LazyOptional.empty()
        }

        override fun serializeNBT(): INBT {
            return CompoundNBT().put("animagus_capability", Capabilities.ANIMAGUS.writeNBT(capability, null)!!)!!
        }
    }

    companion object : Callable<IAnimagusCapability> {

        @JvmStatic
        override fun call(): IAnimagusCapability = AnimagusCapability()

        @JvmStatic
        @SubscribeEvent
        fun onAttachCapability(event: AttachCapabilitiesEvent<Entity>) {
            val entity = event.`object`
            val capabilityProvider = Provider()
            if (entity is PlayerEntity && !entity.getCapability(Capabilities.ANIMAGUS).isPresent) {
                event.addCapability(ResourceLocation(Animagus.MODID, "animagus_capability"), capabilityProvider)
                event.addListener(capabilityProvider::invalidate)
            }
        }

        @JvmStatic
        @SubscribeEvent
        fun onDeath(event: PlayerEvent.Clone) {
            val animagusCapability = event.original.getCapability(Capabilities.ANIMAGUS).ifPresent {
                if (event.isWasDeath) {
                    event.entityLiving.copyDataFromOld(event.original)
                }
            }
        }
    }
}