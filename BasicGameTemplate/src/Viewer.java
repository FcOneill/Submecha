import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.LayoutManager;
import java.awt.Rectangle;
import java.awt.TexturePaint;
import java.awt.Paint;
import java.awt.geom.AffineTransform;
import java.awt.geom.RectangularShape;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;
import java.awt.geom.Area;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.awt.RenderingHints;
import java.awt.RadialGradientPaint;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.Timer;

import util.Enemy;
import util.GameObject;
import util.Point3f;
import util.Torpedo;

//Finn O'Neill 17367986


/*
 * Created by Abraham Campbell on 15/01/2020.
 *   Copyright (c) 2020  Abraham Campbell

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
   
   (MIT LICENSE ) e.g do what you want with this :-) 
 
 * Credits: Kelly Charles (2020)
 */ 
public class Viewer extends JPanel {
	private long CurrentAnimationTime= 0; 
	
	Model gameworld =new Model(); 
	 
	public Viewer(Model World) {
		this.gameworld=World;
		// TODO Auto-generated constructor stub
	}

	public Viewer(LayoutManager layout) {
		super(layout);
		// TODO Auto-generated constructor stub
	}

	public Viewer(boolean isDoubleBuffered) {
		super(isDoubleBuffered);
		// TODO Auto-generated constructor stub
	}

	public Viewer(LayoutManager layout, boolean isDoubleBuffered) {
		super(layout, isDoubleBuffered);
		// TODO Auto-generated constructor stub
	}

	public void updateview() {
		
		this.repaint();
		// TODO Auto-generated method stub
		
	}
	
	
	public void paintComponent(Graphics g) {
		
		super.paintComponent(g);
		CurrentAnimationTime++; // runs animation time step 

		//Draw player Game Object 
		int x = (int) gameworld.getPlayer().getCentre().getX();
		int y = (int) gameworld.getPlayer().getCentre().getY();
		int width = (int) gameworld.getPlayer().getWidth();
		int height = (int) gameworld.getPlayer().getHeight();
		String texture = gameworld.getPlayer().getTexture();
		
		drawBackground(g);
		
		//Draw player
		drawPlayer(x, y, width, height, texture, g);

		
		  
		//Draw playerTorps 
		// change back 
		gameworld.getplayerTorps().forEach((temp) -> 
		{ 
			drawplayerTorp((int) temp.getCentre().getX(), (int) temp.getCentre().getY(), (int) temp.getWidth(), (int) temp.getHeight(), temp.getTexture(),g, temp.getAngle());	 
			
		}); 

		
		//Draw Enemies   
		gameworld.getEnemies().forEach((temp) -> 
		{
			drawEnemies((int) temp.getCentre().getX()-75, (int) temp.getCentre().getY()-75, (int) temp.getWidth(), (int) temp.getHeight(), temp.getTexture(),g);	 
		 
	    }); 

		gameworld.getenemyTorps().forEach((temp) -> 
		{ 
			drawEnemyTorp((int) temp.getCentre().getX(), (int) temp.getCentre().getY(), (int) temp.getWidth(), (int) temp.getHeight(), temp.getTexture(),g, temp.getAngle());	 
			

		}); 
		if(gameworld.getLevel() == 2 || gameworld.getLevel() == 3){
			drawForeground(g);
		}
			
		//Some code adapted from https://stackoverflow.com/questions/15488853/java-mouse-flashlight-effect
		Graphics2D g2d = (Graphics2D) g.create();
		Paint paint = Color.BLACK;
		Rectangle rect = new Rectangle(0, 0 , 1900, 1000);	
		Area a = new Area(rect);
		paint = new RadialGradientPaint( x, y, 250, new float[]{0, 1f}, new Color[]{new Color(0, 0, 0, 0), new Color(0, 0, 0, 255)});  
		for (Torpedo T : gameworld.getplayerTorps()) {
			Ellipse2D circle = new Ellipse2D.Double(T.getCentre().getX()-29, T.getCentre().getY()-32, 75, 75);
			a.subtract(new Area(circle));
			g2d.clip(a);	
		}
		g2d.setPaint(paint);
		g2d.fillRect(0, 0, getWidth(), getHeight());
        g2d.dispose();

		Graphics2D g2d2 = (Graphics2D) g.create();
		for (Enemy E : gameworld.getEnemies()) {
			if(E.getScanned()){
				if(System.currentTimeMillis() - E.getScanTime() < 600 || System.currentTimeMillis() - E.getScanTime() > 2400 ){
					Paint ping = Color.MAGENTA;
					ping = new RadialGradientPaint( E.getCentre().getX(), E.getCentre().getY(), 30, new float[]{0, 1}, new Color[]{Color.MAGENTA, new Color(0, 0, 0, 0)});
					g2d2.setPaint(ping);
					g2d2.fillRect(0, 0, getWidth(), getHeight());	
				}
				if(System.currentTimeMillis() - E.getScanTime() > 1200 && System.currentTimeMillis() - E.getScanTime() < 1800 ){
					Paint ping = Color.MAGENTA;
					ping = new RadialGradientPaint( E.getCentre().getX(), E.getCentre().getY(), 30, new float[]{0, 1}, new Color[]{Color.MAGENTA, new Color(0, 0, 0, 0)});
					g2d2.setPaint(ping);
					g2d2.fillRect(0, 0, getWidth(), getHeight());	
				}	
			}
		}
		g2d2.dispose();

		Graphics2D g2d3 = (Graphics2D) g.create();
		for (Torpedo t : gameworld.getenemyTorps()) {
				if(System.currentTimeMillis()%1000 < 500){
					Paint ping = Color.RED;
					ping = new RadialGradientPaint( t.getCentre().getX(), t.getCentre().getY(), 20, new float[]{0, 1}, new Color[]{new Color(255, 0, 0, 255), new Color(0, 0, 0, 0)});
					g2d3.setPaint(ping);
					g2d3.fillRect(0, 0, getWidth(), getHeight());	
				}
		}

		drawScanIcon(g);
		drawFireIcon(g);



	}
	
