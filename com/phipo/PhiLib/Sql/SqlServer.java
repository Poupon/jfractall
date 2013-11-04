package phipo.PhiLib.Sql;

import java.lang.*;
import java.util.*;

import phipo.PhiLib.Util.PPIniFile;
import phipo.PhiLib.Util.PPTrace;


// **********************************
public class SqlServer{

		public String cName = null;
		public String cMachine= null;
		public String cPort= null;
		public String cUser= null;
		public String cPass= null;
		public String cBase= null;

		public SqlServer( ){

				cName = new String(  );
				cMachine = new String(  );
				cPort = new String(  );
				cUser = new String(  );
				cPass = new String(  );
				cBase = new String(  );
		}
		public SqlServer( SqlServer pSqlServer ){

				cName = new String( pSqlServer.cName );
				cMachine = new String( pSqlServer.cMachine );
				cPort = new String( pSqlServer.cPort );
				cUser = new String( pSqlServer.cUser );
				cPass = new String( pSqlServer.cPass );
				cBase = new String( pSqlServer.cBase );
		}
		public SqlServer( String pName, String pMachine, String pPort,
							 String pUser, String pPass, String pBase ){

				cName = new String( pName );
				cMachine = new String( pMachine );
				cPort = new String( pPort );
				cUser = new String( pUser );
				cPass = new String( pPass );
				cBase = new String( pBase );
		}
		public SqlServer( char pName[], char pMachine[], char pPort[],
							 char pUser[], char pPass[], char pBase[] ){

				cName = new String( pName );
				cMachine = new String( pMachine );
				cPort = new String( pPort );
				cUser = new String( pUser );
				cPass = new String( pPass );
				cBase = new String( pBase );
		}

		public SqlServer( PPIniFile pFileIni, String pKeySection, String pName ){

				cName = new String( pName );

				String  lStr = pFileIni.get( pKeySection, pName );
				PPTrace.Traceln(" SqlServer " + pKeySection + " " + pName + " " + lStr);

				if( lStr != null ){
						StringTokenizer lTok = new StringTokenizer( lStr, "," );
						
						try{
								cMachine =lTok.nextToken().trim();			
								cPort =lTok.nextToken().trim();			
								cUser =lTok.nextToken().trim();			
								cPass =lTok.nextToken().trim();			
								cBase =lTok.nextToken().trim();			
						}
						catch( Exception lE){
								System.err.println( "SqlServer : Error when reading init file : " + lStr );
						}
				}
		}
		//-----------------------

		public boolean isComplete(){
				if( cMachine == null  || cMachine.length() == 0
						|| cPort == null  || cPort.length() == 0
						|| cUser == null  || cUser.length() == 0
						|| cPass == null  || cPass.length() == 0){
						return false;
				}
				return true;
		}
};
