package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Gamepad;

import static org.firstinspires.ftc.teamcode.Anvil.drivetrain.TANK;
import static org.firstinspires.ftc.teamcode.Anvil.drivetrain.WEST_COAST;

/**
 * Created by dcrenshaw on 3/4/18.
 * Clasp is an intelligent teleop designed to interface with Anvil and provide an overhaul to
 * classic teleop.
 */

@TeleOp(name="Bolt", group="Pushbot")
public class ClaspTeleop extends OpMode {
    //Initiates the Anvil object.
    private Anvil anvil = new Anvil();
    @Override
    public void start() {
    }

    @Override
    public void init() {
        //Chooses the drive train from Anvil
        anvil.init(hardwareMap, TANK, telemetry);
        //Below brings in competition specific teleop code, dealing with the scoring mechanism.
    }

    @Override
    public void loop() {
        //Basic Telemetry to help with controller testing, No longer needed.
        telemetry.addData("R_JoystickX", gamepad1.left_stick_x);
        telemetry.addData("R_JoystickY", gamepad1.left_stick_y);

        //Uses Buttons
        if (gamepad1.a) {}
        else if (gamepad1.y) {}


        if (gamepad1.b) {}
        else if(gamepad1.x) {}
        //Deals with controls for drive train.
        if (gamepad1.atRest()) {
            //Ensures that if the gamepad is at rest, then the robot does not move.
            anvil.rest();
        } else {
            if (Math.abs(gamepad1.right_stick_x) > Math.abs(gamepad1.left_stick_y)) {
                //Turns right when left stick is negative.
                anvil.turnLeft(gamepad1.left_stick_x);
            } else {
                //Uses diff function, which means that the robot goes forward and backward,
                //Also allows robot to turn and move forward simultaneously.
                anvil.diff(gamepad1.left_stick_x, -gamepad1.left_stick_y);
            }
        }
        //Deals with controls for drive train.
        if (gamepad2.atRest()) {
            //Ensures that if the gamepad is at rest, then the robot does not move.
            anvil.rest();
        } else {
            if (Math.abs(gamepad2.right_stick_x) > Math.abs(gamepad2.left_stick_y)) {
                //Turns right when left stick is negative.
                anvil.turnLeft(gamepad2.left_stick_x);
            } else {
                //Uses diff function, which means that the robot goes forward and backward,
                //Also allows robot to turn and move forward simultaneously.
                anvil.diff(gamepad2.left_stick_x, -gamepad2.left_stick_y);
            }
        }
    }
}