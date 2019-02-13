package org.firstinspires.ftc.teamcode;

public class TriggerHandler {

    public interface TriggerRunnable {
        void run(double value);
    }

    TriggerRunnable handler;
    Runnable onEnd;

    boolean lastState = false;

    public void handle(TriggerRunnable r) {
        handler = r;
    }
    public void after(Runnable r) {
        onEnd = r;
    }
}
