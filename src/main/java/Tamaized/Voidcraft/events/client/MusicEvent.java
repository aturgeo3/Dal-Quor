package Tamaized.Voidcraft.events.client;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Random;

import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.ISound;
import net.minecraft.client.audio.MusicTicker;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.sound.PlayStreamingSourceEvent;
import Tamaized.Voidcraft.common.voidCraft;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;

public class MusicEvent {
	
	private ISound daSound = null;
	private final ISound wish = PositionedSoundRecord.func_147673_a(new ResourceLocation("VoidCraft:music.wish"));
	private final ISound nether = PositionedSoundRecord.func_147673_a(new ResourceLocation("VoidCraft:music.nether"));
	private int playTicker = 1;
	
	private final Minecraft mc = Minecraft.getMinecraft();
	private static ArrayList<ISound> soundToStop = new ArrayList();
	
	//@SubscribeEvent
	public void InterceptBGMusic(PlayStreamingSourceEvent e){
		EntityClientPlayerMP player = mc.thePlayer;
		
		if(player != null && player.worldObj != null && player.worldObj.provider.dimensionId == voidCraft.dimensionIdVoid){
			if(daSound != null && e.sound.getPositionedSoundLocation() == daSound.getPositionedSoundLocation()){
				if(!mc.getSoundHandler().isSoundPlaying(wish) && !mc.getSoundHandler().isSoundPlaying(nether)){
					if(playTicker <= 0){
						Random randKey = new Random();
						int rand = randKey.nextInt(2);
						if(rand == 0) mc.getSoundHandler().playSound(wish);
						else mc.getSoundHandler().playSound(nether);
						playTicker = 2;
					}else{
						playTicker--;
					}
					
				}
			}
		}
	}
	
	//@SubscribeEvent
	public void ClientTickEvent(TickEvent.ClientTickEvent e){
		//Stop Vanilla music while in VoidCraft DIM
		try{
			EntityClientPlayerMP player = mc.thePlayer;
			if(player != null && player.worldObj != null && player.worldObj.provider.dimensionId == voidCraft.dimensionIdVoid){
				Field musicticker = Minecraft.class.getDeclaredField("mcMusicTicker");
				musicticker.setAccessible(true);
				MusicTicker daTicker = (MusicTicker) musicticker.get(mc);
			
				Field field = MusicTicker.class.getDeclaredField("field_147678_c");
				field.setAccessible(true);
				daSound = (ISound) field.get(daTicker);
				
				mc.getSoundHandler().stopSound(daSound);
			}
			
		}catch (NoSuchFieldException e1) {
			e1.printStackTrace();
		} catch (SecurityException e1) {
			e1.printStackTrace();
		} catch (IllegalArgumentException e1) {
			e1.printStackTrace();
		} catch (IllegalAccessException e1) {
			e1.printStackTrace();
		}
	}
}
