package tamaized.dalquor.common.structures.xia;

import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.MapGenStructureIO;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import net.minecraft.world.gen.structure.StructureComponent;
import net.minecraft.world.gen.structure.StructureComponentTemplate;
import net.minecraft.world.gen.structure.template.PlacementSettings;
import net.minecraft.world.gen.structure.template.Template;
import net.minecraft.world.gen.structure.template.TemplateManager;
import tamaized.dalquor.DalQuor;

import java.util.List;
import java.util.Random;

public class StructureXiaCastlePieces {

	private static final PlacementSettings OVERWRITE = (new PlacementSettings()).setIgnoreEntities(true);
	private static final PlacementSettings INSERT = (new PlacementSettings()).setIgnoreEntities(true).setReplacedBlock(Blocks.AIR);

	public static void registerPieces() {
		MapGenStructureIO.registerStructureComponent(StructureXiaCastlePieces.XiaCastleTemplate.class, DalQuor.modid + "XCP");
	}

	public static void start(TemplateManager templateManager, BlockPos blockPos, Rotation rotation, List<StructureComponent> structureComponents, Random random) {
		StructureXiaCastlePieces.XiaCastleTemplate template = addHelper(structureComponents, new StructureXiaCastlePieces.XiaCastleTemplate(templateManager, "entrance", blockPos, rotation, true));
		template = addHelper(structureComponents, addPiece(templateManager, template, new BlockPos(-1, 0, -1), "entrancebridge", rotation, false));
	}

	private static StructureXiaCastlePieces.XiaCastleTemplate addHelper(List<StructureComponent> list, StructureXiaCastlePieces.XiaCastleTemplate template) {
		list.add(template);
		return template;
	}

	private static StructureXiaCastlePieces.XiaCastleTemplate addPiece(TemplateManager templateManager, StructureXiaCastlePieces.XiaCastleTemplate template, BlockPos pos, String name, Rotation rotation, boolean overwrite) {
		StructureXiaCastlePieces.XiaCastleTemplate castleTemplate = new StructureXiaCastlePieces.XiaCastleTemplate(templateManager, name, template.getTemplatePosition(), rotation, overwrite);
		BlockPos blockpos = template.getTemplate().calculateConnectedPos(template.getPlaceSettings(), pos, castleTemplate.getPlaceSettings(), BlockPos.ORIGIN);
		castleTemplate.offset(blockpos.getX(), blockpos.getY(), blockpos.getZ());
		return castleTemplate;
	}

	public static class XiaCastleTemplate extends StructureComponentTemplate {

		private String pieceName;
		private Rotation rotation;
		private boolean overwrite;

		public XiaCastleTemplate() {

		}

		public XiaCastleTemplate(TemplateManager templateManager, String name, BlockPos pos, Rotation rotation, boolean overwrite) {
			super(0);
			this.pieceName = name;
			this.templatePosition = pos;
			this.rotation = rotation;
			this.overwrite = overwrite;
			this.loadTemplate(templateManager);
		}

		public PlacementSettings getPlaceSettings() {
			return placeSettings;
		}

		public BlockPos getTemplatePosition() {
			return templatePosition;
		}

		public Template getTemplate() {
			return template;
		}

		public void setComponentType(int i) {
			componentType = i;
		}

		private void loadTemplate(TemplateManager templateManager) {
			Template template = templateManager.getTemplate(null, new ResourceLocation(DalQuor.modid, "xia/" + this.pieceName));
			PlacementSettings placementsettings = (this.overwrite ? StructureXiaCastlePieces.OVERWRITE : StructureXiaCastlePieces.INSERT).copy().setRotation(this.rotation);
			this.setup(template, this.templatePosition, placementsettings);
		}

		@Override
		protected void writeStructureToNBT(NBTTagCompound tagCompound) {
			super.writeStructureToNBT(tagCompound);
			tagCompound.setString("Template", this.pieceName);
			tagCompound.setString("Rot", this.rotation.name());
			tagCompound.setBoolean("OW", this.overwrite);
		}

		@Override
		protected void readStructureFromNBT(NBTTagCompound tagCompound, TemplateManager templateManager) {
			super.readStructureFromNBT(tagCompound, templateManager);
			this.pieceName = tagCompound.getString("Template");
			this.rotation = Rotation.valueOf(tagCompound.getString("Rot"));
			this.overwrite = tagCompound.getBoolean("OW");
			this.loadTemplate(templateManager);
		}

		@Override
		protected void handleDataMarker(String name, BlockPos pos, World world, Random rand, StructureBoundingBox bounds) {

		}
	}


}
