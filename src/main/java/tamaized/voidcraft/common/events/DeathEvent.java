package tamaized.voidcraft.common.events;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.DamageSource;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import tamaized.voidcraft.common.entity.mob.EntityMobSpectreChain;
import tamaized.voidcraft.common.handlers.ConfigHandler;

public class DeathEvent {

	@SubscribeEvent
	public void onDeath(LivingDeathEvent e) {
		EntityLivingBase target = e.getEntityLiving();
		if (target instanceof EntityPlayer && target.world.provider.getDimension() == ConfigHandler.dimensionIdDalQuor) {
			target.setHealth(target.getMaxHealth());
			e.setCanceled(true);
			target.fallDistance = 0;
			VoidTickEvent.dream((EntityPlayer) target);
		}
		DamageSource source = e.getSource();
		if (target != null && !(target instanceof EntityMobSpectreChain) && source != null && source.getTrueSource() != null && source.getTrueSource() instanceof EntityMobSpectreChain) {
			EntityMobSpectreChain newSpawn = new EntityMobSpectreChain(target.world);
			newSpawn.setLocationAndAngles(target.posX, target.posY, target.posZ, target.rotationYaw, target.rotationPitch);
			target.world.spawnEntity(newSpawn);
		}
	}

}
