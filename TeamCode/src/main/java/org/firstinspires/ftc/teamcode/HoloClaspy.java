package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Gamepad;

import static org.firstinspires.ftc.teamcode.Anvil.drivetrain.HOLONOMIC;

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

@TeleOp(name="Steve", group="Pushbot")
//@Disabled
public class HoloClaspy extends OpMode {
    //Initializing the Hardware Drive Anvil
    private Anvil anvil = new Anvil();

    @Override
    public void init() {
    //Choosing the drive train from Anvil.
        anvil.init(hardwareMap, HOLONOMIC, telemetry);
    }

    @Override
    public void loop() {
        //Basic Telemetry used for testing with controller
        telemetry.addData("R_JoystickX", gamepad1.left_stick_x);
        telemetry.addData("R_JoystickY", gamepad1.left_stick_y);
        //Handle buttons first
        //Gamepad 1
        if (gamepad1.a) {
            anvil.servoMov(0.15, 0.85);
        } else if (gamepad1.b) {
            anvil.servoMov(0, 1);
        } else if (gamepad1.x) {
            anvil.servoMov(0.55, 0.45);
        } else if (gamepad1.y) {
            anvil.servoMov(0.3, 0.9);
        }
        if (!gamepad1.atRest()){
            anvil.clawMov(gamepad1);
        }
            if (gamepad1.atRest()) {

            //Used to ensure that the robot does not move while the controller is at rest.
                anvil.rest();
            } else {
                //Moves the robot to the left if the right stick is moved to the left.
                //Since left is the opposite of right, the right stick receiving a negative value makes the robot turn right.
                if (Math.abs(gamepad1.right_stick_x) > 0) {
                    anvil.turnLeft(gamepad1.right_stick_x);
                }
                //Below decides whether the left stick x value is farther from the origin than the y value.
                //This decides whether the robot needs to move sideways or forward/backward.
                if (Math.abs(gamepad1.left_stick_x) > Math.abs(gamepad1.left_stick_y)) {
                    anvil.holoMoveLeft(gamepad1.left_stick_x);
                } else {
                    anvil.moveBackward(gamepad1.left_stick_y);
                }
            }

        }
    }
