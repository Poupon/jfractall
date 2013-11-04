/*
JFractAll/TColor.java

	
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


import java.awt.Color;


//***********************************
final public class TColor implements java.io.Serializable{

	String c_name;
	Color T[]=null;
	int c_max_sz=0;
	int c_max_color=256;

public	TColor( String p_name, int p_sz){
		System.out.println( "=====> TColor");
		c_name = p_name;
		c_max_sz = p_sz;
		Color T[]= new Color[c_max_sz];
	}
	//----------------------------
public	void resize( int p_sz ){

		if( p_sz == c_max_sz )
			return;

		
		Color l_t[]= new Color[p_sz];

		
		if( p_sz > c_max_sz )
			for( int i=0; i< c_max_sz; i++)
				l_t[i]=T[i];
		else
			for( int i=0; i< p_sz; i++)
				l_t[i]=T[i];
		
		for( int i= c_max_sz; i < p_sz; i++)
			l_t[i] =Color.black;

		c_max_sz = p_sz;
		T = l_t;
	}
	//----------------------------
public	void set( int pos, Color p_col ){
		T[pos] = p_col;
	}

	//----------------------------
public	void set( int pos, int r, int g, int b ){
		set( pos, new Color( r, g, b ) );
	}

	//----------------------------
public		void gray( int n){

			T = new Color[n];
			degrade( 0, 255, 0, 0, 0, 255, 255, 255);
			//			for( int i=0; i< n; i++){
			//				int p = (c_max_sz-1)-((c_max_sz-1)*i)/n;
			//				T[i] = new Color( p, p, p );
			//			}
			c_max_color = n;
		}

	//----------------------------
public	void degrade( int nb, int r0, int g0, int b0, 
								int r1, int g1, int b1 ){

		double dr = ((double)(r1-r0))/nb;
		double dg = ((double)(g1-g0))/nb;
		double db = ((double)(b1-b0))/nb;

		//		System.out.println( "dr:" + dr + " dg:"+dg +" db:" +db );
		for( int i=0; i< nb; i++){
			//			System.out.println(  "r:" + (int)(r0+dr*i) + " g:" +  (int)(g0+dg*i) + " b:" + (int)(b0+db*i));
			T[i] = new Color( (int)(r0+dr*i), (int)(g0+dg*i), (int)(b0+db*i) );
		}
	}	
//----------------------------
public	void degrade( int deb, int fin, int r0, int g0, int b0, 
								int r1, int g1, int b1 ){

		/*		System.out.println( deb + "->" + fin 
												+ " r:" + r0 + "->" + r1
												+ " g:" + g0 + "->" + g1
												+ " b:" + b0 + "->" + b1 );
 */

		if( deb < fin )
			{
				int nb = fin -deb;

				double dr = ((double)(r1-r0))/nb;
				double dg = ((double)(g1-g0))/nb;
				double db = ((double)(b1-b0))/nb;
				//		System.out.println( "nb:" + nb + " " + deb + "->" + fin  
				//												+ "dr:" + dr + " dg:" + dg + " db:" + db );

		for( int i = 0; i <= nb; i++)
			{
				int r = (int)(r0+dr*i);
				int g = (int)(g0+dg*i);
				int b = (int)(b0+db*i);
				//				System.out.println( i + " r:" + r + " g:" +  g + " b:" + b);
				
				if( r > 255 ) r = 255;
				if( r< 0 ) r= 0;
				if( g > 255  ) g = 255;
				if( g< 0 ) g = 0;
				if( b > 255 ) b = 255;
				if( b < 0 ) b = 0;
				T[deb+i] = new Color( r, g, b );
			}
			}
		else
			{
				int nb =  deb-fin;
				double dr = ((double)(r1-r0))/nb;
				double dg = ((double)(g1-g0))/nb;
				double db = ((double)(b1-b0))/nb;
				//				System.out.println( "nb:" + nb + " " + deb + "->" + fin  
				//														+ "dr:" + dr + " dg:" + dg + " db:" + db );
				
				
				for( int i = 0; i <nb; i++)
					{
						int r = (int)(r0+dr*i);
						int g = (int)(g0+dg*i);
						int b = (int)(b0+db*i);
						//						System.out.println( i + " r:" + r + " g:" +  g + " b:" + b);
						
						if( r > 255 ) r = 255;
						if( r< 0 ) r= 0;
						if( g > 255  ) g = 255;
						if( g< 0 ) g = 0;
						if( b > 255 ) b = 255;
						if( b < 0 ) b = 0;
						T[deb-i] = new Color( r, g, b );
					}
			}
	}
}
//***********************************
