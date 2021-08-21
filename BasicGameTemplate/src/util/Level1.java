package util;

import java.util.concurrent.CopyOnWriteArrayList;
import java.awt.Rectangle;

//Finn O'Neill 17367986

public class Level1 {
    
    private CopyOnWriteArrayList<Enemy> EnemiesList  = new CopyOnWriteArrayList<Enemy>();
    private CopyOnWriteArrayList<Rectangle> terrain = new CopyOnWriteArrayList<Rectangle>();

    public Level1(){
        EnemiesList.add(new Enemy("res/EnemySub.png",150,150,new Point3f(1700, 500, 0))); 
        terrain.add(new Rectangle(0,1000, 1900,1));
        terrain.add(new Rectangle(0, 0, 1, 1000));
        terrain.add(new Rectangle(0, 0, 1900, 1));
        terrain.add(new Rectangle(1900, 0, 1, 1000));
    }
    
    public CopyOnWriteArrayList getEnemies(){
        return EnemiesList;
    }

    public CopyOnWriteArrayList getTerrain(){
        return terrain;
    }
}
