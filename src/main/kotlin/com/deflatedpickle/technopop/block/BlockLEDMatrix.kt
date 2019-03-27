package com.deflatedpickle.technopop.block

import com.deflatedpickle.technopop.api.block.IPCBComponent
import net.minecraft.block.Block
import net.minecraft.block.material.Material
import net.minecraft.block.state.IBlockState
import net.minecraft.creativetab.CreativeTabs
import net.minecraft.util.BlockRenderLayer
import net.minecraft.util.math.AxisAlignedBB
import net.minecraft.util.math.BlockPos
import net.minecraft.world.IBlockAccess

class BlockLEDMatrix : Block(Material.REDSTONE_LIGHT), IPCBComponent {
    val axisAlignedBB = AxisAlignedBB(0.0, 0.0, 0.0, 1.0, 4.0 / 16.0, 1.0)

    init {
        this.translationKey = "led_matrix_2x2"
        this.creativeTab = CreativeTabs.REDSTONE
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