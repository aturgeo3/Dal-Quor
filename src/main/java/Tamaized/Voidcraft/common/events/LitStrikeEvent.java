package tamaized.voidcraft.common.events;

import tamaized.voidcraft.common.entity.nonliving.EntityCasterLightningBolt;
import net.minecraftforge.event.entity.EntityStruckByLightningEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class LitStrikeEvent {

	@SubscribeEvent()
	public void litStrike(EntityStruckByLightningEvent event) {
		if (event.getLightning() instanceof EntityCasterLightningBolt) {
			if (((EntityCasterLightningBolt) event.getLightning()).getCaster() == event.getEntity()) event.setCanceled(true);
		}
	}

}
