package uk.cmdrnorthpaw.animagus

import net.minecraftforge.eventbus.api.Event
import net.minecraftforge.fml.common.Mod
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext
import org.apache.logging.log4j.LogManager
import java.util.function.Consumer

@Mod(Animagus.MODID)
object Animagus {
    const val MODID = "animagus"
    val logger = LogManager.getLogger()

    init {
        registerEvent(Setup::clientSetup)
        registerEvent(Setup::commonSetup)
    }
}

fun <T: Event> registerEvent(event: Consumer<T>) = FMLJavaModLoadingContext.get().modEventBus.addListener(event)

private object Setup {
    fun clientSetup(event: FMLClientSetupEvent) {
        TODO()
    }

    fun commonSetup(event: FMLCommonSetupEvent) {
        TODO()
    }
}