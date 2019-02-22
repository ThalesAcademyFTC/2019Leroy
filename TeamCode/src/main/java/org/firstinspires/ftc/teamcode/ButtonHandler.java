package org.firstinspires.ftc.teamcode;

public class ButtonHandler {

    Runnable pressed = () -> {};
    Runnable held = () -> {}; //WARNING: Not implemented.
    Runnable onEnd = () -> {};

    boolean lastState = false;

    public void onPress(Runnable r) {
        pressed = r;
    }
    public void onHold(Runnable r) {
        held = r;
    }
    public void after(Runnable r) {
        onEnd = r;
    }
}
