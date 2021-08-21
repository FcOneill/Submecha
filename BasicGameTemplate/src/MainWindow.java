
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.event.*;
import javax.swing.*;

import org.w3c.dom.events.MouseEvent;

import java.awt.event.MouseAdapter;

import util.MouseController;
import util.Sound;
import util.UnitTests;


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
 */

public class MainWindow {

	private static JFrame frame = new JFrame("Submecha");
	private static Model gameworld = new Model();
	private static Viewer canvas = new Viewer(gameworld);
	private KeyListener Controller = new Controller();
	private MouseListener ControllerM = new Controller();
	private static int TargetFPS = 100;
	private static boolean startGame = false;
	private JLabel BackgroundImageForStartMenu;
	private static boolean playerDead = false;
	private static boolean win = false;


	public MainWindow() throws UnsupportedAudioFileException, LineUnavailableException {
			
		//TODO set default aspect ratio for window
	    	frame.setSize(1900, 1040); 
	      	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        frame.setLayout(null);
	        frame.add(canvas);  
	        canvas.setBounds(0, 0, 1900, 1000); 
			canvas.setBackground(new Color(255,255,255)); 
		    canvas.setVisible(false);
			JFrame info = new JFrame();
			info.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			info.setBackground(Color.black);
			info.setBounds(400, 90, 800, 840);
			info.setLayout(null);

			File monitorFile = new File("res/monitor.png");  //should work okay on OSX and Linux but check if you have issues depending your eclipse install or if your running this without an IDE 
			try {
				 BufferedImage myPicture = ImageIO.read(monitorFile);
				 JLabel monitor = new JLabel(new ImageIcon(myPicture));
				 monitor.setBounds(0, 0, 800, 784);
				info.add(monitor); 
			}  catch (IOException e) { 
				e.printStackTrace();
			}   

		    JButton ok = new JButton("OK");
			ok.addActionListener(new ActionListener(){
				@Override
				public void actionPerformed(ActionEvent e) {
					info.setVisible(false);
					canvas.addKeyListener(Controller);    //adding the controller to the Canvas  
					canvas.addMouseListener(ControllerM);
	            	canvas.requestFocusInWindow();   // making sure that the Canvas is in focus so keyboard input will be taking in .
					startGame=true;
					
				}
			});

			ok.setBounds(350,690, 100, 50);
		    
	        JButton startMenuButton = new JButton();  // start button 
	        startMenuButton.addActionListener(new ActionListener(){ 
				@Override
				public void actionPerformed(ActionEvent e) { 
					startMenuButton.setVisible(false);
					BackgroundImageForStartMenu.setVisible(false); 
					canvas.setVisible(true); 
					info.setVisible(true);
					
					info.add(ok);

					frame.add(info);
					Sound menu = new Sound("res/MenuButton.wav");
					menu.play();
					
				}
			});  
			Icon i=new ImageIcon("res/start.png");
			
			startMenuButton.setIcon(i);
	        startMenuButton.setBounds(1400, 750, 400, 120); 

			
			
	        
	        //loading background image 
	        File BackroundToLoad = new File("res/Submecha.png");  //should work okay on OSX and Linux but check if you have issues depending your eclipse install or if your running this without an IDE 
			try {
				 BufferedImage myPicture = ImageIO.read(BackroundToLoad);
				 BackgroundImageForStartMenu = new JLabel(new ImageIcon(myPicture));
				 BackgroundImageForStartMenu.setBounds(0, 0, 1900, 1000);
				frame.add(BackgroundImageForStartMenu); 
			}  catch (IOException e) { 
				e.printStackTrace();
			}   
	        frame.add(startMenuButton);  
	       	frame.setVisible(true);   
			   
			
	}

	public static void main(String[] args) throws UnsupportedAudioFileException, LineUnavailableException {
		MainWindow hello = new MainWindow();  //sets up environment 
		//TODO look at using threads instead
		Sound music = new Sound("res/OST.wav");
		music.loop();

		boolean running = true; 
		
		while(running){  
			//not nice but remember we do just want to keep looping till the end.  // this could be replaced by a thread but again we want to keep things simple 
			//swing has timer class to help us time this but I'm writing my own, you can of course use the timer, but I want to set FPS and display it 
			int TimeBetweenFrames =  1000 / TargetFPS;
			long FrameCheck = System.currentTimeMillis() + (long) TimeBetweenFrames; 
			
			//wait till next time step 
			while (FrameCheck > System.currentTimeMillis()){} 

			if(gameworld.getLevel() == 4){
				win = true;
				music.stop();
				running = false;
			}

			if(startGame)
				 {
				 	gameloop();
				 }
			
			if (gameworld.isDead()){
				playerDead = true;
				music.stop();
				running = false;
			}
			//UNIT test to see if framerate matches 
			UnitTests.CheckFrameRate(System.currentTimeMillis(),FrameCheck, TargetFPS); 	  
		}		
		if(playerDead){
			endScreen();
		}
		if(win){
			winScreen();
		}
	} 

	public static void endScreen(){
		canvas.setVisible(false);
		File dead = new File("res/dead.png");  //should work okay on OSX and Linux but check if you have issues depending your eclipse install or if your running this without an IDE 
			try {
				 BufferedImage myPicture = ImageIO.read(dead);
				 JLabel deadLabel = new JLabel(new ImageIcon(myPicture));
				 deadLabel.setBounds(0, 0, 1900, 1000);
				 frame.add(deadLabel); 
				
			}  catch (IOException e) { 
				e.printStackTrace();

			}
	}

	public static void winScreen(){
		canvas.setVisible(false);
		File file = new File("res/winner.png");  //should work okay on OSX and Linux but check if you have issues depending your eclipse install or if your running this without an IDE 
			try {
				 BufferedImage myPicture = ImageIO.read(file);
				 JLabel win = new JLabel(new ImageIcon(myPicture));
				 win.setBounds(0, 0, 1900, 1000);
				 frame.add(win); 
				
			}  catch (IOException e) { 
				e.printStackTrace();
			}
	}

	//Basic Model-View-Controller pattern 
	private static void gameloop() { 
		// controller input  will happen on its own thread 
		// So no need to call it explicitly 
		// model update   
		gameworld.gamelogic();
		// view update 
		canvas.updateview(); 
		// Both these calls could be setup as a thread but we want to simplify the game logic for you.  
		//score update  
		frame.setTitle("Submecha"); 
	}

}
