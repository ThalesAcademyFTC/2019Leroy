package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.Gamepad;

public class ButtonHandler {

    Runnable pressed;
    Runnable held;
    Runnable onEnd;

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
