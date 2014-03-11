package net.bestwebart.generator.perlinNoise;

import java.util.Random;

public class PerlinNoise {

    private final Random rand;
    private final int w, h;
    private double whiteNoise[][];

    public PerlinNoise(int w, int h) {
	this.w = w;
	this.h = h;
	whiteNoise = new double[w][h];
	rand = new Random();
	generateWhiteNoise();
    }
    
    private void generateWhiteNoise() {
	for (int i = 0; i < w; i++) {
	    for (int j = 0; j < h; j++) {
		whiteNoise[i][j] = rand.nextInt(2) - 1;
	    }
	}
    }
    
    
    private double interpolate(double x, double y, double alpha) {
	double ft = alpha * Math.PI;
	double f = (1 - Math.cos(ft)) * 0.5;
	return  x * (1 - f) + y * f; 
    }
    
    private double noise(int x, int y) {
	if (x > 0 && x < w && y > 0 && y < h) {
	    return whiteNoise[x][y];
	} else {
	    return 0d;
	}
    }
    
    private double smoothNoise(int x, int y) {
	double corners = (noise(x - 1, y - 1) + noise(x + 1, y - 1) + noise(x + 1, y + 1) + noise(x - 1, y + 1)) / 16;
	double sides = (noise(x - 1, y) + noise(x, y - 1) + noise(x, y + 1) + noise(x + 1, y)) / 8;
	double center = noise(x, y) / 4;
	
	return (corners + sides + center);
	

    }
    
    private double interpolatedNoise(double x, double y) {
	int xi = (int) x;
	double fx = x - xi;
	
	int yi = (int) y;
	double fy = y - yi;
	
	double v1 = smoothNoise(xi, yi);
	double v2 = smoothNoise(xi + 1, yi);
	double v3 = smoothNoise(xi, yi + 1);
	double v4 = smoothNoise(xi + 1, yi + 1);
	
	double i1 = interpolate(v1, v2, fx);
	double i2 = interpolate(v3, v4, fx);
	double i3 = interpolate(i1, i2, fy);

	return i3;
    }
    
    private double perlinNoise(int x, int y) {
	double total = 0d;
	double persistance = 0.9d;
	int octaves = 7;
	
	for (int i = 0; i < octaves; i++) {
	    
	    double frequency = 0.005d;
	    double amplitude = 0.1d;
	    total += (interpolatedNoise(x * frequency, y * frequency) * amplitude);
	    
	}
	
	return total;
    }
    
    
    public float[][] generateNoise() {
	float perlinNoise[][] = new float[this.w][this.h];
	
	for (int i = 0; i < perlinNoise.length; i++) {
	    for (int j = 0; j < perlinNoise[0].length; j++) {
		perlinNoise[i][j] = (float) perlinNoise(i, j) % 1;
	    }
	}
	
	return perlinNoise;
    }
    
}
