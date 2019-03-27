package com.deflatedpickle.technopop.init

import com.deflatedpickle.technopop.Reference
import com.deflatedpickle.technopop.block.*
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

    @JvmField
    val PCB_TRANSMISSION_LINE = BlockPCBTransmissionLine()
}