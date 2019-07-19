package bl4ckscor3.mod.ceilingtorch;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;

import bl4ckscor3.mod.ceilingtorch.compat.bonetorch.BoneTorchCompat;
import bl4ckscor3.mod.ceilingtorch.compat.vanilla.VanillaCompat;
import net.minecraft.block.Block;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.ModMetadata;
import net.minecraftforge.fml.common.event.FMLLoadCompleteEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod(modid=CeilingTorch.MODID, name=CeilingTorch.NAME, version=CeilingTorch.VERSION, acceptedMinecraftVersions="[" + CeilingTorch.MC_VERSION + "]")
@EventBusSubscriber
public class CeilingTorch
{
	public static final String MODID = "ceilingtorch";
	public static final String NAME = "Ceiling Torch";
	public static final String VERSION = "v1.2";
	public static final String MC_VERSION = "1.12.2";
	private static List<Supplier<ICeilingTorchCompat>> compatList = new ArrayList<>();

	@EventHandler
	public void preInit(FMLPreInitializationEvent event)
	{
		ModMetadata meta = event.getModMetadata();

		meta.authorList = Arrays.asList(new String[]{"bl4ckscor3"});
		meta.autogenerated = false;
		meta.description = "Makes vanilla torches placeable on the ceiling.";
		meta.modId = MODID;
		meta.name = NAME;
		meta.version = VERSION;
		meta.url = "https://minecraft.curseforge.com/projects/ceiling-torch";

		compatList.add(VanillaCompat::new);

		if(Loader.isModLoaded("bonetorch"))
			compatList.add(BoneTorchCompat::new);
	}

	@SubscribeEvent
	public static void registerBlocks(RegistryEvent.Register<Block> event)
	{
		for(Supplier<ICeilingTorchCompat> compat : compatList)
		{
			compat.get().registerBlocks(event);
		}
	}

	@EventHandler
	public void onLoadComplete(FMLLoadCompleteEvent event)
	{
		for(Supplier<ICeilingTorchCompat> compat : compatList)
		{
			compat.get().registerPlaceEntries();
		}
	}
}
