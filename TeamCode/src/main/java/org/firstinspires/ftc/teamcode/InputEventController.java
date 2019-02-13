package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.Gamepad;

public class InputEventController {

    private Gamepad gamepad;

    private Thread server = new Thread() {
        public void run() {
            if (gamepad.a && !a.lastState) {
                a.pressed.run();
                a.lastState = true;
            }
            else if (a.lastState) {
                a.onEnd.run();
                a.lastState = false;
            }
            if (gamepad.b && !b.lastState) {
                b.pressed.run();
                b.lastState = true;
            }
            else if (b.lastState) {
                b.onEnd.run();
                b.lastState = false;
            }
            if (gamepad.x && !x.lastState) {
                x.pressed.run();
                x.lastState = true;
            }
            else if(x.lastState) {
                x.onEnd.run();
                x.lastState = false;
            }
            if (gamepad.y && !y.lastState) {
                y.pressed.run();
                y.lastState = true;
            }
            else if(y.lastState) {
                y.onEnd.run();
                y.lastState = false;
            }
            if (gamepad.dpad_left && !dpadLeft.lastState) {
                dpadLeft.pressed.run();
                dpadLeft.lastState = true;
            }
            else if(dpadLeft.lastState) {
                dpadLeft.onEnd.run();
                dpadLeft.lastState = false;
            }
            if (gamepad.dpad_down && !dpadDown.lastState) {
                dpadDown.pressed.run();
                dpadDown.lastState = true;
            }
            else if (dpadDown.lastState) {
                dpadDown.onEnd.run();
                dpadDown.lastState = false;
            }
            if (gamepad.dpad_up && !dpadUp.lastState) {
                dpadUp.pressed.run();
                dpadUp.lastState = true;
            }
            else if(dpadUp.lastState) {
                dpadUp.onEnd.run();
                dpadUp.lastState = false;
            }
            if (gamepad.dpad_right && !dpadRight.lastState) {
                dpadRight.pressed.run();
                dpadRight.lastState = true;
            }
            else if(dpadRight.lastState) {
                dpadRight.onEnd.run();
                dpadRight.lastState = false;
            }
            if (gamepad.start && !start.lastState) {
                start.pressed.run();
                start.lastState = true;
            }
            else if (start.lastState) {
                start.onEnd.run();
                start.lastState = false;
            }
            if (gamepad.right_trigger != 0) {
                rightTrigger.handler.run(gamepad.right_trigger);
                rightTrigger.lastState = true;
            }
            else if(rightTrigger.lastState) {
                rightTrigger.onEnd.run();
                rightTrigger.lastState = false;
            }
            if (gamepad.left_trigger != 0) {
                leftTrigger.handler.run(gamepad.left_trigger);
                leftTrigger.lastState = true;
            }
            else if (leftTrigger.lastState) {
                leftTrigger.onEnd.run();
                leftTrigger.lastState = false;
            }
            if (gamepad.left_stick_y !=0 || gamepad.left_stick_x != 0) {
                leftJoystick.handler.run(gamepad.left_stick_x, gamepad.left_stick_y);
                leftJoystick.lastState = true;
            }
            else if (leftJoystick.lastState) {
                leftJoystick.onEnd.run();
                leftJoystick.lastState = false;
            }
            if (gamepad.right_stick_y !=0 || gamepad.right_stick_x != 0) {
                rightJoystick.handler.run(gamepad.right_stick_x, gamepad.right_stick_y);
                rightJoystick.lastState = true;
            }
            else if (rightJoystick.lastState) {
                rightJoystick.onEnd.run();
                rightJoystick.lastState = false;
            }
        }
    };

    public ButtonHandler a = new ButtonHandler();
    public ButtonHandler b = new ButtonHandler();
    public ButtonHandler x = new ButtonHandler();
    public ButtonHandler y = new ButtonHandler();

    public ButtonHandler dpadUp = new ButtonHandler();
    public ButtonHandler dpadDown = new ButtonHandler();
    public ButtonHandler dpadLeft = new ButtonHandler();
    public ButtonHandler dpadRight = new ButtonHandler();

    public ButtonHandler start = new ButtonHandler();

    public TriggerHandler rightTrigger = new TriggerHandler();
    public TriggerHandler leftTrigger = new TriggerHandler();

    public JoystickHandler rightJoystick = new JoystickHandler();
    public JoystickHandler leftJoystick = new JoystickHandler();

    public InputEventController(Gamepad g) {
        gamepad = g;
    }
    public void start() {
        server.start();
    }
}
