package Tamaized.Voidcraft.world.dim.TheVoid;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.LongHashMap;
import net.minecraft.util.MathHelper;
import net.minecraft.world.ChunkCoordIntPair;
import net.minecraft.world.Teleporter;
import net.minecraft.world.WorldServer;
import Tamaized.Voidcraft.common.voidCraft;

public class TeleporterVoid extends Teleporter {

	private final WorldServer worldServerInstance;
	private final Random random;

	private final LongHashMap destinationCoordinateCache = new LongHashMap();
	private final List destinationCoordinateKeys = new ArrayList();

	public TeleporterVoid(WorldServer par1WorldServer) {
		super(par1WorldServer);

		this.worldServerInstance = par1WorldServer;
		this.random = new Random(par1WorldServer.getSeed());
	}

	/**
	 * Place an entity in a nearby portal, creating one if necessary.
	 */
	@Override
	public void placeInPortal(Entity entity, float rotationYaw){
		if(entity instanceof EntityPlayer) ((EntityPlayer) entity).addStat(voidCraft.achievements.voidCraftAchMainLine_2, 1);
	
		if (!this.placeInExistingPortal(entity, rotationYaw)){
			this.makePortal(entity);
			this.placeInExistingPortal(entity, rotationYaw);
		}
	}

	public boolean placeInExistingPortal(Entity entityIn, float p_180620_2_)
    {
        boolean flag = true;
        double d0 = -1.0D;
        int i = MathHelper.floor_double(entityIn.posX);
        int j = MathHelper.floor_double(entityIn.posZ);
        boolean flag1 = true;
        Object object = BlockPos.ORIGIN;
        long k = ChunkCoordIntPair.chunkXZ2Int(i, j);

        if (this.destinationCoordinateCache.containsItem(k))
        {
            Teleporter.PortalPosition portalposition = (Teleporter.PortalPosition)this.destinationCoordinateCache.getValueByKey(k);
            d0 = 0.0D;
            object = portalposition;
            portalposition.lastUpdateTime = this.worldServerInstance.getTotalWorldTime();
            flag1 = false;
        }
        else
        {
            BlockPos blockpos4 = new BlockPos(entityIn);

            for (int l = -128; l <= 128; ++l)
            {
                BlockPos blockpos1;

                for (int i1 = -128; i1 <= 128; ++i1)
                {
                    for (BlockPos blockpos = blockpos4.add(l, this.worldServerInstance.getActualHeight() - 1 - blockpos4.getY(), i1); blockpos.getY() >= 0; blockpos = blockpos1)
                    {
                        blockpos1 = blockpos.down();

                        if (this.worldServerInstance.getBlockState(blockpos).getBlock() == Blocks.portal)
                        {
                            while (this.worldServerInstance.getBlockState(blockpos1 = blockpos.down()).getBlock() == Blocks.portal)
                            {
                                blockpos = blockpos1;
                            }

                            double d1 = blockpos.distanceSq(blockpos4);

                            if (d0 < 0.0D || d1 < d0)
                            {
                                d0 = d1;
                                object = blockpos;
                            }
                        }
                    }
                }
            }
        }

        if (d0 >= 0.0D)
        {
            if (flag1)
            {
                this.destinationCoordinateCache.add(k, new Teleporter.PortalPosition((BlockPos)object, this.worldServerInstance.getTotalWorldTime()));
                this.destinationCoordinateKeys.add(Long.valueOf(k));
            }

            double d4 = (double)((BlockPos)object).getX() + 0.5D;
            double d5 = (double)((BlockPos)object).getY() + 0.5D;
            double d6 = (double)((BlockPos)object).getZ() + 0.5D;
            EnumFacing enumfacing = null;

            if (this.worldServerInstance.getBlockState(((BlockPos)object).west()).getBlock() == voidCraft.blocks.blockPortalVoid)
            {
                enumfacing = EnumFacing.NORTH;
            }

            if (this.worldServerInstance.getBlockState(((BlockPos)object).east()).getBlock() == voidCraft.blocks.blockPortalVoid)
            {
                enumfacing = EnumFacing.SOUTH;
            }

            if (this.worldServerInstance.getBlockState(((BlockPos)object).north()).getBlock() == voidCraft.blocks.blockPortalVoid)
            {
                enumfacing = EnumFacing.EAST;
            }

            if (this.worldServerInstance.getBlockState(((BlockPos)object).south()).getBlock() == voidCraft.blocks.blockPortalVoid)
            {
                enumfacing = EnumFacing.WEST;
            }

            EnumFacing enumfacing1 = EnumFacing.getHorizontal(entityIn.getTeleportDirection());

            if (enumfacing != null)
            {
                EnumFacing enumfacing2 = enumfacing.rotateYCCW();
                BlockPos blockpos2 = ((BlockPos)object).offset(enumfacing);
                boolean flag2 = this.func_180265_a(blockpos2);
                boolean flag3 = this.func_180265_a(blockpos2.offset(enumfacing2));

                if (flag3 && flag2)
                {
                    object = ((BlockPos)object).offset(enumfacing2);
                    enumfacing = enumfacing.getOpposite();
                    enumfacing2 = enumfacing2.getOpposite();
                    BlockPos blockpos3 = ((BlockPos)object).offset(enumfacing);
                    flag2 = this.func_180265_a(blockpos3);
                    flag3 = this.func_180265_a(blockpos3.offset(enumfacing2));
                }

                float f6 = 0.5F;
                float f1 = 0.5F;

                if (!flag3 && flag2)
                {
                    f6 = 1.0F;
                }
                else if (flag3 && !flag2)
                {
                    f6 = 0.0F;
                }
                else if (flag3)
                {
                    f1 = 0.0F;
                }

                d4 = (double)((BlockPos)object).getX() + 0.5D;
                d5 = (double)((BlockPos)object).getY() + 0.5D;
                d6 = (double)((BlockPos)object).getZ() + 0.5D;
                d4 += (double)((float)enumfacing2.getFrontOffsetX() * f6 + (float)enumfacing.getFrontOffsetX() * f1);
                d6 += (double)((float)enumfacing2.getFrontOffsetZ() * f6 + (float)enumfacing.getFrontOffsetZ() * f1);
                float f2 = 0.0F;
                float f3 = 0.0F;
                float f4 = 0.0F;
                float f5 = 0.0F;

                if (enumfacing == enumfacing1)
                {
                    f2 = 1.0F;
                    f3 = 1.0F;
                }
                else if (enumfacing == enumfacing1.getOpposite())
                {
                    f2 = -1.0F;
                    f3 = -1.0F;
                }
                else if (enumfacing == enumfacing1.rotateY())
                {
                    f4 = 1.0F;
                    f5 = -1.0F;
                }
                else
                {
                    f4 = -1.0F;
                    f5 = 1.0F;
                }

                double d2 = entityIn.motionX;
                double d3 = entityIn.motionZ;
                entityIn.motionX = d2 * (double)f2 + d3 * (double)f5;
                entityIn.motionZ = d2 * (double)f4 + d3 * (double)f3;
                entityIn.rotationYaw = p_180620_2_ - (float)(enumfacing1.getHorizontalIndex() * 90) + (float)(enumfacing.getHorizontalIndex() * 90);
            }
            else
            {
                entityIn.motionX = entityIn.motionY = entityIn.motionZ = 0.0D;
            }

            entityIn.setLocationAndAngles(d4, d5, d6, entityIn.rotationYaw, entityIn.rotationPitch);
            return true;
        }
        else
        {
            return false;
        }
    }
	
