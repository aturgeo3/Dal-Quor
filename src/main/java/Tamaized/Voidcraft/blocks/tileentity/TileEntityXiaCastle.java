package Tamaized.Voidcraft.blocks.tileentity;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;
import Tamaized.Voidcraft.world.dim.Xia.castle.XiaCastleHandler;

public class TileEntityXiaCastle extends TileEntity implements ITickable{
	
	private boolean running = false;
	private XiaCastleHandler handler;
	
	@Override
	public void update(){
		if(!worldObj.isRemote){
			if(running){
				handler.update();
			}else{ //We need to reset everything
				handler = new XiaCastleHandler(this.worldObj);
				running = true;
			}
		}
	}
	
	@Override
	public Packet getDescriptionPacket(){	
		NBTTagCompound nbt = new NBTTagCompound();
		writeToNBT(nbt);
		return new S35PacketUpdateTileEntity(pos, 2, nbt);
	}
	
	@Override
	public void onDataPacket(NetworkManager netManager, S35PacketUpdateTileEntity packet){
		readFromNBT(packet.getNbtCompound());
	}
	
	public void readFromNBT(NBTTagCompound nbt){
		super.readFromNBT(nbt);
		running = nbt.hasKey("running") ? nbt.getBoolean("running") : false;
		if(handler == null) running = false;
	}
	
	public void writeToNBT(NBTTagCompound nbt){
		super.writeToNBT(nbt);
		nbt.setBoolean("running", running);
	}
	
}
