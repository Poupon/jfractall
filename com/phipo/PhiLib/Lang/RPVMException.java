/*
Lang/RPVMException.java

	
Copyright (C) 2001 Philippe Yves Poupon
	
This program is distributed under the terms of the GNU General Public Licence



This file is part of Lang.

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



package phipo.PhiLib.Lang;


//**********************************************************
public class RPVMException extends RuntimeException{

	long c_line;
	
	public RPVMException( String p_str ){
		super( formatError( 0, p_str) );
	}
	
	public RPVMException( int p_line, String p_str ){
		
		super( formatError( p_line, p_str) );
		c_line = p_line;
	}
	
	public static String formatError( int p_line, String p_str ){
		if( p_line == -1 )
			return new String( "Erreur  :: " + p_str + "\n" );
		
		return new String( "Erreur a la ligne :" + p_line
													 + ": " + p_str + "\n" );
	}	
}
//**********************************************************

