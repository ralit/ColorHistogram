package org.ralit.colorhistogram;

import static org.ralit.colorhistogram.ImageUtility.*;
import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ColorHistogram {
	
	private Image 入力画像;
	private int[][][] histogram;
	
	public ColorHistogram(String 入力画像パス, String 出力ヒストグラムパス, String 可視化ディレクトリ, int x, int y, int w, int h) throws IOException {
		入力画像 = readFile(入力画像パス);
		histogram = new int[32][32][32];
		for(int b = 0; b < 32; b++) {
			for(int g = 0; g < 32; g++) {
				for(int r = 0; r < 32; r++) {
					histogram[b][g][r] = 0;
				}
			}
		}
		
		int blur = 1;
		for(int dy = 0; dy < h; dy++) {
			for(int dx = 0; dx < w; dx++) {
				int pixel = 入力画像.p[y+dy][x+dx];
				int r = r(pixel) / 8;
				int g = g(pixel) / 8;
				int b = b(pixel) / 8;
				for(int db = -blur; db <= blur; db++) {
					if(b + db < 0 || b + db >= 32) { continue; }
					for(int dg = -blur; dg <= blur; dg++) {
						if(g + dg < 0 || g + dg >= 32) { continue; }
						for(int dr = -blur; dr <= blur; dr++) {
							if(r + dr < 0 || r + dr >= 32) { continue; }
							histogram[b+db][g+dg][r+dr] += (3 - Math.abs(db)) + (3 - Math.abs(dg)) + (3 - Math.abs(dr)) - 2;
						}
					}
				}
			}
		}
		writeHistogram(出力ヒストグラムパス);
		writeHistogramPoints(histogram, 可視化ディレクトリ);
	}
	
	public Image getImage() {
		return 入力画像;
	}
	
	public int[][][] getHistogram() {
		return histogram;
	}
	
	private void writeHistogram(String filepath) throws IOException {
		File file = new File(filepath);
		BufferedWriter writer = new BufferedWriter(new FileWriter(file));
		for(int b = 0; b < 32; b++) {
			for(int g = 0; g < 32; g++) {
				for(int r = 0; r < 32; r++) {
					writer.write(histogram[b][g][r] + " ");
					writer.write("(" + r + ", " + g + ", " + b + ")");
					writer.newLine();
				}
			}
		}
		writer.close();
	}
	
	private Image readFile(String file) throws IOException {
		BufferedImage read = ImageIO.read(new File(file));
		int w = read.getWidth();
		int h = read.getHeight();
		int[][] RGB = new int[h][w];
		for (int y = 0; y < h; y++) {
			for (int x = 0; x < w; x++) {
				RGB[y][x] = read.getRGB(x, y);
			}
		}
		read = null;
		return new Image(w, h, RGB);
	}
	

}
