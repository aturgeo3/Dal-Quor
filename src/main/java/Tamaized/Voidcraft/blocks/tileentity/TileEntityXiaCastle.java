package Tamaized.Voidcraft.blocks.tileentity;

import Tamaized.Voidcraft.world.dim.Xia.castle.XiaCastleHandler;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;

public class TileEntityXiaCastle extends TileEntity{
	
	private boolean running = false;
	private XiaCastleHandler handler;
	
	public void updateEntity(){
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
		return new S35PacketUpdateTileEntity(xCoord, yCoord, zCoord, 2, nbt);
	}
	
	@Override
	public void onDataPacket(NetworkManager netManager, S35PacketUpdateTileEntity packet){
		readFromNBT(packet.func_148857_g());
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
