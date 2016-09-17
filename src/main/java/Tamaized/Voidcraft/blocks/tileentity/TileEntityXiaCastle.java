package Tamaized.Voidcraft.blocks.tileentity;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.util.ITickable;
import Tamaized.TamModized.tileentity.TamTileEntity;
import Tamaized.Voidcraft.xiaCastle.XiaCastleHandler;
import Tamaized.Voidcraft.xiaCastle.logic.battle.IBattleHandler;
import Tamaized.Voidcraft.xiaCastle.logic.battle.Xia.XiaBattleHandler;
import Tamaized.Voidcraft.xiaCastle.logic.battle.herobrine.HerobrineBattleHandler;
import Tamaized.Voidcraft.xiaCastle.logic.battle.twins.TwinsBattleHandler;

public class TileEntityXiaCastle extends TamTileEntity implements ITickable{
	
	private boolean running = false;
	private XiaCastleHandler handler;
	
	//Temp stuff
	private final IBattleHandler twins = new TwinsBattleHandler();
	private final IBattleHandler herobrine = new HerobrineBattleHandler();
	private final IBattleHandler xia = new XiaBattleHandler();
	
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
		activeHandler = xia;
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
	public SPacketUpdateTileEntity getUpdatePacket(){	
		NBTTagCompound nbt = new NBTTagCompound();
		writeToNBT(nbt);
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
	public NBTTagCompound writeToNBT(NBTTagCompound nbt){
		super.writeToNBT(nbt);
		nbt.setBoolean("running", running);
		return nbt;
	}
	
}
