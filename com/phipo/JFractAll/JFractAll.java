
package phipo.JFractAll;

import javax.swing.JDesktopPane;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JMenuBar;
import javax.swing.JFrame;

import java.awt.event.*;
import java.awt.*;
import javax.swing.event.*;
import javax.swing.JToolBar;
import javax.swing.JButton;

import javax.swing.JOptionPane;

import java.io.*;
import javax.swing.JFileChooser;
import javax.swing.filechooser.*;

//import com.sun.image.codec.jpeg.*;

import phipo.PhiLib.Interface.*;
 
//***********************************
public class JFractAll extends PPAppli{
	 
	TColor c_tcol = new TColor( "N&B", 256 );

	DialogPalette c_diagpal = null;
	ConfigDiag      c_conf=null;

	PPDoubleField c_field_zoom;
	PPDoubleField c_pos_X, c_pos_Y; 

	public static JFractAll TheJFractAll;
	//-------------------------------------
	public JFractAll() {
		super("JFractAll", true);

		createMenuBar();
		createToolBar();
		c_tcol.gray(256);

		new ConfigDiag();

		createFrameFractal();

		//		c_desktop.add(l_cd);
	}
	//-------------------------------------
	void createToolBar(){

		c_field_zoom = new PPDoubleField( "Zoom", 10, PPDoubleField.HORIZONTAL );
		c_field_zoom.addActionListener( this );
		c_toolbar.add( c_field_zoom );
		c_pos_X = new PPDoubleField( "X", 0.0, PPDoubleField.FLOW_LAYOUT );
		c_pos_X.setEditable( false );
		c_toolbar.add( c_pos_X );
		c_pos_Y = new PPDoubleField( "Y", 0.0, PPDoubleField.FLOW_LAYOUT );
		c_pos_Y.setEditable( false );
		c_toolbar.add( c_pos_Y );
	
	}
	//-------------------------------------
	protected void createMenuBar() {

		//========
		JMenu l_menu_file = new JMenu("File");
		addItem( l_menu_file, "New mandelbrot" );
		addItem( l_menu_file, "Save image as JPEG");
		addItem( l_menu_file, "View info window");
		addItem( l_menu_file, "Save config");
		addItem( l_menu_file, "Load config");
		c_menubar.add(l_menu_file);


		//========
		JMenu l_menu_color = new JMenu("Color");
		addItem( l_menu_color, "Edit current palet" );
		addItem( l_menu_color, "Save current palet" );
		addItem( l_menu_color, "Load current palet" );

		JMenu l_menu_predef = new JMenu("Palette predefini");
		l_menu_color.add( l_menu_predef );
		addItem( l_menu_predef, "B&W");
		addItem( l_menu_predef, "Rainbow");
	 
		c_menubar.add(l_menu_color);
		//========
		JMenu l_menu_help = new JMenu("Help");
		addAbout( l_menu_help);
		c_menubar.add(l_menu_help);

	}
	//---------------------
	public void itemStateChanged(ItemEvent p_e ){

		System.out.println( "itemStateChanged" );
	}
	//---------------------
	public void actionPerformed( ActionEvent p_e ){
		
		if(  p_e.getActionCommand().equals( "B&W")  )
			{		
				c_tcol.gray(256);				
				ConfigDiag.cs_TheConfigDiag.c_current.c_tcol = c_tcol;
			}

			if(  p_e.getActionCommand().equals( "fffRainbow")  )
			{
				c_tcol.degrade( 0, 50,   
												0,  0,   0,   
												255, 0, 255 );
				
				c_tcol.degrade( 50, 100,  
												255,  0,   255,  
												0,  0,   255 );

				c_tcol.degrade( 100, 150,  
												0,    0,   255,  
												0,  255,   255 );

				c_tcol.degrade( 150, 200,  
												0,  255,   255,  
												255,  255,   0 );

				c_tcol.degrade( 200,  255,  
												0, 255, 255,  
												255,  0,  0 );
				ConfigDiag.cs_TheConfigDiag.c_current.c_tcol = c_tcol;
			}
	
		if(  p_e.getActionCommand().equals( "Rainbow")  )
			{
				c_tcol.degrade( 0, 50,   
												0,  0,   0,   
												0,  0, 255 );
				
				c_tcol.degrade( 50, 100,  
												0,  0,     255,  
												0,  255,   255 );

				c_tcol.degrade( 100, 150,  
												0,    255,   255,  
												255,  255,   0 );

				c_tcol.degrade( 150, 200,  
												255,  255,   0,  
												255,  0,   0 );

				c_tcol.degrade( 200,  255,  
												255,  0,   0,  
												255,  255,   255 );
				ConfigDiag.cs_TheConfigDiag.c_current.c_tcol = c_tcol;
			}

		if(  p_e.getActionCommand().equals( "New mandelbrot")  )
			{
				createFrameFractal();
			}
		
		if(  p_e.getActionCommand().equals( "View info window")  )
			{				
				if( c_conf == null )
					addChild( (c_conf=ConfigDiag.cs_TheConfigDiag) );

				openChild( c_conf );
			}

		if(  p_e.getActionCommand().equals( "Edit current palet")  )
			{
				if( c_diagpal == null )
					addChild((c_diagpal = new DialogPalette(c_tcol )));

				openChild( c_diagpal );
				c_diagpal.c_canvas_palette.repaintAll();
			}


		if(  p_e.getActionCommand().equals( "Save current palet")  )
			{
				saveCurrentPalet();
			}

		if(  p_e.getActionCommand().equals( "Load current palet"))
			{
				loadCurrentPalet();

				if( c_diagpal != null )
					c_diagpal.c_canvas_palette.repaintAll();
			}

		if(  p_e.getActionCommand().equals( "Save image as JPEG")  )
			{
				saveJPEG();
			}

		if(  p_e.getActionCommand().equals( "Save config")  )
			{
				saveCurrentConfig();
			}

		if(  p_e.getActionCommand().equals( "Load config"))
			{
				loadCurrentConfig();
			}

		if(  p_e.getActionCommand().equals( "About")){
			new PPAbout( "JFractAll", 
									 "1.0.0", 
									 "Copyright (C) 2001 Philippe Yves Poupon",
									 "",
									 "ppoupon@free.fr" );

			}
	}
	//-------------------------------------
	void saveJPEG( ){
		if( ConfigDiag.cs_TheConfigDiag.c_current == null )
			return;

		File l_current = new File( "." );
		
		final JFileChooser l_fc = new JFileChooser(l_current);


		int l_ret = l_fc.showSaveDialog( this);
		
		if( l_ret == JFileChooser.APPROVE_OPTION){
			{
				File l_file = l_fc.getSelectedFile();

				try {
					DataOutputStream l_out = new DataOutputStream( new FileOutputStream( l_file ) );
					//					JPEGImageEncoder l_encoder = JPEGCodec.createJPEGEncoder( l_out );
					//				JPEGEncodeParam  l_param = l_encoder.getDefaultJPEGEncodeParam( ConfigDiag.cs_TheConfigDiag.c_current.c_canvas.getBufferedImage() );
					
					//				l_param.setQuality( 1.0f, false );
					//				l_encoder.setJPEGEncodeParam( l_param );
					//				l_encoder.encode( ConfigDiag.cs_TheConfigDiag.c_current.c_canvas.getBufferedImage() );
					
					l_out.close();
				}
				catch( IOException err){
					JOptionPane.showMessageDialog( this,
																				 "Erreur d'ecriture de " + l_file.getName(),
																				 "Erreur",
																				 JOptionPane.ERROR_MESSAGE );
				}
			}
		}
	}
	//-------------------------------------
	void loadCurrentPalet(){
		try{
			File l_current = new File( "." );
			JFileChooser l_fc = new JFileChooser(l_current);
			
			PPFileFilter l_filter = new PPFileFilter();
			l_filter.addExtension( "pal" );
			l_filter.setDescription( "Palet of color for JFractAll");
			l_fc.setFileFilter( l_filter );
			
			int l_ret = l_fc.showOpenDialog( this);
			
			if( l_ret == JFileChooser.APPROVE_OPTION){
				File l_file = l_fc.getSelectedFile();
				ObjectInputStream l_in = new ObjectInputStream( new FileInputStream( l_file ) );
				TColor l_pal = (TColor)l_in.readObject();
				
				ConfigDiag.cs_TheConfigDiag.c_current.c_tcol = c_tcol = l_pal;
			}
		}
		catch( IOException err){
			JOptionPane.showMessageDialog( this,
																		 "Erreur de lecture de fichier palette",
																		 "Erreur",
																		 JOptionPane.ERROR_MESSAGE );
		}
		catch( ClassNotFoundException err){
			JOptionPane.showMessageDialog( this,
																		 "Erreur le fichier ne contient pas d'objet palette",
																		 "Erreur",
																		 JOptionPane.ERROR_MESSAGE );
		}
	}
		//-------------------------------------
	void saveCurrentPalet(){
			try{
				File l_current = new File( "." );
				
				final JFileChooser l_fc = new JFileChooser(l_current);
				int l_ret = l_fc.showSaveDialog( this);
				
				if( l_ret == JFileChooser.APPROVE_OPTION){
					{
						File l_file = l_fc.getSelectedFile();
						ObjectOutputStream l_out = new ObjectOutputStream( new FileOutputStream(l_file));
						l_out.writeObject( ConfigDiag.cs_TheConfigDiag.c_current.c_tcol );
						l_out.close();
					}
				}
			}
			catch( IOException ffe ){
				System.out.println( ffe );
				// faire une dialog d erreur!
					JOptionPane.showMessageDialog( this,
																				 "Erreur d ecriture",
																				 "Erreur",
																				 JOptionPane.ERROR_MESSAGE );
				}
	}
	//-------------------------------------
	void loadCurrentConfig(){
		try{
			File l_current = new File( "." );
			JFileChooser l_fc = new JFileChooser(l_current);
			
			PPFileFilter l_filter = new PPFileFilter();
			l_filter.addExtension( "cfg" );
			l_filter.setDescription( "Palet of color for JFractAll");
			l_fc.setFileFilter( l_filter );
			
			int l_ret = l_fc.showOpenDialog( this);
			
			if( l_ret == JFileChooser.APPROVE_OPTION){
				File l_file = l_fc.getSelectedFile();
				ObjectInputStream l_in = new ObjectInputStream( new FileInputStream( l_file ) );
				FractalConfig l_conf= (FractalConfig)l_in.readObject();
				
				ConfigDiag.cs_TheConfigDiag.c_current.c_conf =l_conf;
				ConfigDiag.cs_TheConfigDiag.c_current.calcul(false);
			}
		}
		catch( IOException err){
			JOptionPane.showMessageDialog( this,
																		 "Erreur de lecture de fichier config",
																		 "Erreur",
																		 JOptionPane.ERROR_MESSAGE );
		}
		catch( ClassNotFoundException err){
			JOptionPane.showMessageDialog( this,
																		 "Erreur le fichier ne contient pas d'objet config",
																		 "Erreur",
																		 JOptionPane.ERROR_MESSAGE );
		}
	}
	//-------------------------------------
	void saveCurrentConfig(){
		try{
			File l_current = new File( "." );
			
				final JFileChooser l_fc = new JFileChooser(l_current);
				int l_ret = l_fc.showSaveDialog( this);
				
				if( l_ret == JFileChooser.APPROVE_OPTION){
					{
						File l_file = l_fc.getSelectedFile();
						ObjectOutputStream l_out = new ObjectOutputStream( new FileOutputStream(l_file));
						l_out.writeObject( ConfigDiag.cs_TheConfigDiag.c_current.c_conf );
						l_out.close();
					}
				}
		}

		catch( IOException ffe ){
			System.out.println( ffe );
			// faire une dialog d erreur!
			JOptionPane.showMessageDialog( this,
																		 "Erreur d ecriture",
																		 "Erreur",
																		 JOptionPane.ERROR_MESSAGE );
		}
		
	}

	//-------------------------------------
	protected void createFrameFractal() {
		//		l_tcol.degrade(256, 0, 0, 255, 255, 0, 0);

		FrameFractal frame = new FrameFractal( new  FractalConfig(), c_tcol );
		frame.addInternalFrameListener(frame);
		addChild(frame);


		try {
			frame.setSelected(true);
		} catch (java.beans.PropertyVetoException e) {}
	}
	//-------------------------------------
	public static void main(String[] args) {
		new JFractAll();
		ThePPAppli.setVisible(true);
	}
}
//***********************************
