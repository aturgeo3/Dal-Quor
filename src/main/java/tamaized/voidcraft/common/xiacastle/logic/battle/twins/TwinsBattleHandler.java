package tamaized.voidcraft.common.xiacastle.logic.battle.twins;

import net.minecraft.block.BlockLever;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.tileentity.TileEntitySign;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;
import tamaized.voidcraft.common.entity.boss.twins.EntityBossDol;
import tamaized.voidcraft.common.entity.boss.twins.EntityBossZol;
import tamaized.voidcraft.common.world.dim.xia.WorldProviderXia;
import tamaized.voidcraft.common.xiacastle.logic.battle.IBattleHandler;
import tamaized.voidcraft.common.xiacastle.logic.battle.twins.messages.*;

public class TwinsBattleHandler implements IBattleHandler {

	private int phase = 0;
	private boolean readyForInput = false;

	private boolean running = false;
	private boolean isDone = false;

	private World worldObj;
	private BlockPos pos;

	private EntityBossDol dol;
	private EntityBossZol zol;

	@Override
	public void update() {
		if (worldObj != null && !worldObj.isRemote) {
			if (running) {
				if (zol == null || dol == null || zol.isDead || dol.isDead) {
					stop();
					return;
				}
				switch (phase) {
					case 0:
						if (readyForInput) {
							boolean flag = worldObj.getBlockState(pos.add(3, 0, 1)).getValue(BlockLever.POWERED);
							if (flag) {
								TileEntitySign sign = (TileEntitySign) worldObj.getTileEntity(pos.add(2, 0, 0));
								if (sign != null) {
									boolean flag1 = false;
									for (ITextComponent t : sign.signText) {
										if (t.getUnformattedComponentText().toLowerCase().contains(new TextComponentTranslation("voidcraft.twins.riddle.1.a").getUnformattedComponentText()))
											flag1 = true;
									}
									if (flag1) {
										worldObj.setBlockState(pos.add(3, 1, 0), Blocks.AIR.getDefaultState());
										worldObj.setBlockState(pos.add(2, 0, 0), Blocks.AIR.getDefaultState());
										TileEntityChest te = (TileEntityChest) worldObj.getTileEntity(pos.add(3, 0, 0));
										if (te != null)
											te.clear();
										worldObj.setBlockState(pos.add(3, 0, 0), Blocks.AIR.getDefaultState());
										worldObj.setBlockState(pos.add(3, 0, 1), Blocks.AIR.getDefaultState());
										readyForInput = false;
										phase++;
									} else {
										for (EntityPlayer p : worldObj.getEntitiesWithinAABB(EntityPlayer.class, new AxisAlignedBB(pos.add(-50, -50, -50), pos.add(50, 50, 50))))
											p.setHealth(0.0f);
										stop();
									}
								} else {
									for (EntityPlayer p : worldObj.getEntitiesWithinAABB(EntityPlayer.class, new AxisAlignedBB(pos.add(-50, -50, -50), pos.add(50, 50, 50))))
										p.setHealth(0.0f);
									stop();
								}
							}
						} else {
							readyForInput = TwinsMessages01.run(worldObj, pos, dol, zol);
						}
						break;
					case 1:
						if (readyForInput) {
							boolean flag = worldObj.getBlockState(pos.add(3, 0, 1)).getValue(BlockLever.POWERED);
							if (flag) {
								TileEntitySign sign = (TileEntitySign) worldObj.getTileEntity(pos.add(2, 0, 0));
								if (sign != null) {
									boolean flag1 = false;
									for (ITextComponent t : sign.signText) {
										if (t.getUnformattedComponentText().toLowerCase().contains(new TextComponentTranslation("voidcraft.twins.riddle.2.a").getUnformattedComponentText()))
											flag1 = true;
									}
									if (flag1) {
										worldObj.setBlockState(pos.add(3, 1, 0), Blocks.AIR.getDefaultState());
										worldObj.setBlockState(pos.add(2, 0, 0), Blocks.AIR.getDefaultState());
										TileEntityChest te = (TileEntityChest) worldObj.getTileEntity(pos.add(3, 0, 0));
										if (te != null)
											te.clear();
										worldObj.setBlockState(pos.add(3, 0, 0), Blocks.AIR.getDefaultState());
										worldObj.setBlockState(pos.add(3, 0, 1), Blocks.AIR.getDefaultState());
										readyForInput = false;
										phase++;
									} else {
										for (EntityPlayer p : worldObj.getEntitiesWithinAABB(EntityPlayer.class, new AxisAlignedBB(pos.add(-50, -50, -50), pos.add(50, 50, 50))))
											p.setHealth(0.0f);
										stop();
									}
								} else {
									for (EntityPlayer p : worldObj.getEntitiesWithinAABB(EntityPlayer.class, new AxisAlignedBB(pos.add(-50, -50, -50), pos.add(50, 50, 50))))
										p.setHealth(0.0f);
									stop();
								}
							}
						} else {
							readyForInput = TwinsMessages02.run(worldObj, pos);
						}
						break;
					case 2:
						if (readyForInput) {
							boolean flag = worldObj.getBlockState(pos.add(3, 0, 1)).getValue(BlockLever.POWERED);
							if (flag) {
								TileEntitySign sign = (TileEntitySign) worldObj.getTileEntity(pos.add(2, 0, 0));
								if (sign != null) {
									boolean flag1 = false;
									for (ITextComponent t : sign.signText) {
										if (t.getUnformattedComponentText().toLowerCase().contains(new TextComponentTranslation("voidcraft.twins.riddle.3.a").getUnformattedComponentText()))
											flag1 = true;
									}
									if (flag1) {
										worldObj.setBlockState(pos.add(3, 1, 0), Blocks.AIR.getDefaultState());
										worldObj.setBlockState(pos.add(2, 0, 0), Blocks.AIR.getDefaultState());
										TileEntityChest te = (TileEntityChest) worldObj.getTileEntity(pos.add(3, 0, 0));
										if (te != null)
											te.clear();
										worldObj.setBlockState(pos.add(3, 0, 0), Blocks.AIR.getDefaultState());
										worldObj.setBlockState(pos.add(3, 0, 1), Blocks.AIR.getDefaultState());
										readyForInput = false;
										phase++;
									} else {
										for (EntityPlayer p : worldObj.getEntitiesWithinAABB(EntityPlayer.class, new AxisAlignedBB(pos.add(-50, -50, -50), pos.add(50, 50, 50))))
											p.setHealth(0.0f);
										stop();
									}
								} else {
									for (EntityPlayer p : worldObj.getEntitiesWithinAABB(EntityPlayer.class, new AxisAlignedBB(pos.add(-50, -50, -50), pos.add(50, 50, 50))))
										p.setHealth(0.0f);
									stop();
								}
							}
						} else {
							readyForInput = TwinsMessages03.run(worldObj, pos);
						}
						break;
					case 3:
						if (readyForInput) {
							boolean flag = worldObj.getBlockState(pos.add(2, 0, 1)).getValue(BlockLever.POWERED);
							if (flag) {
								TileEntitySign sign = (TileEntitySign) worldObj.getTileEntity(pos.add(2, 0, 0));
								if (sign != null) {
									boolean flag1 = false;
									for (ITextComponent t : sign.signText) {
										if (t.getUnformattedComponentText().toLowerCase().contains(new TextComponentTranslation("voidcraft.twins.riddle.4.a").getUnformattedComponentText()))
											flag1 = true;
									}
									if (flag1) {
										worldObj.setBlockState(pos.add(3, 1, 0), Blocks.AIR.getDefaultState());
										worldObj.setBlockState(pos.add(3, 1, 1), Blocks.AIR.getDefaultState());
										worldObj.setBlockState(pos.add(3, 1, -1), Blocks.AIR.getDefaultState());
										worldObj.setBlockState(pos.add(2, 0, 0), Blocks.AIR.getDefaultState());
										TileEntityChest te = (TileEntityChest) worldObj.getTileEntity(pos.add(0, 0, -3));
										if (te != null)
											te.clear();
										worldObj.setBlockState(pos.add(3, 0, 0), Blocks.AIR.getDefaultState());
										worldObj.setBlockState(pos.add(3, 0, 1), Blocks.AIR.getDefaultState());
										worldObj.setBlockState(pos.add(3, 0, -1), Blocks.AIR.getDefaultState());
										worldObj.setBlockState(pos.add(2, 0, 1), Blocks.AIR.getDefaultState());
										readyForInput = false;
										phase++;
									} else {
										for (EntityPlayer p : worldObj.getEntitiesWithinAABB(EntityPlayer.class, new AxisAlignedBB(pos.add(-50, -50, -50), pos.add(50, 50, 50))))
											p.setHealth(0.0f);
										stop();
									}
								} else {
									for (EntityPlayer p : worldObj.getEntitiesWithinAABB(EntityPlayer.class, new AxisAlignedBB(pos.add(-50, -50, -50), pos.add(50, 50, 50))))
										p.setHealth(0.0f);
									stop();
								}
							}
						} else {
							readyForInput = TwinsMessages04.run(worldObj, pos);
						}
						break;
					case 4:
						boolean flag = TwinsMessages05.run(worldObj, pos);
						if (flag)
							setDone();
						break;
					default:
						break;
				}
			}
		}
	}

