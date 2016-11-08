package Tamaized.Voidcraft.vadeMecum.contents.documentation.machines.teleporter;

import Tamaized.Voidcraft.voidCraft;
import Tamaized.Voidcraft.capabilities.vadeMecum.IVadeMecumCapability;
import Tamaized.Voidcraft.vadeMecum.IVadeMecumPage;
import Tamaized.Voidcraft.vadeMecum.IVadeMecumPageProvider;
import Tamaized.Voidcraft.vadeMecum.VadeMecumCraftingNormal;
import Tamaized.Voidcraft.vadeMecum.VadeMecumPage;
import Tamaized.Voidcraft.vadeMecum.VadeMecumPageCrafting;
import net.minecraft.item.ItemStack;

public class VadeMecumPageListTeleporter implements IVadeMecumPageProvider {

	public IVadeMecumPage[] getPageList(IVadeMecumCapability cap) {
		return new IVadeMecumPage[] {
				new VadeMecumPage("Reality Teleportation Block", "It is known that stepping into a Hole in Reality sends you to a random location as well as a random dimension. We also know we can collect these holes by stabilizing them with the Reality Stabilizer. With all of this information we can build ourselves a device that can use the power of these holes in reality for controlled teleportation."),
				new VadeMecumPage("", "To actually use the Reality Teleportation Block, you need at least two. Once you have two or more placed down you'll need to link them together, you do this by shift right clicking on the destination point (This will be the reality teleportation block you want to be sent to) with a Reality Teleporter item in hand. This will save the location to the item, you then need to shift right click on the block you wish to teleport from."),
				new VadeMecumPage("", "To clear the location from the item, open the GUI up and click on the button that says \"Clear Link\" this will clear the known link allowing you to create another link. Two teleporters will not automatically be linked both ways upon just linking one, you need to link both at different times. Just remember the first save is the destination point."),
				new VadeMecumPage("", "This will allow you to have multiple points linking to a single destination point as well as multiple point teleportation such as point A leads to point B, point B leads to point C, point C leads to point A, and so on. Both the point you are teleporting from and the destination point require at least 2000 Voidic Power and one Hole in Reality, these will get consumed when teleporting. All that's left is a redstone signal pulse to the block."),
				new VadeMecumPageCrafting(new VadeMecumCraftingNormal("Recipe", new ItemStack[] {
						new ItemStack(voidCraft.blocks.blockVoidbrick),
						new ItemStack(voidCraft.items.emeraldDust),
						new ItemStack(voidCraft.blocks.blockVoidbrick),
						new ItemStack(voidCraft.items.voidCloth),
						new ItemStack(voidCraft.blocks.realityHole),
						new ItemStack(voidCraft.items.voidCloth),
						new ItemStack(voidCraft.blocks.blockVoidbrick),
						new ItemStack(voidCraft.blocks.voidicCharger),
						new ItemStack(voidCraft.blocks.blockVoidbrick) }, new ItemStack(voidCraft.blocks.realityTeleporterBlock, 1))) };
	}

}
