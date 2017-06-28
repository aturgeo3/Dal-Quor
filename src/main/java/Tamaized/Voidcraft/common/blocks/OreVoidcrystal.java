package tamaized.voidcraft.common.blocks;

import Tamaized.TamModized.blocks.TamBlock;
import tamaized.voidcraft.VoidCraft;
import tamaized.voidcraft.common.handlers.ConfigHandler;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.entity.boss.EntityDragon;
import net.minecraft.item.Item;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ChunkCache;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.ReflectionHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.lang.reflect.Field;
import java.util.Random;

public class OreVoidcrystal extends TamBlock {

	public static final PropertyBool VOID = PropertyBool.create("void");
	private static Field world;

	public OreVoidcrystal(CreativeTabs tab, Material material, String n, float hardness) {
		super(tab, material, n, hardness, SoundType.STONE);
		this.setDefaultState(this.blockState.getBaseState().withProperty(VOID, true));
	}

	public IBlockState getStateVoidFalse() {
		return getDefaultState().withProperty(VOID, false);
	}

	@Override
	public boolean isOpaqueCube(IBlockState state) {
		return false;
	}

	@Override
	public IBlockState getActualState(IBlockState state, IBlockAccess worldIn, BlockPos pos) {
		int dim = 0;
		if (worldIn instanceof World)
			dim = ((World) worldIn).provider.getDimension();
		else if (worldIn instanceof ChunkCache) {
			ChunkCache cache = (ChunkCache) worldIn;
			if (world == null)
				world = ReflectionHelper.findField(ChunkCache.class, "world", "field_72815_e");
			try {
				World w = (World) world.get(cache);
				if (w != null && w.provider != null)
					dim = w.provider.getDimension();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
		}
		return getDefaultState().withProperty(VOID, (dim == ConfigHandler.dimensionIdVoid || dim == ConfigHandler.dimensionIdDalQuor));
	}

	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, new IProperty[]{VOID});
	}

	/**
	 * Convert the BlockState into the correct metadata value
	 */
	@Override
	public int getMetaFromState(IBlockState state) {
		return state.getValue(VOID) ? 1 : 0;
	}

	@Override
	public IBlockState getStateFromMeta(int meta) {
		return this.getDefaultState().withProperty(VOID, meta == 0 ? false : true);
	}

	@Override
	public boolean isSideSolid(IBlockState base_state, IBlockAccess world, BlockPos pos, EnumFacing side) {
		return true;
	}

	@SideOnly(Side.CLIENT)
	public BlockRenderLayer getBlockLayer() {
		return BlockRenderLayer.CUTOUT;
	}

	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune) {
		return VoidCraft.items.voidcrystal;
	}

	public int quantityDropped(Random random) {
		return 1 + random.nextInt(3);
	}

	@Override
	public boolean canEntityDestroy(IBlockState state, IBlockAccess world, BlockPos pos, Entity entity) {
		if (entity instanceof EntityDragon)
			return false;
		return true;
	}

	public boolean canCreatureSpawn(EnumCreatureType type, IBlockAccess world, int x, int y, int z) {
		return true;
	}

}
