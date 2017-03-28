import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Random;

/**
 * Created by Acerolla (Evgeniy Solovev) on 10.05.2016.
 */
public class Test {

    public static void main(String[] args) throws Exception {

        String temp = "test.txt";
        temp = temp.substring(0, temp.lastIndexOf('.')) + "ENC" + temp.substring(temp.lastIndexOf('.'));
        System.out.println(temp);

/*
        File testFile = new File("D:\\zzzzkey\\test_file.txt");
        FileInputStream is = new FileInputStream(testFile);

        byte[] qq = new byte[(int)testFile.length()];


        is.read(qq);
        is.close();

        IDEA idea = new IDEA();

        idea.generateKey();

        int subLength = qq.length;
        subLength /= 8;
        subLength ++ ;
        subLength  *= 8;
        byte[] out = new byte[subLength];

        for (int i = 0; i < qq.length; i += 8) {
            byte[] data = new byte[8];
            for (int j = 0; j < 8; j++) {
                if (i + j >= qq.length) {
                    data[j] = 0;
                } else {

                    data[j] = qq[i + j];
                }
            }

            idea.crypt(data);

            for (int j = 0; j < 8; j++) {
                out[i + j] = data[j];
            }
        }

        File testFile2 = new File("D:\\zzzzkey\\test_file2.txt");
        if (!testFile2.exists()) {
            testFile2.createNewFile();
        }

        FileOutputStream os = new FileOutputStream(testFile2);
        os.write(out);
        os.flush();
        os.close();


        testFile = new File("D:\\zzzzkey\\test_file2.txt");
        is = new FileInputStream(testFile);

        qq = new byte[(int)testFile.length()];

        is.read(qq);
        is.close();

        out = new byte[qq.length];

        idea.invertSubKey();

        for (int i = 0; i < qq.length; i += 8) {
            byte[] data = new byte[8];
            for (int j = 0; j < 8; j++) {
                data[j] = qq[i + j];
            }

            idea.crypt(data);

            for (int j = 0; j < 8; j++) {
                out[i + j] = data[j];
            }
        }

        testFile2 = new File("D:\\zzzzkey\\test_file3.txt");
        if (!testFile2.exists()) {
            testFile2.createNewFile();
        }

        os = new FileOutputStream(testFile2);
        os.write(out);
        os.flush();
        os.close();
*/
    }


}
