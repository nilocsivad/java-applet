package com.iam_vip.java_applet;

import java.awt.Dimension;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.swing.JComboBox;

import org.junit.Test;

import gnu.io.CommPort;
import gnu.io.CommPortIdentifier;
import gnu.io.SerialPort;

/**
 * Unit test for simple App.
 */
public class AppTest {
	
	
	@Test
	public void run() {
		
		CommPortIdentifier[] comms = null;;
		
		{
			@SuppressWarnings( "unchecked" ) Enumeration< CommPortIdentifier > enums = CommPortIdentifier.getPortIdentifiers();
			
			List< CommPortIdentifier > tmp = new ArrayList< CommPortIdentifier >();
			List< String > list = new ArrayList< String >();
			
			while ( enums.hasMoreElements() ) {
				CommPortIdentifier port = enums.nextElement();
				if ( port.getPortType() == CommPortIdentifier.PORT_SERIAL ) {
					tmp.add( port );
					list.add( port.getName() );
				}
			}
			
			comms = tmp.toArray( new CommPortIdentifier[ 0 ] );
			
		}
		
		{
			try {
				
				CommPortIdentifier identifier = comms[ 0 ];
				CommPort port = identifier.open( "COM1", 1000 * 10 );
				
				if ( port instanceof SerialPort ) {
					SerialPort serial = ( SerialPort ) port;
					
					serial.setSerialPortParams( 9600, 8, 1, 0 );
					OutputStream output = serial.getOutputStream();
					
					byte[] buf = new byte[1024];
					
					output.write( buf );
					output.close();
					
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
		
	}
	
}

