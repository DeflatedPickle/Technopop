package com.deflatedpickle.technopop.init

import com.deflatedpickle.technopop.Reference
import com.deflatedpickle.technopop.blocks.BlockLamp
import com.github.upcraftlp.glasspane.api.registry.AutoRegistry

@AutoRegistry(Reference.MOD_ID)
object ModBlocks {
    @JvmField
    val LAMP = BlockLamp()
}