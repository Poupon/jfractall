/*
phipo/PhiLib/Interface/PPFileFilter.java

	
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

import javax.swing.filechooser.*;
import java.io.File;


//***********************************
public class PPFileFilter extends javax.swing.filechooser.FileFilter{

	String c_extension;
	String c_description;

public String getDescription()            { return c_description; }
public 	void setDescription(String p_desc) { c_description= p_desc; }
public 	void addExtension( String p_ext )  { c_extension = p_ext; }

public 	PPFileFilter(){
	}
	
public boolean accept( File p_f ){
		
		if( p_f != null ){
			if( p_f.isDirectory() ) 
				return true;
			
			//			System.out.println( "PPFileFilter:" +  p_f.getName() );
			return p_f.getName().toLowerCase().endsWith( c_extension);
		}
		return false;
	}	 
}
