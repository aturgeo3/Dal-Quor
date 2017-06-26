package Tamaized.Voidcraft.common.gui;

import Tamaized.Voidcraft.VoidCraft;
import Tamaized.Voidcraft.client.gui.HeimdallGUI;
import Tamaized.Voidcraft.client.gui.RealityStabilizerGUI;
import Tamaized.Voidcraft.client.gui.RealityTeleporterBlockGUI;
import Tamaized.Voidcraft.client.gui.RealityTeleporterGUI;
import Tamaized.Voidcraft.client.gui.StarForgeGUI;
import Tamaized.Voidcraft.client.gui.VadeMecumSpellsGUI;
import Tamaized.Voidcraft.client.gui.VoidBlastFurnaceGUI;
import Tamaized.Voidcraft.client.gui.VoidBoxGUI;
import Tamaized.Voidcraft.client.gui.VoidInfuserGUI;
import Tamaized.Voidcraft.client.gui.VoidMaceratorGUI;
import Tamaized.Voidcraft.client.gui.VoidicAlchemyGUI;
import Tamaized.Voidcraft.client.gui.VoidicAnchorGUI;
import Tamaized.Voidcraft.client.gui.VoidicChargerGUI;
import Tamaized.Voidcraft.client.gui.VoidicCrystallizerGUI;
import Tamaized.Voidcraft.client.gui.VoidicPowerGenGUI;
import Tamaized.Voidcraft.common.gui.container.HeimdallContainer;
import Tamaized.Voidcraft.common.gui.container.RealityStabilizerContainer;
import Tamaized.Voidcraft.common.gui.container.RealityTeleporterBlockContainer;
import Tamaized.Voidcraft.common.gui.container.RealityTeleporterContainer;
import Tamaized.Voidcraft.common.gui.container.StarForgeContainer;
import Tamaized.Voidcraft.common.gui.container.VadeMecumSpellsContainer;
import Tamaized.Voidcraft.common.gui.container.VoidBlastFurnaceContainer;
import Tamaized.Voidcraft.common.gui.container.VoidBoxContainer;
import Tamaized.Voidcraft.common.gui.container.VoidInfuserContainer;
import Tamaized.Voidcraft.common.gui.container.VoidMaceratorContainer;
import Tamaized.Voidcraft.common.gui.container.VoidicAlchemyContainer;
import Tamaized.Voidcraft.common.gui.container.VoidicAnchorContainer;
import Tamaized.Voidcraft.common.gui.container.VoidicChargerContainer;
import Tamaized.Voidcraft.common.gui.container.VoidicCrystallizerContainer;
import Tamaized.Voidcraft.common.gui.container.VoidicPowerGenContainer;
import Tamaized.Voidcraft.common.blocks.tileentity.TileEntityStarForge;
import Tamaized.Voidcraft.common.capabilities.CapabilityList;
import Tamaized.Voidcraft.common.machina.tileentity.TileEntityHeimdall;
import Tamaized.Voidcraft.common.machina.tileentity.TileEntityRealityStabilizer;
import Tamaized.Voidcraft.common.machina.tileentity.TileEntityRealityTeleporter;
import Tamaized.Voidcraft.common.machina.tileentity.TileEntityVoidBlastFurnace;
import Tamaized.Voidcraft.common.machina.tileentity.TileEntityVoidBox;
import Tamaized.Voidcraft.common.machina.tileentity.TileEntityVoidInfuser;
import Tamaized.Voidcraft.common.machina.tileentity.TileEntityVoidMacerator;
import Tamaized.Voidcraft.common.machina.tileentity.TileEntityVoidicAlchemy;
import Tamaized.Voidcraft.common.machina.tileentity.TileEntityVoidicAnchor;
import Tamaized.Voidcraft.common.machina.tileentity.TileEntityVoidicCharger;
import Tamaized.Voidcraft.common.machina.tileentity.TileEntityVoidicCrystallizer;
import Tamaized.Voidcraft.common.machina.tileentity.TileEntityVoidicPowerGen;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

