/*
phipo/PhiLib/Interface/PPObjectDispatcher.java

	
Copyright (C) 2001 Philippe Yves Poupon
	
This program is distributed under the terms of the GNU General Public Licence



This file is part of phipo/PhiLib/Interface.

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


package phipo.PhiLib.Interface;
import  java.util.ArrayList;
import  java.util.Iterator;


// ***********************************************
public class PPObjectDispatcher {
	
	public static	final  int NEW_OBJECT_EVENT=0;
	public static	final  int DEL_OBJECT_EVENT=1;
	public static	final  int UPD_OBJECT_EVENT=2;
	public static	final  int ALL_OBJECT_EVENT=3;
	
	ArrayList c_register[];
	
	//------------------------
	public	PPObjectDispatcher(){
		c_register = new ArrayList[3];
		for( int i=0; i< ALL_OBJECT_EVENT; i++)
			c_register[i] = new ArrayList();							
	}
	//------------------------
	public void registerClient( PPObjectDispatchClient p_odcli, int c_evtype ){
		
		if( c_evtype == ALL_OBJECT_EVENT ) {
			for( int i=0; i< ALL_OBJECT_EVENT;  i++)
				if( c_register[i].contains( p_odcli ) == false )
					c_register[i].add( p_odcli );
		}
		else
			if( c_register[c_evtype].contains( p_odcli ) == false )
				c_register[c_evtype].add( p_odcli );
	}
	//------------------------
	public void unregisterClient( PPObjectDispatchClient p_odcli, int c_evtype ){
		if( c_evtype == ALL_OBJECT_EVENT ) {
			for( int i=0; i< ALL_OBJECT_EVENT;  i++)
				c_register[i].remove( p_odcli );
		}
		else
			c_register[c_evtype].remove( p_odcli );
	}

	//------------------------
	public	void dispatchEvent(  PPObjectDispatch p_dobj, int c_evtype ){

		if( c_evtype == ALL_OBJECT_EVENT ) {
			for( int i=0; i< ALL_OBJECT_EVENT;  i++){
				Iterator l_iter = c_register[i].iterator();
				while( l_iter.hasNext()){
					PPObjectDispatchClient l_podcli = (PPObjectDispatchClient)l_iter.next();
					l_podcli.receivePPObjectDispatchEvent( p_dobj, c_evtype);
				}
			}
		}
		else {			
			Iterator l_iter = c_register[c_evtype].iterator();
			while( l_iter.hasNext()){
				PPObjectDispatchClient l_podcli = (PPObjectDispatchClient)l_iter.next();
				l_podcli.receivePPObjectDispatchEvent( p_dobj, c_evtype);
			}
		}
	}
}

// ***********************************************

