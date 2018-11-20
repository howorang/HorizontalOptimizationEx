package ex.benchmark;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Random;

public class RandomFileGenerator {
    private static final Random random = new Random();

    public static String saveAndGetFilePath(String filename, int mbSize) throws IOException {
        byte[] byteBuffer = new byte[1024 * 1024];
        File randomFile = new File(filename);
        randomFile.createNewFile();
        try (FileOutputStream fileOutputStream = new FileOutputStream(randomFile.getAbsolutePath())) {
            for (int i = 0; i < mbSize; i++) {
                random.nextBytes(byteBuffer);
                fileOutputStream.write(byteBuffer);
            }
            fileOutputStream.flush();
        }
        return randomFile.getAbsolutePath();
    }
}
