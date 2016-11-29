package Tamaized.Voidcraft.items;

import java.util.List;

import Tamaized.TamModized.items.TamItem;
import Tamaized.Voidcraft.entity.boss.xia.EntityBossXia2;
import Tamaized.Voidcraft.world.SchematicLoader;
import Tamaized.Voidcraft.xiaCastle.logic.battle.Xia2.Xia2BattleHandler;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class Debugger extends TamItem {

	public Debugger(CreativeTabs tab, String n, int maxStackSize) {
		super(tab, n, maxStackSize);
	}

	SchematicLoader sut = new SchematicLoader();

	/**
	 * Called when a Block is right-clicked with this Item
	 */
	@Override
	public EnumActionResult onItemUse(EntityPlayer playerIn, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		if (!worldIn.isRemote) {
			// worldIn.spawnEntityInWorld(new EntityLichInferno(worldIn, pos, 6));
			// EntityGhostPlayerBase entity = EntityGhostPlayerBase.newInstance(worldIn, PlayerNameAlias.Soaryn);
			// EntityHerobrineCreeper entity = new EntityHerobrineCreeper(worldIn);
			// entity.setPositionAndRotation(pos.getX() + 0.5, pos.getY() + 1, pos.getZ() + 0.5, 0, 0);
			// entity.rotationYawHead = entity.rotationYaw = entity.prevRotationYaw = entity.prevRotationYawHead = entity.prevRenderYawOffset = entity.renderYawOffset = 90;
			EntityBossXia2 entity = new EntityBossXia2(worldIn, new Xia2BattleHandler());
			entity.setPositionAndUpdate(pos.getX() + 0.5, pos.getY() + 3, pos.getZ() + 0.5);
			worldIn.spawnEntity(entity);
		}
		return EnumActionResult.PASS;
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand hand) {
		if (worldIn.isRemote) return super.onItemRightClick(worldIn, playerIn, hand);
		// playerIn.clearActivePotions();
		// playerIn.addPotionEffect(new PotionEffect(voidCraft.potions.fireSheath, 20 * 90));
		// Vec3d vec = playerIn.getLook(1.0f);
		// EntityHerobrineFireball entity = new EntityHerobrineFireball(worldIn, playerIn, vec.xCoord, vec.yCoord, vec.zCoord);
		// ProjectileDisintegration entity = new ProjectileDisintegration(worldIn, playerIn, playerIn.posX, playerIn.posY, playerIn.posZ);
		// EntityGhostPlayer entity = new EntityGhostPlayer(worldIn, PlayerNameAlias.Cpw11);
		// entity.setPositionAndRotation(x, y, z, yaw, pitch);
		// worldIn.spawnEntity(entity);
		// playerIn.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(playerIn.getMaxHealth()+1);
		// playerIn.addPotionEffect(new PotionEffect(MobEffects.HEALTH_BOOST, 20 * 20));
		// playerIn.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(playerIn.getMaxHealth()-20);
		// SchematicLoader loader = new SchematicLoader();
		// SchematicLoader.buildSchematic("twinsRoom.schematic", loader, worldIn, playerIn.getPosition());
		return super.onItemRightClick(worldIn, playerIn, hand);
	}

	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack p_77624_1_, EntityPlayer p_77624_2_, List p_77624_3_, boolean p_77624_4_) {
		p_77624_3_.add(TextFormatting.DARK_PURPLE + "Debug Tool");
	}

}
