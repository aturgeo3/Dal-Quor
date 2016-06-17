package Tamaized.Voidcraft.world.dim.Xia.castle.logic.battle.twins;

import net.minecraft.block.BlockLever;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.tileentity.TileEntitySign;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;
import Tamaized.Voidcraft.mobs.entity.boss.EntityMobDol;
import Tamaized.Voidcraft.mobs.entity.boss.EntityMobZol;
import Tamaized.Voidcraft.world.dim.Xia.castle.logic.battle.IBattleHandler;
import Tamaized.Voidcraft.world.dim.Xia.castle.logic.battle.twins.messages.TwinsMessages01;
import Tamaized.Voidcraft.world.dim.Xia.castle.logic.battle.twins.messages.TwinsMessages02;
import Tamaized.Voidcraft.world.dim.Xia.castle.logic.battle.twins.messages.TwinsMessages03;
import Tamaized.Voidcraft.world.dim.Xia.castle.logic.battle.twins.messages.TwinsMessages04;
import Tamaized.Voidcraft.world.dim.Xia.castle.logic.battle.twins.messages.TwinsMessages05;

public class TwinsBattleHandler implements IBattleHandler {
	
	private int phase = 0;
	private int tick = 0;
	private int childPhase = 0;
	private int childPhaseModulate = 20;
	private boolean readyForInput = false;
	
	private boolean running;
	private boolean isDone = false;
	
	private World worldObj;
	private BlockPos pos;
	
