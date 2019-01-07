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

import static org.firstinspires.ftc.teamcode.Anvil.drivetrain.MECHANUM;
import static org.firstinspires.ftc.teamcode.Anvil.drivetrain.WEST_COAST;

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

@TeleOp(name="Freddy", group = "Pushbot")
//@Disabled
public class MechanumClaspy extends OpMode {

    /* Declare OpMode members. */
    //OldHWDrive robot = new OldHWDrive(); // use the class created to define a Pushbot's hardware
    //public Anvil anvil;
    Anvil anvil = new Anvil();
    boolean aSwap = true;
    /*
     * Code to run ONCE when the driver hits INIT
     */
    @Override
    public void init() {
        anvil.init(hardwareMap, MECHANUM, telemetry);
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


    }




    /*
     * Code to run REPEATEDLY after the driver hits PLAY but before they hit STOP
     */

    @Override
    public void loop() {
        telemetry.addData("R_JoystickX", gamepad1.left_stick_x);
        telemetry.addData("R_JoystickY", gamepad1.left_stick_y);
        //Handle buttons first
        if (gamepad1.a && aSwap) {
            anvil.cServo(anvil.cageServo, 0);
            aSwap = false;
        } else if (gamepad1.a && !aSwap){
            anvil.cServo(anvil.cageServo, 0.5);
            aSwap = true;
        }
        if (gamepad1.b) {
            anvil.servoMov(0.5, 0.5);
        } else if (gamepad1.x){
            anvil.servoMov(0.1, 0.9);
        } else if (gamepad1.y){
            anvil.servoMov(0.9, 0.1);
        }
        if (gamepad1.dpad_up){
            anvil.customMov(anvil.slideMotor, 0.5);
        } else if (gamepad1.dpad_down){
            anvil.customMov(anvil.slideMotor, -0.5);
        } else if (!gamepad1.dpad_up && !gamepad1.dpad_down) {
            anvil.customMov(anvil.slideMotor, 0);
        }
        if (gamepad1.atRest()) {
            //Used to ensure that the robot does not move while the controller is at rest.
            anvil.rest();
        } else {
            if (gamepad1.right_trigger > 0){
                anvil.customMov(anvil.armMotor, gamepad1.right_trigger/2);
            } else if (gamepad1.left_trigger > 0){
                anvil.customMov(anvil.armMotor, -gamepad1.left_trigger/2);
            } else if (gamepad1.right_trigger == 0 && gamepad1.left_trigger == 0){
                anvil.customMov(anvil.armMotor,0);
            }
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


    /*
 * Code to run ONCE after the driver hits STOP
 */

    @Override
    public void stop() {

    }
}

