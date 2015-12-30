/**
 * 
 */
package com.iam_vip.java_applet;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.HeadlessException;
import java.awt.Insets;

import javax.swing.JDialog;
import javax.swing.JFrame;

import com.iam_vip.java_applet.rs.t.GBC;
import com.iam_vip.java_applet.rs.zzui.JAPanel;


/**
 * @author Colin
 */
public class GridBagFrame extends JFrame {
	
	/**
	 * 
	 */
	private static final long	serialVersionUID	= -3238761044947926444L;
													
	private static final int	INIT_W				= 720;
	private static final int	INIT_H				= 450;
													
													
	/**
	 * @throws HeadlessException
	 */
	public GridBagFrame() throws HeadlessException {
		
		this.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		this.setMinimumSize( new Dimension( INIT_W, INIT_H ) );
		
		GridBagLayout gbLayout = new GridBagLayout();
		getContentPane().setLayout( gbLayout );
		
		{
			JAPanel pTop = new JAPanel( new FlowLayout( FlowLayout.LEFT, 0, 0 ) );
			pTop.setBackground( Color.BLUE );
			
			Insets insets = new Insets( 0, 0, 0, 0 );
			GBC gbc = new GBC( 0, 0, 2, 1, 1, 0, GBC.CENTER, GBC.BOTH, insets, INIT_W, 60 );
			
			this.add( pTop, gbc );
		}
		{
			JAPanel pLeft = new JAPanel( new FlowLayout( FlowLayout.LEFT, 0, 0 ) );
			pLeft.setBackground( Color.YELLOW );
			
			Insets insets = new Insets( 0, 0, 0, 0 );
			GBC gbc = new GBC( 0, 1, 1, 1, 0, 1, GBC.CENTER, GBC.BOTH, insets, 120, 340 );
			
			this.add( pLeft, gbc );
		}
		{
			JAPanel pRight = new JAPanel( new FlowLayout( FlowLayout.LEFT, 0, 0 ) );
			pRight.setBackground( Color.RED );
			
			Insets insets = new Insets( 0, 0, 0, 0 );
			GBC gbc = new GBC( 1, 1, 1, 1, 1, 1, GBC.CENTER, GBC.BOTH, insets, 0, 0 );
			
			this.add( pRight, gbc );
		}
	}
	
	/**
	 * @param title
	 * @throws HeadlessException
	 */
	public GridBagFrame( String title ) throws HeadlessException {
		super( title );
	}
	
	/**
	 * @param welcomeDialog
	 */
	public void display( JDialog previousView ) {
		
		previousView.dispose();
		
		this.pack();
		
		this.setVisible( true );
	}
	
}
