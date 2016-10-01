package Tamaized.Voidcraft.entity.client.animation;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public interface IAnimatable {

	public boolean update();

	@SideOnly(Side.CLIENT)
	public void render();
}
