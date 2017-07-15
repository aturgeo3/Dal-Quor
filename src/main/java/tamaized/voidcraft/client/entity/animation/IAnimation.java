package tamaized.voidcraft.client.entity.animation;

import io.netty.buffer.ByteBuf;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import tamaized.voidcraft.common.entity.EntityVoidNPC;

public interface IAnimation<T extends EntityVoidNPC, E extends AnimatableModel> {

	/**
	 * return true when finished
	 */
	boolean update(T e);

	@SideOnly(Side.CLIENT)
	void render(T e, E model);

	void encodePacket(ByteBuf stream);

	void decodePacket(ByteBuf stream);

}
