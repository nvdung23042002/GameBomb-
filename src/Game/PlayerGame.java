package Game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

public class PlayerGame extends Entity {

	Board br;
	KeyBoard input;
	Boom b;
	
	BufferedImage player;
	BufferedImage image, heart, boot2, gameOver, victory;
	
	String text;
	boolean moving;
	int framePlayer = 0, intervalPlayer = 5, indexAnimPlayer = 0;
    BufferedImage[] playerAnimUp, playerAnimDown, playerAnimRight, playerAnimLeft;
    
    boolean boomPrevent;
	
	public PlayerGame(Board br, KeyBoard kb, Boom b) {
		// TODO Auto-generated constructor stub
		this.br = br;
		this.input = kb;
		this.b = b;
		setDefaultValue();
		getPlayerImage();
		solPic();
	}
	
	public void getPlayerImage() {
		try {
			image = ImageIO.read(new File("C:\\Users\\hscng\\OneDrive\\Desktop\\game\\JavaGame_BomberMan\\res\\sheets.png"));
			heart = ImageIO.read(new File("C:\\Users\\hscng\\OneDrive\\Desktop\\game\\JavaGame_BomberMan\\res\\heart.png"));
			boot2 = ImageIO.read(new File("C:\\Users\\hscng\\OneDrive\\Desktop\\game\\JavaGame_BomberMan\\res\\item_shoe.gif"));
			gameOver = ImageIO.read(new File("C:\\Users\\hscng\\OneDrive\\Desktop\\game\\JavaGame_BomberMan\\res\\gameOver.png"));
			victory = ImageIO.read(new File("C:\\Users\\hscng\\OneDrive\\Desktop\\game\\JavaGame_BomberMan\\res\\victory.png"));
		} catch (Exception e) {
			System.out.println("false");
		}
	}
	
	public void setDefaultValue () {
		x = (tileSize * SCALE);
        y = (tileSize * SCALE);
	}

	// xu li va cham
	public boolean isFree(int nextX, int nextY) {
    	int size = SCALE * tileSize;
    	
    	int nextX1 = nextX / size;
    	int nextY1 = nextY / size;
    	
    	int nextX2 = (nextX + size - 1) / size;
    	int nextY2 = nextY / size;
    	
    	int nextX3 = (nextX + size - 1) / size;
    	int nextY3 = (nextY + size - 1) / size;
    	
    	int nextX4 = nextX / size;
    	int nextY4 = (nextY + size - 1) / size;
    	
    	return  !(br.scene[nextY1][nextX1] == 1 || br.scene[nextY1][nextX1] == 2 || br.scene[nextY1][nextX1] == 3
    		|| br.scene[nextY2][nextX2] == 1 || br.scene[nextY2][nextX2] == 2 || br.scene[nextY2][nextX2] == 3
    		|| br.scene[nextY3][nextX3] == 1 || br.scene[nextY3][nextX3] == 2 || br.scene[nextY3][nextX3] == 3
  			|| br.scene[nextY4][nextX4] == 1 || br.scene[nextY4][nextX4] == 2 || br.scene[nextY4][nextX4] == 3 			
    		);
    		
    }
	
	// xu li di chuyen
	
