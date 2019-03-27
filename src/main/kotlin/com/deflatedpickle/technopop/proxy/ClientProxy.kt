package com.deflatedpickle.technopop.proxy

import com.deflatedpickle.technopop.event.ClientEventHandler
import net.minecraftforge.common.MinecraftForge
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent

class ClientProxy : CommonProxy() {
    override fun preInit(event: FMLPreInitializationEvent) {
        super.preInit(event)
        MinecraftForge.EVENT_BUS.register(ClientEventHandler())
    }
}