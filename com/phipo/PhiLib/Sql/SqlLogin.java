package phipo.PhiLib.Sql;

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

import phipo.PhiLib.Sql.SqlServer;
import phipo.PhiLib.Interface.PPField;
import phipo.PhiLib.Util.PPIniFile;
import phipo.PhiLib.Util.PPTrace;

// **********************************
public class SqlLogin extends JDialog
	implements  ActionListener	{
		SqlServer cServer;

		JPanel cPanel;

		PPField cFieldServer;
		PPField cFieldMach;
		PPField cFieldPort;
		PPField cFieldUser;
		PPField cFieldPass;
		PPField cFieldBase;

		boolean cValidation = false;

		public boolean   getValidation() { return cValidation; }
		public SqlServer getSqlServer()  { return cServer; }

		
		//-----------------------

		public SqlLogin( Frame pOwner, int pX, int pY  ){
				super( pOwner,"Login", true );
				cServer = new SqlServer();
				init( pX, pY);
		}
		
		public SqlLogin(  Frame pOwner, String pName, PPIniFile pFileIni, String pKeySection, int pX, int pY  ){
				super( pOwner, "Login to " +pName, true );
				cServer = new SqlServer( pFileIni, pKeySection, pName);
				init( pX, pY);
		}

		public SqlLogin(  Frame pOwner, SqlServer pServer,  int pX, int pY  ){
				super( pOwner, "Login to "+pServer.cName, true );
				cServer = pServer;
				init(pX, pY);
		}
		//-----------------------

		void init( int pX, int pY ){

				
				setLocation( pX, pY );


				PPTrace.Traceln( cServer.cMachine );
				getContentPane().setLayout( new BorderLayout() );

				cPanel =	new JPanel();
				getContentPane().add( cPanel, BorderLayout.CENTER );

				cPanel.setLayout( new GridLayout( 6, 1 ));

				cFieldServer = new PPField( "Server", cServer.cName, PPField.HORIZONTAL );
				cPanel.add( cFieldServer );
				cFieldMach = new PPField( "Machine", cServer.cMachine, PPField.HORIZONTAL  );
				cPanel.add( cFieldMach );
				cFieldPort = new PPField( "Port",    cServer.cPort, PPField.HORIZONTAL  );
				cPanel.add( cFieldPort );
				cFieldUser = new PPField( "User",    cServer.cUser, PPField.HORIZONTAL  );
				cPanel.add( cFieldUser );
				cFieldPass = new PPField( "Pass",    cServer.cPass, PPField.HORIZONTAL  );
				cPanel.add( cFieldPass );
				cFieldBase = new PPField( "Base",    cServer.cBase, PPField.HORIZONTAL  );
				cPanel.add( cFieldBase );	

				//===============
				JPanel lSouth = new JPanel();
				lSouth.setLayout( new GridLayout( 1, 0 ));
				lSouth.setBorder( BorderFactory.createEmptyBorder( 10, 10, 10, 10 ));

				JButton lButtonOk = new JButton( "Ok" );
				lButtonOk.setActionCommand( "ok");
				lButtonOk.addActionListener( this );

				JButton  lButtonCancel = new JButton( "Cancel" );
				lButtonCancel.setActionCommand( "cancel");
				lButtonCancel.addActionListener( this );

				lSouth.add( lButtonOk );
				lSouth.add( lButtonCancel );
				//===============

				getContentPane().add( lSouth, BorderLayout.SOUTH );

				pack();
				setVisible(true);

				
		}
		//---------------------
		public void actionPerformed( ActionEvent p_e ){
				
				if( p_e.getActionCommand().equals("ok")) {						
						setVisible(false);
						cServer.cName = new String( cFieldServer.getString());
						cServer.cMachine = new String( cFieldMach.getString());
						cServer.cPort = new String( cFieldPort.getString());
						cServer.cUser = new String( cFieldUser.getString());
						cServer.cPass = new String( cFieldPass.getString());
						cServer.cBase = new String( cFieldBase.getString());
						cValidation = true;
						ok();
				}
				else if( p_e.getActionCommand().equals("cancel")) {
						setVisible(false);
						cValidation = false;
						dispose();
				}
		}
		
		//---------------------
		public void ok(){				
				cValidation = true;					
				setVisible(false);
				dispose();
		}
}
