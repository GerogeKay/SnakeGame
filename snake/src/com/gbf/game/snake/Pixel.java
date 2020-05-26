package com.gbf.game.snake;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public abstract class Pixel {
	static final int width = Constants.WIDTH/Constants.PIXEL-25/Constants.PIXEL;
	static final int height = Constants.HEIGHT/Constants.PIXEL-65/Constants.PIXEL;
	static final int shapeX = 10/Constants.PIXEL;
	static final int shapeY = 40/Constants.PIXEL;
	int x,y;
	Color c ;
	public Pixel(int x, int y ) {
		super();
		this.x = x;
		this.y = y;
	}
	public abstract void setColor() ;

	public void drawPixel(Graphics g){
		
		g.fillRect(x*Constants.PIXEL, y*Constants.PIXEL, Constants.PIXEL, Constants.PIXEL);
	}
	public static void drawPixel(int x,int y, Graphics g){
		
		g.fillRect(x*Constants.PIXEL, y*Constants.PIXEL, Constants.PIXEL, Constants.PIXEL);
	}
	public static void drawPixel(int x,int y, Graphics g , Color c){
		g.setColor(c);
		g.fillRect(x*Constants.PIXEL, y*Constants.PIXEL, Constants.PIXEL, Constants.PIXEL);
	}
	public Rectangle getRect(int x, int y) {
		return new Rectangle(x*Constants.PIXEL, y*Constants.PIXEL, Constants.PIXEL, Constants.PIXEL);
	}
	
}
