/*
phipo/PhiLib/Interface/PPrint.java

	
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
import  phipo.PhiLib.Interface.*;

import java.awt.print.*;
import java.awt.*;
import javax.swing.JPanel;




// **********************************
public class PPrint extends JPanel implements Printable{

	PPManager c_mng;

	public 	PPrint( PPManager l_mng ){
		c_mng = l_mng;
	}

	//--------------------------
	public int print( Graphics g, PageFormat p_pformat, int l_pindex ){
		if( l_pindex == 0 ){

			g.translate( 100, 100 );

			if( c_mng.print( g, this  ) == true )
				return Printable.PAGE_EXISTS;
		}
		
		return Printable.NO_SUCH_PAGE;
	}
}
// **********************************
