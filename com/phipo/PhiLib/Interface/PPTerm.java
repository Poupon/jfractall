package phipo.PhiLib.Interface;

import  phipo.PhiLib.Interface.*;

import javax.swing.*;

import java.awt.event.*;
import java.awt.*;
import java.io.PrintStream;

import  phipo.PhiLib.Interface.PPTextStream;

//***********************************
public class PPTerm extends JPanel implements ActionListener, MouseListener{

	protected JTextArea      cHisto;
	protected JTextField     cSaisie;
	protected ActionListener cListener;
	protected String         cLastCmd;

	protected PrintStream    cPrintStream=null;

	//-----------------------
public 	String getLastCmd() { return cLastCmd; }
	//-----------------------
public 	PPTerm(){
		
		cHisto = new JTextArea();
		cHisto.setEditable( false );
		cHisto.addMouseListener( this );		
		JScrollPane cScrollpane = new JScrollPane( cHisto,
																								JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,	
																								JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED );
		cSaisie = new JTextField();
		cSaisie.addActionListener( this );

		GridBagLayout lGridBag = new GridBagLayout();
		setLayout( lGridBag);
		GridBagConstraints c = new GridBagConstraints();
		c.gridwidth = GridBagConstraints.REMAINDER;

		c.fill = GridBagConstraints.HORIZONTAL;
		lGridBag.setConstraints(cSaisie, c);
		add( cScrollpane );

		c.fill = GridBagConstraints.BOTH;
		c.weightx = 1.0;
		c.weighty = 1.0;
		lGridBag.setConstraints(cScrollpane, c);
		add( cSaisie );

		Font police = new Font( "Monospaced", Font.PLAIN, 14 );
		cHisto.setFont(police);

		cPrintStream = new PrintStream( new PPTextStream(cHisto)); 
	}
	//----------------------
public 	void addActionListener( ActionListener pListener ){
		cListener = pListener;
	}
	//----------------------
public 	void append( String pStr ){
		cHisto.append( pStr );
	}
	//----------------------
public 	void appendln( String pStr ){
		cHisto.append( pStr );
		cHisto.append( "\n" );
	}
	//----------------------
public void actionPerformed(ActionEvent pEv ){
		System.out.println(  "actionPerformed : " + pEv.getActionCommand() + " : " + pEv.getModifiers() );

		if( actionPopupPerformed( pEv )==false){
				
				cLastCmd = cSaisie.getText();
				cHisto.append( cLastCmd + "\n" );
				cSaisie.selectAll();
				
				if( cListener != null )
						cListener.actionPerformed( pEv );
		}
	}
 //----------------------
		public PrintStream Stream() { return cPrintStream; }
 //----------------------
		public JTextArea getLog( ) { return cHisto;}
 //----------------------
		public JTextField getSaisie( ) { return cSaisie;}
	//-------------------------- 
	public void mousePressed( MouseEvent pE ) {
		System.out.println( "mouse clicked" );		

		if( SwingUtilities.isRightMouseButton( pE ) == true 
				&& pE.getClickCount() == 1 ) {
				
				JPopupMenu lPopmenu = new JPopupMenu();
				initPopup( lPopmenu );

				lPopmenu.show( pE.getComponent(),
											 pE.getX(),
											 pE.getY() );
		}					

	}
	//-------------------------- 
		void setSaisie(String pStr){
				
		}
	//-------------------------- 
	public void mouseReleased( MouseEvent pE ) {
	}
	
	public void mouseEntered( MouseEvent pE ) {
	}

	public void mouseExited( MouseEvent pE ) {
	}
	// -----------------------------
	public void mouseClicked( MouseEvent pE ) {

	}
	//-------------------------------------
	public void mouseDragged( MouseEvent pE ){

	}
	//-------------------------------------
	public void mouseMoved( MouseEvent pE ){
		
	}
	// ------------------------
	protected JPopupMenu initPopup(JPopupMenu p_popmenu){
		
		JMenuItem l_mitem;
		p_popmenu.add( (l_mitem = new JMenuItem( "Clear" )));
		l_mitem.addActionListener(this);
		return p_popmenu;
	}
	// ---------------------
	protected boolean actionPopupPerformed( ActionEvent pEv ){		
			
			if( pEv.getActionCommand().equals(  "Clear" )){
					cHisto.setText("");
					return true;
			}
			return false;
	}

};
