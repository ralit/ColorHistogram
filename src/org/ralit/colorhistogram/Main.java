package org.ralit.colorhistogram;

import static org.ralit.colorhistogram.ImageUtility.*;

import java.io.IOException;
import java.io.ObjectInputStream.GetField;

public class Main {

	private static String HOME = System.getProperty("user.home");
	
	public static void main(String[] args) throws IOException {
//		ColorHistogram colorHistogram = new ColorHistogram(
//				HOME + "/Desktop/Snakes/p388-nemu-yumemi.jpg",
//				HOME + "/Desktop/Snakes/color_histogram.txt",
//				HOME + "/Desktop/Snakes/color_histogram/",
//				200, 174,
//				88, 336
//		);
		
//		ColorHistogram colorHistogram = new ColorHistogram(
//				HOME + "/Desktop/Snakes/p388-nemu-yumemi.jpg",
//				HOME + "/Desktop/Snakes/color_histogram_2.txt",
//				HOME + "/Desktop/Snakes/color_histogram_2/",
//				28, 31,
//				88, 336
//		);
		
//		ColorHistogram colorHistogram = new ColorHistogram(
//				HOME + "/Desktop/Snakes/p388-nemu-yumemi.jpg",
//				HOME + "/Desktop/Snakes/color_histogram_3.txt",
//				HOME + "/Desktop/Snakes/color_histogram_3/",
//				225, 469,
//				120, 91
//		);
		
//		ColorHistogram colorHistogram = new ColorHistogram(
//				HOME + "/Desktop/Snakes/p388-nemu-yumemi.jpg",
//				HOME + "/Desktop/Snakes/color_histogram_4.txt",
//				HOME + "/Desktop/Snakes/color_histogram_4/",
//				190, 123 ,99, 398,
//				0, 0, 126, 366
//		);
//		writeBitmap(colorHistogram.getImage().p, HOME + "/Desktop/Snakes/p388-nemu-yumemi_colorHistogram.jpg");
		
		ColorHistogram colorHistogram = new ColorHistogram(
				HOME + "/Desktop/Snakes/DSC_0027.JPG",
				HOME + "/Desktop/Snakes/color_histogram_5.txt",
				HOME + "/Desktop/Snakes/color_histogram_5/",
				668, 355 ,249, 777,
				0, 0, 500, 677
		);
		writeBitmap(colorHistogram.getImage().p, HOME + "/Desktop/Snakes/DSC_0027_colorhistogram.JPG");
		
		
		System.out.println("‚¨‚í‚è");
	}
}


