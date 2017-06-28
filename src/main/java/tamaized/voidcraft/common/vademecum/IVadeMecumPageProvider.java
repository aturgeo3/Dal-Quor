package tamaized.voidcraft.common.vademecum;

import tamaized.voidcraft.common.capabilities.vadeMecum.IVadeMecumCapability;

public interface IVadeMecumPageProvider {
	
	public IVadeMecumPage[] getPageList(IVadeMecumCapability cap);

}
