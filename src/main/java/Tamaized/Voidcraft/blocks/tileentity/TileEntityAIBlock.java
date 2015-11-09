package Tamaized.Voidcraft.blocks.tileentity;

import io.netty.buffer.ByteBufOutputStream;
import io.netty.buffer.Unpooled;

import java.io.DataOutputStream;
import java.io.IOException;

import cpw.mods.fml.common.network.NetworkRegistry.TargetPoint;
import cpw.mods.fml.common.network.internal.FMLProxyPacket;
import Tamaized.Voidcraft.blocks.AIBlock;
import Tamaized.Voidcraft.common.voidCraft;
import Tamaized.Voidcraft.common.handlers.VoidCraftClientPacketHandler;
import Tamaized.Voidcraft.machina.addons.VoidTank;
import Tamaized.Voidcraft.mobs.ai.EntityAIHandler;
import Tamaized.Voidcraft.mobs.ai.handler.IHandlerAI;
import Tamaized.Voidcraft.mobs.entity.boss.EntityMobHerobrine;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.nbt.NBTTagString;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;

public class TileEntityAIBlock extends TileEntity {
	
	public EntityAIHandler aiHandler;
	public IHandlerAI ai;
	public int state = 0;
	private boolean keep = false;
	
	public TileEntityAIBlock(){
		super();
		keep = true;
	}
	
	public int getState(){
		return state;
	}
	
	public void boom(){
		if(aiHandler != null && aiHandler.getEntity() instanceof EntityMobHerobrine && state < 3) state++;
		if(state > 2){
			((EntityMobHerobrine) aiHandler.getEntity()).doDamage(20);
			ai.removeTileEntity(new int[]{xCoord, yCoord, zCoord});
			this.worldObj.setBlockToAir(xCoord, yCoord+2, zCoord);
			this.worldObj.setBlockToAir(xCoord, yCoord+1, zCoord);
			this.worldObj.setBlockToAir(xCoord, yCoord, zCoord);
			this.worldObj.removeTileEntity(xCoord, yCoord, zCoord);
		}
	}
	
	public void updateEntity(){
		if(!this.worldObj.isRemote){
			if(aiHandler == null || ai == null || !keep){
				this.worldObj.setBlockToAir(xCoord, yCoord+2, zCoord);
				this.worldObj.setBlockToAir(xCoord, yCoord+1, zCoord);
				this.worldObj.setBlockToAir(xCoord, yCoord, zCoord);
				this.worldObj.removeTileEntity(xCoord, yCoord, zCoord);
			}else{
				for(int i=0; i<3; i++){
					if(this.worldObj.getBlockMetadata(xCoord, yCoord+i, zCoord) != state){
						this.worldObj.setBlockMetadataWithNotify(xCoord, yCoord+i, zCoord, state, 2);
					}
				}
			}
			sendPacketToClients();
		}
	}
	
	private void sendPacketToClients(){
		NBTTagCompound znbt = new NBTTagCompound();
		this.writeToNBT(znbt);
		
		ByteBufOutputStream bos = new ByteBufOutputStream(Unpooled.buffer());
		DataOutputStream outputStream = new DataOutputStream(bos);
	    try {
	    	outputStream.writeInt(VoidCraftClientPacketHandler.TYPE_TE_UPDATE);
	        outputStream.writeInt(this.xCoord);
	        outputStream.writeInt(this.yCoord);
	        outputStream.writeInt(this.zCoord);
	        outputStream.writeInt(this.state);
	        FMLProxyPacket packet = new FMLProxyPacket(bos.buffer(), voidCraft.networkChannelName);
		    TargetPoint point = new TargetPoint(worldObj.provider.dimensionId, xCoord, yCoord, zCoord, 50.0D);
			if(voidCraft.channel != null && packet != null && point != null) voidCraft.channel.sendToAllAround(packet, point);
		    this.getDescriptionPacket();
		    this.markDirty();
		    bos.close();
	    }catch (IOException e) {
			e.printStackTrace();
		}
		this.markDirty();
	}
	
	@Override
	public Packet getDescriptionPacket(){	
		NBTTagCompound nbt = new NBTTagCompound();
		this.writeToNBT(nbt);
		nbt.setInteger("state", state);
		return new S35PacketUpdateTileEntity(this.xCoord, this.yCoord, this.zCoord, 2, nbt);
	}
	
	@Override
	public void onDataPacket(NetworkManager netManager, S35PacketUpdateTileEntity packet){
		readFromNBT(packet.func_148857_g());
	}
	
	public void readFromNBT(NBTTagCompound nbt){
		super.readFromNBT(nbt);
		state = nbt.getInteger("state");
	}
	
	public void writeToNBT(NBTTagCompound nbt){
		super.writeToNBT(nbt);
		nbt.setInteger("state", state);
	}

}
