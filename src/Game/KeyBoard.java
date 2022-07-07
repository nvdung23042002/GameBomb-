package Game;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.concurrent.TimeUnit;

public class KeyBoard  implements KeyListener {
    public final int SPEED = 4;
    private boolean right, left, up, down;
    boolean bang, cheat, speaker;
    
    /**
     * constructor.
     */
    public KeyBoard() {
    }
    
    public boolean isLeft() {
        return left;
    }

    public void setLeft(boolean left) {
        this.left = left;
    }

    public boolean isUp() {
        return up;
    }

    public void setUp(boolean up) {
        this.up = up;
    }

    public boolean isDown() {
        return down;
    }

    public void setDown(boolean down) {
        this.down = down;
    }

    public boolean isRight() {
        return right;
    }

    public void setRight(boolean right) {
        this.right = right;
    }
    
    // duoc trieu hoi khi 1 key duoc nhan
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            this.right = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            this.left = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_UP) {
            this.up = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            this.down = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            bang = true;
            
        }
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            cheat = true;
            
        }
        if (e.getKeyCode() == KeyEvent.VK_P) {
        	speaker = true;
        }
    }
    
    //duoc trieu hoi khi 1 key tha ra
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            this.right = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            this.left = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_UP) {
            this.up = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            this.down = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
        	bang = false;
		}
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            cheat = false;
        }
    }
    
    //duoc trieu hoi khi 1 key duoc go
    

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
}  