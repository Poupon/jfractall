/*
JFractAll/FractalConfig.java

	
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


//***********************************
final public class FractalConfig  implements java.io.Serializable{

	double c_x  = 0;
	double c_y  = 0;

	double c_zoom = 100;
	int c_depth = 64;  /* nombre de boucle */


	String getString() {
		return new String( "x:" +c_x + " y:" +c_y + " zoom:" + c_zoom + " depth:" +c_depth );
	}
	FractalConfig(){
	}
	FractalConfig( FractalConfig p_conf ){
		c_x = p_conf.c_x;
		c_y = p_conf.c_y;
		c_zoom = p_conf.c_zoom;

		c_depth = p_conf.c_depth;
	}
}
//***********************************
