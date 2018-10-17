package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.exception.RobotCoreException;

/**
 * Created by dcrenshaw on 4/3/18.
 *
 * NXPlayer plays back recordings made by NXRecorder.
 * This has not been vetted or verified and is not guaranteed to work or even run. It is purely for
 * developer purposes, and none of it should be used lightly.
 */

@Autonomous(name="AutoPlayer", group="Pushbot")
public class NXPlayer extends OpMode{

    private ClaspTeleop teleconnect = new ClaspTeleop();

    private NXSerializer serializer = new NXSerializer();
    private NXStateHistory historian; //This will be a StateHistory later

    public void init() {
        //At least half of these lines will be pointless exception handling. Thanks, Java
        try {
            serializer.initializeFile();
            historian = serializer.deserialize();
        }
        catch (Throwable e) {
            throw new RuntimeException(e.getMessage());
        }
        runResolved();
    }
    public void loop() {} // We don't need a loop
    private void runResolved() {
        byte[][] controllerStateHistory = historian.getByteArray2d();
        long[] timechain = historian.getTimeHistory();
        for (int x = 0; x < controllerStateHistory.length; x++) {
            try {
                teleconnect.gamepad1.fromByteArray(controllerStateHistory[x]);
            }
            catch (RobotCoreException e) {
                throw new RuntimeException("Internal error; this one's beyond us.");
            }
            sleep(timechain[x]);
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
