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
public class ListBenchmark {

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
    @Fork(1)
    public abstract static class AbstractListBenchmark {

        private List<Integer> listToTest;
        private Collection<Integer> testSample;

        public abstract List<Integer> getTestList();

        public abstract Collection<Integer> getTestSample();

        @Setup(Level.Invocation)
        public void setup() {
            listToTest = getTestList();
            listToTest.addAll(getTestSample());
        }

        @Benchmark
        public void benchAddAtStart() {
            listToTest.add(0, 11);
        }

        @Benchmark
        public void benchAddAtEnd() {
            listToTest.add(11);
        }

        @Benchmark
        public void benchAddRandom() {
            listToTest.add(random.nextInt(listToTest.size() - 1));
        }

        @Benchmark
        public void removeFromBegginig() {
            listToTest.remove(0);
        }

        @Benchmark
        public void removeFromEnd() {
            listToTest.remove(listToTest.size() -1 );
        }

        @Benchmark
        public void removeRandom() {
            listToTest.remove(random.nextInt(listToTest.size()));
        }

        @Benchmark
        public void browseWithIndexes() {
            int current;
            for (int i = 0; i < listToTest.size(); i++) {
                current = listToTest.get(i);
            }
        }

        @Benchmark
        public void browseWithIterators() {
            int current;
            for (int i  : listToTest) {
                current = i;
            }
        }

    }

    public abstract static class ArrayListBenchmark extends AbstractListBenchmark {
        @Override
        public List<Integer> getTestList() {
            return new ArrayList<>();
        }
    }

    public abstract static class LinkedListBenchmark extends AbstractListBenchmark {
        @Override
        public List<Integer> getTestList() {
            return new LinkedList<>();
        }
    }

//    public static class h10 extends ArrayListBenchmark {
//        @Override
//        public Collection<Integer> getTestSample() {
//            return testSet10;
//        }
//    }
//
//    public static class h100 extends ArrayListBenchmark {
//        @Override
//        public Collection<Integer> getTestSample() {
//            return testSet100;
//        }
//    }
//
//    public static class h1k extends ArrayListBenchmark {
//        @Override
//        public Collection<Integer> getTestSample() {
//            return testSet1k;
//        }
//    }

    public static class h100k extends ArrayListBenchmark {
        @Override
        public Collection<Integer> getTestSample() {
            return testSet100k;
        }
    }

//    public static class h1mil extends ArrayListBenchmark {
//        @Override
//        public Collection<Integer> getTestSample() {
//            return testSet1mil;
//        }
//    }


//    public static class l10 extends LinkedListBenchmark {
//        @Override
//        public Collection<Integer> getTestSample() {
//            return testSet10;
//        }
//    }
//
//    public static class l100 extends LinkedListBenchmark {
//        @Override
//        public Collection<Integer> getTestSample() {
//            return testSet100;
//        }
//    }
//
//    public static class l1k extends LinkedListBenchmark {
//        @Override
//        public Collection<Integer> getTestSample() {
//            return testSet1k;
//        }
//    }

    public static class l100k extends LinkedListBenchmark {
        @Override
        public Collection<Integer> getTestSample() {
            return testSet100k;
        }
    }

//    public static class l1mil extends LinkedListBenchmark {
//        @Override
//        public Collection<Integer> getTestSample() {
//            return testSet1mil;
//        }
//    }
}
