package dynamicClassLoading;//start extract ProductIH

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
//stop extract ProductIH
/*
  This class is necessary because there is a tight coupling between the
  proxy and the invocation handler.  This example needs to break that 
  coupling by facilitating the replacement of the target object of the
  proxy.
*/

//start extract ProductIH

class ProductIH implements InvocationHandler {

    private Product target = null;
    static private final Class<?>[] productAInterfaces = {Product.class};

    public static Product newInstance(AbstractProduct obj) {
        return (Product)
                Proxy.newProxyInstance(obj.getClass().getClassLoader(), productAInterfaces, new ProductIH(obj));
    }

    private ProductIH(AbstractProduct obj) {
        target = obj;
    }

    public void setTarget(Product x) {
        target = x;
    }

    public Product getTarget() {
        return target;
    }

    public Object invoke(Object t, Method m, Object[] args)
            throws Throwable {
        Object result = null;
        try {
            result = m.invoke(target, args);
        } catch (InvocationTargetException e) {
            throw e.getTargetException();
        }
        return result;
    }
}
//stop extract ProductIH
