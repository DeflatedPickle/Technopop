package com.deflatedpickle.technopop.init

import com.deflatedpickle.technopop.Reference
import com.deflatedpickle.technopop.item.ItemPCBTransmissionLine
import com.github.upcraftlp.glasspane.api.registry.AutoRegistry

@AutoRegistry(Reference.MOD_ID)
object ModItems {
    @JvmField
    val PCB_TRANSMISSION_LINE  = ItemPCBTransmissionLine()
}