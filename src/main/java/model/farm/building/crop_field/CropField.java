package model.farm.building.crop_field;

import model.InGameTime;
import model.Position;
import model.farm.Currency;
import model.farm.building.Buildable;
import model.farm.data.item.Crop;
import model.farm.data.item.CropGrowthStage;
import model.farm.building.crop_field.state.CropFieldState;
import model.farm.building.crop_field.state.NotPlanted;
import model.region.RectangleRegion;
import model.region.Region;

import java.util.Objects;

public class CropField extends Buildable {
    private CropFieldState state;

    public CropField(Position topLeft) {
        super(topLeft);
        this.state = new NotPlanted();
    }

    @Override
    public Currency getBuildPrice() {
        return new Currency(3500);
    }

    @Override
    public int getWidth() {
        return 4;
    }

    @Override
    public int getHeight() {
        return 4;
    }

    @Override
    public Region getUntraversableRegion() {
        return new RectangleRegion(
                this.getTopLeftPosition().getTranslated(new Position(1, 1)),
                this.getWidth() - 2,
                this.getHeight() - 2);
    }

    @Override
    public Region getInteractiveRegion() {
        return new RectangleRegion(this.getTopLeftPosition(), this.getWidth(), this.getHeight());
    }

    @Override
    public String getName() {
        return "CROPFIELD";
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

    public Crop getCrop() {
        return this.state.getCrop();
    }

    public InGameTime getRemainingTime() {
        return this.state.getRemainingTime();
    }

    public void setRemainingTime(InGameTime time) {
        this.state.setRemainingTime(time);
    }

    public void changeHarvestAmount(double quantity) {
        this.state.changeHarvestAmount(quantity);
    }

    public int getHarvestAmount() {
        return this.state.getHarvestAmount();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || this.getClass() != o.getClass()) return false;
        CropField cropField = (CropField) o;
        return Objects.equals(this.getTopLeftPosition(), cropField.getTopLeftPosition());
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.getTopLeftPosition());
    }
}
