import java.util.ArrayList;
import java.util.Random;
import java.util.ResourceBundle.Control;
import java.util.concurrent.CopyOnWriteArrayList;
import java.beans.VetoableChangeListenerProxy;
import java.lang.System.Logger.Level;
import java.math.*;
import java.awt.Rectangle;

import util.Player;
import util.Enemy;
import util.GameObject;
import util.Level1;
import util.Level2;
import util.Level3;
import util.MouseController;
import util.Point3f;
import util.Sound;
import util.Terrain;
import util.Torpedo;
import util.UnitTests;
import util.Vector3f; 

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
public class Model {
	
	private Player player;
	private Controller controller = Controller.getInstance();
	private CopyOnWriteArrayList<Enemy> EnemiesList  = new CopyOnWriteArrayList<Enemy>();
	private CopyOnWriteArrayList<Torpedo> playerTorpList  = new CopyOnWriteArrayList<Torpedo>();
	private CopyOnWriteArrayList<Torpedo> enemyTorpList  = new CopyOnWriteArrayList<Torpedo>();
	private CopyOnWriteArrayList<Rectangle> terrain = new CopyOnWriteArrayList<Rectangle>();
	private Level1 level1 = new Level1();
	private Level2 level2 = new Level2();
	private Level3 level3 = new Level3();
	private long soundCoooldown =0;
	private boolean dead = false;
	Random rand = new Random();
	private int enemiesRemaining = 0;
	private int level = 0; 		


	public Model(){
		level = 1;
		player = new Player("res/VanguardSub3.png",150,150,new Point3f(200,500,0));
		EnemiesList = level1.getEnemies();
		
		terrain = level1.getTerrain();			
	}
	
	// This is the heart of the game , where the model takes in all the inputs ,decides the outcomes and then changes the model accordingly. 
	public void gamelogic(){
		enemiesRemaining = EnemiesList.size();
		if(enemiesRemaining == 0){
			level++;
			player.setCentre(new Point3f(200,500,0));
			player.setFront(new Point3f(232, 500, 0));
			player.setAngle(0);
			player.setScanCooldown(0);
			player.setTorpCooldown(0);
			playerTorpList  = new CopyOnWriteArrayList<Torpedo>();
			enemyTorpList  = new CopyOnWriteArrayList<Torpedo>();

			if(level == 2){
				EnemiesList = level2.getEnemies();
				enemiesRemaining = EnemiesList.size();
				terrain = level2.getTerrain();
			}
			if(level== 3){
				EnemiesList = level3.getEnemies();
				enemiesRemaining = EnemiesList.size();
				terrain = level3.getTerrain();
			}
		}
		// Player Logic first 
		playerLogic(); 
		// Enemy Logic next
		enemyLogic();

		enemyTorpLogic();
		// playerTorps move next 
		playerTorpLogic();
		// interactions between objects 
		gameLogic(); 
	}

	private void gameLogic(){ 
		// this is a way to increment across the array list data structure 
		// see if they hit anything 
		// using enhanced for-loop style as it makes it alot easier both code wise and reading wise too 
		for (Enemy temp : EnemiesList){
			for (Torpedo playerTorp : playerTorpList){
				if(temp.getBounds().intersects(playerTorp.getBounds())){
					EnemiesList.remove(temp);
					playerTorpList.remove(playerTorp);
					Sound explosion = new Sound("res/explosion.wav");
					explosion.play();
				}
			}
		}		
	}

	//Inspiration for collisions taken from: https://www.youtube.com/watch?v=DadImcUt9Nk&t=611s

	private void enemyLogic(){
		// TODO Auto-generated method stub
		for (Enemy E : EnemiesList){
			if(System.currentTimeMillis() - E.getTorpCooldown() > 2000 + rand.nextInt(1000)){
				E.setOnCooldown(false);
			}
			float x = E.getCentre().getX() - player.getCentre().getX(); 
			float y = E.getCentre().getY() - player.getCentre().getY(); 
			float dist = (float)Math.sqrt((x*x)+(y*y));
			if(dist<250 && !E.isOnCooldown()){
				createEnemyTorp(E);
				E.setOnCooldown(true);
			}
			if(E.getScanned() && !E.isOnCooldown()){
				createEnemyTorp(E);
				E.setOnCooldown(true);
			}
		    if(System.currentTimeMillis()- E.getScanTime() > 3000 ){
				E.setScanned(false);
			}
		}

		for (Torpedo temp : enemyTorpList){
			temp.getCentre().ApplyVector(temp.getVector());
			if (temp.getCentre().getX()<1){
			 	enemyTorpList.remove(temp);
			} 
		} 	
	}

