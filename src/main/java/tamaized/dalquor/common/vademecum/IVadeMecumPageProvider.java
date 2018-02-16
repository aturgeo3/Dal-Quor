package tamaized.dalquor.common.vademecum;

import tamaized.dalquor.common.capabilities.vadeMecum.IVadeMecumCapability;

public interface IVadeMecumPageProvider {

	public IVadeMecumPage[] getPageList(IVadeMecumCapability cap);

}
