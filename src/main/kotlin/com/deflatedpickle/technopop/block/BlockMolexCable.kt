package com.deflatedpickle.technopop.block

import com.deflatedpickle.technopop.init.ModBlocks
import net.minecraft.block.BlockFence
import net.minecraft.block.material.MapColor
import net.minecraft.block.material.Material
import net.minecraft.block.state.IBlockState
import net.minecraft.creativetab.CreativeTabs
import net.minecraft.entity.Entity
import net.minecraft.util.BlockRenderLayer
import net.minecraft.util.EnumFacing
import net.minecraft.util.math.AxisAlignedBB
import net.minecraft.util.math.BlockPos
import net.minecraft.world.IBlockAccess
import net.minecraft.world.World

class BlockMolexCable : BlockFence(Material.REDSTONE_LIGHT, MapColor.CLOTH) {
    val axisAlignedBB = AxisAlignedBB(0.0, 0.0, 0.0, 1.0, 1.0 / 16.0, 1.0)

    init {
        this.translationKey = "molex_cable"
        this.creativeTab = CreativeTabs.REDSTONE
    }

    override fun canConnectTo(worldIn: IBlockAccess, pos: BlockPos, facing: EnumFacing): Boolean {
        return worldIn.getBlockState(pos).block === ModBlocks.PCB
                || worldIn.getBlockState(pos).block === ModBlocks.MOLEX_CABLE
    }

    override fun getBoundingBox(state: IBlockState, source: IBlockAccess, pos: BlockPos): AxisAlignedBB {
        return axisAlignedBB
    }

    override fun getCollisionBoundingBox(blockState: IBlockState, worldIn: IBlockAccess, pos: BlockPos): AxisAlignedBB {
        return axisAlignedBB
    }

    override fun addCollisionBoxToList(state: IBlockState, worldIn: World, pos: BlockPos, entityBox: AxisAlignedBB, collidingBoxes: MutableList<AxisAlignedBB>, entityIn: Entity?, isActualState: Boolean) {
        addCollisionBoxToList(pos, entityBox, collidingBoxes, axisAlignedBB)
    }

    override fun getRenderLayer(): BlockRenderLayer {
        return BlockRenderLayer.CUTOUT
    }
}