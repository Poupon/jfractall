/*
JFractAll/FrameFractal.java

	
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

import javax.swing.JInternalFrame;
import java.awt.event.*;
import java.awt.*;
import javax.swing.*;
import javax.swing.event.*;


import phipo.PhiLib.Interface.*;

//***********************************
public class FrameFractal extends PFrameChild 
	implements InternalFrameListener{

	CanvasFractal c_canvas;
	JPanel    c_sud_panel;
	JLabel    c_status;



	FractalConfig c_conf;
	TColor        c_tcol;

	FractalProcess c_mproc;

	public  void setProc( FractalProcess p_proc){
		if( c_mproc != null )
			{
				synchronized ( c_mproc ){
					c_mproc = p_proc;
				}			 
			}
		else c_mproc = p_proc;

	}
	public void stopProc(){
		if( c_mproc != null )
			{
				synchronized ( c_mproc ){
					if( c_mproc != null )
						{
							c_mproc.c_stop = true;
						}
				}
			}
	}

	final int MAX_XY=500;
	Graphics  c_cG;

static int c_Compteur=0;


	//-------------------------------------

public FrameFractal( FractalConfig p_conf, 
													TColor p_tcol ){

		super( "Fractal " + c_Compteur++ );

		c_conf = p_conf;
		c_tcol = p_tcol;


		getContentPane().setLayout( new BorderLayout() );
		c_canvas = new CanvasFractal( this );
		getContentPane().add( c_canvas, BorderLayout.CENTER );

		c_sud_panel = new JPanel();		
		c_sud_panel.setLayout( new GridLayout( 1, 0 )); 
		c_status  = new JLabel( "mandelbrot" );
		c_sud_panel.add( c_status );

		getContentPane().add( c_sud_panel, BorderLayout.SOUTH );

		setInfo("");

		calcul(false);
}
	
	//-------------------------------------
	void setInfo( String p_str ){
		c_status.setText( c_conf.getString() + "  " +p_str);
	}
	//-------------------------------------
	void calcul(boolean p_sleep){

		stopProc();
		setProc(  new FractalProcess( (c_cG = c_canvas.c_graphics), 
																			 this, 
																			 c_tcol, 
																			 c_conf, 
																			 (c_canvas.c_size.getWidth()),
																			 (c_canvas.c_size.getHeight()), p_sleep));		
		c_mproc.start();		
	}
	
	//-------------------------------------
	void actualize(){
		c_canvas.repaint( new Rectangle( 0, 0, 20000, 20000 ));
	}																								

	//-------------------------------------------------------------
	public void internalFrameActivated( InternalFrameEvent e ){
		System.out.println( "windowActivated ");

		ConfigDiag.cs_TheConfigDiag.c_current	= this;
		ConfigDiag.cs_TheConfigDiag.setConfig( c_conf );		
	}
	//-------------------------------------
	public void internalFrameClosed( InternalFrameEvent e ){
		System.out.println( "windowClosed ");
	}
	//-------------------------------------
	public void internalFrameClosing( InternalFrameEvent e ){
		System.out.println( "windowClosing ");
	}
	//-------------------------------------
	public void internalFrameDeactivated( InternalFrameEvent e ){
		System.out.println( "windowDeactivated ");
	}
	//-------------------------------------
	public void internalFrameDeiconified( InternalFrameEvent e ){
		System.out.println( "windowDeiconified ");
	}
	//-------------------------------------
	public void internalFrameIconified( InternalFrameEvent e ){
		System.out.println( "windowIconified ");
	}
	//-------------------------------------
	public void internalFrameOpened( InternalFrameEvent e ){
		System.out.println( "windowOpened ");
	}
}
//**********************************
