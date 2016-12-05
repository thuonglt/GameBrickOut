package game;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Ball extends Objects {
	private int radius;
	private int speedX = 1;
	private int speedY = -1;
	private boolean die = false;

	// Khởi tạo Ball
	public Ball(int x, int y, int radius) {
		this.x = x;
		this.y = y;
		this.radius = radius;
	}

	/*
	 * Kiểm tra Ball<Hình tròn> va chạm với 1 vật thể hình chữa nhật<Pad,Brick>!
	 * Trả về các giá trị 1,2,3,4 tương ứng va chạm với các bề mặt vật thể! ..
	 * Trả về 0 nếu không va chạm!
	 */
	public int testCollision(Rectangle rec) {

		int keyTest = 0;
		int x1, y1;

		int top = rec.y;
		int bottom = rec.y + rec.height;
		int left = rec.x;
		int right = rec.x + rec.width;

		x1 = this.x;
		y1 = this.y;

		if (this.x < left) {
			x1 = left;
		}
		if (this.x > right) {
			x1 = right;
		}
		if (this.y < top) {
			y1 = top;
		}
		if (this.y > bottom) {
			y1 = bottom;
		}

		int dX = Math.abs((x1 - this.x));
		int dY = Math.abs((y1 - this.y));

		if ((dX * dX + dY * dY) <= (this.radius * this.radius) && x < left) {
			keyTest = 1; // Va chạm mặt trái vật thể

		} else if ((dX * dX + dY * dY) <= (this.radius * this.radius)
				&& (y < top)) {
			keyTest = 2; // Va chạm mặt phía trên vật thể

		} else if ((dX * dX + dY * dY) <= (this.radius * this.radius)
				&& (x > right)) {
			keyTest = 3; // Va chạm mặt phải vật thể

		} else if ((dX * dX + dY * dY) <= (this.radius * this.radius)
				&& (y > bottom)) {
			keyTest = 4; // Va chạm mặt phía dưới vật thể

		} else {
			keyTest = 0; // Không va chạm
		}
		return keyTest;
	}

	// Update lại speed Ball sau khi va chạm với Pad!
	public void updateCollistionPad(Pad pad) {
		// Va chạm 2 bên của Pad
		if (this.testCollision(pad.getRectagle()) == 1
				|| this.testCollision(pad.getRectagle()) == 3) {
			this.setSpeedX(this.getSpeedX() * -1);
			// Va chạm mặt trên Pad
		} else if (this.testCollision(pad.getRectagle()) == 2) {
			// speedBall = speedBall + speedPad
			this.setSpeedX(this.getSpeedX() + pad.getSpeedX());
			this.setSpeedY(this.getSpeedY() * -1);
		}
	}

	// Update lại speed Ball sau khi va chạm với Brick!
	public void updateCollistionBrick(Brick brick) {
		// Va chạm 2 bên Brick
		if (this.testCollision(brick.getRectagle()) == 1
				|| this.testCollision(brick.getRectagle()) == 3) {
			this.setSpeedX(this.getSpeedX() * -1);
			// Va chạm mặt trên và mặt dưới
		} else if (this.testCollision(brick.getRectagle()) == 2
				|| this.testCollision(brick.getRectagle()) == 4) {
			this.setSpeedY(this.getSpeedY() * -1);
		}

	}

	// Update lại tạo độ Ball
	public void update() {
		x += speedX;
		y += speedY;

		if (x < LEFT || x > (RIGHT + LEFT)) {
			speedX *= -1;
		}
		if (y < TOP) {
			speedY *= -1;
		}

		if (y > (BOTTOM + TOP)) {
			setDie(true);
		}

	}

	// Vẽ Ball
	public void paint(Graphics2D g) {
		g.setColor(Color.BLUE);
		g.fillOval(x - radius, y - radius, radius * 2, radius * 2);
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

	public int getRadius() {
		return radius;
	}

	public void setRadius(int radius) {
		this.radius = radius;
	}

	public int getSpeedX() {
		return speedX;
	}

	public void setSpeedX(int speedX) {
		this.speedX = speedX;
	}

	public int getSpeedY() {
		return speedY;
	}

	public void setSpeedY(int speedY) {
		this.speedY = speedY;
	}

	public boolean isDie() {
		return die;
	}

	public void setDie(boolean die) {
		this.die = die;
	}

}
