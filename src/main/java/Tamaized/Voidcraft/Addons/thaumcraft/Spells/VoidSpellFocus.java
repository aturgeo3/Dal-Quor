package Tamaized.Voidcraft.Addons.thaumcraft.Spells;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.wands.ItemFocusBasic;
import thaumcraft.api.wands.ItemFocusBasic.WandFocusAnimation;

public abstract class VoidSpellFocus extends ItemFocusBasic {

	public abstract void registerIcons(IIconRegister p_94581_1_);
	
	public abstract IIcon getIconFromDamage(int par1);

	/**
	 * How much vis does this focus consume per activation.
	 */
	public abstract AspectList getVisCost(ItemStack focusstack);
	
	public abstract boolean isVisCostPerTick(ItemStack focusstack);
	
	public abstract WandFocusAnimation getAnimation(ItemStack focusstack);
	
	public abstract ItemStack onFocusRightClick(ItemStack wandstack, World world,EntityPlayer player, MovingObjectPosition movingobjectposition);
	
	public abstract void onUsingFocusTick(ItemStack wandstack, EntityPlayer player, int count);
	
	public abstract int getActivationCooldown(ItemStack focusstack);
	
	public abstract void onPlayerStoppedUsingFocus(ItemStack wandstack, World world, EntityPlayer player, int count);
	
	public abstract boolean onFocusBlockStartBreak(ItemStack wandstack, int x, int y,int z, EntityPlayer player);
	
	public abstract int getFocusColor(ItemStack focusstack);

}
