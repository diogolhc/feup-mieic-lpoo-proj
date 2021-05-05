package controller.time.oper;

import model.InGameTime;

public interface InGameTimeOper {
    InGameTime apply(InGameTime time1, InGameTime time2);
}
