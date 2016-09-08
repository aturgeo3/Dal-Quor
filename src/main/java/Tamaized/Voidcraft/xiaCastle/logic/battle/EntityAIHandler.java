package Tamaized.Voidcraft.xiaCastle.logic.battle;

import net.minecraft.util.math.BlockPos;
import Tamaized.Voidcraft.entity.EntityVoidBoss;
import Tamaized.Voidcraft.entity.boss.herobrine.EntityBossHerobrine;
import Tamaized.Voidcraft.xiaCastle.logic.battle.herobrine.phases.HerobrineAIPhase1;
import Tamaized.Voidcraft.xiaCastle.logic.battle.herobrine.phases.HerobrineAIPhase2;
import Tamaized.Voidcraft.xiaCastle.logic.battle.herobrine.phases.HerobrineAIPhase3;

public class EntityAIHandler {

	private EntityVoidBoss entity;
	private IHandlerAI ai;

	private BlockPos pos;

	public EntityAIHandler(EntityVoidBoss e, int x, int y, int z) {
		entity = e;
		pos = new BlockPos(x, y, z);
	}

	public void Init(int phase) {
		if (entity instanceof EntityBossHerobrine) {
			ai = phase == 1 ? new HerobrineAIPhase1(this) : phase == 2 ? new HerobrineAIPhase2(this) : phase == 3 ? new HerobrineAIPhase3(this) : null;
		}
		if (ai != null) ai.Init();
	}

	public void kill() {
		ai.kill();
	}

	public void update() {
		ai.update();
	}

	public EntityVoidBoss getEntity() {
		return entity;
	}

	public BlockPos getPos() {
		return pos;
	}

	public int getX() {
		return pos.getX();
	}

	public int getY() {
		return pos.getY();
	}

	public int getZ() {
		return pos.getZ();
	}

}
