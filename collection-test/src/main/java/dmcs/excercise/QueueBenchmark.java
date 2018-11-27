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
public class QueueBenchmark {

    private static Collection<Integer> testSet10;
    private static Collection<Integer> testSet100;
    private static Collection<Integer> testSet1k;
    private static Collection<Integer> testSet100k;
    private static Collection<Integer> testSet1mil;
    private static Random random = new Random();

    static {
        testSet10 = getTestSample(10);
        testSet100 = getTestSample(100);
        testSet1k = getTestSample(1000);
        testSet100k = getTestSample(100000);
        testSet1mil = getTestSample(1000000);
    }

    public static Collection<Integer> getTestSample(int size) {
        Random random = new Random();
        Collection<Integer> toReturn = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            toReturn.add(random.nextInt());
        }
        return toReturn;
    }

    public static void main(String[] args) throws IOException, RunnerException {
        Main.main(args);
    }

    @State(Scope.Benchmark)
    @Warmup(iterations = 0)
    @Measurement(iterations = 1)
    @BenchmarkMode(Mode.SingleShotTime)
    @Fork(1)
    public abstract static class AbstractQueueBenchmark {

        private Deque<Integer> listToTest;
        private Collection<Integer> testSample;

        public abstract Deque<Integer> getTestList();

        public abstract Collection<Integer> getTestSample();

        @Setup
        public void setup() {
            listToTest = getTestList();
            listToTest.addAll(getTestSample());
        }

        @Benchmark
        public void benchAddAtStart() {
            listToTest.addFirst( 11);
        }

        @Benchmark
        public void benchAddAtEnd() {
            listToTest.addLast(11);
        }

        @Benchmark
        public void benchAddRandom() {
            listToTest.add(random.nextInt(testSample.size()));
        }

        @Benchmark
        public void removeFromBegginig() {
            listToTest.removeFirst();
        }

        @Benchmark
        public void removeFromEnd() {
            listToTest.removeLast();
        }

        @Benchmark
        public void removeRandom() {
            listToTest.remove(random.nextInt(listToTest.size()));
        }

        @Benchmark
        public void browseWithIterators() {
            int current;
            for (int i  : listToTest) {
                current = i;
            }
        }

    }

    public abstract static class ArrayDequeBenchmark extends AbstractQueueBenchmark {
        @Override
        public Deque<Integer> getTestList() {
            return new ArrayDeque<>();
        }
    }

    public abstract static class LinkedListBenchmark extends AbstractQueueBenchmark {
        @Override
        public Deque<Integer> getTestList() {
            return new LinkedList<>();
        }
    }

    public static class h10 extends ArrayDequeBenchmark {
        @Override
        public Collection<Integer> getTestSample() {
            return testSet10;
        }
    }

    public static class h100 extends ArrayDequeBenchmark {
        @Override
        public Collection<Integer> getTestSample() {
            return testSet100;
        }
    }

    public static class h1k extends ArrayDequeBenchmark {
        @Override
        public Collection<Integer> getTestSample() {
            return testSet1k;
        }
    }

    public static class h100k extends ArrayDequeBenchmark {
        @Override
        public Collection<Integer> getTestSample() {
            return testSet100k;
        }
    }

    public static class h1mil extends ArrayDequeBenchmark {
        @Override
        public Collection<Integer> getTestSample() {
            return testSet1mil;
        }
    }


    public static class l10 extends LinkedListBenchmark {
        @Override
        public Collection<Integer> getTestSample() {
            return testSet10;
        }
    }

    public static class l100 extends LinkedListBenchmark {
        @Override
        public Collection<Integer> getTestSample() {
            return testSet100;
        }
    }

    public static class l1k extends LinkedListBenchmark {
        @Override
        public Collection<Integer> getTestSample() {
            return testSet1k;
        }
    }

    public static class l100k extends LinkedListBenchmark {
        @Override
        public Collection<Integer> getTestSample() {
            return testSet100k;
        }
    }

    public static class l1mil extends LinkedListBenchmark {
        @Override
        public Collection<Integer> getTestSample() {
            return testSet1mil;
        }
    }
}
