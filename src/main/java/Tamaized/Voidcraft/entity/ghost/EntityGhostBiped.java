package Tamaized.Voidcraft.entity.ghost;

import Tamaized.Voidcraft.handlers.SkinHandler.PlayerNameAlias;
import net.minecraft.world.World;

public class EntityGhostBiped extends EntityGhostPlayerBase {

	public EntityGhostBiped(World world) {
		super(world);
	}

	public EntityGhostBiped(World world, PlayerNameAlias alias, boolean interactable) {
		super(world, alias, interactable);
	}

}