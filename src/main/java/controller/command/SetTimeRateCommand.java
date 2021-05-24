package controller.command;

import controller.RealTimeToInGameTimeConverter;

public class SetTimeRateCommand implements Command {
    private final RealTimeToInGameTimeConverter timeConverter;
    private long rate;

    public SetTimeRateCommand(RealTimeToInGameTimeConverter timeConverter, long rate) {
        this.timeConverter = timeConverter;
        this.rate = rate;
    }

    @Override
    public void execute() {
        this.timeConverter.setRateMultiplier(rate);
    }

}
