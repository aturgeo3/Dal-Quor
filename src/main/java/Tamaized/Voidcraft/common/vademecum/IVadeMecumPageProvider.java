package Tamaized.Voidcraft.common.vademecum;

import Tamaized.Voidcraft.common.capabilities.vadeMecum.IVadeMecumCapability;

public interface IVadeMecumPageProvider {
	
	public IVadeMecumPage[] getPageList(IVadeMecumCapability cap);

}
