/*
phipo/PhiLib/Util/PPToken.java

	
Copyright (C) 2001 Philippe Yves Poupon
	
This program is distributed under the terms of the GNU General Public Licence



This file is part of phipo/PhiLib/Util.

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

package phipo.PhiLib.Util;

import java.io.*;
import java.util.*;
import java.lang.*;




//***********************************
public class PPToken {

	String c_forend=" \n\t";
	String c_toeat=" \t\n";
	char   c_escape;
	
	char   c_back='\0';
	char   c_sep;
	Reader c_reader;
	int    c_numline;
	boolean c_eatsep=true;

	//-----------------------------
	public char getSep()     {	return c_sep; }
	public int  numLine() { return c_numline; }
	//-----------------------------
	public PPToken( Reader l_reader){
		c_reader = l_reader;
	}
	//-----------------------------
public	PPToken( Reader l_reader, String p_toeat, String p_forend ){
		c_reader = l_reader;
		c_forend = p_forend;
		c_toeat  = p_toeat;
	} 
	//------------
public	int read(){
		int c='\0';
		try {
			
			if( c_back != '\0' )
				{
					c = (int)c_back;
					c_back = '\0';
				}
			else
				if( c_reader.ready() && (c=c_reader.read())!=-1)
					{
						//						System.out.println("PPToken read :" +(char)c);
						return c;
					}
				else 
					return -1;
		}
		catch( IOException e )
			{
				System.out.println( "IO Error: " +e.getMessage() );
			}
		//		System.out.println("PPToken read -:" +(char)c);
		return c;
	}
	//------------
public	void putback( int c ){
		//		System.out.println( "putback:" + (char) c );
		c_back = (char)c;
	}
	//------------
public	void eat(String p_toeat) {

		//		System.out.println("PPToken eat");

		int ic;

		while( (ic=read()) != -1)
			{
				char c = (char)ic;
				if( c == '\n' )
					c_numline++;
				
				if( p_toeat.indexOf( c ) == -1 )
					{
						putback( c );
						break;
					}
			}
	}
		//------------
public	StringBuffer nextToken(){
		return nextToken( c_toeat, c_forend );
	}
	//------------
public	StringBuffer nextToken( String p_toeat, String p_forend ){		
		int rc;
		char c;
		c_sep ='\0';
		StringBuffer l_strbuf = new StringBuffer( 1000 );
		
		eat( p_toeat );

		while( (rc=read()) != -1 )
			{
				c = (char)rc;
				if( p_forend.indexOf( c )!=-1)
					{
						c_sep = c;
						if( c_eatsep == false)		putback( c );
						break;
					}

				if( c == '\n' )
					c_numline++;
				
				l_strbuf.append(  c );				
			}

		if( l_strbuf.length()  == 0 && rc == -1 )
			return null;

		//		System.out.println( "nextToken->" + l_strbuf );
		return l_strbuf;
	}
	//------------
public	StringBuffer getParagraph( String p_toeat, char p_begin, char p_end, boolean p_depsep ){
		
		eat( p_toeat );

		int l_deep=0;
		if( p_depsep )
			l_deep++;

		StringBuffer l_strbuf = new StringBuffer( 1000 );

		int rc;
		while( (rc=read()) != -1 ){
			if( p_begin == (char)(rc) )
				{
					l_deep++;
					if( l_deep == 1 ) continue;
				}
			else if( p_end == (char)(rc) )
					l_deep--;

			if( l_deep <= 0)
				break;

			l_strbuf.append( (char)rc );				
		}

		if( l_strbuf.length()  == 0)
			return null;

		//		System.out.println( "Paragraph=" + l_strbuf );
		return l_strbuf;
	}				
}
//***********************************
