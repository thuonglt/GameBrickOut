package game;

import java.awt.Graphics2D;

public abstract class Objects {
	protected int x;
	protected int y;
	final int TOP = 50;
	final int BOTTOM = 500;
	final int LEFT = 50;
	final int RIGHT = 500;

	public abstract void paint(Graphics2D g);
	public abstract void update();
}
