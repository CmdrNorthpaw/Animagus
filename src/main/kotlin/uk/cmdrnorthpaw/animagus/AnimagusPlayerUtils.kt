package uk.cmdrnorthpaw.animagus

import net.minecraft.entity.LivingEntity
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.inventory.EquipmentSlotType
import net.minecraft.item.ItemStack
import net.minecraft.network.IPacket
import net.minecraft.server.management.PlayerList
import net.minecraft.world.World
import com.mojang.datafixers.util.Pair
import net.minecraft.network.play.server.*
import net.minecraft.util.*
import uk.cmdrnorthpaw.animagus.misc.Capabilities
import java.util.*
import java.util.stream.Collectors

object AnimagusPlayerUtils {
    fun PlayerEntity.toAnimagus() = this.getCapability(Capabilities.ANIMAGUS).ifPresent { capability ->
        if (capability.animagusForm == null || !capability.isAnimagus) return@ifPresent

        val worldKey = this.entityWorld.dimensionKey
        val playerList = this.server?.playerList
        val disguiseEntity = capability.animagusForm?.create(world)

        playerList?.sendPacketToDimension(SDestroyEntitiesPacket(entityId), worldKey)
        playerList?.sendPacketToDimension(SSpawnObjectPacket(disguiseEntity), worldKey)
        playerList?.sendPacketToDimension(SEntityMetadataPacket(entityId, disguiseEntity?.dataManager, true), worldKey)
        playerList?.sendPacketToDimension(SEntityEquipmentPacket(entityId, getEquipment(this)), worldKey)
        playerList?.sendPacketToDimension(SEntityHeadLookPacket(disguiseEntity, (getRotationYawHead() * 256F / 360F).toInt().toByte()), worldKey)

        this.world.playSound(null, posX, posY, posZ, SoundEvents.ENTITY_GUARDIAN_AMBIENT, SoundCategory.PLAYERS, 1F, 2F)
    }

    private fun getEquipment(entity: LivingEntity): List<Pair<EquipmentSlotType, ItemStack>> {
        return Arrays.stream(EquipmentSlotType.values()).map { Pair(it, entity.getItemStackFromSlot(it)) }.collect(Collectors.toList())
    }


    private inline fun PlayerList.sendPacketToDimension(packet: IPacket<*>, world: RegistryKey<World>) = this.func_232642_a_(packet, world)

}