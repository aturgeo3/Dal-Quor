package tamaized.dalquor.client.entity.boss.render;

import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import tamaized.tammodized.client.entity.render.RenderDragonOld;
import tamaized.dalquor.DalQuor;
import tamaized.dalquor.common.entity.boss.dragon.EntityVoidicDragon;

public class RenderVoidicDragon<T extends EntityVoidicDragon> extends RenderDragonOld<T> {

	private static final ResourceLocation DRAGON_EXPLODING_TEXTURES = new ResourceLocation(DalQuor.modid, "textures/entity/dragon/voidic/dragon_exploding.png");
	private static final ResourceLocation DRAGON_TEXTURES = new ResourceLocation(DalQuor.modid, "textures/entity/dragon/voidic/dragon.png");

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