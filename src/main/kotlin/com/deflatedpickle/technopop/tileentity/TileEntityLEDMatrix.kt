package com.deflatedpickle.technopop.tileentity

import net.minecraft.block.state.IBlockState
import net.minecraft.nbt.NBTTagCompound
import net.minecraft.network.play.server.SPacketUpdateTileEntity
import net.minecraft.tileentity.TileEntity
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World

open class TileEntityLEDMatrix : TileEntity() {
    var one = false
    set(value) {
        field = value

        if (this.world != null) {
            markDirty()
            world.notifyBlockUpdate(pos, world.getBlockState(pos), world.getBlockState(pos), 3)
        }
    }

    var two = false
        set(value) {
            field = value

            if (this.world != null) {
                markDirty()
                world.notifyBlockUpdate(pos, world.getBlockState(pos), world.getBlockState(pos), 3)
            }
        }

    var three = false
        set(value) {
            field = value

            if (this.world != null) {
                markDirty()
                world.notifyBlockUpdate(pos, world.getBlockState(pos), world.getBlockState(pos), 3)
            }
        }

    var four = false
        set(value) {
            field = value

            if (this.world != null) {
                markDirty()
                world.notifyBlockUpdate(pos, world.getBlockState(pos), world.getBlockState(pos), 3)
            }
        }

    override fun writeToNBT(compound: NBTTagCompound): NBTTagCompound {
        super.writeToNBT(compound)
        compound.setBoolean("one", one)
        compound.setBoolean("two", two)
        compound.setBoolean("three", three)
        compound.setBoolean("four", four)
        return compound
    }

    override fun readFromNBT(compound: NBTTagCompound) {
        super.readFromNBT(compound)
        this.one = compound.getBoolean("one")
        this.two = compound.getBoolean("two")
        this.three = compound.getBoolean("three")
        this.four = compound.getBoolean("four")
    }

    override fun getUpdateTag(): NBTTagCompound {
        return writeToNBT(NBTTagCompound())
    }

    override fun getUpdatePacket(): SPacketUpdateTileEntity {
        return SPacketUpdateTileEntity(pos, 0, updateTag)
    }
}