package uk.cmdrnorthpaw.animagus

import net.minecraftforge.eventbus.api.Event
import net.minecraftforge.fml.common.Mod
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext
import org.apache.logging.log4j.LogManager
import thedarkcolour.kotlinforforge.KotlinModLoadingContext
import uk.cmdrnorthpaw.animagus.items.AnimagusItems
import java.util.function.Consumer

@Mod(Animagus.MODID)
object Animagus {
    const val MODID = "animagus"
    val logger = LogManager.getLogger()

    init {
        registerEvent(Setup::clientSetup)
        registerEvent(Setup::commonSetup)

        AnimagusItems.registry.register(KotlinModLoadingContext.get().getKEventBus())
    }
}

fun <T: Event> registerEvent(event: Consumer<T>) = KotlinModLoadingContext.get().getKEventBus().register(event)

private object Setup {
    fun clientSetup(event: FMLClientSetupEvent) {
        TODO()
    }

    fun commonSetup(event: FMLCommonSetupEvent) {
        TODO()
    }
}