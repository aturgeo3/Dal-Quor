package Tamaized.Voidcraft.Addons.thaumcraft.Spells;


public class SpellAcid {/*extends VoidSpellFocus{
	
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
		return new AspectList().add(aspects.EARTH, 10);
	}
	
	@Override
	public WandFocusAnimation getAnimation(ItemStack focusstack) {
		return WandFocusAnimation.CHARGE;
	}
	
	public ItemStack onFocusRightClick(ItemStack wandstack, World world, EntityPlayer player, MovingObjectPosition movingobjectposition) {
		if(!world.isRemote && ((ItemWandCasting)wandstack.getItem()).consumeAllVis(wandstack, player, getVisCost(wandstack), true, false)) shoot(world, player);
		player.setItemInUse(wandstack, Integer.MAX_VALUE);
		return wandstack;
	}
	
	public void onUsingFocusTick(ItemStack wandstack, EntityPlayer player, int count) {
		if(!player.worldObj.isRemote){
			ItemWandCasting wand = (ItemWandCasting) wandstack.getItem();
			if(wand.consumeAllVis(wandstack, player, getVisCost(wandstack), true, false)) shoot(player.worldObj, player);
		}
	}
	
	private void shoot(World world, EntityPlayer player){
		AcidBall spell = new AcidBall(player.worldObj, player, 1.6F);
		spell.setDamageRangeSpeed(6D, 0.00F, 1.7D);
		world.spawnEntityInWorld(spell);
		//TODO sound effects
	}
	
	@Override
	public int getActivationCooldown(ItemStack focusstack) {
		return 500;
	}
	
	public void onPlayerStoppedUsingFocus(ItemStack wandstack, World world,	EntityPlayer player, int count) {
		// TODO Auto-generated method stub
		
	}
	
	public boolean onFocusBlockStartBreak(ItemStack wandstack, int x, int y,int z, EntityPlayer player) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int getFocusColor(ItemStack focusstack) {
		return 0x00AA00;
	}

	@Override
	public boolean isVisCostPerTick(ItemStack focusstack) {
		return true;
	}
	
*/
}
