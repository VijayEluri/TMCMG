package togos.noise2.function;

public class Z implements FunctionDaDaDa_Da
{
	public static final Z instance = new Z();
	
	public void apply(int count, double[] inX, double[] inY, double[] inZ, double[] out) {
		for( int i=0; i<count; ++i ) {
			out[i] = inZ[i];
		}
	}
}