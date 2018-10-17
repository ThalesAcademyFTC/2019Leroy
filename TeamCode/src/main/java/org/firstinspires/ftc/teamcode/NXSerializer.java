package org.firstinspires.ftc.teamcode;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import android.os.Environment;

/**
 * Created by dcrenshaw on 4/2/18.
 *
 * Helper class for NXAutonomous to serialize and deserialize NXStateHistories
 */

public class NXSerializer {

    private String filename = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS) + "NXHistoryPermanent.nxh";
    private FileOutputStream ifile;
    private FileInputStream ofile;

    public void initializeFile() {
        try {
            ifile = new FileOutputStream(filename);
            ofile = new FileInputStream(filename);
        }
        catch (FileNotFoundException e) {
            File n = new File(filename);
            try {
                n.createNewFile();
                initializeFile();
            }
            catch (IOException er) {
                //Define what needs to happen if the file that didn't exist suddenly does
                throw new RuntimeException(er.getMessage());
            }
        }
    }
    public void serialize(Serializable obj) throws IOException {
        ObjectOutputStream out = new ObjectOutputStream(ifile);
        out.writeObject(obj);
        out.close();
    }
    public NXStateHistory deserialize() throws IOException, ClassNotFoundException {
        ObjectInputStream in = new ObjectInputStream(ofile);
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
