package org.firstinspires.ftc.teamcode;

public class JoystickHandler {

    public interface JoystickRunnable {
        void run(double x, double y);
    }

    JoystickRunnable handler;
    Runnable onEnd;

    boolean lastState = false;

    public void handle(JoystickRunnable r) {
        handler = r;
    }
    public void after(Runnable r) {
        onEnd = r;
    }
}
