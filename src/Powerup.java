import java.util.Random;

public class Powerup {
	private EZImage powerPicture;
	int x;
	int y;
	static int posX = 500; // Stores the x position of the powerup
	static int posY = -1000; // Stores the y position of the powerup
	// static int directionX = 5; // Stores the x direction of the powerup
	static int directionY = 5;
	Random randomGenerator = new Random();

	// Constructor for the powerup
	public Powerup(String filename, int maxX, int maxY) {
		powerPicture = EZ.addImage(filename, 500, -500);
		powerPicture.scaleBy(0.9);

	}

	// Accessor function to get the X and Y position of the powerup.
	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	// Move the obstacle.
	public void move() {
		powerPicture.translateTo(posX, posY); // Set the position of the powerup

		posY = posY + directionY;
		
		if (posY > 800) {
			
			posX = randomGenerator.nextInt(1024);
			posY = randomGenerator.nextInt(4000) -5000;
			powerPicture.translateTo(posX, posY);
		}
		

	}

	// Check to see if a point is inside an powerup
	public boolean isInside(int posx, int posy) {
		return powerPicture.isPointInElement(posx, posy);
	}
	
	//if ball hits block, move the block out of the screen
	public void delete() {
		posX = randomGenerator.nextInt(1024);
		posY = randomGenerator.nextInt(4000) -5000;
		powerPicture.translateTo(posX, posY);
	}

}
