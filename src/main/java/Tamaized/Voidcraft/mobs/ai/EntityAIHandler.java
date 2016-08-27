package Tamaized.Voidcraft.mobs.ai;

import net.minecraft.util.math.BlockPos;
import Tamaized.Voidcraft.mobs.EntityVoidNPC;
import Tamaized.Voidcraft.mobs.ai.handler.IHandlerAI;
import Tamaized.Voidcraft.mobs.ai.handler.Herobrine.HerobrineAIPhase1;
import Tamaized.Voidcraft.mobs.ai.handler.Herobrine.HerobrineAIPhase2;
import Tamaized.Voidcraft.mobs.entity.boss.EntityMobHerobrine;

public class EntityAIHandler {
	
	private EntityVoidNPC entity;
	private IHandlerAI ai;
	
	private BlockPos pos;
	
	public EntityAIHandler(EntityVoidNPC e, int x, int y, int z){
		entity = e;
		pos = new BlockPos(x, y, z);
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
	
	public BlockPos getPos(){
		return pos;
	}
	
	public int getX(){
		return pos.getX();
	}
	
	public int getY(){
		return pos.getY();
	}
	
	public int getZ(){
		return pos.getZ();
	}

}
