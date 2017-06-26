package Tamaized.Voidcraft.client.entity.animation;

import Tamaized.Voidcraft.common.entity.EntityVoidNPC;
import io.netty.buffer.ByteBufInputStream;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.io.DataOutputStream;
import java.io.IOException;

public interface IAnimation<T extends EntityVoidNPC, E extends AnimatableModel> {

	/**
	 * return true when finished
	 */
	boolean update(T e);

	@SideOnly(Side.CLIENT)
	void render(T e, E model);

	void encodePacket(DataOutputStream stream) throws IOException;

	void decodePacket(ByteBufInputStream stream) throws IOException;
	
}
