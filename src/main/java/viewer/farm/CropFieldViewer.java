package viewer.farm;

import gui.GUI;
import gui.drawer.entity.CropFieldDrawer;
import model.farm.building.crop_field.CropField;
import model.farm.building.crop_field.crop.GrowthStage;

public class CropFieldViewer {
    public void draw(CropField cropField, GUI gui) {
        GrowthStage growthStage = cropField.getCropGrowthStage();
        CropFieldDrawer drawer = new CropFieldDrawer(gui);
        drawer.draw(cropField.getTopLeftPosition(), growthStage.getStageColor(), growthStage.getStageChar());
    }
}
