package tamaized.dalquor.common.events;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.network.play.server.SPacketEntityEffect;
import net.minecraft.network.play.server.SPacketPlayerAbilities;
import net.minecraft.network.play.server.SPacketRespawn;
import net.minecraft.potion.PotionEffect;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.Teleporter;
import net.minecraft.world.World;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.WorldServer;
import net.minecraft.world.gen.ChunkProviderServer;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import tamaized.dalquor.VoidCraft;
import tamaized.dalquor.common.capabilities.CapabilityList;
import tamaized.dalquor.common.capabilities.vadeMecum.IVadeMecumCapability;
import tamaized.dalquor.common.handlers.ConfigHandler;
import tamaized.dalquor.common.world.dim.dalquor.TeleporterDream;
import tamaized.dalquor.common.world.dim.thevoid.ChunkProviderVoid;
import tamaized.dalquor.common.world.dim.xia.TeleporterXia;
import tamaized.dalquor.common.world.dim.xia.WorldProviderXia;

import java.util.Iterator;

public class VoidTickEvent {

	public static void dream(EntityPlayer player) {
		if (player instanceof EntityPlayerMP) {
			EntityPlayerMP p = (EntityPlayerMP) player;
			int dim = (p.dimension == ConfigHandler.dimensionIdDalQuor ? 0 : ConfigHandler.dimensionIdDalQuor);
			transferPlayerToDimension(p.mcServer, p, dim, new TeleportLoc(new TeleporterDream(p.mcServer.getWorld(dim))));
		}
	}

	private static void transferPlayerToDimension(MinecraftServer mcServer, EntityPlayerMP player, int dimId, TeleportLoc teleporter) { // Custom Made to handle teleporting to and from The End (DIM 1)
		int j = player.dimension;
		WorldServer worldserver = mcServer.getWorld(player.dimension);
		player.dimension = dimId;
		WorldServer worldserver1 = mcServer.getWorld(player.dimension);
		player.connection.sendPacket(new SPacketRespawn(player.dimension, worldserver1.getDifficulty(), worldserver1.getWorldInfo().getTerrainType(), player.interactionManager.getGameType())); // Forge: Use new dimensions information
		mcServer.getPlayerList().updatePermissionLevel(player);
		worldserver.removeEntityDangerously(player);
		player.isDead = false;
		transferEntityToWorld(player, j, worldserver, worldserver1, teleporter);
		mcServer.getPlayerList().preparePlayer(player, worldserver);
		player.connection.setPlayerLocation(player.posX, player.posY, player.posZ, player.rotationYaw, player.rotationPitch);
		player.interactionManager.setWorld(worldserver1);
		player.connection.sendPacket(new SPacketPlayerAbilities(player.capabilities));
		mcServer.getPlayerList().updateTimeAndWeatherForPlayer(player, worldserver1);
		mcServer.getPlayerList().syncPlayerInventory(player);
		Iterator iterator = player.getActivePotionEffects().iterator();
		while (iterator.hasNext()) {
			PotionEffect potioneffect = (PotionEffect) iterator.next();
			player.connection.sendPacket(new SPacketEntityEffect(player.getEntityId(), potioneffect));
		}
		FMLCommonHandler.instance().firePlayerChangedDimensionEvent(player, j, dimId);
	}

	private static void transferEntityToWorld(Entity p_82448_1_, int p_82448_2_, WorldServer p_82448_3_, WorldServer p_82448_4_, TeleportLoc teleporter) { // Custom Made to handle teleporting to and from The End (DIM 1)
		WorldProvider pOld = p_82448_3_.provider;
		WorldProvider pNew = p_82448_4_.provider;
		double moveFactor = pOld.getMovementFactor() / pNew.getMovementFactor();
		double d0 = p_82448_1_.posX * moveFactor;
		double d1 = p_82448_1_.posZ * moveFactor;
		double d3 = p_82448_1_.posX;
		double d4 = p_82448_1_.posY;
		double d5 = p_82448_1_.posZ;
		float f = p_82448_1_.rotationYaw;
		p_82448_3_.profiler.startSection("moving");
		p_82448_3_.profiler.endSection();

		p_82448_3_.profiler.startSection("placing");
		d0 = (double) MathHelper.clamp((int) d0, -29999872, 29999872);
		d1 = (double) MathHelper.clamp((int) d1, -29999872, 29999872);

		if (p_82448_1_.isEntityAlive()) {
			p_82448_1_.setLocationAndAngles(d0, p_82448_1_.posY, d1, p_82448_1_.rotationYaw, p_82448_1_.rotationPitch);
			if (teleporter.teleporter != null)
				teleporter.teleporter.placeInPortal(p_82448_1_, f);
			else
				p_82448_1_.setPositionAndUpdate(teleporter.pos.getX(), teleporter.pos.getY(), teleporter.pos.getZ());
			p_82448_4_.spawnEntity(p_82448_1_);
			p_82448_4_.updateEntityWithOptionalForce(p_82448_1_, false);
		}
		p_82448_3_.profiler.endSection();
		p_82448_1_.setWorld(p_82448_4_);
	}

