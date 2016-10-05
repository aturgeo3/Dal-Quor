package Tamaized.Voidcraft.blocks;

import java.util.ArrayList;

import Tamaized.TamModized.blocks.TamBlockFarmland;
import Tamaized.Voidcraft.voidCraft;
import Tamaized.Voidcraft.blocks.tileentity.TileEntityFakeBedrockFarmland;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.IPlantable;

public class BlockFakeBedrockFarmland extends TamBlockFarmland {

	public BlockFakeBedrockFarmland(CreativeTabs tab, Material material, String n, float hardness) {
		super(tab, material, n, hardness);
	}

	@Override
	protected IBlockState getParentBlockState() {
		return voidCraft.blocks.blockFakeBedrock.getDefaultState();
	}

	@Override
	protected Block getWaterBlock() {
		return voidCraft.fluids.voidFluidBlock;
	}

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		return new TileEntityFakeBedrockFarmland();
	}
	
	@Override
	protected void updateTiles(TileEntity oldTile, TileEntity newTile) {
		if(oldTile != null && newTile != null && oldTile instanceof TileEntityFakeBedrockFarmland && newTile instanceof TileEntityFakeBedrockFarmland){
			((TileEntityFakeBedrockFarmland)newTile).setAlteration(((TileEntityFakeBedrockFarmland)oldTile).getAlteration());
		}
	}

	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, ItemStack heldItem, EnumFacing side, float hitX, float hitY, float hitZ) {
		if (heldItem != null && worldIn.isAirBlock(pos.up())) {
			TileEntity t = worldIn.getTileEntity(pos);
			if (!(t instanceof TileEntityFakeBedrockFarmland)) return false;
			TileEntityFakeBedrockFarmland te = (TileEntityFakeBedrockFarmland) t;
			if(te.getAlteration() != TileEntityFakeBedrockFarmland.Alteration.NORMAL) return false;
			Item item = heldItem.getItem();
			if (item == Items.REDSTONE) {
				setColor(te, TileEntityFakeBedrockFarmland.Alteration.REDSTONE, heldItem);
				return true;
			}
			if (item == Items.DYE && heldItem.getMetadata() == 4) {
				setColor(te, TileEntityFakeBedrockFarmland.Alteration.LAPIS, heldItem);
				return true;
			}
			if (item == voidCraft.items.diamondDust) {
				setColor(te, TileEntityFakeBedrockFarmland.Alteration.DIAMOND, heldItem);
				return true;
			}
			if (item == Items.EMERALD) {
				setColor(te, TileEntityFakeBedrockFarmland.Alteration.EMERALD, heldItem);
				return true;
			}
			if (item == voidCraft.items.goldDust) {
				setColor(te, TileEntityFakeBedrockFarmland.Alteration.GOLD, heldItem);
				return true;
			}
		}
		return false;
	}

	private static void setColor(TileEntityFakeBedrockFarmland tile, TileEntityFakeBedrockFarmland.Alteration alter, ItemStack stack) {
		tile.setAlteration(alter);
		stack.stackSize--;
	}

	@Override
	protected ArrayList<IPlantable> getPlantList() {
		ArrayList<IPlantable> list = new ArrayList<IPlantable>();
		list.add(voidCraft.blocks.etherealPlant);
		list.add(voidCraft.items.etherealSeed);
		return list;
	}

}
