/**
 * 
 */
package com.iam_vip.java_applet.rs.zzui;

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
	private static final long serialVersionUID = 4261213004569958741L;
	
	
	/**
	 * 
	 */
	protected JAButton() {}
	
	
	protected int identity;
	
	
	/**
	 * 
	 * @param identity
	 */
	public JAButton( int identity ) {
		this( identity, "" );
	}
	
	/**
	 * 
	 * @param identity
	 * @param text
	 */
	public JAButton( int identity, String text ) {
		this( identity, text, null );
	}
	
	/**
	 * 
	 * @param identity
	 * @param text
	 * @param icon
	 */
	public JAButton( int identity, String text, Icon icon ) {
		super( text, icon );
		this.identity = identity;
	}
	
	/**
	 * @return the identity
	 */
	public int getIdentity() {
		
		return identity;
	}
	
}
