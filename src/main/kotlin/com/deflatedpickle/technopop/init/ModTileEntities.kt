package com.deflatedpickle.technopop.init

import com.deflatedpickle.technopop.Reference
import com.deflatedpickle.technopop.tileentity.TileEntityLEDMatrix
import com.deflatedpickle.technopop.tileentity.TileEntityLamp
import com.deflatedpickle.technopop.tileentity.TileEntityPCBTransmissionLine
import net.minecraftforge.fml.common.registry.GameRegistry

object ModTileEntities {
    init {
        GameRegistry.registerTileEntity(TileEntityLamp::class.java, Reference.MOD_ID + "tileentity_lamp")
        GameRegistry.registerTileEntity(TileEntityPCBTransmissionLine::class.java, Reference.MOD_ID + "tileentity_pcb_transmission_line")
        GameRegistry.registerTileEntity(TileEntityLEDMatrix::class.java, Reference.MOD_ID + "tileentity_led_matrix")
    }
}