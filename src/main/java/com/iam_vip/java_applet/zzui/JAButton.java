/**
 * 
 */
package com.iam_vip.java_applet.zzui;

import javax.swing.Action;
import javax.swing.Icon;
import javax.swing.JButton;


/**
 * @author Colin
 * 		
 */
public class JAButton extends JButton {
	
	/**
	 * 
	 */
	private static final long	serialVersionUID	= 4261213004569958741L;
													
	protected int				identity;
								
								
	/**
	 * 
	 */
	public JAButton( int identity ) {
		this.identity = identity;
	}
	
	/**
	 * @param icon
	 */
	public JAButton( Icon icon ) {
		super( icon );
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * @param text
	 */
	public JAButton( int identity, String text ) {
		super( text );
		this.identity = identity;
	}
	
	/**
	 * @param a
	 */
	public JAButton( Action a ) {
		super( a );
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * @param text
	 * @param icon
	 */
	public JAButton( String text, Icon icon ) {
		super( text, icon );
		// TODO Auto-generated constructor stub
	}
	
	
	/**
	 * @return the identity
	 */
	public int getIdentity() {
		
		return identity;
	}
	
}
