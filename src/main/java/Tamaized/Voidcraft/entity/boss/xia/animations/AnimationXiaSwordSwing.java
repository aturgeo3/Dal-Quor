package Tamaized.Voidcraft.entity.boss.xia.animations;

import net.minecraft.util.math.Vec3d;
import Tamaized.Voidcraft.entity.boss.xia.EntityBossXia;
import Tamaized.Voidcraft.entity.client.animation.IAnimation;

public class AnimationXiaSwordSwing implements IAnimation<EntityBossXia> {

	private int tick = 1;
	private int phase = 0;

	private Vec3d vecResult;
	private Vec3d vecOriginal;
	private float currRotation = 90;
	private double swordLength = 30;

	@Override
	public void init(Vec3d pos) {
		vecOriginal = new Vec3d(pos.xCoord, pos.yCoord, pos.zCoord);
		vecResult = new Vec3d(0, 0, 0);
		currRotation = 90;
		tick = 1;
		phase = 0;
	}

	@Override
	public boolean update(EntityBossXia xia) {
		currRotation--;
		xia.setArmRotations(0, 90, 0, currRotation, false);

		double xCoord = vecOriginal.xCoord + swordLength * -Math.cos(Math.toRadians(xia.rightArmYaw)) * Math.sin(Math.toRadians(90));
		double zCoord = vecOriginal.zCoord + swordLength * -Math.sin(Math.toRadians(xia.rightArmYaw)) * Math.sin(Math.toRadians(90));
		double yCoord = vecOriginal.yCoord + swordLength * Math.cos(Math.toRadians(xia.rightArmPitch));
		vecResult = new Vec3d(xCoord, yCoord, zCoord);

		if (currRotation <= -90) return true;
		return false;
	}

	@Override
	public Vec3d originalVector() {
		return vecOriginal;
	}

	@Override
	public Vec3d resultVector() {
		return vecResult;
	}

}
