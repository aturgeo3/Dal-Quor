package tamaized.voidcraft.network;

import tamaized.voidcraft.common.gui.GuiHandler;
import tamaized.voidcraft.VoidCraft;
import tamaized.voidcraft.common.armor.ArmorCustomElytra;
import tamaized.voidcraft.common.blocks.tileentity.TileEntityStarForge;
import tamaized.voidcraft.common.capabilities.CapabilityList;
import tamaized.voidcraft.common.capabilities.starforge.IStarForgeCapability;
import tamaized.voidcraft.common.capabilities.vadeMecum.IVadeMecumCapability;
import tamaized.voidcraft.common.events.CustomElytraHandler;
import tamaized.voidcraft.client.gui.element.GUIListElement;
import tamaized.voidcraft.common.items.RealityTeleporter;
import tamaized.voidcraft.common.machina.tileentity.TileEntityVoidBox;
import tamaized.voidcraft.registry.VoidCraftBlocks;
import tamaized.voidcraft.registry.VoidCraftItems;
import tamaized.voidcraft.common.starforge.StarForgeEffectEntry;
import tamaized.voidcraft.common.starforge.StarForgeToolEntry;
import tamaized.voidcraft.common.vademecum.progression.VadeMecumPacketHandler;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufInputStream;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.network.NetHandlerPlayServer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.network.FMLNetworkEvent.ServerCustomPacketEvent;
import net.minecraftforge.fml.common.network.internal.FMLNetworkHandler;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.items.IItemHandler;

import java.io.IOException;
import java.util.ArrayList;

public class ServerPacketHandler {

	public static int getPacketTypeID(PacketType type) {
		return type.ordinal();
	}

	public static PacketType getPacketTypeFromID(int id) {
		return PacketType.values()[id];
	}

