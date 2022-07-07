package Game;

public class Entity {
	int tileSize = 16;
    
    final int SCALE = 3;
    
    int size = tileSize * SCALE;
	 public int x, y;
	 public int SPEED = 2;
	 
	 public static int numberOfSprite = 3;
	 public int numberBoom = 1;
	 public int lives = 1;
}
