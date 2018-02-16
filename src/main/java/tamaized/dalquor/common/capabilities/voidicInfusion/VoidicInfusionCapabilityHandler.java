package tamaized.dalquor.common.capabilities.voidicInfusion;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.boss.EntityDragon;
import net.minecraft.entity.boss.EntityWither;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraftforge.fml.common.network.NetworkRegistry.TargetPoint;
import tamaized.tammodized.common.entity.EntityDragonOld;
import tamaized.dalquor.VoidCraft;
import tamaized.dalquor.common.capabilities.CapabilityList;
import tamaized.dalquor.common.capabilities.vadeMecum.IVadeMecumCapability;
import tamaized.dalquor.common.capabilities.voidicPower.IVoidicPowerCapability;
import tamaized.dalquor.common.damagesources.DamageSourceVoidicInfusion;
import tamaized.dalquor.common.entity.EntityVoidMob;
import tamaized.dalquor.common.entity.EntityVoidNPC;
import tamaized.dalquor.common.entity.companion.EntityVoidParrot;
import tamaized.dalquor.common.handlers.ConfigHandler;
import tamaized.dalquor.network.client.ClientPacketHandlerHealth;
import tamaized.dalquor.network.client.ClientPacketHandlerInfusion;
import tamaized.dalquor.registry.VoidCraftBlocks;
import tamaized.dalquor.registry.VoidCraftPotions;

import java.util.Iterator;

public class VoidicInfusionCapabilityHandler implements IVoidicInfusionCapability {

	public static final ResourceLocation ID = new ResourceLocation(VoidCraft.modid, "VoidicInfusionCapabilityHandler");

	private int infusion = 0;
	private int maxInfusion = 6000;

	private boolean hasFlight = false;
	private int xiaDefeats = 0;

	private boolean hasLoaded = false;

	private int tick = 1;

	public static void uWotM8(ItemStack stack, IVoidicPowerCapability cap) { // TODO item caps suck yo
		NBTTagCompound nbt = stack.getOrCreateSubCompound(VoidCraft.modid);
		nbt.setInteger("currPower", cap.getCurrentPower());
		nbt.setInteger("maxPower", cap.getMaxPower());
	}

	@Override
	public void update(EntityLivingBase entity) {
		if (entity.world == null || entity.world.isRemote || entity == null || entity instanceof EntityVoidParrot || entity instanceof EntityVoidMob || entity instanceof EntityVoidNPC || entity instanceof EntityWither || entity instanceof EntityDragon || entity instanceof EntityDragonOld)
			return;
		handleInfusionGain(entity);
		if (tick % 10 == 0)
			doHealthChecks(entity);
		handleEffects(entity);
		if (tick % 20 == 0)
			sendPacketUpdates(entity);
		tick++;
		if (infusion >= 3000 && entity.hasCapability(CapabilityList.VADEMECUM, null)) {
			IVadeMecumCapability cap = entity.getCapability(CapabilityList.VADEMECUM, null);
			if (!cap.hasCategory(IVadeMecumCapability.Category.Voice)) {
				if (cap.hasCategory(IVadeMecumCapability.Category.Flame)) {
					if (cap.hasCategory(IVadeMecumCapability.Category.Freeze)) {
						if (cap.hasCategory(IVadeMecumCapability.Category.AcidSpray)) {
							if (cap.hasCategory(IVadeMecumCapability.Category.Shock)) {
								cap.addCategory(entity, IVadeMecumCapability.Category.Voice);
								entity.sendMessage(new TextComponentTranslation("voidcraft.VadeMecum.voice.Voice"));
							}
						}
					}
				}
			}
		}
	}

