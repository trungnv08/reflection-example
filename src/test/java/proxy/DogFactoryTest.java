package proxy;

import org.junit.jupiter.api.Test;

class DogFactoryTest {


    @Test
    void testProxy() {
        TracingIH.beforeInvoke = (out, method) -> out.println(method.getName() + "() is invoking.");
        TracingIH.afterInvoke = (out, method) -> out.println(method.getName() + "() is invoked.");
        TracingIH.onException = (out, method) -> out.println(method.getName() + "() throw an Exception");
        DogFactory dogFactory = new DogFactory(DogImpl.class.getName(), true);
        Dog dog = dogFactory.newInstance("alaska", 1);
        dog.method1();
        dog.method2();
    }
}