	@SubscribeEvent
	public void onPlayerTick(TickEvent.PlayerTickEvent e) {

		if (e.phase == e.phase.END)
			return;

		World world = e.player.world;

		if (e.player.hasCapability(CapabilityList.VADEMECUM, null)) {
			IVadeMecumCapability cap = e.player.getCapability(CapabilityList.VADEMECUM, null);

			if (cap.hasCategory(IVadeMecumCapability.Category.VoidicControl) && cap.hasCategory(IVadeMecumCapability.Category.ImprovedCasting) && !cap.hasCategory(IVadeMecumCapability.Category.Empowerment)) {
				if ((!e.player.getHeldItem(EnumHand.MAIN_HAND).isEmpty() && e.player.getHeldItem(EnumHand.MAIN_HAND).getItem() == VoidCraft.items.voidStar) || (!e.player.getHeldItem(EnumHand.OFF_HAND).isEmpty() && e.player.getHeldItem(EnumHand.OFF_HAND).getItem() == VoidCraft.items.voidStar)) {
					cap.addCategory(e.player, IVadeMecumCapability.Category.Empowerment);
					cap.addCategory(e.player, IVadeMecumCapability.Category.Invoke);
				}
			}

			if (world.getChunkProvider() instanceof ChunkProviderServer) {
				ChunkProviderServer serverProvider = (ChunkProviderServer) world.getChunkProvider();
				if (serverProvider.chunkGenerator instanceof ChunkProviderVoid) {
					ChunkProviderVoid provider = (ChunkProviderVoid) ((ChunkProviderServer) world.getChunkProvider()).chunkGenerator;
					if (cap.hasCategory(IVadeMecumCapability.Category.Voice) && !cap.hasCategory(IVadeMecumCapability.Category.ImprovedCasting)) {
						if (provider.genFortress.isPositionInStructure(world, e.player.getPosition())) {
							cap.addCategory(e.player, IVadeMecumCapability.Category.ImprovedCasting);
						}
					}
					if (cap.hasCategory(IVadeMecumCapability.Category.Tolerance) && !cap.hasCategory(IVadeMecumCapability.Category.TotalControl)) {
						if ((!e.player.getHeldItem(EnumHand.MAIN_HAND).isEmpty() && e.player.getHeldItem(EnumHand.MAIN_HAND).getItem() == VoidCraft.items.astralEssence) || (!e.player.getHeldItem(EnumHand.OFF_HAND).isEmpty() && e.player.getHeldItem(EnumHand.OFF_HAND).getItem() == VoidCraft.items.astralEssence)) {
							cap.addCategory(e.player, IVadeMecumCapability.Category.TotalControl);
						}
					}
				}
			}
		}

		if (e.player.world.provider.getDimension() == ConfigHandler.dimensionIdVoid) {
			if (e.player.getPosition().getY() >= 127)
				e.player.attackEntityFrom(DamageSource.OUT_OF_WORLD, 4.0F);
		} else if (e.player.world.provider.getDimension() != ConfigHandler.dimensionIdXia && e.player.world.provider.getDimension() != ConfigHandler.dimensionIdDalQuor) {
			if (e.player instanceof EntityPlayerMP && e.player.getPosition().getY() <= -256 && ConfigHandler.voidTeleport) {
				EntityPlayerMP player = (EntityPlayerMP) e.player;
				transferPlayerToDimension(player.mcServer, player, ConfigHandler.dimensionIdVoid, new TeleportLoc(player.getPosition().add(0, 256 * 2, 0)));
			}
		}

		if (e.player instanceof EntityPlayerMP) {
			EntityPlayerMP player = (EntityPlayerMP) e.player;
			if (player.dimension == ConfigHandler.dimensionIdXia) {

				// Prevent players from flying in xia DIM
				if (!player.isCreative() && !player.isSpectator() && player.capabilities.isFlying && (!XiaFlightHandler.shouldPlayerHaveFlight(player) || !(player.world.provider instanceof WorldProviderXia && ((WorldProviderXia) player.world.provider).getXiaCastleHandler().canPlayersFly()))) {
					player.capabilities.allowFlying = false;
					player.capabilities.isFlying = false;
					player.capabilities.disableDamage = false;
					player.sendPlayerAbilities();
					player.sendMessage(new TextComponentTranslation(TextFormatting.DARK_GRAY + "You feel heavy, you can not sustain flight"));
				}

				// Teleport Player back to their spawn point if the xia fight is done or they have over 0 xia Defeats and they fall to the Void.
				WorldProvider provider = player.world.provider;
				if (provider instanceof WorldProviderXia && (((WorldProviderXia) provider).getXiaCastleHandler().hasFinished() || (player.hasCapability(CapabilityList.VOIDICINFUSION, null) && player.getCapability(CapabilityList.VOIDICINFUSION, null).getXiaDefeats() > 0)) && player.posY <= 0) {
					forcePlayerTeleportFromXia(player);
					//					player.addStat(VoidCraft.achievements.worldsEnd, 1); TODO
				}

			}
		}

	}

	public void forcePlayerTeleportFromXia(EntityPlayerMP player) {
		transferPlayerToDimension(player.mcServer, player, 0, new TeleportLoc(new TeleporterXia(player.mcServer.getWorld(0))));
	}

	private static class TeleportLoc {

		public final Teleporter teleporter;
		public final BlockPos pos;

		public TeleportLoc(Teleporter tele) {
			pos = null;
			teleporter = tele;
		}

		public TeleportLoc(BlockPos p) {
			teleporter = null;
			pos = p;
		}

	}

}
