package Tamaized.Voidcraft.armor;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import Tamaized.TamModized.armors.TamArmor;
//import net.minecraftforge.event.entity.player.PlayerEvent;
import Tamaized.Voidcraft.voidCraft;

public class XiaArmor extends TamArmor {

	public static List<String> playersWithFlight = new ArrayList();
	
	public XiaArmor(CreativeTabs tab, ArmorMaterial armorMaterial, int par3, EntityEquipmentSlot par4, String type, String n) {
		super(tab, armorMaterial, par3, par4, type, n);
		MinecraftForge.EVENT_BUS.register(this);
		setHasSubtypes(true);
	}
	
	//Thanks to Vazkii's Botania Mod Source for this part
	@SubscribeEvent
	public void updatePlayerFlyStatus(LivingUpdateEvent event) {
		if(event.getEntityLiving() instanceof EntityPlayer) {
			EntityPlayer player = (EntityPlayer) event.getEntityLiving();

			if(playersWithFlight.contains(playerStr(player))) {
				if(shouldPlayerHaveFlight(player)) { //Keeps the Flight on
					player.capabilities.allowFlying = true;
				} else { //Player should not have Flight
					if(!player.capabilities.isCreativeMode) {
						player.capabilities.allowFlying = false;
						player.capabilities.isFlying = false;
						player.capabilities.disableDamage = false;
						//player.cameraPitch = 0;
						//player.rotationPitch = 0;
					}
					playersWithFlight.remove(playerStr(player));
				}
			} else if(shouldPlayerHaveFlight(player)) { //Initial Flight Turns on here (Called once)
				playersWithFlight.add(playerStr(player));
				player.capabilities.allowFlying = true;
			}
		}
	}

	@SubscribeEvent
	public void playerLoggedOut(PlayerEvent.PlayerLoggedOutEvent event) {
		String username = event.player.getGameProfile().getName();
		XiaArmor.playersWithFlight.remove(username + ":false");
		XiaArmor.playersWithFlight.remove(username + ":true");
	}

	public static String playerStr(EntityPlayer player) {
		return player.getGameProfile().getName() + ":" + player.worldObj.isRemote;
	}

	private boolean shouldPlayerHaveFlight(EntityPlayer player) {
		ItemStack armor = player.getItemStackFromSlot(EntityEquipmentSlot.CHEST);
		return armor != null && armor.getItem() == this;
	}

}