	private void handleInfusionGain(EntityLivingBase entity) {
		IVadeMecumCapability vade = entity.getCapability(CapabilityList.VADEMECUM, null);
		int gain = 1;
		boolean flag = (vade == null || !vade.hasPassive(IVadeMecumCapability.Passive.Vigor));
		boolean override = false;
		ItemStack mainHand = entity.getHeldItemMainhand();
		ItemStack offHand = entity.getHeldItemOffhand();
		if (entity.getActivePotionEffect(VoidCraftPotions.voidicInfusion) != null) {
			override = true;
			flag = true;
			gain = 20;
		}
		/*TODO if (!mainHand.isEmpty() && mainHand.getItem() == VoidCraftItems.voidicSuppressor && mainHand.hasCapability(CapabilityList.VOIDICPOWER, null)) {
			IVoidicPowerCapability cap = mainHand.getCapability(CapabilityList.VOIDICPOWER, null);
			if (cap.getCurrentPower() > 0) {
				cap.drain(1);
				uWotM8(mainHand, cap);
				//				cap.sendUpdates(null, 0, entity.getHeldItemMainhand());
				flag = false;
			}
		} else if (!offHand.isEmpty() && offHand.getItem() == VoidCraftItems.voidicSuppressor && offHand.hasCapability(CapabilityList.VOIDICPOWER, null)) {
			IVoidicPowerCapability cap = offHand.getCapability(CapabilityList.VOIDICPOWER, null);
			if (cap.getCurrentPower() > 0) {
				cap.drain(1);
				uWotM8(offHand, cap);
				//				cap.sendUpdates(null, 0, entity.getHeldItemOffhand());
				flag = false;
			}
		} else*/ if (entity.getActivePotionEffect(VoidCraftPotions.voidicInfusionImmunity) != null) {
			flag = false;
		}
		if ((entity.world.provider.getDimension() == ConfigHandler.dimensionIdVoid || override) && flag) {
			if (!(entity.onGround && (entity.world.getBlockState(entity.getPosition().down()).getBlock() == VoidCraftBlocks.blockVoidbrick || entity.world.getBlockState(entity.getPosition().down()).getBlock() == VoidCraftBlocks.blockVoidBrickHalfSlab || entity.world.getBlockState(entity.getPosition().down()).getBlock() == VoidCraftBlocks.blockVoidstairs || entity.world.getBlockState(entity.getPosition().down(2)).getBlock() == VoidCraftBlocks.blockVoidfence || entity.world.getBlockState(entity.getPosition().down()).getBlock() == VoidCraftBlocks.blockVoidBrickDoubleSlab)))
				infusion += gain;
		} else {
			infusion -= (vade != null && vade.hasPassive(IVadeMecumCapability.Passive.Vigor) ? 10 : 5);
			if (infusion < 0)
				infusion = 0;
		}
		if (infusion > maxInfusion)
			infusion = maxInfusion;
	}

