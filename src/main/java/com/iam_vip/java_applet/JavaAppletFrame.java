/**
 * 
 */
package com.iam_vip.java_applet;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.Toolkit;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTree;
import javax.swing.border.EmptyBorder;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.MutableTreeNode;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreeSelectionModel;

import org.dom4j.Attribute;
import org.dom4j.Element;

import com.iam_vip.java_applet.rs.c.C;
import com.iam_vip.java_applet.rs.u.ContextUtil;

/**
 * @author Colin
 */
public class JavaAppletFrame extends JFrame implements C, TreeSelectionListener {
	
	/**
	 * 
	 */
	private static final long	serialVersionUID	= 4799580515574834063L;
													
	private static final int	TREE_W				= 200;
													
	// private static final Color BG_COLOR = new Color( 242, 242, 242 );
	private static final Color	BG_COLOR			= Color.WHITE;
													
													
	/**
	 * 
	 */
	public JavaAppletFrame() {
		super( APP_TITLE );
	}
	
	
	private JPanel	rightPanel;
	private JTree	menuTree;
					
					
	/* (non-Javadoc)
	 * @see javax.swing.event.TreeSelectionListener#valueChanged(javax.swing.event.TreeSelectionEvent)
	 */
	public void valueChanged( TreeSelectionEvent event ) {
		
		Object obj = menuTree.getSelectionPath().getLastPathComponent();
		if ( obj instanceof MSelfTreeNode ) {
			try {
				resetMainPanel( ( ( MSelfTreeNode ) obj ).getBean() );
			}
			catch ( Exception e ) {
				
				if ( StartupApp.isDebug() ) e.printStackTrace();
				
				String title = event.getPath().getLastPathComponent().toString();
				JOptionPane.showMessageDialog( this, ( "未找到\"" + title + "\"对应的界面!" ), "功能选择", JOptionPane.ERROR_MESSAGE );
			}
		}
	}
	
	/**
	 * @param bean
	 */
	private void resetMainPanel( String key ) {
		
		rightPanel.removeAll();
		
		JABasePanel panel = ContextUtil.getInstance().getBean( key );
		
		JScrollPane p = new JScrollPane( panel );
		p.setBorder( null );
		rightPanel.add( p );
		rightPanel.updateUI();
		
	}
	
	private final void resetLocation() {
		
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize(); // ** 屏幕尺寸
		Insets insets = Toolkit.getDefaultToolkit().getScreenInsets( this.getGraphicsConfiguration() ); // ** 边框尺寸( 任务栏,上下左右 )
		
		int _w = MAX_WIDTH;
		int _h = screen.height - insets.top - insets.bottom;
		this.setSize( _w, _h );
		
		JABasePanel.panelHeight = _h - this.getInsets().top * 2;
		
		int x = ( screen.width - _w ) / 2;
		int y = insets.top;
		this.setLocation( x, y );
	}
	
	public void display( JDialog previousView, final List< Element > treeData ) throws Exception {
		
		JSplitPane splitPane = new JSplitPane( JSplitPane.HORIZONTAL_SPLIT );
		
		TreeNode rootTreeNode = initTreeNode( treeData );
		menuTree = new JTree( rootTreeNode );
		JScrollPane leftPane = new JScrollPane( menuTree );
		leftPane.setBackground( BG_COLOR );
		leftPane.setBorder( new EmptyBorder( 5, 5, 5, 5 ) );
		splitPane.add( leftPane );
		
		{
			rightPanel = new JPanel();
			JScrollPane jp = new JScrollPane( rightPanel );
			jp.setBorder( null );
			splitPane.add( jp );
			splitPane.setBorder( null );
		}
		
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
	
	/**
	 * @param treeData
	 * @return
	 */
	private TreeNode initTreeNode( List< Element > treeData ) {
		
		DefaultMutableTreeNode rootTree = new DefaultMutableTreeNode();
		
		Element tree0 = treeData.get( 0 );
		if ( tree0 != null ) {
			tree0 = tree0.getParent();
			if ( tree0.attribute( "name" ) != null ) {
				rootTree = new DefaultMutableTreeNode( tree0.attributeValue( "name" ) );
			}
		}
		
		for ( Element ele : treeData ) {
			MutableTreeNode node = packMutableTreeNode( ele );
			if ( node != null ) rootTree.add( node );
		}
		return rootTree;
	}
	
	/**
	 * @param ele
	 * @return
	 */
	private MutableTreeNode packMutableTreeNode( Element ele ) {
		
		Attribute attr = ele.attribute( "level" );
		if ( attr != null ) {
			
			String level_str = attr.getValue();
			int level = Integer.parseInt( level_str );
			
			boolean r = false;
			for ( int l : StartupApp.getLevels() ) {
				if ( level == l ) {
					r = true;
					break;
				}
			}
			
			if ( !r ) return null;
		}
		
		List< ? > children;
		DefaultMutableTreeNode treeNode = ( children = ele.selectNodes( "node" ) ).size() > 0 ? new DefaultMutableTreeNode( ele.attributeValue( "name" ) ) : new MSelfTreeNode( ele.attributeValue( "name" ), ele.attributeValue( "class" ) );
		if ( children.size() > 0 ) {
			for ( Object obj : children ) {
				MutableTreeNode node = packMutableTreeNode( ( Element ) obj );
				if ( node != null ) treeNode.add( node );
			}
		}
		return treeNode;
	}
	
	
	class MSelfTreeNode extends DefaultMutableTreeNode {
		
		/**
		 * 
		 */
		private static final long	serialVersionUID	= 5713291276154762423L;
														
		private String				bean;
									
									
		public MSelfTreeNode( Object userObject, String bean ) {
			super( userObject );
			this.bean = bean;
		}
		
		public String getBean() {
			
			return bean;
		}
		
	}
	
}
