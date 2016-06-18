package Tamaized.Voidcraft.capabilities;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class EventHandler {
	
	public EventHandler(){
		System.out.println("Registered");
	}
	
	@SubscribeEvent
    public void attachCapabilityEntity(AttachCapabilitiesEvent.Entity e) {
        if (e.getEntity() instanceof EntityPlayer) {
    		System.out.println("addCapability "+e);
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
					System.out.println("serializeNBT");
					return (NBTTagCompound)CapabilityList.VOIDICINFUSION.getStorage().writeNBT(CapabilityList.VOIDICINFUSION, inst, null);
				}

				@Override
				public void deserializeNBT(NBTTagCompound nbt) {
					System.out.println("deserializeNBT");
					CapabilityList.VOIDICINFUSION.getStorage().readNBT(CapabilityList.VOIDICINFUSION, inst, null, nbt);
				}
            	
            });
        }
    }

}
