package com.gbf.game.snake;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.Iterator;
import java.util.LinkedList;

public class Body{
	Snake headSnake;
	Color c;
	LinkedList<Point> points;
	
	public Body(int x, int y, Snake s) {
		super();
		points = new LinkedList<Point>();
		points.add(new Point(x,y));
		setColor();
		headSnake = s;
	}

	public void setColor() {
		c = Color.GRAY;
	}
	
	void move(int x, int y) {
		int tempX, tempY;
		for(Iterator<Point> i =  points.iterator(); i.hasNext();) {
			Point p = i.next();
			tempX = p.x;
			tempY = p.y;
			p.x = x;
			p.y = y;
			x = tempX;
			y = tempY;
		}
	}
	
	public void drawBody(int x, int y, Graphics g) {
		move(x, y);
		drawBody(g);
	}
	
	public void drawBody(Graphics g) {
		Color c = g.getColor();
		g.setColor(this.c);
		for(Iterator<Point> i =  points.iterator(); i.hasNext();) {
			Point p = i.next();
			Pixel.drawPixel(p.x,p.y,g);
		}
		g.setColor(c);
	}
}
