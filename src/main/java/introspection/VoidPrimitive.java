package introspection;

import java.lang.reflect.Method;

public class VoidPrimitive {

    public String test(Object a) {
        System.out.println("test" + a);
        return "";
    }

    public void test() {
        System.out.println("test");
    }

    public static void main(String[] args) throws NoSuchMethodException {
        Class<?> klass = VoidPrimitive.class;
        Method voidMethod = klass.getMethod("test");
        System.out.println(voidMethod.getReturnType().isPrimitive());
    }
}
