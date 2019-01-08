
public class Block{
	
	//declaring variables
	private EZImage blockPicture;
	int x;
	int y;

	//constructor
	public Block(String filename, int maxX, int maxY) {
		x = maxX;
		y = maxY;
		blockPicture = EZ.addImage(filename, x, y);
		blockPicture.scaleBy(0.1);
	}

	// Accessor return x
	public int getX() {
		return x;
	}
	//returns y
	public int getY() {
		return y;
	}

	// Check to see if a point is inside an obstacle.
	public boolean isInside(int d, int e) {
		return blockPicture.isPointInElement(d, e);
	}
	//"delete" the block by moving it offscreen
	public void move() {
		x = 2000;
		y = 2000;
		blockPicture.translateTo(x, y);
	}

}
