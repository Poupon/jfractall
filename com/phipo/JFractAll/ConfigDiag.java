/*
JFractAll/ConfigDiag.java

	
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
public class ConfigDiag  extends PFrameChild 
	implements ItemListener, ActionListener, ChangeListener{
	

	FrameFractal c_current=null;

	static 	ConfigDiag cs_TheConfigDiag=null;

	JCheckBox     c_checkbox_preview;
	//	PPDoubleField c_field_facteur_zoom;

	JCheckBox c_checkbox_auto_depth;

	PPDoubleField c_pos_X, c_pos_Y, c_zoom, c_center_X, c_center_Y;
	PPIntField    c_depth;
	JPanel        c_pane_mandelbrot;
	JPanel        c_pane_view;

	boolean       c_preview = true;
	boolean       c_auto_depth = true;

	JTabbedPane c_tabpane;

	//	double c_facteur_zoom = 10;
	//--------------------------
	JPanel initPanelView(){

		//===============
		JPanel l_pane = new JPanel();
		l_pane.setLayout( new GridLayout( 0, 1 ));
		
		l_pane.setBorder( BorderFactory.createEmptyBorder( 10, 10, 10, 10 ));
		c_checkbox_preview  = new JCheckBox( "Preview",  c_preview);
		l_pane.add( c_checkbox_preview);

		//		c_field_facteur_zoom = new PPDoubleField( "facteur de zoom ", c_facteur_zoom, PPDoubleField.VERTICAL );
		//		l_pane.add( c_field_facteur_zoom );


		c_checkbox_auto_depth = new JCheckBox( "Auto depth",  c_auto_depth);
		l_pane.add( c_checkbox_auto_depth);

		return l_pane;
	}
	//--------------------------
	JPanel initPanelFractal(){

		//===============
		JPanel l_pane = new JPanel();
		l_pane.setLayout( new GridLayout( 0, 1 ));

		
		l_pane.setBorder( BorderFactory.createEmptyBorder( 10, 10, 10, 10 ));
		c_center_X = new PPDoubleField( "center X", 0.0, PPField.VERTICAL );
		l_pane.add( c_center_X );
		c_center_Y = new PPDoubleField( "center Y", 0.0, PPField.VERTICAL );
		l_pane.add( c_center_Y );

		c_zoom = new PPDoubleField( "Zoom Y", 0.0, PPField.VERTICAL );
		l_pane.add( c_zoom );
		
		c_depth = new PPIntField( "Compute depth", 32, PPField.HORIZONTAL );
		l_pane.add( c_depth );


		//===============

		return l_pane;
	}
	//-----------------------
	ConfigDiag(){
		super("Informations");
		ImageIcon l_turtleimg= new ImageIcon( "images/turtle.gif" );

		getContentPane().setLayout( new BorderLayout() );

		//-----------------------
		JPanel l_panel_north = new JPanel();
		l_panel_north.setLayout( new GridLayout( 0, 1 ));

		c_pos_X = new PPDoubleField( "pos X", 0.0, PPDoubleField.VERTICAL );
		c_pos_X.setEditable( false );
		l_panel_north.add( c_pos_X );
		c_pos_Y = new PPDoubleField( "pos Y", 0.0, PPDoubleField.VERTICAL );
		c_pos_Y.setEditable( false );
		l_panel_north.add( c_pos_Y );
		getContentPane().add( l_panel_north, BorderLayout.NORTH );
		//-----------------------


		c_tabpane = new JTabbedPane();
		getContentPane().add( c_tabpane, BorderLayout.CENTER );

		//===============
		c_pane_view = initPanelView();		
		c_tabpane.addTab( "View", l_turtleimg, c_pane_view,
											"Preferences view" );
		//===============

		c_pane_mandelbrot = initPanelFractal();		
		c_tabpane.addTab( "Compute", l_turtleimg, c_pane_mandelbrot,
											"Preferences Fractal" );

		//===============
		JPanel l_panel_south = new JPanel();
		l_panel_south.setLayout( new GridLayout( 1, 0 ));
		l_panel_south.setBorder( BorderFactory.createEmptyBorder( 10, 10, 10, 10 ));

		JButton  c_button_apply = new JButton( "Apply" );
		c_button_apply.setActionCommand( "Apply");
		c_button_apply.addActionListener( this );

		JButton  c_button_cancel = new JButton( "Cancel" );
		c_button_cancel.setActionCommand( "Cancel");
		c_button_cancel.addActionListener( this );
		l_panel_south.add( c_button_apply );
		l_panel_south.add( c_button_cancel);
		//===============

		getContentPane().add( l_panel_south, BorderLayout.SOUTH );
		
		
		//		final JColorChooser tcc = new JColorChooser( Color.black );
		//		getContentPane().add( tcc, BorderLayout.EAST );

		pack();
		cs_TheConfigDiag = this;
	}
	//---------------------
	public void itemStateChanged(ItemEvent p_e ){
		Object l_source = p_e.getItemSelectable();
		
	}
	//---------------------
	public void actionPerformed( ActionEvent p_e ){

		if( p_e.getActionCommand().equals("Apply"))
			{
				if( c_current == null )  return;
				applyPref();
			}

		if( p_e.getActionCommand().equals("Cancel"))
			{
				if( c_current == null )  return; 
			}
	}
	//---------------------
	public void stateChanged( ChangeEvent p_e ){
	}
	//---------------------
	public void setPos( double p_x, double p_y ){
		c_pos_X.setDouble(p_x);
		c_pos_Y.setDouble(p_y);
	}
	//---------------------
	public void setConfig( FractalConfig p_conf ){
		c_center_X.setDouble( p_conf.c_x );
		c_center_Y.setDouble( p_conf.c_y );
		c_zoom.setDouble( p_conf.c_zoom  );
		c_depth.setInt( p_conf.c_depth );
	}
	//---------------------
	void applyPref(){		
		Object l_obj[]= c_checkbox_preview.getSelectedObjects();
		if( l_obj == null )
			c_preview = false;
		else
			c_preview = true;


		Object l_obj2[]= c_checkbox_auto_depth.getSelectedObjects();
		if( l_obj2 == null )
			c_auto_depth = false;
		else
			c_auto_depth = true;


		//		c_facteur_zoom = c_field_facteur_zoom.getDouble();

		if( c_current != null )
			{				
				try{
					c_current.c_conf.c_x     = c_center_X.getDouble();
					c_current.c_conf.c_y     = c_center_Y.getDouble();
					c_current.c_conf.c_zoom  = c_zoom.getDouble();
					c_current.c_conf.c_depth = c_depth.getInt();
				}catch( NumberFormatException ie){
				}
			}
		if( c_current != null)
			c_current.calcul(false);
	}
}

//***********************************
