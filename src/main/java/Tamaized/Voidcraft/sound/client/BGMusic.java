package Tamaized.Voidcraft.sound.client;

import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.ISound;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.client.audio.SoundCategory;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.client.gui.GuiIngameMenu;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.client.event.sound.PlaySoundEvent17;
import Tamaized.Voidcraft.common.voidCraft;
import Tamaized.Voidcraft.sound.BossMusicManager;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent.ClientTickEvent;
import cpw.mods.fml.common.gameevent.TickEvent.Phase;

public class BGMusic {

	private static ISound sound;
	public static boolean isPlaying = false;
	public static int tick = 0;
	public static boolean pause = false;
	
	@SubscribeEvent
	public void PlaySoundEvent(PlaySoundEvent17 e){
		World world = Minecraft.getMinecraft().theWorld;
		EntityClientPlayerMP player = Minecraft.getMinecraft().thePlayer;
		if(e.category == SoundCategory.MUSIC && world != null && world.provider != null && world.provider.dimensionId == voidCraft.dimensionIdXia){
			if(!e.name.equals("music.undertale")) e.result = null;
		}
	}
	
	@SubscribeEvent
	public void tick(ClientTickEvent e){
		if(e.phase == Phase.END){
			World world = Minecraft.getMinecraft().theWorld;
			EntityClientPlayerMP player = Minecraft.getMinecraft().thePlayer;
			pause = (Minecraft.getMinecraft().currentScreen instanceof GuiIngameMenu);
			if(world != null && world.provider != null && world.provider.dimensionId == voidCraft.dimensionIdXia){
				if(BossMusicManager.isPlaying){
					if(isPlaying) StopMusic();
					tick = 0;
				}else{
					if(tick > 0 && !pause) tick--;
					if(tick <= 0) PlayMusic();
				}
			}else{
				if(isPlaying) StopMusic();
				tick = 0;
			}
			//if(tick != 0 && tick % 20 == 0) System.out.println(tick);
		}
	}
	
	private void PlayMusic(){
		StopMusic();
		isPlaying = true;
		tick = (142*20)-1;
		ResourceLocation rl = new ResourceLocation(voidCraft.modid+":music.undertale");
		sound = PositionedSoundRecord.func_147674_a(rl, 1.0F);
		Minecraft.getMinecraft().getSoundHandler().playSound(sound);
	}
	
	private void StopMusic(){
		if(Minecraft.getMinecraft().getSoundHandler().isSoundPlaying(sound)) Minecraft.getMinecraft().getSoundHandler().stopSound(sound);
		isPlaying = false;
	}

}