	@Override
	public void start(World world, BlockPos p) {
		worldObj = world;
		pos = p.add(0, 1, 0);
		stop();
		isDone = false;
		phase = 0;
		readyForInput = false;

		for (int z = pos.getZ() - 2; z <= pos.getZ() + 2; z++) {
			for (int y = pos.getY(); y <= pos.getY() + 4; y++) {
				int x = pos.getX() - 12;
				worldObj.setBlockState(new BlockPos(x, y, z), Blocks.MOSSY_COBBLESTONE.getDefaultState());
			}
		}

		TwinsMessages01.childPhase = 0;
		TwinsMessages02.childPhase = 0;
		TwinsMessages03.childPhase = 0;
		TwinsMessages04.childPhase = 0;
		TwinsMessages05.childPhase = 0;

		dol = new EntityBossDol(worldObj);
		zol = new EntityBossZol(worldObj);
		dol.setPosition(pos.getX() + 5 + .5, pos.getY() + 4, pos.getZ() - 3 + .5);
		zol.setPosition(pos.getX() + 5 + .5, pos.getY() + 4, pos.getZ() + 3 + .5);
		running = true;
	}

	@Override
	public void stop() {
		readyForInput = false;
		isDone = false;
		if (dol != null) {
			dol.setDead();
			dol = null;
		}
		if (zol != null) {
			zol.setDead();
			zol = null;
		}
		if (pos == null)
			return;
		for (Entity e : worldObj.getEntitiesWithinAABB(EntityBossZol.class, new AxisAlignedBB(pos.add(-50, -50, -50), pos.add(50, 50, 50))))
			e.setDead();
		for (Entity e : worldObj.getEntitiesWithinAABB(EntityBossDol.class, new AxisAlignedBB(pos.add(-50, -50, -50), pos.add(50, 50, 50))))
			e.setDead();
		for (int z = pos.getZ() - 2; z <= pos.getZ() + 2; z++) {
			for (int y = pos.getY(); y <= pos.getY() + 4; y++) {
				int x = pos.getX() - 12;
				worldObj.setBlockState(new BlockPos(x, y, z), Blocks.AIR.getDefaultState());
			}
		}
		worldObj.setBlockState(pos.add(3, 1, -1), Blocks.AIR.getDefaultState());
		worldObj.setBlockState(pos.add(3, 1, 0), Blocks.AIR.getDefaultState());
		worldObj.setBlockState(pos.add(3, 1, 1), Blocks.AIR.getDefaultState());
		worldObj.setBlockState(pos.add(2, 0, 0), Blocks.AIR.getDefaultState());
		TileEntityChest te = (TileEntityChest) worldObj.getTileEntity(pos.add(0, 0, -3));
		if (te != null)
			te.clear();
		worldObj.setBlockState(pos.add(3, 0, 0), Blocks.AIR.getDefaultState());
		worldObj.setBlockState(pos.add(3, 0, 1), Blocks.AIR.getDefaultState());
		worldObj.setBlockState(pos.add(3, 0, -1), Blocks.AIR.getDefaultState());
		worldObj.setBlockState(pos.add(2, 0, 1), Blocks.AIR.getDefaultState());
		running = false;
	}

	@Override
	public boolean isRunning() {
		return running;
	}

	@Override
	public boolean isDone() {
		return isDone;
	}

	@Override
	public void setDone() {
		stop();
		isDone = true;
		if (worldObj != null && worldObj.provider instanceof WorldProviderXia)
			worldObj.provider.onWorldSave();
	}

}
