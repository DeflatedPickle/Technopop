package com.deflatedpickle.technopop.proxy

import com.deflatedpickle.technopop.event.ClientEventHandler
import com.deflatedpickle.technopop.init.ModRenders
import net.minecraftforge.common.MinecraftForge
import net.minecraftforge.fml.common.event.FMLInitializationEvent
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent

class ClientProxy : CommonProxy() {
    override fun preInit(event: FMLPreInitializationEvent) {
        super.preInit(event)
        MinecraftForge.EVENT_BUS.register(ClientEventHandler())
    }

    override fun init(event: FMLInitializationEvent) {
        super.init(event)
        ModRenders
    }
}