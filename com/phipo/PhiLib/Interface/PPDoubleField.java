/*
phipo/PhiLib/Interface/PPDoubleField.java

	
Copyright (C) 2001 Philippe Yves Poupon
	
This program is distributed under the terms of the GNU General Public Licence



This file is part of phipo/PhiLib/Interface.

PPLab is free software; you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation; either version 2, or (at your option)
any later version.
 
PPLab is distributed in the hope that it will be useful, but
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



//*****************************
public class PPDoubleField extends PPField{

	//----------------
	public PPDoubleField( String p_str, double p_val, int p_sens ){

		super( p_str, Double.toString( p_val ), p_sens );
	}	
	//----------------
public	double getDouble(){
		return  Double.parseDouble( c_text.getText() );
	}
	//----------------
	public void setDouble(double p_val){
		c_text.setText( Double.toString( p_val ) );
	}
}
//*****************************
