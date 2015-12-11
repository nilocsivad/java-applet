package com.iam_vip.java_applet;

import javax.swing.JDialog;
import javax.swing.UIManager;

/**
 * Startup Application
 */
public class StartupApp extends JDialog {
	
	
	/**
	 * 
	 */
	private static final long	serialVersionUID	= 7750560403182728950L;
													
	/** allow to debug */
	private static boolean		debug				= true;
													
	// private static ApplicationContext context;
	
	
	public static void main( String[] args ) throws Exception {
		
		System.out.println( "Hello World ......" );
		
		if ( args.length > 0 && args[ 0 ].equals( "true" ) ) {
			debug = true;
		}
		
		// context = new ClassPathXmlApplicationContext( new String[] { "config/spring-beans.xml" } );
		
		UIManager.setLookAndFeel( UIManager.getSystemLookAndFeelClassName() );
		
		new WelcomeDialog().display();
		
	}
	
	
	/**
	 * @return the debug
	 */
	public static boolean isDebug() {
		
		return debug;
	}
	
}
