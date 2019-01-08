public class Player {
	private EZImage ballPicture;
	private int x, y;

	//constructor of player
	public Player(String filename, int posx, int posy) {
		ballPicture = EZ.addImage(filename, posx, posy);
		ballPicture.scaleBy(0.5);
		x = posx;
		y = posy;
	}

	// Accessor method to retrieve the position of the player.
	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	// Set the position of the player
	public void setPosition(int posx, int posy) {
		x = posx;
		y = posy;
		setPersonImagePosition(x, y);
	}
//set picture of the player
	private void setPersonImagePosition(int posx, int posy) {
		ballPicture.translateTo(posx, posy);
	}

	// Methods for moving the player.
	public void moveLeft(int step) {
		x = x - step;
		setPersonImagePosition(x, y);

	}

	public void moveRight(int step) {
		x = x + step;
		setPersonImagePosition(x, y);
	}

	// Keyboard controls for moving the player.
	public void ControlIt() {
		if (EZInteraction.isKeyDown('a')) {
			moveLeft(10);
		}
		else if (EZInteraction.isKeyDown('d')) {
			moveRight(10);
		}
	}

	
	//returns the value of the X and Y
	public float getXCenter() {
		return x;
	}

	public float getYCenter() {
		return y;
	}

}
