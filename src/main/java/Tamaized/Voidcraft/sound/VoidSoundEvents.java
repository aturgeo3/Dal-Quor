package Tamaized.Voidcraft.sound;

import Tamaized.Voidcraft.common.voidCraft;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;

public class VoidSoundEvents {
	
	//new SoundEvent(new ResourceLocation(voidCraft.modid+":"))
	
	public static class MiscSoundEvents{
		public static final SoundEvent chain = new SoundEvent(new ResourceLocation(voidCraft.modid+":random.chain"));
	}
	
	public static class ArmorSoundEvents{
		public static final SoundEvent voidcrystal = SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND;
		public static final SoundEvent demon = SoundEvents.ITEM_ARMOR_EQUIP_GOLD;
		public static final SoundEvent xia = SoundEvents.ITEM_ARMOR_EQUIP_CHAIN;
	}
	
	public static class EntityMobDolSoundEvents{
		public static final SoundEvent hurtSound = null;
		public static final SoundEvent deathSound = null;
		public static final SoundEvent ambientSound = null;
	}
	
	public static class EntityMobZolSoundEvents{
		public static final SoundEvent hurtSound = null;
		public static final SoundEvent deathSound = null;
		public static final SoundEvent ambientSound = null;
	}
	
	public static class EntityMobHerobrineSoundEvents{
		public static final SoundEvent hurtSound = null;
		public static final SoundEvent deathSound = null;
		public static final SoundEvent ambientSound = null;
	}
	
	public static class EntityMobVoidBossSoundEvents{
		public static final SoundEvent hurtSound = SoundEvents.ENTITY_WITHER_HURT;
		public static final SoundEvent deathSound = SoundEvents.ENTITY_WITHER_DEATH;
		public static final SoundEvent ambientSound = SoundEvents.ENTITY_WITHER_AMBIENT;
	}
	
	public static class EntityMobWraithSoundEvents{
		public static final SoundEvent hurtSound = new SoundEvent(new ResourceLocation(voidCraft.modid+":wraith.wraithHurt"));
		public static final SoundEvent deathSound = new SoundEvent(new ResourceLocation(voidCraft.modid+":wraith.wraithDeath"));
		public static final SoundEvent ambientSound = new SoundEvent(new ResourceLocation(voidCraft.modid+":wraith.wraithLive"));
	}
	
	public static class EntityMobLichSoundEvents{
		public static final SoundEvent hurtSound = new SoundEvent(new ResourceLocation(voidCraft.modid+":wraith.wraithHurt"));
		public static final SoundEvent deathSound = new SoundEvent(new ResourceLocation(voidCraft.modid+":lich.lichDeath"));
		public static final SoundEvent ambientSound = new SoundEvent(new ResourceLocation(voidCraft.modid+":lich.lichLive"));
	}
	
	public static class EntityMobSpectreChainSoundEvents{
		public static final SoundEvent hurtSound = new SoundEvent(new ResourceLocation(voidCraft.modid+":wraith.wraithHurt"));
		public static final SoundEvent deathSound = new SoundEvent(new ResourceLocation(voidCraft.modid+":wraith.wraithDeath"));
		public static final SoundEvent ambientSound = new SoundEvent(new ResourceLocation(voidCraft.modid+":spectrechain.chainLive"));
	}
	
	public static class EntityMobWrathSoundEvents{
		public static final SoundEvent hurtSound = new SoundEvent(new ResourceLocation(voidCraft.modid+":voidwrath.hit"));
		public static final SoundEvent deathSound = new SoundEvent(new ResourceLocation(voidCraft.modid+":voidwrath.death"));
		public static final SoundEvent ambientSound = new SoundEvent(new ResourceLocation(voidCraft.modid+":voidwrath.breathe1"));
	}
	
	public static class EntityMobXiaSoundEvents{
		public static final SoundEvent hurtSound = null;
		public static final SoundEvent deathSound = null;
		public static final SoundEvent ambientSound = null;
	}
	
	public static class EntityMobXia2SoundEvents{
		public static final SoundEvent hurtSound = null;
		public static final SoundEvent deathSound = null;
		public static final SoundEvent ambientSound = null;
	}

}
