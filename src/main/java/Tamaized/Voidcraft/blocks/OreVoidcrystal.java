package Tamaized.Voidcraft.blocks;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.entity.boss.EntityDragon;
import net.minecraft.item.Item;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.common.util.ForgeDirection;
import Tamaized.Voidcraft.common.voidCraft;
import Tamaized.Voidcraft.common.client.VoidCraftClientProxy;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class OreVoidcrystal extends Block {
	
	@SideOnly(Side.CLIENT)
	private IIcon iconEnd;
	@SideOnly(Side.CLIENT)
	private IIcon iconVoid;
	@SideOnly(Side.CLIENT)
	private IIcon overlay;
	
	public OreVoidcrystal(Material Material) {
		super(Material);
	}
	
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister iconRegister){
		iconEnd = iconRegister.registerIcon("minecraft:end_stone");
		iconVoid = iconRegister.registerIcon("minecraft:bedrock");
		overlay = iconRegister.registerIcon("voidcraft:VoidCrystalOre");
	}
	
	public IIcon getOverlay(){
		return overlay;
	}

	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int metadata){
		if(Minecraft.getMinecraft().thePlayer != null && Minecraft.getMinecraft().thePlayer.worldObj != null) return Minecraft.getMinecraft().thePlayer.worldObj.provider.dimensionId == voidCraft.dimensionIdVoid ? iconVoid : iconEnd;
		else return iconEnd;
	}
	
	@Override
    public boolean renderAsNormalBlock(){
		return false;
	}
	
	@Override
    public boolean isOpaqueCube(){
		return false;
	}
	
	@SideOnly(Side.CLIENT)
	@Override
    public int getRenderType(){
		return VoidCraftClientProxy.OreRenderType;
	}
	
	@Override
	public boolean isSideSolid(IBlockAccess world, int x, int y, int z, ForgeDirection side){
		return true;
	}
	
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
