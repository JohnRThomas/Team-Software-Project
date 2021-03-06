package tsp.gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class HUD {
	public BufferedImage hpImg;

	public HUD(){
		try {
			hpImg = ImageIO.read(new File("./res/images/HP.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void draw(Graphics g, Canvas canvas){
		int x = 60, y = 500, w = 150, h = 20;
		g.setColor(new Color(200,200,200,200));

		g.fillRoundRect(x, y, 1+w, h, 10, 10);
		if(canvas.player.currentHealth >= 50){
			try{
				g.setColor(new Color((int)(255*((100.0-((double)canvas.player.currentHealth))/50.0)), 255, 0));
			}catch(Exception e){}

		}else{
			try{
				g.setColor(new Color(255, (int)(255*((((double)canvas.player.currentHealth))/50.0)), 0));
			}catch(Exception e){}
		}

		g.fillRoundRect(x+(int)(w*((100.0-((double)canvas.player.currentHealth))/100.0)), y, 1+(int)(w*(((double)canvas.player.currentHealth)/100.0)), h, 10, 10);
		g.setColor(Color.black);
		g.drawRoundRect(x, y, w, h, 10, 10);
		g.drawImage(hpImg, x-50, y, null);

	}
}
