package Tamaized.Voidcraft.common.capabilities;

import Tamaized.Voidcraft.common.capabilities.elytraFlying.IElytraFlyingCapability;
import Tamaized.Voidcraft.common.capabilities.starforge.IStarForgeCapability;
import Tamaized.Voidcraft.common.capabilities.vadeMecum.IVadeMecumCapability;
import Tamaized.Voidcraft.common.capabilities.vadeMecumItem.IVadeMecumItemCapability;
import Tamaized.Voidcraft.common.capabilities.voidicInfusion.IVoidicInfusionCapability;
import Tamaized.Voidcraft.common.capabilities.voidicPower.IVoidicPowerCapability;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;

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
