package Tamaized.Voidcraft.events;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.UUID;

import net.minecraft.block.Block;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.network.play.server.S07PacketRespawn;
import net.minecraft.network.play.server.S1DPacketEntityEffect;
import net.minecraft.potion.PotionEffect;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.MathHelper;
import net.minecraft.world.Teleporter;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.WorldServer;
import Tamaized.Voidcraft.common.voidCraft;
import Tamaized.Voidcraft.handlers.PortalDataHandler;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;

public class VoidTickEvent {
	
	public Map<UUID, PortalDataHandler> data = new HashMap<UUID, PortalDataHandler>();
	private Map<UUID, Boolean> canEdit = new HashMap<UUID, Boolean>();
	
	@SubscribeEvent
	public void onPlayerTick(TickEvent.PlayerTickEvent e) {
		
		//Prevent players from flying aswell as breaking and placing blocks in Xia DIM
		if(e.player instanceof EntityPlayerMP){
			EntityPlayerMP player = (EntityPlayerMP) e.player;
			if(e.player.dimension == voidCraft.dimensionIdXia){
				if(!player.capabilities.isCreativeMode && player.capabilities.isFlying){
					player.capabilities.allowFlying = false;
					player.capabilities.isFlying = false;
					player.capabilities.disableDamage = false;
					player.sendPlayerAbilities();
					player.addChatMessage(new ChatComponentText(EnumChatFormatting.DARK_GRAY+"You feel heavy, you can not sustain flight"));
				}
				if(!player.capabilities.isCreativeMode && player.capabilities.allowEdit == true){
					canEdit.put(player.getGameProfile().getId(), false);
					player.capabilities.allowEdit = false;
				}
			}else{
				if(canEdit.containsKey(player.getGameProfile().getId()) && !canEdit.get(player.getGameProfile().getId())){
					player.capabilities.allowEdit = true;
					canEdit.put(player.getGameProfile().getId(), true);
				}
			}
		}
		
		if(data.get(e.player.getGameProfile().getId()) != null){
			
			//Calculate and Modify Overlay Alpha Float Value - Also used to Determine when to Teleport
			Block block = e.player.worldObj.getBlock(MathHelper.floor_double(e.player.posX), MathHelper.floor_double(e.player.posY-0.2D - (double)e.player.yOffset) + 1, MathHelper.floor_double(e.player.posZ));
			float j = data.get(e.player.getGameProfile().getId()).tick;
			if(j <= 0){
				if(data.get(e.player.getGameProfile().getId()).type != PortalDataHandler.PORTAL_VOID && block == voidCraft.blocks.blockPortalVoid){
					data.get(e.player.getGameProfile().getId()).type = PortalDataHandler.PORTAL_VOID;
				}else if(data.get(e.player.getGameProfile().getId()).type != PortalDataHandler.PORTAL_XIA && block == voidCraft.blocks.blockPortalXia){
					data.get(e.player.getGameProfile().getId()).type = PortalDataHandler.PORTAL_XIA;
				}else{
					data.get(e.player.getGameProfile().getId()).type = 0;
				}
			}
			
			if((data.get(e.player.getGameProfile().getId()).type == PortalDataHandler.PORTAL_VOID && block == voidCraft.blocks.blockPortalVoid) || (data.get(e.player.getGameProfile().getId()).type == PortalDataHandler.PORTAL_XIA && block == voidCraft.blocks.blockPortalXia)){
				if(j < 0.8F) j = j + 0.004F;
				else j = 0.8F;
				data.get(e.player.getGameProfile().getId()).tick = j;
			}else{
				j = data.get(e.player.getGameProfile().getId()).tick;
				if(j > 0.0F) j = j - 0.005F;
				else j = 0.0F;
				data.get(e.player.getGameProfile().getId()).tick = j;
			}
			
			//Teleport Player to Dimension when Ready
			if(!data.get(e.player.getGameProfile().getId()).hasTeleported && data.get(e.player.getGameProfile().getId()).tick >= 0.8F){
				//System.out.println(e.player);
				if (e.player instanceof EntityPlayerMP){
		            teleport((EntityPlayerMP) e.player);
		        }else if(e.player instanceof EntityClientPlayerMP){
		        	//Client Side do nothing
		        }else{
		        	String err = "ISSUE DETECTED, REPORT THIS TO THE AUTHOR OF VOIDCRAFT; Data: "+e.player+"; "+e.player.getClass();
		        	voidCraft.logger.info(err);
		        	System.out.println(err);
		        }
				data.get(e.player.getGameProfile().getId()).hasTeleported = true;
			}
			
			if(data.get(e.player.getGameProfile().getId()).tick <= 0.0F){
				data.get(e.player.getGameProfile().getId()).hasTeleported = false;
			}
		}else{
			voidCraft.logger.info("Adding UUID: '"+e.player.getGameProfile().getId()+"' ("+e.player.getGameProfile().getName()+") to Portal Overlay Handler");
			data.put(e.player.getGameProfile().getId(), new PortalDataHandler(e.player.getGameProfile().getId(), e.player.dimension == PortalDataHandler.PORTAL_VOID ? 0 : e.player.dimension == PortalDataHandler.PORTAL_XIA ? 0 : e.player.dimension));
		}	
	}
	
