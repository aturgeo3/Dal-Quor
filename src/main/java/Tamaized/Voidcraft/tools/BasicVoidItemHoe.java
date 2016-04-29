package Tamaized.Voidcraft.tools;

import net.minecraft.item.ItemHoe;
import net.minecraftforge.fml.common.registry.GameRegistry;
import Tamaized.Voidcraft.registry.IBasicVoid;

public class BasicVoidItemHoe extends ItemHoe implements IBasicVoid{
	
	private final String name;

	public BasicVoidItemHoe(ToolMaterial material, String n) {
		super(material);
		name = n;
		setUnlocalizedName(name);
		GameRegistry.registerItem(this, "tools/"+n);
	}

	@Override
	public String getName() {
		return name;
	}

}
