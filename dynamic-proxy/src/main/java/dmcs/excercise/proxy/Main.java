package dmcs.excercise.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class Main {
    public static void main(String[] args) {
        Testable proxyInstance = (Testable) Proxy.newProxyInstance(Main.class.getClassLoader(),
                new Class[]{Testable.class}, new InvocationHandler() {
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        new Test1().test();
                        return null;
                    }
                });
        proxyInstance.test();

        Testable proxyInstance2 = (Testable) Proxy.newProxyInstance(Main.class.getClassLoader(),
                new Class[]{Testable.class}, new InvocationHandler() {
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        new Test2().test();
                        return null;
                    }
                });
        proxyInstance2.test();
    }
}