	private void teleport(EntityPlayerMP player){
		if(player.dimension != data.get(player.getGameProfile().getId()).type && player.dimension != 1 && player.dimension != data.get(player.getGameProfile().getId()).PORTAL_VOID && player.dimension != data.get(player.getGameProfile().getId()).PORTAL_XIA) { //Teleport into void/xia
        	data.get(player.getGameProfile().getId()).lastDim = player.dimension;
        	transferPlayerToDimension(player.mcServer, player, data.get(player.getGameProfile().getId()).type, data.get(player.getGameProfile().getId()).getTeleporter(player));
        }else if(player.dimension == 1){ //From end
        	data.get(player.getGameProfile().getId()).lastDim = player.dimension;
        	transferPlayerToDimension(player.mcServer, player, data.get(player.getGameProfile().getId()).type, data.get(player.getGameProfile().getId()).getTeleporter(player));
        	transferPlayerToDimension(player.mcServer, player, data.get(player.getGameProfile().getId()).type, data.get(player.getGameProfile().getId()).getTeleporter(player));
        }else{ //Teleport out of void/xia
        	data.get(player.getGameProfile().getId()).lastDim = data.get(player.getGameProfile().getId()).lastDim == PortalDataHandler.PORTAL_VOID ? 0 : data.get(player.getGameProfile().getId()).lastDim == PortalDataHandler.PORTAL_XIA ? 0 : data.get(player.getGameProfile().getId()).lastDim; //Ensure lastDim never equals Void or Xia IDs
        	transferPlayerToDimension(player.mcServer, player, data.get(player.getGameProfile().getId()).lastDim, data.get(player.getGameProfile().getId()).getTeleporter(player));
        }
	}
	
	private void transferPlayerToDimension(MinecraftServer mcServer, EntityPlayerMP p_72356_1_, int p_72356_2_, Teleporter teleporter){ //Custom Made to handle teleporting to and from The End (DIM 1)
		int j = p_72356_1_.dimension;
		WorldServer worldserver = mcServer.worldServerForDimension(p_72356_1_.dimension);
		p_72356_1_.dimension = p_72356_2_;
		WorldServer worldserver1 = mcServer.worldServerForDimension(p_72356_1_.dimension);
		p_72356_1_.playerNetServerHandler.sendPacket(new S07PacketRespawn(p_72356_1_.dimension, worldserver1.difficultySetting, worldserver1.getWorldInfo().getTerrainType(), p_72356_1_.theItemInWorldManager.getGameType())); // Forge: Use new dimensions information
		worldserver.removePlayerEntityDangerously(p_72356_1_);
		p_72356_1_.isDead = false;
		transferEntityToWorld(p_72356_1_, j, worldserver, worldserver1, teleporter);
		mcServer.getConfigurationManager().func_72375_a(p_72356_1_, worldserver);
		p_72356_1_.playerNetServerHandler.setPlayerLocation(p_72356_1_.posX, p_72356_1_.posY, p_72356_1_.posZ, p_72356_1_.rotationYaw, p_72356_1_.rotationPitch);
		p_72356_1_.theItemInWorldManager.setWorld(worldserver1);
		mcServer.getConfigurationManager().updateTimeAndWeatherForPlayer(p_72356_1_, worldserver1);
		mcServer.getConfigurationManager().syncPlayerInventory(p_72356_1_);
		Iterator iterator = p_72356_1_.getActivePotionEffects().iterator();
		while (iterator.hasNext()){
			PotionEffect potioneffect = (PotionEffect)iterator.next();
			p_72356_1_.playerNetServerHandler.sendPacket(new S1DPacketEntityEffect(p_72356_1_.getEntityId(), potioneffect));
		}
		FMLCommonHandler.instance().firePlayerChangedDimensionEvent(p_72356_1_, j, p_72356_2_);
	}
	
	private void transferEntityToWorld(Entity p_82448_1_, int p_82448_2_, WorldServer p_82448_3_, WorldServer p_82448_4_, Teleporter teleporter){ //Custom Made to handle teleporting to and from The End (DIM 1)
		WorldProvider pOld = p_82448_3_.provider;
		WorldProvider pNew = p_82448_4_.provider;
        double moveFactor = pOld.getMovementFactor() / pNew.getMovementFactor();
        double d0 = p_82448_1_.posX * moveFactor;
        double d1 = p_82448_1_.posZ * moveFactor;
        double d3 = p_82448_1_.posX;
        double d4 = p_82448_1_.posY;
        double d5 = p_82448_1_.posZ;
        float f = p_82448_1_.rotationYaw;
        p_82448_3_.theProfiler.startSection("moving");
        p_82448_3_.theProfiler.endSection();
        
        p_82448_3_.theProfiler.startSection("placing");
        d0 = (double)MathHelper.clamp_int((int)d0, -29999872, 29999872);
        d1 = (double)MathHelper.clamp_int((int)d1, -29999872, 29999872);

        if (p_82448_1_.isEntityAlive()){
        	p_82448_1_.setLocationAndAngles(d0, p_82448_1_.posY, d1, p_82448_1_.rotationYaw, p_82448_1_.rotationPitch);
        	teleporter.placeInPortal(p_82448_1_, d3, d4, d5, f);
        	p_82448_4_.spawnEntityInWorld(p_82448_1_);
        	p_82448_4_.updateEntityWithOptionalForce(p_82448_1_, false);
        }
        p_82448_3_.theProfiler.endSection();
        p_82448_1_.setWorld(p_82448_4_);
	}
	
	
}