	private boolean func_180265_a(BlockPos p_180265_1_)
    {
        return !this.worldServerInstance.isAirBlock(p_180265_1_) || !this.worldServerInstance.isAirBlock(p_180265_1_.up());
    }

	
	@Override
	public boolean makePortal(Entity entity) {
		byte b0 = 16;
		double d0 = -1.0D;
		int i = MathHelper.floor_double(entity.posX);
		int j = MathHelper.floor_double(entity.posY);
		int k = MathHelper.floor_double(entity.posZ);
		int l = i;
		int i1 = j;
		int j1 = k;
		int k1 = 0;
		int l1 = this.random.nextInt(4);
		int i2;
		double d1;
		double d2;
		int j2;
		int k2;
		int l2;
		int i3;
		int j3;
		int k3;
		int l3;
		int i4;
		int j4;
		int k4;
		double d3;
		double d4;

		for (i2 = i - b0; i2 <= i + b0; ++i2) {
			d1 = (double) i2 + 0.5D - entity.posX;

			for (j2 = k - b0; j2 <= k + b0; ++j2) {
				d2 = (double) j2 + 0.5D - entity.posZ;
				label274:

				for (k2 = this.worldServerInstance.getActualHeight() - 1; k2 >= 0; --k2) {
					if (this.worldServerInstance.isAirBlock(new BlockPos(i2, k2, j2))) {
						while (k2 > 0 && this.worldServerInstance.isAirBlock(new BlockPos(i2, k2 - 1, j2))) {
							--k2;
						}

						for (i3 = l1; i3 < l1 + 4; ++i3) {
							l2 = i3 % 2;
							k3 = 1 - l2;

							if (i3 % 4 >= 2) {
								l2 = -l2;
								k3 = -k3;
							}

							for (j3 = 0; j3 < 3; ++j3) {
								for (i4 = 0; i4 < 4; ++i4) {
									for (l3 = -1; l3 < 4; ++l3) {
										k4 = i2 + (i4 - 1) * l2 + j3 * k3;
										j4 = k2 + l3;
										int l4 = j2 + (i4 - 1) * k3 - j3 * l2;

										if (l3 < 0 && !this.worldServerInstance.getBlockState(new BlockPos(k4, j4, l4)).getBlock().getMaterial().isSolid() || l3 >= 0 && !this.worldServerInstance.isAirBlock(new BlockPos(k4, j4, l4))) {
											continue label274;
										}
									}
								}
							}

							d4 = (double) k2 + 0.5D - entity.posY;
							d3 = d1 * d1 + d4 * d4 + d2 * d2;

							if (d0 < 0.0D || d3 < d0) {
								d0 = d3;
								l = i2;
								i1 = k2;
								j1 = j2;
								k1 = i3 % 4;
							}
						}
					}
				}
			}
		}

		if (d0 < 0.0D) {
			for (i2 = i - b0; i2 <= i + b0; ++i2) {
				d1 = (double) i2 + 0.5D - entity.posX;

				for (j2 = k - b0; j2 <= k + b0; ++j2) {
					d2 = (double) j2 + 0.5D - entity.posZ;
					label222:

					for (k2 = this.worldServerInstance.getActualHeight() - 1; k2 >= 0; --k2) {
						if (this.worldServerInstance.isAirBlock(new BlockPos(i2, k2, j2))) {
							while (k2 > 0
									&& this.worldServerInstance.isAirBlock(new BlockPos(i2, k2 - 1, j2))) {
								--k2;
							}

							for (i3 = l1; i3 < l1 + 2; ++i3) {
								l2 = i3 % 2;
								k3 = 1 - l2;

								for (j3 = 0; j3 < 4; ++j3) {
									for (i4 = -1; i4 < 4; ++i4) {
										l3 = i2 + (j3 - 1) * l2;
										k4 = k2 + i4;
										j4 = j2 + (j3 - 1) * k3;

										if (i4 < 0 && !this.worldServerInstance.getBlockState(new BlockPos(l3, k4, j4)).getBlock().getMaterial().isSolid() || i4 >= 0 && !this.worldServerInstance.isAirBlock(new BlockPos(l3, k4, j4))) {
											continue label222;
										}
									}
								}

								d4 = (double) k2 + 0.5D - entity.posY;
								d3 = d1 * d1 + d4 * d4 + d2 * d2;

								if (d0 < 0.0D || d3 < d0) {
									d0 = d3;
									l = i2;
									i1 = k2;
									j1 = j2;
									k1 = i3 % 2;
								}
							}
						}
					}
				}
			}
		}

		int i5 = l;
		int j5 = i1;
		j2 = j1;
		int k5 = k1 % 2;
		int l5 = 1 - k5;

		if (k1 % 4 >= 2) {
			k5 = -k5;
			l5 = -l5;
		}

		boolean flag;

		if (d0 < 0.0D) {
			if (i1 < 70) {
				i1 = 70;
			}

			if (i1 > this.worldServerInstance.getActualHeight() - 10) {
				i1 = this.worldServerInstance.getActualHeight() - 10;
			}

			j5 = i1;

			for (k2 = -1; k2 <= 1; ++k2) {
				for (i3 = 1; i3 < 3; ++i3) {
					for (l2 = -1; l2 < 3; ++l2) {
						k3 = i5 + (i3 - 1) * k5 + k2 * l5;
						j3 = j5 + l2;
						i4 = j2 + (i3 - 1) * l5 - k2 * k5;
						flag = l2 < 0;
						this.worldServerInstance.setBlockState(new BlockPos(k3, j3, i4), flag ? voidCraft.blocks.blockVoidcrystal.getDefaultState() : Blocks.air.getDefaultState());
					}
				}
			}
		}

		for (k2 = 0; k2 < 4; ++k2) {
			for (i3 = 0; i3 < 4; ++i3) {
				for (l2 = -1; l2 < 4; ++l2) {
					k3 = i5 + (i3 - 1) * k5;
					j3 = j5 + l2;
					i4 = j2 + (i3 - 1) * l5;
					flag = i3 == 0 || i3 == 3 || l2 == -1 || l2 == 3;
					this.worldServerInstance.setBlockState(new BlockPos(k3, j3, i4), flag ? voidCraft.blocks.blockVoidcrystal.getDefaultState() : voidCraft.blocks.blockPortalVoid.getDefaultState(), 2);
				}
			}

			for (i3 = 0; i3 < 4; ++i3) {
				for (l2 = -1; l2 < 4; ++l2) {
					k3 = i5 + (i3 - 1) * k5;
					j3 = j5 + l2;
					i4 = j2 + (i3 - 1) * l5;
					this.worldServerInstance.notifyBlockOfStateChange(new BlockPos(k3, j3, i4), this.worldServerInstance.getBlockState(new BlockPos(k3, j3, i4)).getBlock());
				}
			}
		}

		return true;
	}

	@Override
	public void removeStalePortalLocations(long par1) {

	}

}
