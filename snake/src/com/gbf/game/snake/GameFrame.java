package com.gbf.game.snake;

import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;


public class GameFrame extends Frame{

	/**
	 * 版本1.0
	 */
	private static final long serialVersionUID = 1L;
	//蛇蛇怪从屏幕中间诞生
	static Snake s = new Snake(Constants.WIDTH/2/Constants.PIXEL,Constants.HEIGHT/2/Constants.PIXEL);
	static Food food =new Food();
	Image bg = GameUtil.getImage("images/bg.jpg");
	static int repaint_time = 0;
	/**
	 * 键盘监听内部类
	 */
	class KeyMonitor extends KeyAdapter{

		@Override
		public void keyPressed(KeyEvent e) {
			s.chageDirection(e);
		}
	}
	/**
	 * 缓冲
	 */
	private Image offSCreenImage = null;
	private int score = 0;
	
	public void update(Graphics g) {
		if(offSCreenImage == null) 
			offSCreenImage = this.createImage(Constants.WIDTH, Constants.HEIGHT);
		Graphics gOff = offSCreenImage.getGraphics();
		paint(gOff);
		g.drawImage(offSCreenImage, 0, 0, null);
	}
	/**
	 * 画笔
	 */
	public void paint(Graphics g) {
		g.drawImage(bg, 0, 0, null);
		
		if(s.live) {
			s.move(g);
			
		}
		else {
			s.drawSnake(g);
		}
		if(food.x==s.x&& food.y == s.y) {
			food.randFood(s);
			score ++;
			s.addBody();
		}
		food.drawFood(g);
		if(s.hitSelf()) {
			s.live = false;
			g.drawString("游戏结束", 200, 250);
		}
		g.drawString("得分："+score, 50, 50);
		
	}
	/**
	 * 重画线程
	 */
	class PaintThread extends Thread{
		@Override
		public void run() {
			while (true) {
				repaint();
				try {
					repaint_time = 80;
					while (repaint_time>0) {
						Thread.sleep(1);
						repaint_time --;
					}
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
	/**
	 * 出现游戏窗口
	 */
	void LaunchFrame() {
		Point p = new Point(100,100);
		this.setTitle("贪吃蛇v1.0");
		this.setLocation(p);
		this.setSize(Constants.WIDTH, Constants.HEIGHT);
		this.setResizable(false);
		this.setVisible(true);
		this.addWindowListener(new WindowAdapter() {

			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
			
		});
		
		new PaintThread().start();//启动重画线程
		addKeyListener(new KeyMonitor());//给窗口增加键盘的监听
	}
	/**
	 * main函数
	 * @param args
	 */
	public static void main(String[] args) {
		food.randFood(s);
		GameFrame F = new GameFrame();
		F.LaunchFrame();
	}
	
}
