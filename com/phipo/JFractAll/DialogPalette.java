/*
JFractAll/DialogPalette.java

	
Copyright (C) 2001 Philippe Yves Poupon
	
This program is distributed under the terms of the GNU General Public Licence



This file is part of JFractAll.

PPLogo is free software; you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation; either version 2, or (at your option)
any later version.
 
PPLogo is distributed in the hope that it will be useful, but
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


package phipo.JFractAll;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JFrame;
import javax.swing.JCheckBox;
import javax.swing.colorchooser.*;

import phipo.PhiLib.Interface.*;




//***********************************
public class DialogPalette extends PFrameChild //JFrame 
	implements ItemListener, ActionListener, ChangeListener{
	
	static 	DialogPalette cs_TheDialogPalette=null;

	TColor c_tc;
	CanvasPalette c_canvas_palette;
	final JColorChooser c_tcc;
	JButton  c_button_debut, c_button_fin;
	PPIntField c_size;
	Color c_deb_color=null, c_fin_color=null;

	//-----------------------
	DialogPalette( TColor p_tc){
		super("Palette");

		c_tc = p_tc;

		getContentPane().setLayout( new BorderLayout() );
		setSize(300, 400 );

		//-----------------------
		JPanel l_panel_north = new JPanel();		
		getContentPane().add( l_panel_north, BorderLayout.NORTH );
		JPanel l_panel_south = new JPanel();
		getContentPane().add( l_panel_south, BorderLayout.SOUTH );
		JPanel l_panel_east  = new JPanel();
		getContentPane().add( l_panel_east, BorderLayout.EAST );
		JPanel l_panel_west  = new JPanel();
		getContentPane().add( l_panel_west, BorderLayout.WEST );
		JPanel l_panel_center= new JPanel();
		getContentPane().add( l_panel_center, BorderLayout.CENTER );
		//-----------------------

		//		l_panel_north.setLayout( new GridLayout(0 , 1 ));
		//		l_panel_north.add( c_pos_Y );
		//		getContentPane().add( l_panel_north, BorderLayout.NORTH );
		//-----------------------

		//		l_panel_east.setLayout( new GridLayout( 0, 1 ));
		//		l_panel_east.setBorder( BorderFactory.createEmptyBorder( 10, 10, 10, 10 ));
		//===============  CENTER ===========

		// ==============  WEST ==============


		l_panel_west.setLayout( new BorderLayout());




		JPanel l_panel= new JPanel();
		l_panel.setLayout( new GridLayout( 4, 0 ));
		l_panel_west.add( l_panel, BorderLayout.SOUTH );
		c_size = new PPIntField( "Palette size", c_tc.c_max_color, PPField.HORIZONTAL);
		c_size.addActionListener( this );
		l_panel.add( c_size );

		c_button_debut = new JButton( "Debut degrade" );
		c_button_debut.setActionCommand( "Debut");
		c_button_debut.addActionListener( this );

		c_button_fin = new JButton( "Fin degrade" );
		c_button_fin.setActionCommand( "Fin");
		c_button_fin.addActionListener( this );
	
		JButton  c_button_go = new JButton( "Go" );
		c_button_go.setActionCommand( "Go");
		c_button_go.addActionListener( this );

		l_panel.add( c_button_debut );
		l_panel.add( c_button_fin );
		l_panel.add( c_button_go );


		JScrollPane l_scrollpane = new JScrollPane( (c_canvas_palette = new CanvasPalette( c_tc, c_button_debut, c_button_fin )),																		
																								JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
																								JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		
		c_canvas_palette.setScrollPane(l_scrollpane);
		c_canvas_palette.setPreferredSize( new Dimension(130,130) );
		l_scrollpane.revalidate();
		//		c_canvas_palette = new CanvasPalette( c_tc );
		//		l_panel_west.add( c_canvas_palette );
		l_panel_west.add( l_scrollpane, BorderLayout.CENTER  );






		//===============  EAST =============
		c_tcc = new JColorChooser( Color.black );
		l_panel_east.add( c_tcc, BorderLayout.WEST );




		//=============== SOUTH ============
		l_panel_south.setLayout( new GridLayout( 1, 0 ));
		l_panel_south.setBorder( BorderFactory.createEmptyBorder( 10, 10, 10, 10 ));

		JButton  c_button_apply = new JButton( "Hide" );
		c_button_apply.setActionCommand( "Hide");
		c_button_apply.addActionListener( this );

		//		JButton  c_button_cancel = new JButton( "Cancel" );
		//		c_button_cancel.setActionCommand( "Cancel");
		//		c_button_cancel.addActionListener( this );
		l_panel_south.add( c_button_apply );
		//		l_panel_south.add( c_button_cancel);
		//===============

		
		

		pack();
		cs_TheDialogPalette = this;
	}
	//---------------------
	public void itemStateChanged(ItemEvent p_e ){
		Object l_source = p_e.getItemSelectable();
		
	}
	//---------------------
	public void actionPerformed( ActionEvent p_e ){

		if( p_e.getActionCommand().equals("Debut"))
			{
				c_deb_color = c_tcc.getColor();
				c_button_debut.setBackground( c_deb_color );
				c_canvas_palette.setDebut( c_deb_color );
			}

		if( p_e.getActionCommand().equals("Fin"))
			{
				c_fin_color = c_tcc.getColor();
				c_button_fin.setBackground( c_fin_color );
				c_canvas_palette.setFin( c_fin_color );
			}

		if( p_e.getActionCommand().equals("Go"))
			{
				c_canvas_palette.degrade();
			}

		if( p_e.getActionCommand().equals("Hide"))
			{
				setVisible( false );
			}

		System.out.println( "Event" );
		if( c_size.isAction( p_e.getSource())==true )
				{
					 int p_size = c_size.getInt();
					 if( p_size > 16 && p_size < 65535 ){
						 c_tc.resize( p_size );
						 c_canvas_palette.repaintAll();
					 }
				}
	}
	//---------------------
	public void stateChanged( ChangeEvent p_e ){
	}
}

//***********************************
