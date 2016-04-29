package Tamaized.Voidcraft.tools;

import net.minecraft.item.ItemSpade;
import net.minecraftforge.fml.common.registry.GameRegistry;
import Tamaized.Voidcraft.registry.IBasicVoid;

public class BasicVoidItemSpade extends ItemSpade implements IBasicVoid{
	
	private final String name;

	public BasicVoidItemSpade(ToolMaterial material, String n) {
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
