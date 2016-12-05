package Tamaized.Voidcraft.entity.boss.xia.finalphase;

import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public interface IEntityMultiPartXia {

	World getWorld();

	boolean attackEntityFromPart(EntityDragonPartXia dragonPart, DamageSource source, float damage);

}
