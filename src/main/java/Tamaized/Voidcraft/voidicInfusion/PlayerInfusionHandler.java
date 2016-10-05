package Tamaized.Voidcraft.voidicInfusion;

import java.io.DataOutputStream;
import java.io.IOException;

import Tamaized.Voidcraft.voidCraft;
import Tamaized.Voidcraft.api.voidicpower.VoidicPowerItemHandler;
import Tamaized.Voidcraft.capabilities.CapabilityList;
import Tamaized.Voidcraft.damageSources.DamageSourceVoidicInfusion;
import Tamaized.Voidcraft.network.ClientPacketHandler;
import io.netty.buffer.ByteBufOutputStream;
import io.netty.buffer.Unpooled;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.network.PacketBuffer;
import net.minecraft.world.WorldServer;
import net.minecraftforge.fml.common.network.internal.FMLProxyPacket;

public class PlayerInfusionHandler {

	private EntityPlayerMP playerMP;
	private EntityPlayer player;
	private int voidicInfusionAmount;
	private int maxAmount = 300;
	private boolean canFly = false;

	private int tick = 0;

	public PlayerInfusionHandler(EntityPlayerMP p) {
		playerMP = p;
		voidicInfusionAmount = 0;
	}

	public PlayerInfusionHandler(EntityPlayerMP p, int a) {
		this(p);
		voidicInfusionAmount = a;
	}

	public PlayerInfusionHandler(EntityPlayerMP p, int a, int m) {
		this(p, a);
		maxAmount = m;
	}

	public EntityPlayer getPlayer() {
		return player;
	}

	public int getInfusion() {
		return voidicInfusionAmount;
	}

	public void addInfusion(int amount) {
		voidicInfusionAmount = voidicInfusionAmount + amount > maxAmount ? maxAmount : voidicInfusionAmount + amount;
	}
	
	public void removeInfusion(int amount){
		voidicInfusionAmount-=amount;
		if(voidicInfusionAmount < 0) voidicInfusionAmount = 0;
	}

	public int getMaxInfusion() {
		return maxAmount;
	}

	public void update() {
		if (player == null || player.isDead) {
			for (WorldServer world : playerMP.getServer().worldServers) {
				player = world.getPlayerEntityByUUID(playerMP.getGameProfile().getId());
				if (player != null) break;
			}
		}
		if (player == null) return;
		if (player instanceof EntityPlayerMP) playerMP = (EntityPlayerMP) player;
		if (tick % 20 == 0) {
			boolean flag = true;
			if (player.getHeldItemMainhand() != null && player.getHeldItemMainhand().getItem() == voidCraft.items.voidicSuppressor) {
				if (VoidicPowerItemHandler.getItemVoidicPower(player.getHeldItemMainhand()) > 0) {
					VoidicPowerItemHandler.setItemVoidicPower(player.getHeldItemMainhand(), VoidicPowerItemHandler.getItemVoidicPower(player.getHeldItemMainhand()) - 1);
					flag = false;
				}
			} else if (player.getHeldItemOffhand() != null && player.getHeldItemOffhand().getItem() == voidCraft.items.voidicSuppressor) {
				if (VoidicPowerItemHandler.getItemVoidicPower(player.getHeldItemOffhand()) > 0) {
					VoidicPowerItemHandler.setItemVoidicPower(player.getHeldItemOffhand(), VoidicPowerItemHandler.getItemVoidicPower(player.getHeldItemOffhand()) - 1);
					flag = false;
				}
			}else if(player.getActivePotionEffect(voidCraft.potions.voidicInfusionImmunity) != null){
				flag = false;
			}
			if (player.worldObj.provider.getDimension() == voidCraft.dimensionIdVoid && flag) {
				voidicInfusionAmount++;
				if (voidicInfusionAmount > maxAmount) voidicInfusionAmount = maxAmount;
			} else {
				voidicInfusionAmount -= 5;
				if (voidicInfusionAmount < 0) voidicInfusionAmount = 0;
			}
		}
		// voidicInfusionAmount = (int)((float)maxAmount*0.00f);
		handleEffects();
		if (tick % 20 == 0) sendPacketUpdates();
		// if(tick % 60 == 0) player.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(20.0f);
		tick++;
	}

