package Tamaized.Voidcraft.Addons.thaumcraft.Spells;

import Tamaized.Voidcraft.common.voidCraft;
import Tamaized.Voidcraft.projectiles.AcidBall;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.wands.ItemFocusBasic;

public class SpellAcid extends VoidSpellFocus{
	
	private IIcon itemIcon;
	private Aspect aspects;
	
	public SpellAcid(){
		super();
		this.setTextureName("VoidCraft:Thaumcraft/Spells/Acid");
	}
	
	 @SideOnly(Side.CLIENT)
	    public void registerIcons(IIconRegister p_94581_1_)
	    {
	        this.itemIcon = p_94581_1_.registerIcon(this.getIconString());
	    }
	 
	 @SideOnly(Side.CLIENT)
		@Override
		public IIcon getIconFromDamage(int par1) {
			return itemIcon;
		}

	@Override
	public AspectList getVisCost(ItemStack focusstack) {
		AspectList daAspectList = new AspectList();
		daAspectList.add(aspects.EARTH, 1);
		return daAspectList;
	}
	
	public ItemStack onFocusRightClick(ItemStack wandstack, World world,EntityPlayer player, MovingObjectPosition movingobjectposition) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public void onUsingFocusTick(ItemStack wandstack, EntityPlayer player, int count) { //Never called I guess, try a system.out sometime
		AcidBall spell = new AcidBall(player.worldObj, player, 1.6F);
		spell.setDamage(10.0D);
		player.worldObj.spawnEntityInWorld(spell);
	}
	
	public void onPlayerStoppedUsingFocus(ItemStack wandstack, World world,	EntityPlayer player, int count) {
		// TODO Auto-generated method stub
		
	}
	
	public boolean onFocusBlockStartBreak(ItemStack wandstack, int x, int y,int z, EntityPlayer player) {
		// TODO Auto-generated method stub
		return false;
	}

}
