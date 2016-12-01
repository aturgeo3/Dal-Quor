package Tamaized.Voidcraft.entity.ghost;

import Tamaized.Voidcraft.handlers.SkinHandler.PlayerNameAlias;
import net.minecraft.world.World;

public class EntityGhostPlayer extends EntityGhostPlayerBase {

	public EntityGhostPlayer(World par1World) {
		super(par1World);
	}

	public EntityGhostPlayer(World world, PlayerNameAlias alias, boolean interactable) {
		super(world, alias, interactable);
	}

}