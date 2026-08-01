import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Scalar;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

class text_draw {
	public static void main(String[] args) {
		// CRITICAL: You must load the native library before using OpenCV classes
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        System.out.println("OpenCV Loaded Successfully!");
        
        Scalar white = new Scalar(255, 255, 255);
        Scalar black = new Scalar(0, 0, 0);
        
        Mat img = new Mat(200, 200, CvType.CV_8UC3, new Scalar(255, 255, 255));
        Imgproc.line(img, new Point(100, 0), new Point(100, 200), black, 1);
        Imgproc.line(img, new Point(0, 100), new Point(200, 100), black, 1);
        Imgproc.putText(img, "ABCDEF", new Point(100, 100), Imgproc.FONT_HERSHEY_SIMPLEX, 1, 
        		black);

        Imgcodecs.imwrite("text.png", img);
	}
}