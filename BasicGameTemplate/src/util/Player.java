package util;

//Finn O'Neill 17367986

import java.awt.Rectangle;

public class Player extends GameObject{
    
    private long scanCooldown = 0;
    private long torpCooldown = 0;


    public Player() {
    }

    public Player(String textureLocation,int width,int height,Point3f centre) {
        super(textureLocation, width, height, centre);
        Rectangle temp = new Rectangle((int)centre.getX()-75, (int)centre.getY()-20, 150, 40);
        super.setBounds(temp);
        scanCooldown = 0;
        torpCooldown = 0;
    }

    public long getScanCooldown() {
        return this.scanCooldown;
    }

    public void setScanCooldown(long scanCooldown) {
        this.scanCooldown = scanCooldown;
    }

    public long getTorpCooldown() {
        return this.torpCooldown;
    }

    public void setTorpCooldown(long torpCooldown) {
        this.torpCooldown = torpCooldown;
    }
   
   
}
