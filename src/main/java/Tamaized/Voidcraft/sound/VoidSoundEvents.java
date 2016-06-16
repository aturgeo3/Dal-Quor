package Tamaized.Voidcraft.sound;

import Tamaized.Voidcraft.common.voidCraft;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class VoidSoundEvents {
	
	//new SoundEvent(new ResourceLocation(voidCraft.modid+":"))
	
	public static class SoundTrack{
		private int length;
		private SoundEvent track;
		
		public SoundTrack(SoundEvent t, int l){
			length = l;
			track = t;
		}
		
		public SoundEvent getTrack(){
			return track;
		}
		
		public int getLength(){
			return length;
		}
	}
	
	public static class MiscSoundEvents{
		public static SoundEvent chain = null;
	}
	
	public static class ArmorSoundEvents{
		public static SoundEvent voidcrystal = SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND;
		public static SoundEvent demon = SoundEvents.ITEM_ARMOR_EQUIP_GOLD;
		public static SoundEvent xia = SoundEvents.ITEM_ARMOR_EQUIP_CHAIN;
	}
	
	public static class MusicDiscSoundEvents{
		public static SoundTrack testDisc = null;
	}
	
	public static class BGMusicSoundEvents{
		public static SoundTrack undertale_core = null;
		public static SoundTrack emile_wish = null;
		public static SoundTrack emile_nether = null;
		public static SoundTrack mcMusic_end = new SoundTrack(SoundEvents.MUSIC_END, 10);
	}
	
	public static class EntityMobDolSoundEvents{
		public static SoundEvent hurtSound = null;
		public static SoundEvent deathSound = null;
		public static SoundEvent ambientSound = null;
	}
	
	public static class EntityMobZolSoundEvents{
		public static SoundEvent hurtSound = null;
		public static SoundEvent deathSound = null;
		public static SoundEvent ambientSound = null;
	}
	
	public static class EntityMobHerobrineSoundEvents{
		public static SoundEvent hurtSound = null;
		public static SoundEvent deathSound = null;
		public static SoundEvent ambientSound = null;
	}
	
	public static class EntityMobVoidBossSoundEvents{
		public static SoundEvent hurtSound = SoundEvents.ENTITY_WITHER_HURT;
		public static SoundEvent deathSound = SoundEvents.ENTITY_WITHER_DEATH;
		public static SoundEvent ambientSound = SoundEvents.ENTITY_WITHER_AMBIENT;
	}
	
	public static class EntityMobWraithSoundEvents{
		public static SoundEvent hurtSound = null;
		public static SoundEvent deathSound = null;
		public static SoundEvent ambientSound = null;
	}
	
	public static class EntityMobLichSoundEvents{
		public static SoundEvent hurtSound = null;
		public static SoundEvent deathSound = null;
		public static SoundEvent ambientSound = null;
	}
	
	public static class EntityMobSpectreChainSoundEvents{
		public static SoundEvent hurtSound = null;
		public static SoundEvent deathSound = null;
		public static SoundEvent ambientSound = null;
	}
	
	public static class EntityMobWrathSoundEvents{
		public static SoundEvent hurtSound = null;
		public static SoundEvent deathSound = null;
		public static SoundEvent ambientSound = null;
	}
	
	public static class EntityMobXiaSoundEvents{
		public static SoundEvent hurtSound = null;
		public static SoundEvent deathSound = null;
		public static SoundEvent ambientSound = null;
	}
	
	public static class EntityMobXia2SoundEvents{
		public static SoundEvent hurtSound = null;
		public static SoundEvent deathSound = null;
		public static SoundEvent ambientSound = null;
	}
	
	public static void register(){
		EntityMobWraithSoundEvents.hurtSound = registerSound("wraith.wraithHurt");
		EntityMobWraithSoundEvents.deathSound = registerSound("wraith.wraithDeath");
		EntityMobWraithSoundEvents.ambientSound = registerSound("wraith.wraithLive");
		
		EntityMobLichSoundEvents.hurtSound = EntityMobWraithSoundEvents.hurtSound;
		EntityMobLichSoundEvents.deathSound = registerSound("lich.lichDeath");
		EntityMobLichSoundEvents.ambientSound = registerSound("lich.lichLive");
		
		EntityMobSpectreChainSoundEvents.hurtSound = EntityMobWraithSoundEvents.hurtSound;
		EntityMobSpectreChainSoundEvents.deathSound = EntityMobWraithSoundEvents.deathSound;
		EntityMobSpectreChainSoundEvents.ambientSound = registerSound("spectrechain.chainLive");

		EntityMobWrathSoundEvents.hurtSound = registerSound("wrath.hit");
		EntityMobWrathSoundEvents.deathSound = registerSound("wrath.death");
		EntityMobWrathSoundEvents.ambientSound = registerSound("wrath.breathe");
		
		MiscSoundEvents.chain = registerSound("random.chain");
		
		MusicDiscSoundEvents.testDisc = new SoundTrack(registerSound("record.undertale"), 142);
		
		BGMusicSoundEvents.undertale_core = new SoundTrack(registerSound("music.undertale"), 142);
		BGMusicSoundEvents.emile_wish = new SoundTrack(registerSound("music.emile.wish"), 142);
		BGMusicSoundEvents.emile_nether = new SoundTrack(registerSound("music.emile.nether"), 142);
	}
	
	private static SoundEvent registerSound(String soundName) {
		ResourceLocation soundID = new ResourceLocation(voidCraft.modid, soundName);
		return GameRegistry.register(new SoundEvent(soundID).setRegistryName(soundID));
	}
	
}
