package Tamaized.Voidcraft.capabilities;

import Tamaized.Voidcraft.capabilities.vadeMecum.IVadeMecumCapability;
import Tamaized.Voidcraft.capabilities.voidicInfusion.IVoidicInfusionCapability;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;

public class CapabilityList {

	@CapabilityInject(IVoidicInfusionCapability.class)
	public static final Capability<IVoidicInfusionCapability> VOIDICINFUSION = null;

	@CapabilityInject(IVadeMecumCapability.class)
	public static final Capability<IVadeMecumCapability> VADEMECUM = null;

}
