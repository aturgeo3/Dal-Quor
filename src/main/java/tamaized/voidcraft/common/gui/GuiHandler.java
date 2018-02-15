package tamaized.voidcraft.common.gui;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;
import tamaized.voidcraft.client.gui.*;
import tamaized.voidcraft.common.blocks.tileentity.TileEntityStarForge;
import tamaized.voidcraft.common.capabilities.CapabilityList;
import tamaized.voidcraft.common.gui.container.*;

public class GuiHandler implements IGuiHandler {

	public enum Type {
		NULL, Macerator, BlastFurnace, MusicBox, Infuser, Heimdall, VoidicGenerator, VoidicCharger, RealityStabilizer, RealityTeleporterBlock,

		VoidicAlchemy, RealityTeleporter, StarForge, VadeMecumSpells, VoidicAnchor, VoidicCrystallizer
	}

	public static int getTypeID(Type type) {
		return type.ordinal();
	}

	public static Type getTypeFromID(int id) {
		return id > Type.values().length ? Type.NULL : Type.values()[id];
	}

	@Override
	public Object getServerGuiElement(int id, EntityPlayer player, World world, int x, int y, int z) {
		TileEntity tileEntity = world.isBlockLoaded(new BlockPos(x, y, z)) ? world.getTileEntity(new BlockPos(x, y, z)) : null;
		ItemStack main = player.getHeldItemMainhand();
		ItemStack off = player.getHeldItemOffhand();
		switch (getTypeFromID(id)) {
			case StarForge:
				if (tileEntity != null && tileEntity instanceof TileEntityStarForge)
					return new StarForgeContainer(player.inventory, (TileEntityStarForge) tileEntity);
				else
					return null;
			case VadeMecumSpells:
				if (player.hasCapability(CapabilityList.VADEMECUM, null))
					return new VadeMecumSpellsContainer(player.inventory, player.getCapability(CapabilityList.VADEMECUM, null));
				else
					return null;
			default:
				return null;
		}
	}

	@Override
	public Object getClientGuiElement(int id, EntityPlayer player, World world, int x, int y, int z) {
		TileEntity tileEntity = world.getTileEntity(new BlockPos(x, y, z));
		ItemStack main = player.getHeldItemMainhand();
		ItemStack off = player.getHeldItemOffhand();
		switch (getTypeFromID(id)) {
			case StarForge:
				if (tileEntity != null && tileEntity instanceof TileEntityStarForge)
					return new StarForgeGUI(player.inventory, (TileEntityStarForge) tileEntity);
				else
					return null;
			case VadeMecumSpells:
				if (player.hasCapability(CapabilityList.VADEMECUM, null))
					return new VadeMecumSpellsGUI(player.inventory, player.getCapability(CapabilityList.VADEMECUM, null));
				else
					return null;
			default:
				return null;
		}
	}
}