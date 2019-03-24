package com.deflatedpickle.technopop.blocks

import net.minecraft.block.Block
import net.minecraft.block.material.Material
import net.minecraft.block.properties.PropertyBool
import net.minecraft.block.state.BlockStateContainer
import net.minecraft.block.state.IBlockState
import net.minecraft.creativetab.CreativeTabs
import net.minecraft.util.math.BlockPos
import net.minecraft.world.IBlockAccess
import net.minecraft.world.World

class BlockLamp : Block(Material.REDSTONE_LIGHT) {
    companion object {
        val power = PropertyBool.create("power")
    }

    init {
        this.translationKey = "lamp"
        this.creativeTab = CreativeTabs.REDSTONE

        this.defaultState = this.defaultState.withProperty(power, false)
    }

    override fun getStateFromMeta(meta: Int): IBlockState {
        return this.defaultState.withProperty(power, meta == 1)
    }

    override fun getMetaFromState(state: IBlockState): Int {
        return if (state.getValue(power)) 1 else 0
    }

    override fun createBlockState(): BlockStateContainer {
        return BlockStateContainer(this, power)
    }

    override fun getLightValue(state: IBlockState, worldIn: IBlockAccess, pos: BlockPos): Int {
        return if (state.getValue(power)) 15 else 0
    }

    override fun neighborChanged(state: IBlockState, worldIn: World, pos: BlockPos, blockIn: Block, fromPos: BlockPos) {
        if (worldIn.isBlockPowered(pos) != state.getValue(power)) {
            worldIn.setBlockState(pos, state.withProperty(power, !state.getValue(power)), 3)
        }
    }
}