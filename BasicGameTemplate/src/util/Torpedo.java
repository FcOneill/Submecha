package util;

//Finn O'Neill 17367986

import java.awt.Rectangle;

public class Torpedo extends GameObject{

    private Vector3f vector = new Vector3f();

    public Torpedo() {
    }

    public Torpedo(String textureLocation,int width,int height, Point3f centre, Vector3f vector) {
        super(textureLocation, width, height, centre);
        this.vector = vector;
        Rectangle temp = new Rectangle((int)centre.getX()-25, (int)centre.getY()+3, 64, 8);
        super.setBounds(temp);
    }

    public Vector3f getVector() {
        return this.vector;
    }

    public void setVector(Vector3f vector) {
        this.vector = vector;
    }

    
    @Override
    public String toString() {
        return "{" +
            " vector='" + getVector().getX() +", " + getVector().getY() +", " + getVector().getZ() + " " + "'" +
            "}";
    }
    
    
}
