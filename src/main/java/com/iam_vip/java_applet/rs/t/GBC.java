/**
 * 
 */
package com.iam_vip.java_applet.rs.t;

import java.awt.GridBagConstraints;
import java.awt.Insets;


/**
 * @author Colin
 */
public final class GBC extends GridBagConstraints {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4328360718846880341L;
	
	
	/**
	 * 
	 */
	public GBC() {}
	
	/**
	 * 
	 * @param insets
	 */
	public GBC( Insets insets ) {
		this.insets = insets;
	}
	
	
	/**
	 * @param gridx
	 * @param gridy
	 * @param gridwidth
	 * @param gridheight
	 * @param weightx
	 * @param weighty
	 * @param anchor
	 * @param fill
	 * @param insets
	 * @param ipadx
	 * @param ipady
	 */
	public GBC( int gridx, int gridy, int gridwidth, int gridheight, double weightx, double weighty, int anchor, int fill, Insets insets, int ipadx, int ipady ) {
		super( gridx, gridy, gridwidth, gridheight, weightx, weighty, anchor, fill, insets, ipadx, ipady );
	}
	
}
