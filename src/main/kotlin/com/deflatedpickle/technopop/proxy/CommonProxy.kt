package com.deflatedpickle.technopop.proxy

import com.deflatedpickle.technopop.events.ClientEventHandler
import com.deflatedpickle.technopop.init.ModTileEntities
import net.minecraftforge.common.MinecraftForge
import net.minecraftforge.fml.common.event.FMLInitializationEvent
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent

open class CommonProxy {
    open fun preInit(event: FMLPreInitializationEvent) {
        ModTileEntities
    }

    open fun init(event: FMLInitializationEvent) {
    }
}