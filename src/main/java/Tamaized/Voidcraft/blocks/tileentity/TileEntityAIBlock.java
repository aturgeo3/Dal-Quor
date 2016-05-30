package Tamaized.Voidcraft.blocks.tileentity;

import io.netty.buffer.ByteBufOutputStream;
import io.netty.buffer.Unpooled;

import java.io.DataOutputStream;
import java.io.IOException;

import net.minecraft.block.state.IBlockState;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;
import net.minecraftforge.fml.common.network.NetworkRegistry.TargetPoint;
import net.minecraftforge.fml.common.network.internal.FMLProxyPacket;
import Tamaized.Voidcraft.common.voidCraft;
import Tamaized.Voidcraft.common.handlers.VoidCraftClientPacketHandler;
import Tamaized.Voidcraft.mobs.ai.EntityAIHandler;
import Tamaized.Voidcraft.mobs.ai.handler.IHandlerAI;
import Tamaized.Voidcraft.mobs.entity.boss.EntityMobHerobrine;

public class TileEntityAIBlock extends TileEntity implements ITickable{
	
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
			ai.removeTileEntity(pos);
			this.worldObj.setBlockToAir(pos.add(0, 2, 0));
			this.worldObj.setBlockToAir(pos.add(0, 1, 0));
			this.worldObj.setBlockToAir(pos);
			this.worldObj.removeTileEntity(pos);
		}
	}
	
	@Override
	public void update(){
		if(!this.worldObj.isRemote){
			if(aiHandler == null || ai == null || !keep){
				this.worldObj.setBlockToAir(pos.add(0, 2, 0));
				this.worldObj.setBlockToAir(pos.add(0, 1, 0));
				this.worldObj.setBlockToAir(pos);
				this.worldObj.removeTileEntity(pos);
			}else{
				for(int i=0; i<3; i++){
					IBlockState Bstate = this.worldObj.getBlockState(pos.add(0, i, 0));
					if(Bstate.getBlock().getMetaFromState(Bstate) != state){
						worldObj.setBlockState(pos.add(0, i, 0), Bstate.getBlock().getStateFromMeta(state), 2);
					}
				}
			}
			sendPacketToClients();
		}
	}
	
	private void sendPacketToClients(){
		NBTTagCompound znbt = new NBTTagCompound();
		this.func_189515_b(znbt);
		
		ByteBufOutputStream bos = new ByteBufOutputStream(Unpooled.buffer());
		DataOutputStream outputStream = new DataOutputStream(bos);
	    try {
	    	outputStream.writeInt(VoidCraftClientPacketHandler.TYPE_TE_UPDATE);
	        outputStream.writeInt(this.pos.getX());
	        outputStream.writeInt(this.pos.getY());
	        outputStream.writeInt(this.pos.getZ());
	        outputStream.writeInt(this.state);
	        FMLProxyPacket packet = new FMLProxyPacket(new PacketBuffer(bos.buffer()), voidCraft.networkChannelName);
		    TargetPoint point = new TargetPoint(worldObj.provider.getDimension(), pos.getX(), pos.getY(), pos.getZ(), 50.0D);
			if(voidCraft.channel != null && packet != null && point != null) voidCraft.channel.sendToAllAround(packet, point);
		    this.func_189518_D_();
		    this.markDirty();
		    bos.close();
	    }catch (IOException e) {
			e.printStackTrace();
		}
		this.markDirty();
	}
	
	@Override
	public SPacketUpdateTileEntity func_189518_D_(){	
		NBTTagCompound nbt = new NBTTagCompound();
		this.func_189515_b(nbt);
		nbt.setInteger("state", state);
		return new SPacketUpdateTileEntity(pos, 2, nbt);
	}
	
	@Override
	public void onDataPacket(NetworkManager netManager, SPacketUpdateTileEntity packet){
		readFromNBT(packet.getNbtCompound());
	}

	@Override
	public void readFromNBT(NBTTagCompound nbt){
		super.readFromNBT(nbt);
		state = nbt.getInteger("state");
	}
	
	@Override
	public NBTTagCompound func_189515_b(NBTTagCompound nbt){
		super.func_189515_b(nbt);
		nbt.setInteger("state", state);
		return nbt;
	}

}
