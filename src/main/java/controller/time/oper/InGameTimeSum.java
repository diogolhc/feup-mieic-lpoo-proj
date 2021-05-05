package controller.time.oper;

import model.InGameTime;

public class InGameTimeSum implements InGameTimeOper {
    @Override
    public InGameTime apply(InGameTime time1, InGameTime time2) {
        return new InGameTime(time1.getMinute() + time2.getMinute());
    }
}
