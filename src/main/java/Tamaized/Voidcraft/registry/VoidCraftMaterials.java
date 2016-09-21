package Tamaized.Voidcraft.registry;

import java.util.ArrayList;

import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraftforge.common.util.EnumHelper;
import Tamaized.TamModized.registry.ITamModel;
import Tamaized.TamModized.registry.ITamRegistry;
import Tamaized.Voidcraft.voidCraft;
import Tamaized.Voidcraft.sound.VoidSoundEvents;

public class VoidCraftMaterials implements ITamRegistry {

	public static ToolMaterial voidTools;
	public static ToolMaterial spectreTools;
	public static ToolMaterial chainTools;
	public static ToolMaterial MoltenTools;
	public static ToolMaterial ArchTools;
	public static ToolMaterial DemonTools;

	public static ArmorMaterial voidArmor;
	public static ArmorMaterial xiaArmor;

	@Override
	public void preInit() {
		voidTools = EnumHelper.addToolMaterial("voidcrystal", 3, 2000, 12.0F, 8.0F, 30);
		spectreTools = EnumHelper.addToolMaterial("ectoplasm", 4, 4000, 30.0F, 9.0F, 30);
		chainTools = EnumHelper.addToolMaterial("voidChain", 5, 6000, 35.0F, 10.0F, 30);
		MoltenTools = EnumHelper.addToolMaterial("MoltenvoidChain", 6, 8000, 40.0F, 11.0F, 30);
		ArchTools = EnumHelper.addToolMaterial("burnBone", 7, 10000, 45.0F, 12.0F, 30);
		DemonTools = EnumHelper.addToolMaterial("voidStar", 8, 12000, 50.0F, 13.0F, 30);

		// 25 = invul
		voidArmor = EnumHelper.addArmorMaterial("Void", voidCraft.modid + ":void", 120, new int[] { 4, 6, 8, 4 }, 30, VoidSoundEvents.ArmorSoundEvents.voidcrystal, 0.0f); // 22
		xiaArmor = EnumHelper.addArmorMaterial("Xia", voidCraft.modid + ":xia", 240, new int[] { 4, 6, 10, 4 }, 30, VoidSoundEvents.ArmorSoundEvents.xia, 0.0f); // 24
	}

	@Override
	public void init() {

	}

	@Override
	public void postInit() {

	}

	@Override
	public ArrayList<ITamModel> getModelList() {
		return new ArrayList<ITamModel>();
	}

	@Override
	public String getModID() {
		return voidCraft.modid;
	}

	@Override
	public void clientPreInit() {

	}

	@Override
	public void clientInit() {

	}

	@Override
	public void clientPostInit() {

	}

}
