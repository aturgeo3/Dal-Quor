package Tamaized.Voidcraft.network;

import java.io.IOException;

import Tamaized.Voidcraft.voidCraft;
import Tamaized.Voidcraft.armor.ArmorCustomElytra;
import Tamaized.Voidcraft.capabilities.CapabilityList;
import Tamaized.Voidcraft.capabilities.vadeMecum.IVadeMecumCapability;
import Tamaized.Voidcraft.handlers.CustomElytraHandler;
import Tamaized.Voidcraft.handlers.VadeMecumPacketHandler;
import Tamaized.Voidcraft.items.HookShot;
import Tamaized.Voidcraft.items.RealityTeleporter;
import Tamaized.Voidcraft.machina.tileentity.TileEntityVoidBox;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufInputStream;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.network.NetHandlerPlayServer;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.network.FMLNetworkEvent.ServerCustomPacketEvent;
import net.minecraftforge.fml.relauncher.Side;

public class ServerPacketHandler {

	public static enum PacketType {
		VOIDBOX_PLAY, VOIDBOX_STOP, VOIDBOX_LOOP, VOIDBOX_AUTO, HOOKSHOT_STOP, VADEMECUM, VADEMECUM_LASTENTRY, CUSTOM_ELYTRA, LINK_CLEAR
	}

	public static int getPacketTypeID(PacketType type) {
		return type.ordinal();
	}

	public static PacketType getPacketTypeFromID(int id) {
		return PacketType.values()[id];
	}

	@SubscribeEvent
	public void onServerPacket(ServerCustomPacketEvent event) {
		EntityPlayerMP player = ((NetHandlerPlayServer) event.getHandler()).playerEntity;
		player.getServer().addScheduledTask(new Runnable() {
			public void run() {
				processPacketOnServer(event.getPacket().payload(), Side.SERVER, player);
			}
		});
	}

	public static void processPacketOnServer(ByteBuf parBB, Side parSide, EntityPlayerMP player) {
		if (parSide == Side.SERVER) {
			ByteBufInputStream bbis = new ByteBufInputStream(parBB);
			int pktType;
			try {
				TileEntityVoidBox voidBox;
				pktType = bbis.readInt();
				switch (getPacketTypeFromID(pktType)) {
					case LINK_CLEAR: {
						int slot = bbis.readInt();
						ItemStack stack = null;
						if (slot >= 0 && slot < player.inventory.mainInventory.size()) stack = player.inventory.mainInventory.get(slot);
						else if (slot == -1) stack = player.inventory.offHandInventory.get(0);
						if (stack != null && stack.getItem() == voidCraft.items.realityTeleporter) {
							RealityTeleporter.clearLink(stack);
						}
					}
						break;
					case CUSTOM_ELYTRA: {
						if (!player.onGround && player.motionY < 0.0D && !CustomElytraHandler.isElytraFlying(player) && !player.isInWater()) {
							ItemStack itemstack = player.getItemStackFromSlot(EntityEquipmentSlot.CHEST);
							if (itemstack != null && itemstack.getItem() instanceof ArmorCustomElytra && ArmorCustomElytra.isBroken(itemstack)) {
								CustomElytraHandler.setFlying(player, true);
							}
						} else {
							CustomElytraHandler.setFlying(player, false);
						}
					}
						break;
					case VADEMECUM: {
						VadeMecumPacketHandler.DecodeRequestServer(bbis, player);
					}
						break;
					case VADEMECUM_LASTENTRY:
						IVadeMecumCapability cap = player.getCapability(CapabilityList.VADEMECUM, null);
						if (cap != null) cap.setLastEntry(bbis.readUTF());
						break;
					case VOIDBOX_PLAY: {
						voidBox = (TileEntityVoidBox) player.world.getTileEntity(new BlockPos(bbis.readInt(), bbis.readInt(), bbis.readInt()));
						if (voidBox == null) {
							bbis.close();
							return;
						}
						voidBox.PlayNextSound();
					}
						break;
					case VOIDBOX_STOP: {
						voidBox = (TileEntityVoidBox) player.world.getTileEntity(new BlockPos(bbis.readInt(), bbis.readInt(), bbis.readInt()));
						if (voidBox == null) {
							bbis.close();
							return;
						}
						voidBox.StopTheSoundAndDeposit();
					}
						break;
					case VOIDBOX_LOOP: {
						voidBox = (TileEntityVoidBox) player.world.getTileEntity(new BlockPos(bbis.readInt(), bbis.readInt(), bbis.readInt()));
						if (voidBox == null) {
							bbis.close();
							return;
						}
						voidBox.setLoopState();
					}
						break;
					case VOIDBOX_AUTO: {
						voidBox = (TileEntityVoidBox) player.world.getTileEntity(new BlockPos(bbis.readInt(), bbis.readInt(), bbis.readInt()));
						if (voidBox == null) {
							bbis.close();
							return;
						}
						voidBox.setAutoState();
					}
						break;
					case HOOKSHOT_STOP: {
						HookShot.handler.put(player, false);
					}
						break;
					default: {

					}
						break;
				}
			} catch (Exception e) {
				try {
					bbis.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				e.printStackTrace();
			}
		}
	}

}
