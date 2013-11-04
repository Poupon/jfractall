/*
phipo/PhiLib/Lang/PPIniFile.java

	
Copyright (C) 2001 Philippe Yves Poupon
	
This program is distributed under the terms of the GNU General Public Licence



This file is part of phipo/PhiLib/Lang.

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

import java.util.StringTokenizer;

import java.util.*;
import java.io.*;

import java.awt.Rectangle;

// *********************************************
public  class PPIniFile{
		
		TreeMap  cMap = new TreeMap();
		static final String SEP = new String( "#$@" );
		
		// ------------------------------
		public	PPIniFile(){			
				File lFile= null;
		}
		// ------------------------------
		public	PPIniFile( String pFilename ){			
				File lFile= new File( pFilename );
				if( lFile != null )
						exec(lFile);
		}
		// ------------------------------
		public	PPIniFile( File pFile ){
				exec( pFile );
		}
		// ------------------------------
		public boolean exec( String pFileName ){		
				File lFile= new File( pFileName );
				if( lFile != null )
						return exec(lFile);	
				else
						return false;
		}
		// ------------------------------
		boolean  exec(  File pFile ){		
			try {
			FileReader lFread = new FileReader( pFile );
			
			String lSbuf;
			BufferedReader lBufread = new BufferedReader(lFread);
			
			String lCurrentSection=null;
			String lCurrentKey=null;
			String lCurrentValeur=null;
			
			int lNumline = 0;
			
			while( (lSbuf=lBufread.readLine()) != null) {
				try{
					lNumline++;
					//					System.out.println( lNumline + ">>>" + lSbuf );
					if(  lSbuf.length() == 0 || lSbuf.charAt(0) == '#'  || lSbuf.charAt(0) == '\n'
							|| lSbuf.trim().length() == 0  )
						continue;
					
					
					if( lSbuf.charAt(0) == '[' ){					
						StringTokenizer lTok = new StringTokenizer(lSbuf.substring(1));
						lCurrentSection = lTok.nextToken("]").trim();
						
						//						System.out.println( "SECTION=" + lCurrentSection );
					}
					else {					
						StringTokenizer lTok = new StringTokenizer( lSbuf );
						lCurrentKey         = lTok.nextToken("=").trim();
						lCurrentValeur      = lTok.nextToken("\n").trim();
						lCurrentValeur  = lCurrentValeur.substring( 1 ).trim();
						//						System.out.println( "VAL " + lCurrentKey + "=" + lCurrentValeur );
						if( lCurrentSection == null ){
							System.out.println( "PPIniFile Error in line:" + lNumline 
																	+ " : No section define for :" + lCurrentKey );
						}
						else{
							String lStr = new String( lCurrentSection + SEP +lCurrentKey );
							cMap.remove( lStr );
							cMap.put( lStr, lCurrentValeur );
							//							System.out.println( "->" + lStr  + "=" + lCurrentValeur );
						}			 
					
					}
				}
				catch( Exception e){
					System.out.println("catch " + e + " in PPIniFile.exec <"+lSbuf+">" );
				}
			}
			lFread.close();						
		}
		catch( Exception e){
				System.out.println("catch " + e + " in PPIniFile.exec read file " );
			return false;
		}
			return true;
	}
	// ------------------------------
	public String  get( String pSection, String pKey ) { 
			//			System.out.println( pSection + ":" + pKey + "=" +  cMap.get( pSection + SEP + pKey ));
		return (String) cMap.get( pSection + SEP + pKey ); 
	}
	// ------------------------------
	public boolean  test( String pSection, String pKey, String pVal) { 

			String  lStr = get( pSection, pKey );
			if( lStr != null && lStr.equals( pVal ) )
					return true;

		return false; 
	}
	// ------------------------------
	public String  get( String pSection, String pKey, String pDefault ) { 

		String lVal = (String) cMap.get( pSection + SEP + pKey ); 
		if( lVal == null )
				return pDefault;

		return lVal;
	}
	// ------------------------------
	public Integer  getInteger( String pSection, String pKey ) { 
			String lStr = get( pSection, pKey );
			if( lStr != null ) 
					return Integer.decode(lStr ); 

			return null;
	}
	// ------------------------------
	public int  getInt( String pSection, String pKey, int pDefaultValue ) { 
			String lStr = get( pSection, pKey );
			if( lStr != null ) 
					return Integer.parseInt(lStr ); 
			return pDefaultValue;
	}
	// -----------------------------
		public static Rectangle GetRectangle(  String pData, String pSep){
				if( pData == null ){
						return null;
				}

				StringTokenizer lTok = new StringTokenizer( pData, pSep );
				
				String lX = lTok.nextToken().trim();
				String lY = lTok.nextToken().trim();
				String lW = lTok.nextToken().trim();
				String lH = lTok.nextToken().trim();
				
				if( lX!= null && lY!= null && lW!=null && lH!=null 
						&& Integer.decode(lX)!= null
						&& Integer.decode(lY)!= null
						&& Integer.decode(lW)!= null
						&& Integer.decode(lH)!= null){						
						return new Rectangle ( Integer.decode(lX).intValue(),
																	 Integer.decode(lY).intValue(),
																	 Integer.decode(lW).intValue(),
																	 Integer.decode(lH).intValue() );
				}
				return null;				
		}
	// -----------------------------
	public void set(  String pSection, String pKey, String pVal ){

		String lStr = new String( pSection + SEP + pKey );
		cMap.remove( lStr );
		cMap.put( lStr, pVal );
		//		System.out.println( "->" + lStr  + "=" + pVal );
	}
	// -----------------------------
	public void remove(  String pSection, String pKey){

		String lStr = new String( pSection + SEP + pKey );
		cMap.remove( lStr );
	}		
	// ------------------------------
	public void writeFile( String pFilename ){
		File lFile= new File( pFilename );
		if( lFile != null )
			writeFile( lFile);
	}
	// ------------------------------
	public void writeFile( File pFile  ){

		try {
			PrintStream lFout = new PrintStream( new FileOutputStream( pFile ) );
			write( lFout );
		}
		catch(Exception e ) { System.out.println( e );}
	}
	// ------------------------------
	public void write( PrintStream pOut ){

		try {			
			Set      lSet   = cMap.keySet();
			Iterator lIter  = lSet.iterator();
			
			String   lCurrentSection="";
			
			while( lIter.hasNext() ){
				String lStr = (String) lIter.next();
				int lPos = lStr.indexOf( SEP );
				
				String lSection = lStr.substring( 0, lPos );
				String lVar     = lStr.substring( lPos + 3 );
				String lVal     = (String)cMap.get(lStr);
				
				if( lSection != null && lVar != null && lVal != null ){
					
					// Nouvelle section 
					if( lSection.compareTo( lCurrentSection ) != 0 ){
						pOut.println( "\n["+ lSection + "]" );
						lCurrentSection = lSection;
					}
					
					pOut.println( lVar + "=" + lVal );				
				}
			}		
		}
		catch(Exception e ) { System.out.println( e );}
	}
	// ------------------------------
	public	void debug(){
		System.out.println( "===================== BEGIN INI ==============================");
		write( System.out );
		System.out.println( "===================== END   INI ==============================");
	}
	// ------------------------------
};
// *********************************************
