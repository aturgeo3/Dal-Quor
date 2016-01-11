package Tamaized.Voidcraft.blocks;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockBreakable;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public abstract class BlockVoidTeleporter extends BlockBreakable {
	
	public BlockVoidTeleporter() {
		super(Material.portal, false);
		this.setTickRandomly(true);
		this.setLightLevel(0.75F);
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
	public AxisAlignedBB getCollisionBoundingBoxFromPool(World par1World, int par2, int par3, int par4) {
		return null;
	}

	/**
	 * Updates the blocks bounds based on its current state. Args: world, x, y,
	 * z
	 */
	public abstract void setBlockBoundsBasedOnState(IBlockAccess par1IBlockAccess, int par2, int par3, int par4);

	/**
	 * Is this block (a) opaque and (B) a full 1m cube? This determines whether
	 * or not to render the shared face of two adjacent blocks and also whether
	 * the player can attach torches, redstone wire, etc to this block.
	 */
	public boolean isOpaqueCube() {
		return false;
	}

	public void onEntityCollidedWithBlock(World par1World, int par2, int par3, int par4, Entity par5Entity) {
		//We handle this elsewhere
	}
	
	public abstract boolean tryToCreatePortal(World par1World, int par2, int par3, int par4);

	public abstract void onNeighborBlockChange(World par1World, int par2, int par3, int par4, Block par5);

	
	/**
	 * Returns which pass should this block be rendered on. 0 for solids and 1 for alpha
	 */
	@SideOnly(Side.CLIENT)
	public int getRenderBlockPass() {
		return 1;
	}

	/**
	 * A randomly called display update to be able to add particles or other items for display
	 */
	@SideOnly(Side.CLIENT)
	public abstract void randomDisplayTick(World par1World, int par2, int par3, int par4, Random par5Random);

	/**
	 * Returns true if the given side of this block type should be rendered, if the adjacent block is at the given
	 * coordinates. Args: blockAccess, x, y, z, side
	 */
	@SideOnly(Side.CLIENT)
	public abstract boolean shouldSideBeRendered(IBlockAccess par1IBlockAccess, int par2, int par3, int par4, int par5);

	/**
	 * Returns the quantity of items to drop on block destruction.
	 */
	public int quantityDropped(Random par1Random) {
		return 0;
	}

}
