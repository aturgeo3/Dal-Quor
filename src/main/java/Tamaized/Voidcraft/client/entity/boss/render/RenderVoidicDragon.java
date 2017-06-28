package tamaized.voidcraft.client.entity.boss.render;

import tamaized.voidcraft.VoidCraft;
import tamaized.voidcraft.common.entity.boss.dragon.EntityVoidicDragon;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

public class RenderVoidicDragon<T extends EntityVoidicDragon> extends RenderDragonOldWithBar<T> {

	private static final ResourceLocation DRAGON_EXPLODING_TEXTURES = new ResourceLocation(VoidCraft.modid, "textures/entity/dragon/voidic/dragon_exploding.png");
	private static final ResourceLocation DRAGON_TEXTURES = new ResourceLocation(VoidCraft.modid, "textures/entity/dragon/voidic/dragon.png");

	public RenderVoidicDragon(RenderManager renderManagerIn) {
		super(renderManagerIn);
	}

	@Override
	protected ResourceLocation getDragonTexture() {
		return DRAGON_TEXTURES;
	}

	@Override
	protected ResourceLocation getExlodeTexture() {
		return DRAGON_EXPLODING_TEXTURES;
	}

}