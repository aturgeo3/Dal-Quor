package Tamaized.Voidcraft.entity.ghost;

import net.minecraft.world.World;
import Tamaized.Voidcraft.handlers.SkinHandler.PlayerNameAlias;

public class EntityGhostBiped extends EntityGhostPlayerBase {
	
	public EntityGhostBiped(World world){
		super(world);
	}

	protected EntityGhostBiped(World world, PlayerNameAlias alias) {
		super(world, alias);
	}

}