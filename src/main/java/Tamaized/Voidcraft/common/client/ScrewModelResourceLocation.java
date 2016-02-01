package Tamaized.Voidcraft.common.client;

import Tamaized.Voidcraft.common.voidCraft;
import net.minecraft.client.resources.model.ModelResourceLocation;

public class ScrewModelResourceLocation extends ModelResourceLocation {

	public ScrewModelResourceLocation(String path, String file, String v) {
		super(0, new String[]{voidCraft.modid, path.concat(file), v});
	}

}
