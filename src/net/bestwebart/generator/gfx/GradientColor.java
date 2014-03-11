package net.bestwebart.generator.gfx;

import java.awt.Color;

public class GradientColor {
    
    private final Color gStart, gEnd;
             
    public GradientColor(Color gStart, Color gEnd) {
	this.gStart = gStart;
	this.gEnd = gEnd;
    }
    
    private Color getColor(float t) {
	float u = 1 - t;
	Color color = new Color(
		(int) (gStart.getRed() * u + gEnd.getRed() * t), 
		(int) (gStart.getGreen() * u + gEnd.getGreen() * t), 
		(int) (gStart.getBlue() * u + gEnd.getBlue() * t), 255);
	return color;
    }

    public int[] createGradient(float[][] pNoise) {
	
	int w = pNoise.length;
	int h = pNoise[0].length;
	int[] pixels = new int[w * h];
	
	for (int i = 0; i < w; i++) {
	    for (int j = 0; j < h; j++) {
		pNoise[i][j] = (pNoise[i][j] < 0) ? Math.abs(pNoise[i][j]) : pNoise[i][j]; 
		pixels[i + j * w] = getColor(pNoise[i][j]).getRGB();
	    }
	}
	return pixels;
    }

}
