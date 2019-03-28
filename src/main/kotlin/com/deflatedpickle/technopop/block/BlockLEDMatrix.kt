package com.deflatedpickle.technopop.block

import com.deflatedpickle.technopop.api.block.IPCBComponent
import com.deflatedpickle.technopop.init.ModTileEntities
import com.deflatedpickle.technopop.tileentity.TileEntityLEDMatrix
import com.deflatedpickle.technopop.tileentity.TileEntityLamp
import net.minecraft.block.Block
import net.minecraft.block.material.Material
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
        val tileEntity = worldIn.getTileEntity(pos)

        if (tileEntity is TileEntityLEDMatrix) {
            val x = pos.subtract(fromPos).x
            val z = pos.subtract(fromPos).z

            when {
                // North
                z == 1 -> tileEntity.one = worldIn.isBlockPowered(fromPos)
                // East
                x == -1 -> tileEntity.two = worldIn.isBlockPowered(fromPos)
                // South
                z == -1 -> tileEntity.three = worldIn.isBlockPowered(fromPos)
                // West
                x == 1 -> tileEntity.four = worldIn.isBlockPowered(fromPos)
            }
        }
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