/*
Interface/PPField.java

	
Copyright (C) 2001 Philippe Yves Poupon
	
This program is distributed under the terms of the GNU General Public Licence



This file is part of Interface.

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




//**********************************************************
public class PPField extends JPanel{

	static public final int VERTICAL=0;
	static public final int HORIZONTAL =1;
	static public final int FLOW_LAYOUT =2;

	JLabel     c_label;
	JTextField c_text;

	//----------------
	public PPField( String p_str, String p_val, int p_sens ){

		
		if( p_sens == VERTICAL )
			setLayout( new GridLayout( 0, 1 ));
		else
			if( p_sens == HORIZONTAL )
				setLayout( new GridLayout( 1, 0 ));
		else
			setLayout( new FlowLayout());
		
		c_label = new JLabel( p_str );
		c_text  = new JTextField( p_val  );
		
		add( c_label );
		add( c_text );

	}
	//----------------
	public	void addActionListener( ActionListener p_al ){
		c_text.addActionListener( p_al );
	}
	//----------------
	public	boolean isAction( Object p_o){
		if( p_o == c_text )
			return true;
		
		return false;
	}
	//----------------
	public String getString() { return c_text.getText();}
	public void setString(String  p_val) { c_text.setText( p_val ); }
	public void setEditable(boolean p_bool ) { c_text.setEditable( p_bool );}
}
//**********************************************************

