package game;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

@SuppressWarnings("serial")
public class MainGame extends JFrame implements Runnable {

	final int TOP = 50;
	final int BOTTOM = 500;
	final int LEFT = 50;
	final int RIGHT = 500;
	final int WIDTH_BRICK = 20;
	final int HEIGH_BRICK = 15;
	private Pad pad;
	private Ball ball;
	private Brick[] brick;

	private Graphics2D g;
	private BufferStrategy strategy;
	private ArrayList<Point> arrBrick;
	private int score = 0;
	private boolean victory = false;

	public Ball getBall() {
		return ball;
	}

	public void setBall(Ball ball) {
		this.ball = ball;
	}

	public boolean isVictory() {
		return victory;
	}

	public void setVictory(boolean victory) {
		this.victory = victory;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public MainGame(ArrayList<Point> arr) {
		super("Brick Out");
		setSize(600, 600);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		addWindowListener(new WindowAdapter() {

			@Override
			public void windowClosed(WindowEvent e) {
				super.windowClosed(e);
				// setVisible(false);
			}

		});
		addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
			}

			@Override
			public void keyPressed(KeyEvent e) {
				pad.keyPressed(e);
			}

			@Override
			public void keyReleased(KeyEvent e) {
				pad.keyReleased(e);
			}

		});
		score = 0;
		pad = new Pad(100, 400, 100, 30);
		ball = new Ball(200, 200, 10);
		this.arrBrick = arr;
		brick = new Brick[arrBrick.size()];
		for (int i = 0; i < arrBrick.size(); i++) {
			Point point = arrBrick.get(i);
			brick[i] = new Brick(point.x, point.y, WIDTH_BRICK, HEIGH_BRICK);
		}

		setVisible(true);
		// Tạo 1 BufferStrategy và lấy Graphics
		createBufferStrategy(2);
		strategy = this.getBufferStrategy();
		g = (Graphics2D) strategy.getDrawGraphics();

		Thread th = new Thread(this);
		th.start();
	}

	@Override
	public void run() {
		while (true) {
			update();
			paintComponent(g);
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			if (ball.isDie() || victory) {
				break;
			}
		}
		if (victory) {
			JOptionPane.showMessageDialog(this, "You are VICTORY!\nScore: "
					+ score);
		} else {
			JOptionPane.showMessageDialog(this, "Game Over!\nScore: " + score);
		}
		this.setVisible(false);

	}

	public void paintComponent(Graphics2D g) {
		super.paint(g);
		g.setColor(Color.BLACK);
		g.drawRect(LEFT, TOP, RIGHT, BOTTOM);
		pad.paint(g);
		ball.paint(g);
		for (int i = 0; i < brick.length; i++) {
			if (!brick[i].isDestroy()) {
				brick[i].paint(g);
			}
		}
		strategy.show();
		Toolkit.getDefaultToolkit().sync();
	}

	public void update() {
		int dem = 0;
		// Kiểm tra va chạm với Brick và update lại speed!
		for (int i = 0; i < brick.length; i++) {
			if (!brick[i].isDestroy()) {
				ball.updateCollistionBrick(brick[i]);
				dem++;
			}
		}
		// Nếu ăn hết brick thì vitory = true;
		if (dem == 0) {
			victory = true;
		}
		// Nếu có va chạm với Brick thì set Destroy = true, update laị Score;
		for (int i = 0; i < brick.length; i++) {
			if (ball.testCollision(brick[i].getRectagle()) != 0
					&& !brick[i].isDestroy()) {
				brick[i].setDestroy(true);
				score += 10;
			}
		}
		// Kiểm tra va chạm với Pad và update lại speed!
		ball.updateCollistionPad(pad);

		ball.update();
		pad.update();
	}

	public static void main(String[] args) {
		ArrayList<Point> arrB = new ArrayList<Point>();
		for (int j = 0; j < 4; j++) {
			for (int i = 0; i < 7; i++) {
				Point poit = new Point(i * 70 + 50, 60 * (j + 1));
				// System.out.println(poit.x + ":" + poit.y);
				arrB.add(poit);
			}
		}

		new MainGame(arrB);

	}
}
