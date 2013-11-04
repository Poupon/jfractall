/*
phipo/PhiLib/Lang/PPLangTokenizer.java

	
Copyright (C) 2001 Philippe Yves Poupon
	
This program is distributed under the terms of the GNU General Public Licence



This file is part of phipo/PhiLib/Lang.

RPVM is free software; you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation; either version 2, or (at your option)
any later version.
 
RPVM is distributed in the hope that it will be useful, but
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

import java.util.*;
import java.io.*;
import java.lang.*;
import java.io.Reader;
import java.io.StringReader;

import phipo.PhiLib.Lang.PPLangToken;



//*****************************************************************************
public class PPLangTokenizer{

	Reader c_reader;
	char   c_sep;
	int    c_numline=1;

	// --------------------------
	public PPLangTokenizer( Reader l_reader ){
		c_reader = l_reader;
	}
	// --------------------------
	public int numLine() { return c_numline; }
	// --------------------------
	public final	PPLangToken readToken( ) {

		eatSpace();
		int ic;
		String l_str;
		PPLangToken l_tok = new PPLangToken();
		
		if( (ic = read())== -1) return null;
		char c = (char)ic;
		
		if( Character.isJavaIdentifierStart( c ) )
			{
				putback(c);
				l_tok.c_val  =  readName();
				l_tok.c_type =  PPLangToken.OPERAND;
			}
		else
			if( Character.isDigit(c) )
				{
					putback(c);
					l_tok.c_val  = readNumber();
					l_tok.c_type = PPLangToken.NUMBER;
				}
		
		else 
			if( PPLangToken.StrDelimiteur.indexOf( c )!=-1)
				{
					//					char l_s[]={c,'\0'};
					char l_s[]={c};
					l_tok.c_val  = new String( l_s );
					l_tok.c_type = PPLangToken.DELIMITEUR;
				}
		else 
			{
				putback(c);
				l_tok.c_val  =  readOperator();
				l_tok.c_type =  PPLangToken.OPERATOR;
			}
		
		//			System.out.print( "readToken:" +l_tok.c_val);
		if( l_tok.c_val != null )			{ 
			switch( l_tok.c_type ){
			case PPLangToken.OPERAND:
				////				System.out.print( "Operand:<" );
				break;
			case PPLangToken.NUMBER:
				/////				System.out.print( "Number:<" );
				break;
			case PPLangToken.OPERATOR:
				/////				System.out.print( "Operator:<" );
				break;
			case PPLangToken.DELIMITEUR:
				/////////				System.out.print( "Delimiteur:<" );
				break;
			}
			////////////			System.out.println( l_tok.c_val + ">");
		}			
		else {
			System.out.println("no data");
		}

		return l_tok;
	}
	// --------------------------
	public final	String readOperator(){

		int rc;
		char c;
		c_sep ='\0';
		StringBuffer l_strbuf = new StringBuffer( 1000 );
		
		while( (rc=read()) != -1 )
			{
				c = (char)rc;
				if( Character.isLetterOrDigit( c ) 
						|| isSpace(c)  
						|| PPLangToken.StrDelimiteur.indexOf( c )!=-1)
					{
						c_sep = c;
						
						putback( c );
						break;
					}
				
				l_strbuf.append(  c );				
			}

		if( l_strbuf.length()  == 0 && rc == -1 )
			return null;

		return l_strbuf.toString();		
	}
	// --------------------------
	public final	String readNumber(){

		int rc;
		char c;
		c_sep ='\0';
		StringBuffer l_strbuf = new StringBuffer( 1000 );
		
		while( (rc=read()) != -1 )
			{
				c = (char)rc;
				if( Character.isDigit( c ) == false && c !='.' )
					{
						c_sep = c;
						
						putback( c );
						break;
					}
				
				l_strbuf.append(  c );				
			}

		if( l_strbuf.length()  == 0 && rc == -1 )
			return null;

		return l_strbuf.toString();		
	}
	// --------------------------
	public final	String readName(){

		int rc;
		char c;
		c_sep ='\0';
		StringBuffer l_strbuf = new StringBuffer( 1000 );
		
		while( (rc=read()) != -1 )
			{
				c = (char)rc;
				if( Character.isJavaIdentifierPart( c ) == false )
					{
						c_sep = c;
						
						putback( c );
						break;
					}
				
				l_strbuf.append(  c );				
			}

		if( l_strbuf.length()  == 0 && rc == -1 )
			return null;

		return l_strbuf.toString();		
	}
	// --------------------------
	public final boolean isSpace( char c ){
		switch( c ){
		case '\n':
		case ' ':
		case '\t':			
			return true;
		default:
		}
		return false;
	}
	//------------
	public final	void eat(String p_toeat) {

		//		System.out.println("PPToken eat");

		int ic;

		while( (ic=read()) != -1)
			{
				char c = (char)ic;				
				if( p_toeat.indexOf( c ) == -1 )
					{
						putback( c );
						break;
					}
			}
	}
	// --------------------------
	public final	 void eatSpace() {
		
		int ic;

		while( (ic=read()) != -1)
			{
				char c = (char)ic;

				if( isSpace(c) == false  )
					{
						putback( c );
						break;
					}
			}
	}
	// ------------------------------------------
	char   c_back='\0';
	// ------------------
	public final	int read(){
		int c='\0';
		try {			
			if( c_back != '\0' )
				{
					c = (int)c_back;
					c_back = '\0';
				}
			else
				if( !c_reader.ready() || (c=c_reader.read())==-1){
					//					System.out.println( ">READ end ");
					return -1;
				}
		}
		catch( IOException e )
			{
				System.out.println( "IO Error: " +e.getMessage() );
			}
		
		if( c == '\n' )
			c_numline++;
		
		return c;
	}
	//----------------------------
	public final	void putback( int c ){

		if( c == '\n' && c_back != '\n')  
			c_numline--;
		
		c_back = (char)c;
	}

	//------------
public final		StringBuffer getParagraph( String p_toeat, char p_begin, 
														 char p_end, boolean p_depsep ){
		
		eat(p_toeat );

		int l_deep=0;
		if( p_depsep )
			l_deep++;

		StringBuffer l_strbuf = new StringBuffer( 10000 );

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
	//------------

	//***********************************
	public String getParagraph(RPVMManager p_mng)
		{
			eat( " \t\n" );
			
			int l_c = read();
			int l_c2=0;
			
			switch( l_c ){
			case '<': l_c2 ='>'; break;
			case '(': l_c2 = ')'; break;
			case '[': l_c2 = ']'; break;
			case '{': l_c2 = '}'; break;
			default: ;
			}
			if( l_c2 == 0){
				p_mng.processError( "CmdWrite Mismatched " + l_c );
				return null;
			}
			
			return getParagraph( "", (char)l_c, (char)l_c2, true ).toString();		
		}
}
//*****************************************************************************
