package Tamaized.Voidcraft.xiaCastle.logic.battle.Xia.phases;

import java.util.ArrayList;
import java.util.Random;

import net.minecraft.entity.Entity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import Tamaized.Voidcraft.entity.boss.xia.EntityBossXia;
import Tamaized.Voidcraft.network.IVoidBossAIPacket;
import Tamaized.Voidcraft.xiaCastle.logic.battle.EntityVoidNPCAIBase;
import Tamaized.Voidcraft.xiaCastle.logic.battle.Xia.phases.actions.XiaPhase2ActionSword;

public class EntityAIXiaPhase2<T extends EntityBossXia> extends EntityVoidNPCAIBase<T> {

	private int actionTick = 20 * 10;

	private XiaPhase2ActionSword actionSword;

	private Action currAction = Action.IDLE;

	public static enum Action {
		IDLE, SWORD, FOLLOW
	}

	public EntityAIXiaPhase2(T entityBoss, ArrayList<Class> c) {
		super(entityBoss, c);
		actionSword = new XiaPhase2ActionSword(entityBoss, getPosition());
		watchNew();
	}

	@Override
	protected void updateClosest() {
	}

	@Override
	protected void update() {
		switch (currAction) {
			case FOLLOW:
			case IDLE:
				if (tick % actionTick == 0) {
					switch (world.rand.nextInt(1)) { // TODO: add more actions
						case 0:
							beginSwordAction();
							break;
						case 1:
							watchNewAndTeleport();
							break;
						default:
							watchNewAndTeleport();
							break;
					}
				}
				break;
			case SWORD:
				actionSword.update();
				if(actionSword.isDone()) finishAction();
				break;
			default:
				break;
		}
	}

	@Override
	public void doAction(BlockPos pos) {

	}

	@Override
	public void readPacket(IVoidBossAIPacket packet) {

	}
	
	private void finishAction(){
		currAction = Action.IDLE;
		tick = 1;
	}

	private void beginSwordAction() {
		actionSword.init();
		currAction = Action.SWORD;
	}

	private void watchNewAndTeleport() {
		watchNew();
		Vec3d vecA = new Vec3d(getEntity().posX, getEntity().posY, getEntity().posZ);
		Vec3d vecB = new Vec3d(closestEntity.posX, closestEntity.posY, closestEntity.posZ);

		float dist = 0.60f;

		double newPointX = vecA.xCoord + ((vecB.xCoord - vecA.xCoord) * dist);
		double newPointY = vecA.yCoord + ((vecB.yCoord - vecA.yCoord) * dist);
		double newPointZ = vecA.zCoord + ((vecB.zCoord - vecA.zCoord) * dist);

		getEntity().setPosition(newPointX, newPointY, newPointZ);
		currAction = Action.FOLLOW;
	}

	private void watchNew() {
		ArrayList<Entity> list = new ArrayList<Entity>();
		for (Class c : watchedClass) {
			list.addAll(getEntity().worldObj.getEntitiesWithinAABB(c, getEntity().getEntityBoundingBox().expand((double) maxDistanceForPlayer, 30.0D, (double) maxDistanceForPlayer)));
		}
		Random rand = world.rand;
		closestEntity = list.size() > 0 ? list.get(rand.nextInt(list.size())) : null;
	}

}
