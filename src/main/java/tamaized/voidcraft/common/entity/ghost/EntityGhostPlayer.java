package tamaized.voidcraft.common.entity.ghost;

import net.minecraft.entity.Entity;
import net.minecraft.world.World;

import java.util.UUID;

public class EntityGhostPlayer extends EntityGhostPlayerBase {

	public EntityGhostPlayer(World par1World) {
		super(par1World);
	}

	protected EntityGhostPlayer(World world, UUID id, boolean interactable) {
		super(world, id, interactable);
	}

	protected EntityGhostPlayer(World world, UUID id, boolean interactable, Entity target, int length) {
		super(world, id, interactable, target, length);
	}

}