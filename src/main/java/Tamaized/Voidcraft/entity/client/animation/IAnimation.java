package Tamaized.Voidcraft.entity.client.animation;

import net.minecraft.util.math.Vec3d;
import Tamaized.Voidcraft.entity.EntityVoidBoss;

public interface IAnimation<T extends EntityVoidBoss> {

	public void init(Vec3d pos);

	/**
	 * return true when finished
	 */
	public boolean update(T e);

	public Vec3d originalVector();

	public Vec3d resultVector();

}
