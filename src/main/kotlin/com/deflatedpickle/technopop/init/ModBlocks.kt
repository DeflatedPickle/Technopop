package com.deflatedpickle.technopop.init

import com.deflatedpickle.technopop.Reference
import com.deflatedpickle.technopop.blocks.BlockLEDMatrix
import com.deflatedpickle.technopop.blocks.BlockLamp
import com.deflatedpickle.technopop.blocks.BlockMolexCable
import com.deflatedpickle.technopop.blocks.BlockPCB
import com.github.upcraftlp.glasspane.api.registry.AutoRegistry

@AutoRegistry(Reference.MOD_ID)
object ModBlocks {
    @JvmField
    val LAMP = BlockLamp()

    @JvmField
    val LED_MATRIX_2x2 = BlockLEDMatrix()

    @JvmField
    val PCB = BlockPCB()

    @JvmField
    val MOLEX_CABLE = BlockMolexCable()
}