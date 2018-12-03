/*
Copyright (c) 2016 Robert Atkinson

All rights reserved.

Redistribution and use in source and binary forms, with or without modification,
are permitted (subject to the limitations in the disclaimer below) provided that
the following conditions are met:

Redistributions of source code must retain the above copyright notice, this list
of conditions and the following disclaimer.

Redistributions in binary form must reproduce the above copyright notice, this
list of conditions and the following disclaimer in the documentation and/or
other materials provided with the distribution.

Neither the name of Robert Atkinson nor the names of his contributors may be used to
endorse or promote products derived from this software without specific prior
written permission.

NO EXPRESS OR IMPLIED LICENSES TO ANY PARTY'S PATENT RIGHTS ARE GRANTED BY THIS
LICENSE. THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
"AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESSFOR A PARTICULAR PURPOSE
ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE
FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR
TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF
THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
*/
package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

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

@TeleOp(name="HoloTeleop", group="Pushbot")
@Disabled
public class HoloTeleop extends OpMode {

    /* Declare OpMode members. */
    OldHWDrive robot = new OldHWDrive(); // use the class created to define a Pushbot's hardware
    // could also use HardwarePushbotMatrix class.
//First Commit

    /*
         * Code to run ONCE when the driver hits INIT
         */
    @Override
    public void init() {
    robot.motor1 = hardwareMap.dcMotor.get("motor1");
    robot.motor2 = hardwareMap.dcMotor.get("motor2");
    robot.motor3 = hardwareMap.dcMotor.get("motor3");
    robot.motor4 = hardwareMap.dcMotor.get("motor4");
    robot.clawMotor = hardwareMap.dcMotor.get("clawMotor");
    robot.servo1 = hardwareMap.servo.get("servo1");
    robot.servo2 = hardwareMap.servo.get("servo2");
    robot.jewelServo = hardwareMap.servo.get("jewelServo");

    }

    /*
     * Code to run REPEATEDLY after the driver hits INIT, but before they hit PLAY
     */
    @Override
    public void init_loop() {


    }

    /*
     * Code to run ONCE when the driver hits PLAY
     */
    @Override
    public void start() {
        //-------------------------------------------------------------------------------------
        //Variable defining
  // robot.servo1.setPosition(0.5);
  //  robot.servo2.setPosition(0.5);

    }




    /*
     * Code to run REPEATEDLY after the driver hits PLAY but before they hit STOP
     */

