package tamaized.dalquor.network;

import tamaized.dalquor.common.xiacastle.logic.battle.EntityVoidNPCAIBase;

import java.util.ArrayList;
import java.util.List;

/**
 * Each {@link tamaized.dalquor.common.entity.EntityVoidBoss} has their own Bus
 * This is used to notify each attached {@link EntityVoidNPCAIBase} of some event that occurs in the entity
 * which allows each and every single one to act accordingly
 */
@Deprecated
public class VoidBossAIBus {

	private List<EntityVoidNPCAIBase> listeners = new ArrayList<>();
	private List<IVoidBossAIPacket> packetSpool = new ArrayList<>();

	/**
	 * Packets are to be sent from the entity to the bus, ALL connected AIs will see and read the packet
	 */
	public void sendPacket(IVoidBossAIPacket packet) {
		packetSpool.add(packet);
	}

	public void readNextPacket() {
		if (packetSpool.isEmpty())
			return;
		IVoidBossAIPacket packet = packetSpool.get(0);
		if (packet == null)
			return;
		for (EntityVoidNPCAIBase listener : listeners) {
			listener.readPacket(packet);
		}
		packetSpool.remove(0);
	}

	public void clearListeners() {
		listeners.clear();
	}

	public void addListener(EntityVoidNPCAIBase listener) {
		listeners.add(listener);
	}

	public void removeListener(EntityVoidNPCAIBase listener) {
		listeners.remove(listener);
	}

}
