package Tamaized.Voidcraft.events;

import Tamaized.Voidcraft.voidCraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EntitySelectors;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.Phase;
import net.minecraftforge.fml.common.gameevent.TickEvent.WorldTickEvent;

public class ItemEntityEvent {

	@SubscribeEvent
	public void tick(WorldTickEvent e) {
		if (e.phase == Phase.START || e.world == null || e.world.isRemote) return;
		for (Entity entity : e.world.getEntities(EntityItem.class, EntitySelectors.NOT_SPECTATING)) {
			if (entity instanceof EntityItem) {
				EntityItem ei = (EntityItem) entity;
				ItemStack stack = ei.getEntityItem();
				if (stack != null && stack.getItem() == Items.BOOK && e.world.getBlockState(ei.getPosition()).getBlock() == voidCraft.blocks.fireVoid) {
					e.world.setBlockToAir(ei.getPosition());
					e.world.addWeatherEffect(new EntityLightningBolt(e.world, ei.getPosition().getX(), ei.getPosition().getY(), ei.getPosition().getZ(), true));
					e.world.spawnEntityInWorld(new EntityItem(e.world, ei.getPosition().getX(), ei.getPosition().getY(), ei.getPosition().getZ(), new ItemStack(voidCraft.items.vadeMecum)));
					ei.setDead();
				}
			}
		}
	}

}
