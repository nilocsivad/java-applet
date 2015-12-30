/**
 * 
 */
package com.iam_vip.java_applet.rs.zzui;

import javax.swing.JTextField;


/**
 * @author Colin
 * 		
 */
public class JATextField extends JTextField {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7249870338553411275L;
	
	
	/**
	 * 
	 */
	protected JATextField() {}
	
	
	protected int identity;
	
	
	/**
	 * 
	 * @param identity
	 */
	public JATextField( int identity ) {
		this( identity, "" );
	}
	
	/**
	 * 
	 * @param identity
	 * @param text
	 */
	public JATextField( int identity, String text ) {
		super( text );
		this.identity = identity;
	}
	
	
	/**
	 * @return the identity
	 */
	public int getIdentity() {
		
		return identity;
	}
	
}
