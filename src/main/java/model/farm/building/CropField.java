package model.farm.building;

import model.InGameTime;
import model.Position;
import model.farm.item.CropGrowthStage;
import model.farm.building.crop_field_state.CropFieldState;
import model.farm.building.crop_field_state.NotPlanted;
import model.region.RectangleRegion;
import model.region.Region;

import java.util.Objects;


public class CropField extends Building {
    private CropFieldState state;

    public CropField(Position topLeft) {
        super(topLeft);
        this.state = new NotPlanted();
    }

    @Override
    public int getWidth() {
        return 4;
    }

    @Override
    public int getHeight() {
        return 4;
    }

    public void setState(CropFieldState state) {
        this.state = state;
    }

    public CropFieldState getState() {
        return this.state;
    }

    public CropGrowthStage getCropGrowthStage() {
        InGameTime remainingTime = this.state.getRemainingTime();
        return this.state.getCrop().getCurrentGrowthStage(remainingTime);
    }

    @Override
    public Region getUntraversableRegion() {
        return new RectangleRegion(
                this.getTopLeftPosition().getRight().getDown(),
                this.getWidth() - 2,
                this.getHeight() - 2);
    }

    @Override
    public Region getInteractiveRegion() {
        return new RectangleRegion(this.getTopLeftPosition(), this.getWidth(), this.getHeight());
    }

    public InGameTime getRemainingTime() {
        return this.state.getRemainingTime();
    }

    public void setRemainingTime(InGameTime time) {
        this.state.setRemainingTime(time);
    }
}
