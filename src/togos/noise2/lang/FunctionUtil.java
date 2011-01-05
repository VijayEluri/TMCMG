package togos.noise2.lang;

import togos.noise2.data.DataDa;
import togos.noise2.data.DataDaDa;
import togos.noise2.data.DataDaDaDa;
import togos.noise2.function.AdaptInDaDa_DaDaDa_Da;
import togos.noise2.function.AdaptOutDaDa_Da_Ia;
import togos.noise2.function.Constant_Da;
import togos.noise2.function.Constant_Ia;
import togos.noise2.function.FunctionDaDaDa_Da;
import togos.noise2.function.TNLFunctionDaDaDa_Da;
import togos.noise2.function.FunctionDaDa_Da;
import togos.noise2.function.FunctionDaDa_Ia;

public class FunctionUtil
{
	public static TNLFunctionDaDaDa_Da toDaDaDa_Da( Object r, SourceLocation sloc ) throws CompileError {
		if( r instanceof TNLFunctionDaDaDa_Da ) {
			return (TNLFunctionDaDaDa_Da)r;
		} else if( r instanceof Number ) {
			return new Constant_Da( ((Number)r).doubleValue() );
		} else {
			throw new CompileError("Can't convert "+r.getClass()+" to FunctionDaDaDa_Da", sloc);
		}
	}
	
	public static FunctionDaDa_Da toDaDa_Da( Object r, SourceLocation sloc ) throws CompileError {
		if( r instanceof FunctionDaDa_Da ) {
			return (FunctionDaDa_Da)r;
		} else if( r instanceof TNLFunctionDaDaDa_Da ) {
			return new AdaptInDaDa_DaDaDa_Da( (TNLFunctionDaDaDa_Da)r );
		} else if( r instanceof Number ) {
			return new Constant_Da( ((Number)r).doubleValue() );
		} else {
			throw new CompileError("Can't convert "+r.getClass()+" to FunctionDaDaDa_Da", sloc);
		}		
	}

	public static FunctionDaDa_Ia toDaDa_Ia( Object r, SourceLocation sloc ) throws CompileError {
		if( r instanceof TNLFunctionDaDaDa_Da ) {
			r = new AdaptInDaDa_DaDaDa_Da( (TNLFunctionDaDaDa_Da)r );
		}
		
		if( r instanceof FunctionDaDa_Ia ) {
			return (FunctionDaDa_Ia)r;
		} else if( r instanceof FunctionDaDa_Da ) {
			return new AdaptOutDaDa_Da_Ia( (FunctionDaDa_Da)r );
		} else if( r instanceof Number ) {
			return new Constant_Ia( ((Number)r).intValue() );
		} else {
			throw new CompileError("Can't convert "+r.getClass()+" to FunctionDaDaDa_Da", sloc);
		}		
	}
	
	public static int toInt( Object r, SourceLocation sloc ) throws CompileError {
		if( r instanceof Number ) {
			return ((Number)r).intValue();
		} else {
			throw new CompileError("Can't convert "+r.getClass()+" to double", sloc);
		}
	}
	
	public static double toDouble( Object r, SourceLocation sloc ) throws CompileError {
		if( r instanceof Number ) {
			return ((Number)r).doubleValue();
		} else {
			throw new CompileError("Can't convert "+r.getClass()+" to double", sloc);
		}
	}

	public static double getValue( FunctionDaDa_Da da, double x, double y ) {
		double[] ax = new double[]{x};
		double[] ay = new double[]{y};
		DataDa out = da.apply(new DataDaDa(ax,ay));
		return out.v[0];
	}

	public static double getValue( FunctionDaDaDa_Da da, double x, double y, double z ) {
		double[] ax = new double[]{x};
		double[] ay = new double[]{y};
		double[] az = new double[]{z};
		DataDa out = da.apply(new DataDaDaDa(ax,ay,az));
		return out.v[0];
	}

	public static double getConstantValue( TNLFunctionDaDaDa_Da da ) {
		return getValue( da, 0, 0, 0 );
	}
	
	public static Constant_Da getConstantFunction( TNLFunctionDaDaDa_Da f ) {
		return new Constant_Da( getConstantValue(f) );
	}
	
	public static TNLFunctionDaDaDa_Da collapseIfConstant( TNLFunctionDaDaDa_Da f ) {
		if( f.isConstant() ) return getConstantFunction(f);
		return f;
	}
}
