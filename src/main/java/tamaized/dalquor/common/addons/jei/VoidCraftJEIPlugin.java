package tamaized.dalquor.common.addons.jei;

import mezz.jei.api.IJeiHelpers;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.IModRegistry;
import mezz.jei.api.JEIPlugin;
import mezz.jei.api.ingredients.IIngredientRegistry;
import net.minecraft.item.ItemStack;
import tamaized.dalquor.DalQuor;
import tamaized.dalquor.registry.ModItems;

import javax.annotation.Nonnull;

@JEIPlugin
public class VoidCraftJEIPlugin implements IModPlugin {

	public static IJeiHelpers jeiHelpers;

	@Override
	public void register(@Nonnull IModRegistry registry) {
		DalQuor.instance.logger.info("jei detected, loading VoidCraft jei Plugin");
		IIngredientRegistry itemRegistry = registry.getIngredientRegistry();
		jeiHelpers = registry.getJeiHelpers();

		registry.addIngredientInfo(new ItemStack(ModItems.vadeMecum), ItemStack.class, "Toss a book into Void Fire created from an Obsidian Flask");
		registry.addIngredientInfo(new ItemStack(ModItems.obsidianFlask), ItemStack.class, "While at Y level 5 and below, Right Click with an Empty Obsidian Flask in hand");

		registry.addAdvancedGuiHandlers(new VadeMecumSpellAdvancedGuiHandler());

	}

}
