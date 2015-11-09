package Tamaized.Voidcraft.events;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import net.minecraft.entity.Entity;
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

public class VoidTickEvent {
	
	public Map<String, Float> portalTick = new HashMap<String, Float>();
	public Map<String, Boolean> hasTeleported = new HashMap<String, Boolean>();
	public Map<String, Integer> teleportLoc = new HashMap<String, Integer>();
	
	@SubscribeEvent
	public void onPlayerTick(TickEvent.PlayerTickEvent e) {
		
		//Set Skin on Player based on Name
		/*if(e.side.isClient() && e.player.getGameProfile().getName().equals("Tamaized")){
			AbstractClientPlayer acp = (AbstractClientPlayer) e.player;
			acp.func_152121_a(Type.SKIN, skin);
		}*/
		
		
		if(portalTick.get(e.player.getGameProfile().getName()) != null){
			//Calculate and Modify Overlay Alpha Float Value - Also used to Determine when to Teleport
			if(e.player.worldObj.getBlock(MathHelper.floor_double(e.player.posX), MathHelper.floor_double(e.player.posY-0.2D - (double)e.player.yOffset) + 1, MathHelper.floor_double(e.player.posZ)) == voidCraft.blockTeleporterVoid){
				float j = portalTick.get(e.player.getGameProfile().getName());
				if(j < 0.8F) j = j + 0.004F;
				else j = 0.8F;
				portalTick.put(e.player.getGameProfile().getName(), j);
			}else{
				float j = portalTick.get(e.player.getGameProfile().getName());
				if(j > 0.0F) j = j - 0.005F;
				else j = 0.0F;
				portalTick.put(e.player.getGameProfile().getName(), j);
			}
			
			//Teleport Player to Dimension when Ready
			if(!hasTeleported.get(e.player.getGameProfile().getName()) && portalTick.get(e.player.getGameProfile().getName()) >= 0.8F){
				if (e.player instanceof EntityPlayerMP){
		            EntityPlayerMP thePlayer = (EntityPlayerMP)e.player;
		            if(teleportLoc.containsKey(thePlayer.getGameProfile().getName()) && thePlayer.dimension == voidCraft.dimensionIdVoid){
		            	thePlayer.mcServer.getConfigurationManager().transferPlayerToDimension(thePlayer, teleportLoc.get(thePlayer.getGameProfile().getName()), new TeleporterVoid(thePlayer.mcServer.worldServerForDimension(teleportLoc.get(thePlayer.getGameProfile().getName()))));
		            	//if(teleportLoc.get(thePlayer.getGameProfile().getName())==1) thePlayer.mcServer.getConfigurationManager().transferPlayerToDimension(thePlayer, teleportLoc.get(thePlayer.getGameProfile().getName()), new TeleporterVoid(thePlayer.mcServer.worldServerForDimension(teleportLoc.get(thePlayer.getGameProfile().getName()))));
		            }else if(thePlayer.dimension != voidCraft.dimensionIdVoid && thePlayer.dimension != 1) {
		            	teleportLoc.put(thePlayer.getGameProfile().getName(), thePlayer.dimension);
		            	thePlayer.mcServer.getConfigurationManager().transferPlayerToDimension(thePlayer, voidCraft.dimensionIdVoid, new TeleporterVoid(thePlayer.mcServer.worldServerForDimension(voidCraft.dimensionIdVoid)));
		            }else if(thePlayer.dimension == 1){
		            	teleportLoc.put(thePlayer.getGameProfile().getName(), thePlayer.dimension);
		            	thePlayer.mcServer.getConfigurationManager().transferPlayerToDimension(thePlayer, voidCraft.dimensionIdVoid, new TeleporterVoid(thePlayer.mcServer.worldServerForDimension(voidCraft.dimensionIdVoid)));
		            	thePlayer.mcServer.getConfigurationManager().transferPlayerToDimension(thePlayer, voidCraft.dimensionIdVoid, new TeleporterVoid(thePlayer.mcServer.worldServerForDimension(voidCraft.dimensionIdVoid)));
		            }else{
		            	teleportLoc.put(thePlayer.getGameProfile().getName(), thePlayer.dimension);
		            	transferPlayerToDimension(thePlayer.mcServer, thePlayer, 0, new TeleporterVoid(thePlayer.mcServer.worldServerForDimension(0)));
		            }
		            hasTeleported.put(e.player.getGameProfile().getName(), true);
		        }
			}
			
			if(portalTick.get(e.player.getGameProfile().getName()) <= 0.0F){
				hasTeleported.put(e.player.getGameProfile().getName(), false);
			}
		}else{
			voidCraft.logger.info("Adding Player: '"+e.player.getGameProfile().getName()+"' to Portal Overlay Handler");
			portalTick.put(e.player.getGameProfile().getName(), 0.0F);
			hasTeleported.put(e.player.getGameProfile().getName(), false);
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
