package org.firstinspires.ftc.teamcode;

import android.content.Context;

import com.qualcomm.robotcore.hardware.HardwareMap;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * Created by dcrenshaw on 4/2/18.
 *
 * Helper class for NXAutonomous to serialize and deserialize NXStateHistories
 */

public class NXSerializer {

    private FileOutputStream ofile;
    private FileInputStream ifile;

    public void initializeFile(HardwareMap hmap) {
        try {
            ofile = hmap.appContext.openFileOutput("NXHistory.nxh", Context.MODE_PRIVATE);
            ifile = hmap.appContext.openFileInput("NXHistory.nxh");
        }
        catch (FileNotFoundException e) {
            File persistentHistory = new File(hmap.appContext.getFilesDir().getAbsolutePath() + "NXHistory.nxh");
            try {
                persistentHistory.createNewFile();
                initializeFile(hmap);
            }
            catch (IOException er) {
                er.printStackTrace();
            }
        }
    }
    public void serialize(Serializable obj) throws IOException {
        ObjectOutputStream out = new ObjectOutputStream(ofile);
        out.writeObject(obj);
        out.close();
    }
    public NXStateHistory deserialize() throws IOException, ClassNotFoundException {
        ObjectInputStream in = new ObjectInputStream(ifile);
        NXStateHistory rval = (NXStateHistory) in.readObject();
        in.close();
        ofile.close();
        return rval;
    }
    public void closeEverything() {
        try {
            ifile.close();
            ofile.close();
        }
        catch (IOException e) {
            throw new RuntimeException("Kill Java. Kill it with fire.");
        }
    }
}
