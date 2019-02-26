/* Copyright (c) 2017 FIRST. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification,
 * are permitted (subject to the limitations in the disclaimer below) provided that
 * the following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice, this list
 * of conditions and the following disclaimer.
 *
 * Redistributions in binary form must reproduce the above copyright notice, this
 * list of conditions and the following disclaimer in the documentation and/or
 * other materials provided with the distribution.
 *
 * Neither the name of FIRST nor the names of its contributors may be used to endorse or
 * promote products derived from this software without specific prior written permission.
 *
 * NO EXPRESS OR IMPLIED LICENSES TO ANY PARTY'S PATENT RIGHTS ARE GRANTED BY THIS
 * LICENSE. THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
 * CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package org.firstinspires.ftc.teamcode;

import com.disnodeteam.dogecv.CameraViewDisplay;
import com.disnodeteam.dogecv.DogeCV;
import com.disnodeteam.dogecv.detectors.roverrukus.GoldAlignDetector;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.matrices.OpenGLMatrix;
import org.firstinspires.ftc.robotcore.external.matrices.VectorF;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackable;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackableDefaultListener;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackables;

import java.util.ArrayList;
import java.util.List;

import static org.firstinspires.ftc.robotcore.external.navigation.AngleUnit.DEGREES;
import static org.firstinspires.ftc.robotcore.external.navigation.AxesOrder.XYZ;
import static org.firstinspires.ftc.robotcore.external.navigation.AxesOrder.YZX;
import static org.firstinspires.ftc.robotcore.external.navigation.AxesReference.EXTRINSIC;
import static org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer.CameraDirection.BACK;
import static org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer.CameraDirection.FRONT;
import static org.firstinspires.ftc.teamcode.Anvil.drivetrain.MECHANUM;
import static org.firstinspires.ftc.teamcode.Anvil.drivetrain.WEST_COAST;
import static org.firstinspires.ftc.teamcode.Anvil.mPos.CENTER;
import static org.firstinspires.ftc.teamcode.Anvil.mPos.LEFT;
import static org.firstinspires.ftc.teamcode.Anvil.mPos.RIGHT;
import static org.firstinspires.ftc.teamcode.Anvil.mPos.UNKNOWN;

/**
 * This file contains an minimal example of a Linear "OpMode". An OpMode is a 'program' that runs in either
 * the autonomous or the teleop period of an FTC match. The names of OpModes appear on the menu
 * of the FTC Driver Station. When an selection is made from the menu, the corresponding OpMode
 * class is instantiated on the Robot Controller and executed.
 *
 * This particular OpMode just executes a basic Tank Drive Teleop for a two wheeled robot
 * It includes all the skeletal structure that all linear OpModes contain.
 *
 * Use Android Studios to Copy this Class, and Paste it into your team's code folder with a new name.
 * Remove or comment out the @Disabled line to add this opmode to the Driver Station OpMode list
 */

@Autonomous(name="DepotAuton", group="Linear Opmode")
//@Disabled

public class DepotAuton extends LinearOpMode {
    Anvil anvil = new Anvil();

    // Declare OpMode members.
    Anvil.mPos Dpos = UNKNOWN;
    private ElapsedTime runtime = new ElapsedTime();
    private GoldAlignDetector detector;
    //-------------------------------------------------------------------------------------------
    @Override
    public void runOpMode() {
        anvil.init(hardwareMap, MECHANUM, telemetry);
        telemetry.addData("Status", "Initialized");
        runtime.reset();
        telemetry.update();

        // Set up detector
        detector = new GoldAlignDetector(); // Create detector
        detector.init(hardwareMap.appContext, CameraViewDisplay.getInstance(), 1, false); // Initialize it with the app context and camera
        detector.useDefaults(); // Set detector to use default settings

        // Optional tuning
        detector.alignSize = 100; // How wide (in pixels) is the range in which the gold object will be aligned. (Represented by green bars in the preview)
        detector.alignPosOffset = 0; // How far from center frame to offset this alignment zone.
        detector.downscale = 0.4; // How much to downscale the input frames

        detector.areaScoringMethod = DogeCV.AreaScoringMethod.MAX_AREA; // Can also be PERFECT_AREA
        //detector.perfectAreaScorer.perfectArea = 10000; // if using PERFECT_AREA scoring
        detector.maxAreaScorer.weight = 0.005; //

        detector.ratioScorer.weight = 5; //
        detector.ratioScorer.perfectRatio = 1.0; // Ratio adjustment

        waitForStart();
        anvil.liftMov(3800, -1); //lower the lift
        anvil.moveFB(100, -1);
        anvil.moveLR(100, 1);
        sleep(100);
        anvil.moveLR(500, -1);
        anvil.turn(550, 0.5); //turning to align with center
        anvil.cServo(anvil.phoneServo, 0.7);
        detector.enable(); // Start the detector!
        sleep(1500);
        sleep(1000);
        if (detector.getXPosition() > 100 && detector.getXPosition() < 600) {
            Dpos = CENTER;
        } else {
            anvil.turn(450, 0.5);
            sleep(500);
            if (detector.getXPosition() > 100 && detector.getXPosition() < 600) {
                Dpos = LEFT;
            } else {
                anvil.turn(950, -0.5);
                sleep(500);
                if (detector.getXPosition() > 100 && detector.getXPosition() < 600) {
                    Dpos = RIGHT;
                } else {
                    anvil.turn(350, 0.5);
                    Dpos = RIGHT;
                }
            }
        }
        anvil.cServo(anvil.phoneServo, 0.3);
        if (Dpos == CENTER){
            anvil.turn(400, 0.5);
            anvil.moveFB(500, -1); //Back from lander position (This should knock off the jewel as well
            sleep(700);
            anvil.moveFB(500, 1);
        } else if (Dpos == LEFT){
            anvil.turn(400, 0.5);
            anvil.moveFB(900, -1); //Back from lander position (This should knock off the jewel as well`
           sleep(700);
            anvil.moveFB(900, 1);
            anvil.turn(450, -0.5);
        } else if (Dpos == RIGHT){
            anvil.turn(400, 0.5);
            anvil.moveFB(900, -1); //Back from lander position (This should knock off the jewel as well
            sleep(700);
            anvil.moveFB(900, 1);
            anvil.turn(550, 0.5);
        }
        anvil.moveFB(500, -1);
        anvil.servoMov(0.3, 0.7); //Moving the birdcage platform so arm does not get stuck
        anvil.armMov(2500, -0.5); //Moving arm to release marker
        sleep(500); //Waiting a bit so that arm is not out while the robot is moving
        anvil.armMov(2500, 0.5); //Bringing back the arm
        anvil.servoMov(0.6, 0.4); //Putting up the bird cage
        sleep(200);
        anvil.moveFB(600, 1);
        anvil.turn(500, 1);
        anvil.moveFB(2500, 1); //Moving towards the crater */


        while (opModeIsActive() && runtime.milliseconds() < 30000) {
            telemetry.addData("M1 Encoder", anvil.motor1.getCurrentPosition());
            telemetry.addData("M2 Encoder", anvil.motor2.getCurrentPosition());
            telemetry.addData("M3 Encoder", anvil.motor3.getCurrentPosition());
            telemetry.addData("M4 Encoder", anvil.motor4.getCurrentPosition());
            telemetry.addData("Dpos", Dpos);
            telemetry.addData("IsAligned" , detector.getAligned()); // Is the bot aligned with the gold mineral?
            telemetry.addData("X Pos" , detector.getXPosition()); // Gold X position.
            telemetry.update();
        }

    }

}