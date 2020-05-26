package com.gbf.game.snake;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.Iterator;

public class Food extends Pixel{

	
	public Food() {
		super(-1, -1);
		setColor();
	}
	
	private void setFood(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public void randFood(Snake s) {
		int x=0,y=0;
		int count=0;
		boolean unequ = true;
		while(unequ) {
			Iterator<Point> i = s.body.points.iterator();
			x = (int)(Math.random()*(width-shapeX)+shapeX);
			y = (int)(Math.random()*(height-shapeY)+shapeY);
			count ++;
			// System.out.println("Ëæ»ú´ÎÊý£º"+count);
			if(x==s.x&&y==s.y) {
				unequ = true;
				continue;
			}
			else {
				unequ = false;
			}
			while(i.hasNext()) {
				Point p = i.next();
				if(x==p.x&&y==p.y) {
					unequ = true;
					break;
				}
				else {
					unequ = false;
				}
			}
		}
		setFood(x,y);
	}
	@Override
	public void setColor() {
		c = Color.BLUE;
	}
	public void drawFood(Graphics g) {
		Color c = g.getColor();
		g.setColor(this.c);
		g.fillOval(x*Constants.PIXEL, y*Constants.PIXEL, Constants.PIXEL, Constants.PIXEL);
		g.setColor(c);
	}
	
}
