/**
 * 
 */
package com.iam_vip.java_applet;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.iam_vip.java_applet.rs.c.C;


/**
 * @author Colin
 * 		
 */
public class WelcomeDialog extends JDialog implements C {
	
	/**
	 * 
	 */
	private static final long	serialVersionUID	= -260798595195928229L;
													
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
		
		{ // <parse-menu-xml>
			
			List< Element > treeData = this.getTreeData();
			
			long l = System.currentTimeMillis() - start;
			l = l > 3000 ? 3000 - l : 100;
			Thread.sleep( l );
			
			// new JavaAppletFrame().display( this, treeData );
			new GridBagFrame().display( this );
			
		} // </parse-menu-xml>
		
	}
	
	/**
	 * @return
	 * @throws DocumentException
	 */
	@SuppressWarnings( "unchecked" )
	private List< Element > getTreeData() throws DocumentException {
		
		SAXReader reader = new SAXReader();
		reader.setEncoding( "UTF-8" );
		Document xmlDoc = reader.read( WelcomeDialog.class.getResourceAsStream( TREE_MENU_DATA ) );
		
		List< ? > nodes = xmlDoc.selectNodes( "//root/node" );
		List< Element > menus = new ArrayList< Element >( nodes.size() );
		menus.addAll( ( Collection< ? extends Element > ) nodes );
		return menus;
	}
	
}
