package togos.noise.v1.func;

import togos.noise.v1.data.DataDaDa;
import togos.noise.v1.data.DataDaDaDa;
import togos.noise.v1.data.DataIa;
import togos.noise.v1.lang.Expression;
import togos.noise.v1.rewrite.ExpressionRewriter;

public class Constant_Ia implements FunctionDaDa_Ia, FunctionDaDaDa_Ia, Expression, PossiblyConstant
{
	public static final Constant_Ia ZERO = new Constant_Ia(0);
	
	public static Constant_Ia forValue( int v ) {
		if( v == 0 ) {
			return ZERO;
		} else {
			return new Constant_Ia(v);
		}
	}
	
	int value;
	
	public Constant_Ia( int value ) {
		this.value = value;
	}
	
	public DataIa apply( final int vectorSize ) {
		int[] out = new int[vectorSize];
		for( int j=0; j<vectorSize; ++j ) {
			out[j] = value;
		}
		return new DataIa( vectorSize, out );
	}
	
	public DataIa apply( DataDaDa in ) {
		return apply( in.getLength() );
	}
	
	public DataIa apply( DataDaDaDa in ) {
		return apply( in.getLength() );
	}
	
	public boolean equals( Object oth ) {
		if( !(oth instanceof Constant_Ia) ) return false;
		return value == ((Constant_Ia)oth).value;
	}
	
	public String toString() {
		return "constant-int("+value+")";
	}
	
	public String toTnl() {
		return Integer.toString(value);
	}
	
	public Object[] directSubExpressions() {
		return new Expression[0];
	}
	
	public Object rewriteSubExpressions( ExpressionRewriter v ) {
	    return this;
	}
	
	public boolean isConstant() {
	    return true;
	}
	
	public int getTriviality() {
	    return 100;
	}
}