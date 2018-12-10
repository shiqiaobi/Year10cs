package game;

import java.awt.Image;
import java.awt.Toolkit;

public class Knives {
	private int xCoord;
	private int yCoord;
	private int width;
	private int height;
	private Image img;
	public int facing = 1;
	
	public Knives() {
		setxCoord(20);
		setyCoord(30);
		setWidth(30);
		setHeight(30);
		setImg("Images/badguy.png");
	}
	
	public Knives(int x, int y, int w, int h, String imgpath) {
		setxCoord(x);
		setyCoord(y);
		setWidth(w);
		setHeight(h);
		setImg(imgpath);
	}
	
	public Knives(int x, int y, int w, int h, String imgpath, int f) {
		setxCoord(x);
		setyCoord(y);
		setWidth(w);
		setHeight(h);
		setImg(imgpath);
		this.facing = f;
	}
	
	public void setImg(String imgpath) {
		this.img = Toolkit.getDefaultToolkit().getImage(imgpath);
	}

	public Image getImg() {
		return img;
	}
	public int getxCoord() {
		return this.xCoord;
	}

	public int getyCoord() {
		return this.yCoord;
	}

	public void setxCoord(int x) {
		this.xCoord = x;
	}
	public void setyCoord(int y) {
		this.yCoord = y;
	}
	public int getWidth() {
		return this.width;
	}
	public int getHeight() {
		return this.height;
	}
	public void setWidth(int width) {
		this.width = width;
	}


	public void setHeight(int height) {
		this.height = height;
	}
	public void moveIt (int direction) {
		int speed = 20;
		int x = getxCoord();
	
	}
}
