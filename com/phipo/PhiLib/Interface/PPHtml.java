package phipo.PhiLib.Interface;


import  phipo.PhiLib.Interface.*;

import javax.swing.*;

import java.io.*;
import java.lang.*;

import java.awt.event.*;
import java.awt.*;

import java.net.URL;


//***********************************
public class PPHtml extends JEditorPane{

		PPHtml( String pPage ){

				setEditable(false);

				//		URL lURL = this.getClass().getRessource( pPage );
				try{
						URL lURL = new  URL (pPage );
						
						if( lURL != null ){
								try{
										setPage( lURL );
								}
								catch( IOException e){
										System.err.println( e + "Attemped to read a bad URL : " + lURL);								
								}						
						}
						else
								System.err.println( "Couldn't find file:"	);
				}
				catch( Exception e ){
						System.err.println( e );								
				}
		}
}
//***********************************
