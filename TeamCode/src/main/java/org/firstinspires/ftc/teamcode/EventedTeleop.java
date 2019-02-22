package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name="EventedClasp", group = "Pushbot")
public class EventedTeleop extends OpMode {

    InputEventController controller = new InputEventController();
    Anvil anvil = new Anvil();

    public void loop() {
        controller.run(gamepad1);
    }
    public void init() {
        anvil.init(hardwareMap, Anvil.drivetrain.MECHANUM, telemetry);
        controller.rightJoystick.handle((double x, double y) -> {
            if (Math.abs(x) > Math.abs(y)) anvil.turnLeft(x);
            else anvil.moveBackward(y);
        });
        controller.rightJoystick.after(() -> anvil.rest());
    }
}
