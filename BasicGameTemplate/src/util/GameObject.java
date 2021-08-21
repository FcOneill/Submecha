package util;

import java.awt.Rectangle;
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
public class GameObject {
	
	private Point3f centre= new Point3f(0,0,0);			// Centre of object, using 3D as objects may be scaled  
	private Point3f front = new Point3f(0,0,0);
	private double angle = 0;
	private float velX = 0;
	private float velY = 0; 
	private int width=10;
	private int height=10;
	private boolean hasTextured=false;
	private String textureLocation; 
	private String blanktexture="res/blankSprite.png";
	private Rectangle bounds;

	//Finn O'Neill 17367986
	
	public GameObject() {  	
		
	}
	
    public GameObject(String textureLocation,int width,int height,Point3f centre) { 
    	hasTextured=true;
    	this.textureLocation=textureLocation;
    	this.width=width;
		this.height=height;
		this.centre =centre;
		front = new Point3f(centre.getX()+32, centre.getY(), centre.getZ());
		this.angle = 0;
		bounds = new Rectangle(0,0, width, height);
	}

	public float getVelX(){
		return velX;
	}

	public void setVelX(float velX){
		this.velX = velX;
	}

	public float getVelY(){
		return velY;
	}

	public void setVelY(float velY){
		this.velY = velY;
	}


	public Point3f getCentre() {
		return centre;
	}

	public void setCentre(Point3f centre) {
		this.centre = centre;
		
		//make sure to put boundaries on the gameObject 
	 
	}

	public double getAngle() {
		return angle;
	}

	public void setAngle(double angle) {
		this.angle = angle;	 
	}

	public Point3f getFront() {
		return front;
	}

	public void setFront(Point3f front) {
		this.front = front;	 
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public Rectangle getBounds() {
		return this.bounds;
	}

	public void setBounds(Rectangle bounds) {
		this.bounds = bounds;
	}

	public String getTexture() {
		if(hasTextured) 
			{
			return textureLocation;
			}
		 
		return blanktexture; 
	}
  
}