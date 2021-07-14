package proxy; /**
 * Traced is an invocation handler for a proxy that
 * prints messages before and after every method invocation.
 */
//start extract proxy.TracingIH

import java.io.PrintWriter;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

public class TracingIH implements InvocationHandler {

    public static BiConsumer<PrintWriter, Method> beforeInvoke;
    public static BiConsumer<PrintWriter, Method> onException;
    public static BiConsumer<PrintWriter, Method> afterInvoke;

    public static Object createProxy(Object obj, PrintWriter out) {
        return Proxy.newProxyInstance(obj.getClass().getClassLoader(),
                obj.getClass().getInterfaces(),
                new TracingIH(obj, out));
    }

    private final Object target;
    private final PrintWriter out;

    private TracingIH(Object obj, PrintWriter out) {
        target = obj;
        this.out = out;
    }

    public Object invoke(Object proxy, Method method, Object[] args)
            throws Throwable {
        Object result;
        try {
            beforeInvoke.accept(out, method);
            out.flush();
            result = method.invoke(target, args);
        } catch (Exception e) {
            onException.accept(out, method);
            out.flush();
            throw e;
        } finally {
            afterInvoke.accept(out, method);
            out.flush();
        }
        return result;
    }
}
//stop extract proxy.TracingIH