	@Override
	public void update(){
		if(!worldObj.isRemote){
			if(running){
				switch(phase){
					case 0:
						if(readyForInput){
							boolean flag = worldObj.getBlockState(pos.add(1, 0, -3)).getValue(BlockLever.POWERED);
							if(flag){
								TileEntitySign sign = (TileEntitySign) worldObj.getTileEntity(pos.add(0, 0, -2));
								if(sign != null){
									boolean flag1 = false;
									for(ITextComponent t : sign.signText){
										if(t.getUnformattedText().toLowerCase().contains("dol")) flag1 = true;
									}
									if(flag1){
										worldObj.setBlockState(pos.add(0, 1, -3), Blocks.AIR.getDefaultState());
										worldObj.setBlockState(pos.add(0, 0, -2), Blocks.AIR.getDefaultState());
										TileEntityChest te = (TileEntityChest) worldObj.getTileEntity(pos.add(0, 0, -3));
										if(te != null) te.clear();
										worldObj.setBlockState(pos.add(0, 0, -3), Blocks.AIR.getDefaultState());
										worldObj.setBlockState(pos.add(1, 0, -3), Blocks.AIR.getDefaultState());
										readyForInput = false;
										phase++;
									}else{
										for(EntityPlayer p : worldObj.getEntitiesWithinAABB(EntityPlayer.class, new AxisAlignedBB(pos.add(-50, -50, -50), pos.add(50, 50, 50)))) p.setHealth(0.0f);
										stop();
									}
								}else{
									for(EntityPlayer p : worldObj.getEntitiesWithinAABB(EntityPlayer.class, new AxisAlignedBB(pos.add(-50, -50, -50), pos.add(50, 50, 50)))) p.setHealth(0.0f);
									stop();
								}
							}
						}else{
							tick++;
							readyForInput = TwinsMessages01.run(worldObj, pos, tick);
						}
						break;
					case 1:
						if(readyForInput){
							boolean flag = worldObj.getBlockState(pos.add(1, 0, -3)).getValue(BlockLever.POWERED);
							if(flag){
								TileEntitySign sign = (TileEntitySign) worldObj.getTileEntity(pos.add(0, 0, -2));
								if(sign != null){
									boolean flag1 = false;
									for(ITextComponent t : sign.signText){
										if(t.getUnformattedText().toLowerCase().contains("void")) flag1 = true;
									}
									if(flag1){
										worldObj.setBlockState(pos.add(0, 1, -3), Blocks.AIR.getDefaultState());
										worldObj.setBlockState(pos.add(0, 0, -2), Blocks.AIR.getDefaultState());
										TileEntityChest te = (TileEntityChest) worldObj.getTileEntity(pos.add(0, 0, -3));
										if(te != null) te.clear();
										worldObj.setBlockState(pos.add(0, 0, -3), Blocks.AIR.getDefaultState());
										worldObj.setBlockState(pos.add(1, 0, -3), Blocks.AIR.getDefaultState());
										readyForInput = false;
										phase++;
									}else{
										for(EntityPlayer p : worldObj.getEntitiesWithinAABB(EntityPlayer.class, new AxisAlignedBB(pos.add(-50, -50, -50), pos.add(50, 50, 50)))) p.setHealth(0.0f);
										stop();
									}
								}else{
									for(EntityPlayer p : worldObj.getEntitiesWithinAABB(EntityPlayer.class, new AxisAlignedBB(pos.add(-50, -50, -50), pos.add(50, 50, 50)))) p.setHealth(0.0f);
									stop();
								}
							}
						}else{
							tick++;
							readyForInput = TwinsMessages02.run(worldObj, pos, tick);
						}
						break;
					case 2:
						if(readyForInput){
							boolean flag = worldObj.getBlockState(pos.add(1, 0, -3)).getValue(BlockLever.POWERED);
							if(flag){
								TileEntitySign sign = (TileEntitySign) worldObj.getTileEntity(pos.add(0, 0, -2));
								if(sign != null){
									boolean flag1 = false;
									for(ITextComponent t : sign.signText){
										if(t.getUnformattedText().toLowerCase().contains("herobrine")) flag1 = true;
									}
									if(flag1){
										worldObj.setBlockState(pos.add(0, 1, -3), Blocks.AIR.getDefaultState());
										worldObj.setBlockState(pos.add(0, 0, -2), Blocks.AIR.getDefaultState());
										TileEntityChest te = (TileEntityChest) worldObj.getTileEntity(pos.add(0, 0, -3));
										if(te != null) te.clear();
										worldObj.setBlockState(pos.add(0, 0, -3), Blocks.AIR.getDefaultState());
										worldObj.setBlockState(pos.add(1, 0, -3), Blocks.AIR.getDefaultState());
										readyForInput = false;
										phase++;
									}else{
										for(EntityPlayer p : worldObj.getEntitiesWithinAABB(EntityPlayer.class, new AxisAlignedBB(pos.add(-50, -50, -50), pos.add(50, 50, 50)))) p.setHealth(0.0f);
										stop();
									}
								}else{
									for(EntityPlayer p : worldObj.getEntitiesWithinAABB(EntityPlayer.class, new AxisAlignedBB(pos.add(-50, -50, -50), pos.add(50, 50, 50)))) p.setHealth(0.0f);
									stop();
								}
							}
						}else{
							tick++;
							readyForInput = TwinsMessages03.run(worldObj, pos, tick);
						}
						break;
					case 3:
						if(readyForInput){
							boolean flag = worldObj.getBlockState(pos.add(1, 0, -2)).getValue(BlockLever.POWERED);
							if(flag){
								TileEntitySign sign = (TileEntitySign) worldObj.getTileEntity(pos.add(0, 0, -2));
								if(sign != null){
									boolean flag1 = false;
									for(ITextComponent t : sign.signText){
										if(t.getUnformattedText().toLowerCase().contains("dream")) flag1 = true;
									}
									if(flag1){
										worldObj.setBlockState(pos.add(0, 1, -3), Blocks.AIR.getDefaultState());
										worldObj.setBlockState(pos.add(1, 1, -3), Blocks.AIR.getDefaultState());
										worldObj.setBlockState(pos.add(-1, 1, -3), Blocks.AIR.getDefaultState());
										worldObj.setBlockState(pos.add(0, 0, -2), Blocks.AIR.getDefaultState());
										TileEntityChest te = (TileEntityChest) worldObj.getTileEntity(pos.add(0, 0, -3));
										if(te != null) te.clear();
										worldObj.setBlockState(pos.add(0, 0, -3), Blocks.AIR.getDefaultState());
										worldObj.setBlockState(pos.add(1, 0, -3), Blocks.AIR.getDefaultState());
										worldObj.setBlockState(pos.add(-1, 0, -3), Blocks.AIR.getDefaultState());
										worldObj.setBlockState(pos.add(1, 0, -2), Blocks.AIR.getDefaultState());
										readyForInput = false;
										phase++;
									}else{
										for(EntityPlayer p : worldObj.getEntitiesWithinAABB(EntityPlayer.class, new AxisAlignedBB(pos.add(-50, -50, -50), pos.add(50, 50, 50)))) p.setHealth(0.0f);
										stop();
									}
								}else{
									for(EntityPlayer p : worldObj.getEntitiesWithinAABB(EntityPlayer.class, new AxisAlignedBB(pos.add(-50, -50, -50), pos.add(50, 50, 50)))) p.setHealth(0.0f);
									stop();
								}
							}
						}else{
							tick++;
							readyForInput = TwinsMessages04.run(worldObj, pos, tick);
						}
						break;
					case 4:
						boolean flag = TwinsMessages05.run(worldObj, pos, tick);
						if(flag){
							stop();
						}
						tick++;
						break;
					default:
						break;
				}
			}
		}
	}
	
