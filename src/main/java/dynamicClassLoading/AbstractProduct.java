package dynamicClassLoading;

import simpleClassLoader.SimpleClassLoader;

import java.lang.ref.WeakReference;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.List;

//start extract AbstractProduct
abstract public class AbstractProduct implements Product {
    
    static private ClassLoader cl = null;
    static private String      directory = null;
    static private Class       implClass;
    static private List        instances = new ArrayList();
    
    public static Product newInstance( )
	throws InstantiationException, IllegalAccessException 
    {
	AbstractProduct obj = (AbstractProduct)implClass.newInstance();
	Product anAProxy = (Product)ProductIH.newInstance( obj );
	instances.add( new WeakReference( anAProxy ) );
	return anAProxy;
    }
	
    public static void reload( String dir ) 
	throws ClassNotFoundException, 
	       InstantiationException, 
	       IllegalAccessException,
	       NoSuchMethodException, 
	       InvocationTargetException 
    {
    	//TODO Constructs new class loader and loads implementation
	cl = new SimpleClassLoader( dir );                        //#1
	implClass = cl.loadClass( "ProductImpl" );                //#1
//TODO ì first implement
	if (directory == null) { // first implementation          //#2
	    directory = dir;                                      //#2
	    return;                                               //#2
	}

	directory = dir;
	List newInstances = new ArrayList();

	Method evolve = implClass.getDeclaredMethod( "evolve", Object.class);

	//TODO envolves each instance of the product
	for ( int i = 0; i < instances.size(); i++ ) {                     //#3
	    Proxy x =(Proxy)((WeakReference)instances.get(i)).get();       //#3
	    if ( x != null ) {                                             //#3
		ProductIH aih = (ProductIH)Proxy.getInvocationHandler(x);  //#3
		Product oldObject = aih.getTarget();                       //#3
		Product replacement                                        //#3
		    = (Product)evolve.invoke( null,                        //#3
					       new Object[]{oldObject} );  //#3
		aih.setTarget( replacement );                              //#3
		newInstances.add( new WeakReference( x ) );                //#3
	    }
	}
	//TODO replace  list of the instances
	instances = newInstances;                                          //#4
    }
}
//stop extract AbstractProduct
