package generate;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.PrintWriter;
import java.lang.reflect.Method;

//start extract HelloWorldGenerator2
public class HelloWorldGenerator2 {
    
    public static void main( String[] args ) {

	try {
	    FileOutputStream fstream = new FileOutputStream( "./target/classes/generate/HelloWorld.java" );
	    PrintWriter out = new PrintWriter( fstream );
	    out.println( "package generate; \n"
	    		+"class HelloWorld {                             \n"
		       + "    public static void main( String[] args ) { \n"
		       + "        System.out.println( \"Hello world!\" );\n"
		       + "    }                                          \n"
		       + "}                                              " );
	    out.flush();
	    Process p = Runtime.getRuntime().exec( "javac ./target/classes/generate/HelloWorld.java" );

	    // The following section substitutes for p.waitFor()
	    int exitValue = -1;  // compilation failure is not -1
	    int errStreamAvailable = 0;
	    while ( exitValue == -1 ) {
		Thread.sleep( 10 );
		try {
		    exitValue = p.exitValue();
		} catch(IllegalThreadStateException e){
		    InputStream errStream = p.getErrorStream();
		    if ( errStream.available() > 0 
			 && errStream.available() == errStreamAvailable ) {
			for ( int j = errStream.available(); j > 0; j-- )
			    System.out.write( errStream.read() );
			p.destroy();
			throw new RuntimeException("compile failed" );
		    } 
		    errStreamAvailable = errStream.available();
		    exitValue = -1;
		}
	    }

	    if ( p.exitValue() == 0 ) {
		Class outputClassObject = Class.forName( "generate.HelloWorld" );
		Class[] fpl = { String[].class };
		Method m = outputClassObject.getMethod( "main", fpl );
		m.invoke( null, new Object[]{ new String[] {} } );
	    } else {
		InputStream errStream = p.getErrorStream();
		for ( int j = errStream.available(); j > 0; j-- )
		    System.out.write( errStream.read() );
	    }
	} catch(Exception e){ throw new RuntimeException(e); }
    }
}
//stop extract HelloWorldGenerator2
