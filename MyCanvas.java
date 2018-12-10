package game;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;
import sun.audio.*;
import java.awt.Font;

public class MyCanvas extends Canvas implements KeyListener {
	Goodguy genji = new Goodguy(10,90,100,100, "Images/rightp.png");
	LinkedList badguys = new LinkedList();
	LinkedList knives = new LinkedList();
	Image menu = Toolkit.getDefaultToolkit().createImage("Images/1.jpg");
	Image background = Toolkit.getDefaultToolkit().createImage("Images/1-1.jpg");
	boolean bmenu = true;
	
	int level = 1;
	String sciencename = "Animalia";
	int time = 0;
	int kills = 0;
	
	public MyCanvas() {
		this.setSize(1200,900);
		this.addKeyListener(this);
		playIt("Audio/music.wav");
	    TimerTask repeatedTask = new TimerTask() {
            public void run() {
                for(int i = 0; i < badguys.size(); i++) {// draw bad guys
                    Badguy bg = (Badguy) badguys.get(i);
                    bg.setxCoord(bg.getxCoord() - 50);
                }
                repaint();
            }
        };
        Timer timer = new Timer("Timer");
         
        long delay  = 1000L;
        long period = 1000L;
        timer.scheduleAtFixedRate(repeatedTask, delay, period);
	} 
	public void respawn() {
		Random rand = new Random();
			int winwidth = this.getWidth()-50;
			int winheight = this.getHeight()-50;
			for(int i = 0; i<10; i++) {
				int rx = rand.nextInt(winwidth);
				Badguy bg = new Badguy (rx+1000, rand.nextInt(winheight)+100,50,50,"Images/badguy.png");
				Rectangle r = new Rectangle (rx,100,30,30);
				if (r.contains(genji.getxCoord(),genji.getyCoord())) {
					System.out.println("badguy on top of genji");
					continue;
				}
				badguys.add(bg);
			}
	}
	
	
	
	public void playIt(String Audio) {
		
		try {
			InputStream in = new FileInputStream(Audio);
			AudioStream as = new AudioStream(in);
			AudioPlayer.player.start(as);
		} catch (IOException e) {
			System.out.println(e);
		}
	}
	@Override
	public void paint (Graphics g) {
		
		if (bmenu) {
			g.drawImage(menu,0,0,1200,900,this);
		} else {	
		time++;
		g.drawImage(background, 0, 0, 1200, 900, this);
		Font myFont = new Font("Helvetica", 1, 50);
		g.setFont(myFont);
		g.drawString(String.valueOf(time/400), 900, 70);
		g.drawImage(genji.getImg(), genji.getxCoord(), genji.getyCoord(), genji.getHeight(), genji.getWidth(), this);
		System.out.println(badguys.size());
		if (badguys.size() == 0) {
			level = level + 1;
			if (level == 2) sciencename = "Animalia";
			if (level == 4) sciencename = "Chordata";
			if (level == 6)	sciencename = "Mammalia";
			if (level == 8) sciencename = "Primates";
			if (level == 10) sciencename = "Haplorhini";
			if (level == 12) sciencename = "Hominidae";
			if (level == 14) sciencename = "Homo";
			if (level == 20) sciencename = "Homo sapiens";
			respawn();
			}
		for(int a = 0; a < badguys.size(); a++) {
			Badguy bg = (Badguy) badguys.get(a);
			g.drawImage(bg.getImg(), bg.getxCoord(), bg.getyCoord(), bg.getHeight(), bg.getWidth(), this);
			Rectangle r = new Rectangle(bg.getxCoord(), bg.getyCoord(), bg.getHeight(), bg.getWidth());
			for(int j = 0; j < knives.size(); j++) {
				Knives k = (Knives) knives.get(j); 
				if (k.getxCoord() > this.getWidth()) { knives.remove(k); }
				if(k.facing==1) {
					k.setxCoord(k.getxCoord() + 1);
				}else if (k.facing==3) {
					k.setxCoord(k.getxCoord()-1);
				}else if (k.facing==2) {
					k.setyCoord(k.getyCoord()+1);
				}else {
					k.setyCoord(k.getyCoord()-1);
				}
				g.drawImage(k.getImg(), k.getxCoord(), k.getyCoord(), k.getWidth(), k.getHeight(), this);
				
				Rectangle kr = new Rectangle(k.getxCoord(),k.getyCoord(),k.getWidth(),k.getHeight());
				if (kr.intersects(r)) {
					playIt("Audio/hit2.wav");
					badguys.remove(a);
					knives.remove(j);
					kills = kills + 1;
				}
				repaint();
			}
		}
		g.setFont(new Font("TimesRoman", Font.PLAIN, 20));
		g.drawString("Sciencename: " + String.valueOf(sciencename), 20, 65);
		g.drawString("Level: " + String.valueOf(level).toString(), 20, 45);
		g.drawString("High score: " + String.valueOf(kills).toString(), 20, 85);
		repaint();
		}
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
		
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		if (badguys.size()==2) {
    		respawn();
		}
		
		if (e.getKeyCode() == 10 && bmenu) {
			bmenu = false;
		}
		
		System.out.println(e);
		genji.moveIt(e.getKeyCode());
		
		if (e.getKeyCode()==70) {
			Knives knife= new Knives(genji.getxCoord(),genji.getyCoord(),30,30,"Images/bullet.png",genji.facing);
			knives.add(knife);
		}
		genji.moveIt(e.getKeyCode());
		Rectangle ggrect = new Rectangle(genji.getxCoord(),genji.getyCoord(),genji.getWidth(),genji.getHeight());
		for(int i= 0; i< badguys.size(); i++) {
			Badguy bg = (Badguy) badguys.get(i);
			Rectangle r = new Rectangle (bg.getxCoord(), bg.getyCoord(), bg.getWidth(), bg.getHeight());
			if (r.intersects(ggrect)) {
				playIt("Audio/hit2.wav");
				System.out.println("badguy hit by genji");
				badguys.remove(bg);
			}
		}
		repaint();
	}

	@Override
	public void keyReleased(KeyEvent e) {
	}
}