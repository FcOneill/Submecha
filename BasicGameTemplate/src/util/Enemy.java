package util;
import java.awt.Rectangle;

//Finn O'Neill 17367986

public class Enemy extends GameObject{

    private boolean scanned = false; 
    long scanTime = 0;
    private long torpCooldown = 0;
    private boolean onCooldown = true;

    public Enemy() {
    }

    public Enemy(String textureLocation,int width,int height,Point3f centre) {
        super(textureLocation, width,height, centre);
        Rectangle temp = new Rectangle((int)centre.getX()-75, (int)centre.getY()-20, 150, 40);
        super.setBounds(temp);
    }

    public Boolean getScanned() {
        return this.scanned;
    }

    public void setScanned(Boolean scanned) {
        this.scanned = scanned;
        this.scanTime = System.currentTimeMillis();
    }

    public long getScanTime() {
        return this.scanTime;
    }

    public void setScanTime(long scanTime) {
        this.scanTime = scanTime;
    }

    public long getTorpCooldown() {
        return this.torpCooldown;
    }

    public void setTorpCooldown(long torpCooldown) {
        this.torpCooldown = torpCooldown;
    }

    public boolean isOnCooldown() {
        return this.onCooldown;
    }

    public boolean getOnCooldown() {
        return this.onCooldown;
    }

    public void setOnCooldown(boolean onCooldown) {
        this.onCooldown = onCooldown;
    }
}
