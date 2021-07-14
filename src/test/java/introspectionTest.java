import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class introspectionTest {

    @Test
    void testClass() throws ClassNotFoundException {
        class A {
            String name="class1A";
        }
        System.out.println(A.class.getName());
        Object a = Class.forName(A.class.getName());
    }
    @Test
    void testClass2() throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        class A {
            String name="class2A";
        }
        System.out.println(A.class.getName());
        Class<?> a = Class.forName("introspectionTest$2A");
        Object b = a.newInstance();
        System.out.println(b);
    }

}