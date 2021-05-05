package controller.time.oper;

import model.IngameTime;

public class InGameTimeSubtraction implements InGameTimeOper {
    @Override
    public IngameTime apply(IngameTime time1, IngameTime time2) {
        if (time1.getMinute() > time2.getMinute())
            return new IngameTime(time1.getMinute() - time2.getMinute());
        else
            return new IngameTime(0);
    }
}
