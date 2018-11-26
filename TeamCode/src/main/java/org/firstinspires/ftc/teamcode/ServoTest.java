package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;

import static org.firstinspires.ftc.teamcode.Anvil.drivetrain.HOLONOMIC;
import static org.firstinspires.ftc.teamcode.Anvil.drivetrain.TEST;

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

@TeleOp(name="ServoTest", group="Pushbot")
//@Disabled
public class ServoTest extends OpMode {
    //Initializing the Hardware Drive Anvil
    OldHWDrive robot = new OldHWDrive();
    double servoSpeed;
    @Override
    public void init() {
    //Choosing the drive train from Anvil.
        robot.servo1 = hardwareMap.servo.get("servo1");
    }

    @Override
    public void loop() {
        //Basic Telemetry used for testing with controller
        telemetry.addData("Servo Position", robot.servo1.getPosition());
        telemetry.addData("Servo Speed", servoSpeed);

        //Handle buttons first
        servoSpeed = gamepad1.left_stick_y;
        robot.servo1.setPosition(servoSpeed);
        }
    }
