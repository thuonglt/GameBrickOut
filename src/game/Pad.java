package game;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;

public class Pad extends Objects {
	private int x, y;
	private int width, height;
	private int speedX = 0;
	private Rectangle rectagle;

	// Khởi tạo Pad
	public Pad(int x, int y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		rectagle = new Rectangle(x, y, width, height);
		this.height = height;

	}

	// Update tọa độ Pad
	public void update() {
		x += speedX;
		if (x > ((RIGHT + LEFT) - getWidth())) {
			setX((RIGHT + LEFT) - getWidth());
		}
		if (x < LEFT) {
			setX(LEFT);
		}
		// set lại  Rectangle
		setRectagle(new Rectangle(this.x, this.y, this.width, this.height));
	}

	// Vẽ Pad
	public void paint(Graphics2D g) {
		g.setColor(Color.GREEN);
		g.fillRect(x, y, width, height);
	}

	// Sự kiện nhấn phím
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			speedX = -3;
		} else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			speedX = 3;
		} else {
			speedX = 0;
		}
	}

	// Sự kiện nhả phím
	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			speedX = 0;
		} else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			speedX = 0;
		}
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getSpeedX() {
		return speedX;
	}

	public void setSpeedX(int speedX) {
		this.speedX = speedX;
	}

	public void setRectagle(Rectangle rectagle) {
		this.rectagle = rectagle;
	}

	public Rectangle getRectagle() {
		return rectagle;
	}

}
