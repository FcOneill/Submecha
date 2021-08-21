import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.event.MouseInputListener;

import util.Point3f;

import java.awt.event.*;
import javax.swing.*;

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

//Singeton pattern
public class Controller implements KeyListener, MouseListener {
        
	private static boolean KeyWPressed= false;
	private static boolean KeyAPressed= false;
	private static boolean KeySPressed= false;
	private static boolean KeyDPressed= false;
	private static boolean KeyQPressed= false;
	private static boolean KeyShiftPressed= false;
	private static boolean KeyCtrlPressed= false;
	private static boolean KeySpacePressed= false;
	private static Point3f mousePoint;
	private static boolean mousePressed=false;

	private static final Controller instance = new Controller();
	   
	public Controller(){}
	 
	public static Controller getInstance(){
		return instance;
	}
	   
	@Override
	public void keyTyped(KeyEvent e){}

	@Override
	public void keyPressed(KeyEvent e) 
	{ 
		switch (e.getKeyCode()) 
		{
			case KeyEvent.VK_W:setKeyWPressed(true);break; 
			case KeyEvent.VK_A:setKeyAPressed(true);break;  
			case KeyEvent.VK_S:setKeySPressed(true);break;
			case KeyEvent.VK_D:setKeyDPressed(true);break;
			case KeyEvent.VK_Q:setKeyQPressed(true);break;
			case KeyEvent.VK_SHIFT:setKeyShiftPressed(true);break;
			case KeyEvent.VK_CONTROL:setKeyCtrlPressed(true);break;
			case KeyEvent.VK_SPACE:setKeySpacePressed(true);break;  
		    default:break;
		}  
	}

	@Override
	public void keyReleased(KeyEvent e) 
	{ 
		switch (e.getKeyCode()) 
		{
			case KeyEvent.VK_W:setKeyWPressed(false);break;
			case KeyEvent.VK_A:setKeyAPressed(false);break;  
			case KeyEvent.VK_S:setKeySPressed(false);break;
			case KeyEvent.VK_D:setKeyDPressed(false);break;
			case KeyEvent.VK_Q:setKeyQPressed(false);break;
			case KeyEvent.VK_SHIFT:setKeyShiftPressed(false);break;
			case KeyEvent.VK_CONTROL:setKeyCtrlPressed(false);break;
			case KeyEvent.VK_SPACE:setKeySpacePressed(false);break;   
		    default:break;
		} 
	}

	@Override
    public void mouseClicked(MouseEvent e){}

    @Override
    public void mousePressed(MouseEvent e) {
        mousePoint = new Point3f(e.getX(), e.getY(), 0);
        mousePressed= true;
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        mousePressed= false;
    }

    @Override
    public void mouseEntered(MouseEvent e){}

    @Override
    public void mouseExited(MouseEvent e){}

	public boolean isMousePressed(){return mousePressed;}
	
	public void setMousePressed(boolean click){
		mousePressed = click;
	}

	public Point3f getMousePoint(){return mousePoint;}
	
	public void setMousePoint(Point3f point){
		mousePoint = point;
	}

	public boolean isKeyWPressed(){return KeyWPressed;}
	
	public void setKeyWPressed(boolean keyWPressed){
		KeyWPressed = keyWPressed;
	}

	public boolean isKeyAPressed(){return KeyAPressed;}
	
	public void setKeyAPressed(boolean keyAPressed) {
		KeyAPressed = keyAPressed;
	}

	public boolean isKeySPressed(){return KeySPressed;}
	
	public void setKeySPressed(boolean keySPressed){
		KeySPressed = keySPressed;
	}

	public boolean isKeyDPressed(){return KeyDPressed;}
	
	public void setKeyDPressed(boolean keyDPressed){
		KeyDPressed = keyDPressed;
	}

	public boolean isKeyQPressed(){return KeyQPressed;}
	
	public void setKeyQPressed(boolean keyQPressed){
		KeyQPressed = keyQPressed;
	}

	public boolean isKeyShiftPressed(){return KeyShiftPressed;}
	
	public void setKeyShiftPressed(boolean keyShiftPressed){
		KeyShiftPressed = keyShiftPressed;
	}

	public boolean isKeyCtrlPressed(){return KeyCtrlPressed;}
	
	public void setKeyCtrlPressed(boolean keyCtrlPressed){
		KeyCtrlPressed = keyCtrlPressed;
	}

	public boolean isKeySpacePressed(){return KeySpacePressed;}
	
	public void setKeySpacePressed(boolean keySpacePressed){
		KeySpacePressed = keySpacePressed;
	}
}


