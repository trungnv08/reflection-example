//start extract SimpleClassLoader
package simpleClassLoader;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class SimpleClassLoader extends ClassLoader {

    String[] dirs;

    public SimpleClassLoader( String path ) {                              //#1
	dirs = path.split( System.getProperty("path.separator") );           //#1
    }                                                                      //#1
                                                                           //#1
    public SimpleClassLoader( String path, ClassLoader parent ) {          //#1
	super( parent );                                                   //#1
	dirs = path.split( System.getProperty("path.separator") );           //#1
    }                                                                      //#1

//stop extract SimpleClassLoader
    public Class loadClass( String name ) throws ClassNotFoundException {
	Class c;
	String addr = this.toString().substring(this.toString().indexOf('@'));
	System.out.println( addr + " SimpleClassLoader: super.loadClass( " +  name + " )" );
	c = super.loadClass(name);
	return c;
    }

//start extract SimpleClassLoader
    public void extendClasspath( String path ) {                           //#2
	String[] exDirs = path.split( System.getProperty("path.separator")); //#2
	String[] newDirs = new String[ dirs.length + exDirs.length ];      //#2
	System.arraycopy( dirs, 0, newDirs, 0, dirs.length );              //#2
	System.arraycopy( exDirs, 0, newDirs, dirs.length, exDirs.length );//#2
	dirs = newDirs;                                                    //#2
//stop extract SimpleClassLoader
	for ( int k = 0; k < dirs.length; k++ )
	    System.out.println( dirs[k] );
//start extract SimpleClassLoader
    }

    public synchronized Class findClass( String name ) 
	throws ClassNotFoundException 
    {
//stop extract SimpleClassLoader
	//String addrx = this.toString().substring(this.toString().indexOf('@'));
	//System.out.println( addrx + " SimpleClassLoader: findClass( " +  name + " )" );
//start extract SimpleClassLoader
	for ( int i = 0; i < dirs.length; i++ ) {
		byte[] buf = getClassData( dirs[i], name );
		if ( buf != null )
//stop extract SimpleClassLoader
		    {
			String addr = this.toString().substring(this.toString().indexOf('@'));
			System.out.println( addr + " SimpleClassLoader: defineClass( " +  dirs[i] + "/" +  name + " )" );
//start extract SimpleClassLoader
		    return defineClass( name, buf, 0, buf.length );        //#3
//stop extract SimpleClassLoader
		    }
//start extract SimpleClassLoader
	    }
	    throw new ClassNotFoundException();
    }
    
    protected byte[] getClassData( String directory, String name ){
	String classFile = directory + "/" + name.replace('.','/') + ".class";
	int classSize 
	    = (new Long((new File( classFile )).length())).intValue();
	byte[] buf = new byte[classSize];
	try {
	    FileInputStream filein = new FileInputStream( classFile );
	    classSize = filein.read( buf );
	} catch(FileNotFoundException e){
	    return null;
	} catch(IOException e){
	    return null;
	}
	return buf;
    }
}
//stop extract SimpleClassLoader

