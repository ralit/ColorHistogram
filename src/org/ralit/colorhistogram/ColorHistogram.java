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
	private int[][][] histogram_fore;
	private int[][][] histogram_back;
	
	public ColorHistogram(String 入力画像パス, String 出力ヒストグラムパス, String 可視化ディレクトリ, int x, int y, int w, int h, int x1, int y1, int w1, int h1) throws IOException {
		入力画像 = readFile(入力画像パス);
		histogram_fore = new int[32][32][32];
		histogram_back = new int[32][32][32];
		for(int b = 0; b < 32; b++) {
			for(int g = 0; g < 32; g++) {
				for(int r = 0; r < 32; r++) {
					histogram_fore[b][g][r] = 0;
					histogram_back[b][g][r] = 0;
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
							histogram_fore[b+db][g+dg][r+dr] += (blur+1 - Math.abs(db)) + (blur+1 - Math.abs(dg)) + (blur+1 - Math.abs(dr)) - 2;
						}
					}
				}
			}
		}

		for(int dy = 0; dy < h1; dy++) {
			for(int dx = 0; dx < w1; dx++) {
				int pixel = 入力画像.p[y1+dy][x1+dx];
				int r = r(pixel) / 8;
				int g = g(pixel) / 8;
				int b = b(pixel) / 8;
				for(int db = -blur; db <= blur; db++) {
					if(b + db < 0 || b + db >= 32) { continue; }
					for(int dg = -blur; dg <= blur; dg++) {
						if(g + dg < 0 || g + dg >= 32) { continue; }
						for(int dr = -blur; dr <= blur; dr++) {
							if(r + dr < 0 || r + dr >= 32) { continue; }
							histogram_back[b+db][g+dg][r+dr] += (blur+1 - Math.abs(db)) + (blur+1 - Math.abs(dg)) + (blur+1 - Math.abs(dr)) - 2;
						}
					}
				}
			}
		}

		long fore_area = w * h;
		long back_area = w1 * h1;
		for(int dy = 0; dy < 入力画像.h; dy++) {
			for(int dx = 0; dx < 入力画像.w; dx++) {
				int pixel = 入力画像.p[dy][dx];
				int r = r(pixel) / 8;
				int g = g(pixel) / 8;
				int b = b(pixel) / 8;
				if((long)histogram_back[b][g][r] * fore_area > (long)histogram_fore[b][g][r] * back_area) {
					入力画像.p[dy][dx] = rgb(0, 255, 0);
				}
			}
		}
//		writeHistogram(出力ヒストグラムパス);
//		writeHistogramPoints(histogram_fore, 可視化ディレクトリ);
	}
	
	public Image getImage() {
		return 入力画像;
	}
	
	public int[][][] getHistogram_fore() {
		return histogram_fore;
	}

	public int[][][] getHistogram_back() {
		return histogram_back;
	}
	
	private void writeHistogram(String filepath) throws IOException {
		File file = new File(filepath);
		BufferedWriter writer = new BufferedWriter(new FileWriter(file));
		for(int b = 0; b < 32; b++) {
			for(int g = 0; g < 32; g++) {
				for(int r = 0; r < 32; r++) {
					writer.write(histogram_fore[b][g][r] + " ");
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
