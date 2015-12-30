/**
 * 
 */
package com.iam_vip.java_applet.pnl.code;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.filechooser.FileFilter;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.iam_vip.java_applet.JAPanel;
import com.iam_vip.java_applet.StartupApp;
import com.iam_vip.java_applet.rs.u.DTUtil;
import com.iam_vip.java_applet.zzui.JAButton;
import com.sun.xml.internal.messaging.saaj.util.ByteOutputStream;

import gnu.io.CommPort;
import gnu.io.CommPortIdentifier;
import gnu.io.SerialPort;
import gnu.io.SerialPortEvent;
import gnu.io.SerialPortEventListener;

/**
 * @author Colin
 */
public class GenerateQRCode extends JAPanel implements ActionListener, SerialPortEventListener {
	
	/**
	 * 
	 */
	private static final long	serialVersionUID	= 8375036441039109348L;
													
	private static final int	BTN_ID_MAKE_UP		= 10000;
	private static final int	BTN_ID_SAVE_AS		= 10001;
													
													
	InputStream					is;
	// FileWriter fw;
	
	
	/**
	 * 
	 */
	public GenerateQRCode() {
		
		super( new FlowLayout( FlowLayout.LEFT, 0, 0 ) );
		this.setBorder( null );
		
		this.setPreferredSize( new Dimension( PANEL_WIDTH, panelHeight ) );
		
		this.initComponent();
		
		// try {
		// fw = new FileWriter( "F:\\out.txt", true );
		// }
		// catch ( IOException e ) {
		// e.printStackTrace();
		// }
	}
	
	
	private CommPortIdentifier[]	comms	= null;;
											
											
	private JTextArea				textArea;
	private JLabel					codeImgLbl;
	private File					codeImgFile;
									
									
	/**
	 * 
	 */
	private void initComponent() {
		
		{
			JPanel panel = new JPanel( new FlowLayout( FlowLayout.LEFT, 0, 0 ) );
			{
				textArea = new JTextArea( "请输入内容( 生成后请立即扫描测试,有可能内容过多而生成失败,请尽量控制内容 ):", 5, 0 );
				textArea.setSelectedTextColor( new Color( 240, 240, 240 ) );
				textArea.setLineWrap( true );
				textArea.setWrapStyleWord( true );
				
				JScrollPane scrollPane = new JScrollPane( textArea );
				scrollPane.setBorder( DEFAULT_BORDER );
				scrollPane.setPreferredSize( new Dimension( PANEL_WIDTH, textArea.getPreferredSize().height + 8 ) );
				panel.add( scrollPane );
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
			codeImgLbl = new JLabel();
			codeImgLbl.setPreferredSize( new Dimension( 400, 400 ) );
			codeImgLbl.setBorder( DEFAULT_BORDER );
			panel.add( codeImgLbl );
			this.add( panel );
		}
		{
			JPanel panel = new JPanel( new FlowLayout( FlowLayout.CENTER ) );
			panel.setPreferredSize( new Dimension( 120, 400 ) );
			{
				JAButton button = new JAButton( BTN_ID_MAKE_UP, "Make Up..." );
				button.setToolTipText( "生成QR二维码" );
				button.setPreferredSize( new Dimension( 100, 24 ) );
				button.addActionListener( this );
				panel.add( button );
			}
			{
				JAButton button = new JAButton( BTN_ID_SAVE_AS, "Save As..." );
				button.setToolTipText( "QR另存为" );
				button.setPreferredSize( new Dimension( 100, 24 ) );
				button.addActionListener( this );
				panel.add( button );
			}
			// {
			// @SuppressWarnings( "unchecked" ) Enumeration< CommPortIdentifier > enums = CommPortIdentifier.getPortIdentifiers();
			//
			// List< CommPortIdentifier > tmp = new ArrayList< CommPortIdentifier >();
			// List< String > list = new ArrayList< String >();
			//
			// while ( enums.hasMoreElements() ) {
			// CommPortIdentifier port = enums.nextElement();
			// if ( port.getPortType() == CommPortIdentifier.PORT_SERIAL ) {
			// tmp.add( port );
			// list.add( port.getName() );
			// }
			// }
			//
			// comms = tmp.toArray( new CommPortIdentifier[ 0 ] );
			// String[] items = list.toArray( new String[ 0 ] );
			//
			// JComboBox< String > boxes = new JComboBox< String >( items );
			// boxes.setPreferredSize( new Dimension( 100, 24 ) );
			// boxes.addActionListener( this );
			// panel.add( boxes );
			// }
			this.add( panel );
		}
	}
	
	
	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	public void actionPerformed( ActionEvent e ) {
		
		if ( e.getSource() instanceof JAButton ) {
			
			JAButton button = ( JAButton ) e.getSource();
			switch ( button.getIdentity() ) {
				
				case BTN_ID_MAKE_UP:
					this.touchMakeUpButton( e );
					break;
				case BTN_ID_SAVE_AS:
					this.touchSaveAsButton( e );
					break;
			}
			
			return;
		}
		
		if ( e.getSource() instanceof JComboBox ) {
			
			JComboBox< ? > boxes = ( JComboBox< ? > ) e.getSource();
			
			this.selectComboBox( boxes.getSelectedIndex() );
		}
		
	}
	
	
	/**
	 * @param e
	 */
	private void touchMakeUpButton( ActionEvent event ) {
		
		try {
			String text = textArea.getText();
			
			if ( StartupApp.isDebug() ) System.out.println( "QRCode Content:" + text );
			
			codeImgFile = File.createTempFile( "qr-code", ".jpg" );
			
			Map< EncodeHintType, String > hints = new HashMap< EncodeHintType, String >();
			hints.put( EncodeHintType.CHARACTER_SET, "UTF-8" );
			
			BitMatrix bitMatrix = new MultiFormatWriter().encode( text, BarcodeFormat.QR_CODE, 400, 400, hints );
			MatrixToImageWriter.writeToPath( bitMatrix, "jpg", Paths.get( codeImgFile.getAbsolutePath() ) );
			
			codeImgLbl.setIcon( new ImageIcon( codeImgFile.getAbsolutePath() ) );
			
			if ( StartupApp.isDebug() ) System.out.println( "QR Image:" + codeImgFile.getAbsolutePath() );
			
		}
		catch ( Exception e ) {
			if ( StartupApp.isDebug() ) e.printStackTrace();
			
			JOptionPane.showMessageDialog( null, "生成二维码失败,请重试!", "二维码", JOptionPane.ERROR_MESSAGE );
		}
		
	}
	
	
	/**
	 * @param e
	 */
	private void touchSaveAsButton( ActionEvent event ) {
		
		try {
			
			JFileChooser fc = new JFileChooser();
			fc.setDialogTitle( "请选择保存文件路径:" );
			fc.setAcceptAllFileFilterUsed( false );
			fc.setSelectedFile( new File( "QR二维码_" + DTUtil.formatDT( DTUtil.FMT_FILE ) ) );
			fc.setFileFilter( new FileFilter() {
				
				public String getDescription() {
					
					return "图片文件";
				}
				
				public boolean accept( File f ) {
					
					return f.isDirectory() || f.getName().endsWith( ".png" ) || f.getName().endsWith( ".jpg" ) || f.getName().endsWith( ".bmp" );
				}
			} );
			fc.setDialogType( JFileChooser.SAVE_DIALOG );
			int result = fc.showOpenDialog( null );
			if ( result == JFileChooser.APPROVE_OPTION ) {
				File f = fc.getSelectedFile();
				InputStream is = new BufferedInputStream( new FileInputStream( codeImgFile ) );
				OutputStream os = new BufferedOutputStream( new FileOutputStream( f ) );
				byte[] buf = new byte[ 1024 ];
				int len = 0;
				while ( ( len = is.read( buf ) ) > 0 ) {
					os.write( buf, 0, len );
				}
				is.close();
				os.close();
				
				JOptionPane.showMessageDialog( null, "文件已经保存至 " + f.getAbsolutePath() + " ,请注意查看!", "另存为", JOptionPane.INFORMATION_MESSAGE );
			}
			
		}
		catch ( Exception e ) {
			if ( StartupApp.isDebug() ) e.printStackTrace();
			
			JOptionPane.showMessageDialog( null, "另存为失败,请重试!", "另存为", JOptionPane.ERROR_MESSAGE );
		}
		
	}
	
	
	
	// private static class MatrixToImageWriter {
	//
	// private static final int BLACK = 0xFF000000;
	// private static final int WHITE = 0xFFFFFFFF;
	//
	//
	// private static void writeToFile( BitMatrix bitMatrix, String format, File codeImgFile ) throws IOException {
	//
	// BufferedImage image = toBufferedImage( bitMatrix );
	// if ( !ImageIO.write( image, format, codeImgFile ) ) { throw new IOException( "Could not write an image of format " + format ); }
	// }
	//
	// private static BufferedImage toBufferedImage( BitMatrix matrix ) {
	//
	// int width = matrix.getWidth();
	// int height = matrix.getHeight();
	// BufferedImage image = new BufferedImage( width, height, BufferedImage.TYPE_INT_RGB );
	// for ( int x = 0; x < width; x ++ ) {
	// for ( int y = 0; y < height; y ++ ) {
	// image.setRGB( x, y, matrix.get( x, y ) ? BLACK : WHITE );
	// }
	// }
	// return image;
	// }
	// }
	
	
	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	public void selectComboBox( int idx ) {
		
		try {
			
			CommPortIdentifier identifier = comms[ idx ];
			CommPort port = identifier.open( "COM1", 1000 * 10 );
			
			if ( port instanceof SerialPort ) {
				SerialPort serial = ( SerialPort ) port;
				
				serial.setSerialPortParams( 9600, 8, 1, 0 );
				is = serial.getInputStream();
				serial.notifyOnDataAvailable( true );
				serial.addEventListener( this );
				
				System.out.println( "OK" );
			}
			
		}
		catch ( Exception e ) {
			// try {
			//
			// StackTraceElement el = Thread.currentThread().getStackTrace()[ 0 ];
			// fw.write( "------------ " + el.getClassName() + " -- " + el.getMethodName() + " -- " + e.getMessage() );
			// el = Thread.currentThread().getStackTrace()[ 1 ];
			// fw.write( "------------ " + el.getClassName() + " -- " + el.getMethodName() + " -- " + e.getMessage() );
			// fw.flush();
			// }
			// catch ( IOException e1 ) {
			// e1.printStackTrace();
			// }
			e.printStackTrace();
		}
		
	}
	
	
	/* (non-Javadoc)
	 * @see gnu.io.SerialPortEventListener#serialEvent(gnu.io.SerialPortEvent)
	 */
	public void serialEvent( SerialPortEvent event ) {
		
		byte[] buffer = new byte[ 1024 ];
		
		ByteOutputStream output = new ByteOutputStream();
		
		int len = 0;
		try {
			
			
			while ( ( len = is.read( buffer ) ) > 0 ) {
				output.write( buffer, 0, len );
			}
			
			
			String txt = new String( output.getBytes(), 0, output.size(), "GB2312" );
			System.out.println( txt );
			textArea.append( txt );
			output.close();
		}
		catch ( Exception e ) {
			// try {
			//
			// StackTraceElement el = Thread.currentThread().getStackTrace()[ 0 ];
			// fw.write( "------------ " + el.getClassName() + " -- " + el.getMethodName() + " -- " + e.getMessage() );
			// el = Thread.currentThread().getStackTrace()[ 1 ];
			// fw.write( "------------ " + el.getClassName() + " -- " + el.getMethodName() + " -- " + e.getMessage() );
			// fw.flush();
			// }
			// catch ( IOException e1 ) {
			// e1.printStackTrace();
			// }
			
		}
		
	}
	
	
	
	
	
	
	
	
	
	
}
