package Tamaized.Voidcraft.items;

import Tamaized.Voidcraft.power.VoidicPowerItem;

public class VoidicSuppressor extends VoidicPowerItem {

	public VoidicSuppressor(String n) {
		super(n);
	}

	@Override
	protected int getDefaultVoidicPower() {
		return 0;
	}

	@Override
	protected int getDefaultMaxVoidicPower() {
		return 2000;
	}

}
