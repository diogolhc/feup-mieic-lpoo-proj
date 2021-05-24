package model.farm.building;

import model.Position;
import model.region.EdificeUntraversableRegion;
import model.region.PositionRegion;
import model.region.Region;

// TODO warehouse is too similar to house
public class Warehouse extends Building {
    public Warehouse(Position topLeft) {
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
}
