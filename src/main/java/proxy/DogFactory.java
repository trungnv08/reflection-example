package proxy;//start extract proxy.DogFactory

import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;

public class DogFactory {

    private final Class<?> dogClass;
    private boolean traceIsOn = false;

    public DogFactory(String className, boolean trace) {
        try {
            dogClass = Class.forName(className);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e); // or whatever is appropriate
        }
        traceIsOn = trace;
    }

    public Dog newInstance(String name, int size) {
        try {
            Dog d = (Dog) dogClass.getDeclaredConstructor().newInstance();
            d.initialize(name, size);
            if (traceIsOn) {
                d = (Dog) TracingIH.createProxy(d, new PrintWriter(System.out));
            }
            return d;
        } catch (InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
            throw new RuntimeException(e); // or whatever is appropriate
        }
    }
}
//stop extract proxy.DogFactory
