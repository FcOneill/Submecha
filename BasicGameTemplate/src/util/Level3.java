package util;

//Finn O'Neill 17367986

import java.util.concurrent.CopyOnWriteArrayList;
import java.awt.Rectangle;

public class Level3 {
    
    private CopyOnWriteArrayList<Enemy> EnemiesList  = new CopyOnWriteArrayList<Enemy>();
    private CopyOnWriteArrayList<Rectangle> terrain = new CopyOnWriteArrayList<Rectangle>();

    public Level3(){
        EnemiesList.add(new Enemy("res/EnemySub.png",150,150,new Point3f(800, 200, 0))); 
        EnemiesList.add(new Enemy("res/EnemySub.png",150,150,new Point3f(800, 600, 0))); 
        EnemiesList.add(new Enemy("res/EnemySub.png",150,150,new Point3f(900, 100, 0))); 
        EnemiesList.add(new Enemy("res/EnemySub.png",150,150,new Point3f(1700, 700, 0))); 
        EnemiesList.add(new Enemy("res/EnemySub.png",150,150,new Point3f(1400, 300, 0))); 
        EnemiesList.add(new Enemy("res/EnemySub.png",150,150,new Point3f(1430, 930, 0))); 
        terrain.add(new Rectangle(0,1000, 1900,1));
        terrain.add(new Rectangle(0, 0, 1, 1000));
        terrain.add(new Rectangle(0, 0, 1900, 1));
        terrain.add(new Rectangle(1900, 0, 1, 1000));
        terrain.add(new Rectangle(0, 900, 30, 100));
        terrain.add(new Rectangle(30, 930, 30, 70));
        terrain.add(new Rectangle(60, 980, 325, 20));
        terrain.add(new Rectangle(385, 907, 73, 100));
        terrain.add(new Rectangle(458, 980, 112, 72));
        terrain.add(new Rectangle(570, 926, 60, 74));
        terrain.add(new Rectangle(630, 859, 90, 141));
        terrain.add(new Rectangle(720, 921, 58, 79));
        terrain.add(new Rectangle(778, 945, 42, 55));
        terrain.add(new Rectangle(820, 971, 100, 29));
        terrain.add(new Rectangle(920, 927, 60, 73));
        terrain.add(new Rectangle(980, 897, 57, 103));
        terrain.add(new Rectangle(1037, 850, 43, 150));
        terrain.add(new Rectangle(1080, 790, 168, 210));
        terrain.add(new Rectangle(1248, 972, 272, 28));
        terrain.add(new Rectangle(1520, 912, 60, 88));
        terrain.add(new Rectangle(1580, 872, 110, 128));
        terrain.add(new Rectangle(1690, 838, 35, 162));
        terrain.add(new Rectangle(1725, 810, 175, 190));
    }
    
    public CopyOnWriteArrayList getEnemies(){
        return EnemiesList;
    }

    public CopyOnWriteArrayList getTerrain(){
        return terrain;
    }
}
