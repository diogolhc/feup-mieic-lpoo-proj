package controller;

public interface ChronologicalTimeObserver {

    // TODO decide the "right" interval of time between notifications
    void notifyChronologicalTimeChange(int minutes);

}
