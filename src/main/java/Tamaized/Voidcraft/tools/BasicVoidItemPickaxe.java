package Tamaized.Voidcraft.tools;

import net.minecraft.item.ItemPickaxe;
import net.minecraftforge.fml.common.registry.GameRegistry;
import Tamaized.Voidcraft.registry.IBasicVoid;

public class BasicVoidItemPickaxe extends ItemPickaxe implements IBasicVoid {
	
	private final String name;

	public BasicVoidItemPickaxe(ToolMaterial material, String n) {
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
