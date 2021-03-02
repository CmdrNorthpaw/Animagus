package uk.cmdrnorthpaw.animagus

import net.minecraftforge.eventbus.api.Event
import net.minecraftforge.fml.common.Mod
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext
import org.apache.logging.log4j.LogManager
import thedarkcolour.kotlinforforge.KotlinModLoadingContext
import thedarkcolour.kotlinforforge.forge.MOD_BUS
import uk.cmdrnorthpaw.animagus.data.DataGenerators
import uk.cmdrnorthpaw.animagus.events.DropCatHair
import uk.cmdrnorthpaw.animagus.items.AnimagusItems
import uk.cmdrnorthpaw.animagus.misc.Catalysts
import java.util.function.Consumer

@Mod(Animagus.MODID)
object Animagus {
    const val MODID = "animagus"
    val logger = LogManager.getLogger()

    init {
        registerEvent(Setup::clientSetup)
        registerEvent(Setup::commonSetup)

        registerEvent(DataGenerators::gatherData)
        registerEvent(DropCatHair::dropCatHair)
        registerEvent(Catalysts::registerInternalCatalysts)

        AnimagusItems.registry.register(MOD_BUS)
    }
}

fun <T: Event> registerEvent(event: Consumer<T>) = MOD_BUS.addListener(event)
private object Setup {
    fun clientSetup(event: FMLClientSetupEvent) {
    }

    fun commonSetup(event: FMLCommonSetupEvent) {
    }
}