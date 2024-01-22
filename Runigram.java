// This class uses the Color class, which is part of a package called awt,
// which is part of Java's standard class library.
import java.awt.Color;

/** A library of image processing functions. */
public class Runigram {

	public static void main(String[] args) {
	    
		//// Hide / change / add to the testing code below, as needed.
		
		// Tests the reading and printing of an image:	
		Color[][] tinypic = read("tinypic.ppm");
		print(tinypic);
		System.out.println();
		print(flippedHorizontally(tinypic));
		System.out.println();
		print(flippedVertically(tinypic));
		System.out.println();
		System.out.println();
		System.out.println();
		Color test = new Color (100,0,0);
		print(luminance(test));
		System.out.println();
		System.out.println();
		System.out.println();
		print(grayScaled(tinypic));
		System.out.println();
		print(scaled(tinypic,3,5));


		System.out.println();
		System.out.println();
		System.out.println();
		Color test1 = new Color (100,40,100);
		Color test2 = new Color (200,20,40);
		print(blend(test1,test2, 0.25));
		System.out.println();
		System.out.println();
		System.out.println();
		Color[][] sourceImage = read("cake.ppm");
		Color[][] targetImage = read("ironman.ppm");
		setCanvas(read("cake.ppm"));
		morph(sourceImage,targetImage, 50);
		print(read("cake.ppm"));



		// Creates an image which will be the result of various 
		// image processing operations:
		//Color[][] imageOut;

		// Tests the horizontal flipping of an image:
		//imageOut = flippedHorizontally(tinypic);
		//System.out.println();
		//print(imageOut);
		
		//// Write here whatever code you need in order to test your work.
		//// You can reuse / overide the contents of the imageOut array.*/
		





	}

	/** Returns a 2D array of Color values, representing the image data
	 * stored in the given PPM file. */
	public static Color[][] read(String fileName) {
		In in = new In(fileName);
		// Reads the file header, ignoring the first and the third lines.
		in.readString();
		int numCols = in.readInt();
		int numRows = in.readInt();
		in.readInt();
		// Creates the image array
		Color[][] image = new Color[numRows][numCols];


		// Reads the RGB values from the file, into the image array. 
		// For each pixel (i,j), reads 3 values from the file,
		// creates from the 3 colors a new Color object, and 
		// makes pixel (i,j) refer to that object.
		//// Replace the following statement with your code.
		for (int i = 0; i < numRows ;i++ ) 
		{
			for (int j = 0;j < numCols ;j++ ) 
			{
			  image[i][j] = new Color(in.readInt(),in.readInt(),in.readInt());
			}	
		}
		return image;
	}

    // Prints the RGB values of a given color.
	public static void print(Color c) {
	    System.out.print("(");
		System.out.printf("%3s,", c.getRed());   // Prints the red component
		System.out.printf("%3s,", c.getGreen()); // Prints the green component
        System.out.printf("%3s",  c.getBlue());  // Prints the blue component
        System.out.print(")  ");
       
	}

	// Prints the pixels of the given image.
	// Each pixel is printed as a triplet of (r,g,b) values.
	// This function is used for debugging purposes.
	// For example, to check that some image processing function works correctly,
	// we can apply the function and then use this function to print the resulting image.
	public static void print(Color[][] image) {
		//// Replace this comment with your code
		for (int i = 0; i < image.length ;i++ ) 
		{
			for (int j = 0;j < image[0].length ;j++ ) 
			{
				print(image[i][j]);	
			
			}	
		System.out.println();
		}

	}
	
	/**
	 * Returns an image which is the horizontally flipped version of the given image. 
	 */
	public static Color[][] flippedHorizontally(Color[][] image) {
		//// Replace the following statement with your code
		Color [][] flippedArray = new Color [image.length][image[0].length];
		int rows = image.length;
		int cols = image[0].length;
		for (int i = 0 ;i < rows ;i++ ) 
		{
			for (int j = 0;j < cols ;j++ ) 
			{
				flippedArray[i][j] = image[i][cols - 1 - j];			
			}	
		}
		return flippedArray;
	}
	
	/**
	 * Returns an image which is the vertically flipped version of the given image. 
	 */
	public static Color[][] flippedVertically(Color[][] image){
		//// Replace the following statement with your code
		Color [][] flippedArray = new Color[image.length][image[0].length];
		int rows = image.length;
		int cols = image[0].length;
		for (int i = 0 ;i < rows ;i++ ) 
		{
			for (int j = 0;j < cols ;j++ ) 
			{
				flippedArray[i][j] = image[rows - 1 - i][j];			
			}	
		}



		return flippedArray;
	}
	
