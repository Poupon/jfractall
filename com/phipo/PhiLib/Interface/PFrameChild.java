/*
phipo/PhiLib/Interface/PFrameChild.java

	
Copyright (C) 2001 Philippe Yves Poupon
	
This program is distributed under the terms of the GNU General Public Licence



This file is part of phipo/PhiLib/Interface.

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

package phipo.PhiLib.Interface;
import  phipo.PhiLib.Interface.*;

import javax.swing.JInternalFrame;
import javax.swing.event.*;

import java.awt.event.*;
import java.awt.*;
import java.beans.PropertyVetoException;

import java.util.StringTokenizer;


//***********************************
public class PFrameChild extends JInternalFrame implements InternalFrameListener {

    static int openFrameCount = 0;
    static final int xOffset = 30, yOffset = 30;

	//-------------------------------------
    public PFrameChild( String p_name ) {
        super( p_name + "#" + (++openFrameCount), 
              true, //resizable
              true, //closable
              true, //maximizable
              true);//iconifiable

        //...Create the GUI and put it in the window...

        //...Then set the window size or call pack...
        setSize(300,300);

        //Set the  window's location.
        setLocation(xOffset*openFrameCount, yOffset*openFrameCount);
				if( openFrameCount > 10 ) openFrameCount=1;

				addInternalFrameListener( this );
					
    }
	//-------------------------------------
		public void front(){
				try{
					setIcon( false );
					show();
					toFront();	
				}
				catch( PropertyVetoException pv ){
				}
		}
		//-------------------------------------
		//-------------------------------------
		// implements internalFrameListenery

		//-------------------------------------
		//-------------------------------------

		public void internalFrameOpened( InternalFrameEvent pEv){
				System.out.println( "internalFrameOpened");
		}
		//-------------------------------------
		public void internalFrameClosing( InternalFrameEvent pEv){
				System.out.println( "internalFrameClosing");
		}
		//-------------------------------------
		public void internalFrameClosed(InternalFrameEvent pEv){
				System.out.println( "internalFrameClosed");
		}
		//-------------------------------------
		public void internalFrameIconified( InternalFrameEvent pEv){
			System.out.println( "internalFrameIconified");
		}
		//-------------------------------------
		public void internalFrameDeiconified( InternalFrameEvent pEv){
			System.out.println( "internalFrameDeiconified");
		}
		//-------------------------------------
		public void internalFrameActivated( InternalFrameEvent pEv){
			System.out.println( "internalFrameActivated");
		}
		//-------------------------------------
		public void internalFrameDeactivated( InternalFrameEvent pEv){
			System.out.println( "internalFrameDeactivated");
		}	
}
//***********************************
