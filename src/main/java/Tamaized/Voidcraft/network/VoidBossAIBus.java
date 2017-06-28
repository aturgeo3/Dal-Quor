package tamaized.voidcraft.network;

import java.util.ArrayList;

import tamaized.voidcraft.common.xiacastle.logic.battle.EntityVoidNPCAIBase;

public class VoidBossAIBus {

	private ArrayList<EntityVoidNPCAIBase> listeners = new ArrayList<EntityVoidNPCAIBase>();
	private ArrayList<IVoidBossAIPacket> packetSpool = new ArrayList<IVoidBossAIPacket>();

	/**
	 * Packets are to be sent from the entity to the bus, ALL connected AIs will see and read the packet
	 */
	public void sendPacket(IVoidBossAIPacket packet) {
		packetSpool.add(packet);
	}

	public void readNextPacket() {
		if(packetSpool.isEmpty()) return;
		IVoidBossAIPacket packet = packetSpool.get(0);
		if (packet == null) return;
		for (EntityVoidNPCAIBase listener : listeners) {
			listener.readPacket(packet);
		}
		packetSpool.remove(0);
	}
	
	public void clearListeners(){
		listeners.clear();
	}
	
	public void addListener(EntityVoidNPCAIBase listener){
		listeners.add(listener);
	}
	
	public void removeListener(EntityVoidNPCAIBase listener){
		listeners.remove(listener);
	}

}
