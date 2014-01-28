package appeng.integration.modules;

import ic2.api.energy.tile.IEnergyTile;
import ic2.api.recipe.RecipeInputItemStack;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.MinecraftForge;
import appeng.api.AEApi;
import appeng.integration.IIntegrationModule;
import appeng.integration.abstraction.IIC2;

public class IC2 implements IIntegrationModule, IIC2
{

	public static IC2 instance;

	@Override
	public void Init()
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void PostInit()
	{
		AEApi.instance().registries().matterCannon().registerAmmo( getItem( "uraniumDrop" ), 238.0289 );

		// certus quartz
		maceratorRecipe( AEApi.instance().materials().materialCertusQuartzCrystal.stack( 1 ), AEApi.instance().materials().materialCertusQuartzDust.stack( 1 ) );

		maceratorRecipe( AEApi.instance().materials().materialCertusQuartzCrystalCharged.stack( 1 ), AEApi.instance().materials().materialCertusQuartzDust.stack( 1 ) );

		// fluix
		maceratorRecipe( AEApi.instance().materials().materialFluixCrystal.stack( 1 ), AEApi.instance().materials().materialFluixDust.stack( 1 ) );

		// nether quartz
		maceratorRecipe( new ItemStack( Item.netherQuartz ), AEApi.instance().materials().materialNetherQuartzDust.stack( 1 ) );
	}

	/*
	 * private void compressorRecipe(ItemStack in, ItemStack out) {
	 * ic2.api.recipe.Recipes.compressor.addRecipe( new RecipeInputItemStack(
	 * in, in.stackSize ), null, out ); }
	 */
	private void maceratorRecipe(ItemStack in, ItemStack out)
	{
		ic2.api.recipe.Recipes.macerator.addRecipe( new RecipeInputItemStack( in, in.stackSize ), null, out );
	}

	@Override
	public void addToEnergyNet(TileEntity appEngTile)
	{
		MinecraftForge.EVENT_BUS.post( new ic2.api.energy.event.EnergyTileLoadEvent( (IEnergyTile) appEngTile ) );
	}

	@Override
	public void removeFromEnergyNet(TileEntity appEngTile)
	{
		MinecraftForge.EVENT_BUS.post( new ic2.api.energy.event.EnergyTileUnloadEvent( (IEnergyTile) appEngTile ) );
	}

	@Override
	public ItemStack getItem(String name)
	{
		return ic2.api.item.Items.getItem( name );
	}

}
