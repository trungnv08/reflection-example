package simpleClassLoader.classLoaderTest;//start extract SimpleClassLoaderTest

import simpleClassLoader.SimpleClassLoader;

public class SimpleClassLoaderTest {
    
    public static void main( String[] args ) 
	throws ClassNotFoundException, 
	       InstantiationException, 
	       IllegalAccessException 
    {
	SimpleClassLoader firstClassLoader 
	    = new SimpleClassLoader( "testclasses" );
	Class c1 = firstClassLoader.loadClass( "ConstructOnce" );
	
	SimpleClassLoader secondClassLoader 
	    = new SimpleClassLoader( "testclasses" );
	Class c2 = secondClassLoader.loadClass( "ConstructOnce" );

	Object x = c1.newInstance();
	
	try {
	    Object y = c1.newInstance();
	    throw new RuntimeException("Test fails");
	} catch( IllegalStateException e ) { }

	Object z = c2.newInstance();
    }
}
//stop extract SimpleClassLoaderTest
