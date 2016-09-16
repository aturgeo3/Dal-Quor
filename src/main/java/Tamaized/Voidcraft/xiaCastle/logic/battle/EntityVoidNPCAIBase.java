package Tamaized.Voidcraft.xiaCastle.logic.battle;

import java.util.ArrayList;

import net.minecraft.entity.Entity;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import Tamaized.Voidcraft.voidCraft;
import Tamaized.Voidcraft.entity.EntityVoidBoss;
import Tamaized.Voidcraft.network.VoidBossAIBus.Packet;


public abstract class EntityVoidNPCAIBase extends EntityAIBase {
	
	private boolean execute = false;

	private EntityVoidBoss entity;
	protected World world;
	
    /** The closest entity which is being watched by this one. */
    protected Entity closestEntity;
    /** This is the Maximum distance that the AI will look for the Entity */
    protected float maxDistanceForPlayer = 30;
    protected ArrayList<Class> watchedClass = new ArrayList<Class>();
    
	private double[] spawnLoc = new double[3];
	private BlockPos pos;

	protected int tick = 1;

	public EntityVoidNPCAIBase(EntityVoidBoss entityBoss, ArrayList<Class> c) {
		watchedClass = new ArrayList<Class>();
		watchedClass.addAll(c);
		entity = entityBoss;
		pos = entity.getPosition();
		world = entityBoss.worldObj;
	}

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
		
		execute = true;
	}

	/**
	 * DO NOT OVERRIDE THIS METHOD, use update() instead
	 */
	@Override
	public void updateTask(){
		if(!shouldExecute() || world.isRemote) return;
		update();
		tick++;
	}
	
	/**
	 * Use this method to deal with logic updates
	 */
	protected abstract void update();
	
	public abstract void doAction(BlockPos pos);
	
	public EntityVoidBoss getEntity(){
		return entity;
	}
	
	public BlockPos getPosition(){
		return pos;
	}
	
	public abstract void readPacket(Packet packet);

}
