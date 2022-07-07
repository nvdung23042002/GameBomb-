package Game;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;



import java.awt.event.KeyListener;

public class Board extends JPanel implements Runnable {

    boolean isRunning;
    Thread thread;
    BufferedImage view, concreteTile, blockTile, player;
    BufferedImage heart;
    BufferedImage boots;
    BufferedImage door;
    BufferedImage des1, des2;
    int[][] scene;
    int tileSize = 16, rows = 13, columns = 15;
    
    final int SCALE = 3;
    final int WIDTH = tileSize * SCALE * columns + 200;
    final int HEIGHT = tileSize * SCALE * rows;
    
    int level = 1;
    public final int SPEED = 4; //
    
    
    KeyBoard input = new KeyBoard(); // 
    Boom b1 = new Boom(this, input); // boom
    PlayerGame playerGame = new PlayerGame(this, input, b1); // nhan vat chinh
    Sprite sp1 = null;  // enemy
    Sprite sp2 = null;
    Sprite sp3 = null;
    Sprite sp4 = null;
    Sprite sp5 = null;
    Sprite sp6 = null;
    
    Sound sound;
    
    //static JFrame w;
      
    public Board() {
        setPreferredSize(new Dimension(WIDTH, HEIGHT));        
        this.addKeyListener(input);        
    }
    
   /* public static void main(String[] args) {
        Board d = new Board();
        w = new JFrame("Bomberman");
        w.add(d);
        w.pack();
        w.setLocationRelativeTo(d);
        w.setVisible(true);
    } */
    
    public void start() {
        try {
            view = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);

            BufferedImage spriteSheet = ImageIO.read(new File("C:\\Users\\hscng\\OneDrive\\Desktop\\game\\JavaGame_BomberMan\\res\\sheets.png")); 

            concreteTile = spriteSheet.getSubimage(4 * tileSize, 3 * tileSize, tileSize, tileSize);
            blockTile = spriteSheet.getSubimage(3 * tileSize, 3 * tileSize, tileSize, tileSize);
            player = spriteSheet.getSubimage(4 * tileSize, 0, tileSize, tileSize);
            heart = ImageIO.read(new File("C:\\Users\\hscng\\OneDrive\\Desktop\\game\\JavaGame_BomberMan\\res\\heart.png"));
            boots = ImageIO.read(new File("C:\\Users\\hscng\\OneDrive\\Desktop\\game\\JavaGame_BomberMan\\res\\item_shoe.gif"));
            door = ImageIO.read(new File("C:\\Users\\hscng\\OneDrive\\Desktop\\game\\JavaGame_BomberMan\\res\\door.png"));
            des1 = ImageIO.read(new File("C:\\Users\\hscng\\OneDrive\\Desktop\\game\\JavaGame_BomberMan\\res\\des1.png"));
            des2 = ImageIO.read(new File("C:\\Users\\hscng\\OneDrive\\Desktop\\game\\JavaGame_BomberMan\\res\\des2.png"));
            //playerGame.start();
           
            // load map
            
    	    //round1();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void round1() {
    	Map map = new Map();
    	scene = map.levelMap();
    	sp1 = new Sprite(this,playerGame, 11, 1,1);  // enemy
        sp2 = new Sprite(this,playerGame, 9, 3,2);
    	sp3 = new Sprite(this,playerGame, 9, 11,1);
    	sp4 = new Sprite(this,playerGame, 13, 7,2);
    }
    
    public void round2() {
    	Map map = new Map();
    	scene = map.levelMap2();
    	sp1 = new Sprite(this,playerGame, 11, 1,2);  // enemy
        sp2 = new Sprite(this,playerGame, 10, 3,2);
    	sp3 = new Sprite(this,playerGame, 9, 11,2);
    	sp4 = new Sprite(this,playerGame, 1, 7,2);
    	sp5 = new Sprite(this,playerGame, 9, 11,2);
    	sp6 = new Sprite(this,playerGame, 13, 11,2);
    }
    public void draw() {
        // ????????????????????????????????????????????
        Graphics2D g2 = (Graphics2D) view.getGraphics();
        g2.setColor(new Color(56, 135, 0));
        g2.fillRect(0, 0, WIDTH, HEIGHT);
        
        
        int size = tileSize * SCALE;
        
        for (int i = 0; i < columns; i++) {
            for (int j = 0; j < rows; j++) {
                if (scene[j][i] == 1) {
                    g2.drawImage(blockTile, i * size, j * size, size, size, null);
                } else if (scene[j][i] == 2) {
                	g2.drawImage(concreteTile, i * size, j * size, size, size, null);
                } else if (scene[j][i] == 4) {
                	g2.drawImage(heart, i * size, j * size, size, size, null);
                } else if (scene[j][i] == 5) {
                	g2.drawImage(boots, i * size, j * size, size, size, null);
                } else if (scene[j][i] == 6) {
                	g2.drawImage(door, i * size, j * size, size, size, null);
                }
            }
        } 
        // in nhan vat
        g2.drawImage(des1, 730, 1 * size, 178, 180, null);
        g2.drawImage(des2, 780, 10 * size, 90, 112, null);
        playerGame.draw(g2);
        sp1.draw(g2);
        sp2.draw(g2);
        if (sp3 != null)
        	sp3.draw(g2);
        if (sp4 != null)
        	sp4.draw(g2);
        if (sp5 != null)
        	sp5.draw(g2);
        if (sp6 != null)
        	sp6.draw(g2);
        b1.draw(g2);
        Graphics g = getGraphics();
        g.drawImage(view, 0, 0, WIDTH, HEIGHT, null);
        
    }
    public void update() {
    	 playerGame.update();
    	 
    	 
    	 //set action enemi
    	 sp1.setActionLeftRight();
    	 sp2.setActionLeftRight();
    	 if (sp3 != null)
    		 sp3.setActionUpDown();
    	 if (sp4 != null)
    		 sp4.setActionUpDown();
    	 if (sp5 != null)
    		 sp5.setActionLeftRight();
    	 if (sp6 != null)
    		 sp6.setActionUpDown();
    	 
    	 
    	 //boom update
    	 b1.update(playerGame);
    	 
    	 //enemi update
    	 sp1.update(b1);
    	 sp2.update(b1);
    	 if (sp3 != null)
    		 sp3.update(b1);
    	 if (sp4 != null)
    		 sp4.update(b1);
    	 if (sp5 != null)
    		 sp5.update(b1);
    	 if (sp6 != null)
    		 sp6.update(b1);
    }
    
    
    //?????????????????????????????????????????????????
    @Override
    public void addNotify() {
        super.addNotify();
        if (thread == null) {
            thread = new Thread(this);
            isRunning = true;
            thread.start();
        }
    }
    
    //????????????????????????????????????????????????????
    @Override
    public void run() {
        try {
            requestFocus();
            start();
            round1();
            sound = new Sound("C:\\Users\\hscng\\OneDrive\\Desktop\\game\\JavaGame_BomberMan\\res\\audio\\background_music.wav");
            sound.loop();
            while (isRunning) { 
            	if (input.speaker == true) {
                	sound.stop();
                }
                update();
                draw();
                Thread.sleep(1000 / 60);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    
}