package Tamaized.Voidcraft.xiaCastle.logic.battle.ai;

import net.minecraft.util.math.BlockPos;
import Tamaized.Voidcraft.mobs.EntityVoidNPC;
import Tamaized.Voidcraft.mobs.entity.boss.EntityMobHerobrine;
import Tamaized.Voidcraft.xiaCastle.logic.battle.ai.handler.IHandlerAI;
import Tamaized.Voidcraft.xiaCastle.logic.battle.herobrine.phases.HerobrineAIPhase1;
import Tamaized.Voidcraft.xiaCastle.logic.battle.herobrine.phases.HerobrineAIPhase2;
import Tamaized.Voidcraft.xiaCastle.logic.battle.herobrine.phases.HerobrineAIPhase3;

public class EntityAIHandler {

	private EntityVoidNPC entity;
	private IHandlerAI ai;

	private BlockPos pos;

	public EntityAIHandler(EntityVoidNPC e, int x, int y, int z) {
		entity = e;
		pos = new BlockPos(x, y, z);
	}

	public void Init(int phase) {
		if (entity instanceof EntityMobHerobrine) {
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

	public EntityVoidNPC getEntity() {
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
