package controller.command.controller_state;

import controller.TimeConverter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SetTimeRateCommandTest {

    @Test
    public void execute() {
        int base = 17;
        int rate = 19;

        TimeConverter timeConverter = new TimeConverter(base);

        SetTimeRateCommand command = new SetTimeRateCommand(timeConverter, rate);

        Assertions.assertNotEquals(base * rate, timeConverter.getRate());
        command.execute();
        Assertions.assertEquals(base * rate, timeConverter.getRate());
    }
}
