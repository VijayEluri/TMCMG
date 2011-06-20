package togos.noise2.rdf;

import java.io.UnsupportedEncodingException;
import java.util.Iterator;
import java.util.Map;

import org.bitpedia.util.Base32;

import togos.noise2.DigestUtil;

public class ExprUtil
{
	public static String getIdentifier( Object o ) {
		if( o instanceof Expression ) {
			return ((Expression)o).getIdentifier();
		} else {
			return o.toString();
		}
	}
	
	public static String generateIdentifier( Expression e ) {
		String k = e.getTypeName();
		for( Iterator i=e.getAttributeEntries().iterator(); i.hasNext(); ) {
			Map.Entry en = (Map.Entry)i.next();
			k += "+" + en.getKey();
			k += "@" + getIdentifier(en.getValue());
		}
		byte[] sha1;
		try {
			sha1 = DigestUtil.createSha1Digestor().digest(k.getBytes("UTF-8"));
		} catch( UnsupportedEncodingException e1 ) {
			throw new RuntimeException(e1);
		}
		return Base32.encode(sha1);
	}
	
	public static String toString( Expression e ) {
		return e.getTypeName()+"...";
	}
}