/**
 * 
 */
package com.iam_vip.java_applet.pnl.file;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.iam_vip.java_applet.JABasePanel;
import com.iam_vip.java_applet.rs.zzui.JAButton;
import com.iam_vip.java_applet.rs.zzui.JATextField;


/**
 * @author Colin
 * 		
 */
public class FileOption extends JABasePanel implements ActionListener {
	
	/**
	 * 
	 */
	private static final long	serialVersionUID	= -5077945881792012428L;
													
	private static final int	BTN_ID_HELLO		= 10000;
													
	private static final int	TXT_ID_PATH			= 10000;
													
	private DropTarget			drop;
								
	private JATextField			txtPath;
								
								
	/**
	 * 
	 */
	public FileOption() {
		
		super( new FlowLayout( FlowLayout.LEFT, 0, 0 ) );
		this.setBorder( null );
		
		this.setPreferredSize( new Dimension( PANEL_WIDTH, panelHeight ) );
		
		this.initComponent();
		
		// <拖拽文件 />
		drop = new DropTarget( this, DnDConstants.ACTION_COPY, this );
	}
	
	
	/**
	 * 
	 */
	private void initComponent() {
		
		{
			JPanel panel = new JPanel( new FlowLayout( FlowLayout.LEFT, 0, 0 ) );
			int w = 0;
			{
				JLabel lblNewLabel = new JLabel("Files:");
				w = lblNewLabel.getWidth();
				panel.add(lblNewLabel);
			}
			{
				txtPath = new JATextField( TXT_ID_PATH );
				txtPath.setEditable( false );
				txtPath.setPreferredSize( new Dimension( PANEL_WIDTH, 24 ) );
				txtPath.setBorder( DEFAULT_BORDER );
				txtPath.setDropTarget( drop );
				panel.add( txtPath );
			}
			this.add( panel );
		}
		
		{
			JPanel emptyPanel = new JPanel( new FlowLayout( FlowLayout.LEFT, 0, 0 ) );
			emptyPanel.setPreferredSize( new Dimension( PANEL_WIDTH, 6 ) );
			this.add( emptyPanel );
		}
		
		{
			JPanel panel = new JPanel( new FlowLayout( FlowLayout.LEFT, 0, 0 ) );
			{
				JAButton button = new JAButton( BTN_ID_HELLO, "hello" );
				button.setPreferredSize( new Dimension( 100, 24 ) );
				button.addActionListener( this );
				panel.add( button );
			}
			this.add( panel );
		}
	}
	
	@Override
	public void drop( DropTargetDropEvent event ) {
		
		try {
			
			if ( event.isDataFlavorSupported( DataFlavor.javaFileListFlavor ) ) {
				
				event.acceptDrop( DnDConstants.ACTION_COPY );
				
				Transferable transferable = event.getTransferable();
				@SuppressWarnings( "unchecked" ) List< File > files = ( List< File > ) transferable.getTransferData( DataFlavor.javaFileListFlavor );
				
				if ( !files.isEmpty() && files.size() > 0 ) {
					
					StringBuffer buffer = new StringBuffer( 16 );
					for ( File f : files ) {
						buffer.append( "|" + f.getAbsolutePath() );
					}
					txtPath.setText( buffer.substring( 1 ) );
				}
				
				event.dropComplete( true ); // ** 告知系统,拖拽完成
			}
			
		}
		catch ( Exception e ) {
			e.printStackTrace();
		}
	}
	
	
	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	public void actionPerformed( ActionEvent e ) {
		
		if ( e.getSource() instanceof JAButton ) {
			
			JAButton button = ( JAButton ) e.getSource();
			switch ( button.getIdentity() ) {
				
				case BTN_ID_HELLO:
					this.touchHelloButton( e );
					break;
			}
		}
		
	}
	
	
	/**
	 * @param e
	 */
	private void touchHelloButton( ActionEvent event ) {
		
		JOptionPane.showMessageDialog( null, "hello", "提示", JOptionPane.INFORMATION_MESSAGE );
		
	}
	
}
