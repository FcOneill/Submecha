package util;

//Finn O'Neill 17367986

import java.awt.event.*;
import javax.swing.*;


public class MouseController implements MouseListener {

    int X = 0;
    int Y = 0;; 

    public MouseController(){
    }

    public MouseController(JFrame frame){
        frame.addMouseListener(this);
    }

    public void mouseClicked(MouseEvent e){
        X  = e.getX();
        Y = e.getY();
    };

    
    public void mousePressed(MouseEvent e){};

    
    public void mouseReleased(MouseEvent e){};

  
    public void mouseEntered(MouseEvent e){};

   
    public void mouseExited(MouseEvent e){};

    public int getX(){return X;}
	
	public void setX(int x){
		X = x;
	}

    public int getY(){return Y;}
	
	public void setY(int y){
		Y = y;
	}

}