	public void start(World world, BlockPos p){
		worldObj = world;
		pos = p;
		stop();
		phase = 0;
		readyForInput = false;
		for(int x = pos.getX()-1; x<=pos.getX()+1; x++){
			for(int y = pos.getY(); y<=pos.getY()+2; y++){
				int z = pos.getZ()+12;
				worldObj.setBlockState(new BlockPos(x,  y, z), Blocks.MOSSY_COBBLESTONE.getDefaultState());
			}
		}
		TwinsMessages01.childPhase = 0;
		TwinsMessages02.childPhase = 0;
		TwinsMessages03.childPhase = 0;
		TwinsMessages04.childPhase = 0;
		TwinsMessages05.childPhase = 0;
		//phase = 3;
		running = true;
	}
	
	public void stop(){
		readyForInput = false;
		for(Entity e : worldObj.getEntitiesWithinAABB(EntityMobZol.class, new AxisAlignedBB(pos.add(-50, -50, -50), pos.add(50, 50, 50)))) worldObj.removeEntity(e);
		for(Entity e : worldObj.getEntitiesWithinAABB(EntityMobDol.class, new AxisAlignedBB(pos.add(-50, -50, -50), pos.add(50, 50, 50)))) worldObj.removeEntity(e);
		for(int x = pos.getX()-1; x<=pos.getX()+1; x++){
			for(int y = pos.getY(); y<=pos.getY()+2; y++){
				int z = pos.getZ()+12;
				worldObj.setBlockState(new BlockPos(x,  y, z), Blocks.AIR.getDefaultState());
			}
		}
		worldObj.setBlockState(pos.add(-1, 1, -3), Blocks.AIR.getDefaultState());
		worldObj.setBlockState(pos.add(0, 1, -3), Blocks.AIR.getDefaultState());
		worldObj.setBlockState(pos.add(1, 1, -3), Blocks.AIR.getDefaultState());
		worldObj.setBlockState(pos.add(0, 0, -2), Blocks.AIR.getDefaultState());
		TileEntityChest te = (TileEntityChest) worldObj.getTileEntity(pos.add(0, 0, -3));
		if(te != null) te.clear();
		worldObj.setBlockState(pos.add(0, 0, -3), Blocks.AIR.getDefaultState());
		worldObj.setBlockState(pos.add(1, 0, -3), Blocks.AIR.getDefaultState());
		worldObj.setBlockState(pos.add(-1, 0, -3), Blocks.AIR.getDefaultState());
		worldObj.setBlockState(pos.add(1, 0, -2), Blocks.AIR.getDefaultState());
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

}
