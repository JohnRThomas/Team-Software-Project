package tsp.gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class HUD {
	public BufferedImage hpImg;
	
	public HUD(){
		//TODO init hpimg
	}
	
	public void draw(Graphics g, Canvas canvas){
		int x = 10, y = 500, w = 150, h = 20;
		g.setColor(new Color(200,200,200,200));
		g.fillRoundRect(x, y, w, h, 10, 10);
		if(canvas.player.currentHealth >= 50){
			g.setColor(new Color((int)(255*((100.0-((double)canvas.player.currentHealth))/50.0)), 255, 0));
		}else{
			g.setColor(new Color(255, (int)(255*((((double)canvas.player.currentHealth))/50.0)), 0));
		}
		g.fillRoundRect(x+(int)(w*((100.0-((double)canvas.player.currentHealth))/100.0)), y, 1+(int)(w*(((double)canvas.player.currentHealth)/100.0)), h, 10, 10);
		g.setColor(Color.black);
		g.drawRoundRect(x, y, w, h, 10, 10);
	}
}
