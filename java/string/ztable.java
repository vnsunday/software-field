import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.highgui.HighGui;
import org.opencv.imgproc.Imgproc;

class ztable {
	public void Table(String str) {
	}
	
	public static void main(String[] args) {
		// CRITICAL: You must load the native library before using OpenCV classes
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        System.out.println("OpenCV Loaded Successfully!");
        
        
        
        /*============================================================
         * 	Configuration
         *============================================================*/
		
        int nW = 1280;  /// 720p Standard
		int nH = 720;
		int nMarginL = 100;  // Left
		int nMarginT = 100;  // Top
		int nWCH = 50; // Character - width 
		int nWCH_mgr = nWCH / 5; // Margin of Character box
		int nFF;
		
		Scalar clwhite = new Scalar(255, 255, 255);
		Scalar clyel = new Scalar(0,2255,255);
		
				
		String str = "abcdef";
		int n = str.length();
		Mat img = new Mat(nW, nH, CvType.CV_8UC3, new Scalar(0, 0, 0));
        
        /*========================================
         * Scripts/Scenes
         * S1. Print all characters and Table
         *========================================*/

        /*------------------------------
         * Structure of Scenes (Note: 1-index)
         *    str: string
         *    n: length of (str)
         *    i: current processing index
         *    steps: [1..n]
         *    [l,r]: right-most endpoint of a 2-box beginning >= i
         *    ztable
         *------------------------------*/
        while (true) {
        	// Update a scene
        	
        	/*==============================
        	 * Visualize a scene 
        	 * v1) Draw characters box
        	 * v2) Draw ztable 
        	 *==============================*/
        	//v1) Character box
        	for (int i=0; i<n; i++) {
        		Imgproc.rectangle(img, new Rect(new Point(nMarginL + i * nWCH , nMarginT), new Size(nWCH, nWCH)), clwhite ,1);
        		Imgproc.putText(img, str.substring(i, i+1), new Point(nMarginL + i* nWCH + nWCH_mgr, nMarginT + nWCH_mgr), Imgproc.FONT_HERSHEY_SIMPLEX, 1, clyel);
        	}
        	// v2) ztable
        	for (int i=0; i<n;i++) {
        	
        	}
        	
        }
	}
}