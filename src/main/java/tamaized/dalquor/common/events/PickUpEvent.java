package tamaized.dalquor.common.events;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.player.EntityItemPickupEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import tamaized.dalquor.registry.ModItems;

public class PickUpEvent {

	@SubscribeEvent
	public void SomethingPickedup(EntityItemPickupEvent event) { // TODO
		if (event.getEntityPlayer() instanceof EntityPlayerMP) {
			EntityPlayerMP player = (EntityPlayerMP) event.getEntityPlayer();
			if (event.getItem().getItem().isItemEqual(new ItemStack(ModItems.voidcrystal))) {
				//			event.getEntityPlayer().addStat(VoidCraft.achievements.voidCrystal, 1);
			} else if (event.getItem().getItem().isItemEqual(new ItemStack(ModItems.voidStar))) {
				//			event.getEntityPlayer().addStat(VoidCraft.achievements.artifact, 1);
			}
		}
	}

}