	private void enemyTorpLogic(){
		for (Torpedo temp : enemyTorpList){
			if(temp.getBounds().intersects(player.getBounds())){
				dead = true;
			}
			temp.getCentre().ApplyVector(temp.getVector());
			updateTorpBounds(temp);
			if (temp.getCentre().getX()>1895 || temp.getCentre().getX()<0){
			 	enemyTorpList.remove(temp);
			} 
			if (temp.getCentre().getY()<0 || temp.getCentre().getY()>999){
				enemyTorpList.remove(temp);
		   } 
		   for(Rectangle r : terrain){
			   if(temp.getBounds().intersects(r)){
				enemyTorpList.remove(temp);
			   }
		   }
		} 	
	}


	private void playerTorpLogic(){
		// TODO Auto-generated method stub
		// move playerTorps 
		for (Torpedo temp : playerTorpList){
			temp.getCentre().ApplyVector(temp.getVector());
			updateTorpBounds(temp);
			if (temp.getCentre().getX()>1895 || temp.getCentre().getX()<0){
			 	playerTorpList.remove(temp);
			} 
			if (temp.getCentre().getY()<0 || temp.getCentre().getY()>999){
				playerTorpList.remove(temp);
		   } 
		   for(Rectangle r : terrain){
			   if(temp.getBounds().intersects(r)){
				playerTorpList.remove(temp);
			   }
		   }
		} 	
	}

	private void playerLogic(){

		updatePlayerBounds(player);

		if(Controller.getInstance().isMousePressed()){
			if(Controller.getInstance().getMousePoint().getY() > 825 && Controller.getInstance().getMousePoint().getY() < 925) {
				if(Controller.getInstance().getMousePoint().getX() > 50 && Controller.getInstance().getMousePoint().getX() < 150){
					playerScan();
				}
				if(Controller.getInstance().getMousePoint().getX() > 1690 && Controller.getInstance().getMousePoint().getX() < 1850){
					createPlayerTorp();
				}
			}
		}
		
		float vecX = player.getFront().getX() - player.getCentre().getX();
		float vecY = player.getFront().getY() - player.getCentre().getY();
		float vecZ = player.getFront().getZ() - player.getCentre().getZ();
		Vector3f movement = new Vector3f(vecX, -vecY, vecZ);

		if(Controller.getInstance().isKeyAPressed()){
			if(playerCollision(movement.byScalar((float)-0.1))){
				player.getCentre().ApplyVector(movement.byScalar((float)-0.1));
				player.getFront().ApplyVector(movement.byScalar((float)-0.1));
			}	
		}
		
		if(Controller.getInstance().isKeyDPressed()){
			if(playerCollision(movement.byScalar((float)0.1))){
				player.getCentre().ApplyVector(movement.byScalar((float)0.1));
				player.getFront().ApplyVector(movement.byScalar((float)0.1));
			}
		}
			
		if(Controller.getInstance().isKeyShiftPressed()){
			if(playerCollision(new Vector3f(0,(float)1.5,0))){
				player.getCentre().ApplyVector( new Vector3f(0,(float)1.5,0));
				player.getFront().ApplyVector(new Vector3f(0,(float)1.5,0));
			}
		}
		
		if(Controller.getInstance().isKeyCtrlPressed()){
			if(playerCollision(new Vector3f(0,(float)-1.5,0))){
				player.getCentre().ApplyVector( new Vector3f(0,(float)-1.5,0));
				player.getFront().ApplyVector(new Vector3f(0,(float)-1.5,0));
			}
		}

		if(Controller.getInstance().isKeyWPressed()){ 

			if(player.getAngle()>-0.785){
				player.setAngle(player.getAngle()-.01);
			
				float w = player.getFront().getX() - player.getCentre().getX();
				float z = player.getFront().getY() - player.getCentre().getY();

				float w1 = ((float)Math.cos(-.01)*w) - ((float)Math.sin(-.01)*z);
				float z1 = ((float)Math.sin(-.01)*w) + ((float)Math.cos(-.01)*z);

				w1 += player.getCentre().getX();
				z1 += player.getCentre().getY();

				Point3f temp1 = new Point3f(w1, z1, 0);

				player.setFront(temp1);
			}
			
		}

		if(Controller.getInstance().isKeySPressed()){

			if(player.getAngle()<0.785){
				player.setAngle(player.getAngle()+.01);

				float w = player.getFront().getX() - player.getCentre().getX();
				float z = player.getFront().getY() - player.getCentre().getY();

				float w1 = ((float)Math.cos(.01)*w) - ((float)Math.sin(.01)*z);
				float z1 = ((float)Math.sin(.01)*w) + ((float)Math.cos(.01)*z);

				w1 += player.getCentre().getX();
				z1 += player.getCentre().getY();

				Point3f temp1 = new Point3f(w1, z1, 0);

				player.setFront(temp1);
			}	
		}
		
		if(Controller.getInstance().isKeySpacePressed()){
			createPlayerTorp();
			Controller.getInstance().setKeySpacePressed(false);
		} 

		if(Controller.getInstance().isKeyQPressed()){
			playerScan();	
		}	
	}

