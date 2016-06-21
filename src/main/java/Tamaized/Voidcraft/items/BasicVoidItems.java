package Tamaized.Voidcraft.items;

import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.GameRegistry;
import Tamaized.Voidcraft.registry.IBasicVoid;

public class BasicVoidItems extends Item implements IBasicVoid{
	
	private final String name;

	public BasicVoidItems(String n) {
		super();
		name = n;
		setUnlocalizedName(name);
		GameRegistry.registerItem(this, "items/"+n);
	}
	
	@Override
	public String getName() {
		return name;
	}
}
