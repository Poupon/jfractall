package phipo.PhiLib.Lang;


import javax.swing.JMenu;
import java.io.*;
import java.util.*;

import java.lang.Integer;
import java.lang.*;
import java.util.HashMap;
import java.util.Iterator;

import phipo.PhiLib.Util.PPToken;

//*****************************************************************************
public class RPVM{
  static public volatile boolean  c_stop_process=false;  // arrete l execution

  public volatile boolean  c_stop_local=false; 

	public volatile boolean  c_debug = false;

	public void setStop( boolean p_b) { c_stop_process=p_b; } 
	public void setStopLocal( boolean p_b) { c_stop_local=p_b; } 
	
	// le dictionnaire du langage
	int           c_nbcmd;
	//////	RPVMCmd c_dico[] = new RPVMCmd[256]; // ATTENTION !!!!!
	HashMap c_dico= new HashMap();


	// la pile des donnees
	double        c_pile[]= new double[65535];
	int           c_pilepos=0;

	PPLangToken c_ptok;  // token courant lors de l analyse
	
	public PPLangToken getCurrentToken() { return c_ptok; }


  public final boolean pileEmpty() { return c_pilepos == 0; }
	public final double popPile() { //if( pileEmpty() { throw
		return c_pile[--c_pilepos];
	}
	public final double topPile() { //if( pileEmpty() { throw
		return c_pile[c_pilepos-1];
	}
	public final void pushPile(double p_val)   { c_pile[c_pilepos++] = p_val; }
	public final void pushPile( String p_str ) {		pushPile(Double.parseDouble( p_str )); }
		
	
	// -----------------------------------
	public Object saveDictionary() {
		return new HashMap( c_dico );
	}	
	// -----------------------------------
	public void restoreDictionary( Object l_obj) {
		c_dico = (HashMap)l_obj;
	}
	// -----------------------------------
	public void tracePile(RPVMManager p_mng){
		p_mng.processTrace(  "Pile=>>" );
		for( int i=0; i< c_pilepos; i++) 			
			p_mng.processTrace( Double.toString( c_pile[i] ) + " ");

		p_mng.processTrace(  "\n" );
	}
	// -----------------------------------
	public RPVM(){		
	}
	//----------------
	public void addCmd( RPVMCmd p_cmd ){
		c_dico.put( p_cmd.getName().toUpperCase(), p_cmd );
	}
	//----------------
	public RPVMCmd findCmd( String p_str ) {
		return (RPVMCmd)c_dico.get( p_str.toUpperCase() );
	}
	//------------
	public StringBuffer writeHelp() {
		StringBuffer p_strbuf = new StringBuffer();
		
		Iterator l_iter = c_dico.values().iterator();
		while( l_iter.hasNext() ){
				p_strbuf.append( ((RPVMCmd)l_iter.next()).help()+"\n" );
			}
		return p_strbuf;
	}
	//------------
	Integer getTokenInt(PPToken p_tokenizer){
		
		StringBuffer l_tok;
		l_tok = p_tokenizer.nextToken();

		if( l_tok == null )
			{
				throw new RPVMException( p_tokenizer.numLine(), " il faut un nombre valide" );		 
			}
		String l_str = new String( l_tok );
		try{
			Integer l = new Integer(  l_str );
			return l;
		}
		catch( NumberFormatException ie ){
			throw new RPVMException( p_tokenizer.numLine(), " ce n'est pas un nombre valide" );		 
		}
	}
	//----------------
	public boolean processStream( Object p_userdata,
											RPVMManager p_mng,
											Reader l_reader ){
		try{

			System.out.println( "processStream" );
			char l_char[] = new char[2];
			String l_forend = new String( " \t\n" );
			
			PPLangTokenizer l_tokenizer  = new PPLangTokenizer(l_reader);

			RPVMCmd l_cmd;


		setStopLocal(false);		 

		while( (c_ptok = l_tokenizer.readToken()) != null && c_stop_process == false && c_stop_local == false )
			{
				//				System.out.println( "<"+c_ptok.c_val+">" );
				switch(c_ptok.c_type ){
				case PPLangToken.NUMBER:
					pushPile( c_ptok.c_val );
					break;
					 
				case PPLangToken.OPERAND:		
				case PPLangToken.OPERATOR:
				case PPLangToken.DELIMITEUR:
					if( (l_cmd = findCmd( c_ptok.c_val )) == null){
						
							//								System.out.println( "<" + c_ptok.c_val +  " Inconnu >" );
						p_mng.processError( RPVMException.formatError( l_tokenizer.numLine(), 	
																															" : Commande inconnu <" + c_ptok.c_val+">") );
							return false;
						}
					else {
							//							System.out.println( "<" + l_ptok.c_val + ":" +l_cmd.c_str + "  Ok>" );
			
							if( c_debug ) p_mng.processTrace( l_cmd.stringDebug( ) +"\n");

							if( l_cmd.exec( this, p_userdata, p_mng, l_tokenizer ) ==false)
								{
									return false;
								}
						}

					break;
				default:
				}
			}
		}
		catch( RPVMException ple ){
			System.out.println( "RPVMException" );
			p_mng.processError( ple );
			return false;
		}
		//		System.out.println( "Fini !" );
		return true;
	}

	//----------------
	public boolean processString( Object p_userdata,
								 RPVMManager p_mng,
								 String l_str ){
		StringReader l_reader = new StringReader(l_str);
		return processStream( p_userdata, p_mng, l_reader );
	}	
}
//*****************************************************************************
