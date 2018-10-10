package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.exception.RobotCoreException;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

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
    private byte[][] a;
    private long[] b;

    public void init() {
        //At least half of these lines will be pointless exception handling. Thanks, Java
        try {
            serializer.initializeFile();
            serializer.deserialize();
        }
        catch (Throwable e) {
            throw new RuntimeException("Everything that could go wrong has.");
        }
    }
    public void loop() {} // We don't need a loop
    public void postproc() {
        //This is really just to get away from the horribly messy init function
        for (int x = 0; x < a.length; x++) {
            try {
                teleconnect.gamepad1.fromByteArray(a[x]);
                sleep(b[x + 1]);
            }
            catch (RobotCoreException e) {
                telemetry.addLine("Gamepad library is in an invalid state. There's nothing else to be done.");
                return;
            }
        }
    }
    public final void sleep(long milliseconds) {
        //Ripped right from the FTC OpMode specifications
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
