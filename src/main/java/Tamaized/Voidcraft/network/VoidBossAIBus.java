package Tamaized.Voidcraft.network;

import java.util.ArrayList;

import Tamaized.Voidcraft.voidCraft;
import Tamaized.Voidcraft.xiaCastle.logic.battle.EntityVoidNPCAIBase;

public class VoidBossAIBus {

	private ArrayList<EntityVoidNPCAIBase> listeners = new ArrayList<EntityVoidNPCAIBase>();
	private ArrayList<Packet> packetSpool = new ArrayList<Packet>();

	/**
	 * Packets are to be sent from the entity to the bus, ALL connected AIs will see and read the packet
	 */
	public void sendPacket(Packet packet) {
		packetSpool.add(packet);
	}

	public void readNextPacket() {
		Packet packet = packetSpool.get(0);
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

	public abstract class Packet {
		
	}

}
