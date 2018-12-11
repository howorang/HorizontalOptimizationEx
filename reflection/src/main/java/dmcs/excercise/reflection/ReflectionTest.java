package dmcs.excercise.reflection;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;

public class ReflectionTest {
    public static void main(String[] args) {
        Constructor<?>[] ctors = Test.class.getDeclaredConstructors();
        Constructor<?> ctor = ctors[0];
        try {
            ctor.setAccessible(true);
            Test t = (Test) ctor.newInstance();
            t.test();
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            Test t = new Test();
            Field f = t.getClass().getDeclaredField("veryPrivate");
            f.setAccessible(true);
            System.out.println(f.get(t));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
