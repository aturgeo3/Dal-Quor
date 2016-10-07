package Tamaized.Voidcraft.GUI.server;

import net.minecraft.entity.player.EntityPlayer;

public class VadeMecumContainer extends ContainerBase {

	private final EntityPlayer player;

	public VadeMecumContainer(EntityPlayer player) {
		this.player = player;
	}

	@Override
	public boolean canInteractWith(EntityPlayer playerIn) {
		return true;
	}

}
