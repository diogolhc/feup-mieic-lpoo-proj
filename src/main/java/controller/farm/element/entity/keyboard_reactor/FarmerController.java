package controller.farm.element.entity.keyboard_reactor;

import model.farm.Farm;
import model.Position;

public class FarmerController extends KeyboardReactorEntityController {
    private final Farm farm;

    public FarmerController(Farm farm) {
        this.farm = farm;
    }

    @Override
    protected Position getEntityPosition() {
        return this.farm.getFarmer().getPosition();
    }

    @Override
    protected void moveEntity(Position position) {
        if (this.farm.isTraversable(position)) {
            this.farm.getFarmer().setPosition(position);
        }
    }
}
