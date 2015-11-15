package sonar.calculator.mod.common.block.generators;

import java.util.List;
import java.util.Random;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import sonar.calculator.mod.Calculator;
import sonar.calculator.mod.api.IStableBlock;
import sonar.calculator.mod.common.tileentity.generators.TileEntityCalculatorLocator;
import sonar.calculator.mod.network.CalculatorGui;
import sonar.calculator.mod.utils.helpers.CalculatorHelper;
import sonar.core.common.block.SonarMachineBlock;
import sonar.core.utils.SonarMaterials;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class CalculatorLocator extends SonarMachineBlock {

	public CalculatorLocator() {
		super(SonarMaterials.machine, false);
		setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.75F, 1.0F);
	}

	public boolean hasSpecialRenderer() {
		return true;
	}

	@Override
	public boolean operateBlock(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ) {
		this.getCollisionBoundingBoxFromPool(world, x, y, z);
		if (player != null) {
			if (!world.isRemote) {
				player.openGui(Calculator.instance, CalculatorGui.CalculatorLocator, world, x, y, z);
			}
		}
		return true;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void randomDisplayTick(World world, int x, int y, int z, Random random) {
		TileEntityCalculatorLocator te = (TileEntityCalculatorLocator) world.getTileEntity(x, y, z);
		if (te.active == 1) {
			float x1 = x + random.nextFloat();
			float y1 = y + 0.5F;
			float z1 = z + random.nextFloat();

			world.spawnParticle("smoke", x1, y1, z1, 0.0D, 0.0D, 0.0D);
			world.spawnParticle("smoke", x1, y1, z1, 0.0D, 0.0D, 0.0D);
		}
	}

	public static int multiBlockStructure(World world, int x, int y, int z) {
		for (int size = 1; size < 12; size++) {
			if (checkSize(world, x, y, z, size)) {
				return size;
			}
		}
		return 0;
	}

	public static boolean checkSize(World world, int x, int y, int z, int size) {
		for (int X = -size; X <= size; X++) {
			for (int Z = -size; Z <= size; Z++) {
				if (!(X == 0 && Z == 0)) {
					if (!(world.getBlock(x + X, y - 1, z + Z) instanceof IStableBlock)) {
						return false;
					}
				}
			}
		}

		for (int XZ = -(size); XZ <= (size); XZ++) {
			for (int Y = -1; Y <= 0; Y++) {
				if (!(world.getBlock(x + XZ, y + Y, z + size+1) instanceof IStableBlock)) {
					return false;
				} else if (!(world.getBlock(x + XZ, y + Y, z - (size+1)) instanceof IStableBlock)) {
					return false;
				} else if (!(world.getBlock(x + (size+1), y + Y, z + XZ) instanceof IStableBlock)) {
					return false;
				} else if (!(world.getBlock(x - (size+1), y + Y, z + XZ) instanceof IStableBlock)) {
					return false;
				}
			}
		}

		for (int X = -(size); X <= (size); X++) {
			for (int Z = -(size); Z <= (size); Z++) {
				if (!(X == 0 && Z == 0)) {
					if (!(world.getBlock(x + X, y, z + Z) == Calculator.calculatorplug)) {
						return false;
					}
				}
			}
		}
		return true;
	}

	@Override
	public TileEntity createNewTileEntity(World var1, int var2) {
		return new TileEntityCalculatorLocator();
	}

	@Override
	public void addSpecialToolTip(ItemStack stack, EntityPlayer player, List list) {
		CalculatorHelper.addEnergytoToolTip(stack, player, list);

	}

}
