package dmcs.excercise;

import org.openjdk.jmh.Main;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.RunnerException;

import java.io.IOException;
import java.util.*;

@State(Scope.Thread)
@Warmup(iterations = 0)
@Measurement(iterations = 1)
@Fork(1)
public class ReadBenchmark {

    private Collection<Integer> testSet10;
    private Collection<Integer> testSet100;
    private Collection<Integer> testSet1k;
    private Collection<Integer> testSet100k;
    private Collection<Integer> testSet1mil;

    public static void main(String[] args) throws IOException, RunnerException {
        Main.main(args);
    }

    @Setup
    public void setup() throws IOException {
      testSet10 = getTestSet(10);
      testSet100 = getTestSet(100);
      testSet1k = getTestSet(1000);
      testSet100k = getTestSet(100000);
      testSet1mil = getTestSet(1000000);
    }

    public static Collection<Integer> getTestSet(int size) {
        Random random = new Random();
        Collection<Integer> toReturn = new ArrayList<>();
        for (int i = 0; i < size; i++) {
             toReturn.add(random.nextInt());
        }
        return toReturn;
    }


}
