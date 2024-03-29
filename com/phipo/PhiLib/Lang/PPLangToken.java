/*
phipo/PhiLib/Lang/PPLangToken.java

	
Copyright (C) 2001 Philippe Yves Poupon
	
This program is distributed under the terms of the GNU General Public Licence



This file is part of phipo/PhiLib/Lang.

RPVM is free software; you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation; either version 2, or (at your option)
any later version.
 
RPVM is distributed in the hope that it will be useful, but
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

package phipo.PhiLib.Lang;


import java.util.*;
import java.io.*;
import java.lang.*;
import java.io.Reader;
import java.io.StringReader;

//*****************************************************************************
public class PPLangToken{

public	PPLangToken() {;}
	public int    c_type;
	public String c_val;

public	static final int OPERAND     =1;
public	static final int OPERATOR    =2;
public	static final int NUMBER      =3;
public	static final int DELIMITEUR  =4;

public  static final String StrDelimiteur="(),\"'";
}
//*****************************************************************************