	public void solPic() {
		playerAnimUp = new BufferedImage[3];
        playerAnimDown = new BufferedImage[3];
        playerAnimRight = new BufferedImage[3];
        playerAnimLeft = new BufferedImage[3];
        
        for (int i = 0; i < 3; i++) {
            playerAnimLeft[i] = image.getSubimage(i * tileSize, 0, tileSize, tileSize);
            playerAnimRight[i] = image.getSubimage(i * tileSize, tileSize, tileSize, tileSize);
            playerAnimDown[i] = image.getSubimage((i + 3) * tileSize, 0, tileSize, tileSize);
            playerAnimUp[i] = image.getSubimage((i + 3) * tileSize, tileSize, tileSize, tileSize);
            
        }
	}
	
	
	void isFreeWithBoom() {
		if (b.exploded) {
			if (this.x <= b.boomX - size || this.x >= b.boomX + size || this.y <= b.boomY - size || this.y >= b.boomY + size) {
				br.scene[b.boomY / size][b.boomX / size] = 3;
			}
		} else {
			br.scene[b.boomY / size][b.boomX / size] = 0;
		}
	}
	
	
	
	
	public void update() {	
		
		moving = false;
		isFreeWithBoom();
    	if (input.isRight() && isFree(x + SPEED, y)) {
            x += SPEED;
            moving = true;
        }

        if (input.isLeft() && isFree(x - SPEED, y)) {
            x -= SPEED;
            moving = true;
        }

        if (input.isUp() && isFree(x , y - SPEED)) {
            y -= SPEED;
            moving = true;
        }

        if (input.isDown() && isFree(x , y + SPEED)) {
            y += SPEED;
            moving = true;
        }
        if (input.cheat) {
            br.scene[11][14] = 6;
        }
               
        if (moving) {
            
        	framePlayer++;   // delay toc do load anh
            if (framePlayer > intervalPlayer) {
                framePlayer = 0;
                indexAnimPlayer++;
                if (indexAnimPlayer > 2) {
                    indexAnimPlayer = 0;
                }
            }

            if (input.isRight()) {
                player = playerAnimRight[indexAnimPlayer];
            } else if (input.isLeft()) {
                player = playerAnimLeft[indexAnimPlayer];
            } else if (input.isUp()) {
                player = playerAnimUp[indexAnimPlayer];
            } else if (input.isDown()) {
                player = playerAnimDown[indexAnimPlayer];
            }
        } else {
            player = playerAnimDown[1];
        }
        // luc an item
        if (br.scene[(this.y + size / 2) / size][(this.x + size / 2) / size] == 4) { // an mang
        	br.scene[(this.y + size / 2) / size][(this.x + size / 2) / size] = 0;
        	if (lives < 3) {
        		lives ++;
        	}     	
        }
        
        if (br.scene[(this.y + size / 2) / size][(this.x + size / 2) / size] == 5) { // an giay
        	br.scene[(this.y + size / 2) / size][(this.x + size / 2) / size] = 0;
        	if (SPEED < 4)
        		SPEED = SPEED + 2;
        }
        
        //     ************** luc chien thang, di qua canh cua *****************************
        if (br.scene[(this.y + size / 2) / size][(this.x + size / 2) / size] == 6 && br.level == 1) {
        	br.scene[11][14] = 1;
        	br.round2();
        	x = (tileSize * SCALE);
            y = (tileSize * SCALE);
            numberOfSprite = 3;
            br.level ++;
        }
	}
	
	
	
	public void draw(Graphics2D g2) {							
		g2.drawImage(player, x, y, size, size, null); 
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
		        RenderingHints.VALUE_ANTIALIAS_ON);
		    for (int i = 1; i <= lives; i++) {
		    	g2.drawImage(heart, 700 + i * size, 5 * size + 50, size, size, null);
		    }
		    
		    for (int i = 1; i <= SPEED / 2; i++) {
		    	g2.drawImage(boot2, 700 + i * size, 7 * size + 20, size, size, null);
		    }
		    
		    // luc chet
		    if (lives == 0) {
		    	g2.drawImage(gameOver, 300, 150, 300, 300, null); 
		    	br.isRunning = false;
		    	Sound sound = new Sound("C:\\Users\\hscng\\OneDrive\\Desktop\\game\\JavaGame_BomberMan\\res\\audio\\dead.wav");
		    	sound.play();
	        } 
		    // luc qua ca 2 man
		    if (br.scene[(this.y + size / 2) / size][(this.x + size / 2) / size] == 6 && br.level == 2) {
		    	g2.drawImage(victory, 300, 150, 300, 300, null);
		    	br.isRunning = false;
		    	
	        }
	}
}
