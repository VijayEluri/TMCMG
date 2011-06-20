package togos.noise2.vm.vops;

import junit.framework.TestCase;
import togos.noise2.function.LFunctionDaDaDa_Da;
import togos.noise2.rdf.BaseExpression;
import togos.noise2.rdf.Expression;
import togos.noise2.rdf.ExpressionBuilder;
import togos.noise2.rdf.TNLNamespace;

public class VKExpressionCompilerTest extends TestCase
{
	public void testCompileAddConstants() {
		Expression add = new ExpressionBuilder( TNLNamespace.ADD ).
			with( TNLNamespace.TERM, Double.valueOf(4.0) ).
			with( TNLNamespace.TERM, Double.valueOf(5.0) ).toExpression();
		
		VKExpressionCompiler vkec = new VKExpressionCompiler();
		LFunctionDaDaDa_Da addFunc = vkec.expressionToFunction( add );
		double[] x = new double[]{1};
		double[] y = new double[]{2};
		double[] z = new double[]{3};
		double[] dest = new double[]{3};
		addFunc.apply(1, x, y, z, dest);
		assertEquals( 9, (int)dest[0] );
	}

	public void testCompileAddXY() {
		Expression add = new ExpressionBuilder( TNLNamespace.ADD ).
			with( TNLNamespace.TERM, new BaseExpression(TNLNamespace.X) ).
			with( TNLNamespace.TERM, new BaseExpression(TNLNamespace.Y) ).toExpression();
		
		VKExpressionCompiler vkec = new VKExpressionCompiler();
		LFunctionDaDaDa_Da addFunc = vkec.expressionToFunction( add );
		double[] x = new double[]{4};
		double[] y = new double[]{5};
		double[] z = new double[]{6};
		double[] dest = new double[]{3};
		addFunc.apply(1, x, y, z, dest);
		assertEquals( 9, (int)dest[0] );
	}
}