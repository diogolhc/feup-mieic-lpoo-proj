package controller;

import gui.GUI;
import model.Farm;

public class FarmController {
    private final Farm farm;
    private final FarmerController farmerController;

    public FarmController(Farm farm) {
        this.farm = farm;
        this.farmerController = new FarmerController(farm);
    }

    public FarmerController getFarmerController() {
        return this.farmerController;
    }

    public void doAction(GUI.ACTION action) {
        this.farmerController.doAction(action);
    }
}
