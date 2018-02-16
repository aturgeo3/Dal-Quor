package tamaized.dalquor.common.capabilities.vadeMecumItem;

import io.netty.buffer.ByteBufInputStream;

import java.io.DataOutputStream;
import java.io.IOException;

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
