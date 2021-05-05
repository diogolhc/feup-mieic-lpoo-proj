package controller.time.oper;

import model.InGameTime;

public class InGameTimeSubtraction implements InGameTimeOper {
    @Override
    public InGameTime apply(InGameTime time1, InGameTime time2) {
        if (time1.getMinute() > time2.getMinute())
            return new InGameTime(time1.getMinute() - time2.getMinute());
        else
            return new InGameTime(0);
    }
}