	// Computes the luminance of the RGB values of the given pixel, using the formula 
	// lum = 0.299 * r + 0.587 * g + 0.114 * b, and returns a Color object consisting
	// the three values r = lum, g = lum, b = lum.
	public static Color luminance(Color pixel) {
		double newR = (pixel.getRed()*0.299);
		double newG = (pixel.getGreen()*0.587);
		double newB = (pixel.getBlue()*0.114);
		int greyScaled = (int) (newR + newG + newB);
		Color luminated = new Color(greyScaled, greyScaled, greyScaled);
		return luminated;
	}
	
	/**
	 * Returns an image which is the grayscaled version of the given image.
	 */
	public static Color[][] grayScaled(Color[][] image) {
		//// Replace the following statement with your code
		int rows = image.length;
		int cols = image[0].length;
		Color [][] greyScaledImage = new Color[image.length][image[0].length];
		for (int i = 0;i < rows ;i++ ) 
		{
			for (int j = 0;j < cols ;j++ ) 
			{
				greyScaledImage[i][j] = luminance(image[i][j]);			
			}	
		}
		return greyScaledImage;
	}	
	
	/**
	 * Returns an image which is the scaled version of the given image. 
	 * The image is scaled (resized) to have the given width and height.
	 */
	public static Color[][] scaled(Color[][] image, int width, int height) {
		//// Replace the following statement with your code
		Color [][] rescaled = new Color[height][width];
		int originalHeight = image.length;
		int originalWidth = image[0].length;
		for (int i = 0; i < height ;i++ ) 
		{
			for (int j = 0; j < width ;j++ ) 
			{
				int heightIndex = (int) ((i * ((double)originalHeight))/height);
				int widthtIndex = (int) ((j * ((double)originalWidth))/width);
				rescaled[i][j] = image[heightIndex][widthtIndex];
			}

		}
		return rescaled;
	}
	
	/**
	 * Computes and returns a blended color which is a linear combination of the two given
	 * colors. Each r, g, b, value v in the returned color is calculated using the formula 
	 * v = alpha * v1 + (1 - alpha) * v2, where v1 and v2 are the corresponding r, g, b
	 * values in the two input color.
	 */
	public static Color blend(Color c1, Color c2, double alpha) {
		//// Replace the following statement with your code
		int newR = (int) (alpha * c1.getRed() + (1 - alpha) * c2.getRed());
		int newG = (int) (alpha * c1.getGreen() + (1 - alpha) * c2.getGreen());
		int newB = (int) (alpha * c1.getBlue() + (1 - alpha) * c2.getBlue());

		Color blendedColor = new Color(newR,newG,newB);


		return blendedColor;
	}
	
	/**
	 * Cosntructs and returns an image which is the blending of the two given images.
	 * The blended image is the linear combination of (alpha) part of the first image
	 * and (1 - alpha) part the second image.
	 * The two images must have the same dimensions.
	 */
	public static Color[][] blend(Color[][] image1, Color[][] image2, double alpha) {
		//// Replace the following statement with your code
		Color [][] blendedImage = new Color [image1.length][image1[0].length];
		for (int i = 0; i < blendedImage.length ;i++ ) 
		{
			for (int j = 0; j < blendedImage[0].length ;j++ ) 
			{
				blendedImage[i][j] = blend(image1[i][j],image2[i][j], alpha);			
			}	
		}
		return blendedImage;
	}

	/**
	 * Morphs the source image into the target image, gradually, in n steps.
	 * Animates the morphing process by displaying the morphed image in each step.
	 * Before starting the process, scales the target image to the dimensions
	 * of the source image.
	 */
	public static void morph(Color[][] source, Color[][] target, int n) {
		//// Replace this comment with your code
		if (source.length == target.length && source[0].length == target[0].length) 
		{
		  for (int i = 0; i < n ;i++ ) 
		  {
		  	source = blend(source,target,(n-i)/n);
		  }
		}
		else
		{
			Color[][] adjustedTarget = scaled(target,source[0].length,source.length);
			for (int i = 0; i < n ;i++ ) 
		    {
		    	source = blend(source,adjustedTarget,(n-i)/n);
		    }


		}
	}
	
	/** Creates a canvas for the given image. */
	public static void setCanvas(Color[][] image) {
		StdDraw.setTitle("Runigram 2023");
		int height = image.length;
		int width = image[0].length;
		StdDraw.setCanvasSize(height, width);
		StdDraw.setXscale(0, width);
		StdDraw.setYscale(0, height);
        // Enables drawing graphics in memory and showing it on the screen only when
		// the StdDraw.show function is called.
		StdDraw.enableDoubleBuffering();
	}

	/** Displays the given image on the current canvas. */
	public static void display(Color[][] image) {
		int height = image.length;
		int width = image[0].length;
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				// Sets the pen color to the pixel color
				StdDraw.setPenColor( image[i][j].getRed(),
					                 image[i][j].getGreen(),
					                 image[i][j].getBlue() );
				// Draws the pixel as a filled square of size 1
				StdDraw.filledSquare(j + 0.5, height - i - 0.5, 0.5);
			}
		}
		StdDraw.show();
	}
}

