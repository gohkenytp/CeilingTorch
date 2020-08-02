package bl4ckscor3.mod.ceilingtorch;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

import bl4ckscor3.mod.ceilingtorch.compat.vanilla.VanillaCompat;
import net.minecraft.block.Block;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;

@Mod(CeilingTorch.MODID)
@EventBusSubscriber(bus=Bus.MOD)
public class CeilingTorch
{
	public static final String MODID = "ceilingtorch";
	public static final String NAME = "Ceiling Torch";
	private static final Map<String,Supplier<ICeilingTorchCompat>> COMPAT_LIST = new HashMap<>();

	public CeilingTorch()
	{
		COMPAT_LIST.put("minecraft", VanillaCompat::new);

		//		if(ModList.get().isLoaded("bonetorch"))
		//			COMPAT_LIST.put("bonetorch", BoneTorchCompat::new);
		//
		//		if(ModList.get().isLoaded("torchmaster"))
		//			COMPAT_LIST.put("torchmaster", TorchmasterCompat::new);
		//
		//		if(ModList.get().isLoaded("silentgear"))
		//			COMPAT_LIST.put("silentgear", SilentGearCompat::new);
	}

	@SubscribeEvent
	public static void registerBlocks(RegistryEvent.Register<Block> event)
	{
		for(Supplier<ICeilingTorchCompat> compat : COMPAT_LIST.values())
		{
			compat.get().registerBlocks(event);
		}
	}

	@SubscribeEvent
	public static void onInterModEnqueue(InterModEnqueueEvent event)
	{
		for(Supplier<ICeilingTorchCompat> compat : COMPAT_LIST.values())
		{
			compat.get().registerPlaceEntries();
		}
	}

	/**
	 * Adds ceiling torch compatibility for the mod with the given modid, if that mod is loaded
	 * @param modid The modid of the mod to add compatibility to
	 * @param compat The compatibility class
	 */
	public static void addCompat(String modid, Supplier<ICeilingTorchCompat> compat)
	{
		if(ModList.get().isLoaded(modid))
			COMPAT_LIST.put(modid, compat);
	}
}
