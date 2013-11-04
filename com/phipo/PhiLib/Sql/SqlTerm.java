package phipo.PhiLib.Sql;

import java.awt.event.*;
import java.awt.Font;


import javax.swing.*;
import javax.swing.event.*;
import javax.swing.JInternalFrame;
import javax.swing.ImageIcon;

import phipo.PhiLib.Interface.*;
import phipo.PhiLib.Util.*;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JFrame;
import javax.swing.JCheckBox;


import java.sql.*;
import java.io.*;
import java.util.*;

import phipo.PhiLib.Interface.*;

// **********************************

public class SqlTerm extends PFrameChild implements ActionListener{
		PPTerm     cTerm;
		SqlServer  cServer;
		SqlConnex  cSqlConnex;

		//		JMenuBar   cMenuBar;

		//-----------------------
		public SqlTerm( SqlServer pServer ){
				super( "SQL server : " + pServer.cName );
				
				cServer = pServer;

				
				cTerm = new PPTerm();
			 	cTerm.addActionListener( this );
				
				getContentPane().add( cTerm );
				PPAppli.ThePPAppli.addChild( this );
				connect();

				//				setJMenuBar(cMenuBar = new JMenuBar());
				//				JMenuItem lItem = new JMenuItem( "Login" );				
				//				lItem.addActionListener( this );	 		
				//				cMenuBar.add( lItem)s
		}
		//-----------------------
		void connect(){
				while( true ) {
						if( cServer.isComplete() 
								&& (cSqlConnex = new SqlConnex( cServer, cTerm.Stream()  )).connect() ) {
								cTerm.appendln( "Connected to " + cServer.cName );
								break;
						}		
						SqlLogin lSqlLogin = new SqlLogin( PPAppli.ThePPAppli, cServer, 300, 200  );
						if( lSqlLogin.getValidation() == false ){
								cSqlConnex.disconnect();
								cSqlConnex = null;
								cTerm.appendln( "Not connected");
								break;
						}
				}
		}
		//-----------------------
		public boolean  sendCommand(String pQuery ){
				return cSqlConnex.sendCommandAndPrintResult( pQuery );
		}
		//-----------------------
		public void actionPerformed(ActionEvent pEv ){
			//		System.out.println( cTerm.getLastCmd() );

				//				if(  pEv.getActionCommand().equals( "Login")){	
				//						new SqlLogin( PPAppli.ThePPAppli, cServer );		
				//				}	
				//				else
				//				if( actionPopupPerformed( pEv )==false){
				sendCommand( cTerm.getLastCmd() );
				//}
		}
	//-----------------------
		PPTerm getTerm() { return cTerm; }
}
//***********************************
