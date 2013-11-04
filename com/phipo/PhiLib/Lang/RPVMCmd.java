/*
Lang/RPVMCmd.java

	
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
public class RPVMCmd{


	String c_help;

	String c_str;

	//------------------
	public RPVMCmd( String p_str){
		c_str = p_str;
	}

	String getName() { return c_str; }
	String print() { return c_str; }
	String help() { 
		return new String( c_str + "\t" + c_help  );
			}

public	boolean exec( RPVM p_lang, Object  p_userdata,
						 RPVMManager p_mng, PPLangTokenizer p_tokenizer ){

		System.out.println( "["  +c_str + "  default exec]" );

		if( p_mng.getTraceFlag() ) trace( p_mng );

		return true;
	}
	String stringDebug() {
		return new String( c_str );
	}

	public void trace( RPVMManager p_mng ){
		p_mng.processTrace( c_str +"\n" );
	}
}
//**********************************************************

