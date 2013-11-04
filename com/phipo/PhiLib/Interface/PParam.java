/*
phipo/PhiLib/Interface/PParam.java

	
Copyright (C) 2001 Philippe Yves Poupon
	
This program is distributed under the terms of the GNU General Public Licence



This file is part of phipo/PhiLib/Interface.

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

package phipo.PhiLib.Interface;
import  phipo.PhiLib.Interface.*;


import java.lang.*;



//***********************************

public class PParam{


	PParam() {;}

	//-------------------------------
public 	static String GetString( String[] args, String p_prefix){

		int l = p_prefix.length();

		for( int i=0; i<  args.length; i++){
			
			String arg = args[i];

			if( arg.startsWith( p_prefix ))
				{
					return arg.substring( l );
				}
		}
		return null;
	}
	//-------------------------------
public 	static Integer GetInt( String[] args, String p_prefix){

		int l = p_prefix.length();

		for( int i=0; i<  args.length; i++){
			
			String arg = args[i];
			
			if( arg.startsWith( p_prefix ))
				{
					try{
						return new Integer( arg.substring(l));
					}catch(NumberFormatException ex){
						System.out.println( "Mauvais format pour commande "+p_prefix);
						return null;
					}					
				}
		}
		return null;
	}

};
//***********************************
