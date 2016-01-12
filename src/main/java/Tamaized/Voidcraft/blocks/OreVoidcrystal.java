package Tamaized.Voidcraft.blocks;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.entity.boss.EntityDragon;
import net.minecraft.item.Item;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.IBlockAccess;
import Tamaized.Voidcraft.common.voidCraft;
import Tamaized.Voidcraft.common.client.VoidCraftClientProxy;

public class OreVoidcrystal extends BasicVoidBlock {
	
	public OreVoidcrystal(Material Material, String s) {
		super(Material, s);
	}
	
	@Override
    public boolean isOpaqueCube(){
		return false;
	}
	/*
	@SideOnly(Side.CLIENT)
	@Override
    public int getRenderType(){
		return VoidCraftClientProxy.OreRenderType;
	}
	*/
	@Override
	public boolean isSideSolid(IBlockAccess world, BlockPos pos, EnumFacing side){
		return true;
	}
	/*
	@Override
    public boolean canRenderInPass(int pass){
		//Set the static var in the client proxy
		VoidCraftClientProxy.renderPass = pass;
		//the block can render in both passes, so return true always
		return true;
	}
	
	@Override
    public int getRenderBlockPass(){
        return 1;
	}
	*/
	public Item getItemDropped(int par1, Random par2Random, int par3){
		return voidCraft.items.voidcrystal;
	}
	
	public int quantityDropped(Random random){
		return 2;
	}
	
	public boolean canEntityDestroy(IBlockAccess world, int x, int y, int z, Entity entity){
		if (entity instanceof EntityDragon) return false;        
		return true;
	}
	
	public boolean canCreatureSpawn(EnumCreatureType type, IBlockAccess world, int x, int y, int z){
        return true;
    }

}
