package Game;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Random;

import javax.imageio.ImageIO;

public class Boom extends Entity{

	Board br;
	KeyBoard input;
	PlayerGame player;
	
	BufferedImage boom;
	BufferedImage image;
	
	int item[] = {0 , 4, 0, 5};	
	
	 int boomX = size;
	 int boomY = size;
	 // 
	 int frameBomb = 0, intervalBomb = 7, indexAnimBomb = 0;
	 int countToExplode, intervalToExplode = 4;
	 boolean exploded;
	 BufferedImage[] bombAnim;
	 
	 
	 // xu li loang vu no
	 BufferedImage midExplosion, leftExplosion1, leftExplosion2, rightExplosion1, rightExplosion2, 
	 						upExplosion1, upExplosion2, downExplosion1, downExplosion2;
	 int countExplosion = 0;
	 int indExplosion = 20;
	 boolean Explosion;
	
	Boom(Board br,	KeyBoard input) {
		this.br = br;
		this.input = input;
		getPlayerImage();
		solvingPic();
	}
	
	public void getPlayerImage() {
		try {
			 image = ImageIO.read(new File("C:\\Users\\hscng\\OneDrive\\Desktop\\game\\JavaGame_BomberMan\\res\\sheets.png"));
		} catch (Exception e) {
			System.out.println("false");
		}
	}
	
	public void solvingPic() {
		bombAnim = new BufferedImage[3];		
		for (int i = 0; i < 3; i++) {
			bombAnim[i] = image.getSubimage(i * tileSize, 3 * tileSize, tileSize, tileSize);
		}
	}
	
	int isFree(int x, int y) {  
		if (br.scene[x][y] == 1)
		{
			return 1;
		}
			
		if (br.scene[x][y] == 0)
		{
			return 0;
		}
		if (br.scene[x][y] == 2) {
			return 2;
		}
		return 1;
	}
	
