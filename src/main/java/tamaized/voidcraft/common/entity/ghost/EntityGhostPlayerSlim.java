package tamaized.voidcraft.common.entity.ghost;

import net.minecraft.entity.Entity;
import net.minecraft.world.World;

import java.util.UUID;

public class EntityGhostPlayerSlim extends EntityGhostPlayerBase {

	public EntityGhostPlayerSlim(World world) {
		super(world);
	}

	protected EntityGhostPlayerSlim(World world, UUID id, boolean interactable) {
		super(world, id, interactable);
	}

	protected EntityGhostPlayerSlim(World world, UUID id, boolean interactable, Entity target, int length) {
		super(world, id, interactable, target, length);
	}

}