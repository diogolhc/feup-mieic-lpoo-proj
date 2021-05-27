package model.farm.building.edifice;

import model.Position;
import model.farm.building.Building;
import model.region.EdificeUntraversableRegion;
import model.region.PositionRegion;
import model.region.Region;

import java.io.Serializable;

public class House extends Edifice {
    public House(Position topLeft) {
        super(topLeft);
    }

    @Override
    public String getName() {
        return "HOUSE";
    }

    public long getRestRate() {
        // TODO in the future, this could depend on the House level
        //      if the upgrades feature were implemented
        return 15;
    }
}