	private void drawEnemies(int x, int y, int width, int height, String texture, Graphics g) {
		File TextureToLoad = new File(texture);  //should work okay on OSX and Linux but check if you have issues depending your eclipse install or if your running this without an IDE 
		try {
			Image myImage = ImageIO.read(TextureToLoad);
			//The spirte is 32x32 pixel wide and 4 of them are placed together so we need to grab a different one each time 
			//remember your training :-) computer science everything starts at 0 so 32 pixels gets us to 31  
			int currentPositionInAnimation= ((int) (CurrentAnimationTime%4 )*32); //slows down animation so every 10 frames we get another frame so every 100ms 
			g.drawImage(myImage, x,y, x+width, y+height, 0  , 0, 64, 64, null); 
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
	}

	private void drawBackground(Graphics g)
	{
		File TextureToLoad = new File("res/underwater.jpg");  //should work okay on OSX and Linux but check if you have issues depending your eclipse install or if your running this without an IDE 
		try {
			Image myImage = ImageIO.read(TextureToLoad); 
			 g.drawImage(myImage, 0,0, 1900, 1000, 0 , 0, 3107, 1378, null); 
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void drawplayerTorp(int x, int y, int width, int height, String texture,Graphics g, double angle)
	{
		File TextureToLoad = new File(texture);  //should work okay on OSX and Linux but check if you have issues depending your eclipse install or if your running this without an IDE 
		try {
			//64 by 128 
			BufferedImage myImage = ImageIO.read(TextureToLoad); 
			int currentPositionInAnimation= ((int) ((CurrentAnimationTime%40)/10))*64;
			Graphics2D g2d = (Graphics2D)g;
			g2d.setRenderingHint(
				RenderingHints.KEY_RENDERING, 
				RenderingHints.VALUE_RENDER_QUALITY);
	
			
			AffineTransform oldAT = g2d.getTransform();
			g2d.translate(x,y);
			g2d.rotate(angle);
			g2d.translate(-x-25, -y-25);
			g2d.drawImage(myImage, x,y, x+width, y+height, currentPositionInAnimation  , 0, currentPositionInAnimation+63, 64, null);
			g2d.setTransform(oldAT);

			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void drawEnemyTorp(int x, int y, int width, int height, String texture,Graphics g, double angle)
	{
		File TextureToLoad = new File("res/EnemyTorp.png");  //should work okay on OSX and Linux but check if you have issues depending your eclipse install or if your running this without an IDE 
		try {
			BufferedImage myImage = ImageIO.read(TextureToLoad); 
			Graphics2D g2d = (Graphics2D)g;
			g2d.setRenderingHint(
				RenderingHints.KEY_RENDERING, 
				RenderingHints.VALUE_RENDER_QUALITY);
	
			AffineTransform oldAT = g2d.getTransform();
			g2d.translate(x,y);
			g2d.rotate(angle);
			g2d.translate(-x-25, -y-25);
			g2d.drawImage(myImage, x,y, x+width, y+height,  0  , 0, 64, 64, null);
			g2d.setTransform(oldAT);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void drawPlayer(int x, int y, int width, int height, String texture,Graphics g) { 
		File TextureToLoad = new File(texture);  //should work okay on OSX and Linux but check if you have issues depending your eclipse install or if your running this without an IDE 
		try {
			
			//Rotation code adapted from stackoverflow thread
			//https://stackoverflow.com/questions/26607930/java-rotate-image-towards-mouse-position
			BufferedImage myImage = ImageIO.read(TextureToLoad); 
			int currentPositionInAnimation= ((int) ((CurrentAnimationTime%40)/10))*64;
			Graphics2D g2d = (Graphics2D)g;
			g2d.setRenderingHint(
				RenderingHints.KEY_RENDERING, 
				RenderingHints.VALUE_RENDER_QUALITY);
	
			
			AffineTransform oldAT = g2d.getTransform();
			g2d.translate(x,y);
			g2d.rotate(gameworld.getPlayer().getAngle());
			g2d.translate(-x-75, -y-75);
			g2d.drawImage(myImage, x,y, x+width, y+height, currentPositionInAnimation  , 0, currentPositionInAnimation+63, 64, null);
			g2d.setTransform(oldAT);
			//g.setColor(Color.RED);
			//g.fillRect(x-5, y-5, 10, 10);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	} 

	private void drawScanIcon(Graphics g) { 
		File TextureToLoad = new File("res/radar.png");  //should work okay on OSX and Linux but check if you have issues depending your eclipse install or if your running this without an IDE 
		try {
			Image myImage = ImageIO.read(TextureToLoad); 
			g.drawImage(myImage, 50, 825, 150, 925, 0 , 0, 452, 452, null); 
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void drawFireIcon(Graphics g) { 
		File TextureToLoad = new File("res/fire.png");  //should work okay on OSX and Linux but check if you have issues depending your eclipse install or if your running this without an IDE 
		try {
			Image myImage = ImageIO.read(TextureToLoad); 
			g.drawImage(myImage, 1690, 845, 1850, 925, 0 , 0, 159, 80, null); 
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void drawForeground(Graphics g) {
		File TextureToLoad = new File("res/Rocks.png");  //should work okay on OSX and Linux but check if you have issues depending your eclipse install or if your running this without an IDE 
		try {
			Image myImage = ImageIO.read(TextureToLoad); 
			g.drawImage(myImage, 0, 772, 1900, 1000, 0 , 0, 1900, 128, null); 
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
