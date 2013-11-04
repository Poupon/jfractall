/*
JFractAll/CanvasPalette.java

	
Copyright (C) 2001 Philippe Yves Poupon
	
This program is distributed under the terms of the GNU General Public Licence



This file is part of Fractal.

Fractal is free software; you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation; either version 2, or (at your option)
any later version.
 
Fractal is distributed in the hope that it will be useful, but
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

import java.awt.event.*;
import java.awt.*;
import java.awt.image.*;

import javax.swing.*;
import javax.swing.Timer;


import java.awt.GraphicsEnvironment;
import java.awt.GraphicsDevice;
import java.awt.GraphicsConfiguration;

import phipo.PhiLib.Interface.*;

//***********************************
final public class CanvasPalette extends PPCanvas 
	implements MouseMotionListener, MouseListener, ActionListener{

	TColor c_tc;

	JScrollPane    c_scrollpane;
	void setScrollPane(JScrollPane p_scrollpane){ c_scrollpane = p_scrollpane; }

	int c_debut=0;
	int c_fin=255;

	JButton c_button_deb, c_button_fin;

	//--------------------------
	public CanvasPalette( TColor p_tc, JButton l_button_deb, JButton l_button_fin) { 
		
		super();
		
		c_tc = p_tc;

		c_button_deb = l_button_deb;
		c_button_fin = l_button_fin;
		c_debut  = 0;
		c_fin    = c_tc.c_max_color-1;

		setMinimumSize( new Dimension( 130, 130 ));
		setSize( 130, 130);

 		addMouseListener( this );		
 		addMouseMotionListener( this );		
	}
	//-------------------------- 
	void setDebut( Color p_color ){
		c_tc.T[c_debut] = p_color;
		repaintAll();
	}
	//-------------------------- 
	void setFin( Color p_color ){
		c_tc.T[c_fin] = p_color;
		repaintAll();
	}


	 int c_nb_lig = 16;
	final int c_nb_col = 16;
	final int c_sz_pas =8;
	final int c_sz_inf =6;
	//-------------------------- 
	public void paint( Graphics g ){
		super.paintComponent(g);
		
		Dimension p_size =  getSize();
		
		c_nb_lig = c_tc.c_max_sz/c_nb_col;
		for( int y=0; y< c_nb_lig; y++)
			{
				for( int x=0; x< c_nb_col; x++)
					{						
					if( c_debut == y*c_nb_col+x ){
						g.setColor( Color.red );
						g.fillRect( x*c_sz_pas-1, y*c_sz_pas-1, c_sz_pas, c_sz_pas );				
						g.drawString( "1", x*c_sz_pas-1, y*c_sz_pas-1);
					}
					if( c_fin ==  y*c_nb_col+x ){
						g.setColor( Color.green );
						g.fillRect( x*c_sz_pas-1, y*c_sz_pas-1, c_sz_pas, c_sz_pas );				
						g.drawString( "2", x*c_sz_pas-1, y*c_sz_pas-1);
					}
					g.setColor( c_tc.T[y*c_nb_col+x] );
					g.fillRect( x*c_sz_pas, y*c_sz_pas, c_sz_inf, c_sz_inf  );
				}
			}
	}
	//-------------------------- 
	public	void update( Graphics g ){
		System.out.println( "Update" );
	}
	//-------------------------- 
	public void actionPerformed( ActionEvent p_ev ){		
		System.out.println( "repaint" );
	}
	//-------------------------- 
	public void mousePressed( MouseEvent p_e ) {
		System.out.println( "mouse pressed" );		
	} 
	//-------------------------- 
	public void mouseReleased( MouseEvent p_e ) {
		
	}
	
	public void mouseEntered( MouseEvent p_e ) {
		//		System.out.println( "mouse entered" );
	}

	public void mouseExited( MouseEvent p_e ) {
		//		System.out.println( "mouse exited" );
	}
	
	// -----------------------------
	public void mouseClicked( MouseEvent p_e ) {
		
		int x = p_e.getX() / c_sz_pas;
		int y = p_e.getY() / c_sz_pas;

		int pos = y*c_nb_col+x;
		if( pos <0 || pos > c_nb_col* c_nb_lig-1 )
			return;

		if( SwingUtilities.isLeftMouseButton( p_e ) == true )
			{
				c_debut = pos;
				c_button_deb.setBackground( c_tc.T[c_debut] );
			}

		if( SwingUtilities.isRightMouseButton( p_e ) == true )
			{
				c_fin = pos;
				c_button_fin.setBackground( c_tc.T[c_fin] );
			}
					
		System.out.println( "mouse pressed" );		
		repaintAll();
	}
	//-------------------------------------
	void repaintAll(){
		repaint( new Rectangle( 0, 0, 20000, 20000 ));
	}
	//-------------------------------------
	public void mouseDragged( MouseEvent p_e ){
		
	}

	//-------------------------------------
	public void mouseMoved( MouseEvent p_e ){
		
	}
	//-------------------------------------
	void degrade(){
		Color A = c_tc.T[c_debut];
		Color B = c_tc.T[c_fin];
		c_tc.degrade( c_debut, c_fin, 
									A.getRed(), A.getGreen(), A.getBlue(),
									B.getRed(), B.getGreen(), B.getBlue() );
		repaintAll();


		c_button_deb.setBackground( c_tc.T[c_debut] );
		c_button_fin.setBackground( c_tc.T[c_fin] );				
	}
}

//***********************************

