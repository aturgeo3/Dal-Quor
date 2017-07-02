package tamaized.voidcraft.common.machina.tileentity;

import tamaized.tammodized.common.tileentity.TamTileEntityInventory;
import tamaized.voidcraft.VoidCraft;
import tamaized.voidcraft.common.sound.OggLength;
import tamaized.voidcraft.common.sound.VanillaRecordLengths;
import com.google.gson.stream.JsonReader;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemRecord;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.fml.relauncher.ReflectionHelper;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.items.wrapper.CombinedInvWrapper;

import javax.annotation.Nullable;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.util.Map;
import java.util.Map.Entry;

public class TileEntityVoidBox extends TamTileEntityInventory {

	private static Field field;
	public ItemStackFilterHandler SLOT_CURRENT;
	public ItemStackFilterHandler SLOT_NEXT;
	public ItemStackFilterHandler SLOT_FINISH;
	private boolean playing;
	private int songTimeLeft;
	private int songTime;
	private boolean loop;
	private boolean autoFill;
	private ItemStack oldRecord = ItemStack.EMPTY;
	// Unused for now
	private boolean isPowered = false;
	private boolean pulsed = false;

	public TileEntityVoidBox() {
		super();
	}

	@Override
	protected ItemStackHandler[] register() {
		return new ItemStackHandler[]{

				SLOT_CURRENT = new ItemStackFilterHandler(new ItemStack[0], false, new ItemStack[0], false),

				SLOT_NEXT = new ItemStackFilterHandler(new Class[]{ItemRecord.class}, true, new Class[0], false),

				SLOT_FINISH = new ItemStackFilterHandler(new ItemStack[0], false, new ItemStack[0], true)

		};
	}

	@Nullable
	@Override
	protected IItemHandler getCap(EnumFacing enumFacing) {
		return new CombinedInvWrapper(SLOT_CURRENT, SLOT_FINISH, SLOT_NEXT);
	}

	public void readNBT(NBTTagCompound nbt) {
		playing = nbt.getBoolean("playing");
		songTimeLeft = nbt.getInteger("songTimeLeft");
		songTime = nbt.getInteger("songTime");
		loop = nbt.getBoolean("loop");
		autoFill = nbt.getBoolean("autoFill");
		int temp = nbt.getInteger("oldRecord");
		oldRecord = temp > -1 ? new ItemStack(Item.getItemById(temp)) : ItemStack.EMPTY;
	}

	@Override
	public NBTTagCompound writeNBT(NBTTagCompound nbt) {
		nbt.setBoolean("playing", playing);
		nbt.setInteger("songTimeLeft", songTimeLeft);
		nbt.setInteger("songTime", songTime);
		nbt.setBoolean("loop", loop);
		nbt.setBoolean("autoFill", autoFill);
		nbt.setInteger("oldRecord", !oldRecord.isEmpty() ? Item.getIdFromItem(oldRecord.getItem()) : -1);
		return nbt;
	}

	public boolean isPlaying() {
		return playing;
	}

	public int getSongLength() {
		return songTime;
	}

	public int getSongTimeLeft() {
		return songTimeLeft;
	}

	public boolean getAutoState() {
		return autoFill;
	}

	public boolean getLoopState() {
		return loop;
	}

	public void setAutoState() {
		autoFill = !autoFill;
	}

	public void setLoopState() {
		loop = !loop;
	}

