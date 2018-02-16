package tamaized.dalquor.common.handlers;

import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import tamaized.dalquor.VoidCraft;

@Mod.EventBusSubscriber(modid = VoidCraft.modid)
@Config(modid = VoidCraft.modid)
public class ConfigHandler {

	@Config.Comment("Enable VoidCrystal Ore Gen")
	public static boolean generate_VoidOre = true;
	@Config.Comment("Enable Cosmic Material Gen")
	public static boolean generate_CosmicOre = true;
	@Config.Comment("Void Dimension ID")
	public static int dimensionIdVoid = -2;
	@Config.Comment("xia Dimension ID")
	public static int dimensionIdXia = -3;
	@Config.Comment("Dal Quor Dimension ID")
	public static int dimensionIdDalQuor = -4;
	@Config.Comment("Reality Hole Dimension Whitelist")
	public static int[] realityWhitelist = new int[]{0, -1};
	@Config.Comment("Render Vade Mecum Item Particles")
	public static boolean renderVadeMecumParticles = true;
	@Config.Comment("Teleport below Y level -256")
	public static boolean voidTeleport = true;
	@Config.Comment("Enable all custom BG Music")
	public static boolean music = true;

	@SubscribeEvent
	public static void onConfigChanged(ConfigChangedEvent.OnConfigChangedEvent event) {
		if (event.getModID().equals(VoidCraft.modid)) {
			ConfigManager.sync(VoidCraft.modid, Config.Type.INSTANCE);
		}
	}

}
