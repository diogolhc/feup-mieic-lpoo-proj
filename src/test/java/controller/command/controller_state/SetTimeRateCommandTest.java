package controller.command.controller_state;

import controller.TimeConverter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SetTimeRateCommandTest {

    @Test
    public void execute() {
        int base = 10;
        TimeConverter timeConverter = new TimeConverter(base);
        int rate = 15;
        SetTimeRateCommand command = new SetTimeRateCommand(timeConverter, rate);
        command.execute();
        Assertions.assertEquals(150, timeConverter.getRate());
    }
}
