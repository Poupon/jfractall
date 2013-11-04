/*
JFractAll/FractalProcess.java

	
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


import java.awt.Graphics;


//***********************************
final public class FractalProcess  extends Thread{

	FrameFractal c_frame;
	TColor          c_col;
	FractalConfig c_conf;
	Graphics           c_G;
	double                c_ncol;
	double                c_nlig;
	boolean            c_sleep;

	 public volatile boolean  c_stop=false;


	//----------------------------
public	FractalProcess( Graphics  p_g,
											 FrameFractal p_frame, 
											 TColor p_col,
											 FractalConfig p_conf,
											 double p_ncol, double p_nlig, boolean b_sleep ){
		c_frame  = p_frame;
		c_col  = p_col;
		c_conf = p_conf;
		c_G    = p_g;

		c_ncol = p_ncol;
		c_nlig = p_nlig;
		c_sleep = b_sleep;
	}
	//----------------------------
	public void run(){

		if( c_sleep )
			{
				try{
					Thread.sleep( 80 );
				}
				catch(Exception e){
				}			
			}

		if( c_stop == true)
			return;

		try{
			System.out.println(">>run");
			c_frame.setInfo("Computing ..." );		
			
			calcul();
		}
		catch(Exception e)
			{
				System.out.println( e );
			}

		if( c_stop == false )
			c_frame.setInfo("Finish." );

		c_frame.actualize();
	}
	//----------------------------
	void calcul( ){
		System.out.println( "calcul" );


		double pas   = 1/c_conf.c_zoom;
		
		double width  = c_ncol / c_conf.c_zoom;
		double height = c_nlig / c_conf.c_zoom;

		double xmin  = c_conf.c_x - width  / 2;
		double ymin  = c_conf.c_y - height / 2;

		double xmax  = c_conf.c_x + width  / 2;
		double ymax  = c_conf.c_y + height / 2;


		System.out.println(   "xmin:"  + xmin
												+ " xmax:" + xmax
												+ " ymin:" + ymin
												+ " ymax:" + ymax );

		double depth = c_conf.c_depth;
		
		double x,y;
		double a,b,tmp;
		int i;
		double x2, y2;

		int memcolor = 0;

		double diviseur_color = (c_col.c_max_sz-1)/depth;

		
		int k=0;
		if( ConfigDiag.cs_TheConfigDiag.c_preview == true )
			k=2;
		
		for( int l_inc=1 ; k>=0; k-- )
			{
				switch(k){
				case 2: l_inc = 16; break;
				case 1: l_inc = 4; break;
				case 0: l_inc = 1; break;
				}
				for( int lig=0;lig<c_nlig && c_stop == false; lig += l_inc)
					{
						for( int  col=0;col<c_ncol && c_stop == false; col += l_inc) {
							b=ymin+(double)lig*pas;
							a=xmin+(double)col*pas;
							
							x=0;
							y=0;
							i=0;
							
							while (i<depth) {
								tmp=x;
								x2 = x*x;
								y2 = y*y;
								x=x2-y2+a;
								y=2*tmp*y+b;
								if (x2+y2>4) break;
								i++;
							}
							try{
								
							c_G.setColor( c_col.T[(int)(i*diviseur_color) ] );
							}
							catch( Exception e)
								{
									System.out.println( "i=" + i + " diviseur_color="+diviseur_color 
																			+ " c_col.c_max_sz=" +c_col.c_max_sz
																			+ " depth=" + depth );
									System.out.println( e );
								}
							if( l_inc == 1 )
								c_G.drawLine( col, lig, col, lig);        
							else
								c_G.fillRect( col, lig, l_inc, l_inc );        
						}						
						if( lig % 10 == 0){
							if( c_stop == false )c_frame.actualize();
						}
					}		
			}		
		if( c_stop == false )c_frame.actualize();
	}	
}
//***********************************
