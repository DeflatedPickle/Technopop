package com.deflatedpickle.technopop.init

import com.deflatedpickle.technopop.Reference
import com.deflatedpickle.technopop.tileentities.TileEntityLamp
import net.minecraftforge.fml.common.registry.GameRegistry

object ModTileEntities {
    init {
        GameRegistry.registerTileEntity(TileEntityLamp::class.java, Reference.MOD_ID + "tileentity_lamp")
    }
}