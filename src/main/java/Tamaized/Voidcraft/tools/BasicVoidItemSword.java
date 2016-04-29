package Tamaized.Voidcraft.tools;

import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemSword;
import net.minecraftforge.fml.common.registry.GameRegistry;
import Tamaized.Voidcraft.registry.IBasicVoid;

public class BasicVoidItemSword extends ItemSword implements IBasicVoid{
	
	private final String name;

	public BasicVoidItemSword(ToolMaterial material, String n) {
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
