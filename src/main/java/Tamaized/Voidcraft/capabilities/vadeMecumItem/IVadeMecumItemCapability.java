package Tamaized.Voidcraft.capabilities.vadeMecumItem;

import java.io.DataOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import Tamaized.Voidcraft.capabilities.vadeMecum.IVadeMecumCapability.ActivePower;
import io.netty.buffer.ByteBufInputStream;

public interface IVadeMecumItemCapability {
	
	public void markDirty();
	
	public boolean isDirty();
	
	public void resetDirty();

	public void setBookState(boolean state);
	
	public void toggleBookState();

	public boolean getBookState();

	public boolean hasLoaded();

	public void setLoaded();

	public void copyFrom(IVadeMecumItemCapability cap);

	public void decodePacket(ByteBufInputStream stream) throws IOException;

	public void encodePacket(DataOutputStream stream) throws IOException;

}
