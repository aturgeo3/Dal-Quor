package Tamaized.Voidcraft.registry;

import Tamaized.Voidcraft.sound.VoidSoundEvents;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class Materials extends RegistryBase{
	
	public static ToolMaterial voidTools;
	public static ToolMaterial spectreTools;
	public static ToolMaterial chainTools;
	public static ToolMaterial MoltenTools;
	public static ToolMaterial ArchTools;
	public static ToolMaterial DemonTools;
	
	public static ArmorMaterial voidArmor;
	public static ArmorMaterial demonArmor;
	public static ArmorMaterial xiaArmor;

	@Override
	public void preInit() {
		voidTools = EnumHelper.addToolMaterial("voidcrystal", 3, 2000, 12.0F, 8.0F, 30);
		spectreTools = EnumHelper.addToolMaterial("ectoplasm", 4, 4000, 30.0F, 9.0F, 30);
		chainTools = EnumHelper.addToolMaterial("voidChain", 5, 6000, 35.0F, 10.0F, 30);
		MoltenTools = EnumHelper.addToolMaterial("MoltenvoidChain", 6, 8000, 40.0F, 11.0F, 30);
		ArchTools = EnumHelper.addToolMaterial("burnBone", 7, 10000, 45.0F, 12.0F, 30);
		DemonTools = EnumHelper.addToolMaterial("voidStar", 8, 12000, 50.0F, 13.0F, 30);
		
		voidArmor = EnumHelper.addArmorMaterial("Void", "", 120, new int[] {4, 8, 6, 4}, 30, VoidSoundEvents.ArmorSoundEvents.voidcrystal, 0.0f); //22
		demonArmor = EnumHelper.addArmorMaterial("Demon", "", 240, new int[] {4, 10, 6, 4}, 30, VoidSoundEvents.ArmorSoundEvents.demon, 0.0f); //24
		xiaArmor = EnumHelper.addArmorMaterial("Xia", "", 480, new int[] {1, 10, 1, 1}, 30, VoidSoundEvents.ArmorSoundEvents.xia, 0.0f);
	}

	@Override
	public void init() {
		
	}

	@Override
	public void postInit() {
		
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public void setupRender() {
		
	}

}