	public static void processPacketOnServer(ByteBuf parBB, Side parSide, EntityPlayerMP player) {
		if (parSide == Side.SERVER) {
			World world = player.world;
			ByteBufInputStream bbis = new ByteBufInputStream(parBB);
			int pktType;
			try {
				pktType = bbis.readInt();
				switch (getPacketTypeFromID(pktType)) {
					case STARFORGE_CRAFT: {
						BlockPos pos = new BlockPos(bbis.readInt(), bbis.readInt(), bbis.readInt());
						if (!world.isBlockLoaded(pos))
							break;
						TileEntity te = world.getTileEntity(pos);
						if (te instanceof TileEntityStarForge) {
							TileEntityStarForge tile = (TileEntityStarForge) te;
							int index = bbis.readInt();
							ArrayList<GUIListElement> list = tile.buildPossibleEffectList();
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
											IItemHandler slot = checkStack.getItem() == Item.getItemFromBlock(VoidCraftBlocks.cosmicMaterial) ? tile.SLOT_INPUT_COSMICMATERIAL : checkStack.getItem() == VoidCraftItems.voidicDragonScale ? tile.SLOT_INPUT_DRAGONSCALE : checkStack.getItem() == VoidCraftItems.quoriFragment ? tile.SLOT_INPUT_QUORIFRAGMENT : checkStack.getItem() == VoidCraftItems.astralEssence ? tile.SLOT_INPUT_ASTRALESSENCE : tile.SLOT_INPUT_VOIDICPHLOG;
											if (slot.getStackInSlot(0).getCount() >= checkStack.getCount())
												continue;
											flag = false;
										}
										if (flag) {
											ItemStack tool = tile.SLOT_INPUT_TOOL.getStackInSlot(0).copy();
											IStarForgeCapability cap = tool.getCapability(CapabilityList.STARFORGE, null);
											if (cap != null && cap.getEffect(entry.getRecipe().getEffect().getTier()) == null) {
												for (ItemStack checkStack : entry.getRecipe().getInputs()) {
													IItemHandler slot = checkStack.getItem() == Item.getItemFromBlock(VoidCraftBlocks.cosmicMaterial) ? tile.SLOT_INPUT_COSMICMATERIAL : checkStack.getItem() == VoidCraftItems.voidicDragonScale ? tile.SLOT_INPUT_DRAGONSCALE : checkStack.getItem() == VoidCraftItems.quoriFragment ? tile.SLOT_INPUT_QUORIFRAGMENT : checkStack.getItem() == VoidCraftItems.astralEssence ? tile.SLOT_INPUT_ASTRALESSENCE : tile.SLOT_INPUT_VOIDICPHLOG;
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
					break;
					case LINK_CLEAR: {
						int slot = bbis.readInt();
						ItemStack stack = ItemStack.EMPTY;
						if (slot >= 0 && slot < player.inventory.mainInventory.size())
							stack = player.inventory.mainInventory.get(slot);
						else if (slot == -1)
							stack = player.inventory.offHandInventory.get(0);
						if (!stack.isEmpty() && stack.getItem() == VoidCraftItems.realityTeleporter) {
							RealityTeleporter.clearLink(stack);
						}
					}
					break;
					case CUSTOM_ELYTRA: {
						if (!player.onGround && player.motionY < 0.0D && !CustomElytraHandler.isElytraFlying(player) && !player.isInWater()) {
							ItemStack itemstack = player.getItemStackFromSlot(EntityEquipmentSlot.CHEST);
							if (!itemstack.isEmpty() && itemstack.getItem() instanceof ArmorCustomElytra && ArmorCustomElytra.isBroken(itemstack)) {
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
					case VADEMECUM_LASTENTRY: {
						IVadeMecumCapability cap = player.getCapability(CapabilityList.VADEMECUM, null);
						if (cap != null)
							cap.setLastEntry(bbis.readUTF());
					}
					break;
					case VADEMECUM_SPELLBOOK: {
						FMLNetworkHandler.openGui(player, VoidCraft.instance, GuiHandler.getTypeID(GuiHandler.Type.VadeMecumSpells), player.world, player.getPosition().getX(), player.getPosition().getY(), player.getPosition().getZ());
					}
					break;
					case VOIDBOX_PLAY: {
						BlockPos pos = new BlockPos(bbis.readInt(), bbis.readInt(), bbis.readInt());
						if (!world.isBlockLoaded(pos))
							break;
						TileEntity te = world.getTileEntity(pos);
						if (!(te instanceof TileEntityVoidBox))
							break;
						TileEntityVoidBox voidBox = (TileEntityVoidBox) te;
						if (voidBox == null)
							break;
						voidBox.PlayNextSound();
					}
					break;
					case VOIDBOX_STOP: {
						BlockPos pos = new BlockPos(bbis.readInt(), bbis.readInt(), bbis.readInt());
						if (!world.isBlockLoaded(pos))
							break;
						TileEntity te = world.getTileEntity(pos);
						if (!(te instanceof TileEntityVoidBox))
							break;
						TileEntityVoidBox voidBox = (TileEntityVoidBox) te;
						if (voidBox == null)
							break;
						voidBox.StopTheSoundAndDeposit();
					}
					break;
					case VOIDBOX_LOOP: {
						BlockPos pos = new BlockPos(bbis.readInt(), bbis.readInt(), bbis.readInt());
						if (!world.isBlockLoaded(pos))
							break;
						TileEntity te = world.getTileEntity(pos);
						if (!(te instanceof TileEntityVoidBox))
							break;
						TileEntityVoidBox voidBox = (TileEntityVoidBox) te;
						if (voidBox == null)
							break;
						voidBox.setLoopState();
					}
					break;
					case VOIDBOX_AUTO: {
						BlockPos pos = new BlockPos(bbis.readInt(), bbis.readInt(), bbis.readInt());
						if (!world.isBlockLoaded(pos))
							break;
						TileEntity te = world.getTileEntity(pos);
						if (!(te instanceof TileEntityVoidBox))
							break;
						TileEntityVoidBox voidBox = (TileEntityVoidBox) te;
						if (voidBox == null)
							break;
						voidBox.setAutoState();
					}
					break;
					default: {

					}
					break;
				}
				bbis.close();
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

	@SubscribeEvent
	public void onServerPacket(ServerCustomPacketEvent event) {
		EntityPlayerMP player = ((NetHandlerPlayServer) event.getHandler()).player;
		player.getServer().addScheduledTask(() -> processPacketOnServer(event.getPacket().payload(), Side.SERVER, player));
	}

	public static enum PacketType {
		VOIDBOX_PLAY, VOIDBOX_STOP, VOIDBOX_LOOP, VOIDBOX_AUTO, VADEMECUM, VADEMECUM_LASTENTRY, VADEMECUM_SPELLBOOK, CUSTOM_ELYTRA, LINK_CLEAR, STARFORGE_CRAFT
	}

}
