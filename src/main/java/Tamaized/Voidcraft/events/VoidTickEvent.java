package Tamaized.Voidcraft.events;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.UUID;

import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.network.play.server.S07PacketRespawn;
import net.minecraft.network.play.server.S1DPacketEntityEffect;
import net.minecraft.potion.PotionEffect;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.MathHelper;
import net.minecraft.world.Teleporter;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.WorldServer;
import Tamaized.Voidcraft.common.voidCraft;
import Tamaized.Voidcraft.world.dim.TheVoid.TeleporterVoid;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import cpw.mods.fml.relauncher.Side;

public class VoidTickEvent {
	
	public Map<UUID, Float> portalTick = new HashMap<UUID, Float>();
	public Map<UUID, Boolean> hasTeleported = new HashMap<UUID, Boolean>();
	public Map<UUID, Integer> teleportLoc = new HashMap<UUID, Integer>();
	
	@SubscribeEvent
	public void onPlayerTick(TickEvent.PlayerTickEvent e) {
		
		//Set Skin on Player based on Name
		/*if(e.side.isClient() && e.player.getGameProfile().getId().equals("Tamaized")){
			AbstractClientPlayer acp = (AbstractClientPlayer) e.player;
			acp.func_152121_a(Type.SKIN, skin);
		}*/
		
		if(portalTick.get(e.player.getGameProfile().getId()) != null){
			//Calculate and Modify Overlay Alpha Float Value - Also used to Determine when to Teleport
			if(e.player.worldObj.getBlock(MathHelper.floor_double(e.player.posX), MathHelper.floor_double(e.player.posY-0.2D - (double)e.player.yOffset) + 1, MathHelper.floor_double(e.player.posZ)) == voidCraft.blocks.blockTeleporterVoid){
				float j = portalTick.get(e.player.getGameProfile().getId());
				if(j < 0.8F) j = j + 0.004F;
				else j = 0.8F;
				portalTick.put(e.player.getGameProfile().getId(), j);
			}else{
				float j = portalTick.get(e.player.getGameProfile().getId());
				if(j > 0.0F) j = j - 0.005F;
				else j = 0.0F;
				portalTick.put(e.player.getGameProfile().getId(), j);
			}
			
			//Teleport Player to Dimension when Ready
			
			if(!hasTeleported.get(e.player.getGameProfile().getId()) && portalTick.get(e.player.getGameProfile().getId()) >= 0.8F){
				System.out.println(e.player);
				if (e.player instanceof EntityPlayerMP){
		            EntityPlayerMP player = (EntityPlayerMP)e.player;
		            if(teleportLoc.containsKey(player.getGameProfile().getId()) && player.dimension == voidCraft.dimensionIdVoid){
		            	player.mcServer.getConfigurationManager().transferPlayerToDimension(player, teleportLoc.get(player.getGameProfile().getId()), new TeleporterVoid(player.mcServer.worldServerForDimension(teleportLoc.get(player.getGameProfile().getId()))));
		            	//if(teleportLoc.get(player.getGameProfile().getId())==1) player.mcServer.getConfigurationManager().transferPlayerToDimension(player, teleportLoc.get(player.getGameProfile().getId()), new TeleporterVoid(player.mcServer.worldServerForDimension(teleportLoc.get(player.getGameProfile().getId()))));
		            }else if(player.dimension != voidCraft.dimensionIdVoid && player.dimension != 1) {
		            	teleportLoc.put(player.getGameProfile().getId(), player.dimension);
		            	player.mcServer.getConfigurationManager().transferPlayerToDimension(player, voidCraft.dimensionIdVoid, new TeleporterVoid(player.mcServer.worldServerForDimension(voidCraft.dimensionIdVoid)));
		            }else if(player.dimension == 1){
		            	teleportLoc.put(player.getGameProfile().getId(), player.dimension);
		            	player.mcServer.getConfigurationManager().transferPlayerToDimension(player, voidCraft.dimensionIdVoid, new TeleporterVoid(player.mcServer.worldServerForDimension(voidCraft.dimensionIdVoid)));
		            	player.mcServer.getConfigurationManager().transferPlayerToDimension(player, voidCraft.dimensionIdVoid, new TeleporterVoid(player.mcServer.worldServerForDimension(voidCraft.dimensionIdVoid)));
		            }else{
		            	teleportLoc.put(player.getGameProfile().getId(), player.dimension);
		            	transferPlayerToDimension(player.mcServer, player, 0, new TeleporterVoid(player.mcServer.worldServerForDimension(0)));
		            }
		        }else if(e.player instanceof EntityClientPlayerMP){
		        	EntityClientPlayerMP player = (EntityClientPlayerMP) e.player;
		        	if(teleportLoc.containsKey(player.getGameProfile().getId()) && player.dimension == voidCraft.dimensionIdVoid){
		            	player.travelToDimension(teleportLoc.get(player.getGameProfile().getId()));
		            	//if(teleportLoc.get(player.getGameProfile().getId())==1) player.mcServer.getConfigurationManager().transferPlayerToDimension(player, teleportLoc.get(player.getGameProfile().getId()), new TeleporterVoid(player.mcServer.worldServerForDimension(teleportLoc.get(player.getGameProfile().getId()))));
		            }else if(player.dimension != voidCraft.dimensionIdVoid && player.dimension != 1) {
		            	teleportLoc.put(player.getGameProfile().getId(), player.dimension);
		            	player.travelToDimension(voidCraft.dimensionIdVoid);
		            }else if(player.dimension == 1){
		            	teleportLoc.put(player.getGameProfile().getId(), player.dimension);
		            	player.travelToDimension(voidCraft.dimensionIdVoid);
		            	player.travelToDimension(voidCraft.dimensionIdVoid);
		            }else{
		            	teleportLoc.put(player.getGameProfile().getId(), player.dimension);
		            	player.travelToDimension(0);
		            }
		        }else{
		        	String err = "ISSUE DETECTED, REPORT THIS TO THE AUTHOR OF VOIDCRAFT; Data: "+e.player+"; "+e.player.getClass();
		        	voidCraft.logger.info(err);
		        	System.out.println(err);
		        }
	            hasTeleported.put(e.player.getGameProfile().getId(), true);
			}
			
			if(portalTick.get(e.player.getGameProfile().getId()) <= 0.0F){
				hasTeleported.put(e.player.getGameProfile().getId(), false);
			}
		}else{
			voidCraft.logger.info("Adding UUID: '"+e.player.getGameProfile().getId()+"' to Portal Overlay Handler");
			portalTick.put(e.player.getGameProfile().getId(), 0.0F);
			hasTeleported.put(e.player.getGameProfile().getId(), false);
		}	
	}
	
	private void transferPlayerToDimension(MinecraftServer mcServer, EntityPlayerMP p_72356_1_, int p_72356_2_, Teleporter teleporter){ //Custom Made to handle teleporting to The End (DIM 1)
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
	
	private void transferEntityToWorld(Entity p_82448_1_, int p_82448_2_, WorldServer p_82448_3_, WorldServer p_82448_4_, Teleporter teleporter){ //Custom Made to handle teleporting to The End (DIM 1)
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
