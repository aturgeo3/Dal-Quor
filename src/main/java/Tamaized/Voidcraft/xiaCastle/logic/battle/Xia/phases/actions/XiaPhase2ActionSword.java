package Tamaized.Voidcraft.xiaCastle.logic.battle.Xia.phases.actions;

import net.minecraft.util.math.Vec3d;
import Tamaized.Voidcraft.entity.boss.xia.EntityBossXia;
import Tamaized.Voidcraft.entity.boss.xia.EntityBossXia.Action;

public class XiaPhase2ActionSword {

	private final EntityBossXia xia;
	private final Vec3d throne;

	private int tick = 1;
	private int actionTick = (int) (20f * 1.5f);
	private boolean isActing = false;
	private boolean done = true;
	private int phase = 0;

	private Vec3d vecResult;
	private Vec3d vecOriginal;
	private float currRotation = 90;
	private double swordLength = 30;

	public XiaPhase2ActionSword(EntityBossXia entity, Vec3d pos) {
		xia = entity;
		throne = pos;
	}

	public void init() {
		System.out.println("debug: init");
		xia.setPosition(throne.xCoord, throne.yCoord, throne.zCoord);
		currRotation = 90;
		xia.setArmRotations(0, 90, 0, currRotation);
		tick = 1;
		phase = 0;
		isActing = false;
		done = false;
		vecOriginal = new Vec3d(throne.xCoord, throne.yCoord, throne.zCoord);
		vecResult = new Vec3d(0, 0, 0);
	}

	public void update() {
		if (done) return;
		if (!isActing) {
			xia.setArmRotations(0, 0, 0, 0); //TODO: dont send this all the time instead send a packet for clients to update this themselves
			if (tick % actionTick == 0) {
				phase++;
				isActing = true;
				tick = 1;
			}
		} else {
			switch (phase) {
				case 1:
					currRotation--;
					xia.setArmRotations(0, 90, 0, currRotation);
					if (currRotation <= -90) {
						finish();
						isActing = false;
						tick = 1;
					}
					break;
				default:
					break;
			}
		}
		double xCoord = vecOriginal.xCoord + swordLength * -Math.cos(Math.toRadians(currRotation)) * Math.sin(Math.toRadians(90));
		double zCoord = vecOriginal.zCoord + swordLength * -Math.sin(Math.toRadians(currRotation)) * Math.sin(Math.toRadians(90));
		double yCoord = vecOriginal.yCoord + swordLength * Math.cos(Math.toRadians(90));
		vecResult = new Vec3d(xCoord, yCoord, zCoord);
		xia.setAction(Action.SWORD_PROJECTION_RIGHT);
		tick++;
	}
	
	private void finish(){
		xia.setAction(Action.IDLE);
		xia.setArmRotations(0, 0, 0, 0);
		done = true;
	}

	public Vec3d getVector() {
		return vecResult;
	}

	public boolean isDone() {
		return done;
	}

}
