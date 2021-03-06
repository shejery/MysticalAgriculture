package com.blakebr0.mysticalagriculture.blocks.furnace;

import java.util.List;
import java.util.Random;

import com.blakebr0.cucumber.lib.Colors;
import com.blakebr0.mysticalagriculture.blocks.ModBlocks;
import com.blakebr0.mysticalagriculture.lib.Tooltips;
import com.blakebr0.mysticalagriculture.tileentity.furnace.TileIntermediumFurnace;

import net.minecraft.block.state.IBlockState;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockIntermediumFurnace extends BlockEssenceFurnace {

    public BlockIntermediumFurnace(boolean isBurning, String name) {
        super(isBurning, name);
    }
    
    @Override
    public TileEntity createNewTileEntity(World world, int meta) {
    	return new TileIntermediumFurnace();
    }

	@SideOnly(Side.CLIENT)
	@Override
	public void addInformation(ItemStack stack, World world, List<String> tooltip, ITooltipFlag advanced) {
		tooltip.add(Tooltips.EFFICIENCY + Colors.GOLD + "+60%");
	}

	@Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune) {
        return Item.getItemFromBlock(ModBlocks.blockIntermediumFurnace);
    }

	@Override
    public ItemStack getItem(World world, BlockPos pos, IBlockState state) {
        return new ItemStack(ModBlocks.blockIntermediumFurnace);
    }

    public static void setState(boolean active, World world, BlockPos pos) {
        IBlockState state = world.getBlockState(pos);
        TileEntity tile = world.getTileEntity(pos);
        keepInventory = true;

        if (active) {
            world.setBlockState(pos, ModBlocks.blockIntermediumFurnaceActive.getDefaultState().withProperty(FACING, state.getValue(FACING)), 3);
            world.setBlockState(pos, ModBlocks.blockIntermediumFurnaceActive.getDefaultState().withProperty(FACING, state.getValue(FACING)), 3);
        } else {
            world.setBlockState(pos, ModBlocks.blockIntermediumFurnace.getDefaultState().withProperty(FACING, state.getValue(FACING)), 3);
            world.setBlockState(pos, ModBlocks.blockIntermediumFurnace.getDefaultState().withProperty(FACING, state.getValue(FACING)), 3);
        }

        keepInventory = false;

        if (tile != null) {
            tile.validate();
            world.setTileEntity(pos, tile);
        }
    }
}