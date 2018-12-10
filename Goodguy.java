package game;
import java.awt.Image;
import java.awt.Toolkit;

public class Goodguy {

		private int xCoord = 0;
		private int yCoord = 0;
		private int width = 0;
		private int height = 0;
		private Image img;
		public int facing = 1;
		
		public Goodguy() {
			setxCoord(10);
			setyCoord(10);
			setWidth(30);
			setHeight(30);
			setImg("../Images/rightp.png");
		}
		
		public Goodguy(int x, int y, int w, int h, String imgpath) {
			setxCoord(x);
			setyCoord(y);
			setWidth(w);
			setHeight(h);
			setImg(imgpath);
		}
		public Goodguy(int x, int y, int w, int h, String imgpath,int f) {
			setxCoord(x);
			setyCoord(y);
			setWidth(w);
			setHeight(h);
			setImg(imgpath);
			this.facing=f;
		}
		public void moveIt (int direction) {
			int speed = 20;
			int x = getxCoord();
			int y = getyCoord();
			
			if (direction == 39) {
			x= x +speed;
			facing=1;
			if (x > 950) {x = x - speed;}
			setxCoord(x);
			setImg("Images/rightp.png");
			} else if (direction==37) {
				if (x<0) { x = x+speed;}
				x = x - speed;
				facing=3;
				setxCoord(x);
				setImg("Images/leftp.png");
			} else if (direction ==38) {
				if (y<0) {y=y+speed;}
				y=y - speed;
				facing=0;
			
				setyCoord(y);
				setImg("Images/upp.png");
			} else if (direction == 40) {
				if (y>700) { y = y - speed;}
				y = y + speed;
				facing=2;
				setyCoord(y);
				setImg("Images/downp.png");
			}
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
			return width;
		}

		public int getHeight() {
			return height;
		}
		
		public void setWidth(int width) {
			this.width = width;
		}


		public void setHeight(int height) {
			this.height = height;
		}
}
