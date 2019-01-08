import java.util.Random;

public class Ball {
	
	// Private variable for Ball Class's image, X/Y positions, X/Y directions, X/Y boundaries
	private EZImage ballPicture;
	static int posX = 1; // Stores the x position of the probe
	static int posY = 400; // Stores the y position of the probe
	static int directionX = 5; // Stores the x direction of the probe
	static int directionY = 5;
	static final int BALL_SPEED = 1;
	static final int BALL_END = 2;
	int rotationAngle = 0;
	int speed = 1;

	// Constructor for the Ball .
	public Ball(String filename, int maxX, int maxY) {
		// Sets the ball to an image of Sonic and scales the picture down in size
		ballPicture = EZ.addImage(filename, 1, 400);
		ballPicture.scaleBy(0.4);
	}

	// Getter function for the X position of the Ball.
	public int getX() {
		return posX;
	}

	// Getter function for the Y position of the Ball.
	public int getY() {
		return posY;
	}

	// Move the obstacle.
	public void moveProbe() {
		ballPicture.translateTo(posX, posY); // Set the position of the ball
		ballPicture.rotateTo(rotationAngle); // Set the rotation of the ball
		rotationAngle += 10; // Set the new angle of the rotation.

		// Changes the Ball's position to another new position along the direction
		posX = posX + directionX * speed;
		posY = posY + directionY * speed;

		// Changes the direction of the Ball based on position with a screen's bounds.
		if (posX > 1024) {
			directionX = -directionX;
		}
		if (posX < 0) {
			directionX = -directionX;
		}

		// if the ball reaches all the way to the bottom reset it to the top
		if (posY > 800) {
			posY = 400;
			posX = 1;
			speed = 1;
		}
		// if the ball reaches the top of the screen the ball reverses the Y direction
		if (posY < 0) {
			directionY = -directionY;
		}
		

	}

	// Check to see if an object is inside the ball.
	public boolean isInside(int posx, int posy) {
		return ballPicture.isPointInElement(posx, posy);
	}

	// Reverses the Y direction of the ball for a bounce
	public void bounce() {
		directionY = -directionY;
	}

	// this method is used for the powerup class to increase the speed of the ball.
	public void speed() {
		speed = 2;
		 if (posY > 800) {
			 speed = 1;
		 }

	}
}