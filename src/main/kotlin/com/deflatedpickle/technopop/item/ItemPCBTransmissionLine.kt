package com.deflatedpickle.technopop.item

import net.minecraft.creativetab.CreativeTabs
import net.minecraft.item.Item


class ItemPCBTransmissionLine : Item() {
    init {
        this.translationKey = "pcb_transmission_line"
        this.creativeTab = CreativeTabs.REDSTONE
    }
}