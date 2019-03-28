package com.deflatedpickle.technopop.block

import com.deflatedpickle.technopop.tileentity.TileEntityLamp
import net.minecraft.block.Block
import net.minecraft.block.material.Material
import net.minecraft.block.properties.PropertyBool
import net.minecraft.block.state.BlockStateContainer
import net.minecraft.block.state.IBlockState
import net.minecraft.client.util.ITooltipFlag
import net.minecraft.creativetab.CreativeTabs
import net.minecraft.entity.EntityLivingBase
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.item.EnumDyeColor
import net.minecraft.item.ItemStack
import net.minecraft.nbt.NBTTagCompound
import net.minecraft.tileentity.TileEntity
import net.minecraft.util.EnumFacing
import net.minecraft.util.EnumHand
import net.minecraft.util.NonNullList
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.RayTraceResult
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

    override fun addInformation(stack: ItemStack, worldIn: World?, tooltip: MutableList<String>, flagIn: ITooltipFlag) {
        if (stack.hasTagCompound() && stack.tagCompound!!.hasKey("colour")) {
            tooltip.add("Colour: ${stack.tagCompound!!.getInteger("colour")}")
        }
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

    override fun onBlockPlacedBy(worldIn: World, pos: BlockPos, state: IBlockState, placer: EntityLivingBase, stack: ItemStack) {
        val tileEntity = worldIn.getTileEntity(pos)

        if (tileEntity is TileEntityLamp && stack.hasTagCompound() && stack.tagCompound!!.hasKey("colour")) {
            tileEntity.colourIndex = stack.tagCompound!!.getInteger("colour")
        }
    }

    override fun canConnectRedstone(state: IBlockState, world: IBlockAccess, pos: BlockPos, side: EnumFacing?): Boolean {
        return true
    }

    override fun getStateForPlacement(world: World, pos: BlockPos, facing: EnumFacing, hitX: Float, hitY: Float, hitZ: Float, meta: Int, placer: EntityLivingBase, hand: EnumHand): IBlockState {
        return super.getStateForPlacement(world, pos, facing, hitX, hitY, hitZ, meta, placer, hand).withProperty(power, world.isBlockPowered(pos))
    }

    override fun getPickBlock(state: IBlockState, target: RayTraceResult, world: World, pos: BlockPos, player: EntityPlayer): ItemStack {
        val tileEntity = world.getTileEntity(pos)

        var colourIndex = EnumDyeColor.WHITE.colorValue
        if (tileEntity is TileEntityLamp) {
            colourIndex = tileEntity.colourIndex
        }

        return super.getPickBlock(state, target, world, pos, player).apply {
            if (!hasTagCompound()) {
                tagCompound = NBTTagCompound()
            }
            tagCompound!!.setInteger("colour", colourIndex)
        }
    }

    override fun getSubBlocks(itemIn: CreativeTabs, items: NonNullList<ItemStack>) {
        for (i in EnumDyeColor.values()) {
            items.add(ItemStack(this).apply {
                tagCompound = NBTTagCompound().apply {
                    setInteger("colour", i.colorValue)
                }
            })
        }
    }

    override fun hasTileEntity(state: IBlockState): Boolean {
        return true
    }

    override fun createTileEntity(world: World, state: IBlockState): TileEntity {
        return TileEntityLamp()
    }
}