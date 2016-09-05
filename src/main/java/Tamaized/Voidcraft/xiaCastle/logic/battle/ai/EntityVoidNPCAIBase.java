package Tamaized.Voidcraft.xiaCastle.logic.battle.ai;

import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import Tamaized.Voidcraft.mobs.EntityVoidNPC;

public class EntityVoidNPCAIBase extends EntityAIBase{
	
	public EntityVoidNPC entity;
	public boolean execute = false;
	
	private double[] spawnLoc = new double[3];

	@Override
	public boolean shouldExecute() {
		return (execute && entity!=null);
	}

	public void kill() {
		execute = false;
		entity.posX = spawnLoc[0];
		entity.posY = spawnLoc[1];
		entity.posZ = spawnLoc[2];
		entity = null;
	}

	public void Init() {
		spawnLoc[0] = entity.posX;
		spawnLoc[1] = entity.posY;
		spawnLoc[2] = entity.posZ;
	}

}
