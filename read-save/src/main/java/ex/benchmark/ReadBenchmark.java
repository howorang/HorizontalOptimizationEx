package ex.benchmark;

import org.openjdk.jmh.Main;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.RunnerException;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.EnumSet;

@State(Scope.Thread)
@Warmup(iterations = 0)
@Measurement(iterations = 1)
@Fork(1)
public class ReadBenchmark {

    private String filePath;

    public static void main(String[] args) throws IOException, RunnerException {
        Main.main(args);
    }

    @Setup
    public void setup() throws IOException {
        System.out.println("SETUP");
        filePath = RandomFileGenerator.saveAndGetFilePath("tst.rnd",500);
    }



    @Benchmark
    @BenchmarkMode(Mode.SingleShotTime)
    public void benchmarkBufferedRead() {
        readWithBuffered(filePath);
    }

    @Benchmark
    @BenchmarkMode(Mode.SingleShotTime)
    public void benchmarkNIO() throws IOException {
        readWithNIO(filePath);
    }

    @Benchmark
    @BenchmarkMode(Mode.SingleShotTime)
    public void benchmarkMemoryMapped() throws Exception {
        readWithMemoryMapped(filePath);
    }


    private static void readWithBuffered(String filePath) {
        try(FileInputStream fileInputStream = new FileInputStream(filePath);
            BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream)) {
          //  byte[] bytes = bufferedInputStream.readAllBytes();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void readWithNIO(String filePath) throws IOException {
        Path pathToRead = Paths.get(filePath);
        Files.readAllBytes(pathToRead);
    }

    private static void readWithMemoryMapped(String filePath) throws Exception {
        Path pathToRead = Paths.get(filePath);
        try (FileChannel fileChannel = (FileChannel) Files.newByteChannel(
                pathToRead, EnumSet.of(StandardOpenOption.READ))) {
            MappedByteBuffer mappedByteBuffer = fileChannel
                    .map(FileChannel.MapMode.READ_ONLY, 0, fileChannel.size());
        }
    }


}
