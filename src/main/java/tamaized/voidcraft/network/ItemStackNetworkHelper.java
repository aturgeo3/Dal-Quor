package tamaized.voidcraft.network;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufInputStream;
import io.netty.buffer.ByteBufOutputStream;
import io.netty.handler.codec.EncoderException;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompressedStreamTools;
import net.minecraft.nbt.NBTSizeTracker;
import net.minecraft.nbt.NBTTagCompound;

import javax.annotation.Nullable;
import java.io.IOException;

public class ItemStackNetworkHelper { // TODO: TamModized

	private ItemStackNetworkHelper() {
	}

	public static void encodeStack(ItemStack stack, ByteBuf stream) {
		if (stack.isEmpty()) {
			stream.writeShort(-1);
		} else {
			stream.writeShort(Item.getIdFromItem(stack.getItem()));
			stream.writeByte(stack.getCount());
			stream.writeShort(stack.getMetadata());
			NBTTagCompound nbttagcompound = null;

			if (stack.getItem().isDamageable() || stack.getItem().getShareTag()) {
				nbttagcompound = stack.getTagCompound();
			}

			writeNBTTagCompoundToBuffer(nbttagcompound, stream);
		}
	}

	public static ItemStack decodeStack(ByteBuf stream) {
		ItemStack itemstack = ItemStack.EMPTY;
		int i = stream.readShort();

		if (i >= 0) {
			int j = stream.readByte();
			int k = stream.readShort();
			itemstack = new ItemStack(Item.getItemById(i), j, k);
			itemstack.setTagCompound(readNBTTagCompoundFromBuffer(stream));
		}

		return itemstack;
	}

	public static void writeNBTTagCompoundToBuffer(@Nullable NBTTagCompound nbt, ByteBuf stream) {
		if (nbt == null) {
			stream.writeByte(0);
		} else {
			try {
				CompressedStreamTools.write(nbt, new ByteBufOutputStream(stream));
			} catch (IOException ioexception) {
				throw new EncoderException(ioexception);
			}
		}
	}

	@Nullable
	public static NBTTagCompound readNBTTagCompoundFromBuffer(ByteBuf buf) {
		int i = buf.readerIndex();
		byte b0 = buf.readByte();

		if (b0 == 0) {
			return null;
		} else {
			buf.readerIndex(i);

			try {
				return CompressedStreamTools.read(new ByteBufInputStream(buf), new NBTSizeTracker(2097152L));
			} catch (IOException ioexception) {
				throw new EncoderException(ioexception);
			}
		}
	}

}
