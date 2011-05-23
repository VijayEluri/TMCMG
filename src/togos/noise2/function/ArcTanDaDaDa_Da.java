package togos.noise2.function;

import togos.noise2.data.DataDa;
import togos.noise2.data.DataDaDaDa;

public class ArcTanDaDaDa_Da extends OneArgDaDaDa_Da
{
	public ArcTanDaDaDa_Da( FunctionDaDaDa_Da arg ) {
		super(arg);
	}
	
	public String getMacroName() {
	    return "arctan";
    }

	public DataDa apply( DataDaDaDa in ) {
		int len = in.getLength();
		double[] dat = arg.apply(in).x;
		double[] out = new double[len];
		for (int i = 0; i < len; i++) {
			out[i] = Math.atan(dat[i]);
		}
		return new DataDa( out );
	}
	
	
}
