/*
JFractAll/FractalMng.java

	
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


import javax.swing.JFrame;
import java.awt.image.*;
import java.awt.Graphics;

import phipo.PhiLib.Interface.*;

//***********************************
final public class FractalMng{

	CanvasFractal      c_canvas;
	BufferedImage c_bufferimg;
	Graphics      c_bG;
	Graphics      c_cG; 

	TColor        c_tcol;

	FractalConfig  c_conf;
	FractalProcess c_mproc;
	final int MAX_XY=500;
	//-------------------------------------
	public FractalMng( CanvasFractal p_canvas ){
		c_canvas = p_canvas;		

		c_tcol = new TColor( "Gris", 256 );
		c_tcol.gray(256);
		c_conf = new FractalConfig();
		c_cG   = c_canvas.getGraphics();

	}
	//-------------------------------------
	public void repaint( Graphics p_G, PPCanvas p_canvas ){
		
	}
	//-------------------------------------

	void calcul(boolean p_sleep){
		
		c_mproc = new FractalProcess( c_canvas.getGraphics(), c_canvas.getFrame(), c_tcol, c_conf,
																			 c_canvas.c_size.getWidth(), c_canvas.c_size.getHeight(), p_sleep );
		c_mproc.start();		
	}
		
};
//***********************************
