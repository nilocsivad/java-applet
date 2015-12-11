/**
 * 
 */
package com.iam_vip.java_applet;

import java.awt.Dimension;
import java.awt.Insets;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreeSelectionModel;

/**
 * @author Colin
 */
public class JavaAppletFrame extends JFrame implements TreeSelectionListener {
	
	/**
	 * 
	 */
	private static final long	serialVersionUID	= 4799580515574834063L;
													
	private static final int	TREE_W				= 200;
													
	private static final String	ICON				= "/image/welcome.png";
													
													
	/**
	 * 
	 */
	public JavaAppletFrame() {
		super( "Java 小助手" );
	}
	
	
	private JPanel	rightPanel;
	private JTree	menuTree;
					
					
	/* (non-Javadoc)
	 * @see javax.swing.event.TreeSelectionListener#valueChanged(javax.swing.event.TreeSelectionEvent)
	 */
	public void valueChanged( TreeSelectionEvent e ) {
	
	
	}
	
	private final void resetLocation() {
		
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize(); // ** 屏幕尺寸
		Insets insets = Toolkit.getDefaultToolkit().getScreenInsets( this.getGraphicsConfiguration() ); // ** 边框尺寸( 任务栏,上下左右 )
		
		int _w = 980;
		int _h = screen.height - insets.top - insets.bottom;
		this.setSize( _w, _h );
		
		int x = ( screen.width - _w ) / 2;
		int y = insets.top;
		this.setLocation( x, y );
	}
	
	public void display( JDialog previousView ) throws Exception {
		
		JSplitPane splitPane = new JSplitPane( JSplitPane.HORIZONTAL_SPLIT );
		
		TreeNode rootTreeNode = null; // InitTreeNode( treeData );
		menuTree = new JTree( rootTreeNode );
		splitPane.add( new JScrollPane( menuTree ) );
		
		rightPanel = new JPanel();
		splitPane.add( new JScrollPane( rightPanel ) );
		
		menuTree.setAutoscrolls( true );
		menuTree.getSelectionModel().setSelectionMode( TreeSelectionModel.SINGLE_TREE_SELECTION );
		menuTree.addTreeSelectionListener( this );
		
		this.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		this.setResizable( false );
		
		
		this.getContentPane().add( splitPane );
		this.pack();
		
		
		menuTree.setPreferredSize( new Dimension( TREE_W, splitPane.getHeight() ) );
		
		splitPane.setEnabled( false ); // ** 进制拖动
		splitPane.setDividerSize( 1 ); // ** 分隔栏宽度
		
		this.resetLocation(); // ** window size and location
		
		this.setIconImage( new ImageIcon( JavaAppletFrame.class.getResource( ICON ) ).getImage() );
		
		previousView.dispose();
		this.setVisible( true );
		
	}
	
}
