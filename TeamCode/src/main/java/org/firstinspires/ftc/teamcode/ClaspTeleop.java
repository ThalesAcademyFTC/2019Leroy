package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Gamepad;

/**
 * Created by dcrenshaw on 3/4/18.
 * Clasp is an intelligent teleop designed to interface with Anvil and provide an overhaul to
 * classic teleop.
 */

@TeleOp(name="Clasp", group="Pushbot")
public class ClaspTeleop extends OpMode {

    private Anvil anvil = new Anvil();

    @Override
    public void init() {
        anvil.init(hardwareMap, "WEST_COAST", telemetry);
        Competition.init(hardwareMap);
    }

    @Override
    public void loop() {
        telemetry.addData("R_JoystickX", gamepad1.left_stick_x);
        telemetry.addData("R_JoystickY", gamepad1.left_stick_y);

        //Handle buttons first
        if (gamepad1.a) { //Emergency brake. Stops all motors immediately.
            anvil.rest();
            while (gamepad1.a) {
                if (!(gamepad1.a)) break;
            }
        } else if (gamepad1.left_trigger != .0f) {
            //Grab things
        }
        //Handle controls
        if (gamepad1.atRest()) {
            anvil.rest();
        } else {
            if (Math.abs(gamepad1.left_stick_x) > Math.abs(gamepad1.left_stick_y)) {
                anvil.turnLeft(gamepad1.left_stick_x);
            } else {
                anvil.moveBackward(gamepad1.left_stick_y);
            }
        }
    }
}