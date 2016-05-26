package Tamaized.Voidcraft.blocks;

import java.util.Random;

import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.entity.boss.EntityDragon;
import net.minecraft.item.Item;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumWorldBlockLayer;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import Tamaized.Voidcraft.common.voidCraft;

public class OreVoidcrystal extends BasicVoidBlock {
	
	public static final PropertyBool VOID = PropertyBool.create("void");
	
	public OreVoidcrystal(Material Material, String s) {
		super(Material, s);
		this.setDefaultState(this.blockState.getBaseState().withProperty(VOID, true));
	}
	
	public IBlockState getStateVoidFalse(){
		return getDefaultState().withProperty(VOID, false);
	}
	
	@Override
    public boolean isOpaqueCube(){
		return false;
	}
	
	@Override
	public void onBlockAdded(World world, BlockPos pos, IBlockState state){
		if(world.provider.getDimensionId() == voidCraft.dimensionIdVoid && !state.getValue(VOID)) world.setBlockState(pos, state.withProperty(VOID, true), 2);
		else if(world.provider.getDimensionId() != voidCraft.dimensionIdVoid && state.getValue(VOID)) world.setBlockState(pos, state.withProperty(VOID, false), 2);
	}
	
	@Override
	protected BlockState createBlockState(){
		return new BlockState(this, new IProperty[]{VOID});
	}
	
	@SideOnly(Side.CLIENT)
	public IBlockState getStateForEntityRender(IBlockState state){
		return this.getDefaultState().withProperty(VOID, false);
	}
	
	/**
	 * Convert the BlockState into the correct metadata value
	 */
	@Override
	public int getMetaFromState(IBlockState state){
		return state.getValue(VOID) ? 1 : 0;
	}
	
	@Override
	public IBlockState getStateFromMeta(int meta){
		return this.getDefaultState().withProperty(VOID, meta == 0 ? false : true);
	}
	
	@Override
	public boolean isSideSolid(IBlockAccess world, BlockPos pos, EnumFacing side){
		return true;
	}
	
	@SideOnly(Side.CLIENT)
    public EnumWorldBlockLayer getBlockLayer()
    {
        return EnumWorldBlockLayer.CUTOUT;
    }
	
	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune){
		return voidCraft.items.voidcrystal;
	}
	
	public int quantityDropped(Random random){
		return 1 + random.nextInt(3);
	}
	
	@Override
	public boolean canEntityDestroy(IBlockAccess world, BlockPos pos, Entity entity){
		if (entity instanceof EntityDragon) return false;        
		return true;
	}
	
	public boolean canCreatureSpawn(EnumCreatureType type, IBlockAccess world, int x, int y, int z){
        return true;
    }

}
