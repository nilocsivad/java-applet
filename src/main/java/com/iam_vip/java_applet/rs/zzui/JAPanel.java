/**
 * 
 */
package com.iam_vip.java_applet.rs.zzui;

import java.awt.Color;
import java.awt.LayoutManager;

import javax.swing.JPanel;


/**
 * @author Colin
 * 		
 */
public class JAPanel extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5123824041726365140L;
	
	
	/**
	 * 
	 */
	public JAPanel() {}
	
	
	
	/**
	 * @param layout
	 */
	public JAPanel( LayoutManager layout ) {
		super( layout );
	}
	
	/**
	 * 
	 * @param r
	 * @param g
	 * @param b
	 * @return
	 */
	public void setBG( int r, int g, int b ) {
		
		this.setBG( new Color( r, g, b ) );
	}
	
	/**
	 * 
	 * @param bg
	 * @return
	 */
	public void setBG( Color bg ) {
		
		super.setBackground( bg );
	}
	
	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		
		return serialVersionUID;
	}
	
}
