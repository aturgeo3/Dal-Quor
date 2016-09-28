package Tamaized.Voidcraft.entity.boss.xia.render;

import net.minecraft.util.math.Vec3d;
import Tamaized.Voidcraft.entity.boss.xia.EntityBossXia;
import Tamaized.Voidcraft.entity.boss.xia.animations.AnimationXiaSwordSwing;
import Tamaized.Voidcraft.entity.client.animation.IAnimatable;
import Tamaized.Voidcraft.entity.client.animation.IAnimation;

public class EntityAnimationsXia implements IAnimatable {

	private final EntityBossXia xia;
	private final IAnimation currAnimation;

	public static enum Animation {
		SWORD_SWING
	}

	public static int getAnimationID(Animation animation) {
		return animation.ordinal();
	}

	public static Animation getAnimationFromID(int id) {
		return Animation.values()[id];
	}

	public EntityAnimationsXia(EntityBossXia xia, Animation animation) {
		this.xia = xia;
		switch (animation) {
			case SWORD_SWING:
				currAnimation = new AnimationXiaSwordSwing();
				break;
			default:
				currAnimation = null;
				break;
		}
		if (currAnimation != null) currAnimation.init(new Vec3d(xia.posX, xia.posZ, xia.posZ));
	}

	/**
	 * returns true when finished
	 */
	@Override
	public boolean update() {
		return currAnimation == null ? true : currAnimation.update(xia);
	}

}
