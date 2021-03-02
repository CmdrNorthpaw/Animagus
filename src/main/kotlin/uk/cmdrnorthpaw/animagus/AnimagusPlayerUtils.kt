package uk.cmdrnorthpaw.animagus

import net.minecraft.entity.LivingEntity
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.inventory.EquipmentSlotType
import net.minecraft.item.ItemStack
import net.minecraft.network.IPacket
import net.minecraft.server.management.PlayerList
import net.minecraft.world.World
import com.mojang.datafixers.util.Pair
import net.minecraft.entity.EntityType
import net.minecraft.entity.MobEntity
import net.minecraft.network.play.server.*
import net.minecraft.util.*
import uk.cmdrnorthpaw.animagus.AnimagusPlayerUtils.toAnimagus
import uk.cmdrnorthpaw.animagus.misc.Capabilities
import java.util.*
import java.util.stream.Collectors

object AnimagusPlayerUtils {
    fun PlayerEntity.toAnimagus() {
        getCapability(Capabilities.ANIMAGUS).ifPresent { capability ->
            if (capability.isInAnimagusForm || !capability.isAnimagus) return@ifPresent
             val form = capability.animagusForm

            form?.let { morph(form); capability.isInAnimagusForm = true }
        }
    }

    private fun PlayerEntity.morph(type: EntityType<*>? = null) {
        val worldKey = this.entityWorld.dimensionKey
        val playerList = this.server?.playerList
        val disguiseEntity = if (type == null) this else type.create(world)

        if (disguiseEntity !is MobEntity && disguiseEntity !is PlayerEntity) {
            Animagus.logger.error("Player ${name.unformattedComponentText} attempted to morph into not a mob! This is *not* allowed")
            return
        }

        playerList?.sendPacketToDimension(SDestroyEntitiesPacket(entityId), worldKey)
        playerList?.sendPacketToDimension(SSpawnObjectPacket(disguiseEntity), worldKey)
        playerList?.sendPacketToDimension(SEntityMetadataPacket(entityId, disguiseEntity.dataManager, true), worldKey)
        playerList?.sendPacketToDimension(SEntityEquipmentPacket(entityId, getEquipment(this)), worldKey)
        playerList?.sendPacketToDimension(SEntityHeadLookPacket(disguiseEntity, (getRotationYawHead() * 256F / 360F).toInt().toByte()), worldKey)

        this.world.playSound(null, posX, posY, posZ, SoundEvents.ENTITY_GUARDIAN_AMBIENT, SoundCategory.PLAYERS, 1F, 2F)
    }

    fun PlayerEntity.toPlayer() {
        getCapability(Capabilities.ANIMAGUS).ifPresent { capability ->
            if (!capability.isInAnimagusForm) return@ifPresent
            morph()

            capability.isInAnimagusForm = false
        }
    }

    private fun getEquipment(entity: LivingEntity): List<Pair<EquipmentSlotType, ItemStack>> {
        return Arrays.stream(EquipmentSlotType.values()).map { Pair(it, entity.getItemStackFromSlot(it)) }.collect(Collectors.toList())
    }


    private inline fun PlayerList.sendPacketToDimension(packet: IPacket<*>, world: RegistryKey<World>) = this.func_232642_a_(packet, world)

}