public class GuiHandler implements IGuiHandler {

	public static enum Type {
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
		TileEntity tileEntity = world.getTileEntity(new BlockPos(x, y, z));
		ItemStack main = player.getHeldItemMainhand();
		ItemStack off = player.getHeldItemOffhand();
		switch (getTypeFromID(id)) {
			// case VadeMecum:
			// return new VadeMecumContainer(player);
			case Macerator:
				if (tileEntity != null && tileEntity instanceof TileEntityVoidMacerator) return new VoidMaceratorContainer(player.inventory, (TileEntityVoidMacerator) tileEntity);
				else return null;
			case BlastFurnace:
				if (tileEntity != null && tileEntity instanceof TileEntityVoidBlastFurnace) return new VoidBlastFurnaceContainer(player.inventory, (TileEntityVoidBlastFurnace) tileEntity);
				else return null;
			case MusicBox:
				if (tileEntity != null && tileEntity instanceof TileEntityVoidBox) return new VoidBoxContainer(player.inventory, (TileEntityVoidBox) tileEntity);
				else return null;
			case Infuser:
				if (tileEntity != null && tileEntity instanceof TileEntityVoidInfuser) return new VoidInfuserContainer(player.inventory, (TileEntityVoidInfuser) tileEntity);
				else return null;
			case Heimdall:
				if (tileEntity != null && tileEntity instanceof TileEntityHeimdall) return new HeimdallContainer(player.inventory, (TileEntityHeimdall) tileEntity);
				else return null;
			case VoidicGenerator:
				if (tileEntity != null && tileEntity instanceof TileEntityVoidicPowerGen) return new VoidicPowerGenContainer(player.inventory, (TileEntityVoidicPowerGen) tileEntity);
				else return null;
			case VoidicCharger:
				if (tileEntity != null && tileEntity instanceof TileEntityVoidicCharger) return new VoidicChargerContainer(player.inventory, (TileEntityVoidicCharger) tileEntity);
				else return null;
			case RealityStabilizer:
				if (tileEntity != null && tileEntity instanceof TileEntityRealityStabilizer) return new RealityStabilizerContainer(player.inventory, (TileEntityRealityStabilizer) tileEntity);
				else return null;
			case RealityTeleporterBlock:
				if (tileEntity != null && tileEntity instanceof TileEntityRealityTeleporter) return new RealityTeleporterBlockContainer(player.inventory, (TileEntityRealityTeleporter) tileEntity);
				else return null;
			case VoidicAlchemy:
				if (tileEntity != null && tileEntity instanceof TileEntityVoidicAlchemy) return new VoidicAlchemyContainer(player.inventory, (TileEntityVoidicAlchemy) tileEntity);
				else return null;
			case RealityTeleporter:
				if (!main.isEmpty() && main.getItem() == VoidCraft.items.realityTeleporter) return new RealityTeleporterContainer(player.inventory, main);
				else if (!off.isEmpty() && off.getItem() == VoidCraft.items.realityTeleporter) return new RealityTeleporterContainer(player.inventory, off);
				else return null;
			case StarForge:
				if (tileEntity != null && tileEntity instanceof TileEntityStarForge) return new StarForgeContainer(player.inventory, (TileEntityStarForge) tileEntity);
				else return null;
			case VadeMecumSpells:
				if (player.hasCapability(CapabilityList.VADEMECUM, null)) return new VadeMecumSpellsContainer(player.inventory, player.getCapability(CapabilityList.VADEMECUM, null));
				else return null;
			case VoidicAnchor:
				if (tileEntity != null && tileEntity instanceof TileEntityVoidicAnchor) return new VoidicAnchorContainer(player.inventory, (TileEntityVoidicAnchor) tileEntity);
				else return null;
			case VoidicCrystallizer:
				if (tileEntity != null && tileEntity instanceof TileEntityVoidicCrystallizer) return new VoidicCrystallizerContainer(player.inventory, (TileEntityVoidicCrystallizer) tileEntity);
				else return null;
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
			// case VadeMecum:
			// return new VadeMecumGUI(player);
			case Macerator:
				if (tileEntity != null && tileEntity instanceof TileEntityVoidMacerator) return new VoidMaceratorGUI(player.inventory, (TileEntityVoidMacerator) tileEntity);
				else return null;
			case BlastFurnace:
				if (tileEntity != null && tileEntity instanceof TileEntityVoidBlastFurnace) return new VoidBlastFurnaceGUI(player.inventory, (TileEntityVoidBlastFurnace) tileEntity);
				else return null;
			case MusicBox:
				if (tileEntity != null && tileEntity instanceof TileEntityVoidBox) return new VoidBoxGUI(player.inventory, (TileEntityVoidBox) tileEntity);
				else return null;
			case Infuser:
				if (tileEntity != null && tileEntity instanceof TileEntityVoidInfuser) return new VoidInfuserGUI(player.inventory, (TileEntityVoidInfuser) tileEntity);
				else return null;
			case Heimdall:
				if (tileEntity != null && tileEntity instanceof TileEntityHeimdall) return new HeimdallGUI(player.inventory, (TileEntityHeimdall) tileEntity);
				else return null;
			case VoidicGenerator:
				if (tileEntity != null && tileEntity instanceof TileEntityVoidicPowerGen) return new VoidicPowerGenGUI(player.inventory, (TileEntityVoidicPowerGen) tileEntity);
				else return null;
			case VoidicCharger:
				if (tileEntity != null && tileEntity instanceof TileEntityVoidicCharger) return new VoidicChargerGUI(player.inventory, (TileEntityVoidicCharger) tileEntity);
				else return null;
			case RealityStabilizer:
				if (tileEntity != null && tileEntity instanceof TileEntityRealityStabilizer) return new RealityStabilizerGUI(player.inventory, (TileEntityRealityStabilizer) tileEntity);
				else return null;
			case RealityTeleporterBlock:
				if (tileEntity != null && tileEntity instanceof TileEntityRealityTeleporter) return new RealityTeleporterBlockGUI(player.inventory, (TileEntityRealityTeleporter) tileEntity);
				else return null;
			case VoidicAlchemy:
				if (tileEntity != null && tileEntity instanceof TileEntityVoidicAlchemy) return new VoidicAlchemyGUI(player.inventory, (TileEntityVoidicAlchemy) tileEntity);
				else return null;
			case RealityTeleporter:
				if (!main.isEmpty() && main.getItem() == VoidCraft.items.realityTeleporter) return new RealityTeleporterGUI(player.inventory, main);
				else if (!off.isEmpty() && off.getItem() == VoidCraft.items.realityTeleporter) return new RealityTeleporterGUI(player.inventory, off);
				else return null;
			case StarForge:
				if (tileEntity != null && tileEntity instanceof TileEntityStarForge) return new StarForgeGUI(player.inventory, (TileEntityStarForge) tileEntity);
				else return null;
			case VadeMecumSpells:
				if (player.hasCapability(CapabilityList.VADEMECUM, null)) return new VadeMecumSpellsGUI(player.inventory, player.getCapability(CapabilityList.VADEMECUM, null));
				else return null;
			case VoidicAnchor:
				if (tileEntity != null && tileEntity instanceof TileEntityVoidicAnchor) return new VoidicAnchorGUI(player.inventory, (TileEntityVoidicAnchor) tileEntity);
				else return null;
			case VoidicCrystallizer:
				if (tileEntity != null && tileEntity instanceof TileEntityVoidicCrystallizer) return new VoidicCrystallizerGUI(player.inventory, (TileEntityVoidicCrystallizer) tileEntity);
				else return null;
			default:
				return null;
		}
	}
}