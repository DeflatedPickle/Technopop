package com.deflatedpickle.technopop.blocks

import com.deflatedpickle.technopop.tileentities.TileEntityLamp
import net.minecraft.block.Block
import net.minecraft.block.material.Material
import net.minecraft.block.properties.PropertyBool
import net.minecraft.block.state.BlockStateContainer
import net.minecraft.block.state.IBlockState
import net.minecraft.creativetab.CreativeTabs
import net.minecraft.entity.EntityLivingBase
import net.minecraft.item.ItemStack
import net.minecraft.util.math.BlockPos
import net.minecraft.world.IBlockAccess
import net.minecraft.world.World

class BlockLEDMatrix : Block(Material.REDSTONE_LIGHT) {
    companion object {
        val power = PropertyBool.create("power")
    }

    init {
        this.translationKey = "led_matrix"
        this.creativeTab = CreativeTabs.REDSTONE

        this.defaultState = this.defaultState.withProperty(power, false)
    }

    override fun getStateFromMeta(meta: Int): IBlockState {
        return this.defaultState.withProperty(BlockLamp.power, meta == 1)
    }

    override fun getMetaFromState(state: IBlockState): Int {
        return if (state.getValue(BlockLamp.power)) 1 else 0
    }

    override fun createBlockState(): BlockStateContainer {
        return BlockStateContainer(this, BlockLamp.power)
    }

    override fun getLightValue(state: IBlockState, worldIn: IBlockAccess, pos: BlockPos): Int {
        return if (state.getValue(BlockLamp.power)) (0.5f * 15).toInt() else 0
    }

    override fun neighborChanged(state: IBlockState, worldIn: World, pos: BlockPos, blockIn: Block, fromPos: BlockPos) {
        if (worldIn.isBlockPowered(pos) != state.getValue(BlockLamp.power)) {
            worldIn.setBlockState(pos, state.withProperty(BlockLamp.power, !state.getValue(BlockLamp.power)), 3)
        }
    }

    override fun onBlockPlacedBy(worldIn: World, pos: BlockPos, state: IBlockState, placer: EntityLivingBase, stack: ItemStack) {
        val tileEntity = worldIn.getTileEntity(pos)

        if (tileEntity is TileEntityLamp && stack.hasTagCompound() && stack.tagCompound!!.hasKey("colour")) {
            tileEntity.colourIndex = stack.tagCompound!!.getInteger("colour")
        }
    }
}