	public void update(PlayerGame pl) {	
		
			//dat boom
			if (input.bang && numberBoom > 0 && Explosion != true) {
				numberBoom--;				
				exploded = true;
				int tempX = (pl.x + size / 2) / size;
				int tempY = (pl.y + size / 2) / size;
				boomX = tempX * size;
				boomY = tempY * size;
				Sound sound = new Sound("C:\\Users\\hscng\\OneDrive\\Desktop\\game\\JavaGame_BomberMan\\res\\audio\\place_bomb.wav");
				sound.play();
			}
			
			// random item 
			Random rand = new Random();
		    int numRand = rand.nextInt(4);
		    
		    // khi bom no
			if (countToExplode > intervalToExplode)
			{
				numberBoom++;
				exploded = false;
				countToExplode = 0;
				boom = null;
				Explosion = true;
				Sound sound = new Sound("C:\\Users\\hscng\\OneDrive\\Desktop\\game\\JavaGame_BomberMan\\res\\audio\\explosion.wav");
				sound.play();
			}
			
			
			if(exploded) { // khi dang dat boom	
				frameBomb++;
				if (frameBomb == intervalBomb) {
	                frameBomb = 0;
	                boom = bombAnim[indexAnimBomb];
	                //boom = image.getSubimage(0, 3 * tileSize, tileSize, tileSize);
	                indexAnimBomb++;
	                if (indexAnimBomb > 2) {
	                    indexAnimBomb = 0;
	                    countToExplode++;
	                }
				}
			}
			
			if (Explosion) { // khi loang vu no
				
								
				countExplosion++;
				
				if (countExplosion > indExplosion) {
					Explosion = false;
					midExplosion = null;
					leftExplosion1 = null;
					leftExplosion2 = null;
					rightExplosion1 = null;
					rightExplosion2 = null;
					upExplosion1 = null;
					upExplosion2 = null;
					downExplosion1 = null;
					downExplosion2 = null;
										
					countExplosion = 0;
				}
				//left1
			if (countExplosion == 2) {
				midExplosion = image.getSubimage(2 * tileSize, 6 * tileSize, tileSize, tileSize);
				
				// boom no trung nguoi
				if (((pl.x + size / 2) / size) == boomX / size && (pl.y + size / 2) / size == boomY / size) {
					pl.player = null;
					pl.lives --;
					if (pl.SPEED > 2)
					pl.SPEED -= 2;
				}
				
				if (isFree(boomY / size, (boomX - size) / size) != 1) {
					if (isFree(boomY / size, (boomX - size) / size) == 0)
					{
						leftExplosion1 = image.getSubimage(1 * tileSize, 6 * tileSize, tileSize, tileSize);
						// left 2
						if (isFree(boomY / size, (boomX - 2 * size) / size) != 1) {
							if (isFree(boomY / size, (boomX - 2 * size) / size) == 0)
								leftExplosion2 = image.getSubimage(0 * tileSize, 6 * tileSize, tileSize, tileSize);
							if (isFree(boomY / size, (boomX - 2 * size) / size) == 2) {
								  br.scene[boomY/size][(boomX - 2 * size) / size] = 0;
							}
						}
						
						// boom no trung nguoi
						if (((pl.x + size / 2) / size) == (boomX - 2 * size) / size && (pl.y + size / 2) / size == boomY / size) {
							pl.player = null;
							pl.lives --;
							if (pl.SPEED > 2)
							pl.SPEED -= 2;
						}
						if (((pl.x + size / 2) / size) == (boomX - size) / size && (pl.y + size / 2) / size == boomY / size) {
							pl.player = null;
							pl.lives --;
							if (pl.SPEED > 2)
							pl.SPEED -= 2;
						}
						
					}						
					if (isFree(boomY / size, (boomX - size) / size) == 2) {
						  br.scene[boomY/size][(boomX - size) / size] = item[numRand];
					}					
				}
				
				// right 1
				if (isFree(boomY / size, (boomX + size) / size) != 1) {
					if (isFree(boomY / size, (boomX + size) / size) == 0) {
						rightExplosion1 = image.getSubimage(3 * tileSize, 6 * tileSize, tileSize, tileSize);
						// tight 2
						if (isFree(boomY / size, (boomX + 2 * size) / size) != 1) {
							if (isFree(boomY / size, (boomX + 2 * size) / size) == 0)
								rightExplosion2 = image.getSubimage(4 * tileSize, 6 * tileSize, tileSize, tileSize);
							if (isFree(boomY / size, (boomX + 2 * size) / size) == 2) {
								  br.scene[boomY/size][(boomX + 2 * size) / size] = 0;
							}
						}
						
						// boom no trung nguoi
						if (((pl.x + size / 2) / size) == (boomX + 2 * size) / size && (pl.y + size / 2) / size == boomY / size) {
							pl.player = null;
							pl.lives --;
							if (pl.SPEED > 2)
							pl.SPEED -= 2;
						}
						if (((pl.x + size / 2) / size) == (boomX + size) / size && (pl.y + size / 2) / size == boomY / size) {
							pl.player = null;
							pl.lives --;
							if (pl.SPEED > 2)
							pl.SPEED -= 2;
						}
					}
						
					if (isFree(boomY / size, (boomX + size) / size) == 2) {
						  br.scene[boomY/size][(boomX + size) / size] = item[numRand];
					}					
				}
				
				// up 1
					if (isFree((boomY - size) / size, boomX / size) != 1) {
						if (isFree((boomY - size) / size, boomX / size) == 0) {
							upExplosion1 = image.getSubimage(2 * tileSize, 5 * tileSize, tileSize, tileSize);
							// up 2
							if (isFree((boomY - 2 * size) / size, boomX / size) != 1) {
								if (isFree((boomY - 2 * size) / size, boomX / size) == 0)
									upExplosion2 = image.getSubimage(2 * tileSize, 4 * tileSize, tileSize, tileSize);
								if (isFree((boomY - 2 * size) / size, boomX / size) == 2) {
									 br.scene[(boomY - 2 * size) / size][(boomX) / size] = 0;
									}
							}
							
							// boom no trung nguoi
							if (((pl.x + size / 2) / size) == boomX / size && (pl.y + size / 2) / size == (boomY - size) / size) {
								pl.player = null;
								pl.lives --;
								if (pl.SPEED > 2)
									pl.SPEED -= 2;
							}
							if (((pl.x + size / 2) / size) == boomX / size && (pl.y + size / 2) / size == (boomY - 2 * size) / size) {
								pl.player = null;
								pl.lives --;
								if (pl.SPEED > 2)
									pl.SPEED -= 2;
							}
						}
							
						if (isFree((boomY - size) / size, boomX / size) == 2) {
							br.scene[(boomY - size) / size][boomX / size] = item[numRand];
							}						
					}
					
				// down 1
					if (isFree((boomY + size) / size, boomX / size) != 1) {
						if (isFree((boomY + size) / size, boomX / size) == 0) {
							downExplosion1 = image.getSubimage(2 * tileSize, 7 * tileSize, tileSize, tileSize);
							// up 2
							if (isFree((boomY + 2 * size) / size, boomX / size) != 1) {
								if (isFree((boomY + 2 * size) / size, boomX / size) == 0)
									downExplosion2 = image.getSubimage(2 * tileSize, 8 * tileSize, tileSize, tileSize);
								if (isFree((boomY + 2 * size) / size, boomX / size) == 2) {
									 br.scene[(boomY + 2 * size) / size][(boomX) / size] = 0;
								}
							}
							
							// boom no trung nguoi
							if (((pl.x + size / 2) / size) == boomX / size && (pl.y + size / 2) / size == (boomY + size) / size) {
								pl.player = null;
								pl.lives --;
								if (pl.SPEED > 2)
									pl.SPEED -= 2;
							}
							if (((pl.x + size / 2) / size) == boomX / size && (pl.y + size / 2) / size == (boomY + 2 * size) / size) {
								pl.player = null;
								pl.lives --;
								if (pl.SPEED > 2)
									pl.SPEED -= 2;
							}
						}
							
						if (isFree((boomY + size) / size, boomX / size) == 2) {
							br.scene[(boomY + size) / size][boomX / size] = item[numRand];
							}					
					}							
					
			}
			
								
			}

	}

	public void draw(Graphics2D g2) {
		
			// in ra qua boom
			g2.drawImage(boom, boomX, boomY, size, size, null);	
			
			
			// no
			g2.drawImage(midExplosion, boomX, boomY, size, size, null);
			g2.drawImage(leftExplosion1, boomX - size, boomY, size, size, null);
			g2.drawImage(leftExplosion2, boomX - 2 * size, boomY, size, size, null);
			g2.drawImage(rightExplosion1, boomX + size, boomY, size, size, null);
			g2.drawImage(rightExplosion2, boomX + 2 * size, boomY, size, size, null);
			g2.drawImage(upExplosion1, boomX, boomY - size, size, size, null);
			g2.drawImage(upExplosion2, boomX, boomY - 2 * size, size, size, null);
			g2.drawImage(downExplosion1, boomX, boomY + size, size, size, null);
			g2.drawImage(downExplosion2, boomX, boomY + 2 * size, size, size, null);
			
			
	}
}
