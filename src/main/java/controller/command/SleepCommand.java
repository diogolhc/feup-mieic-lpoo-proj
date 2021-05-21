package controller.command;

import model.farm.building.House;

public class SleepCommand implements Command {
    private final House house;
    private final boolean sleep;

    public SleepCommand(House house, boolean sleep) {
        this.house = house;
        this.sleep = sleep;
    }

    @Override
    public void execute() {
        this.house.setSleeping(sleep);
    }

}
