package Tamaized.Voidcraft.Addons.thaumcraft.Spells;

import net.minecraft.item.ItemStack;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.wands.ItemFocusBasic;

public class VoidSpellFocus extends ItemFocusBasic{
	
	public String myName;
	
	public void setMyName(String s){
		myName = s;
	}
	
	public String getMyName(){
		return myName;
	}
	
	/**
	 * How much vis does this focus consume per activation. 
	 */
	public AspectList getVisCost(ItemStack focusstack){return null;};

}
