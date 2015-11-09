package Tamaized.Voidcraft.mobs.ai;

import Tamaized.Voidcraft.mobs.EntityVoidNPC;
import Tamaized.Voidcraft.mobs.ai.handler.IHandlerAI;
import Tamaized.Voidcraft.mobs.ai.handler.Herobrine.HerobrineAIPhase1;
import Tamaized.Voidcraft.mobs.ai.handler.Herobrine.HerobrineAIPhase2;
import Tamaized.Voidcraft.mobs.entity.boss.EntityMobHerobrine;

public class EntityAIHandler {
	
	private EntityVoidNPC entity;
	private IHandlerAI ai;
	
	private int x;
	private int y;
	private int z;
	
	public EntityAIHandler(EntityVoidNPC e, int par1, int par2, int par3){
		entity = e;
		x = par1;
		y = par2;
		z = par3;
	}
	
	public void Init(int phase){
		if(entity instanceof EntityMobHerobrine){
			ai =  phase==1 ? new HerobrineAIPhase1(this) : phase==2 ? new HerobrineAIPhase2(this) : null;
		}
		if(ai != null) ai.Init();
	}
	
	public void kill(){
		ai.kill();
	}
	
	public void update(){
		ai.update();
	}
	
	public EntityVoidNPC getEntity(){
		return entity;
	}
	
	public int getX(){
		return x;
	}
	
	public int getY(){
		return y;
	}
	
	public int getZ(){
		return z;
	}

}
