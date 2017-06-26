package Tamaized.Voidcraft.client.sound;

import Tamaized.Voidcraft.common.handlers.ConfigHandler;
import Tamaized.Voidcraft.common.sound.BossMusicPlayer;
import Tamaized.Voidcraft.common.sound.VoidSoundEvents;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.ISound;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.minecraftforge.client.event.sound.PlaySoundEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.ClientTickEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.Phase;

public class BGMusic {

	private static final SoundEvent[] musicVoid = new SoundEvent[] {

			VoidSoundEvents.MusicSoundEvents.mcMusic_end,

			VoidSoundEvents.MusicSoundEvents.crystalcove,

			VoidSoundEvents.MusicSoundEvents.darkness

	};

	private static final SoundEvent[] musicDalQuor = new SoundEvent[] {

			VoidSoundEvents.MusicSoundEvents.mcMusic_end,

			VoidSoundEvents.MusicSoundEvents.fleshmaker,

			VoidSoundEvents.MusicSoundEvents.gop1,

			VoidSoundEvents.MusicSoundEvents.gop2,

			VoidSoundEvents.MusicSoundEvents.inferno

	};

	private static volatile ISound sound;

	@SubscribeEvent
	public void PlaySoundEvent(PlaySoundEvent e) {
		if(!ConfigHandler.music) return;
		if (sound != null && e.getSound().getCategory() == SoundCategory.MUSIC) {
			if (e.getResultSound() == sound) return;
			e.setResultSound(null);
			return;
		}
		World world = Minecraft.getMinecraft().world;
		EntityPlayerSP player = Minecraft.getMinecraft().player;
		if (e.getSound().getCategory() == SoundCategory.MUSIC && world != null && world.provider != null) {
			if (world.provider.getDimension() == ConfigHandler.dimensionIdVoid) {
				if (isPlaying(sound)) e.setResultSound(null);
				else e.setResultSound(sound = PositionedSoundRecord.getMusicRecord(musicVoid[world.rand.nextInt(musicVoid.length)]));
			} else if (world.provider.getDimension() == ConfigHandler.dimensionIdDalQuor) {
				if (isPlaying(sound)) e.setResultSound(null);
				else e.setResultSound(sound = PositionedSoundRecord.getMusicRecord(musicDalQuor[world.rand.nextInt(musicDalQuor.length)]));
			} else if (world.provider.getDimension() == ConfigHandler.dimensionIdXia) {
				if (isPlaying(sound)) e.setResultSound(null);
			}
		}
	}

	@SubscribeEvent
	public void tick(ClientTickEvent e) {
		if(!ConfigHandler.music) return;
		if (e.phase == Phase.END && !Minecraft.getMinecraft().isGamePaused()) {
			World world = Minecraft.getMinecraft().world;
			boolean boss = BossMusicPlayer.update(sound);
			if (sound != null) {
				if (world == null || world.provider == null) {
					StopMusic();
				} else if (!boss && isNotInDims(world.provider.getDimension())) {
					StopMusic();
				} else if (!isPlaying(sound)) {
					StopMusic();
				}
			} else if (world != null && world.provider != null) { // This is to loop music if needed while in a specific DIM
				if (!boss) { // Check if Boss Music isn't playing, if so then play our track
					if (world.provider.getDimension() == ConfigHandler.dimensionIdXia && !isPlaying(sound)) {
						PlayMusic(VoidSoundEvents.MusicSoundEvents.deathwyrm);
					}
				}
			}
		}
	}

	private static boolean isNotInDims(int dim) {
		return dim != ConfigHandler.dimensionIdXia && dim != ConfigHandler.dimensionIdVoid && dim != ConfigHandler.dimensionIdDalQuor;
	}

	public static boolean isPlaying(ISound sound) {
		return sound != null && Minecraft.getMinecraft().getSoundHandler().isSoundPlaying(sound);
	}

	public static ISound PlayMusic(SoundEvent track) {
		PlayMusic(PositionedSoundRecord.getMusicRecord(track));
		return sound;
	}

	public static void PlayMusic(ISound s) {
		if(!ConfigHandler.music) return;
		StopMusic();
		sound = s;
		Minecraft.getMinecraft().getSoundHandler().playSound(sound);
	}

	public static void StopMusic() {
		if (isPlaying(sound)) Minecraft.getMinecraft().getSoundHandler().stopSound(sound);
		sound = null;
		MusicTickerStopper.stop(0);
	}

}
