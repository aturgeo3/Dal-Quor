package tamaized.dalquor.common.blocks.tileentity;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.wrapper.CombinedInvWrapper;
import tamaized.tammodized.common.tileentity.TamTileEntityInventory;
import tamaized.dalquor.VoidCraft;
import tamaized.dalquor.client.gui.element.GUIListElement;
import tamaized.dalquor.common.capabilities.CapabilityList;
import tamaized.dalquor.common.capabilities.starforge.IStarForgeCapability;
import tamaized.dalquor.common.starforge.IStarForgeTool;
import tamaized.dalquor.common.starforge.StarForgeEffectEntry;
import tamaized.dalquor.common.starforge.StarForgeEffectRecipeList;
import tamaized.dalquor.common.starforge.StarForgeToolEntry;
import tamaized.dalquor.common.starforge.effects.IStarForgeEffect;
import tamaized.dalquor.registry.VoidCraftTools;

import java.util.ArrayList;
import java.util.List;

public class TileEntityStarForge extends TamTileEntityInventory {

	public ItemStackFilterHandler SLOT_INPUT_TOOL;
	public ItemStackFilterHandler SLOT_INPUT_COSMICMATERIAL;
	public ItemStackFilterHandler SLOT_INPUT_DRAGONSCALE;
	public ItemStackFilterHandler SLOT_INPUT_QUORIFRAGMENT;
	public ItemStackFilterHandler SLOT_INPUT_ASTRALESSENCE;
	public ItemStackFilterHandler SLOT_INPUT_VOIDICPHLOG;

	public TileEntityStarForge() {
		super();
	}

	@Override
	protected ItemStackFilterHandler[] register() {
		return new ItemStackFilterHandler[]{

				SLOT_INPUT_TOOL = new ItemStackFilterHandler(new Class[]{IStarForgeTool.class}, true, new Class[]{}, false),

				SLOT_INPUT_COSMICMATERIAL = new ItemStackFilterHandler(new ItemStack[]{new ItemStack(VoidCraft.blocks.cosmicMaterial)}, true, new ItemStack[]{}, false),

				SLOT_INPUT_DRAGONSCALE = new ItemStackFilterHandler(new ItemStack[]{new ItemStack(VoidCraft.items.voidicDragonScale)}, true, new ItemStack[]{}, false),

				SLOT_INPUT_QUORIFRAGMENT = new ItemStackFilterHandler(new ItemStack[]{new ItemStack(VoidCraft.items.quoriFragment)}, true, new ItemStack[]{}, false),

				SLOT_INPUT_ASTRALESSENCE = new ItemStackFilterHandler(new ItemStack[]{new ItemStack(VoidCraft.items.astralEssence)}, true, new ItemStack[]{}, false),

				SLOT_INPUT_VOIDICPHLOG = new ItemStackFilterHandler(new ItemStack[]{new ItemStack(VoidCraft.items.voidicPhlogiston)}, true, new ItemStack[]{}, false)

		};
	}

	@Override
	public void readNBT(NBTTagCompound nbt) {
	}

	@Override
	public NBTTagCompound writeNBT(NBTTagCompound nbt) {
		return nbt;
	}

	public List<GUIListElement> buildPossibleEffectList() {
		List<GUIListElement> list = new ArrayList<>();
		IStarForgeEffect.Type type = null;
		ItemStack stack = SLOT_INPUT_TOOL.getStackInSlot(0);
		if (stack.isEmpty()) {
			try {
				list.add(new StarForgeToolEntry(new ItemStack(VoidCraftTools.starforgedSword)));
				list.add(new StarForgeToolEntry(new ItemStack(VoidCraftTools.starforgedPickaxe)));
				list.add(new StarForgeToolEntry(new ItemStack(VoidCraftTools.starforgedAxe)));
				list.add(new StarForgeToolEntry(new ItemStack(VoidCraftTools.starforgedSpade)));
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			IStarForgeCapability cap = stack.getCapability(CapabilityList.STARFORGE, null);
			if (stack.getItem() instanceof IStarForgeTool)
				type = ((IStarForgeTool) stack.getItem()).getType();
			if (type != null) {
				for (StarForgeEffectRecipeList.Recipe recipe : StarForgeEffectRecipeList.instance.getRecipes(type)) {
					if (cap != null) {
						if (recipe.getEffect().getTier() == IStarForgeEffect.Tier.ONE && cap.getEffect(IStarForgeEffect.Tier.ONE) != null)
							continue;
						if (recipe.getEffect().getTier() == IStarForgeEffect.Tier.TWO && cap.getEffect(IStarForgeEffect.Tier.TWO) != null)
							continue;
						if (recipe.getEffect().getTier() == IStarForgeEffect.Tier.THREE && cap.getEffect(IStarForgeEffect.Tier.THREE) != null)
							continue;
					}
					list.add(new StarForgeEffectEntry(recipe));
				}
			}
		}
		return list;
	}

	@Override
	public void onUpdate() {

	}

	@Override
	public IItemHandler getCap(EnumFacing enumFacing) {
		return new CombinedInvWrapper(SLOT_INPUT_TOOL, SLOT_INPUT_ASTRALESSENCE, SLOT_INPUT_COSMICMATERIAL, SLOT_INPUT_DRAGONSCALE, SLOT_INPUT_QUORIFRAGMENT, SLOT_INPUT_VOIDICPHLOG);
	}
}
