package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

//import com.qualcomm.robotcore.hardware.Servo;

/**
 * This is NOT an opmode.
 *
 * This class can be used to define all the specific hardware for a single robot.
 * In this case that robot is a Pushbot.
 * See PushbotTeleopTank_Iterative and others classes starting with "Pushbot" for usage examples.
 *
 * This hardware class assumes the following device names have been configured on the robot:
 * Note:  All names are lower case and some have single spaces between words.
 *
 * Motor channel:  Left  drive motor:        "left_drive"
 * Motor channel:  Right drive motor:        "right_drive"
 * Motor channel:  Manipulator drive motor:  "left_arm"
 * Servo channel:  Servo to open left claw:  "left_hand"
 * Servo channel:  Servo to open right claw: "right_hand"
 */

//--------------------------------------------------------------------------------------
/* Allows hardware map to be used in other opmodes*/
public class OldHWDrive {
    //---------------------------------------------------------------------------------------
    /* Activate motors and define them as a variable */
    public DcMotor motor1 = null;
    public DcMotor motor2 = null;
    public DcMotor motor3 = null;
    public DcMotor motor4 = null;
    public DcMotor clawMotor = null;
    public Servo servo1 = null;
    public Servo servo2 = null;
    public Servo jewelServo = null;
 /* public DcMotor  armMotor    = null;
    public Servo    leftClaw    = null;
    public Servo    rightClaw   = null;

    public static final double MID_SERVO       =  0.5 ;
    public static final double ARM_UP_POWER    =  0.45 ;
    public static final double ARM_DOWN_POWER  = -0.45 ;
*/

    //-------------------------------------------------------------------------------
    /*Access the hardware on the robot controller*/
    HardwareMap hwMap = null;
    /*Records the time that the opmode is running*/
    private ElapsedTime period = new ElapsedTime();

    //--------------------------------------------------------------------------------
    /*?*/
    public OldHWDrive() {
    }

    /* Initialize the interface of a hardware map */
    public void init (HardwareMap ahwMap){
        /**/
        hwMap = ahwMap;
        //--------------------------------------------------------------------------------
        // Define and connect variables to their matching motors on the robot

       motor1 = hwMap.dcMotor.get("motor1");
       motor2 = hwMap.dcMotor.get("motor2");
       motor3 = hwMap.dcMotor.get("motor3");
        motor4 = hwMap.dcMotor.get("motor4");
       // servo1 = hwMap.servo.get("servo1");
       // servo2 = hwMap.servo.get("servo2");
       // clawMotor = hwMap.dcMotor.get("clawMotor");


        //----------------------------------------------------------------------------------
        //Sets the motors to FORWARD direction, FORWARD=Clockwise, REVERSE=CounterClockwise
        motor1.setDirection(DcMotor.Direction.FORWARD); // MAY NEED TO CHANGE LATER (REVERSE)
        motor2.setDirection(DcMotor.Direction.FORWARD);// MAY NEED TO CHANGE LATER (REVERSE)
        motor3.setDirection(DcMotor.Direction.FORWARD);//MAY NEED TO CHANGE LATER (REVERSE)
        motor4.setDirection(DcMotor.Direction.FORWARD);//MAY NEED TO CHANGE LATER (REVERSE)
        //clawMotor.setDirection(DcMotor.Direction.FORWARD);
        //--------------------------------------------------------------------------------
        /* Set all motors to zero power = no movement */
        motor1.setPower(0);
        motor2.setPower(0);
        motor3.setPower(0);
        motor4.setPower(0);
        //clawMotor.setPower(0);
        //----------------------------------------------------------------------------------
        // Set all motors to run without encoders.
        motor1.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        motor2.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        motor3.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        motor4.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
       // clawMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        //-----------------------------------------------------------------------------------
        // Define and initialize ALL installed servos, this is a example code below:
     /*   leftClaw = hwMap.servo.get("left_hand");
        rightClaw = hwMap.servo.get("right_hand");
        leftClaw.setPosition(MID_SERVO);
        rightClaw.setPosition(MID_SERVO);
     //------------------------------------------------------------------------------------
    */
    }

    /***
     *
     * waitForTick implements a periodic delay. However, this acts like a metronome with a regular
     * periodic tick.  This is used to compensate for varying processing times for each cycle.
     * The function looks at the elapsed cycle time, and sleeps for the remaining time interval.
     *
     * @param periodMs  Length of wait cycle in mSec.
     */

    public void waitForTick(long periodMs) {

        long remaining = periodMs - (long) period.milliseconds();

        // sleep for the remaining portion of the regular cycle period.
        if (remaining > 0) {
            try {
                Thread.sleep(remaining);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        // Reset the cycle clock for the next pass.
        period.reset();
    }
}

