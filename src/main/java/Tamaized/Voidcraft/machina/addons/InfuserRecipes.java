package Tamaized.Voidcraft.machina.addons;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;
import Tamaized.Voidcraft.common.voidCraft;

public class InfuserRecipes {
	
	private static final InfuserRecipes smeltingBase = new InfuserRecipes();

    /** The list of Macerator results. */
    private Map smeltingList = new HashMap();
    private Map experienceList = new HashMap();
    private HashMap<List<ItemStack>, ItemStack> metaSmeltingList = new HashMap<List<ItemStack>, ItemStack>();
    private HashMap<List<Integer>, Float> metaExperience = new HashMap<List<Integer>, Float>();

    /**
     * Used to call methods addSmelting and getSmeltingResult.
     */
    public static final InfuserRecipes smelting(){
        return smeltingBase;
    }

    private InfuserRecipes(){
    	this.addSmelting(new ItemStack(Blocks.furnace).getItem(), new ItemStack(voidCraft.blocks.voidMacerator), 0);
    	this.addSmelting(new ItemStack(voidCraft.tools.archSword).getItem(), new ItemStack(voidCraft.tools.demonSword), 0);
    	this.addSmelting(new ItemStack(Blocks.beacon).getItem(), new ItemStack(voidCraft.blocks.Heimdall), 0);
    }
    
    private void addPreSmelting(String s, Item i){
    	for(ItemStack ore : OreDictionary.getOres(s)){
    		if(ore != null){
    			if(ore.getItemDamage() != -1 || ore.getItemDamage() != OreDictionary.WILDCARD_VALUE){
    				this.addSmelting(ore.getItem(), new ItemStack(i), ore.getItemDamage());
    			}else{
    				this.addSmelting(ore.getItem(), new ItemStack(i), ore.getItemDamage());
    			}
    		}
    	}
    }

    /**
     * Adds a smelting recipe.
     */
    public void addSmelting(Item coal, ItemStack par2ItemStack, float par3){
        this.smeltingList.put(coal, par2ItemStack);
        this.experienceList.put(par2ItemStack, Float.valueOf(par3));
    }

    public Map getSmeltingList(){
        return this.smeltingList;
    }

    /**
     * A metadata sensitive version of adding a furnace recipe.
     */
    public void addSmelting(List<ItemStack> itemID, int metadata, ItemStack itemstack, float experience){	
        metaSmeltingList.put(itemID, itemstack);
        metaExperience.put(Arrays.asList(itemstack.getItem().getIdFromItem(itemstack.getItem()), itemstack.getItemDamage()), experience);
    }

    /**
     * Used to get the resulting ItemStack form a source ItemStack
     * @param item The Source ItemStack
     * @return The result ItemStack
     */
    public ItemStack getSmeltingResult(ItemStack item) {
    	if (item == null){
    		return null;
    	}
    	return (ItemStack)smeltingList.get(item.getItem());
    }

    /**
     * Grabs the amount of base experience for this item to give when pulled from the furnace slot.
     */
    public float getExperience(ItemStack item){
    	if (item == null || item.getItem() == null){
    		return 0;
    	}
        float ret = item.getItem().getSmeltingExperience(item);
        if (ret < 0 && metaExperience.containsKey(Arrays.asList(item, item.getItemDamage()))){
            ret = metaExperience.get(Arrays.asList(item, item.getItemDamage()));
        }
        if (ret < 0 && experienceList.containsKey(item)){
            ret = ((Float)experienceList.get(item)).floatValue();
        }
        return (ret < 0 ? 0 : ret);
    }

    public Map<List<ItemStack>, ItemStack> getMetaInscribingList(){
        return metaSmeltingList;
    }
}
