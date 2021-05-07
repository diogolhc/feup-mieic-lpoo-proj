package model.farm.building.crop_field;

import model.InGameTime;
import model.Position;
import model.farm.building.Building;
import model.farm.building.crop_field.crop.GrowthStage;
import model.farm.building.crop_field.state.CropFieldState;
import model.farm.building.crop_field.state.NotPlanted;


public class CropField extends Building {
    public static final int CROP_FIELD_SIZE = 4;
    private CropFieldState state;

    public CropField(Position topLeft) {
        super(topLeft);
        this.state = new NotPlanted();
    }

    public void setState(CropFieldState state) {
        this.state = state;
    }

    public CropFieldState getState() {
        return this.state;
    }

    public GrowthStage getCropGrowthStage() {
        InGameTime remainingTime = this.state.getRemainingTime();
        return this.state.getCrop().getGrowthStage(remainingTime);
    }

    @Override
    public boolean isTraversable(Position position) {
        Position invalidPosition = this.getTopLeftPosition().getRight().getDown();
        if (position.equals(invalidPosition)) return false;
        invalidPosition = invalidPosition.getRight();
        if (position.equals(invalidPosition)) return false;
        invalidPosition = invalidPosition.getDown();
        if (position.equals(invalidPosition)) return false;
        invalidPosition = invalidPosition.getLeft();
        if (position.equals(invalidPosition)) return false;
        return true;
    }

    @Override
    public boolean isInInteractiveZone(Position position) {
        int x = position.getX();
        int y = position.getY();
        int left = this.getTopLeftPosition().getX();
        int right = left + CROP_FIELD_SIZE - 1;
        int top = this.getTopLeftPosition().getY();
        int bottom = top + CROP_FIELD_SIZE - 1;
        return (x >= left && x <= right && y >= top && y <= bottom);
    }

    public InGameTime getRemainingTime() {
        return this.state.getRemainingTime();
    }

    public void setRemainingTime(InGameTime time) {
        this.state.setRemainingTime(time);
    }
}
