package com.iam_vip.java_applet;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

import com.iam_vip.java_applet.rs.u.RegexUtil;

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
													
	private static Integer[]	levels				= {};
													
													
	public static void main( String[] args ) throws Exception {
		
		System.out.println( "Hello World ......" );
		
		if ( args.length > 0 ) {
			
			for ( String arg : args ) {
				initParameter( arg );
			}
			
		}
		
		UIManager.setLookAndFeel( UIManager.getSystemLookAndFeelClassName() );
		
		new WelcomeDialog().display();
		
	}
	
	private static void initParameter( String arg ) {
		
		if ( RegexUtil.isIntegerString( arg ) ) { // <param1-is-numeric>
			
			String[] nums = arg.split( "," );
			Set< String > set = new HashSet< String >( nums.length );
			set.addAll( Arrays.asList( nums ) );
			
			List< Integer > list = new ArrayList< Integer >( set.size() );
			for ( String txt : set ) {
				list.add( Integer.parseInt( txt ) );
			}
			
			levels = list.toArray( new Integer[ 0 ] );
			
			// </param1-is-numeric>
		}
		else { // <param1-is-not-numeric>
			
			if ( arg.equals( "true" ) ) {
				debug = true;
			}
			else if ( arg.equals( "l" ) || arg.equals( "level" ) ) {
				JOptionPane.showMessageDialog( null, "[default=100,222,333,666]", "Level", JOptionPane.INFORMATION_MESSAGE );
			}
			
			// </param1-is-not-numeric>
		}
	}
	
	
	/**
	 * @return the debug
	 */
	public static boolean isDebug() {
		
		return debug;
	}
	
	
	
	/**
	 * @return the level
	 */
	public static Integer[] getLevels() {
		
		return levels;
	}
	
}
