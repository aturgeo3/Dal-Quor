package Tamaized.Voidcraft.blocks;

import java.util.Random;

import Tamaized.TamModized.blocks.TamBlock;
import Tamaized.Voidcraft.voidCraft;
import Tamaized.Voidcraft.entity.EntityVoidBoss;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.Teleporter;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;

public class BlockRealityHole extends TamBlock {

	public BlockRealityHole(CreativeTabs tab, Material material, String n, float hardness) {
		super(tab, material, n, hardness);
		setLightLevel(1.0F);
		setResistance(100);
	}

	/**
	 * Triggered whenever an entity collides with this block (enters into the block)
	 */
	public void onEntityWalk(World worldIn, BlockPos pos, Entity entityIn) {
		/*
		 * if(!worldIn.isRemote){ if(entityIn instanceof EntityPlayerMP){ EntityPlayerMP player = ((EntityPlayerMP) entityIn); player.mcServer.getPlayerList().transferPlayerToDimension(player, 0, new RealityTeleporter(player.mcServer.worldServerForDimension(0), player.getPosition())); } else entityIn.setDead(); }
		 */
	}

	/**
	 * Called When an Entity Collided with the Block
	 */
	public void onEntityCollidedWithBlock(World worldIn, BlockPos pos, IBlockState state, Entity entityIn) {
		if (!worldIn.isRemote) {
			if (entityIn instanceof EntityPlayerMP) {
				EntityPlayerMP player = ((EntityPlayerMP) entityIn);
				if (entityIn.dimension == voidCraft.config.getDimensionIDxia()) {
					entityIn.setPositionAndUpdate(52.5, 61, 4.5);
				} else {
					if (voidCraft.config.getRealityWhiteList().size() > 0) {
						Random rand = worldIn.rand;
						int i = rand.nextInt(voidCraft.config.getRealityWhiteList().size());
						int dim = voidCraft.config.getRealityWhiteList().get(i);
						if (player.mcServer.worldServerForDimension(dim) != null) {
							player.mcServer.getPlayerList().transferPlayerToDimension(player, dim, new RealityTeleporter(player.mcServer.worldServerForDimension(dim), player.getPosition()));
						}
						// else new RealityTeleporter(player.getServerWorld(), player.getPosition()).placeInPortal(player, player.rotationYaw);
					}
				}
			} else if (!(entityIn instanceof EntityVoidBoss)) entityIn.setDead();
		}
	}

	/**
	 * Returns a bounding box from the pool of bounding boxes (this means this box can change after the pool has been cleared to be reused)
	 */
	@Override
	public AxisAlignedBB getCollisionBoundingBox(IBlockState blockState, World worldIn, BlockPos pos) {
		return null;
	}

	private class RealityTeleporter extends Teleporter {

		private int x;
		private int y;
		private int z;

		public RealityTeleporter(WorldServer worldIn, BlockPos pos) {
			super(worldIn);
			x = pos.getX();
			y = pos.getY();
			z = pos.getZ();
		}

		public void placeInPortal(Entity entityIn, float rotationYaw) {
			World w = entityIn.worldObj;
			x += (w.rand.nextInt(400) - 200);
			z += (w.rand.nextInt(400) - 200);
			while (!w.isAirBlock(new BlockPos(x, y, z)) && !w.isAirBlock(new BlockPos(x, y + 1, z))) { // TODO: fix this later
				y++;
				// System.out.println(x+":"+y+":"+z);
			}
			entityIn.setPosition(x, y, z);
		}

	}

}
