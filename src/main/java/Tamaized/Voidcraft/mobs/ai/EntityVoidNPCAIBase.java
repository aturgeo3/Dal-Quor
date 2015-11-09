package Tamaized.Voidcraft.mobs.ai;

import Tamaized.Voidcraft.mobs.EntityVoidNPC;
import net.minecraft.entity.ai.EntityAIBase;

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
