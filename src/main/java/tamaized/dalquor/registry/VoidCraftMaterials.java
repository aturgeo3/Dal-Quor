package tamaized.dalquor.registry;

import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraftforge.common.util.EnumHelper;
import tamaized.dalquor.DalQuor;
import tamaized.dalquor.common.sound.VoidSoundEvents;

public class VoidCraftMaterials {

	public static ToolMaterial voidTools;
	public static ToolMaterial spectreTools;
	public static ToolMaterial chainTools;
	public static ToolMaterial MoltenTools;
	public static ToolMaterial ArchTools;
	public static ToolMaterial DemonTools;
	public static ToolMaterial CosmicTools;

	public static ArmorMaterial voidArmor;
	public static ArmorMaterial xiaArmor;

	static {
		voidTools = EnumHelper.addToolMaterial(DalQuor.modid + ":voidcrystal", 3, 2000, 12.0F, 8.0F, 30);
		spectreTools = EnumHelper.addToolMaterial(DalQuor.modid + ":spectre", 4, 4000, 30.0F, 9.0F, 30);
		chainTools = EnumHelper.addToolMaterial(DalQuor.modid + ":chain", 5, 6000, 35.0F, 10.0F, 30);
		MoltenTools = EnumHelper.addToolMaterial(DalQuor.modid + ":molten", 6, 8000, 40.0F, 11.0F, 30);
		ArchTools = EnumHelper.addToolMaterial(DalQuor.modid + ":angelic", 7, 10000, 45.0F, 12.0F, 30);
		DemonTools = EnumHelper.addToolMaterial(DalQuor.modid + ":demonic", 8, 12000, 50.0F, 13.0F, 30);
		CosmicTools = EnumHelper.addToolMaterial(DalQuor.modid + ":cosmic", 9, 14000, 55.0F, 14.0F, 30);

		// 25 = invul
		voidArmor = EnumHelper.addArmorMaterial(DalQuor.modid + ":void", DalQuor.modid + ":void", 120, new int[]{4, 6, 8, 4}, 30, VoidSoundEvents.ArmorSoundEvents.voidcrystal, 3.0f); // 22
		xiaArmor = EnumHelper.addArmorMaterial(DalQuor.modid + ":xia", DalQuor.modid + ":xia", 240, new int[]{4, 6, 10, 4}, 30, VoidSoundEvents.ArmorSoundEvents.xia, 4.0f); // 24
	}

}
