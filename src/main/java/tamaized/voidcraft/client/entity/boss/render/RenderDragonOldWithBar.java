package tamaized.voidcraft.client.entity.boss.render;

import net.minecraft.client.renderer.entity.RenderManager;
import tamaized.tammodized.client.entity.render.RenderDragonOld;
import tamaized.voidcraft.client.entity.boss.bossbar.RenderAlternateBossBars;
import tamaized.voidcraft.common.entity.boss.dragon.EntityAbstractDragonOld;

public class RenderDragonOldWithBar<T extends EntityAbstractDragonOld> extends RenderDragonOld<T> {

	public RenderDragonOldWithBar(RenderManager renderManagerIn) {
		super(renderManagerIn);
	}

	@Override
	public void doRender(T entity, double x, double y, double z, float entityYaw, float partialTicks) {
		RenderAlternateBossBars.addBoss(entity.bossBarWrapper);
		super.doRender(entity, x, y, z, entityYaw, partialTicks);
	}

}