package dmcs.excercise.references;

import java.lang.ref.*;
import java.nio.charset.Charset;
import java.util.Random;
import java.util.WeakHashMap;

public class Main {
    public static void main(String[] args) {
        testPhantom();

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

        int cleanedCount = 0;
        for (WeakReference<String> i : refs) {
            if (i.get() == null) {
                cleanedCount++;
            }
        }
        System.out.println(String.valueOf((cleanedCount/1024f)*100) + "% refs cleared");
    }

    private static void testSoft() {
        int objectCount = 2048;
        Object[] objects = new Object[objectCount];
        SoftReference[] refs = new SoftReference[objectCount];
        for (int i = 0; i < objectCount; i++) {
            String randomString = getRandomString();
            objects[i] = randomString;
            refs[i] = new SoftReference(randomString);
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

        for (PhantomReference p : refs) {
            System.out.println(p.isEnqueued());
        }

        Reference ref = null;
        while ((ref = referenceQueue.poll()) != null) {
            System.out.println("Finilizing");
            ref.clear();
        }
    }

    private static String getRandomString() {
        byte[] array = new byte[64];
        new Random().nextBytes(array);
        return new String(array, Charset.forName("UTF-8"));
    }
}
