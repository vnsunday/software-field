import org.opencv.core.Core;
import org.opencv.core.Mat;

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
		int nW = 400;
		int nH = 400;
		
		String str = "abcdef";
		int n = str.length();        
        Mat img;
	}
}