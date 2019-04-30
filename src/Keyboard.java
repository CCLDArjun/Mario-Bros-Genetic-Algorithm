

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Keyboard implements KeyListener{
	Mario m = null;
	int[] r = new int[3];

	public Keyboard(Mario m) {
		super();
		this.m = m;
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
//		System.out.println("What");
	}

	@Override
	public void keyPressed(KeyEvent e) {
//		System.out.println("keyPressed = " + KeyEvent.getKeyText(e.getKeyCode()));
		useKeys(e, 1);
	}

	@Override
	public void keyReleased(KeyEvent e) {
//		System.out.println("keyReleased = " + KeyEvent.getKeyText(e.getKeyCode()));
		useKeys(e, 0);
	}
	
	public void useKeys(KeyEvent e, int yup) {
		String s = KeyEvent.getKeyText(e.getKeyCode());
		if ((s.equals("W")) || (s.equals("Up"))) {
			r[1] = -1 * yup;
		}
		if ((s.equals("S")) || (s.equals("Down"))) {
			r[1] = 1 * yup;
		}
		if ((s.equals("D")) || (s.equals("Right"))) {
			r[0] = 1 * yup;
		}
		if ((s.equals("A")) || (s.equals("Left"))) {
			r[0] = -1 * yup;
		}
		if (s.equals("Z")) {
			r[2] = 1 * yup;
		}
	}
}
