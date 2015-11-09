package Tamaized.Voidcraft.Addons.thaumcraft;

import net.minecraft.item.ItemStack;
import thaumcraft.api.ThaumcraftApi;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import Tamaized.Voidcraft.common.voidCraft;

public class AspectsForItems {
	
	private Aspect aspects;
	private AspectList daAspectList;
	
	public AspectsForItems(ThaumcraftApi instance){
		
		
		daAspectList = new AspectList();
		daAspectList.add(aspects.VOID, 4);
		daAspectList.add(aspects.CRYSTAL, 4);
		instance.registerObjectTag(new ItemStack(voidCraft.voidcrystal), daAspectList);
		
		daAspectList = new AspectList();
		daAspectList.add(aspects.AURA, 4);
		daAspectList.add(aspects.AIR, 4);
		daAspectList.add(aspects.COLD, 4);
		daAspectList.add(aspects.SOUL, 4);
		instance.registerObjectTag(new ItemStack(voidCraft.ectoplasm), daAspectList);
		
		daAspectList = new AspectList();
		daAspectList.add(aspects.VOID, 4);
		daAspectList.add(aspects.METAL, 4);
		daAspectList.add(aspects.TRAP, 4);
		daAspectList.add(aspects.ELDRITCH, 4);
		instance.registerObjectTag(new ItemStack(voidCraft.voidChain), daAspectList);
		
		daAspectList = new AspectList();
		daAspectList.add(aspects.FIRE, 4);
		daAspectList.add(aspects.DARKNESS, 4);
		daAspectList.add(aspects.DEATH, 4);
		daAspectList.add(aspects.ELDRITCH, 4);
		instance.registerObjectTag(new ItemStack(voidCraft.burnBone), daAspectList);
		
		daAspectList = new AspectList();
		daAspectList.add(aspects.FIRE, 4);
		daAspectList.add(aspects.METAL, 4);
		instance.registerObjectTag(new ItemStack(voidCraft.MoltenvoidChainPart), daAspectList);
		
		daAspectList = new AspectList();
		daAspectList.add(aspects.FIRE, 4);
		daAspectList.add(aspects.METAL, 4);
		daAspectList.add(aspects.ELDRITCH, 4);
		daAspectList.add(aspects.TRAP, 4);
		instance.registerObjectTag(new ItemStack(voidCraft.MoltenvoidChain), daAspectList);
		
		daAspectList = new AspectList();
		daAspectList.add(aspects.MAGIC, 4);
		daAspectList.add(aspects.VOID, 4);
		daAspectList.add(aspects.ELDRITCH, 4);
		daAspectList.add(aspects.CLOTH, 4);
		instance.registerObjectTag(new ItemStack(voidCraft.voidCloth), daAspectList);
		
		daAspectList = new AspectList();
		daAspectList.add(aspects.ENTROPY, 4);
		daAspectList.add(aspects.VOID, 4);
		daAspectList.add(aspects.ELDRITCH, 4);
		daAspectList.add(aspects.DARKNESS, 4);
		instance.registerObjectTag(new ItemStack(voidCraft.voidStar), daAspectList);
	}

}
