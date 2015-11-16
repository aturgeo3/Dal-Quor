package Tamaized.Voidcraft.items;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import Tamaized.Voidcraft.common.voidCraft;
import Tamaized.Voidcraft.items.entity.EntityHookShot;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class HookShot extends Item{
	
	@SideOnly(Side.CLIENT)
	private IIcon cast;
	@SideOnly(Side.CLIENT)
	private IIcon uncast;
	
	public static Map<UUID, Boolean> handler = new HashMap<UUID, Boolean>();
	
	/**
     * Returns True is the item is renderer in full 3D when hold.
     */
    @SideOnly(Side.CLIENT)
    public boolean isFull3D(){
        return true;
    }
    
    @SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister iconRegister){
		cast = iconRegister.registerIcon("voidCraft:HookShot_cast");
		uncast = iconRegister.registerIcon("voidCraft:HookShot_uncast");
	}
    
    public IIcon getIcon(ItemStack stack, int renderPass, EntityPlayer player, ItemStack usingItem, int useRemaining){
        return HookShot.handler.containsKey(player.getGameProfile().getId()) ? HookShot.handler.get(player.getGameProfile().getId()) ? cast : uncast : uncast;
    }
	
	public ItemStack onItemRightClick(ItemStack itemstack, World world, EntityPlayer player){
		if(!HookShot.handler.containsKey(player.getGameProfile().getId())){
			voidCraft.logger.info("Adding UUID "+player.getGameProfile().getId()+" ("+player.getGameProfile().getName()+") to HookShot handler");
			HookShot.handler.put(player.getGameProfile().getId(), false);
		}
		player.setItemInUse(itemstack, 1000);
		if(HookShot.handler.get(player.getGameProfile().getId()) || world.isRemote) return itemstack;
		EntityHookShot hook = new EntityHookShot(world, player, 1.6F);
		hook.setRangeAndSpeed(0.0F, 1.7D);
		world.spawnEntityInWorld(hook);
		HookShot.handler.put(player.getGameProfile().getId(), true);
		return itemstack;	
	}
	
	public void onPlayerStoppedUsing(ItemStack itemstack, World world, EntityPlayer player, int count) {
		HookShot.handler.put(player.getGameProfile().getId(), false);
	}
	
	public boolean onItemUse(ItemStack p_77648_1_, EntityPlayer p_77648_2_, World p_77648_3_, int p_77648_4_, int p_77648_5_, int p_77648_6_, int p_77648_7_, float p_77648_8_, float p_77648_9_, float p_77648_10_){
        return true;
    }
	
	@SideOnly(Side.CLIENT)
    public boolean shouldRotateAroundWhenRendering(){
        return true;
    }
	
	public EnumAction getItemUseAction(ItemStack p_77661_1_){
        return EnumAction.bow;
    }
	
	@SideOnly(Side.CLIENT)
    public void addInformation(ItemStack p_77624_1_, EntityPlayer p_77624_2_, List p_77624_3_, boolean p_77624_4_) {
		p_77624_3_.add(EnumChatFormatting.AQUA + "Hold down right click in the Air to fire!");
	}

}
