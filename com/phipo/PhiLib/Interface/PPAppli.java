/*
phipo/PhiLib/Interface/PPAppli.java

	
Copyright (C) 2001 Philippe Yves Poupon
	
This program is distributed under the terms of the GNU General Public Licence



This file is part of phipo/PhiLib/Interface.

PPMandelbrot is free software; you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation; either version 2, or (at your option)
any later version.
 
PPMandelbrot is distributed in the hope that it will be useful, but
WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
General Public License for more details.

You should have received a copy of the GNU General Public License
along with GNU Classpath; see the file COPYING.  If not, write to the
Free Software Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA
02111-1307 USA.

As a special exception, if you link this library with other files to
produce an executable, this library does not by itself cause the
resulting executable to be covered by the GNU General Public License.
This exception does not however invalidate any other reasons why the
executable file might be covered by the GNU General Public License. */

package phipo.PhiLib.Interface;
import  phipo.PhiLib.Interface.*;

import javax.swing.JDesktopPane;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JMenuBar;
import javax.swing.JFrame;

import java.awt.event.*;
import java.awt.*;
import javax.swing.event.*;
import javax.swing.JToolBar;
import javax.swing.JButton;

import javax.swing.JOptionPane;



//***********************************
public abstract class PPAppli extends JFrame
	implements ActionListener, ItemListener{

	 public JDesktopPane c_desktop;
	 public JMenuBar     c_menubar;
	 public JToolBar     c_toolbar;


	public static PPAppli ThePPAppli;
	public static PPAppli GetAppli() { return ThePPAppli; }
	
	//-------------------------------------
public 	PPAppli( String p_str, boolean p_desk ){
		super( p_str );
		ThePPAppli = this;



		// ----- Make the big window be indented 50 pixels from each edge 
		//of the screen.
		int inset = 50;
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setBounds(inset, inset, 
							screenSize.width - inset*2, 
							screenSize.height-inset*2);
		
		
		//---- Quit this app when the big window closes. 
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		
		getContentPane().setLayout( new BorderLayout());
    c_toolbar = new JToolBar();

		getContentPane().add( c_toolbar, BorderLayout.NORTH );

		if( p_desk ){			
			//Set up the GUI.
			c_desktop = new JDesktopPane(); //a specialized layered pane
			getContentPane().add( c_desktop, BorderLayout.CENTER );
			//Make dragging faster:
			c_desktop.putClientProperty("JDesktopPane.dragMode", "outline");
		}

		//		createFrameDraw(); //Create first window
		//		setContentPane(c_desktop);

		setJMenuBar(c_menubar = new JMenuBar());


	}
	//-------------------------------------
		public 	JMenuItem  addItem( JMenu p_menu, String p_str ){

		JMenuItem l_item = new JMenuItem( p_str);

		l_item.addActionListener( this );	 

		p_menu.add( l_item);
		return l_item;
	}
	//-------------------------------------
public 	JMenuItem  addAbout( JMenu p_menu ){

		JMenuItem l_item = new JMenuItem( "About");

		l_item.addActionListener( this );	 

		p_menu.add( l_item);
		return l_item;
	}
	//-------------------------------------
public 	JButton addButtonToToolbar( String p_str ){

		JButton  l_button = new JButton( p_str );

		l_button.setActionCommand( "p_str");
		l_button.addActionListener( this );
		c_toolbar.add( l_button );
		return l_button;
	}
	//-------------------------------------
	public void openChild(  PFrameChild p_frame ){
		p_frame.setClosable(false );
		try{
			p_frame.setIcon(false);
		}catch(java.beans.PropertyVetoException e ){
		}
		p_frame.setVisible(true); //necessary as of kestrel
		p_frame.show();
	}
	//-------------------------------------
	public void addChild( PFrameChild p_frame ){
		p_frame.setVisible(true); //necessary as of kestrel				
		c_desktop.add(p_frame); 
		p_frame.show();
		try{
			p_frame.setSelected(true);
		}
		catch( java.beans.PropertyVetoException e ){
		}
	}
	//-------------------------------------
	public void removeChild( PFrameChild p_frame ){
		p_frame.setVisible(false); //necessary as of kestrel				
		c_desktop.remove(p_frame); 
	}

		public void log( String pStr ){
		}
	//-------------------------------------

};
//***********************************
