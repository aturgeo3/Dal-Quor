package Tamaized.Voidcraft.blocks.tileentity;

import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import Tamaized.Voidcraft.mobs.entity.boss.EntityMobDol;
import Tamaized.Voidcraft.mobs.entity.boss.EntityMobZol;
import Tamaized.Voidcraft.world.dim.Xia.castle.XiaCastleHandler;
import Tamaized.Voidcraft.world.dim.Xia.castle.logic.battle.IBattleHandler;
import Tamaized.Voidcraft.world.dim.Xia.castle.logic.battle.twins.TwinsBattleHandler;

public class TileEntityXiaCastle extends TileEntity implements ITickable{
	
	private boolean running = false;
	private XiaCastleHandler handler;
	
	//Temp stuff
	private final IBattleHandler twins = new TwinsBattleHandler();
	private IBattleHandler activeHandler;
	
	@Override
	public void update(){
		if(!worldObj.isRemote){
			if(running){
				if(activeHandler != null){
					activeHandler.update();
				}
				//handler.update();
			}else{ //We need to reset everything
				//handler = new XiaCastleHandler(this.worldObj);
				//running = true;
			}
		}
	}
	
	public void start(){
		if(running) stop();
		activeHandler = twins;
		activeHandler.start(worldObj, getPos());
		running = true;
	}
	
	public void stop(){
		if(activeHandler != null){
			activeHandler.stop();
			activeHandler = null;
		}
		running = false;
	}
	
	@Override
	public SPacketUpdateTileEntity func_189518_D_(){	
		NBTTagCompound nbt = new NBTTagCompound();
		func_189515_b(nbt);
		return new SPacketUpdateTileEntity(pos, 2, nbt);
	}
	
	@Override
	public void onDataPacket(NetworkManager netManager, SPacketUpdateTileEntity packet){
		readFromNBT(packet.getNbtCompound());
	}
	
	@Override
	public void readFromNBT(NBTTagCompound nbt){
		super.readFromNBT(nbt);
		running = nbt.hasKey("running") ? nbt.getBoolean("running") : false;
		if(handler == null) running = false;
	}
	
	@Override
	public NBTTagCompound func_189515_b(NBTTagCompound nbt){
		super.func_189515_b(nbt);
		nbt.setBoolean("running", running);
		return nbt;
	}
	
}
