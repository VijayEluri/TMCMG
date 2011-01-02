package togos.noise2.function;

import togos.noise2.rewrite.ExpressionRewriter;

public class ClampOutDaDaDa_Da implements SmartFunctionDaDaDa_Da
{
	SmartFunctionDaDaDa_Da lower;
	SmartFunctionDaDaDa_Da upper;
	SmartFunctionDaDaDa_Da clamped;
	
	public ClampOutDaDaDa_Da( SmartFunctionDaDaDa_Da lower, SmartFunctionDaDaDa_Da upper, SmartFunctionDaDaDa_Da clamped ) {
		this.lower = lower;
		this.upper = upper;
		this.clamped = clamped;
	}
	
	public void apply( int count, double[] inX, double[] inY, double[] inZ, double[] out ) {
		double[] lower = new double[count];
		this.lower.apply(count, inX, inY, inZ, lower);
		double[] upper = new double[count];
		this.upper.apply(count, inX, inY, inZ, upper);
		this.clamped.apply(count, inX, inY, inZ, out);
		for( int i=0; i<count; ++i ) {
			if( out[i] < lower[i] ) out[i] = lower[i];
			if( out[i] > upper[i] ) out[i] = upper[i];
		}
	}
	
	public boolean isConstant() {
		return lower.isConstant() && upper.isConstant() && clamped.isConstant();
	}
	
	public Object rewriteSubExpressions(ExpressionRewriter rw) {
		return new ClampOutDaDaDa_Da(
			(SmartFunctionDaDaDa_Da)rw.rewrite(lower),
			(SmartFunctionDaDaDa_Da)rw.rewrite(upper),
			(SmartFunctionDaDaDa_Da)rw.rewrite(clamped)
		);
	}
	
	public String toString() {
		return "clamp("+lower+", "+upper+", "+clamped+")";
	}
}
