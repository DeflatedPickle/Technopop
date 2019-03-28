package com.deflatedpickle.technopop.block

import com.deflatedpickle.technopop.api.block.IPCBComponent
import com.deflatedpickle.technopop.init.ModTileEntities
import com.deflatedpickle.technopop.tileentity.TileEntityLEDMatrix
import com.deflatedpickle.technopop.tileentity.TileEntityLamp
import net.minecraft.block.Block
import net.minecraft.block.material.Material
import net.minecraft.block.state.BlockFaceShape
import net.minecraft.block.state.IBlockState
import net.minecraft.client.renderer.EnumFaceDirection
import net.minecraft.creativetab.CreativeTabs
import net.minecraft.entity.EntityLivingBase
import net.minecraft.item.ItemStack
import net.minecraft.tileentity.TileEntity
import net.minecraft.util.BlockRenderLayer
import net.minecraft.util.EnumFacing
import net.minecraft.util.math.AxisAlignedBB
import net.minecraft.util.math.BlockPos
import net.minecraft.world.IBlockAccess
import net.minecraft.world.World

class BlockLEDMatrix : Block(Material.REDSTONE_LIGHT), IPCBComponent {
    val axisAlignedBB = AxisAlignedBB(0.0, 0.0, 0.0, 1.0, 4.0 / 16.0, 1.0)

    init {
        this.translationKey = "led_matrix_2x2"
        this.creativeTab = CreativeTabs.REDSTONE
    }

    override fun neighborChanged(state: IBlockState, worldIn: World, pos: BlockPos, blockIn: Block, fromPos: BlockPos) {
        onBlockAdded(worldIn, pos, state)
    }

    override fun onBlockAdded(worldIn: World, pos: BlockPos, state: IBlockState) {
        val tileEntity = worldIn.getTileEntity(pos)

        if (tileEntity is TileEntityLEDMatrix) {
            tileEntity.one = worldIn.isSidePowered(pos.offset(EnumFacing.NORTH), EnumFacing.NORTH)
            tileEntity.two = worldIn.isSidePowered(pos.offset(EnumFacing.EAST), EnumFacing.EAST)
            tileEntity.three = worldIn.isSidePowered(pos.offset(EnumFacing.WEST), EnumFacing.WEST)
            tileEntity.four = worldIn.isSidePowered(pos.offset(EnumFacing.SOUTH), EnumFacing.SOUTH)
        }
    }

    override fun canConnectRedstone(state: IBlockState, world: IBlockAccess, pos: BlockPos, side: EnumFacing?): Boolean {
        return side !== EnumFacing.UP
    }

    override fun getBlockFaceShape(worldIn: IBlockAccess, state: IBlockState, pos: BlockPos, face: EnumFacing): BlockFaceShape {
        return BlockFaceShape.UNDEFINED
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

    override fun getRenderLayer(): BlockRenderLayer {
        return BlockRenderLayer.CUTOUT
    }

    override fun hasTileEntity(state: IBlockState): Boolean {
        return true
    }

    override fun createTileEntity(world: World, state: IBlockState): TileEntity {
        return TileEntityLEDMatrix()
    }
}