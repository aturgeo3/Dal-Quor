package tamaized.dalquor.registry;

import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.potion.PotionType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import tamaized.dalquor.common.potion.PotionSheathe;
import tamaized.dalquor.common.potion.PotionVoidImmunity;
import tamaized.dalquor.common.potion.PotionVoidicInfusion;
import tamaized.dalquor.common.potion.PotionVoidicInfusionImmunity;

import java.util.ArrayList;
import java.util.List;

@Mod.EventBusSubscriber
public class VoidCraftPotions {

	public static Potion voidicInfusion;
	public static Potion voidicInfusionImmunity;
	public static Potion voidImmunity;

	public static Potion fireSheathe;
	public static Potion frostSheathe;
	public static Potion litSheathe;
	public static Potion acidSheathe;
	public static Potion voidSheathe;

	public static PotionType type_voidImmunity;

	private static List<Potion> potionList;
	private static List<PotionType> typeList;

	static {
		potionList = new ArrayList<>();
		typeList = new ArrayList<>();

		potionList.add(voidicInfusion = new PotionVoidicInfusion("voidicinfusion"));
		potionList.add(voidicInfusionImmunity = new PotionVoidicInfusionImmunity("voidicinfusionimmunity"));
		potionList.add(voidImmunity = new PotionVoidImmunity("voidimmunity"));

		potionList.add(fireSheathe = new PotionSheathe("potionsheathefire", PotionSheathe.Type.Fire));
		potionList.add(frostSheathe = new PotionSheathe("potionsheathefrost", PotionSheathe.Type.Frost));
		potionList.add(litSheathe = new PotionSheathe("potionsheathelit", PotionSheathe.Type.Lit));
		potionList.add(acidSheathe = new PotionSheathe("potionsheatheacid", PotionSheathe.Type.Acid));
		potionList.add(voidSheathe = new PotionSheathe("potionsheathevoid", PotionSheathe.Type.Void));

		typeList.add(type_voidImmunity = new PotionType(new PotionEffect[]{new PotionEffect(voidImmunity, ((60 * 3) + 30) * 20)}).setRegistryName("voidimmunity"));
	}


	@SubscribeEvent
	public static void registerPotions(RegistryEvent.Register<Potion> event) {
		for (Potion p : potionList)
			event.getRegistry().register(p);
	}

	@SubscribeEvent
	public static void registerPotionTypes(RegistryEvent.Register<PotionType> event) {
		for (PotionType t : typeList)
			event.getRegistry().register(t);
	}

}
