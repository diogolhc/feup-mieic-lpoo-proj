package model.farm.building;

import model.Position;
import model.region.EdificeUntraversableRegion;
import model.region.PositionRegion;
import model.region.Region;

public class House extends Building {
    public House(Position topLeft) {
        super(topLeft);
    }

    @Override
    public int getWidth() {
        return 7;
    }

    @Override
    public int getHeight() {
        return 7;
    }

    @Override
    public Region getUntraversableRegion() {
        return new EdificeUntraversableRegion(this.getTopLeftPosition());
    }

    @Override
    public Region getInteractiveRegion() {
        return new PositionRegion(this.getTopLeftPosition().getTranslated(new Position(4, 6)));
    }

    public long getSleepRate() {
        // TODO in the future, this could depend on the House level
        //      if the upgrades feature were to be implemented
        return 15;
    }
}
