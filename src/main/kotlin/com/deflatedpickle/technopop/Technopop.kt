package com.deflatedpickle.technopop

import com.deflatedpickle.technopop.proxy.CommonProxy
import net.minecraftforge.fml.common.Mod
import net.minecraftforge.fml.common.Mod.EventHandler
import net.minecraftforge.fml.common.SidedProxy
import net.minecraftforge.fml.common.event.FMLInitializationEvent
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent

@Mod(modid = Reference.MOD_ID, name = Reference.NAME, version = Reference.VERSION, acceptedMinecraftVersions = Reference.ACCEPTED_VERSIONS, dependencies = Reference.DEPENDENCIES, modLanguageAdapter = Reference.ADAPTER)
object Technopop {
    @SidedProxy(clientSide = Reference.CLIENT_PROXY_CLASS, serverSide = Reference.SERVER_PROXY_CLASS)
    var proxy: CommonProxy? = null

    @EventHandler
    fun preInit(event: FMLPreInitializationEvent) {
        proxy!!.preInit(event)
    }

    @EventHandler
    fun init(event: FMLInitializationEvent) {
        proxy!!.init(event)
    }
}