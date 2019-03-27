package com.deflatedpickle.technopop.blocks

import com.deflatedpickle.technopop.init.ModBlocks
import com.deflatedpickle.technopop.tileentities.TileEntityLamp
import com.deflatedpickle.technopop.tileentities.TileEntityPCBTransmissionLine
import net.minecraft.block.BlockFence
import net.minecraft.block.material.MapColor
import net.minecraft.block.material.Material
import net.minecraft.block.state.IBlockState
import net.minecraft.creativetab.CreativeTabs
import net.minecraft.entity.Entity
import net.minecraft.entity.EntityLivingBase
import net.minecraft.item.EnumDyeColor
import net.minecraft.item.ItemStack
import net.minecraft.tileentity.TileEntity
import net.minecraft.util.BlockRenderLayer
import net.minecraft.util.EnumFacing
import net.minecraft.util.math.AxisAlignedBB
import net.minecraft.util.math.BlockPos
import net.minecraft.world.IBlockAccess
import net.minecraft.world.World

class BlockPCBTransmissionLine : BlockFence(Material.REDSTONE_LIGHT, MapColor.CLOTH) {
    val axisAlignedBB = AxisAlignedBB(0.0, 0.0, 0.0, 1.0, 1.0 / 16.0, 1.0)

    init {
        this.translationKey = "pcb_transmission_line"
        this.creativeTab = CreativeTabs.REDSTONE
    }

    override fun onBlockPlacedBy(worldIn: World, pos: BlockPos, state: IBlockState, placer: EntityLivingBase, stack: ItemStack) {
        val tileEntity = worldIn.getTileEntity(pos)

        if (tileEntity is TileEntityPCBTransmissionLine) {
            tileEntity.colourIndex = pos.x * pos.y * pos.z
        }
    }

    override fun canConnectTo(worldIn: IBlockAccess, pos: BlockPos, facing: EnumFacing): Boolean {
        return worldIn.getBlockState(pos).block === ModBlocks.PCB_TRANSMISSION_LINE
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

    override fun hasTileEntity(state: IBlockState): Boolean {
        return true
    }

    override fun createTileEntity(world: World, state: IBlockState): TileEntity {
        return TileEntityPCBTransmissionLine()
    }
}