	private void doHealthChecks(EntityLivingBase living) {
		if (living instanceof EntityVoidMob || living instanceof EntityVoidNPC || !living.isNonBoss())
			return;
		final String name = "Voidic Infusion";
		for (IAttributeInstance att : living.getAttributeMap().getAllAttributes()) {
			if (att.getAttribute() == SharedMonsterAttributes.MAX_HEALTH) {
				Iterator<AttributeModifier> iter = att.getModifiers().iterator();
				while (iter.hasNext()) {
					AttributeModifier mod = iter.next();
					if (mod.getName().equals(name)) {
						att.removeModifier(mod);
					}
				}
			}
		}
		IVadeMecumCapability cap = living.getCapability(CapabilityList.VADEMECUM, null);
		float value = getInfusionPerc() * ((cap == null) ? 1.0F : cap.hasPassive(IVadeMecumCapability.Passive.Anchor) ? cap.hasPassive(IVadeMecumCapability.Passive.Vigor) ? 0.5F : 2.0F : 1.0F);
		if (value == 0)
			return;
		float min = 1F - (0.5F / living.getMaxHealth());
		living.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).applyModifier(new AttributeModifier(name, Math.min(value, min) * ((cap != null && cap.hasPassive(IVadeMecumCapability.Passive.Vigor) ? 1 : -1)), 2));
		if (living.getHealth() > living.getMaxHealth()) {
			if (living instanceof EntityPlayerMP) {
				EntityPlayerMP player = (EntityPlayerMP) living;
				VoidCraft.network.sendTo(new ClientPacketHandlerHealth.Packet(player.getMaxHealth()), player);
			}
			living.setHealth(living.getMaxHealth());
		}
	}

	private void handleEffects(EntityLivingBase entity) {
		if (infusion >= maxInfusion) {
			boolean kill = true;
			if (entity.hasCapability(CapabilityList.VADEMECUM, null)) {
				IVadeMecumCapability cap = entity.getCapability(CapabilityList.VADEMECUM, null);
				if (cap.hasPassive(IVadeMecumCapability.Passive.Anchor))
					kill = false;
				if (cap.hasCategory(IVadeMecumCapability.Category.Voice) && !cap.hasCategory(IVadeMecumCapability.Category.VoidicControl)) {
					kill = false;
					infusion = 0;
					cap.addCategory(entity, IVadeMecumCapability.Category.VoidicControl);
					entity.sendMessage(new TextComponentTranslation("voidcraft.VadeMecum.voice.VoidicControl"));
				}
				if (cap.hasCategory(IVadeMecumCapability.Category.Empowerment) && !cap.hasCategory(IVadeMecumCapability.Category.Tolerance) && entity.world.provider.getDimension() != ConfigHandler.dimensionIdVoid) {
					kill = false;
					infusion = 0;
					cap.addCategory(entity, IVadeMecumCapability.Category.Tolerance);
					entity.sendMessage(new TextComponentTranslation("voidcraft.VadeMecum.voice.Tolerance"));
				}
			}
			if (kill) {
				if (entity instanceof EntityPlayer) {
					EntityPlayer player = (EntityPlayer) entity;
					if (!player.capabilities.isCreativeMode) {
						entity.attackEntityFrom(new DamageSourceVoidicInfusion(), Integer.MAX_VALUE);
					}
				} else {
					entity.attackEntityFrom(new DamageSourceVoidicInfusion(), Integer.MAX_VALUE);
				}
				return;
			}
		}

		{
			final String name = "Voidic Infusion";
			for (IAttributeInstance att : entity.getAttributeMap().getAllAttributes()) {
				if (att.getAttribute() == SharedMonsterAttributes.ATTACK_DAMAGE) {
					Iterator<AttributeModifier> iter = att.getModifiers().iterator();
					while (iter.hasNext()) {
						AttributeModifier mod = iter.next();
						if (mod.getName().equals(name)) {
							att.removeModifier(mod);
						}
					}
				}
			}
			IVadeMecumCapability cap = entity.getCapability(CapabilityList.VADEMECUM, null);
			if (cap != null) {
				if (cap.hasPassive(IVadeMecumCapability.Passive.Empowerment)) {
					float value = getInfusionPerc();
					if (value != 0) {
						entity.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).applyModifier(new AttributeModifier(name, value, 2));
					}
				}
				if (cap.hasPassive(IVadeMecumCapability.Passive.Tolerance))
					maxInfusion = 12000;
				else
					maxInfusion = 6000;

			}
		}

		if (entity instanceof EntityPlayer) {
			EntityPlayer player = (EntityPlayer) entity;
			if (!player.capabilities.isCreativeMode) {
				if (!hasFlight && canFly(entity)) {
					player.capabilities.allowFlying = true;
					player.sendPlayerAbilities();
					hasFlight = true;
				} else if (hasFlight && !canFly(entity)) {
					player.capabilities.allowFlying = false;
					player.capabilities.isFlying = false;
					player.capabilities.disableDamage = false;
					hasFlight = false;
					player.sendPlayerAbilities();
				}
			}
		}

	}

	@Override
	public int getInfusion() {
		return infusion;
	}

	@Override
	public void setInfusion(int i) {
		infusion = i;
	}

	@Override
	public void addInfusion(int amount) {
		infusion += amount;
	}

	@Override
	public int getMaxInfusion() {
		return maxInfusion;
	}

	@Override
	public void setMaxInfusion(int i) {
		maxInfusion = i;
	}

	@Override
	public float getInfusionPerc() {
		return (float) infusion / (float) maxInfusion;
	}

	@Override
	public boolean canFly(EntityLivingBase entity) {
		IVadeMecumCapability cap = entity.getCapability(CapabilityList.VADEMECUM, null);
		return getInfusionPerc() >= (cap != null && cap.hasPassive(IVadeMecumCapability.Passive.Flight) ? 0.25F : 0.75F);
	}

	@Override
	public int getXiaDefeats() {
		return xiaDefeats;
	}

	@Override
	public void setXiaDefeats(int amount) {
		xiaDefeats = amount;
	}

	@Override
	public boolean hasLoaded() {
		return hasLoaded;
	}

	@Override
	public void load(EntityLivingBase living) {
		if (maxInfusion < 6000)
			maxInfusion = 6000;
		hasLoaded = true;
	}

	@Override
	public void copyFrom(IVoidicInfusionCapability cap) {
		// setInfusion(cap.getInfusion());
		setMaxInfusion(cap.getMaxInfusion());
		setXiaDefeats(cap.getXiaDefeats());
	}

	private void sendPacketUpdates(EntityLivingBase living) {
		tick = 0;
		if (living != null && !living.world.isRemote)
			VoidCraft.network.sendToAllAround(new ClientPacketHandlerInfusion.Packet(living.getEntityId(), infusion, maxInfusion, xiaDefeats), new TargetPoint(living.dimension, living.posX, living.posY, living.posZ, 16 * 8));
	}

}
