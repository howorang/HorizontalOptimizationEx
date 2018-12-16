package dmcs.excercise.leaks;

import java.nio.charset.Charset;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class Leaks {
    public static void main(String[] args) throws InterruptedException {
      //  setLeak();
        stackLeak();
    }

    private static void stackLeak() throws InterruptedException {
        Thread.sleep(5000);
        System.out.println("START");
        LeakyStack<Data> stack = new LeakyStack<>();
        for (int i = 0; i < 100000; i++) {
            stack.put(new Data());
        }
        for (int i = 0; i < 10000; i++) {
            stack.get();
        }
        System.out.println("END");
    }

    public static class Data {
        String value = getRandomString();

        @Override
        public boolean equals(Object o) {
            return false;
        }

        @Override
        public int hashCode() {
            return 1;
        }
    }

    private static void setLeak() {
        Data dat = new Data();
        Set<Data> testSet = new HashSet<Data>();
        for (int i = 0; i < 1000000; i++) {
            testSet.add(new Data());
        }



    }

    private static String getRandomString() {
        byte[] array = new byte[2048];
        new Random().nextBytes(array);
        return new String(array, Charset.forName("UTF-8"));
    }
}
