package Tamaized.Voidcraft.registry;

import java.util.ArrayList;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import Tamaized.TamModized.registry.ITamModel;
import Tamaized.TamModized.registry.ITamRegistry;
import Tamaized.Voidcraft.voidCraft;
import Tamaized.Voidcraft.machina.addons.TERecipeInfuser;
import Tamaized.Voidcraft.machina.addons.TERecipesMacerator;

public class VoidCraftTERecipes implements ITamRegistry {
	
	public static TERecipesMacerator macerator;
	public static TERecipeInfuser infuser;

	@Override
	public void preInit() {
		macerator = new TERecipesMacerator();
		infuser = new TERecipeInfuser();
	}

	@Override
	public void init() {
		macerator.registerRecipe("oreCoal", macerator.new MaceratorRecipe(new ItemStack(voidCraft.items.coalDust, 8), 200));
		macerator.registerRecipe("oreQuartz", macerator.new MaceratorRecipe(new ItemStack(voidCraft.items.quartzDust, 4), 200));
		macerator.registerRecipe("oreIron", macerator.new MaceratorRecipe(new ItemStack(voidCraft.items.ironDust, 4), 200));
		macerator.registerRecipe("oreGold", macerator.new MaceratorRecipe(new ItemStack(voidCraft.items.goldDust, 4), 200));
		macerator.registerRecipe("oreCopper", macerator.new MaceratorRecipe(new ItemStack(voidCraft.items.copperDust, 4), 200));
		macerator.registerRecipe("oreTin", macerator.new MaceratorRecipe(new ItemStack(voidCraft.items.tinDust, 4), 200));
		macerator.registerRecipe("oreLead", macerator.new MaceratorRecipe(new ItemStack(voidCraft.items.leadDust, 4), 200));
		macerator.registerRecipe("oreLapis", macerator.new MaceratorRecipe(new ItemStack(voidCraft.items.lapisDust, 8), 200));
		macerator.registerRecipe("oreEmerald", macerator.new MaceratorRecipe(new ItemStack(voidCraft.items.emeraldDust, 4), 200));
		macerator.registerRecipe("oreDiamond", macerator.new MaceratorRecipe(new ItemStack(voidCraft.items.diamondDust, 4), 200));

		macerator.registerRecipe(new ItemStack(Items.COAL, 1), macerator.new MaceratorRecipe(new ItemStack(voidCraft.items.coalDust, 4), 200));
		macerator.registerRecipe("gemQuartz", macerator.new MaceratorRecipe(new ItemStack(voidCraft.items.quartzDust, 1), 200));
		macerator.registerRecipe("ingotIron", macerator.new MaceratorRecipe(new ItemStack(voidCraft.items.ironDust, 1), 200));
		macerator.registerRecipe("ingotGold", macerator.new MaceratorRecipe(new ItemStack(voidCraft.items.goldDust, 1), 200));
		macerator.registerRecipe("ingotCopper", macerator.new MaceratorRecipe(new ItemStack(voidCraft.items.copperDust, 1), 200));
		macerator.registerRecipe("ingotTin", macerator.new MaceratorRecipe(new ItemStack(voidCraft.items.tinDust, 1), 200));
		macerator.registerRecipe("ingotLead", macerator.new MaceratorRecipe(new ItemStack(voidCraft.items.leadDust, 1), 200));
		macerator.registerRecipe("gemLapis", macerator.new MaceratorRecipe(new ItemStack(voidCraft.items.lapisDust, 1), 200));
		macerator.registerRecipe("gemEmerald", macerator.new MaceratorRecipe(new ItemStack(voidCraft.items.emeraldDust, 1), 200));
		macerator.registerRecipe("gemDiamond", macerator.new MaceratorRecipe(new ItemStack(voidCraft.items.diamondDust, 1), 200));
		
		infuser.registerRecipe(new ItemStack(Blocks.FURNACE), infuser.new InfuserRecipe(new ItemStack(voidCraft.blocks.voidMacerator), 1000));
		infuser.registerRecipe(new ItemStack(voidCraft.tools.archSword), infuser.new InfuserRecipe(new ItemStack(voidCraft.tools.demonSword), 1000));
		infuser.registerRecipe(new ItemStack(Blocks.BEACON), infuser.new InfuserRecipe(new ItemStack(voidCraft.blocks.Heimdall), 1000));
	}

	@Override
	public void postInit() {
		
	}

	@Override
	public ArrayList<ITamModel> getModelList() {
		return new ArrayList<ITamModel>();
	}

	@Override
	public String getModID() {
		return voidCraft.modid;
	}

	@Override
	public void clientPreInit() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void clientInit() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void clientPostInit() {
		// TODO Auto-generated method stub
		
	}

}
