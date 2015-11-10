package Tamaized.Voidcraft.armor;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;

import org.lwjgl.opengl.GL11;

//import net.minecraftforge.event.entity.player.PlayerEvent;
import Tamaized.Voidcraft.common.voidCraft;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class XiaArmor extends ItemArmor {

	private String texturePath = voidCraft.modid + ":" + "textures/models/armor/";
	public static List<String> playersWithFlight = new ArrayList();
	
	public XiaArmor(ArmorMaterial armorMaterial, int par3, int par4, String type) {
		super(armorMaterial, par3, par4);
		
		this.setMaxStackSize(1);
		this.setCreativeTab(voidCraft.tabs.tabVoid);
		this.setTextureName(type, par4);
		
		MinecraftForge.EVENT_BUS.register(this);
		FMLCommonHandler.instance().bus().register(this);
		setHasSubtypes(true);

	}
	
	private void setTextureName(String type, int armorPart){
		
		if(armorType == 0 || armorType == 1 || armorType == 3){
			this.texturePath = voidCraft.modid + ":" + "textures/models/armor/" + type + "_layer_1.png";
		
		}else{
			this.texturePath = voidCraft.modid + ":" + "textures/models/armor/" + type + "_layer_2.png";
			}
		
	}
	
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister register){
		this.itemIcon = register.registerIcon(voidCraft.modid + ":" + this.getUnlocalizedName().substring(this.getUnlocalizedName().indexOf(".")+1));
	}
	
	@Override
	public String getArmorTexture(ItemStack armor, Entity entity, int slot, String type) {
		return this.texturePath;
	}
	
	//Thanks to Vazkii's Botania Mod Source for this part
	@SubscribeEvent
	public void updatePlayerFlyStatus(LivingUpdateEvent event) {
		if(event.entityLiving instanceof EntityPlayer) {
			EntityPlayer player = (EntityPlayer) event.entityLiving;

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
		ItemStack armor = player.getEquipmentInSlot(3);
		return armor != null && armor.getItem() == this;
	}

}
