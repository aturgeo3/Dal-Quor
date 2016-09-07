package Tamaized.Voidcraft.events;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.DamageSource;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import Tamaized.Voidcraft.capabilities.CapabilityList;
import Tamaized.Voidcraft.damageSources.DamageSourceVoidicInfusion;
import Tamaized.Voidcraft.entity.EntityVoidBossMob;
import Tamaized.Voidcraft.entity.EntityVoidMob;
import Tamaized.Voidcraft.entity.EntityVoidNPC;

public class DamageEvent {
	
	@SubscribeEvent
	public void entityDamaged(LivingAttackEvent e){
		if(e.getSource() instanceof DamageSourceVoidicInfusion || (e.getSource() != null && !(e.getSource().getEntity() instanceof EntityPlayer))) return;
		EntityPlayer attacker = (EntityPlayer) e.getSource().getEntity();
		Entity victim = e.getEntity();
		if(!attacker.hasCapability(CapabilityList.VOIDICINFUSION, null) || attacker.getCapability(CapabilityList.VOIDICINFUSION, null).getInfusionPerc() < 0.30f) return;
		victim.attackEntityFrom(new DamageSourceVoidicInfusion(), 1.0f);
	}
	
	@SubscribeEvent
	public void playerDamaged(LivingAttackEvent e){
		if(!(e.getEntity() instanceof EntityPlayer)) return;
		EntityPlayer player = (EntityPlayer) e.getEntity();
		if(!player.hasCapability(CapabilityList.VOIDICINFUSION, null) || player.getCapability(CapabilityList.VOIDICINFUSION, null).getInfusionPerc() < 0.50f) return;
		if(Math.floor(Math.random()*5) == 0 && isWhiteListed(e.getSource())){ //0-4; 25%
			e.setCanceled(true);
			player.addChatMessage(new TextComponentString("Attack Dodged"));
			return;
		}
		if(isWhiteListed(e.getSource()) && !e.getSource().damageType.equals("arrow")){
			if(e.getSource().getEntity() != null && !(e.getSource().getEntity() instanceof EntityVoidMob || e.getSource().getEntity() instanceof EntityVoidNPC || e.getSource().getEntity() instanceof EntityVoidBossMob)){
				int a = (int) Math.floor(player.getCapability(CapabilityList.VOIDICINFUSION, null).getInfusionPerc()*10);
				if(a > 0) e.getEntity().attackEntityFrom(new DamageSourceVoidicInfusion(), a);
			}
		}
	}
	
	private boolean isWhiteListed(DamageSource source){
		return source.damageType.equals("generic") || 
				source.damageType.equals("mob") ||
				source.damageType.equals("player") ||
				source.damageType.equals("arrow") ||
				source.damageType.equals("thrown");
	}

}
