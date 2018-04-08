package tamaized.dalquor.common.events;

import net.minecraft.block.BlockBed;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import tamaized.dalquor.DalQuor;
import tamaized.dalquor.common.entity.boss.dragon.EntityVoidicDragon;
import tamaized.dalquor.common.handlers.ConfigHandler;

public class PlayerRightClickEvent {

	@SubscribeEvent
	public void rightClick(PlayerInteractEvent.RightClickBlock e) {
		World world = e.getWorld();
		BlockPos pos = e.getPos();
		ItemStack stack = e.getItemStack();
		IBlockState state = world.getBlockState(pos);
		if (!world.isRemote && state.getBlock() instanceof BlockBed && world.provider.getDimension() != ConfigHandler.dimensionIdDalQuor) {
			if (!stack.isEmpty() && stack.getItem() == DalQuor.items.quoriFragment) {
				stack.shrink(1);
				e.setCanceled(true);
				VoidTickEvent.dream(e.getEntityPlayer());
			}
		}
		if (state != null && state.getBlock() == Blocks.DRAGON_EGG && !stack.isEmpty() && stack.getItem() == DalQuor.items.voidStar) {
			if (!world.isRemote) {
				world.createExplosion(null, pos.getX(), pos.getY(), pos.getZ(), 0, true);
				world.setBlockToAir(pos);
				EntityVoidicDragon dragon = new EntityVoidicDragon(world);
				dragon.setPositionAndUpdate(pos.getX(), pos.getY() + 20, pos.getZ());
				world.createExplosion(null, pos.getX(), pos.getY() + 20, pos.getZ(), 0, true);
				world.spawnEntity(dragon);
				stack.shrink(1);
			}
			e.setCanceled(true);
		}
	}

}
