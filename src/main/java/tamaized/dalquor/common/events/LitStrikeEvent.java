package tamaized.dalquor.common.events;

import net.minecraftforge.event.entity.EntityStruckByLightningEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import tamaized.dalquor.common.entity.nonliving.EntityCasterLightningBolt;

public class LitStrikeEvent {

	@SubscribeEvent()
	public void litStrike(EntityStruckByLightningEvent event) {
		if (event.getLightning() instanceof EntityCasterLightningBolt) {
			if (((EntityCasterLightningBolt) event.getLightning()).getCaster() == event.getEntity())
				event.setCanceled(true);
		}
	}

}
