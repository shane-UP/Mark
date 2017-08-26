package packUI;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.event.MouseInputAdapter;
import javax.swing.event.MouseInputListener;

public class PanelMid extends JPanel{

	 
	String bg = null;
	public ImageIcon icon; 
	public static int ImgWidth;
	public static int ImgHeight;
	
	
	
	public PanelMid(){
		init();
	}
	
	 
	 public void paint(Graphics g) {
	  super.paint(g);
	  g.setColor(Color.CYAN);
	  
	  Graphics2D g2d = (Graphics2D)g;
	  g2d.setStroke(new BasicStroke(3f));
	  
	  
	  
	  for (Rectangle rect : MyFrame.list) {
	//   g.fillRect(rect.x, rect.y, rect.width, rect.height);
		  g.drawRect(rect.x, rect.y, rect.width, rect.height);
		  
	  }
	  
	  if (MyFrame.current != null) {
	   g.drawRect(MyFrame.current.x, MyFrame.current.y, MyFrame.current.width, MyFrame.current.height);
	   
	  }
	 }
	public void openRe(String bg){
		init();
		this.bg = bg;
		if(this.bg == null) 
			System.out.println("no such picture");
		else{
			this.repaint();
		}
	}
	
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		if(bg!=null)
		{
	//		System.out.println("paintComponent(Graphics g");
			icon = new ImageIcon(bg);
			ImgWidth = icon.getIconWidth();
			ImgHeight = icon.getIconHeight();
			g.drawImage(icon.getImage(), (ATP.midW-ImgWidth)/2,(ATP.midTopH-ImgHeight)/2 , this);
//			MyFrame.PPR.repaint();
		}
	}

	
	public void init(){
		this.setBackground(Color.WHITE);
		this.setBounds(ATP.leftW, 0, ATP.midW, ATP.midTopH);
		this.setOpaque(false);
		this.setVisible(true);
	}
}

