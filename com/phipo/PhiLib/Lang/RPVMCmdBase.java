/*
Lang/RPVMCmdBase.java

	
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

import java.io.*;
import java.util.*;

import java.lang.Integer;
import java.lang.*;


//**********************************************************
public class RPVMCmdBase extends RPVMCmd{

public	static final int PLUS    =1;
public	static final int MOINS   =2;
public	static final int MUL     =3;
public	static final int DIV     =4;

public  static final int INF=1;
public  static final int SUP=2;
public  static final int INF_EG=3;
public  static final int SUP_EG=4;
public  static final int EGAL=5;
public  static final int DIFF=6;
public  static final int NOT=7; 
public  static final int ET=8;
public  static final int OU=9;

	public RPVMCmdBase( String p_str){
		super( p_str );
	}

	public static void Init( RPVM p_rpvm ){
		p_rpvm.addCmd( new RPVMCmdRepeat(  "Repete" ));
		p_rpvm.addCmd( new RPVMCmdRepeat(  "Repeter" ));
		p_rpvm.addCmd( new RPVMCmdRepeat(  "Repeat" ));
		p_rpvm.addCmd( new RPVMCmdRepeat(  "REP" ));		
	

		p_rpvm.addCmd( new RPVMCmdDeclareFonction("Fonction"));
		p_rpvm.addCmd( new RPVMCmdDeclareFonction("POUR"));
		p_rpvm.addCmd( new RPVMCmdDeclareFonction("FTN"));


		p_rpvm.addCmd( new RPVMCmdHelp("Aide") );
		p_rpvm.addCmd( new RPVMCmdHelp("Help") );
		p_rpvm.addCmd( new RPVMCmdTracePile("Pile"));

		p_rpvm.addCmd( new RPVMCmdOperation("+", PLUS));
		p_rpvm.addCmd( new RPVMCmdOperation("-", MOINS));
		p_rpvm.addCmd( new RPVMCmdOperation("*", MUL));
		p_rpvm.addCmd( new RPVMCmdOperation("/", DIV));


		p_rpvm.addCmd( new RPVMCmdCmp( "<",  INF  , 2 ));
		p_rpvm.addCmd( new RPVMCmdCmp( ">",  SUP  , 2));
		p_rpvm.addCmd( new RPVMCmdCmp( "<=", INF_EG , 2 ));
		p_rpvm.addCmd( new RPVMCmdCmp( ">=", SUP_EG , 2 ));
		p_rpvm.addCmd( new RPVMCmdCmp( "==", EGAL  , 2 ));

		p_rpvm.addCmd( new RPVMCmdCmp( "Not",  NOT , 1 ));
		p_rpvm.addCmd( new RPVMCmdCmp( "Non",  NOT , 1 ));

		p_rpvm.addCmd( new RPVMCmdCmp( "!=",  DIFF , 2 ));
		p_rpvm.addCmd( new RPVMCmdCmp( "<>",  DIFF , 2 ));
		
		p_rpvm.addCmd( new RPVMCmdCmp( "Et",  ET  , 2));
		p_rpvm.addCmd( new RPVMCmdCmp( "And", ET  , 2));
		p_rpvm.addCmd( new RPVMCmdCmp( "&&",  ET  , 2));
		p_rpvm.addCmd( new RPVMCmdCmp( "Ou",  OU  , 2));
		p_rpvm.addCmd( new RPVMCmdCmp( "Or",  OU  , 2));
		p_rpvm.addCmd( new RPVMCmdCmp( "||",  OU  , 2));

		p_rpvm.addCmd( new RPVMCmdIf( "IF"));
		p_rpvm.addCmd( new RPVMCmdIf( "SI"));


		p_rpvm.addCmd( new RPVMCmdStop( "Stop"));

		p_rpvm.addCmd( new RPVMCmdDeclareVariable( "Var") );

		p_rpvm.addCmd( new RPVMRandom( "Alea") );
		p_rpvm.addCmd( new RPVMRandom( "Random") );

 
		p_rpvm.addCmd( new RPVMPileDup(  "Dup") );
		p_rpvm.addCmd( new RPVMPileDrop( "Drop") );
		p_rpvm.addCmd( new RPVMPileSwap( "Swap") );


		p_rpvm.addCmd( new RPVMCmdExecFile( "Include" ) );
		p_rpvm.addCmd( new RPVMCmdTrace( "trace"));
		p_rpvm.addCmd( new RPVMCmdExit( "exit"));
	}
}
//**********************************************************
class RPVMCmdHelp  extends  RPVMCmd{
		RPVMCmdHelp( String p_str ){
		super( p_str );				
	}
	//-------------------
	public boolean exec( RPVM p_rpvm, Object p_userdata,
						  RPVMManager p_mng, PPLangTokenizer p_tokenizer ){

		p_mng.processTrace( p_rpvm.writeHelp().toString() );		
		return true;
	}
}
//***********************************
class RPVMCmdTrace  extends  RPVMCmd{

		RPVMCmdTrace( String p_str){
		super( p_str );				
	}
	//-------------------
	public boolean exec( RPVM p_rpvm, Object p_userdata,
						  RPVMManager p_mng, PPLangTokenizer p_tokenizer ){

		// AJOUTER UNE DETERMINATION DU SEPARATEUR !!!!!
		StringBuffer l_strbuf= p_tokenizer.getParagraph( " \t\n", '"', '"', false);		


		p_mng.processTrace( l_strbuf.toString() );		
		return true;
	}
}
//***********************************
class RPVMCmdExecFile  extends RPVMCmd {
		RPVMCmdExecFile( String p_str ){
		super( p_str );				
	}		
	public boolean exec( RPVM p_rpvm, Object p_userdata,
						 RPVMManager p_mng, PPLangTokenizer p_tokenizer ){

		//		StringBuffer l_str= p_tokenizer.nextToken(" \t\n", " \t\n;" );
		PPLangToken l_cmd = p_tokenizer.readToken( );
		String l_str = l_cmd.c_val;

		try{

			FileReader l_fread= new FileReader( l_str.toString() );
		if( p_mng.getTraceFlag() ) trace( p_mng );

			boolean l_b =  p_rpvm.processStream( p_userdata, p_mng, l_fread );
			p_rpvm.setStopLocal(false);		 

			l_fread = null;
			return l_b;

		}catch( FileNotFoundException ffe ){
			throw new RPVMException( p_tokenizer.numLine(), "Fichier <" + l_str + "> non trouve");
		}
		
	}
}
//***********************************
class RPVMCmdBreak  extends RPVMCmd {

	static int c_mem_repi=0;

		RPVMCmdBreak( String p_str ){
		super( p_str );				
	}		
	public boolean exec( RPVM p_rpvm, Object p_userdata,
						 RPVMManager p_mng, PPLangTokenizer p_tokenizer ){
		p_rpvm.c_stop_local = true;
		return true;
	}
}
//***********************************
class RPVMCmdRepeat  extends RPVMCmd {

	static int c_mem_repi=0;

		RPVMCmdRepeat( String p_str ){
		super( p_str );				
	}		
	public boolean exec( RPVM p_rpvm, Object p_userdata,
						 RPVMManager p_mng, PPLangTokenizer p_tokenizer ){

		double l_x = p_rpvm.popPile();

		int nb = (int)l_x;
		boolean l_b=true;
 
		boolean l_init_deep = false;
		//		if( p_tokenizer.getSep() == '[' )
		//			{
				//				l_init_deep = true;
				//			}
		StringBuffer l_strbuf= p_tokenizer.getParagraph( " \t\n",'[',']', false);
		
		String l_str = l_strbuf.toString();

		if( p_mng.getTraceFlag() ) trace( p_mng );

		for( int i=0; i< nb && p_rpvm.c_stop_local == false; i++ ){
			int l_mem_repi = c_mem_repi;
			c_mem_repi = i;

			p_rpvm.addCmd(  new RPVMCmdNumber( "REPI", (double)i ) );
			StringReader l_reader = new StringReader(l_str);

			l_b =  p_rpvm.processStream( p_userdata, p_mng, l_reader);

			c_mem_repi = l_mem_repi;
			p_rpvm.addCmd(  new RPVMCmdNumber( "REPI", (double)c_mem_repi ) );

			if( l_b == false )
				return l_b;
		}

		p_rpvm.setStopLocal(false);		 

		return l_b;
	}
}
//***********************************
class RPVMCmdIf  extends RPVMCmd {

	static int c_mem_repi=0;

		RPVMCmdIf( String p_str ){
		super( p_str );				
	}		
	public boolean exec( RPVM p_rpvm, Object p_userdata,
						 RPVMManager p_mng, PPLangTokenizer p_tokenizer ){

		int l_x  = (int) p_rpvm.popPile();
		boolean l_bool = true;
		if( l_x == 0 )
			l_bool = false;
 

		
		boolean l_b=true;
 
		boolean l_init_deep = false;
		StringBuffer l_strbuf= p_tokenizer.getParagraph( " \t\n",
																										 '[',
																										 ']', false);
		
		String l_str = l_strbuf.toString();

		if( l_bool == true )
			{
				if( p_mng.getTraceFlag() ) trace( p_mng );

				StringReader l_reader = new StringReader(l_str);
				l_b =  p_rpvm.processStream( p_userdata, p_mng, l_reader);	
				p_rpvm.setStopLocal(false);		 
				return l_b;
					}
		return true;
	}
}
//***********************************
class RPVMCmdStop extends RPVMCmd {
	RPVMCmdStop(String p_str){	
		super( p_str );				
	}
	
	public boolean exec( RPVM p_rpvm, Object p_userdata,
						 RPVMManager p_mng, PPLangTokenizer p_tokenizer ){
		p_rpvm.setStopLocal(true);		 
		return true;
	}
}
//***********************************
class RPVMCmdTracePile extends RPVMCmd {
	RPVMCmdTracePile(String p_str){	
		super( p_str );				
	}

	
	public boolean exec( RPVM p_rpvm, Object p_userdata,
						 RPVMManager p_mng, PPLangTokenizer p_tokenizer ){
		p_rpvm.tracePile(p_mng);
		return true;
	}
}
//***********************************
class RPVMCmdFonction  extends RPVMCmd {

	String c_code;    // le code de la fonction

		RPVMCmdFonction( String p_str, String p_code ){
		super( p_str );				
		c_code = p_code;
	}		
	public boolean exec( RPVM p_rpvm, Object p_userdata,
						 RPVMManager p_mng, PPLangTokenizer p_tokenizer ){


		StringReader l_reader = new StringReader(c_code);
		if( p_mng.getTraceFlag() ) trace( p_mng );
		boolean l_bool = p_rpvm.processStream( p_userdata, p_mng, l_reader);
		p_rpvm.setStopLocal(false);		 
		return l_bool;
	}
}
//***********************************
class RPVMCmdDeclareFonction  extends RPVMCmd {
		RPVMCmdDeclareFonction( String p_str ){
		super( p_str );				
	}		
	public boolean exec( RPVM p_rpvm, Object p_userdata,
						 RPVMManager p_mng, PPLangTokenizer p_tokenizer ){

		// on recupere le nom de la fonction
		PPLangToken l_cmd =  p_tokenizer.readToken();
		String l_fname = l_cmd.c_val;
		
		boolean l_b=true;

		//		boolean l_init_deep = false;
		//		if( p_tokenizer.getSep() == '[' )
		//			{
		//				//				l_init_deep = true;
		//			}

		// on recupere le contenu de la fonction
		StringBuffer l_strbuf= p_tokenizer.getParagraph( " \t\n", '[', ']', false);		

		if( p_mng.getTraceFlag() ) trace( p_mng );
		p_rpvm.addCmd( new RPVMCmdFonction( l_fname.toString(), l_strbuf.toString() ) );

		return true;
	}
}
//***********************************
class RPVMCmdDeclareVariable extends RPVMCmd {
	RPVMCmdDeclareVariable( String p_str ){
		super( p_str );				
	}
	public boolean exec( RPVM p_rpvm, Object p_userdata,
						 RPVMManager p_mng, PPLangTokenizer p_tokenizer ){

		// on recupere le nom de la variable
		PPLangToken l_cmd =  p_tokenizer.readToken();
		String l_fname = l_cmd.c_val;
		double l_b = p_rpvm.popPile();
				
		p_rpvm.addCmd( new RPVMCmdNumber( l_fname.toString(), l_b ) );

		return true;
	}
}
//***********************************
class RPVMCmdNumber extends RPVMCmd {
double c_var;

	RPVMCmdNumber( String p_str, double p_var ){
		super( p_str );				
		c_var = p_var;
	}
	public boolean exec( RPVM p_rpvm, Object p_userdata,
						 RPVMManager p_mng, PPLangTokenizer p_tokenizer ){

		p_rpvm.pushPile( c_var);
		return true;
	}
	String help() { 
		return new String( "Variable:"+ c_str+"=" + Double.toString(c_var )); 
	}
}
//***********************************
class RPVMCmdOperation extends RPVMCmd {
	int c_optype;
		
	RPVMCmdOperation( String p_str, int p_optype ){
		super( p_str );
		c_optype = p_optype;
	}
	
	public boolean exec( RPVM p_rpvm, Object p_userdata,
								RPVMManager p_mng, PPLangTokenizer p_tokenizer ){

		double l_b = p_rpvm.popPile();
		double l_a = p_rpvm.popPile();
		double l_c = 0;
		
		switch( c_optype )
			{
			case RPVMCmdBase.PLUS : l_c = l_a + l_b; break;
			case RPVMCmdBase.MOINS: l_c = l_a - l_b; break;
			case RPVMCmdBase.MUL  : l_c = l_a * l_b; break;
			case RPVMCmdBase.DIV  : l_c = l_a / l_b; break;
			default:				
			}
		p_rpvm.pushPile(l_c);
		return true;
	}	
}
//***********************************
class RPVMCmdCmp extends RPVMCmd {
	int c_optype;
	int c_nbparam;
	
	RPVMCmdCmp( String p_str, int p_optype, int p_nbparam ){
		super( p_str );
		c_optype  = p_optype;
		c_nbparam = p_nbparam;
	}


	public boolean exec( RPVM p_rpvm, Object p_userdata,
								RPVMManager p_mng, PPLangTokenizer p_tokenizer ){

		double l_b = 0;
		if( c_nbparam == 2 )
			l_b = p_rpvm.popPile();

		double l_a = p_rpvm.popPile();
		boolean l_c = false;

		switch( c_optype )
			{
			case RPVMCmdBase.INF:    l_c = l_a <  l_b; break;
			case RPVMCmdBase.SUP:    l_c = l_a >  l_b; break;
			case RPVMCmdBase.INF_EG: l_c = l_a <= l_b; break;
			case RPVMCmdBase.SUP_EG: l_c = l_a >= l_b; break;
			case RPVMCmdBase.EGAL:   l_c = l_a == l_b; break;
			case RPVMCmdBase.DIFF:   l_c = l_a != l_b; break;

			case RPVMCmdBase.NOT:    if( l_a == 0 ) l_c = true ; 
				break;
			case RPVMCmdBase. ET:  
				{				 
					boolean l_ba = l_a == 1;
					boolean l_bb = l_b == 1; 
					l_c = l_ba && l_bb;
				break;
				}
			case RPVMCmdBase.OU:    
				{				 
					boolean l_ba = l_a == 1;
					boolean l_bb = l_b == 1; 
					l_c = l_ba || l_bb;
				}
			default:
			}
		if( l_c == true )			
			p_rpvm.pushPile(1);
		else
			p_rpvm.pushPile(0);
		return true;
	}	
}
//***********************************
class RPVMPileDup extends RPVMCmd {
	RPVMPileDup( String p_str ){
		super( p_str );				
	}
	public boolean exec( RPVM p_rpvm, Object p_userdata,
						 RPVMManager p_mng, PPLangTokenizer p_tokenizer ){
														 
		double l_b = p_rpvm.topPile();
		p_rpvm.pushPile( l_b );

		return true;
	}
}
//***********************************
class RPVMPileDrop extends RPVMCmd {
	RPVMPileDrop( String p_str ){
		super( p_str );				
	}
	public boolean exec( RPVM p_rpvm, Object p_userdata,
						 RPVMManager p_mng, PPLangTokenizer p_tokenizer ){
														 
		double l_b = p_rpvm.topPile();

		return true;
	}
}
//***********************************
class RPVMPileSwap extends RPVMCmd {
	RPVMPileSwap( String p_str ){
		super( p_str );				
	}
	public boolean exec( RPVM p_rpvm, Object p_userdata,
						 RPVMManager p_mng, PPLangTokenizer p_tokenizer ){
														 
		double l_a = p_rpvm.popPile();
		double l_b = p_rpvm.popPile();
		p_rpvm.pushPile( l_a );													
		p_rpvm.pushPile( l_b );													

		return true;
	}
}
//***********************************
class RPVMRandom extends RPVMCmd {
	RPVMRandom( String p_str ){
		super( p_str );	
	}
	public boolean exec( RPVM p_rpvm, Object p_userdata,
						 RPVMManager p_mng, PPLangTokenizer p_tokenizer ){
														 
		double l_a = p_rpvm.popPile();
		double l_r = Math.random() % l_a;
		p_rpvm.pushPile( l_r );													

		return true;
	}
}
//***********************************
class RPVMCmdExit extends RPVMCmd {
	RPVMCmdExit( String p_str ){
		super( p_str );	
	}
	public boolean exec( RPVM p_rpvm, Object p_userdata,
						 RPVMManager p_mng, PPLangTokenizer p_tokenizer ){
														 
		p_rpvm.setStop( true );
		return true;
	}
}



