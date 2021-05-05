package controller.time.oper;

import model.IngameTime;

public interface InGameTimeOper {
    IngameTime apply(IngameTime time1, IngameTime time2);
}
