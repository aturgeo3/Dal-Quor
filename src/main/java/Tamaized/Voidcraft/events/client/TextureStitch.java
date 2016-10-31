package Tamaized.Voidcraft.events.client;

import Tamaized.Voidcraft.voidCraft;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class TextureStitch {

	public static TextureAtlasSprite colorFire_layer_0;
	public static TextureAtlasSprite colorFire_layer_1;

	@SubscribeEvent
	public void stitchTexture(TextureStitchEvent.Pre e) {
		colorFire_layer_0 = e.getMap().registerSprite(new ResourceLocation(voidCraft.modid, "layer/fire_layer_0"));
		colorFire_layer_1 = e.getMap().registerSprite(new ResourceLocation(voidCraft.modid, "layer/fire_layer_1"));
	}

}
