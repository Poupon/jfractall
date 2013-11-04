package phipo.PhiLib.Sql;


import java.awt.event.*;

import phipo.PhiLib.Util.*;
import phipo.PhiLib.Sql.*;


import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JFrame;
import javax.swing.JCheckBox;

import java.io.OutputStream;

import java.sql.*;
import java.io.*;
import java.util.*;

// **********************************

public class SqlConnex {
		SqlServer    cServer;
		PrintStream  cOStream = System.out;

		Connection   cConnect;
		char         cSeparator = '\0';
		boolean      cPrintColumnName=true;
  	boolean      cPrintInfo=true;


		public  Connection getConnection() { return  cConnect; }
		public  SqlServer getSqlServer() { return cServer; }

		//-----------------------
		public void setPrintStream( PrintStream  pOStream ){
				cOStream = pOStream;
		}
		//-----------------------
		public SqlConnex( SqlServer pServer, PrintStream pOStream){
				
				cServer  = pServer;
				cOStream = pOStream;
		}
		//-----------------------
		public SqlConnex( SqlServer pServer){
				
				cServer  = pServer;
		}
		//-----------------------
		void setSeparator( char pSep )         { cSeparator = pSep; }
		void setPrintColumnName(boolean pFlag) { cPrintColumnName = pFlag; }
		void setPrintInfo( boolean pFlag)      { cPrintInfo  = pFlag; }
		
	//-----------------------
		public boolean connect(){

				try{
						Class.forName("com.sybase.jdbc2.jdbc.SybDriver");
						String url = "jdbc:sybase:Tds:"+cServer.cMachine+":"+cServer.cPort;
						
						Properties props = new Properties();	
						
						props.put( "user",      cServer.cUser);
						props.put( "password",  cServer.cPass);
						
						//if (_gateway != null) props.put("proxy", _gateway);

						cConnect = DriverManager.getConnection(url, props);
				}
				catch(  Exception sqe){		
						cOStream.println( "SqlConnex.connect Error : " + sqe);
						sqe.printStackTrace();
						cConnect = null;
						return false;
				}
				if( cServer.cBase != null && cServer.cBase.length() > 0 )
						return sendCommand( "use " + cServer.cBase );
				
				return true;
		}		
		//-----------------------
		public void disconnect(){
				try{
						cConnect.close();
				}
				catch(  Exception sqe){		
						cOStream.println(sqe);
				}

				cConnect = null;
				cServer = null;
		}
		//-----------------------
		static final String sData=new String("                                                                                                                                ");

		void format( StringBuffer pStr, String pData, int pSz ){
				pStr.append( pData );
				pSz -= pData.length();
				if( pSz >=0 ) {
						if( pSz > 128 ) 
								pSz= 128;
						
						pStr.append( sData.substring( 0, pSz ));
				}
				if( cSeparator != '\0' )
						pStr.append('|');
		}
		//-----------------------
		public boolean sendCommandAndPrintResult(String pQuery ){
				if( pQuery.length() == 0)
						return true;

				try{
						if( cConnect == null &&  connect() == false )
								return false;
								
						Statement lStatement = cConnect.createStatement();
						
						System.out.println( "+++++> lStatement.execute <<<" +pQuery +">>>");
						boolean lResults = lStatement.execute(pQuery);

						int lNbResult = 0;
						
						int rowsAffected = 0;

						ResultSetMetaData lResultSetmd = null;
						int lRowsAffected =0;

						do {
								if (lResults)
										{
												lNbResult++;
												
												ResultSet lResultSet = lStatement.getResultSet();												
												ResultSetMetaData lResultSetMeta = lResultSet.getMetaData();


												int lNumColumns = lResultSetMeta.getColumnCount();
												
												if( cPrintInfo )
														cOStream.println("\n------------------ Result set " 																					 
																					 + lNbResult + " -----------------------\n");
												StringBuffer lColumn = new StringBuffer();
									
												if( cPrintColumnName )
														for (int i = 1; i <= lNumColumns; i++) {														
																//			lColumn.append("\t" + lResultSetMeta.getColumnName(i));														
																format( lColumn, lResultSetMeta.getColumnLabel(i), lResultSetMeta.getColumnDisplaySize(i)+1 );													
														}


												cOStream.println( lColumn.toString() );


												for(int lRowNum = 1; lResultSet.next(); lRowNum++) {
														lColumn.setLength(0);
														//														lColumn = new StringBuffer("[ " + lRowNum + "]");

														for (int i = 1; i <= lNumColumns; i++) {
																format( lColumn, lResultSet.getString(i), lResultSetMeta.getColumnDisplaySize(i)+1);
																//																lColumn.append("\t" + lResultSet.getString(i));																
														}
														cOStream.println(lColumn.toString());														
												}
										}								
								else 	{										
										lRowsAffected = lStatement.getUpdateCount();										
										if (lRowsAffected >= 0) {			
												if( cPrintInfo )
														cOStream.println(rowsAffected + " rows Affected.");
										}
								}
								
								lResults = lStatement.getMoreResults();								
						}	while (lResults || lRowsAffected != -1);

						lStatement.close();	
				}
				catch (SQLException sqe) {
						cOStream.println("Unexpected exception : " +															 
															 sqe.toString() + ", sqlstate = " + sqe.getSQLState());						
						sqe.printStackTrace();

						//						lStatement.cancel();						
						return false;
				}
				return true;
		}
		//-----------------------
		public boolean sendCommand(String pQuery ){
				if( pQuery.length() == 0)
						return true;

				try{
						if( cConnect == null &&  connect() == false )
								return false;
								
						Statement lStatement = cConnect.createStatement();
						
						System.out.println( "+++++> lStatement.execute <<<" +pQuery +">>>");
						boolean lResults = lStatement.execute(pQuery);
						System.out.println( "++++++++++++++++++++++++++++++++++++++++++++++++++++");

						int lNbResult = 0;
						
						int rowsAffected = 0;

						ResultSetMetaData lResultSetmd = null;
						int lRowsAffected =0;

						do {
								if (lResults)
										{
										}								
								else 	{										
										lRowsAffected = lStatement.getUpdateCount();										
										if (lRowsAffected >= 0) {			
												if( cPrintInfo )
														cOStream.println(rowsAffected + " rows Affected.");
										}
								}
								
								lResults = lStatement.getMoreResults();								
						}	while (lResults || lRowsAffected != -1);

						lStatement.close();	
				}
				catch (SQLException sqe) {
						cOStream.println("Unexpected exception : " +															 
															 sqe.toString() + ", sqlstate = " + sqe.getSQLState());						
						sqe.printStackTrace();

						//						lStatement.cancel();						
						return false;
				}
				return true;
		}
}
//***********************************
