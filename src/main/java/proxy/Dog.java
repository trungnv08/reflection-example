package proxy;

interface Dog {
    void initialize( String name, int size );
    int method1();
    int method2();
}

class DogImpl implements Dog {
    private int x = 0;
    private int y = 0;
    DogImpl (){
	if ( !invariant() )
	    throw new RuntimeException();
    }
    public void initialize( String name, int size ) {}
    public int method1() { x++; y--; return 0; }
    public int method2() { 
	x++; y++; 
	System.out.println( "proxy.Dog(" + x + "," + y + ")" );
	return 0; }
    public boolean invariant() {
	return x + y == 0;
    }
}
	
