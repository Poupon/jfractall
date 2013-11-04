package phipo.PhiLib.Interface;

import java.awt.event.*;
import java.awt.Font;


import javax.swing.*;
import javax.swing.event.*;
import javax.swing.JInternalFrame;
import javax.swing.ImageIcon;

import phipo.PhiLib.Interface.*;
import phipo.PhiLib.Util.*;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JFrame;
import javax.swing.JCheckBox;


import java.io.*;
import java.util.*;

import phipo.PhiLib.Interface.*;

// **********************************

public class PPTraceTerm extends PFrameChild implements ActionListener,  MouseListener{
	protected JTextArea      cHisto;
	protected PrintStream    cPrintStream=null;

		//		JMenuBar   cMenuBar;

		//-----------------------
		public 	PPTraceTerm( String pName) {
				super( pName );

				cHisto = new JTextArea();
		 		cHisto.setEditable( false );
				cHisto.addMouseListener( this );		

				JScrollPane lScrollpane = new JScrollPane( cHisto,
																								JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,	
																								JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED );
				getContentPane().add( lScrollpane );

				Font police = new Font( "Monospaced", Font.PLAIN, 14 );
				cHisto.setFont(police);

				cPrintStream = new PrintStream( new PPTextStream(cHisto)); 
				PPAppli.ThePPAppli.addChild( this );	


		}

		//-----------------------
		public void mousePressed( MouseEvent pEv ) {
		}
		//-------------------------- 
		public void mouseReleased( MouseEvent pEv ) {
		}
		
		public void mouseEntered( MouseEvent pEv ) {
		}
		
		public void mouseExited( MouseEvent pEv ) {
		}
		// -----------------------------
		public void mouseClicked( MouseEvent pEv ) {
				
				if( SwingUtilities.isRightMouseButton( pEv ) == true 
						&& pEv.getClickCount() == 1 ) {
						
						JPopupMenu lPopmenu = new JPopupMenu();
						initPopup( lPopmenu );
						
						lPopmenu.show( pEv.getComponent(),
													 pEv.getX(),
													 pEv.getY() );
				}					
		}
		//-------------------------------------
		public void mouseDragged( MouseEvent pEv ){
				
		}
		//-------------------------------------
		public void mouseMoved( MouseEvent pEv ){
				
		}
		// ------------------------
		JPopupMenu initPopup(JPopupMenu pPopmenu){
				
				JMenuItem lMitem;
				pPopmenu.add( (lMitem = new JMenuItem( "Clear" )));
				lMitem.addActionListener(this);
				return pPopmenu;
		}
		//----------------------
		public void actionPerformed(ActionEvent pEv ){
				if( pEv.getActionCommand().equals(  "Clear" )){
						cHisto.setText("");
				}
		}
		//----------------------
		public PrintStream Stream() { return cPrintStream; }
	  //----------------------
		public JTextArea getLog( ) { return cHisto;}

};
//***********************************
