package Tamaized.Voidcraft.items;

import io.netty.buffer.ByteBufOutputStream;
import io.netty.buffer.Unpooled;

import java.io.DataOutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.internal.FMLProxyPacket;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import Tamaized.Voidcraft.common.voidCraft;
import Tamaized.Voidcraft.common.handlers.VoidCraftServerPacketHandler;
import Tamaized.Voidcraft.items.entity.EntityHookShot;

public class HookShot extends Item{
	
	@SideOnly(Side.CLIENT)
	private IIcon cast;
	@SideOnly(Side.CLIENT)
	private IIcon uncast;
	
	public static Map<EntityPlayer, Boolean> handler = new HashMap<EntityPlayer, Boolean>(); //Keep this Server Side
	
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
    
    /**
     * Player, Render pass, and item usage sensitive version of getIconIndex.
     *
     * @param stack The item stack to get the icon for. (Usually this, and usingItem will be the same if usingItem is not null)
     * @param renderPass The pass to get the icon for, 0 is default.
     * @param player The player holding the item
     * @param usingItem The item the player is actively using. Can be null if not using anything.
     * @param useRemaining The ticks remaining for the active item.
     * @return The icon index
     */
    public IIcon getIcon(ItemStack stack, int renderPass, EntityPlayer player, ItemStack usingItem, int useRemaining)
    {
        return usingItem == null ? uncast : cast;
    }
	
	public ItemStack onItemRightClick(ItemStack itemstack, World world, EntityPlayer player){
		player.setItemInUse(itemstack, 20*4);
		if(world.isRemote) return itemstack;

		if(!HookShot.handler.containsKey(player)){
			voidCraft.logger.info("Adding player ("+player.getGameProfile().getName()+") to HookShot handler");
			HookShot.handler.put(player, false);
		}
		if(HookShot.handler.get(player)) return itemstack;
		EntityHookShot hook = new EntityHookShot(world, player, 1.6F);
		hook.setRangeAndSpeed(0.0F, 1.7D);
		world.spawnEntityInWorld(hook);
		HookShot.handler.put(player, true);
		return itemstack;	
	}
	
	public void onPlayerStoppedUsing(ItemStack itemstack, World world, EntityPlayer player, int count) { //Doesnt seem to be called server side so send a packet
		int pktType = VoidCraftServerPacketHandler.TYPE_HOOKSHOT_STOP;
		ByteBufOutputStream bos = new ByteBufOutputStream(Unpooled.buffer());
		DataOutputStream outputStream = new DataOutputStream(bos);
		try {
			outputStream.writeInt(pktType);
			FMLProxyPacket packet = new FMLProxyPacket(bos.buffer(), voidCraft.networkChannelName);
			voidCraft.channel.sendToServer(packet);
			outputStream.close();
			bos.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
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
