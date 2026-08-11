import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.io.File;
import java.io.IOException;

import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

class text_draw {
	
	static void LoadFont(String path, float fontsize) throws IOException, FontFormatException {
		File ff = new File(path);
		Font baseFont = Font.createFont(Font.TRUETYPE_FONT, ff);
	}
	
	static BufferedImage matToBFI(Mat mat)	{
		int nW = mat.cols();
		int nH = mat.rows();
		int nC = mat.channels();
		
		int nT = (nC == 1) ? BufferedImage.TYPE_BYTE_GRAY : 
							BufferedImage.TYPE_3BYTE_BGR;
		BufferedImage img = new BufferedImage(nW, nH, nT);
		byte[] targetPixels =((DataBufferByte)img.getRaster().getDataBuffer()).getData();
		mat.get(0,0, targetPixels);
		
		return img;
	}
	
	static void draw_text_multilines(Mat img, int x, int y, 
										String[] lines, 
										int nline,
										int fontFace,
										double scale,
										int thickness,
										Scalar color) 
	{
		double dnlr = 1.5; // New-line ratio 
		int[] baseline = new int[1];
		int onerow;
		Size tsize =  Imgproc.getTextSize("ABCabc", fontFace, scale, thickness, baseline);
		
		
		onerow = (int)( dnlr * tsize.height );
		// Imgproc.getTextSize(null, x, y, nL, null)
		for (int i=0; i<nline;i++) {
			Imgproc.putText(img, lines[i], 
					new Point(x , y + i * onerow), fontFace, scale, color, thickness);
		}
	}
	
	public static void main(String[] args) {
		// CRITICAL: You must load the native library before using OpenCV classes
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        System.out.println("OpenCV Loaded Successfully!");
        
        Scalar white = new Scalar(255, 255, 255);
        Scalar black = new Scalar(0, 0, 0);
        
        Mat img = new Mat(200, 200, CvType.CV_8UC3, new Scalar(255, 255, 255));
        Imgproc.line(img, new Point(100, 0), new Point(100, 200), black, 1);
        Imgproc.line(img, new Point(0, 100), new Point(200, 100), black, 1);
        
        String[] lines = new String[] { "HI", "I am well"}; 
        
        /*
        Imgproc.putText(img, "ABCD\r\n\r\nEFGH", new Point(100, 100), Imgproc.FONT_HERSHEY_DUPLEX, 
        		1, 
        		black,
        		1,
        		Imgproc.LINE_AA,
        		true);   // false: Top-Left 
        */
        draw_text_multilines(img, 100, 100, lines, 2, Imgproc.FONT_HERSHEY_DUPLEX, 1, 1, black);
        Imgcodecs.imwrite("text.png", img);
	}
}