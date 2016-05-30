package Tamaized.Voidcraft.events;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.UUID;

import net.minecraft.block.Block;
import net.minecraft.client.entity.EntityOtherPlayerMP;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.network.play.server.SPacketEntityEffect;
import net.minecraft.network.play.server.SPacketPlayerAbilities;
import net.minecraft.network.play.server.SPacketRespawn;
import net.minecraft.potion.PotionEffect;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.Teleporter;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.WorldServer;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import Tamaized.Voidcraft.common.voidCraft;
import Tamaized.Voidcraft.handlers.PortalDataHandler;

public class VoidTickEvent {
	
	public Map<UUID, PortalDataHandler> data = new HashMap<UUID, PortalDataHandler>();
	
	@SubscribeEvent
	public void onPlayerTick(TickEvent.PlayerTickEvent e) {
		
		//Prevent players from flying in Xia DIM
		if(e.player instanceof EntityPlayerMP){
			EntityPlayerMP player = (EntityPlayerMP) e.player;
			if(e.player.dimension == voidCraft.dimensionIdXia){
				if(!player.capabilities.isCreativeMode && player.capabilities.isFlying){
					player.capabilities.allowFlying = false;
					player.capabilities.isFlying = false;
					player.capabilities.disableDamage = false;
					player.sendPlayerAbilities();
					player.addChatMessage(new TextComponentTranslation(TextFormatting.DARK_GRAY+"You feel heavy, you can not sustain flight"));
				}
			}
		}
		
		if(e.player.worldObj.isRemote) return;
		
		if(data.get(e.player.getGameProfile().getId()) != null){
			
			//Calculate and Modify Overlay Alpha Float Value - Also used to Determine when to Teleport
			BlockPos bPos = new BlockPos(MathHelper.floor_double(e.player.posX), MathHelper.floor_double(e.player.posY-0.2D - (double)e.player.getYOffset()) + 1, MathHelper.floor_double(e.player.posZ));
			Block block = e.player.worldObj.getBlockState(bPos).getBlock();
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
		        }else if(e.player instanceof EntityOtherPlayerMP){
		        	//Client Side do nothing
		        }else{
		        	String err = "ISSUE DETECTED, REPORT THIS TO THE AUTHOR OF VOIDCRAFT; Data: "+j+" : "+e.player+"; "+e.player.getClass();
		        	voidCraft.logger.info(err);
		        	//System.out.println(err);
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
		if(
				player.dimension != data.get(player.getGameProfile().getId()).type &&
				player.dimension != 1 &&
				player.dimension != data.get(player.getGameProfile().getId()).PORTAL_VOID &&
				player.dimension != data.get(player.getGameProfile().getId()).PORTAL_XIA
				){ //Teleport into void/xia
        	data.get(player.getGameProfile().getId()).lastDim = player.dimension;
        	transferPlayerToDimension(
        			player.mcServer,
        			player,
        			data.get(player.getGameProfile().getId()).type,
        			data.get(player.getGameProfile().getId()).getTeleporter(player)
        			);
        }else if(player.dimension == 1){ //From end
        	data.get(player.getGameProfile().getId()).lastDim = player.dimension;
        	transferPlayerToDimension(
        			player.mcServer,
        			player,
        			data.get(player.getGameProfile().getId()).type,
        			data.get(player.getGameProfile().getId()).getTeleporter(player)
        			);
        	transferPlayerToDimension(
        			player.mcServer,
        			player,
        			data.get(player.getGameProfile().getId()).type,
        			data.get(player.getGameProfile().getId()).getTeleporter(player)
        			);
        }else{ //Teleport out of void/xia
        	data.get(player.getGameProfile().getId()).lastDim = data.get(player.getGameProfile().getId()).lastDim == PortalDataHandler.PORTAL_VOID ? 0 : data.get(player.getGameProfile().getId()).lastDim == PortalDataHandler.PORTAL_XIA ? 0 : data.get(player.getGameProfile().getId()).lastDim; //Ensure lastDim never equals Void or Xia IDs
        	transferPlayerToDimension(
        			player.mcServer,
        			player,
        			data.get(player.getGameProfile().getId()).lastDim,
        			data.get(player.getGameProfile().getId()).getTeleporter(player)
        			);
        }
	}
	
	private void transferPlayerToDimension(MinecraftServer mcServer, EntityPlayerMP player, int dimId, Teleporter teleporter){ //Custom Made to handle teleporting to and from The End (DIM 1)
		int j = player.dimension;
		WorldServer worldserver = mcServer.worldServerForDimension(player.dimension);
		player.dimension = dimId;
		WorldServer worldserver1 = mcServer.worldServerForDimension(player.dimension);
		player.connection.sendPacket(new SPacketRespawn(player.dimension, worldserver1.getDifficulty(), worldserver1.getWorldInfo().getTerrainType(), player.interactionManager.getGameType())); // Forge: Use new dimensions information
		mcServer.getPlayerList().updatePermissionLevel(player);
		worldserver.removeEntityDangerously(player);
		player.isDead = false;
		transferEntityToWorld(player, j, worldserver, worldserver1, teleporter);
		mcServer.getPlayerList().preparePlayer(player, worldserver);
		player.connection.setPlayerLocation(player.posX, player.posY, player.posZ, player.rotationYaw, player.rotationPitch);
		player.interactionManager.setWorld(worldserver1);
        player.connection.sendPacket(new SPacketPlayerAbilities(player.capabilities));
		mcServer.getPlayerList().updateTimeAndWeatherForPlayer(player, worldserver1);
		mcServer.getPlayerList().syncPlayerInventory(player);
		Iterator iterator = player.getActivePotionEffects().iterator();
		while (iterator.hasNext()){
			PotionEffect potioneffect = (PotionEffect)iterator.next();
			player.connection.sendPacket(new SPacketEntityEffect(player.getEntityId(), potioneffect));
		}
		FMLCommonHandler.instance().firePlayerChangedDimensionEvent(player, j, dimId);
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
        	teleporter.placeInPortal(p_82448_1_, f);
        	p_82448_4_.spawnEntityInWorld(p_82448_1_);
        	p_82448_4_.updateEntityWithOptionalForce(p_82448_1_, false);
        }
        p_82448_3_.theProfiler.endSection();
        p_82448_1_.setWorld(p_82448_4_);
	}
	
	
}
