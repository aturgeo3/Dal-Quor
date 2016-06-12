package Tamaized.Voidcraft.sound;

import net.minecraft.init.SoundEvents;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;

public class VoidSoundEvents {
	
	//new SoundEvent(new ResourceLocation(""))
	
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
		public static final SoundEvent hurtSound = new SoundEvent(new ResourceLocation("voidcraft:wraith.wraithHurt"));
		public static final SoundEvent deathSound = new SoundEvent(new ResourceLocation("voidcraft:wraith.wraithDeath"));
		public static final SoundEvent ambientSound = new SoundEvent(new ResourceLocation("voidcraft:wraith.wraithLive"));
	}
	
	public static class EntityMobLichSoundEvents{
		public static final SoundEvent hurtSound = new SoundEvent(new ResourceLocation("voidcraft:wraith.wraithHurt"));
		public static final SoundEvent deathSound = new SoundEvent(new ResourceLocation("voidcraft:wraith.lichDeath"));
		public static final SoundEvent ambientSound = new SoundEvent(new ResourceLocation("voidcraft:wraith.lichLive"));
	}

}
