package tamaized.dalquor.network.server;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.items.IItemHandler;
import tamaized.dalquor.client.gui.element.GUIListElement;
import tamaized.dalquor.common.blocks.tileentity.TileEntityStarForge;
import tamaized.dalquor.common.capabilities.CapabilityList;
import tamaized.dalquor.common.capabilities.starforge.IStarForgeCapability;
import tamaized.dalquor.common.starforge.StarForgeEffectEntry;
import tamaized.dalquor.common.starforge.StarForgeToolEntry;
import tamaized.dalquor.registry.ModBlocks;
import tamaized.dalquor.registry.ModItems;

import java.util.List;

public class ServerPacketHandlerStarforgeCraft implements IMessageHandler<ServerPacketHandlerStarforgeCraft.Packet, IMessage> {

	private static void processPacket(Packet message, EntityPlayerMP player, World world) {
		BlockPos pos = new BlockPos(message.x, message.y, message.z);
		if (!world.isBlockLoaded(pos))
			return;
		TileEntity te = world.getTileEntity(pos);
		if (te instanceof TileEntityStarForge) {
			TileEntityStarForge tile = (TileEntityStarForge) te;
			int index = message.index;
			List<GUIListElement> list = tile.buildPossibleEffectList();
			if (index >= 0 && index < list.size()) {
				GUIListElement element = list.get(index);
				if (element instanceof StarForgeToolEntry) {
					StarForgeToolEntry entry = (StarForgeToolEntry) element;
					if (tile.SLOT_INPUT_TOOL.getStackInSlot(0).isEmpty() && tile.SLOT_INPUT_COSMICMATERIAL.getStackInSlot(0).getCount() >= 4 && tile.SLOT_INPUT_QUORIFRAGMENT.getStackInSlot(0).getCount() >= 1) {
						tile.SLOT_INPUT_COSMICMATERIAL.getStackInSlot(0).shrink(4);
						tile.SLOT_INPUT_QUORIFRAGMENT.getStackInSlot(0).shrink(1);
						tile.SLOT_INPUT_TOOL.setStackInSlot(0, entry.getTool());
					}
				} else if (element instanceof StarForgeEffectEntry) {
					StarForgeEffectEntry entry = (StarForgeEffectEntry) element;
					if (!tile.SLOT_INPUT_TOOL.getStackInSlot(0).isEmpty() && tile.SLOT_INPUT_TOOL.getStackInSlot(0).hasCapability(CapabilityList.STARFORGE, null)) {
						boolean flag = true;
						for (ItemStack checkStack : entry.getRecipe().getInputs()) {
							IItemHandler slot = checkStack.getItem() == Item.getItemFromBlock(ModBlocks.cosmicMaterial) ? tile.SLOT_INPUT_COSMICMATERIAL : checkStack.getItem() == ModItems.voidicDragonScale ? tile.SLOT_INPUT_DRAGONSCALE : checkStack.getItem() == ModItems.quoriFragment ? tile.SLOT_INPUT_QUORIFRAGMENT : checkStack.getItem() == ModItems.astralEssence ? tile.SLOT_INPUT_ASTRALESSENCE : tile.SLOT_INPUT_VOIDICPHLOG;
							if (slot.getStackInSlot(0).getCount() >= checkStack.getCount())
								continue;
							flag = false;
						}
						if (flag) {
							ItemStack tool = tile.SLOT_INPUT_TOOL.getStackInSlot(0).copy();
							IStarForgeCapability cap = tool.getCapability(CapabilityList.STARFORGE, null);
							if (cap != null && cap.getEffect(entry.getRecipe().getEffect().getTier()) == null) {
								for (ItemStack checkStack : entry.getRecipe().getInputs()) {
									IItemHandler slot = checkStack.getItem() == Item.getItemFromBlock(ModBlocks.cosmicMaterial) ? tile.SLOT_INPUT_COSMICMATERIAL : checkStack.getItem() == ModItems.voidicDragonScale ? tile.SLOT_INPUT_DRAGONSCALE : checkStack.getItem() == ModItems.quoriFragment ? tile.SLOT_INPUT_QUORIFRAGMENT : checkStack.getItem() == ModItems.astralEssence ? tile.SLOT_INPUT_ASTRALESSENCE : tile.SLOT_INPUT_VOIDICPHLOG;
									slot.getStackInSlot(0).shrink(checkStack.getCount());
								}
								cap.addEffect(entry.getRecipe().getEffect());
							}
							tile.SLOT_INPUT_TOOL.setStackInSlot(0, tool);
						}
					}
				}
			}
		}
	}

	@Override
	public IMessage onMessage(Packet message, MessageContext ctx) {
		EntityPlayerMP player = ctx.getServerHandler().player;
		player.getServer().addScheduledTask(() -> processPacket(message, player, player.world));
		return null;
	}

	public static class Packet implements IMessage {

		public int x;
		public int y;
		public int z;
		public int index;

		public Packet() {

		}

		public Packet(int x, int y, int z, int index) {
			this.x = x;
			this.y = y;
			this.z = z;
			this.index = index;
		}

		@Override
		public void fromBytes(ByteBuf buf) {
			x = buf.readInt();
			y = buf.readInt();
			z = buf.readInt();
			index = buf.readInt();
		}

		@Override
		public void toBytes(ByteBuf buf) {
			buf.writeInt(x);
			buf.writeInt(y);
			buf.writeInt(z);
			buf.writeInt(index);
		}
	}

}
