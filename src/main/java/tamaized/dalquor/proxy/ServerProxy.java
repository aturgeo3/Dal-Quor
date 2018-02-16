package tamaized.dalquor.proxy;

import com.mojang.authlib.GameProfile;
import net.minecraftforge.fml.common.FMLCommonHandler;

public class ServerProxy extends CommonProxy {

	public ServerProxy() {
		super(Side.SERVER);
	}

	@Override
	public void preRegisters() {

	}

	@Override
	public void preInit() {

	}

	@Override
	public void init() {

	}

	@Override
	public void postInit() {

	}

	@Override
	public void fillProfileProperties(GameProfile profile, boolean verify) {
		FMLCommonHandler.instance().getMinecraftServerInstance().getMinecraftSessionService().fillProfileProperties(profile, verify);
	}
}