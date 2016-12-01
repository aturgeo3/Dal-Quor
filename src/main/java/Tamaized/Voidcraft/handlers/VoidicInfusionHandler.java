package Tamaized.Voidcraft.handlers;

import Tamaized.Voidcraft.capabilities.CapabilityList;
import Tamaized.Voidcraft.capabilities.voidicInfusion.IVoidicInfusionCapability;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class VoidicInfusionHandler {

	@SubscribeEvent
	public void update(LivingUpdateEvent e) {
		if (e.getEntityLiving().hasCapability(CapabilityList.VOIDICINFUSION, null)){
			IVoidicInfusionCapability cap = e.getEntityLiving().getCapability(CapabilityList.VOIDICINFUSION, null);
			if(cap.hasLoaded()) cap.update(e.getEntityLiving());
			else cap.load(e.getEntityLiving());
		}
	}

}
