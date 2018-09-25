package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Gamepad;

/**
 * This file provides basic Telop driving for a Pushbot robot.
 * The code is structured as an Iterative OpMode
 *
 * This OpMode uses the common Pushbot hardware class to define the devices on the robot.
 * All device access is managed through the HardwarePushbot class.
 *
 * This particular OpMode executes a basic Tank Drive Teleop for a PushBot
 * It raises and lowers the claw using the Gampad Y and A buttons respectively.
 * It also opens and closes the claws slowly using the left and right Bumper buttons.
 *
 * Use Android Studios to Copy this Class, and Paste it into your team's code folder with a new name.
 * Remove or comment out the @Disabled line to add this opmode to the Driver Station OpMode list
 */

@TeleOp(name="Bernard", group="Pushbot")
//@Disabled
public class HoloClaspy extends OpMode {

    private Anvil anvil = new Anvil();
    private boolean speedMode = false;

    @Override
    public void init() {
        anvil.init(hardwareMap, "HOLONOMIC", telemetry);
    }

    @Override
    public void loop() {
        telemetry.addData("R_JoystickX", gamepad1.left_stick_x);
        telemetry.addData("R_JoystickY", gamepad1.left_stick_y);
        telemetry.addData("SpeedMode", speedMode);

        //Handle buttons first
        if (gamepad1.a) { //Emergency brake. Stops all motors immediately.
            anvil.rest();
            while (gamepad1.a) {
                if (!(gamepad1.a)) break;
            }
        } else if (gamepad1.b) {
            //Unused button
        } else if (gamepad1.x) {
            //Unused button
        } else if (gamepad1.y) {
            //Unused button
        } else if (gamepad1.dpad_left) {
            speedMode ^= true; //evil bitwise witchcraft
            //Handle controls
            if (gamepad1.atRest()) {
                anvil.rest();
            } else {
                if (Math.abs(gamepad1.right_stick_x) > 0) {
                    anvil.turnLeft(gamepad1.right_stick_x);
                }
                if (Math.abs(gamepad1.left_stick_x) > Math.abs(gamepad1.left_stick_y)) {
                    anvil.holoMoveLeft(gamepad1.left_stick_x);
                } else {
                    anvil.moveBackward(gamepad1.left_stick_y);
                }
            }
        }
    }
}