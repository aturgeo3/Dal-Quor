package Tamaized.Voidcraft.capabilities.elytraFlying;

import java.io.DataOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import io.netty.buffer.ByteBufInputStream;

public interface IElytraFlyingCapability {

	public boolean isDirty();

	public void resetDirty();

	public boolean hasLoaded();

	public void setLoaded();

	public boolean isElytraFlying();

	public void setElytraFlying(boolean flag);
	
	public int getElytraTime();

	void setElytraTime(int time);
	
	public void copyFrom(IElytraFlyingCapability old);

}
