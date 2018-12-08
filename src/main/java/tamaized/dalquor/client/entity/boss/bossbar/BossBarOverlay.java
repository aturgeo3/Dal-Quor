package tamaized.dalquor.client.entity.boss.bossbar;

import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent.ElementType;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class BossBarOverlay {

	@SubscribeEvent
	public void Render(RenderGameOverlayEvent.Post e) {
		if (e.getType() != ElementType.BOSSHEALTH)
			return;

		RenderBossHeathBar.render(e.getResolution());
		RenderAlternateBossBars.render(e.getResolution(), e.getPartialTicks());
	}

}
