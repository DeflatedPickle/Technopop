package com.deflatedpickle.technopop.init

import com.deflatedpickle.technopop.render.tileentity.RenderLEDMatrix
import com.deflatedpickle.technopop.tileentity.TileEntityLEDMatrix
import net.minecraftforge.fml.client.registry.ClientRegistry

object ModRenders {
    init {
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityLEDMatrix::class.java, RenderLEDMatrix())
    }
}