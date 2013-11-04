package phipo.PhiLib.Interface;


import  phipo.PhiLib.Interface.*;
import javax.swing.*;
import java.awt.*;


//***********************************

public class PPHelpChild extends PFrameChild{
		
		public PPHelpChild(  String pPage ){

				super( pPage );

				getContentPane().setLayout( new BorderLayout() );
	
				PPHtml lHtml = new PPHtml( pPage );
				//JTextArea lHtml = new JTextArea( pPage );
				JScrollPane lScroll  = new JScrollPane(lHtml,	
																							 JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
																							 JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
				
				getContentPane().add( lScroll, BorderLayout.CENTER );
				setDefaultCloseOperation( WindowConstants.HIDE_ON_CLOSE );		
		}

}
//***********************************
