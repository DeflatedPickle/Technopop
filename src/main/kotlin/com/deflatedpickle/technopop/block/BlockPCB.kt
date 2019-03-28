package com.deflatedpickle.technopop.block

import net.minecraft.block.Block
import net.minecraft.block.material.Material
import net.minecraft.block.state.BlockFaceShape
import net.minecraft.block.state.IBlockState
import net.minecraft.creativetab.CreativeTabs
import net.minecraft.util.BlockRenderLayer
import net.minecraft.util.EnumFacing
import net.minecraft.util.math.AxisAlignedBB
import net.minecraft.util.math.BlockPos
import net.minecraft.world.IBlockAccess

class BlockPCB : Block(Material.REDSTONE_LIGHT) {
    val axisAlignedBB = AxisAlignedBB(0.0, 0.0, 0.0, 1.0, 1.0 / 16.0, 1.0)

    init {
        this.translationKey = "pcb"
        this.creativeTab = CreativeTabs.REDSTONE
    }

    override fun canPlaceTorchOnTop(state: IBlockState, world: IBlockAccess, pos: BlockPos): Boolean {
        return false
    }

    override fun isTopSolid(state: IBlockState): Boolean {
        return false
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
}