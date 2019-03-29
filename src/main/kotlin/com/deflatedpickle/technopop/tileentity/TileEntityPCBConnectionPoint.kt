package com.deflatedpickle.technopop.tileentity

import net.minecraft.nbt.NBTTagCompound
import net.minecraft.nbt.NBTTagIntArray
import net.minecraft.nbt.NBTTagList
import net.minecraft.nbt.NBTUtil
import net.minecraft.network.NetworkManager
import net.minecraft.network.play.server.SPacketUpdateTileEntity
import net.minecraft.tileentity.TileEntity
import net.minecraft.util.math.BlockPos
import net.minecraftforge.common.util.Constants

class TileEntityPCBConnectionPoint : TileEntity() {
    val connections = mutableListOf<BlockPos>()

    override fun writeToNBT(compound: NBTTagCompound): NBTTagCompound {
        super.writeToNBT(compound)
        compound.setTag("connections", NBTTagList().apply {
            for (i in connections) {
                this.appendTag(NBTUtil.createPosTag(i))
            }
        })
        return compound
    }

    override fun readFromNBT(compound: NBTTagCompound) {
        super.readFromNBT(compound)

        val nbtTagList = compound.getTagList("connections", Constants.NBT.TAG_COMPOUND)

        this.connections.clear()
        for (i in nbtTagList) {
            this.connections.add(NBTUtil.getPosFromTag(nbtTagList.getCompoundTagAt(nbtTagList.indexOf(i))))
        }
    }

    override fun getUpdateTag(): NBTTagCompound {
        return writeToNBT(NBTTagCompound())
    }

    override fun getUpdatePacket(): SPacketUpdateTileEntity {
        return SPacketUpdateTileEntity(pos, 0, updateTag)
    }

    override fun onDataPacket(net: NetworkManager, pkt: SPacketUpdateTileEntity) {
        readFromNBT(pkt.nbtCompound)
    }
}