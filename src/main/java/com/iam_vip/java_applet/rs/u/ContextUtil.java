/**
 * 
 */
package com.iam_vip.java_applet.rs.u;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.iam_vip.java_applet.rs.c.C;

/**
 * @author Administrator
 * 		
 */
public class ContextUtil implements C {
	
	/**
	 * 
	 */
	private ContextUtil() {
		ctx = new ClassPathXmlApplicationContext( SPRING_XML );
	}
	
	
	private static final class BeanFactorySingle {
		
		private static final ContextUtil factory = new ContextUtil();
	}
	
	
	public static final ContextUtil getInstance() {
		
		return BeanFactorySingle.factory;
	}
	
	
	private static ApplicationContext ctx;
	
	
	@SuppressWarnings( "unchecked" )
	public final < T > T getBean( String key ) {
		
		return ( T ) ctx.getBean( key );
	}
	
}
