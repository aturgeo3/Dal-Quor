package Tamaized.Voidcraft.common.client;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import Tamaized.Voidcraft.common.voidCraft;

public class ScrewModelResourceLocation extends ModelResourceLocation {

	public ScrewModelResourceLocation(String path, String file, String v) {
		super(0, new String[]{voidCraft.modid, path.concat(file), v});
	}

}
