package org.ralit.colorhistogram;

import static org.ralit.colorhistogram.ImageUtility.rgb;
import static org.ralit.colorhistogram.ImageUtility.writePoints;

import java.io.IOException;

public class Main {

	private static String HOME = System.getProperty("user.home");
	
	public static void main(String[] args) throws IOException {
		ColorHistogram colorHistogram = new ColorHistogram(
				HOME + "/Desktop/Snakes/p388-nemu-yumemi.jpg",
				HOME + "/Desktop/Snakes/color_histogram.txt",
				HOME + "/Desktop/Snakes/color_histogram/",
				200, 174,
				88, 336
				);
		
		System.out.println("‚¨‚í‚è");
	}
}


