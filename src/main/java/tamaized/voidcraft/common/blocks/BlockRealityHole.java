package tamaized.voidcraft.common.blocks;

import tamaized.tammodized.common.blocks.TamBlock;
import tamaized.voidcraft.common.entity.EntityVoidBoss;
import tamaized.voidcraft.client.entity.boss.bossbar.RenderAlternateBossBars.IAlternateBoss;
import tamaized.voidcraft.common.handlers.ConfigHandler;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.Teleporter;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;

import java.util.Random;

public class BlockRealityHole extends TamBlock {

	public BlockRealityHole(CreativeTabs tab, Material material, String n, float hardness) {
		super(tab, material, n, hardness, SoundType.CLOTH);
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
				if (entityIn.dimension == ConfigHandler.dimensionIdXia) {
					entityIn.setPositionAndUpdate(52.5, 61, 4.5);
				} else {
					if (ConfigHandler.realityWhitelist.length > 0) {
						Random rand = worldIn.rand;
						int i = rand.nextInt(ConfigHandler.realityWhitelist.length);
						int dim =ConfigHandler.realityWhitelist[i];
						if (player.mcServer.getWorld(dim) != null) {
							player.mcServer.getPlayerList().transferPlayerToDimension(player, dim, new RealityTeleporter(player.mcServer.getWorld(dim), player.getPosition()));
						}
						// else new RealityTeleporter(player.getServerWorld(), player.getPosition()).placeInPortal(player, player.rotationYaw);
					}
				}
			} else if (!(entityIn instanceof EntityVoidBoss) && !(entityIn instanceof IAlternateBoss) && (entityIn.isNonBoss())) entityIn.setDead();
		}
	}

	@Override
	public AxisAlignedBB getCollisionBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		return NULL_AABB;
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
			World w = entityIn.world;
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
