package com.gbf.game.snake;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.util.Iterator;

public class Snake extends Pixel{
	Body body;//指向身体
	Direction direction = Direction.right; //默认方向为右
	int lives ;//生命数量
	boolean live ;//是否存活
	
	public Snake(int x, int y) {
		super(x, y);
		setColor();
		body = new Body(x-1, y, this);
		body.points.add(new Point(x-2, y));
		live = false;
		lives = 1;
	}
	
	@Override
	public void setColor() {
		this.c = Color.RED;//红色的头
	}
	/**
	 * 增加一截
	 */
	public void addBody() {
		Point lastPoint = (Point) body.points.getLast().clone();
		body.points.add(lastPoint);
	}
	
	public void move(Graphics g) {
		int x = this.x, y = this.y;
		switch (direction) {
		case left:
			if(this.x>shapeX)
				this.x --;
			else
				this.x = width+shapeX;
			break;
		case right:
			if(this.x< width+shapeX)
				this.x ++;
			else
				this.x = shapeX;
			break;
		case up:
			if(this.y >shapeY)
				this.y--;
			else
				this.y = height +shapeY;
			break;
		case down:
			if(this.y < height +shapeY)
				this.y ++;
			else
				this.y = shapeY;
			break;
		default:
			break;
		}
		drawSnake(x, y, g);
	}

	private void drawSnake(int x, int y, Graphics g) {
		Color c = g.getColor();
		g.setColor(this.c);
		drawPixel(g);
		g.setColor(c);
		body.drawBody(x, y, g);
	}
	
	public void drawSnake(Graphics g) {
		Color c = g.getColor();
		g.setColor(this.c);
		drawPixel(g);
		g.setColor(c);
		body.drawBody(g);
	}

	public void chageDirection(KeyEvent e) {
		Direction old = direction;
		if(live) {
			switch (e.getKeyCode()) {
			case KeyEvent.VK_LEFT:
				if(direction != Direction.right)
					direction = Direction.left;
				break;
			case KeyEvent.VK_RIGHT:
				if(direction != Direction.left)
					direction = Direction.right;
				break;
			case KeyEvent.VK_UP:
				if(direction != Direction.down)
					direction = Direction.up;
				break;
			case KeyEvent.VK_DOWN:
				if(direction != Direction.up)
					direction = Direction.down;
				break;
			}
		}
		else {
			if(lives>0) {
				lives --;
				live = true;
			}
		}
		if(old != direction) {
			GameFrame.repaint_time = 1;
		}
	}

	public boolean hitSelf() {
		boolean eatSelf = false;
		Iterator<Point> i = body.points.iterator();
		while(i.hasNext()) {
			Point b = i.next();
			if(x == b.x && y ==b.y) {
				eatSelf = true;
				break;
			}
		}
		return eatSelf;
		
	}
}
