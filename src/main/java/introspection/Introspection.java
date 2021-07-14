package introspection;

public class Introspection {

   static void testClass() throws ClassNotFoundException {
        class A {
            String name="class1A";

            @Override
            public String toString() {
                return "A{" +
                        "name='" + name + '\'' +
                        '}';
            }
        }
        System.out.println(A.class.getName());
        Object a = Class.forName(A.class.getName());
    }
    public static void main(String[] args) throws IllegalAccessException, InstantiationException, ClassNotFoundException {
        class A {
            String name="class2A";

            @Override
            public String toString() {
                return "A{" +
                        "name='" + name + '\'' +
                        '}';
            }
        }
        System.out.println(A.class.getName());
        Class<?> a = Class.forName("introspection$1A");
        Object b = a.newInstance();
        System.out.println(b);
    }
}
