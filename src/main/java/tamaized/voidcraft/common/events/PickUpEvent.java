package tamaized.voidcraft.common.events;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.player.EntityItemPickupEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import tamaized.voidcraft.registry.VoidCraftItems;

public class PickUpEvent {

	@SubscribeEvent
	public void SomethingPickedup(EntityItemPickupEvent event) { // TODO
		if (event.getEntityPlayer() instanceof EntityPlayerMP) {
			EntityPlayerMP player = (EntityPlayerMP) event.getEntityPlayer();
			if (event.getItem().getItem().isItemEqual(new ItemStack(VoidCraftItems.voidcrystal))) {
				//			event.getEntityPlayer().addStat(VoidCraft.achievements.voidCrystal, 1);
			} else if (event.getItem().getItem().isItemEqual(new ItemStack(VoidCraftItems.voidStar))) {
				//			event.getEntityPlayer().addStat(VoidCraft.achievements.artifact, 1);
			}
		}
	}

}
