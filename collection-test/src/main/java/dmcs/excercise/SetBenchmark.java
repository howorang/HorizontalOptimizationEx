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
public class SetBenchmark {

    private static Collection<Integer> testSet10;
    private static Collection<Integer> testSet100;
    private static Collection<Integer> testSet1k;
    private static Collection<Integer> testSet100k;
    private static Collection<Integer> testSet1mil;

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
    public abstract static class AbstractSetBenchmark {

        private Set<Integer> setToTest;
        private Collection<Integer> testSample;

        public abstract Set<Integer> getTestSet();

        public abstract Collection<Integer> getTestSample();

        @Setup
        public void setup() {
            setToTest = getTestSet();
            setToTest.addAll(getTestSample());
        }

        @Benchmark
        public void addBench() {
            setToTest.add(11);
        }

        @Benchmark
        public void removeBench() {
            setToTest.remove(11);
        }

        @Benchmark
        public void browseBench() {
            int i = 0;
            for (int integer : setToTest) {
                i++;
                if (integer > 200) {
                    break;
                }
            }
        }

        @Benchmark
        public void benchExists() {
            setToTest.contains(13);
        }

    }

    public abstract static class HashSetBenchmark extends AbstractSetBenchmark {
        @Override
        public Set<Integer> getTestSet() {
            return new HashSet<>();
        }
    }

    public abstract static class LinkedHashSetBenchmark extends AbstractSetBenchmark {
        @Override
        public Set<Integer> getTestSet() {
            return new LinkedHashSet<>();
        }
    }

    public abstract static class TreeSetBenchmark extends AbstractSetBenchmark {
        @Override
        public Set<Integer> getTestSet() {
            return new TreeSet<>();
        }
    }

    public static class h10 extends HashSetBenchmark {
        @Override
        public Collection<Integer> getTestSample() {
            return testSet10;
        }
    }

    public static class h100 extends HashSetBenchmark {
        @Override
        public Collection<Integer> getTestSample() {
            return testSet100;
        }
    }

    public static class h1k extends HashSetBenchmark {
        @Override
        public Collection<Integer> getTestSample() {
            return testSet1k;
        }
    }

    public static class h100k extends HashSetBenchmark {
        @Override
        public Collection<Integer> getTestSample() {
            return testSet100k;
        }
    }

    public static class h1mil extends HashSetBenchmark {
        @Override
        public Collection<Integer> getTestSample() {
            return testSet1mil;
        }
    }


    public static class l10 extends LinkedHashSetBenchmark {
        @Override
        public Collection<Integer> getTestSample() {
            return testSet10;
        }
    }

    public static class l100 extends LinkedHashSetBenchmark {
        @Override
        public Collection<Integer> getTestSample() {
            return testSet100;
        }
    }

    public static class l1k extends LinkedHashSetBenchmark {
        @Override
        public Collection<Integer> getTestSample() {
            return testSet1k;
        }
    }

    public static class l100k extends LinkedHashSetBenchmark {
        @Override
        public Collection<Integer> getTestSample() {
            return testSet100k;
        }
    }

    public static class l1mil extends LinkedHashSetBenchmark {
        @Override
        public Collection<Integer> getTestSample() {
            return testSet1mil;
        }
    }


    public static class t10 extends TreeSetBenchmark {
        @Override
        public Collection<Integer> getTestSample() {
            return testSet10;
        }
    }

    public static class t100 extends TreeSetBenchmark {
        @Override
        public Collection<Integer> getTestSample() {
            return testSet100;
        }
    }

    public static class t1k extends TreeSetBenchmark {
        @Override
        public Collection<Integer> getTestSample() {
            return testSet1k;
        }
    }

    public static class t100k extends TreeSetBenchmark {
        @Override
        public Collection<Integer> getTestSample() {
            return testSet100k;
        }
    }

    public static class t1mil extends TreeSetBenchmark {
        @Override
        public Collection<Integer> getTestSample() {
            return testSet1mil;
        }
    }
}
