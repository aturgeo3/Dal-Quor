package tamaized.dalquor.proxy;

import com.mojang.authlib.GameProfile;
import tamaized.tammodized.proxy.AbstractProxy;

public abstract class CommonProxy extends AbstractProxy {

	public CommonProxy(Side side) {
		super(side);
	}

	public abstract void fillProfileProperties(GameProfile profile, boolean verify);
}