	private void handleEffects() {
		if (player.capabilities.isCreativeMode || !player.hasCapability(CapabilityList.VOIDICINFUSION, null) || !player.getCapability(CapabilityList.VOIDICINFUSION, null).hasLoaded()) return;
		if (voidicInfusionAmount >= maxAmount) {
			player.attackEntityFrom(new DamageSourceVoidicInfusion(), player.getMaxHealth());
			voidicInfusionAmount = 0;
			return;
		}
		float perc = ((float) voidicInfusionAmount / (float) maxAmount);
		if (perc >= 0.75f) { // Flight
			player.capabilities.allowFlying = true;
			canFly = true;
		} else {
			if (canFly) {
				player.capabilities.allowFlying = false;
				player.capabilities.isFlying = false;
				player.capabilities.disableDamage = false;
				canFly = false;
			}
		}

		player.getCapability(CapabilityList.VOIDICINFUSION, null).setInfusion(voidicInfusionAmount);

		double i = 0;
		for (IAttributeInstance att : player.getAttributeMap().getAllAttributes()) {
			if (att.getAttribute() == SharedMonsterAttributes.MAX_HEALTH) {
				for (AttributeModifier mod : att.getModifiers()) {
					i += mod.getAmount();
				}
			}
		}
		float preHp = player.getCapability(CapabilityList.VOIDICINFUSION, null).preMaxHealth() + (float) i;
		float hpPerc = (perc * 10.0f) % preHp;
		float hpCheck = player.getCapability(CapabilityList.VOIDICINFUSION, null).checkMaxHealth();
		// System.out.println(preHp+" : "+hpPerc);

		boolean flag = false;
		// System.out.println(player.getMaxHealth()+(preHp*(hpCheck/10.0f))+" : "+preHp);
		if ((player.getMaxHealth() + Math.floor(preHp * (hpCheck / 10.0f))) != preHp) {
			preHp = player.getMaxHealth();
			player.getCapability(CapabilityList.VOIDICINFUSION, null).setPreMaxHealth(preHp - (float) i);
			flag = true;
		}
		if (hpPerc > 0 && player.getMaxHealth() == preHp) {
			flag = true;
		}

		if (hpCheck != hpPerc || flag) {
			float temp = (float) (preHp - (Math.floor(preHp * (hpPerc / 10.0f))));
			temp = (float) (temp < 1.0f ? 1.0f : Math.floor(temp));
			player.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(temp);
			player.getCapability(CapabilityList.VOIDICINFUSION, null).setCheckMaxHealth(hpPerc);
			if (player.getHealth() > player.getMaxHealth()) player.setHealth(player.getMaxHealth());
		}
	}

	public PlayerInfusionHandler sendPacketUpdates() {
		ByteBufOutputStream bos = new ByteBufOutputStream(Unpooled.buffer());
		DataOutputStream outputStream = new DataOutputStream(bos);
		try {
			outputStream.writeInt(ClientPacketHandler.getPacketTypeID(ClientPacketHandler.PacketType.INFUSION_UPDATE));
			outputStream.writeInt(voidicInfusionAmount);
			outputStream.writeInt(maxAmount);
			FMLProxyPacket packet = new FMLProxyPacket(new PacketBuffer(bos.buffer()), voidCraft.networkChannelName);
			if (voidCraft.channel != null && packet != null && playerMP != null) voidCraft.channel.sendTo(packet, playerMP);
			else {
				voidCraft.logger.warn("Something went wrong! Report this to Tamaized");
				voidCraft.logger.warn("channel: " + voidCraft.channel);
				voidCraft.logger.warn("packet: " + packet);
				voidCraft.logger.warn("playerMP: " + playerMP);
			}
			if (player != null) {
				if (canFly) player.capabilities.allowFlying = true;
				player.sendPlayerAbilities();
			}
			bos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		player.sendPlayerAbilities();
		return this;
	}

}
