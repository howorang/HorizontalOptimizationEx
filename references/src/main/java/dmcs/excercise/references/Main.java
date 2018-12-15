package dmcs.excercise.references;

import java.lang.ref.*;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.Random;
import java.util.WeakHashMap;

public class Main {
    public static void main(String[] args) throws InterruptedException {
       testSoft();
       // testWeak();
     //  testPhantom();
    }

    private static void testWeak() {
        int objectCount = 2048;
        Object[] objects = new Object[objectCount];
        WeakReference[] refs = new WeakReference[objectCount];
        for (int i = 0; i < objectCount; i++) {
            String randomString = getRandomString();
            objects[i] = randomString;
            refs[i] = new WeakReference(randomString);
        }
        Arrays.fill(objects, null);
        System.gc();
        int cleanedCount = 0;
        for (WeakReference<String> i : refs) {
            if (i.get() == null) {
                cleanedCount++;
            }
        }
        System.out.println(String.valueOf((cleanedCount/objectCount)*100) + "% refs cleared");
    }

    private static void testSoft() throws InterruptedException {
        int objectCount = 1000000;
        SoftReference[] refs = new SoftReference[objectCount];
        ReferenceQueue<String> referenceQueue = new ReferenceQueue<>();
        for (int i = 0; i < objectCount; i++) {
            String randomString = getRandomString();
            Thread.sleep(1);
            refs[i] = new SoftReference(randomString, referenceQueue);
            while (referenceQueue.poll() != null) {
                System.out.println("Cleaned!");
            }
        }
    }

    private static void testPhantom() {
        int objectCount = 2048;
        Object[] objects = new Object[objectCount];
        PhantomReference[] refs = new PhantomReference[objectCount];
        ReferenceQueue<String> referenceQueue = new ReferenceQueue<>();

        for (int i = 0; i < objectCount; i++) {
            String randomString = getRandomString();
            objects[i] = randomString;
            refs[i] = new PhantomReference(randomString, referenceQueue);
        }
        objects = null;
        System.gc();
        int cleanedCount = 0;
        for (PhantomReference p : refs) {
            cleanedCount++;
        }
        System.out.println(String.valueOf((cleanedCount/objectCount)*100) + "% refs cleared");
        Reference ref;
        int finalizedCount = 0;
        while ((ref = referenceQueue.poll()) != null) {
            finalizedCount++;
            ref.clear();
        }
        System.out.println(String.valueOf((finalizedCount/objectCount)*100) + "% refs finalized");
    }

    private static String getRandomString() {
        byte[] array = new byte[64];
        new Random().nextBytes(array);
        return new String(array, Charset.forName("UTF-8"));
    }
}
