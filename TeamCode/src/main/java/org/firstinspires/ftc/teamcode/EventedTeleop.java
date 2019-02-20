package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name="EventedClasp", group = "Pushbot")
public class EventedTeleop extends OpMode {

    InputEventController controller = new InputEventController(gamepad1);
    Anvil anvil = new Anvil();

    public void loop() {

    }
    public void init() {
        anvil.init(hardwareMap, Anvil.drivetrain.MECHANUM, telemetry);
        controller.rightJoystick.handle(new JoystickHandler.JoystickRunnable() {
            @Override
            public void run(double x, double y) {
                if (Math.abs(x) > Math.abs(y)) anvil.turnLeft(x);
                else anvil.moveBackward(y);
            }
        });
        controller.rightJoystick.after(new Runnable() {
            @Override
            public void run() {
                anvil.rest();
            }
        });
    }
    public void start() {

    }
}
