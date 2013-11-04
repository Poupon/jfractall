/*
JFractAll/CanvasFractal.java

	
Copyright (C) 2001 Philippe Yves Poupon
	
This program is distributed under the terms of the GNU General Public Licence



This file is part of Fractal.

Fractal is free software; you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation; either version 2, or (at your option)
any later version.
 
Fractal is distributed in the hope that it will be useful, but
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

package phipo.JFractAll;


import phipo.PhiLib.Interface.*;


import java.awt.event.*;
import java.awt.*;
import java.awt.image.*;

import javax.swing.*;
import javax.swing.Timer;
import javax.swing.JPopupMenu;

import java.awt.GraphicsEnvironment;
import java.awt.GraphicsDevice;
import java.awt.GraphicsConfiguration;

//***********************************
final public class CanvasFractal extends PPCanvas
	implements MouseMotionListener, MouseListener, ActionListener{
	Dimension         c_size;
	FrameFractal c_frame;

	BufferedImage c_buffer;
	Graphics      c_graphics=null;

	int  c_memx;
	int  c_memy;

	final int CMD_NO = 0;
	final int CMD_ZONE_DRAG=1;

	int c_mode = CMD_NO;


	JPopupMenu c_popmenu=null;
	
	BufferedImage getBufferedImage() { return c_buffer;}

	FrameFractal getFrame() { return c_frame; }
	//--------------------------
	public CanvasFractal( FrameFractal p_frame) { 
		
		super();
		c_frame   = p_frame;
		c_size    = getSize();


 		addMouseListener( this );		
 		addMouseMotionListener( this );		
	}
	//-------------------------- 
	public void paint( Graphics g ){
		super.paintComponent(g);

		boolean l_must_be_redraw = false;

		Dimension l_size = getSize();


		if( c_buffer == null || l_size.equals( c_size) == false )
			{
				GraphicsEnvironment l_ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
				GraphicsDevice []    l_gd = l_ge.getScreenDevices();
				GraphicsConfiguration[] l_gc = l_gd[0].getConfigurations();
				
				c_size = l_size;
				BufferedImage l_old_buffer=null;

				if( c_buffer != null )
					l_old_buffer = c_buffer;
				
				c_buffer = l_gc[0].createCompatibleImage( c_size.width, c_size.height );
				//				c_buffer = new BufferedImage( c_size.width, c_size.height,  BufferedImage.TYPE_BYTE_INDEXED );
				if( c_graphics != null) 
					{
						c_graphics.finalize();
					}			 
				c_graphics = c_buffer.createGraphics();
				c_graphics.setColor( Color.black );				
				c_graphics.clearRect(  0, 0,  c_size.width, c_size.height);
				if( l_old_buffer != null )
					{
						c_graphics.drawImage( l_old_buffer, 0, 0, this );	
					}				
				l_must_be_redraw = true;
			}
	
		g.drawImage( c_buffer, 0, 0, this );	
		if( l_must_be_redraw )
			{									
			  c_frame.calcul(true);
			}
	}
	//-------------------------- 
	public	void update( Graphics g ){
		System.out.println( "Update" );
	}
	//-------------------------- 
	public void actionPerformed( ActionEvent p_ev ){		
		System.out.println( "repaint" );
	}
	//-------------------------- 
	public void mousePressed( MouseEvent p_e ) {
		System.out.println( "mouse pressed" );		
	} 
	//-------------------------- 
	public void mouseReleased( MouseEvent p_e ) {
		//		System.out.println( "mouse released" );			
	}
	
	public void mouseEntered( MouseEvent p_e ) {
		//		System.out.println( "mouse entered" );
	}

	public void mouseExited( MouseEvent p_e ) {
		//		System.out.println( "mouse exited" );
	}
	// -----------------------------
	double getInternalX(int x) {		
				int     l_xe = (int)(x  - c_size.getWidth() /2);
				return  c_frame.c_conf.c_x + l_xe/c_frame.c_conf.c_zoom;
	}
	// -----------------------------
	double getInternalY(int y){
				int    l_ye = (int)(y  - c_size.getHeight()/2);				
				return c_frame.c_conf.c_y + l_ye/c_frame.c_conf.c_zoom;
	}
	// -----------------------------
	public void mouseClicked( MouseEvent p_e ) {

		ConfigDiag.cs_TheConfigDiag.c_current	= c_frame;
		ConfigDiag.cs_TheConfigDiag.setConfig( c_frame.c_conf );		

		
		if( SwingUtilities.isMiddleMouseButton( p_e ) == true )
			{				
				double l_x  = getInternalX(p_e.getX());
				double l_y  = getInternalY(p_e.getY());
				
				System.out.println( "X:" + l_x + " Y:" + l_y  );
				
				if( p_e.isShiftDown() )
					{
						FractalConfig l_conf = new FractalConfig( c_frame.c_conf );

						l_conf.c_x = l_x;
						l_conf.c_y = l_y;
						
						FrameFractal l_frame = new FrameFractal( l_conf, c_frame.c_tcol );
						PPAppli.ThePPAppli.addChild(l_frame);
					}
						else
							{
								c_frame.c_conf.c_x = l_x;
								c_frame.c_conf.c_y = l_y;		
								c_frame.calcul(false);
							}				
			}
		else
		if( SwingUtilities.isRightMouseButton( p_e ) == true || SwingUtilities.isLeftMouseButton( p_e ) == true)
			{
				int    l_xe = (int)(p_e.getX() - c_size.getWidth() /2);
				int    l_ye = (int)(p_e.getY() - c_size.getHeight()/2);
				
				double l_x  = c_frame.c_conf.c_x + l_xe/c_frame.c_conf.c_zoom;
				double l_y  = c_frame.c_conf.c_y + l_ye/c_frame.c_conf.c_zoom;
				
				System.out.println( "X:" + l_x + " Y:" + l_y  );

				double l_zoom = ((JFractAll)PPAppli.ThePPAppli).c_field_zoom.getDouble();
				if( l_zoom <= 1 )
							{
								l_zoom = 10;
								((JFractAll)PPAppli.ThePPAppli).c_field_zoom.setDouble(l_zoom);
							}
	
						
				if( p_e.isShiftDown() )
					{
						FractalConfig l_conf = new FractalConfig( c_frame.c_conf );

						l_conf.c_x = l_x;
						l_conf.c_y = l_y;
						
						if( SwingUtilities.isLeftMouseButton( p_e ) ){
							l_conf.c_zoom *= l_zoom;
							if( ConfigDiag.cs_TheConfigDiag.c_auto_depth == true )							
							l_conf.c_depth *= 1.2;

						}
						else {							
							l_conf.c_zoom /= l_zoom;
							if( ConfigDiag.cs_TheConfigDiag.c_auto_depth == true )							
								l_conf.c_depth /= 1.2;

						}
						
						FrameFractal l_frame = new FrameFractal( l_conf, c_frame.c_tcol );
						PPAppli.ThePPAppli.addChild(l_frame);
					}
				else
					{
						c_frame.c_conf.c_x = l_x;
						c_frame.c_conf.c_y = l_y;	
						

						if( SwingUtilities.isLeftMouseButton( p_e ) ){
							c_frame.c_conf.c_zoom *= l_zoom;
							if( ConfigDiag.cs_TheConfigDiag.c_auto_depth == true )
								c_frame.c_conf.c_depth *= 1.2;
						}
						else {
							c_frame.c_conf.c_zoom /= l_zoom;
							if( ConfigDiag.cs_TheConfigDiag.c_auto_depth == true )
								c_frame.c_conf.c_depth /= 1.2;
						}
						
						c_frame.calcul(false);
					}
			}
					
				/*
				if( c_popmenu == null )
					{
						c_popmenu = new JPopupMenu();
						c_popmenu.add( new JMenuItem( " item1 " ));
						c_popmenu.add( new JMenuItem( " item2 " ));
						c_popmenu.add( new JMenuItem( " item3 " ));
					}
				
				//				if( p_e.isPopupTrigger() )
					{
					c_popmenu.show( p_e.getComponent(),
													p_e.getX(),
													p_e.getY() );
				}
 */
		System.out.println( "mouse clicked" );		
	}
	//-------------------------------------
	public void mouseDragged( MouseEvent p_e ){
		
	}

	//-------------------------------------
	public void mouseMoved( MouseEvent p_e ){
		double l_x  = getInternalX(p_e.getX());
		double l_y  = getInternalY(p_e.getY());
		ConfigDiag.cs_TheConfigDiag.setPos( l_x, l_y );
		((JFractAll)PPAppli.ThePPAppli).c_pos_X.setDouble(l_x);
		((JFractAll)PPAppli.ThePPAppli).c_pos_Y.setDouble(l_y);
	}

}

//***********************************

