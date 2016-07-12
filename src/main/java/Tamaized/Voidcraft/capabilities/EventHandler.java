package Tamaized.Voidcraft.capabilities;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class EventHandler {
	
	@SubscribeEvent
    public void attachCapabilityEntity(AttachCapabilitiesEvent.Entity e) {
        if (e.getEntity() instanceof EntityPlayer) {
            e.addCapability(VoidicInfusionCapabilityHandler.ID, new ICapabilitySerializable<NBTTagCompound>(){
            	
            	IVoidicInfusionCapability inst = CapabilityList.VOIDICINFUSION.getDefaultInstance();
            	
				@Override
				public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
					return capability == CapabilityList.VOIDICINFUSION;
				}

				@Override
				public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
					return capability == CapabilityList.VOIDICINFUSION ? CapabilityList.VOIDICINFUSION.<T>cast(inst) : null;
				}

				@Override
				public NBTTagCompound serializeNBT() {
					return (NBTTagCompound)CapabilityList.VOIDICINFUSION.getStorage().writeNBT(CapabilityList.VOIDICINFUSION, inst, null);
				}

				@Override
				public void deserializeNBT(NBTTagCompound nbt) {
					CapabilityList.VOIDICINFUSION.getStorage().readNBT(CapabilityList.VOIDICINFUSION, inst, null, nbt);
				}
            	
            });
        }
    }
	
	@SubscribeEvent
	public void updateClone(PlayerEvent.Clone e){
		System.out.println("Clone");
		EntityPlayer oldPlayer = e.getOriginal();
		EntityPlayer newPlayer = e.getEntityPlayer();
		IVoidicInfusionCapability oldCap = oldPlayer.getCapability(CapabilityList.VOIDICINFUSION, null);
		IVoidicInfusionCapability newCap = newPlayer.getCapability(CapabilityList.VOIDICINFUSION, null);
		newCap.setCheckMaxHealth(oldCap.checkMaxHealth());
		newCap.setInfusion(oldCap.getInfusion());
		newCap.setMaxInfusion(oldCap.getMaxInfusion());
		newCap.setPreMaxHealth(oldCap.preMaxHealth());
		newCap.setLoaded();
	}

}
