package net.bestwebart.generator;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;
import javax.swing.JPanel;

import net.bestwebart.generator.gfx.GradientColor;
import net.bestwebart.generator.perlinNoise.PerlinNoise;

public class Generator {
    
    private final int WIDTH = 600;
    private final int HEIGHT = 600;
    private final int SCALE = 1;
    
    private JFrame frame;
    private PerlinNoise pN;
    private GradientColor gC;
    private Image image;
    private JPanel pane;
    
    private int pixels[];
        
    public Generator() {

	frame = new JFrame("Perlin Noise test");
	frame.setResizable(false);
	frame.setPreferredSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	pN = new PerlinNoise(WIDTH, HEIGHT);
	gC = new GradientColor(Color.black, Color.white);
	

	generateNewNoise();
	
	frame.add(pane);
	
	frame.pack();
	frame.setLocationRelativeTo(null);
	frame.setVisible(true);

    }
    
    private Image getImage(int[] pixels, int w, int h) {
	BufferedImage img = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
	for (int x = 0; x < w; x++) {
	    for (int y = 0; y < h; y++) {
		img.setRGB(x, y, pixels[x + y * w]);
	    }
	}
	return img;
    }
    
    private void generateNewNoise() {
	pixels = gC.createGradient(pN.generateNoise());
	
	image = getImage(pixels, WIDTH, HEIGHT);
			
	pane = new JPanel() {

	    private static final long serialVersionUID = 1L;

	    protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(image, 0, 0, null);
	    }
	};
    }
    

    public static void main(String args[]) {
	new Generator();
    }
}
