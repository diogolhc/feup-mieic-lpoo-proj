package controller;

public interface TimeObserver {

    // TODO decide the "right" interval of time between notifications
    void notifyTimeChange(int minutes);

}
