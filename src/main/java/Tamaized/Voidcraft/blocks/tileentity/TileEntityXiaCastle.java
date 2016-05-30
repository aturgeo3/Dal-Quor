package Tamaized.Voidcraft.blocks.tileentity;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
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