	private void PlayTheSound() {
		ItemStack itemStack = SLOT_CURRENT.getStackInSlot(0);
		if (!itemStack.isEmpty() && itemStack.getItem() instanceof ItemRecord) {
			try {
				ItemRecord theRecord = (ItemRecord) itemStack.getItem();
				Class<? extends ItemRecord> c = ItemRecord.class;
				if (field == null)
					field = ReflectionHelper.findField(c, "field_150928_b", "RECORDS");
				Map<SoundEvent, ItemRecord> RECORDS = (Map<SoundEvent, ItemRecord>) field.get(theRecord);
				SoundEvent soundEvent = null;
				for (Entry<SoundEvent, ItemRecord> entry : RECORDS.entrySet()) {
					if (entry.getValue() == theRecord) {
						soundEvent = entry.getKey();
						break;
					}
				}
				if (soundEvent == null) {
					VoidCraft.instance.logger.error("null SoundEvent");
					return;
				}
				ResourceLocation resourceLocation = SoundEvent.REGISTRY.getNameForObject(soundEvent);
				if (resourceLocation == null) {
					VoidCraft.instance.logger.error("null ResourceLocation");
					return;
				}
				String modid = resourceLocation.getResourceDomain();
				String soundID = resourceLocation.getResourcePath();
				String encodedValue = "";
				InputStream jsonStream = modid.equals("minecraft") ? null : getClass().getResourceAsStream("/assets/" + modid + "/sounds.json");
				if (jsonStream != null) {
					JsonReader json = new JsonReader(new InputStreamReader(jsonStream, "UTF-8"));
					{
						json.beginObject();
						{
							while (json.hasNext()) {
								String key = json.nextName();
								if (key.equals(soundID)) {
									json.beginObject();
									{
										while (json.hasNext()) {
											key = json.nextName();
											if (key.equals("sounds")) {
												json.beginArray();
												{
													json.beginObject();
													{
														while (json.hasNext()) {
															key = json.nextName();
															if (key.equals("name")) {
																encodedValue = json.nextString();
															} else {
																json.skipValue();
															}
														}
													}
													json.endObject();
												}
												json.endArray();
											} else {
												json.skipValue();
											}
										}
									}
									json.endObject();
								} else {
									json.skipValue();
								}
							}

						}
						json.endObject();
					}
					json.close();
					jsonStream.close();
					String[] decodedValue = {"", ""};
					if (encodedValue.contains(":")) {
						decodedValue = encodedValue.split(":");
					} else {
						decodedValue[0] = "minecraft";
						decodedValue[1] = encodedValue;
					}
					String path = "/assets/" + decodedValue[0] + "/sounds/" + decodedValue[1] + ".ogg";
					songTime = songTimeLeft = OggLength.getLengthInSeconds(path) * 20;
				} else {
					songTime = songTimeLeft = 20 * VanillaRecordLengths.getLength(soundID.split("\\.")[soundID.split("\\.").length - 1]);
				}
				world.playEvent((EntityPlayer) null, 1010, getPos(), Item.getIdFromItem(theRecord));
				oldRecord = SLOT_CURRENT.getStackInSlot(0);
				playing = true;
				// IF YOU GOT THIS FAR, CONGRATS THIS WAS NOT FUN TO WRITE
			} catch (Exception e) {
				VoidCraft.instance.logger.warn("Could not play ItemRecord");
				e.printStackTrace();
				loop = false; // Prevent soft lock
			}
		} else {
			VoidCraft.instance.logger.warn("NULL/NON-ITEMRECORD IN SLOT DETECTED");
		}
	}

	public void PlayNextSound() {
		if (SLOT_CURRENT.getStackInSlot(0).isEmpty()) {
			SLOT_CURRENT.setStackInSlot(0, SLOT_NEXT.getStackInSlot(0).copy());
			SLOT_NEXT.setStackInSlot(0, ItemStack.EMPTY);
			PlayTheSound();
		}
	}

	public void StopTheSound() {
		world.playEvent((EntityPlayer) null, 1010, getPos(), 0);
		playing = false;
	}

	public void StopTheSoundAndDeposit() {
		StopTheSound();
		if (!SLOT_CURRENT.getStackInSlot(0).isEmpty() && SLOT_FINISH.getStackInSlot(0).isEmpty()) {
			SLOT_FINISH.setStackInSlot(0, SLOT_CURRENT.getStackInSlot(0).copy());
			SLOT_CURRENT.setStackInSlot(0, ItemStack.EMPTY);
		}
	}

	public void setHasRedstoneSignal(boolean b) { // TODO
		isPowered = b;
	}

	@Override
	public void onUpdate() {
		if (!world.isRemote) {
			if (playing) {
				if (songTimeLeft > 0)
					songTimeLeft--;
				else
					StopTheSound();
				if (SLOT_CURRENT.getStackInSlot(0).isEmpty() || !SLOT_CURRENT.getStackInSlot(0).equals(oldRecord))
					StopTheSound();
			} else {
				if (loop && !SLOT_CURRENT.getStackInSlot(0).isEmpty()) {
					PlayTheSound();
				}
				if (!loop && !SLOT_CURRENT.getStackInSlot(0).isEmpty() && SLOT_FINISH.getStackInSlot(0).isEmpty()) {
					SLOT_FINISH.setStackInSlot(0, SLOT_CURRENT.getStackInSlot(0).copy());
					SLOT_CURRENT.setStackInSlot(0, ItemStack.EMPTY);
				}
				if (autoFill) {
					if (SLOT_CURRENT.getStackInSlot(0).isEmpty() && !SLOT_NEXT.getStackInSlot(0).isEmpty()) {
						PlayNextSound();
					}
				}
			}
		}
	}

}
