//package bl4ckscor3.mod.ceilingtorch.compat.torchmaster;
//
//import bl4ckscor3.mod.ceilingtorch.CeilingTorch;
//import bl4ckscor3.mod.ceilingtorch.ICeilingTorchCompat;
//import bl4ckscor3.mod.ceilingtorch.PlaceHandler;
//import net.minecraft.block.Block;
//import net.minecraft.block.BlockState;
//import net.minecraft.block.material.Material;
//import net.minecraft.entity.player.PlayerEntity;
//import net.minecraft.item.ItemStack;
//import net.minecraft.util.ResourceLocation;
//import net.minecraft.util.math.BlockPos;
//import net.minecraft.util.math.RayTraceResult;
//import net.minecraft.world.IBlockReader;
//import net.minecraftforge.event.RegistryEvent;
//import net.xalcon.torchmaster.common.ModBlocks;
//import net.xalcon.torchmaster.common.blocks.EntityBlockingLightBlock;
//
//public class TorchmasterCompat implements ICeilingTorchCompat
//{
//	public static Block megaCeilingTorch;
//
//	@Override
//	public void registerBlocks(RegistryEvent.Register<Block> event)
//	{
//		event.getRegistry().register(megaCeilingTorch = new EntityBlockingLightBlock(Block.Properties
//				.create(Material.WOOD)
//				.hardnessAndResistance(1.0F, 1.0F)
//				.lightValue(15),
//				pos -> CeilingTorch.MODID + ":MCT_" + pos.getX() + "_" + pos.getY() + "_" + pos.getZ(),
//				MegaCeilingTorchEntityBlockingLight::new, -0.025F) {
//			@Override
//			public ResourceLocation getLootTable()
//			{
//				return ModBlocks.blockMegaTorch.getLootTable();
//			}
//
//			@Override
//			public ItemStack getPickBlock(BlockState state, RayTraceResult target, IBlockReader world, BlockPos pos, PlayerEntity player)
//			{
//				return new ItemStack(ModBlocks.blockMegaTorch);
//			}
//		}.setRegistryName(new ResourceLocation(CeilingTorch.MODID, "torchmaster_megatorch")));
//	}
//
//	@Override
//	public void registerPlaceEntries()
//	{
//		PlaceHandler.registerPlaceEntry(ModBlocks.itemMegaTorch.getRegistryName(), megaCeilingTorch);
//	}
//}
