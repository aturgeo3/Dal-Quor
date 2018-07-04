package tamaized.dalquor.common.events;

import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EntitySelectors;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.Phase;
import net.minecraftforge.fml.common.gameevent.TickEvent.WorldTickEvent;
import tamaized.dalquor.DalQuor;
import tamaized.dalquor.registry.ModBlocks;
import tamaized.dalquor.registry.ModItems;

import java.util.ConcurrentModificationException;

public class ItemEntityEvent {

	@SubscribeEvent
	public void tick(WorldTickEvent e) {
		if (e.phase == Phase.END || e.world == null || e.world.isRemote)
			return;
		try {
			for (EntityItem entity : e.world.getEntities(EntityItem.class, EntitySelectors.NOT_SPECTATING)) {
				ItemStack stack = entity.getItem();
				if (!stack.isEmpty() && stack.getItem() == Items.BOOK && e.world.getBlockState(entity.getPosition()).getBlock() == ModBlocks.voidFire) {
					e.world.setBlockToAir(entity.getPosition());
					e.world.addWeatherEffect(new EntityLightningBolt(e.world, entity.getPosition().getX(), entity.getPosition().getY(), entity.getPosition().getZ(), true));
					e.world.spawnEntity(new EntityItem(e.world, entity.getPosition().getX(), entity.getPosition().getY(), entity.getPosition().getZ(), new ItemStack(ModItems.vadeMecum)));
					entity.setDead();
				}
			}
		} catch (ConcurrentModificationException stacktrace) {
			DalQuor.instance.logger.error("Something went very wrong while trying to retrieve the loadedEntityList from the world!");
			stacktrace.printStackTrace();
		}
	}

}
