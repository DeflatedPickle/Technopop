package com.deflatedpickle.technopop.tileentity

import com.deflatedpickle.technopop.Technopop
import net.minecraft.block.state.IBlockState
import net.minecraft.item.EnumDyeColor
import net.minecraft.nbt.NBTTagCompound
import net.minecraft.network.play.server.SPacketUpdateTileEntity
import net.minecraft.tileentity.TileEntity
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World

open class TileEntityLamp : TileEntity() {
    var colourIndex = EnumDyeColor.WHITE.colorValue
    set(value) {
        field = value

        if (this.world != null) {
            markDirty()
            world.notifyBlockUpdate(pos, world.getBlockState(pos), world.getBlockState(pos), 3)
        }
    }

    override fun shouldRefresh(world: World, pos: BlockPos, oldState: IBlockState, newSate: IBlockState): Boolean {
        return oldState.block !== newSate.block
    }

    override fun writeToNBT(compound: NBTTagCompound): NBTTagCompound {
        super.writeToNBT(compound)
        compound.setInteger("colour", colourIndex)
        return compound
    }

    override fun readFromNBT(compound: NBTTagCompound) {
        super.readFromNBT(compound)
        this.colourIndex = compound.getInteger("colour")
    }

    override fun getUpdateTag(): NBTTagCompound {
        return writeToNBT(NBTTagCompound().apply {
            this.setInteger("colour", colourIndex)
        })
    }

    override fun getUpdatePacket(): SPacketUpdateTileEntity {
        Technopop.logger.info(updateTag)
        return SPacketUpdateTileEntity(pos, 0, updateTag)
    }
}