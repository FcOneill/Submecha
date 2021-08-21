package util;

//Finn O'Neill 17367986

import java.util.LinkedList;
import java.awt.Rectangle;

public class Terrain {

    LinkedList<Rectangle> terrain = new LinkedList<Rectangle>();
    
    public Terrain(){
        Rectangle temp = new Rectangle(0, 900, 30, 100);
        terrain.add(temp);
        temp = new Rectangle(30, 930, 30, 70);
        terrain.add(temp);
        temp = new Rectangle(60, 980, 325, 20);
        terrain.add(temp);
        temp = new Rectangle(385, 907, 73, 100);
        terrain.add(temp);
        temp = new Rectangle(458, 980, 112, 72);
        terrain.add(temp);
        temp = new Rectangle(570, 926, 60, 74);
        terrain.add(temp);
        temp = new Rectangle(630, 859, 90, 141);
        terrain.add(temp);
        temp = new Rectangle(720, 921, 58, 79);
        terrain.add(temp);
        temp = new Rectangle(778, 945, 42, 55);
        terrain.add(temp);
        temp = new Rectangle(820, 971, 100, 29);
        terrain.add(temp);
        temp = new Rectangle(920, 927, 60, 73);
        terrain.add(temp);
        temp = new Rectangle(980, 897, 57, 103);
        terrain.add(temp);
        temp = new Rectangle(1037, 850, 43, 150);
        terrain.add(temp);
        temp = new Rectangle(1080, 790, 168, 210);
        terrain.add(temp);
        temp = new Rectangle(1248, 972, 272, 28);
        terrain.add(temp);
        temp = new Rectangle(1520, 912, 60, 88);
        terrain.add(temp);
        temp = new Rectangle(1580, 872, 110, 128);
        terrain.add(temp);
        temp = new Rectangle(1690, 838, 35, 162);
        terrain.add(temp);
        temp = new Rectangle(1725, 810, 175, 190);
        terrain.add(temp);

    }

    public LinkedList<Rectangle> getTerrain() {
        return this.terrain;
    }

    public void setTerrain(LinkedList<Rectangle> terrain) {
        this.terrain = terrain;
    }
    
}
