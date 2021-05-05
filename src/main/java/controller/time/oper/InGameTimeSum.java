package controller.time.oper;

import model.IngameTime;

public class InGameTimeSum implements InGameTimeOper {
    @Override
    public IngameTime apply(IngameTime time1, IngameTime time2) {
        return new IngameTime(time1.getMinute() + time2.getMinute());
    }
}