	private void playerScan(){
		if(System.currentTimeMillis() - player.getScanCooldown() > 10000){
			for(Enemy E: EnemiesList){
				E.setScanned(true);
			}
			player.setScanCooldown(System.currentTimeMillis());
			Sound scan = new Sound("res/sonar.wav");
			scan.play();
		}	
	}

	private void createPlayerTorp() {
		if((System.currentTimeMillis()- player.getTorpCooldown() > 5000)){
			float vecX = player.getFront().getX() - player.getCentre().getX();
			float vecY = player.getFront().getY() - player.getCentre().getY();
			float vecZ = player.getFront().getZ() - player.getCentre().getZ();
			Vector3f torpVec = new Vector3f(vecX, -vecY, vecZ).byScalar((float)0.125);	
			Torpedo torpedo = new Torpedo("res/PlayerTorp.png",64,64,new Point3f(player.getFront().getX(),player.getFront().getY(),0.0f ), torpVec);
			torpedo.setAngle(player.getAngle());
			playerTorpList.add(torpedo);
			player.setTorpCooldown(System.currentTimeMillis());
			Sound torp = new Sound("res/torpedo.wav");
			torp.play();
		}
	}

	private void createEnemyTorp(Enemy E) {

		//if((System.currentTimeMillis()- E.getTorpCooldown() > 1000 + rand.nextInt(12000))){
			Vector3f vect = new Vector3f(player.getCentre().getX()-E.getCentre().getX(), -(player.getCentre().getY()-E.getCentre().getY()), 0);
			float absolute = (float)Math.sqrt((vect.getX()*vect.getX()) + (vect.getY()*vect.getY()));
			Vector3f unitVect = new Vector3f((float)vect.getX()/absolute, (float)vect.getY()/absolute, 0);
			Torpedo torpedo = new Torpedo("res/EnemyTorp.png", 64, 64, new Point3f(E.getCentre().getX(),E.getCentre().getY()-32,0.0f), unitVect);
			float tan = unitVect.getY()/unitVect.getX();
			float angle = (float)Math.toRadians(Math.atan(tan));
			torpedo.setAngle(angle);
			enemyTorpList.add(torpedo);
			E.setTorpCooldown(System.currentTimeMillis());
			Sound torp = new Sound("res/torpedo.wav");
			torp.play();
		//}
	}

	private boolean playerCollision(Vector3f vector){
		GameObject temp = new GameObject("res/blankSprite.png", 150, 150, new Point3f(player.getCentre().getX(), player.getCentre().getY(), 0));
		Point3f tempPoint = new Point3f(player.getFront().getX(), player.getFront().getY(), 0f);
		temp.setFront(tempPoint);
		temp.setAngle(player.getAngle());
		temp.getCentre().ApplyVector(vector);
		temp.setBounds(player.getBounds());
		updatePlayerBounds(temp);
		for(Rectangle r : terrain){
			if(temp.getBounds().intersects(r)){
				if(System.currentTimeMillis() - soundCoooldown > 1000){
					Sound crash = new Sound("res/crashing.wav");
					crash.play();
					soundCoooldown = System.currentTimeMillis();
				}
				return false;
			}
		}
		return true;
	}

	private void updateTorpBounds(Torpedo t){
		Rectangle temp = new Rectangle((int)t.getCentre().getX()-32, (int)t.getCentre().getY()-3, 64, 6);
		t.setBounds(temp);
	}

	private void updatePlayerBounds(GameObject g){
		Rectangle temp = new Rectangle((int)g.getCentre().getX()-75, (int)g.getCentre().getY()-37, 150, 75);
		g.setBounds(temp);
	}

	public GameObject getPlayer() {
		return player;
	}

	public CopyOnWriteArrayList<Enemy> getEnemies() {
		return EnemiesList;
	}
	
	public CopyOnWriteArrayList<Torpedo> getplayerTorps() {
		return playerTorpList;
	}

	public CopyOnWriteArrayList<Torpedo> getenemyTorps() {
		return enemyTorpList;
	}

	public CopyOnWriteArrayList<Rectangle> getTerrain() {
		return terrain;
	}
	public int getEnemiesRemaining() {
		return this.enemiesRemaining;
	}

	public void setEnemiesRemaining(int enemiesRemaining) {
		this.enemiesRemaining = enemiesRemaining;
	}

	public int getLevel() {
		return this.level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public boolean isDead(){return dead;}
}
