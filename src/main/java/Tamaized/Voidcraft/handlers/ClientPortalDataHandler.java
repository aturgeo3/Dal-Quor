package Tamaized.Voidcraft.handlers;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ClientPortalDataHandler {
	
	@SideOnly(Side.CLIENT)
	public static float tick = 0;
	@SideOnly(Side.CLIENT)
	public static int type = 0;
	@SideOnly(Side.CLIENT)
	public static boolean active = false;

}
