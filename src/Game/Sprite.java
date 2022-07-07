package Game;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Random;

import javax.imageio.ImageIO;

public class Sprite extends Entity {
    public int SPEED_ENEMY ;
    
    int SpriteX;
    int SpriteY;
    
    BufferedImage sprite;
    boolean check = true;
    
    Board br;
    PlayerGame pl;
    
    Sprite(Board br, PlayerGame pl, int X, int Y, int Speed) {
        this.br = br;
        this.pl = pl;
        this.SPEED_ENEMY = Speed;
        setDefaultValue (X, Y);
        getPlayerImage();
    }
    
    public void setDefaultValue (int X, int Y) {
        SpriteX = (tileSize * SCALE * X);
        SpriteY = (tileSize * SCALE * Y);
    }
    
    public void getPlayerImage() {
        try {
            sprite = ImageIO.read(new File("C:\\Users\\hscng\\OneDrive\\Desktop\\game\\JavaGame_BomberMan\\res\\sprite1.png"));
            //g2.drawImage(player, x, y, size, size, null);
        } catch (Exception e) {
            System.out.println("false");
        }
    }
    
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
        
        return  !(br.scene[nextY1][nextX1] == 1 || br.scene[nextY1][nextX1] == 2
            || br.scene[nextY2][nextX2] == 1 || br.scene[nextY2][nextX2] == 2
            || br.scene[nextY3][nextX3] == 1 || br.scene[nextY3][nextX3] == 2
            || br.scene[nextY4][nextX4] == 1 || br.scene[nextY4][nextX4] == 2);
            
    }
    
    public void setActionLeftRight() {
         
        if (br.scene[SpriteY / size][(SpriteX - 1) / size] != 0) {
        	check = true;
        }
        if (br.scene[SpriteY / size][(SpriteX + size) / size] != 0) {
        	check = false;
        }
               
        if (check == true) {
        	 SpriteX += SPEED_ENEMY;
        }
        if (check == false) {
        	SpriteX -= SPEED_ENEMY;
        }      
    }
    
    public void update(Boom b ) {
    	if (numberOfSprite == 0) {
    		br.scene[11][14] = 6;
    	}
    	if (b.countExplosion == 1) {
    		// boom no trung quai
    		// va cham ben trai
    		if (br.scene[b.boomY/size][(b.boomX - size) / size] == 0) {
    			if (((this.SpriteX + size / 2) / size) == (b.boomX - 2 * size) / size && (this.SpriteY + size / 2) / size == b.boomY / size) {
        			this.sprite = null;
        			this.SpriteX = 0;
            		this.SpriteY = 0;
            		check = (Boolean) null;
        			numberOfSprite--;
        		}
        		if (((this.SpriteX + size / 2) / size) == (b.boomX - size) / size && (this.SpriteY + size / 2) / size == b.boomY / size) {
        			this.sprite = null;
        			this.SpriteX = 0;
        			this.SpriteY = 0;
        			check = (Boolean) null;
        			numberOfSprite--;
        		}
    		}
    		
    		//va cham ben phai
    		if (br.scene[b.boomY/size][(b.boomX + size) / size] == 0) {
    			if (((this.SpriteX + size / 2) / size) == (b.boomX + 2 * size) / size && (this.SpriteY + size / 2) / size == b.boomY / size) {
        			this.sprite = null;
        			this.SpriteX = 48;
        			this.SpriteY = 48;
        			numberOfSprite--;
        		}
        		if (((this.SpriteX + size / 2) / size) == (b.boomX + size) / size && (this.SpriteY + size / 2) / size == b.boomY / size) {
        			this.sprite = null;
        			this.SpriteX = 48;
        			this.SpriteY = 48;
        			numberOfSprite--;
        		}
    		}
    		
    		// va cham phia tren
    		if (br.scene[(b.boomY - size) / size][b.boomX / size] == 0) {
    			if (((this.SpriteX + size / 2) / size) == (b.boomX) / size && (this.SpriteY + size / 2) / size == (b.boomY - size) / size) {
        			this.sprite = null;
        			this.SpriteX = 0;
        			this.SpriteY = 0;
        			numberOfSprite--;
        		}
        		if (((this.SpriteX + size / 2) / size) == (b.boomX) / size && (this.SpriteY + size / 2) / size == (b.boomY - 2 * size) / size) {
        			this.sprite = null;
        			this.SpriteX = 0;
        			this.SpriteY = 0;
        			numberOfSprite--;
        		}
    		}
    		
    		// va cham phia duoi
    		if (br.scene[(b.boomY + size) / size][b.boomX / size] == 0) {
    			if (((this.SpriteX + size / 2) / size) == b.boomX / size && (this.SpriteY + size / 2) / size == (b.boomY + size) / size) {
        			this.sprite = null;
        			this.SpriteX = 0;
        			this.SpriteY = 0;
        			numberOfSprite--;
        		}
        		if (((this.SpriteX + size / 2) / size) == b.boomX  / size && (this.SpriteY + size / 2) / size == (b.boomY + 2 * size) / size) {
        			this.sprite = null;
        			this.SpriteX = 0;
        			this.SpriteY = 0;
        			numberOfSprite--;
        		}
    		}
    	} 
    	
    	
    	// va cham voi nguoi choi
    	if (this.SpriteY == pl.y && (this.SpriteX + size == pl.x || pl.x + size == this.SpriteX)) {
    		pl.player = null;
    		pl.lives--;
    		this.sprite = null;
    		if (pl.SPEED > 2)
				pl.SPEED -= 2;
    	}
    	if (this.SpriteX == pl.x && (this.SpriteY + size == pl.y || pl.y + size == this.SpriteY)) {
    		pl.player = null;
    		pl.lives--;
    		this.sprite = null;
    		if (pl.SPEED > 2)
				pl.SPEED -= 2;
    	}
    	if ((this.SpriteX + size - 4) == pl.x && ((this.SpriteY < pl.y && this.SpriteY + size > pl.y) || (this.SpriteY < pl.y + size && this.SpriteY + size > pl.y + size))) {
    		pl.player = null;
    		pl.lives--;
    		this.sprite = null;
    		if (pl.SPEED > 2)
				pl.SPEED -= 2;
    	}
    	if ((this.SpriteX - size + 4) == pl.x && ((this.SpriteY < pl.y && this.SpriteY + size > pl.y) || (this.SpriteY < pl.y + size && this.SpriteY + size > pl.y + size))) {
    		pl.player = null;
    		pl.lives--;
    		this.sprite = null;
    		if (pl.SPEED > 2)
				pl.SPEED -= 2;
    	}
    	if ((this.SpriteY + size - 4) == pl.y && ((this.SpriteX < pl.x && this.SpriteX + size > pl.x) || (this.SpriteX < pl.x + size && this.SpriteX + size > pl.x + size))) {
    		pl.player = null;
    		pl.lives--;
    		this.sprite = null;
    		if (pl.SPEED > 2)
				pl.SPEED -= 2;
    	}
    	if ((this.SpriteY - size + 4) == pl.y && ((this.SpriteX < pl.x && this.SpriteX + size > pl.x) || (this.SpriteX < pl.x + size && this.SpriteX + size > pl.x + size))) {
    		pl.player = null;
    		pl.lives--;    		
    		if (pl.SPEED > 2)
				pl.SPEED -= 2;
    	}
    }
    
    public void setActionUpDown() {
        if (br.scene[(SpriteY - 1) / size][SpriteX  / size] != 0) {
        	check = true;
        }
        if (br.scene[(SpriteY + size) / size][SpriteX / size] != 0) {
        	check = false;
        }
               
        if (check == true) {
        	 SpriteY += SPEED_ENEMY;
        }
        if (check == false) {
        	SpriteY -= SPEED_ENEMY;
        }   
    }
    
    public void draw(Graphics2D g2) {                       
        g2.drawImage(sprite, SpriteX, SpriteY, size, size, null);
    }
}