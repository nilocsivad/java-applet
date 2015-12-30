/**
 * 
 */
package com.iam_vip.java_applet;

import java.awt.Color;
import java.awt.LayoutManager;
import java.awt.dnd.DropTargetDragEvent;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.dnd.DropTargetEvent;
import java.awt.dnd.DropTargetListener;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.Border;

import com.iam_vip.java_applet.rs.c.C;

/**
 * @author Administrator
 * 		
 */
public class JABasePanel extends JPanel implements C, DropTargetListener {
	
	
	/**
	 * 
	 */
	private static final long		serialVersionUID	= -656612397530196710L;
														
	protected static final Border	DEFAULT_BORDER		= BorderFactory.createLineBorder( Color.GRAY, 1 );
														
	protected static int			panelHeight			= 600;
														
														
	/**
	 * 
	 */
	public JABasePanel() {}
	
	/**
	 * @param layout
	 */
	public JABasePanel( LayoutManager layout ) {
		super( layout );
	}
	
	/* (non-Javadoc)
	 * @see java.awt.dnd.DropTargetListener#dragEnter(java.awt.dnd.DropTargetDragEvent)
	 */
	public void dragEnter( DropTargetDragEvent dtde ) {
		
		// TODO Auto-generated method stub
		
	}
	
	/* (non-Javadoc)
	 * @see java.awt.dnd.DropTargetListener#dragOver(java.awt.dnd.DropTargetDragEvent)
	 */
	public void dragOver( DropTargetDragEvent dtde ) {
		
		// TODO Auto-generated method stub
		
	}
	
	/* (non-Javadoc)
	 * @see java.awt.dnd.DropTargetListener#dropActionChanged(java.awt.dnd.DropTargetDragEvent)
	 */
	public void dropActionChanged( DropTargetDragEvent dtde ) {
		
		// TODO Auto-generated method stub
		
	}
	
	/* (non-Javadoc)
	 * @see java.awt.dnd.DropTargetListener#dragExit(java.awt.dnd.DropTargetEvent)
	 */
	public void dragExit( DropTargetEvent dte ) {
		
		// TODO Auto-generated method stub
		
	}
	
	/* (non-Javadoc)
	 * @see java.awt.dnd.DropTargetListener#drop(java.awt.dnd.DropTargetDropEvent)
	 */
	public void drop( DropTargetDropEvent dtde ) {
		
		// TODO Auto-generated method stub
		
	}
	
}
