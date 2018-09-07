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

@TeleOp(name="Claspy", group="Pushbot")
//@Disabled
public class Claspy2 extends OpMode {

    /* Declare OpMode members. */
    //OldHWDrive robot = new OldHWDrive(); // use the class created to define a Pushbot's hardware
    public Anvil anvil;

    public boolean speedMode = true;

    public double[] zoneList = new double[]{0.35, 0.5, 0.75};
    // could also use HardwarePushbotMatrix class.
    public void swapControllers() {
        Gamepad r = gamepad1;
        gamepad1 = gamepad2;
        gamepad2 = r;
    }
    public double computerAssistedZones() {
        double a = Math.sqrt(Math.pow(gamepad1.left_stick_x, 2) + Math.pow(gamepad1.left_stick_y, 2));
        return zoneList[(int) Math.ceil(a*zoneList.length) - 1];
    }

    /*
         * Code to run ONCE when the driver hits INIT
         */
    @Override
    public void init() {




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
        //Handle buttons first
        if (gamepad1.a) { //Emergency brake. Stops all motors immediately.
            anvil.rest();
            while (gamepad1.a) {
                if (!(gamepad1.a)) break;
            }
        }
        else if (gamepad1.b) {
            //Unused button
        }
        else if (gamepad1.x) {
            //Unused button
        }
        else if (gamepad1.y) {
            //Unused button
        }
        else if (gamepad1.dpad_left) speedMode ^= true;
        else if (gamepad1.dpad_down) swapControllers();
        //Handle controls
        if (gamepad1.atRest()) {
            anvil.rest();
        }
        else {
            if (Math.abs(gamepad1.left_stick_x) > Math.abs(gamepad1.left_stick_y)) {
                if (gamepad1.left_stick_x > 0) {
                    if (speedMode) {
                        anvil.turnLeft(computerAssistedZones());
                    } else {
                        anvil.turnLeft(gamepad1.left_stick_x);
                    }
                }
                else {
                    if (speedMode) {
                        anvil.turnRight(computerAssistedZones());
                    } else {
                        anvil.turnRight(-gamepad1.left_stick_x);
                    }
                }
            }
            else {
                if (gamepad1.left_stick_y > 0) {
                    if (speedMode) {
                        anvil.moveBackward(computerAssistedZones());
                    } else {
                        anvil.moveBackward(gamepad1.left_stick_y);
                    }
                }
                else {
                    if (speedMode) {
                        anvil.moveForward(computerAssistedZones());
                    } else {
                        anvil.moveForward(-gamepad1.left_stick_y);
                    }
                }
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
