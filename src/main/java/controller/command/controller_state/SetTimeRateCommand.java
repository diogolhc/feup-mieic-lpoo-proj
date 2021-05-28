package controller.command.controller_state;

import controller.RealTimeToInGameTimeConverter;
import controller.command.Command;

public class SetTimeRateCommand implements Command {
    private final RealTimeToInGameTimeConverter timeConverter;
    private final long rate;

    public SetTimeRateCommand(RealTimeToInGameTimeConverter timeConverter, long rate) {
        this.timeConverter = timeConverter;
        this.rate = rate;
    }

    @Override
    public void execute() {
        this.timeConverter.setRateMultiplier(rate);
    }

}
