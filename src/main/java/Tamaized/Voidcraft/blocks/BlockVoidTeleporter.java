package Tamaized.Voidcraft.blocks;

import java.util.Random;

import Tamaized.Voidcraft.common.voidCraft;
import Tamaized.Voidcraft.registry.IBasicVoid;
import net.minecraft.block.Block;
import net.minecraft.block.BlockBreakable;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public abstract class BlockVoidTeleporter extends BlockBreakable implements IBasicVoid{
	
	private final String name;
	
	public BlockVoidTeleporter(String n) {
		super(Material.portal, false);
		this.setTickRandomly(true);
		this.setLightLevel(0.75F);
		name = n;
		setUnlocalizedName(name);
		GameRegistry.registerBlock(this, "blocks/"+n);
	}
	
	@Override
	public String getName() {
		return name;
	}

	/**
	 * Ticks the block if it's been scheduled
	 */
	@Override
	public void updateTick(World par1World, BlockPos pos, IBlockState state, Random par5Random) {
		super.updateTick(par1World, pos, state, par5Random);
		if (par1World.provider.isSurfaceWorld()) {
			int l;
			int x = pos.getX();
			int y = pos.getY();
			int z = pos.getZ();
			for (l = y; !par1World.doesBlockHaveSolidTopSurface(par1World, new BlockPos(x, l, z)) && l > 0; --l) {
				;
			}
			if (l > 0 && !par1World.isBlockNormalCube(new BlockPos(x, l + 1, z), false)) { } //Prevent pigmen from spawning
		}
	}

	/**
	 * Returns a bounding box from the pool of bounding boxes (this means this
	 * box can change after the pool has been cleared to be reused)
	 */
	@Deprecated
	public AxisAlignedBB getCollisionBoundingBoxFromPool(World par1World, int par2, int par3, int par4) {
		return null;
	}

	/**
	 * Updates the blocks bounds based on its current state. Args: world, x, y,
	 * z
	 */
	@Override
	public abstract void setBlockBoundsBasedOnState(IBlockAccess worldIn, BlockPos pos);

	/**
	 * Is this block (a) opaque and (B) a full 1m cube? This determines whether
	 * or not to render the shared face of two adjacent blocks and also whether
	 * the player can attach torches, redstone wire, etc to this block.
	 */
	@Override
	public boolean isOpaqueCube() {
		return false;
	}

	@Override
	public void onEntityCollidedWithBlock(World worldIn, BlockPos pos, IBlockState state, Entity entityIn) {
		//We handle this elsewhere
	}
	
	public abstract boolean tryToCreatePortal(World par1World, BlockPos pos);

	@Override
	public abstract void onNeighborBlockChange(World worldIn, BlockPos pos, IBlockState state, Block neighborBlock);

	/**
	 * A randomly called display update to be able to add particles or other items for display
	 */
	@SideOnly(Side.CLIENT)
	@Override
	public abstract void randomDisplayTick(World worldIn, BlockPos pos, IBlockState state, Random rand);

	/**
	 * Returns true if the given side of this block type should be rendered, if the adjacent block is at the given
	 * coordinates. Args: blockAccess, x, y, z, side
	 */
	@SideOnly(Side.CLIENT)
	@Override
	public abstract boolean shouldSideBeRendered(IBlockAccess worldIn, BlockPos pos, EnumFacing side);

	/**
	 * Returns the quantity of items to drop on block destruction.
	 */
	public int quantityDropped(Random par1Random) {
		return 0;
	}

}
