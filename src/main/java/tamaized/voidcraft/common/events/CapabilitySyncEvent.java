package tamaized.voidcraft.common.events;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.relauncher.Side;
import tamaized.voidcraft.VoidCraft;
import tamaized.voidcraft.common.capabilities.CapabilityList;
import tamaized.voidcraft.common.capabilities.vadeMecum.IVadeMecumCapability;
import tamaized.voidcraft.network.client.ClientPacketHandlerVadeMecumUpdate;

public class CapabilitySyncEvent {

	@SubscribeEvent
	public void onPlayerTick(TickEvent.PlayerTickEvent e) {
		if (e.phase == TickEvent.Phase.END && e.side == Side.SERVER && e.player instanceof EntityPlayerMP) {
			IVadeMecumCapability cap = e.player.getCapability(CapabilityList.VADEMECUM, null);
			if (cap != null && cap.isDirty()) {
				VoidCraft.network.sendTo(new ClientPacketHandlerVadeMecumUpdate.Packet(cap, ClientPacketHandlerVadeMecumUpdate.Type.NULL), (EntityPlayerMP)e.player);
				cap.resetDirty();
			}
		}
	}

}
