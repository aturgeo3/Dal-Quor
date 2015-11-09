package Tamaized.Voidcraft.Addons.thaumcraft;

import thaumcraft.api.ThaumcraftApi;

public class voidCraftThaum{
	
	private ThaumcraftApi instance;
	public AspectsForItems aspects;
	public VoidCraftSpells spells;
	public VoidCraftThaumRecipes recipes;
	public VoidCraftResearch research;
	
	public voidCraftThaum(){
		
		//Register Aspects on Items
		aspects = new AspectsForItems(instance);
		
		//Register Spells
		//spells = new VoidCraftSpells(instance);
		
		//Register Recipes
		//recipes = new VoidCraftThaumRecipes(instance);
		
		//Research Tab
		//research = new VoidCraftResearch(recipes);
	}
	
}
