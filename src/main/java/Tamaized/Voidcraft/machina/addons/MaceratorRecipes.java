package Tamaized.Voidcraft.machina.addons;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;
import Tamaized.Voidcraft.common.voidCraft;

public class MaceratorRecipes {
	
	private static final MaceratorRecipes smeltingBase = new MaceratorRecipes();

    /** The list of Macerator results. */
    private Map smeltingList = new HashMap<String, ItemStack>();
    private Map smeltingList4NEI = new HashMap<ItemStack, ItemStack>();
    private Map experienceList = new HashMap();
    private HashMap<List<ItemStack>, ItemStack> metaSmeltingList = new HashMap<List<ItemStack>, ItemStack>();
    private HashMap<List<Integer>, Float> metaExperience = new HashMap<List<Integer>, Float>();

    /**
     * Used to call methods addSmelting and getSmeltingResult.
     */
    public static final MaceratorRecipes smelting()
    {
        return smeltingBase;
    }

    private MaceratorRecipes()
    {
    	//HardCode
    	Map<ItemStack, ItemStack> hcList = voidCraft.maceratorList.getHardCodeList();
    	for (Map.Entry<ItemStack, ItemStack> entry : hcList.entrySet()) {
    	    this.addSmelting(entry.getKey().getItem().toString().concat(":"+entry.getKey().getItemDamage()), entry.getValue(), 0);
    	    this.smeltingList4NEI.put(entry.getKey(), entry.getValue());
    	}
    	
    	//OreDict
    	Map<String, ItemStack> odList = voidCraft.maceratorList.getOreDictList();
    	for (Map.Entry<String, ItemStack> entry : odList.entrySet()) {
    	    this.addPreSmelting(entry.getKey(), entry.getValue());
    	}
    }
    
    public void addPreSmelting(String s, ItemStack i){
    	for(ItemStack ore : OreDictionary.getOres(s))
    	{
    	      if(ore != null)
    	      {
    	         if(ore.getItemDamage() != -1 || ore.getItemDamage() != OreDictionary.WILDCARD_VALUE)
    	         {
    	               this.addSmelting(ore.getItem().toString().concat(":"+ore.getItemDamage()), i, 0);
    	               this.smeltingList4NEI.put(ore, i);
    	         }
    	         else
    	         {
    	               this.addSmelting(ore.getItem().toString().concat(":"+ore.getItemDamage()), i, 0);
    	               this.smeltingList4NEI.put(ore, i);
    	         }
    	      }
    	}
    }

    /**
     * Adds a smelting recipe.
     */
    public void addSmelting(String coal, ItemStack par2ItemStack, float par3)
    {
        this.smeltingList.put(coal, par2ItemStack);
        this.experienceList.put(par2ItemStack, Float.valueOf(par3));
    }

    /**
     * Returns the smelting result of an item.
     * Deprecated in favor of a metadata sensitive version
     */
    @Deprecated
    public ItemStack getSmeltingResult(int par1)
    {
        return (ItemStack) this.smeltingList.get(Integer.valueOf(par1));
    }

    public Map getSmeltingList()
    {
        return this.smeltingList4NEI;
    }

    @Deprecated //In favor of ItemStack sensitive version
    public float getExperience(int par1)
    {
        return this.experienceList.containsKey(Integer.valueOf(par1)) ? ((Float)this.experienceList.get(Integer.valueOf(par1))).floatValue() : 0.0F;
    }

    /**
     * A metadata sensitive version of adding a furnace recipe.
     */
    public void addSmelting(List<ItemStack> itemID, int metadata, ItemStack itemstack, float experience)
    {
    	
        metaSmeltingList.put(itemID, itemstack);
        metaExperience.put(Arrays.asList(itemstack.getItem().getIdFromItem(itemstack.getItem()), itemstack.getItemDamage()), experience);
    }

    /**
     * Used to get the resulting ItemStack form a source ItemStack
     * @param item The Source ItemStack
     * @return The result ItemStack
     */
    public ItemStack getSmeltingResult(ItemStack item) 
    {
        if (item == null)
        {
            return null;
        }
        
        ItemStack ret = (ItemStack)smeltingList.get(item.getItem().toString().concat(":"+item.getItemDamage()));
        if (ret != null) 
        {
            return ret;
        }else{
        	return null;
        }
        
        
    }

    /**
     * Grabs the amount of base experience for this item to give when pulled from the furnace slot.
     */
    public float getExperience(ItemStack item)
    {
        if (item == null || item.getItem() == null)
        {
            return 0;
        }
        float ret = item.getItem().getSmeltingExperience(item);
        if (ret < 0 && metaExperience.containsKey(Arrays.asList(item, item.getItemDamage())))
        {
            ret = metaExperience.get(Arrays.asList(item, item.getItemDamage()));
        }
        if (ret < 0 && experienceList.containsKey(item))
        {
            ret = ((Float)experienceList.get(item)).floatValue();
        }
        return (ret < 0 ? 0 : ret);
    }

    public Map<List<ItemStack>, ItemStack> getMetaInscribingList()
    {
        return metaSmeltingList;
    }
}
