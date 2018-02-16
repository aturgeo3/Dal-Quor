package tamaized.dalquor.common.events;

import net.minecraftforge.event.entity.living.LivingSpawnEvent;
import net.minecraftforge.fml.common.eventhandler.Event.Result;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import tamaized.dalquor.common.handlers.ConfigHandler;
import tamaized.dalquor.common.world.dim.dalquor.BiomeGenDream;

public class SpawnEvent {

	@SubscribeEvent
	public void canEntitySpawn(LivingSpawnEvent.CheckSpawn event) {
		if (!event.getWorld().isRemote) {
			if (event.getWorld().provider.getDimension() == ConfigHandler.dimensionIdDalQuor) {
				if (BiomeGenDream.allowedEntities.contains(event.getEntity().getClass())) {
					if (event.getWorld().rand.nextInt(500) == 0)
						event.setResult(Result.ALLOW);
					else
						event.setResult(Result.DENY);
				}
			}
		}
	}

}
