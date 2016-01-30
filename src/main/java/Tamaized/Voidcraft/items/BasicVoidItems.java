package Tamaized.Voidcraft.items;

import Tamaized.Voidcraft.registry.IBasicVoid;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class BasicVoidItems extends Item implements IBasicVoid{
	
	private final String name;

	public BasicVoidItems(String n) {
		super();
		name = n;
		setUnlocalizedName(name);
		GameRegistry.registerItem(this, n);
	}
	
	@Override
	public String getName() {
		return name;
	}

}
