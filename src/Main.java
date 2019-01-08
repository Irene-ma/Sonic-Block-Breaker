/* Team members: Tri Pham and Nickoles Barela and Irene Ma
 * 
 * concepts used: Array lists and private/public member functions and variables. 
 * 
 * Tri contributed to the block class, I contributed to the player class, and Nickoles contributed
 * to the ball class. We all contributed to the powerup and main class. We all contributed equal
 * effort into the project, so around 33% each.
 * 
 * How to play: Classic block breaker game, to move the platform, press 'a' for left and 'd' for right.
 * The goal for the game is to get the ball to hit every block in the game without dropping the ball. You
 * only have 3 lives. There are also powerups that increase the speed of the ball.
 * 
 */

import java.awt.Color;
import java.util.ArrayList;

public class Main {
	static int directionX = 5; // Stores the x direction of the probe
	static int directionY = 5;
	static final int BALL_MOVING = 1;
	

	public static void main(String[] args) {
		
		//initilizing the windows and background and images
		EZ.initialize(1024, 800);
		EZ.addImage("background1.png", 0, 100);
		Player myPerson = new Player("platform.png", 500, 740);
		EZSound theme = EZ.addSound("background.wav");
		EZSound hit = new EZSound("hit.wav");
		EZSound gameover = new EZSound("Gameover.wav");
		EZSound victory = new EZSound("victory.wav");
		Ball ball = new Ball("sonic.png", 500, 400);
		Powerup bolt = new Powerup("bolt.png", 500, -500);
		ArrayList<Block> m = new ArrayList<Block>();
		
		//creates how many lives the player has
		int lives = 3;
		boolean alive = true;
		EZText Lives = EZ.addText(100, 700, "Lives: " + lives, Color.BLUE, 50);
		//theme.play();
		theme.loop();;
		
		//creates the array list of blocks
		int x = 0;
		int y = 0;
		for (int c = 0; c < 8; c++) {
			y += 45;
			if (y <= 380) {
				x = 0;
				for (int r = 0; r < 10; r++) {
					x += 90;
					if (x <= 950) {
						Block brick = new Block("ring.png", x, y);
						m.add(brick);
					}
				}
			}
		}
		//while alive, you control the person 
		while (alive) {
			myPerson.ControlIt();
			ball.moveProbe();
			bolt.move();
			

			//if the ball hits the player, it bounces
			if ((ball.isInside(myPerson.getX() + 40, myPerson.getY()))
					|| (ball.isInside(myPerson.getX() - 40, myPerson.getY()))) {
				Ball.directionY = -directionY;
			}
			
			//if a ball hits a block, the block is moved off the screen
			for (int c = 0; c < m.size(); c++) {
				if ((ball.isInside(m.get(c).getX() - 5, m.get(c).getY() - 5))
						|| (ball.isInside(m.get(c).getX() + 5, m.get(c).getY() - 5))
						|| (ball.isInside(m.get(c).getX() - 5, m.get(c).getY() + 5))
						|| (ball.isInside(m.get(c).getX() + 5, m.get(c).getY() + 5))) {
					ball.bounce();
					hit.play();
					((Block) m.get(c)).move();
					m.remove(c);
				}

				//if powerup hits the player, it speeds up the ball
				if ((bolt.isInside(myPerson.getX() - 40, myPerson.getY() - 40))
						|| (bolt.isInside(myPerson.getX() + 40, myPerson.getY()- 40))
						|| (bolt.isInside(myPerson.getX() - 40, myPerson.getY() + 40))
						|| (bolt.isInside(myPerson.getX() + 40, myPerson.getY() + 40))) {
					bolt.delete();
					ball.speed();
				}

				
			}
			//if lives runs out, the game is over. if all the blocks are gone, you win
			if (lives <= 0) {
				alive = false;
				EZ.addText(1024/2, 800/2, "YOU LOSE", Color.RED, 200);
				theme.stop();
				gameover.play();
			}
			if (m.isEmpty()) {
				alive = false;
				EZ.addText(1024/2, 800/2, "YOU WIN", Color.GREEN, 200);
				theme.stop();
				victory.play();
				
			}

			//if ball is dropped, the lives decrease
			if(ball.getY() >= 800)
			{
				
				lives--;
				Lives.setMsg("Lives: " + lives);
			}
			
			EZ.refreshScreen();
		}
	}

}
