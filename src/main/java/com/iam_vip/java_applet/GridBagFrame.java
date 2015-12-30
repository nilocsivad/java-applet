/**
 * 
 */
package com.iam_vip.java_applet;

import java.awt.HeadlessException;

import javax.swing.JFrame;
import java.awt.GridBagLayout;
import javax.swing.JPanel;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.FlowLayout;


/**
 * @author Colin
 */
public class GridBagFrame extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3238761044947926444L;
	
	
	/**
	 * @throws HeadlessException
	 */
	public GridBagFrame() throws HeadlessException {
		setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		GridBagLayout gridBagLayout = new GridBagLayout();
		getContentPane().setLayout( gridBagLayout );
	}
	
	/**
	 * @param title
	 * @throws HeadlessException
	 */
	public GridBagFrame( String title ) throws HeadlessException {
		super( title );
	}
	
}
