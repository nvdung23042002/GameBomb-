package Game;

import javax.swing.JFrame;

public class Main {

	public Main() {
		// TODO Auto-generated constructor stub
	}
	
	public static void main(String[] args) {
        Main m = new Main();
        m.round1();
    }
	
	public void round1() {

		Board d = new Board();
        JFrame w = new JFrame("Bomberman");
        w.add(d);
        w.pack();
        w.setVisible(true);
	}
	
}
