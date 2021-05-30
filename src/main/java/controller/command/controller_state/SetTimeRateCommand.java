package controller.command.controller_state;

import controller.TimeConverter;
import controller.command.Command;

public class SetTimeRateCommand implements Command {
    private final TimeConverter timeConverter;
    private final long rate;

    public SetTimeRateCommand(TimeConverter timeConverter, long rate) {
        this.timeConverter = timeConverter;
        this.rate = rate;
    }

    @Override
    public void execute() {
        this.timeConverter.setRateMultiplier(this.rate);
    }

}
