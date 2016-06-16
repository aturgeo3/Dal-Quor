package Tamaized.Voidcraft.voidicInfusion;

import io.netty.buffer.ByteBufOutputStream;
import io.netty.buffer.Unpooled;

import java.io.DataOutputStream;
import java.io.IOException;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.network.PacketBuffer;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraftforge.fml.common.network.internal.FMLProxyPacket;
import Tamaized.Voidcraft.DamageSources.DamageSourceVoidicInfusion;
import Tamaized.Voidcraft.common.voidCraft;
import Tamaized.Voidcraft.common.handlers.VoidCraftClientPacketHandler;

public class PlayerInfusionHandler {
	
	private EntityPlayerMP playerMP;
	private EntityPlayer player;
	private int voidicInfusionAmount;
	private int maxAmount = 300;
	private boolean canFly = false;
	
	private int tick = 0;
	
	public PlayerInfusionHandler(EntityPlayerMP p){
		playerMP = p;
		voidicInfusionAmount = 0;
	}
	
	public PlayerInfusionHandler(EntityPlayerMP p, int a){
		this(p);
		voidicInfusionAmount = a;
	}
	
	public PlayerInfusionHandler(EntityPlayerMP p, int a, int m){
		this(p, a);
		maxAmount = m;
	}
	
	public int getAmount(){
		return voidicInfusionAmount;
	}
	
	public void update(){
		if(player == null || player.isDead){
			for(WorldServer world : playerMP.getServer().worldServers){
				player = world.getPlayerEntityByUUID(playerMP.getGameProfile().getId());
				if(player != null) break;
			}
		}
		if(player == null) return;
		if(player instanceof EntityPlayerMP) playerMP = (EntityPlayerMP) player;
		if(tick % 20 == 0){
			if(player.worldObj.provider.getDimension() == voidCraft.dimensionIdVoid){
				voidicInfusionAmount++;
				if(voidicInfusionAmount > maxAmount) voidicInfusionAmount = maxAmount;
			}else{
				voidicInfusionAmount-=10;
				if(voidicInfusionAmount < 0) voidicInfusionAmount = 0;
			}
		}
		if(player != null) handleEffects();
		if(tick % (5*20) == 0){ //Send packet every 5 seconds to stay synchronized
			sendPacketUpdates();
			tick = 1;
		}else tick++;
	}
	
	private void handleEffects(){
		if(player.capabilities.isCreativeMode) return;
		if(voidicInfusionAmount >= maxAmount){
			player.attackEntityFrom(new DamageSourceVoidicInfusion(), player.getMaxHealth());
			return;
		}
		float perc = ((float)voidicInfusionAmount/(float)maxAmount);
		if(perc >= 0.75f){ //Flight
			player.capabilities.allowFlying = true;
			canFly = true;
			player.sendPlayerAbilities();
		}else{
			if(canFly){
				player.capabilities.allowFlying = false;
				player.capabilities.isFlying = false;
				player.capabilities.disableDamage = false;
				canFly = false;
				player.sendPlayerAbilities();
			}
		}
		if(perc >= 0.90f){
			player.setHealth(1);
		}
	}
	
	public PlayerInfusionHandler sendPacketUpdates(){
		ByteBufOutputStream bos = new ByteBufOutputStream(Unpooled.buffer());
		DataOutputStream outputStream = new DataOutputStream(bos);
		try {
			outputStream.writeInt(VoidCraftClientPacketHandler.TYPE_INFUSION_UPDATE);
			outputStream.writeInt(voidicInfusionAmount);
			outputStream.writeInt(maxAmount);
			FMLProxyPacket packet = new FMLProxyPacket(new PacketBuffer(bos.buffer()), voidCraft.networkChannelName);
			if(voidCraft.channel != null && packet != null && playerMP != null) voidCraft.channel.sendTo(packet, playerMP);
			if(player != null){
				if(canFly) player.capabilities.allowFlying = true;
				player.sendPlayerAbilities();
			}
			bos.close();
		}catch (IOException e) {
			e.printStackTrace();
		}
		return this;
	}

}
