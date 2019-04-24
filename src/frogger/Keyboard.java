package frogger;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Keyboard implements KeyListener{
	Frogger frog = null;
	
	public Keyboard(Frogger frog) {
		super();
		this.frog = frog;
	}
	@Override
	public void keyTyped(KeyEvent e) {
//		System.out.println("What");
	}

	@Override
	public void keyPressed(KeyEvent e) {
//		System.out.println("keyPressed = " + KeyEvent.getKeyText(e.getKeyCode()));
		frog.key(useKeys(e));
	}

	@Override
	public void keyReleased(KeyEvent e) {
//		System.out.println("keyReleased = " + KeyEvent.getKeyText(e.getKeyCode()));
	}
	
	public int[] useKeys(KeyEvent e) {
		String s = KeyEvent.getKeyText(e.getKeyCode());
		if ((s.equals("W")) || (s.equals("Up"))) {
			return new int[] {0, -1};
		}
		if ((s.equals("S")) || (s.equals("Down"))) {
			return new int[] {0, 1};
		}
		if ((s.equals("D")) || (s.equals("Right"))) {
			return new int[] {1, 0};
		}
		if ((s.equals("A")) || (s.equals("Left"))) {
			return new int[] {-1, 0};
		}
		return new int[] {0, 0};
	}
}
