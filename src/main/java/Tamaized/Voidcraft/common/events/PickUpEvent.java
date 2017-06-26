package Tamaized.Voidcraft.common.events;

import net.minecraftforge.event.entity.player.EntityItemPickupEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class PickUpEvent {

	@SubscribeEvent
	public void SomethingPickedup(EntityItemPickupEvent event) { // TODO
		/*if (event.getItem().getEntityItem().isItemEqual(new ItemStack(VoidCraft.items.voidcrystal))) {
			event.getEntityPlayer().addStat(VoidCraft.achievements.voidCrystal, 1);
		} else if (event.getItem().getEntityItem().isItemEqual(new ItemStack(VoidCraft.items.voidStar))) {
			event.getEntityPlayer().addStat(VoidCraft.achievements.artifact, 1);
		} else if (event.getItem().getEntityItem().isItemEqual(new ItemStack(VoidCraft.items.vademecum))) {
			event.getEntityPlayer().addStat(VoidCraft.achievements.vademecum, 1);
		}*/
	}

}
