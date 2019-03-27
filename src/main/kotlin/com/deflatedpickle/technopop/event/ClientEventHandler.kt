package com.deflatedpickle.technopop.event

import com.deflatedpickle.technopop.init.ModBlocks
import com.deflatedpickle.technopop.tileentity.TileEntityLamp
import com.deflatedpickle.technopop.tileentity.TileEntityPCBTransmissionLine
import net.minecraft.client.renderer.color.IBlockColor
import net.minecraft.client.renderer.color.IItemColor
import net.minecraft.item.EnumDyeColor
import net.minecraftforge.client.event.ColorHandlerEvent
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent

class ClientEventHandler {
    @SubscribeEvent
    fun registerColorHandlers(event: ColorHandlerEvent.Item) {
        // Lamp
        event.blockColors.registerBlockColorHandler(IBlockColor { state, worldIn, pos, tintIndex ->
            val tileEntity = worldIn!!.getTileEntity(pos!!)

            if (tileEntity is TileEntityLamp) {
                return@IBlockColor tileEntity.colourIndex
            }
            return@IBlockColor EnumDyeColor.WHITE.colorValue
        }, ModBlocks.LAMP)

        event.itemColors.registerItemColorHandler(IItemColor { stack, tintIndex ->
            if (stack.hasTagCompound()) {
                return@IItemColor stack.tagCompound!!.getInteger("colour")
            }
            return@IItemColor EnumDyeColor.WHITE.colorValue
        }, ModBlocks.LAMP)

        // PCB Transmission Line
        event.blockColors.registerBlockColorHandler(IBlockColor { state, worldIn, pos, tintIndex ->
            val tileEntity = worldIn!!.getTileEntity(pos!!)

            if (tileEntity is TileEntityPCBTransmissionLine) {
                return@IBlockColor tileEntity.colourIndex
            }
            return@IBlockColor EnumDyeColor.WHITE.colorValue
        }, ModBlocks.PCB_TRANSMISSION_LINE)
    }
}