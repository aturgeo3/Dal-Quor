package Tamaized.Voidcraft.Addons.NEI;

import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import Tamaized.Voidcraft.common.voidCraft;
import Tamaized.Voidcraft.machina.addons.InfuserRecipes;
import Tamaized.Voidcraft.machina.tileentity.TileEntityVoidInfuser;
import Tamaized.Voidcraft.GUI.client.voidInfuserGUI;
import net.minecraft.block.Block;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.gui.inventory.GuiFurnace;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.Container;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraft.util.ResourceLocation;
import codechicken.nei.ItemList;
import codechicken.nei.NEIClientUtils;
import codechicken.nei.NEIServerUtils;
import codechicken.nei.PositionedStack;
import codechicken.nei.api.IOverlayHandler;
import codechicken.nei.api.IRecipeOverlayRenderer;
import codechicken.nei.recipe.FurnaceRecipeHandler;
import codechicken.nei.recipe.GuiRecipe;
import codechicken.nei.recipe.ICraftingHandler;
import codechicken.nei.recipe.TemplateRecipeHandler;
import codechicken.nei.recipe.FurnaceRecipeHandler.FuelPair;
import codechicken.nei.recipe.FurnaceRecipeHandler.SmeltingPair;
import codechicken.nei.recipe.TemplateRecipeHandler.CachedRecipe;
import codechicken.nei.recipe.TemplateRecipeHandler.RecipeTransferRect;

public class VoidInfuserHandler extends TemplateRecipeHandler {

	@Override
	public String getRecipeName() {
		return "Void Infuser";
	}

	@Override
	public String getGuiTexture() {
		return new ResourceLocation(voidCraft.modid, "textures/gui/voidInfuser.png").toString();
	}

	
	
	public class SmeltingPair extends CachedRecipe
    {
        public SmeltingPair(ItemStack ingred, ItemStack result) {
            ingred.stackSize = 1;
            this.ingred = new PositionedStack(ingred, 85, 23);
            this.result = new PositionedStack(result, 142, 24);
        }

        public List<PositionedStack> getIngredients() {
            return getCycledIngredients(cycleticks / 48, Arrays.asList(ingred));
        }

        public PositionedStack getResult() {
            return result;
        }

        public PositionedStack getOtherStack() {
            return afuels.get((cycleticks / 48) % afuels.size()).stack;
        }

        PositionedStack ingred;
        PositionedStack result;
    }

    public static class FuelPair
    {
        public FuelPair(ItemStack ingred, int burnTime) {
            this.stack = new PositionedStack(ingred, 47, 23, false);
            this.burnTime = burnTime;
        }

        public PositionedStack stack;
        public int burnTime;
    }

    public static ArrayList<FuelPair> afuels;
    public static HashSet<Block> efuels;

    @Override
    public void loadTransferRects() {
        transferRects.add(new RecipeTransferRect(new Rectangle(105, 20, 25, 20), "Void Infuser"));
    }

    @Override
    public Class<? extends GuiContainer> getGuiClass() {
        return voidInfuserGUI.class;
    }

   

    @Override
    public TemplateRecipeHandler newInstance() {
        if (afuels == null)
            findFuels();
        return super.newInstance();
    }

    @Override
    public void loadCraftingRecipes(String outputId, Object... results) {
        if (outputId.equals("Void Infuser") && getClass() == VoidInfuserHandler.class) {//don't want subclasses getting a hold of this
            Map<Item, ItemStack> recipes = (Map<Item, ItemStack>) InfuserRecipes.smelting().getSmeltingList();
            for (Entry<Item, ItemStack> recipe : recipes.entrySet())
                arecipes.add(new SmeltingPair(new ItemStack(recipe.getKey()), recipe.getValue()));
        } else
            super.loadCraftingRecipes(outputId, results);
    }

    @Override
    public void loadCraftingRecipes(ItemStack result) {
        Map<Item, ItemStack> recipes = (Map<Item, ItemStack>) InfuserRecipes.smelting().getSmeltingList();
        for (Entry<Item, ItemStack> recipe : recipes.entrySet())
            if (NEIServerUtils.areStacksSameType(recipe.getValue(), result))
                arecipes.add(new SmeltingPair(new ItemStack(recipe.getKey()), recipe.getValue()));
    }

    @Override
    public void loadUsageRecipes(String inputId, Object... ingredients) {
        if (inputId.equals("fuel") && getClass() == VoidInfuserHandler.class)//don't want subclasses getting a hold of this
            loadCraftingRecipes("smelting");
        else
            super.loadUsageRecipes(inputId, ingredients);
    }

    @Override
    public void loadUsageRecipes(ItemStack ingredient) {
        Map<Item, ItemStack> recipes = (Map<Item, ItemStack>) InfuserRecipes.smelting().getSmeltingList();
        for (Entry<Item, ItemStack> recipe : recipes.entrySet())
            if (NEIServerUtils.areStacksSameTypeCrafting(new ItemStack(recipe.getKey()), ingredient)) {
                SmeltingPair arecipe = new SmeltingPair(new ItemStack(recipe.getKey()), recipe.getValue());
                arecipe.setIngredientPermutation(Arrays.asList(arecipe.ingred), ingredient);
                arecipes.add(arecipe);
            }
    }

    

    @Override
    public void drawExtras(int recipe) {
    	drawProgressBar(105, 23, 0, 177, 24, 17, 80, 0);
    	drawProgressBar(10, 8, 0, 195, 24, 50, 120, 7);
        //drawProgressBar(74, 23, 176, 14, 24, 16, 48, 0);
    }

    private static Set<Item> excludedFuels() {
        Set<Item> efuels = new HashSet<Item>();
        efuels.add(Item.getItemFromBlock(Blocks.brown_mushroom));
        efuels.add(Item.getItemFromBlock(Blocks.red_mushroom));
        efuels.add(Item.getItemFromBlock(Blocks.standing_sign));
        efuels.add(Item.getItemFromBlock(Blocks.wall_sign));
        efuels.add(Item.getItemFromBlock(Blocks.wooden_door));
        efuels.add(Item.getItemFromBlock(Blocks.trapped_chest));
        return efuels;
    }

    private static void findFuels() {
        afuels = new ArrayList<FuelPair>();
        Set<Item> efuels = excludedFuels();
        for (ItemStack item : ItemList.items)
            if (!efuels.contains(item.getItem())) {
                int burnTime = TileEntityVoidInfuser.getItemBurnTime(item);
                if (burnTime > 0)
                    afuels.add(new FuelPair(item.copy(), burnTime));
            }
    }

    @Override
    public String getOverlayIdentifier() {
        return "Void Infuser";
    }
	

}
