package tamaized.voidcraft.common.capabilities;

import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import tamaized.voidcraft.common.capabilities.elytraFlying.IElytraFlyingCapability;
import tamaized.voidcraft.common.capabilities.starforge.IStarForgeCapability;
import tamaized.voidcraft.common.capabilities.vadeMecum.IVadeMecumCapability;
import tamaized.voidcraft.common.capabilities.vadeMecumItem.IVadeMecumItemCapability;
import tamaized.voidcraft.common.capabilities.voidicInfusion.IVoidicInfusionCapability;
import tamaized.voidcraft.common.capabilities.voidicPower.IVoidicPowerCapability;

public class CapabilityList {

	@CapabilityInject(IVoidicInfusionCapability.class)
	public static final Capability<IVoidicInfusionCapability> VOIDICINFUSION = null;

	@CapabilityInject(IVoidicPowerCapability.class)
	public static final Capability<IVoidicPowerCapability> VOIDICPOWER = null;

	@CapabilityInject(IVadeMecumCapability.class)
	public static final Capability<IVadeMecumCapability> VADEMECUM = null;

	@CapabilityInject(IVadeMecumItemCapability.class)
	public static final Capability<IVadeMecumItemCapability> VADEMECUMITEM = null;

	@CapabilityInject(IElytraFlyingCapability.class)
	public static final Capability<IElytraFlyingCapability> ELYTRAFLYING = null;

	@CapabilityInject(IStarForgeCapability.class)
	public static final Capability<IStarForgeCapability> STARFORGE = null;

}
