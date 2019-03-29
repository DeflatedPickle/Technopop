package com.deflatedpickle.technopop.block

import com.deflatedpickle.technopop.api.block.IPCBComponent
import com.deflatedpickle.technopop.init.ModItems
import com.deflatedpickle.technopop.tileentity.TileEntityPCBConnectionPoint
import net.minecraft.block.Block
import net.minecraft.block.material.Material
import net.minecraft.block.state.IBlockState
import net.minecraft.creativetab.CreativeTabs
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.nbt.NBTTagCompound
import net.minecraft.tileentity.TileEntity
import net.minecraft.util.EnumFacing
import net.minecraft.util.EnumHand
import net.minecraft.util.math.AxisAlignedBB
import net.minecraft.util.math.BlockPos
import net.minecraft.world.IBlockAccess
import net.minecraft.world.World

class BlockPCBConnectionPoint : Block(Material.REDSTONE_LIGHT) {
    val axisAlignedBB = AxisAlignedBB(0.0, 0.0, 0.0, 1.0, 1.0 / 16.0, 1.0)

    init {
        this.translationKey = "pcb_connection_point"
        this.creativeTab = CreativeTabs.REDSTONE
    }

    override fun onBlockActivated(worldIn: World, pos: BlockPos, state: IBlockState, playerIn: EntityPlayer, hand: EnumHand, facing: EnumFacing, hitX: Float, hitY: Float, hitZ: Float): Boolean {
        val tileEntity = worldIn.getTileEntity(pos)

        if (tileEntity is TileEntityPCBConnectionPoint) {
            val itemStack = playerIn.getHeldItem(hand)

            if (itemStack.item == ModItems.PCB_TRANSMISSION_LINE) {
                if (!itemStack.hasTagCompound()) {
                    itemStack.tagCompound = NBTTagCompound()
                    itemStack.tagCompound!!.setBoolean("connecting", false)
                }

                if (itemStack.tagCompound!!.getBoolean("connecting")) {
                    itemStack.tagCompound!!.setBoolean("connecting", false)

                    val itemPos = itemStack.tagCompound!!.getIntArray("connectingPosition")
                    val originalTileEntity = worldIn.getTileEntity(BlockPos(itemPos[0], itemPos[1], itemPos[2]))

                    if (originalTileEntity is TileEntityPCBConnectionPoint) {
                        originalTileEntity.connections.add(tileEntity.pos)
                        itemStack.shrink(1)
                    }
                }
                else {
                    itemStack.tagCompound!!.setBoolean("connecting", true)
                    itemStack.tagCompound!!.setIntArray("connectingPosition", intArrayOf(pos.x, pos.y, pos.z))
                }
            }
        }

        return super.onBlockActivated(worldIn, pos, state, playerIn, hand, facing, hitX, hitY, hitZ)
    }

    override fun isFullCube(state: IBlockState): Boolean {
        return false
    }

    override fun isOpaqueCube(state: IBlockState): Boolean {
        return false
    }

    override fun getBoundingBox(state: IBlockState, source: IBlockAccess, pos: BlockPos): AxisAlignedBB {
        return axisAlignedBB
    }

    override fun hasTileEntity(state: IBlockState): Boolean {
        return true
    }

    override fun createTileEntity(world: World, state: IBlockState): TileEntity {
        return TileEntityPCBConnectionPoint()
    }
}