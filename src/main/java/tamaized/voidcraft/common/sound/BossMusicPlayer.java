package tamaized.voidcraft.common.sound;

import tamaized.voidcraft.common.handlers.ConfigHandler;
import tamaized.voidcraft.client.sound.BGMusic;
import net.minecraft.client.audio.ISound;
import net.minecraft.entity.Entity;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.ClientTickEvent;

public class BossMusicPlayer {

	private static volatile ISound music = null;
	private static volatile Entity boss = null;

	private BossMusicPlayer() {
		// This is a static class
	}

	/**
	 * This is updated directly from {@link BGMusic#tick(ClientTickEvent)}
	 * 
	 * @return true if we are playing boss music
	 */
	public static boolean update(ISound sound) {
		if (music != null) {
			if (boss == null || !boss.isEntityAlive()) {
				StopMusic();
			} else if (sound != music) { // We need to loop
				BGMusic.PlayMusic(music);
			}
		} else {
			if (boss != null) boss = null;
		}
		return music != null && boss != null && boss.isEntityAlive();
	}

	public static void PlayMusic(Entity entity, SoundEvent sound) {
		if(!ConfigHandler.music) return;
		boss = entity;
		music = BGMusic.PlayMusic(sound);
	}

	public static void StopMusic() {
		boss = null;
		music = null;
		BGMusic.StopMusic();
	}

}
