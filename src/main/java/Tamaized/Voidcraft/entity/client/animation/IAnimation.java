package Tamaized.Voidcraft.entity.client.animation;

import Tamaized.Voidcraft.entity.EntityVoidBoss;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.Vec3d;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public interface IAnimation<T extends EntityVoidBoss> {

	public void init(Vec3d pos);

	/**
	 * return true when finished
	 */
	public boolean update(T e);

	public Vec3d originalVector();

	public Vec3d resultVector();
	
	public AxisAlignedBB getBounds(int range);

	@SideOnly(Side.CLIENT)
	public void render(T e);

}
