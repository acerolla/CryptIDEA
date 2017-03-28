import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

/**
 * Created by Acerolla (Evgeniy Solovev) on 13.05.2016.
 */
public class Crypt {

    File in,
         out;

    IDEA idea;

    public Crypt(String from, File out, boolean crypt, IDEA idea) {
        in = new File(from);
        this.out = out;
        if (!crypt) {
            idea.invertSubKey();
        }
        this.idea = idea;
    }

    public void start() throws Exception {
        FileInputStream is = new FileInputStream(in);

        byte[] fileInBytes = new byte[(int)in.length()];
        is.read(fileInBytes);
        is.close();

        int subLength = fileInBytes.length;

        if (in.length() % 8 != 0) {
            subLength /= 8;
            subLength += 1;
            subLength *= 8;
        }

        byte[] fileOutBytes = new byte[subLength];

        for (int i = 0; i < fileInBytes.length; i += 8) {
            byte[] data = new byte[8];
            for (int j = 0; j < 8; j++) {
                if (i + j >= fileInBytes.length) {
                    data[j] = 0;
                } else {
                    data[j] = fileInBytes[i + j];
                }
            }

            idea.crypt(data);

            for (int j = 0; j < 8; j++) {
                fileOutBytes[i + j] = data[j];
            }
        }


        if (!out.exists()) {
            out.createNewFile();
        }

        FileOutputStream os = new FileOutputStream(out);
        os.write(fileOutBytes);
        os.flush();
        os.close();
    }

}
