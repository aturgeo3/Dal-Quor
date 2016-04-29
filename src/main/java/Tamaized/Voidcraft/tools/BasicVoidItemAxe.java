package Tamaized.Voidcraft.tools;

import net.minecraft.item.ItemAxe;
import net.minecraftforge.fml.common.registry.GameRegistry;
import Tamaized.Voidcraft.registry.IBasicVoid;

public class BasicVoidItemAxe extends ItemAxe implements IBasicVoid{
	
	private final String name;

	public BasicVoidItemAxe(ToolMaterial material, String n) {
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
