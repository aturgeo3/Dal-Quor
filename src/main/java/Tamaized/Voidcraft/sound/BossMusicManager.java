package Tamaized.Voidcraft.sound;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import Tamaized.Voidcraft.items.VoidRecord;
import Tamaized.Voidcraft.mobs.EntityVoidNPC;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent.Phase;
import cpw.mods.fml.common.gameevent.TickEvent.ServerTickEvent;
import cpw.mods.fml.relauncher.Side;

public class BossMusicManager {
	
	public static BossMusicManager instance = new BossMusicManager();
	
	public static boolean isPlaying = false;
	public static World worldObj;
	private static EntityVoidNPC boss;
	public static int[] location = {0, 0, 0};
	public static ItemStack item;
	public static boolean loop = false;
	public static int tick = 0;
	
	@SubscribeEvent
	public void update(ServerTickEvent e){
		if(!isPlaying || e.side == Side.CLIENT || e.phase != Phase.END) return;
		if(boss.isDead) boss = null;
		if(isPlaying && boss == null) {
			StopTheSound();
			return;
		}
		if(tick > 0) tick--;
		if(loop && tick <= 0) PlayTheSound(worldObj, boss, item, location, loop);	
		
	}
	
	/**
	 *  Play the Record  
     */
	public static void PlayTheSound(World world, EntityVoidNPC b, ItemStack itemStack, int[] loc, boolean l){
		if(itemStack != null){
			if(isPlaying) StopTheSound();
			worldObj = world;
			boss = b;
			location = loc;
			loop = l;
			item = itemStack;
			if(loop && itemStack.getItem() instanceof VoidRecord) tick = (((VoidRecord) itemStack.getItem()).getTime()*20)-1;
			worldObj.playAuxSFXAtEntity((EntityPlayer)null, 1005, location[0], location[1], location[2], Item.getIdFromItem(itemStack.getItem()));
			isPlaying = true;
		}else{
			System.out.println("NULL SLOT DETECTED");
			loop = false;
			isPlaying = false;
		}
	}
	
	/**
	 *  Stop the Record
     */
	public static void StopTheSound(){
		if(!isPlaying) return;
		worldObj.playAuxSFXAtEntity((EntityPlayer)null, 1005, location[0], location[1], location[2], 0);
		isPlaying = false;
		boss = null;
	}

}
