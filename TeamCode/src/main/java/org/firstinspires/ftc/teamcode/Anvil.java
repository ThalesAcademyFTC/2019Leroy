
package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.matrices.OpenGLMatrix;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;

import java.util.ArrayList;

import static org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer.CameraDirection.BACK;

/**
 * Created by dcrenshaw on 3/3/18.
 * Extensible class derived from Metal. Used to invoke Metal upon drive trains other than Holonomic
 */

public class Anvil {
    //Define servo and motor variables
    public DcMotor motor1, motor2, motor3, motor4, motor5;
    public DcMotor clawMotor;
    public Servo servo1, servo2;
    public Servo jewelServo;
    //Reference to mapped servo/motor controller
    private HardwareMap hwMap;

    public Gamepad controller1, controller2;

    public Telemetry telemetry;

    private double prevailingSpeed = 0.5;

    public DcMotor[] forward, right, left, movLeft, movRight;

    public boolean hs = true;

    public enum drivetrain {
        HOLONOMIC,
        TANK,
        WEST_COAST,
        MECHANUM,
        OMNIDRIVE,
        SWERVE
    }

    public void init (HardwareMap ahwMap, drivetrain type, Telemetry telem){
        hwMap = ahwMap;

        telemetry = telem;



        //Each of these cases are for different drive trains, the setup for each drive train is within.
        switch (type) {
            /*Example drive train:
            case "TRAIN_NAME":
                //Map all motors to proper variables.
                motor1 = hwMap.dcMotor.get("motor1");
                motor2 = hwMap.dcMotor.get("motor2");
                //Set motor directions. These should all be set so that power 1 for all
                //motors == robot moves forwards.
                motor1.setDirection(DcMotor.Direction.REVERSE);
                motor2.setDirection(DcMotor.Direction.FORWARD);
                //Set motor purposes for maneuvers. Motors in 'right' are the motors which must
                //move in reverse for the robot to turn right, and the same applies for left.
                //'forward' should contain all motors.
                forward = new DcMotor[]{motor1, motor2};
                right = new DcMotor[]{motor1};
                left = new DcMotor[]{motor2};
                // for drivetrains that can shift left and right, a movRight and movLeft array must be added
                //that will implement these functions
                break;
             */

            case OMNIDRIVE:
                //Weird drive train, only two wheels move for the robot to go forward. Will need to
                // consider this when programming the robot to move.
                motor1 = hwMap.dcMotor.get("motor1");
                motor2 = hwMap.dcMotor.get("motor1");
                motor3 = hwMap.dcMotor.get("motor3");
                motor4 = hwMap.dcMotor.get("motor4");
                clawMotor = hwMap.dcMotor.get("clawMotor");
                servo1 = hwMap.servo.get("servo1");
                motor1.setDirection(DcMotor.Direction.FORWARD);
                motor2.setDirection(DcMotor.Direction.REVERSE);
                motor3.setDirection(DcMotor.Direction.FORWARD);
                motor3.setDirection(DcMotor.Direction.REVERSE);
                forward = new DcMotor[]{motor1, motor2, motor3, motor4};
                right = new DcMotor[]{motor1, motor3};
                left = new DcMotor[]{motor2, motor4};
                break;


            case HOLONOMIC:
                //Assign motors
                clawMotor = hwMap.dcMotor.get("clawMotor");
                servo1 = hwMap.servo.get("servo1");
                servo2 = hwMap.servo.get("servo2");
                jewelServo = hwMap.servo.get("jewelServo");
                motor1 = hwMap.dcMotor.get("motor1");
                motor2 = hwMap.dcMotor.get("motor2");
                motor3 = hwMap.dcMotor.get("motor3");
                motor4 = hwMap.dcMotor.get("motor4");
                //Set motor directions
                motor1.setDirection(DcMotor.Direction.FORWARD);
                motor2.setDirection(DcMotor.Direction.REVERSE);
                motor3.setDirection(DcMotor.Direction.FORWARD);
                motor4.setDirection(DcMotor.Direction.REVERSE);
                //Set motor purposes
                forward = new DcMotor[]{motor1, motor2, motor3, motor4};
                right = new DcMotor[]{motor2, motor4};
                left = new DcMotor[]{motor1, motor3};
                movLeft = new DcMotor[]{motor2, motor3};
                movRight = new DcMotor[]{motor1, motor4};
                hs = false;
                break;
            case TANK:
                motor1 = hwMap.dcMotor.get("motor1");
                motor2 = hwMap.dcMotor.get("motor2");
                motor1.setDirection(DcMotor.Direction.REVERSE);
                motor2.setDirection(DcMotor.Direction.FORWARD);
                forward = new DcMotor[]{motor1, motor2};
                right = new DcMotor[]{motor1};
                left = new DcMotor[]{motor2};
                break;
            case WEST_COAST:
                motor1 = hwMap.dcMotor.get("motor1");
                motor2 = hwMap.dcMotor.get("motor2");
                motor3 = hwMap.dcMotor.get("motor3");
                motor4 = hwMap.dcMotor.get("motor4");
                motor1.setDirection(DcMotor.Direction.REVERSE);
                motor2.setDirection(DcMotor.Direction.FORWARD);
                motor3.setDirection(DcMotor.Direction.FORWARD);
                motor4.setDirection(DcMotor.Direction.REVERSE);
                forward = new DcMotor[]{motor1, motor2, motor3, motor4};
                right = new DcMotor[]{motor1, motor4};
                left = new DcMotor[]{motor2, motor3};
                break;
            case MECHANUM:
                //Map all motors to proper variables.
                motor1 = hwMap.dcMotor.get("motor1");
                motor2 = hwMap.dcMotor.get("motor2");
                motor3 = hwMap.dcMotor.get("motor3");
                motor4 = hwMap.dcMotor.get("motor4");
                motor1.setDirection(DcMotor.Direction.FORWARD);
                motor2.setDirection(DcMotor.Direction.FORWARD);
                motor3.setDirection(DcMotor.Direction.FORWARD);
                motor4.setDirection(DcMotor.Direction.FORWARD);
                forward = new DcMotor[]{motor1, motor2, motor3, motor4};
                right = new DcMotor[]{motor1, motor3};
                left = new DcMotor[]{motor2, motor4};
                movRight = new DcMotor[]{motor1, motor2};
                movLeft = new DcMotor[]{motor3, motor4};
                break;
            case SWERVE:
                motor1 = hwMap.dcMotor.get("motor1");
                motor2 = hwMap.dcMotor.get("motor2");
                motor3 = hwMap.dcMotor.get("motor3");
                motor4 = hwMap.dcMotor.get("motor4");
                motor5 = hwMap.dcMotor.get("motor5");
                motor1.setDirection(DcMotor.Direction.FORWARD);
                motor2.setDirection(DcMotor.Direction.FORWARD);
                motor3.setDirection(DcMotor.Direction.FORWARD);
                motor4.setDirection(DcMotor.Direction.FORWARD);
                motor5.setDirection(DcMotor.Direction.FORWARD);
                forward = new DcMotor[]{motor1, motor2, motor3, motor4};
                right = new DcMotor [] {motor5};
                left = new DcMotor[] {motor5};
            default:
                telemetry.addLine("Invalid type " + type + " passed to Anvil's init function. Nothing has been set up.");
                break;
        }
        for (DcMotor x : forward) {x.setPower(0); x.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);}
    }
    public void initCustom(HardwareMap ahwMap, Telemetry telem, DcMotor[] rightI, DcMotor[] leftI, DcMotor[] forwardI, DcMotor.Direction[] orderedDirections) {
        //Allows initialization of custom drive trains not in list programatically.
        telemetry = telem;
        hwMap = ahwMap;
        right = rightI;
        left = leftI;
        forward = forwardI;
        for (int x = 0; x < forward.length; x++) {
            forward[x].setDirection(orderedDirections[x]);
        }
    }
    //Movement, turning, and resting methods for the all current drive trains
    public void moveForward(double pace) {for (DcMotor x:forward) x.setPower(pace);}
    public void turnRight(double pace) {
        if (hs) {
            for (DcMotor x:right) x.setPower(-pace / 2);
            for (DcMotor x:left) x.setPower(pace / 2);
        }
        else {
            for (DcMotor x:right) x.setPower(-pace);
            for (DcMotor x:left) x.setPower(pace);
        }
    }
    public void turnLeft(double pace) {
        if (hs) {
            for (DcMotor x:left) x.setPower(-pace / 2);
            for (DcMotor x:right) x.setPower(pace / 2);
        }
        else {
            for (DcMotor x:left) x.setPower(-pace);
            for (DcMotor x:right) x.setPower(pace);
        }
    }
    public void moveBackward(double pace) {
        for (DcMotor x:forward)
            x.setPower(-pace);
    }
    public void clawMov(Gamepad gamepad) {
        if (gamepad.left_trigger > 0){
            clawMotor.setPower(gamepad.left_trigger);
        } else if (gamepad.right_trigger > 0){
            clawMotor.setPower(-gamepad.right_trigger);
        }
    }
    public void servoMov(double pace, double pace2){
        servo1.setPosition(pace);
        servo2.setPosition(pace2);
    }
    public void rest() {for (DcMotor x:forward) x.setPower(0);}

    //Experimental function to turn while moving forward. Increases Maneuverability of robot.
    //ctx = controller x
    public void diff(double ctx, double pace) {
        for (DcMotor x:left) x.setPower(pace - (ctx * 2));
        for (DcMotor x:right) x.setPower(pace + (ctx * 2));
    }

    //Holonomic specific movements
    public void holoMoveRight(double pace) {
        for (DcMotor x:movLeft) x.setPower(pace);
        for (DcMotor x:movRight) x.setPower(-pace);
    }
    public void holoMoveLeft(double pace) {
        for (DcMotor x:movLeft) x.setPower(-pace);
        for (DcMotor x:movRight) x.setPower(pace);
    }

    //Experimental Autonomous Code that locates the robot on the field using 2 distance sensors at 90 degree angles.
    //Not needed any more because vuforia navigation targets can be used more efficiently.
    public void distDeg(double initx, double inity, double posX, double posY) {
        double distance = Math.sqrt(2 * (inity - posY) * (initx - posX));
        double degrees = Math.atan((inity - posY) / (initx - posX));
        double distDeg[] = {degrees, distance};
        if (inity < posY & initx > posX) {
            degrees += 180 + 2 * degrees;
        } else if (inity < posY & initx < posX) {
            degrees += 180;
        } else if (inity > posY & initx < posX) {
            degrees *= 2;
        }

        int circumference = 1; //Still unknown.
        int target = (int)Math.round(distance/circumference*420);
        for (DcMotor x : forward) {
            x.setMode(DcMotor.RunMode.RESET_ENCODERS);
            x.setTargetPosition(target);
            x.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        }
     //Incomplete, and possibly obsolete.
    }
    public void vufMove(ArrayList pos, float x, float y, float z){
//Here we need to get from the pos, or current position, to the new position, x, y, z.
//We will need to use the encoder ticks per inch of robot movement
//We then use the robot direction to turn a certain amount. We will need to find out
// how much the robot turns per tick in degrees
        int target = 1; //Amount of encoder ticks to go forward
        int turn = 1; // Amount of encoder ticks to turn
        for (DcMotor w : right) {
            w.setMode(DcMotor.RunMode.RESET_ENCODERS);
            w.setTargetPosition(turn);
            w.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        }
        for (DcMotor w : left) {
            w.setMode(DcMotor.RunMode.RESET_ENCODERS);
            w.setTargetPosition(-turn);
            w.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        }
        sleep(600);
        //This is for moving forward the desired amount.
        for (DcMotor w : forward) {
            w.setMode(DcMotor.RunMode.RESET_ENCODERS);
            w.setTargetPosition(target);
            w.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        }
        }

    private void sleep(long milliseconds) {
        //Ripped right from the FTC OpMode specifications
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