    double motorPower = 0.35;
    @Override
    public void loop() {
    /*---------IF GOING REVERSE, CHANGE ALL 1s to -1, and -1 to 1-----------*/

       telemetry.addData("R_JoystickX", gamepad1.left_stick_x);
        telemetry.addData("R_JoystickY", gamepad1.left_stick_y);
        telemetry.addData("L_JoystickX", gamepad1.right_stick_x);
        telemetry.addData("L_JoystickY", gamepad1.right_stick_y);
        telemetry.addData("Right_Trigger", gamepad1.right_trigger);
        telemetry.addData("Left_Trigger", gamepad1.left_trigger);
       telemetry.addData("Servo1", robot.servo1.getPosition());
        telemetry.addData("Servo2", robot.servo2.getPosition());
       telemetry.addData("Motorspeed", motorPower);
        //1 = clockwise. -1 = counterclockwise
        //---------------------------------------------------------------------------------------
         robot.jewelServo.setPosition(0);
         if (gamepad1.a == true) {
         motorPower = 0.25;
         }
         if (gamepad1.b == true) {
         motorPower = 0.35;
         }
         if (gamepad1.y == true) {
         motorPower = 0.5;
         }
         if (gamepad1.x == true) {
         motorPower = 1;
         }
        //---------------------------------------------------------------------------------------
        //Move backward Function
            if (gamepad1.left_stick_y == 1) {
            robot.motor1.setPower(-motorPower);  //cw
            robot.motor2.setPower(motorPower); //ccw
            robot.motor3.setPower(-motorPower);  //cw
            robot.motor4.setPower(motorPower); //ccw
        }
        robot.motor1.setPower(gamepad1.left_stick_y);
        robot.motor2.setPower(gamepad1.left_stick_y);
        robot.motor3.setPower(gamepad1.right_stick_y);
        robot.motor4.setPower(gamepad1.right_stick_y);
        //stops movement when joysticks are at 0
            if (gamepad1.left_stick_y == 0 & gamepad2.right_stick_y == 0) {
            robot.motor1.setPower(0);
            robot.motor2.setPower(0);
            robot.motor3.setPower(0);
            robot.motor4.setPower(0);
        }
        //---------------------------------------------------------------------------------------
        //Move left Function
            if (gamepad1.left_stick_x == 1) {   /*Y JOYSTICK MAY NEED TO BE CHANGED, ADD TELEMETRY*/
            robot.motor1.setPower(motorPower);
            robot.motor2.setPower(motorPower);
            robot.motor3.setPower(-motorPower);
            robot.motor4.setPower(-motorPower);
        }
        //----------------------------------------------------------------------------------------
        //move forward function
        if (gamepad1.left_stick_y == -1) {
            robot.motor1.setPower(motorPower);
            robot.motor2.setPower(-motorPower);
            robot.motor3.setPower(motorPower);
            robot.motor4.setPower(-motorPower);
        }
        //----------------------------------------------------------------------------------------
        //move right function
        if (gamepad1.left_stick_x == -1) {
            robot.motor1.setPower(-motorPower);
            robot.motor2.setPower(-motorPower);
            robot.motor3.setPower(motorPower);
            robot.motor4.setPower(motorPower);
        }
        //----------------------------------------------------------------------------------------
        //rotate counter clockwise function
        if (gamepad1.right_stick_x == 1) {
            robot.motor1.setPower(-motorPower);
            robot.motor2.setPower(-motorPower);
            robot.motor3.setPower(-motorPower);
            robot.motor4.setPower(-motorPower);
        }
        //----------------------------------------------------------------------------------------
        //rotate clockwise
        if (gamepad1.right_stick_x == -1) {
            robot.motor1.setPower(motorPower);
            robot.motor2.setPower(motorPower);
            robot.motor3.setPower(motorPower);
            robot.motor4.setPower(motorPower);
        }
        //----------------------------------------------------------------------------------------
        if (gamepad1.right_trigger > 0.5) {
            robot.motor1.setPower(-motorPower);
            robot.motor4.setPower(motorPower);
        }

        if (gamepad1.left_bumper == true) {
            robot.motor2.setPower(motorPower);
            robot.motor3.setPower(-motorPower);
        }


        if (gamepad1.right_bumper == true) {
            robot.motor2.setPower(-motorPower);
            robot.motor3.setPower(motorPower);
        }
        if (gamepad1.left_trigger > 0.5) {
            robot.motor1.setPower(motorPower);
            robot.motor4.setPower(-motorPower);
        }

        if (gamepad2.left_stick_y == -1) {
            robot.clawMotor.setPower(1);
            /* Attempt to record position of the claw arm. May desync. Requires testing
            armIteratesFor++;
             */
        }
         if (gamepad2.left_stick_y == 1) {
            robot.clawMotor.setPower(-1);
        }
        if (gamepad2.left_stick_y == 0) {
           robot.clawMotor.setPower(0);
        }

        if (gamepad2.b) {
        robot.servo1.setPosition(0);
        robot.servo2.setPosition(1);
        }

        if (gamepad2.y) {
        robot.servo1.setPosition(0.6);
        robot.servo2.setPosition(0.3);
        }

        if (gamepad2.x) {
        robot.servo1.setPosition(0.55);
        robot.servo2.setPosition(0.45);
        }

        if (gamepad2.a) {
        robot.servo1.setPosition(0.15);
        robot.servo2.setPosition(0.85);
        }

        if (gamepad2.left_stick_y == 0 && gamepad2.left_stick_x == 0) {
        robot.clawMotor.setPower(0);
        }
    }



    /*
 * Code to run ONCE after the driver hits STOP
 */

    @Override
    public void stop() {

    }
}

