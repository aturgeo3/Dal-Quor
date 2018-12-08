package tamaized.dalquor.registry;

import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.potion.PotionType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import tamaized.dalquor.DalQuor;
import tamaized.dalquor.common.potion.ModPotion;
import tamaized.dalquor.common.potion.PotionSheathe;

@GameRegistry.ObjectHolder(DalQuor.modid)
@Mod.EventBusSubscriber
public class ModPotions {

	@GameRegistry.ObjectHolder("voidicinfusion")
	public static final Potion voidicInfusion;
	@GameRegistry.ObjectHolder("voidicinfusionimmunity")
	public static final Potion voidicInfusionImmunity;
	@GameRegistry.ObjectHolder("voidimmunity")
	public static final Potion voidImmunity;

	@GameRegistry.ObjectHolder("potionsheathefire")
	public static final Potion fireSheathe;
	@GameRegistry.ObjectHolder("potionsheathefrost")
	public static final Potion frostSheathe;
	@GameRegistry.ObjectHolder("potionsheathelit")
	public static final Potion litSheathe;
	@GameRegistry.ObjectHolder("potionsheatheacid")
	public static final Potion acidSheathe;
	@GameRegistry.ObjectHolder("potionsheathevoid")
	public static final Potion voidSheathe;

	@GameRegistry.ObjectHolder("typevoidimmunity")
	public static final PotionType type_voidImmunity;

	static {
		voidicInfusion = null;
		voidicInfusionImmunity = null;
		voidImmunity = setup("voidimmunity", 0x000000, false, true);

		fireSheathe = null;
		frostSheathe = null;
		litSheathe = null;
		acidSheathe = null;
		voidSheathe = null;

		type_voidImmunity = null;
	}

	private static Potion setup(String name, int color, boolean bad, boolean beneficial) {
		return setup(new ModPotion(name, color, bad), name, beneficial);
	}

	private static Potion setup(String name, PotionSheathe.Type type, int color, boolean bad, boolean beneficial) {
		return setup(new PotionSheathe(name, type, color, bad), name, beneficial);
	}

	private static Potion setup(Potion potion, String name, boolean beneficial) {
		potion.setRegistryName(DalQuor.modid, name);
		potion.setPotionName("effect." + potion.getRegistryName().getNamespace() + "." + potion.getRegistryName().getPath());
		if (beneficial)
			potion.setBeneficial();
		return potion;
	}

	private static PotionType setup(Potion potion, String name, int length) {
		return new PotionType(DalQuor.modid + "." + name, new PotionEffect(potion, length)).setRegistryName(name);
	}


	@SubscribeEvent
	public static void registerPotions(RegistryEvent.Register<Potion> event) {
		event.getRegistry().registerAll(

				setup("voidicinfusion", 0x7700FF, true, false),

				setup("voidicinfusionimmunity", 0x7700FF, false, true),

				voidImmunity,

				setup("potionsheathefire", PotionSheathe.Type.Fire, 0xFF7700, false, true),

				setup("potionsheathefrost", PotionSheathe.Type.Frost, 0x00FFFF, false, true),

				setup("potionsheathelit", PotionSheathe.Type.Lit, 0xAAAACC, false, true),

				setup("potionsheatheacid", PotionSheathe.Type.Acid, 0x00FF00, false, true),

				setup("potionsheathevoid", PotionSheathe.Type.Void, 0x7700FF, false, true)


		);
	}

	@SubscribeEvent
	public static void registerPotionTypes(RegistryEvent.Register<PotionType> event) {
		event.getRegistry().registerAll(

				setup(voidImmunity, "typevoidimmunity", ((60 * 3) + 30) * 20)

		);
	}

}
