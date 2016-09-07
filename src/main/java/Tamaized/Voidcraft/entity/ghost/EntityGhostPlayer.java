package Tamaized.Voidcraft.entity.ghost;

import net.minecraft.world.World;
import Tamaized.Voidcraft.handlers.SkinHandler.PlayerNameAlias;

public class EntityGhostPlayer extends EntityGhostPlayerBase {
	
	public EntityGhostPlayer(World world){
		super(world);
	}

	protected EntityGhostPlayer(World world, PlayerNameAlias alias) {
		super(world, alias);
	}

}