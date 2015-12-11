/**
 * 
 */
package com.iam_vip.java_applet;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;


/**
 * @author Colin
 * 		
 */
public class WelcomeDialog extends JDialog {
	
	/**
	 * 
	 */
	private static final long	serialVersionUID	= -260798595195928229L;
													
	private static final String	WELCOME_IMG			= "/image/welcome.png";
													
	private long				start				= 0;
													
													
	/** */
	public WelcomeDialog() {
		start = System.currentTimeMillis();
	}
	
	/** add welcome image to dialog */
	private final void addWelcomeImage() {
		
		// <load an image show at center />
		this.add( new JLabel( new ImageIcon( WelcomeDialog.class.getResource( WELCOME_IMG ) ) ), BorderLayout.CENTER );
	}
	
	
	/** show at center */
	private final void resetLocation() {
		
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		int w = this.getWidth(), x = ( screen.width - w ) / 2;
		int h = this.getHeight(), y = ( screen.height - h ) / 2;
		this.setLocation( x, y );
	}
	
	public void display() throws Exception {
		
		this.setLocationRelativeTo( null );
		this.setResizable( false );
		this.setUndecorated( true ); // ** remove dialog border
		this.setLayout( new BorderLayout() );
		
		this.addWelcomeImage();
		
		this.pack();
		
		this.resetLocation();
		
		this.setVisible( true );
		
		long l = System.currentTimeMillis() - start;
		Thread.sleep( l );
		
		new JavaAppletFrame().display( this );
		
	}
	
}
