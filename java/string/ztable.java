import java.util.ArrayList;

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
	
	// Configuration==================================
    int nW = 1280;  /// 720p Standard
	int nH = 720;
	int nMarginL = 100;  // Left
	int nMarginT = 200;  // Top
	int nWCH = 50; // Character - width 
	int nWCH_mgr = nWCH / 5; // Margin of Character box
	
	Scalar clwhite = new Scalar(255, 255, 255);
	Scalar clyel = new Scalar(0,2255,255);
	Scalar cl_blue = new Scalar(255, 0, 0);
	Scalar cl_red = new Scalar(0, 0, 255);
	Scalar cl_green = new Scalar(0, 255, 0);
	Scalar cl_lgreen = new Scalar(100, 100, 150);
	Scalar cl_lblue = new Scalar(150, 100, 100);
	// Configuration END ==============================
	
	public void draw_overview(Mat img, String str, int[] Z, int l, int r,  int ix) {

		int n = str.length();
		/*==============================
    	 * Visualize a scene 
    	 * v1) Highlight Current step (hight light selected)
    	 * v1) Draw characters box
    	 * v2) Draw ztable 
    	 * v3) Visualize [l,r] range
    	 * v5) Transition states  
    	 *==============================*/
    	// v1) High light selected 
    	Imgproc.rectangle(img, 
    			new Rect(new Point(nMarginL + ix * nWCH , nMarginT), 
						 new Size(nWCH, nWCH)), 
    			clyel, 
    			Imgproc.FILLED);
    	Imgproc.rectangle(img, 
    			new Rect(new Point(nMarginL + ix*nWCH, nMarginT + 3 * nWCH), 
    					 new Size(nWCH, nWCH)), 
    			clyel, 
    			Imgproc.FILLED);
    	
    	//v1) Character box
    	for (int i=0; i<n; i++) {
    		Imgproc.rectangle(img, new Rect(new Point(nMarginL + i * nWCH , nMarginT), 
    								new Size(nWCH, nWCH)), 
    									clwhite ,1);
    		Imgproc.putText(img, str.substring(i, i+1), 
    						new Point(nMarginL + i* nWCH + nWCH_mgr, nMarginT + nWCH_mgr), 
    						Imgproc.FONT_HERSHEY_SIMPLEX, 1, clyel);
    	}
    	// v2) ztable
    	for (int i=0; i<n;i++) {
    		Imgproc.rectangle(img, new Rect(
    									 new Point(nMarginL + i*nWCH, nMarginT + 3 * nWCH), 
    									 new Size(nWCH, nWCH)), 
    				clwhite, 1);
    		Imgproc.putText(img, String.format("%d", Z[i]), 
    				new Point(nMarginL + i*nWCH + nWCH_mgr, nMarginT + 3 * nWCH + nWCH_mgr), 
    				Imgproc.FONT_HERSHEY_SCRIPT_SIMPLEX
    				, 1, clyel);
    	}
    	
    	// v3) [l,r] range
    	if (l >= 1) {
    		Imgproc.rectangle(img, 
    				new Rect(
    						new Point(nMarginL + l * nWCH, nMarginT +  nWCH), 
    						new Size( (r-l+1) * nWCH, nWCH)), 
    				cl_lgreen,
    				Imgproc.FILLED);

    		Imgproc.putText(img, String.format("[l=%d; r=%d]", l, r), 
    				new Point(nMarginL + l * nWCH + nWCH_mgr, nMarginT + nWCH + nWCH_mgr),
    				Imgproc.FONT_HERSHEY_SIMPLEX, 
    				1, 
    				clyel);
    	}
	}
	
	public void draw_compare_prefix(Mat img, String str, int[] Z, int l, int r,  int ix, int j2) {
		/*============================================================
		 * Visualize (ix>r) case; 
		 * 	(c1) j2 > ix: Found prefix
		 * 		(1.1) Highlight prefix and prefix-matched (starting at ix)
		 * 		(1.2) Highlight updated Z[ix] 
		 * 	(c2) 
		 * 		(2.1) visualize zero matched prefix. (by red border)
		 * 	(c3) Highlight updated Z[ix] 
		 *============================================================*/
		
		// c1.
		if (j2 > ix) {
			// c1.1) Highlight matched
			// Prefix
			Imgproc.rectangle(img, 
					new Rect(
							new Point(nMarginL, nMarginT), 
							new Size((j2 - ix) * nWCH, nWCH)), 
					cl_blue, 
					2);
			
			// starting at i
			Imgproc.rectangle(img, 
					new Rect(
							new Point(nMarginL + ix * nWCH , nMarginT), 
							new Size((j2 - ix) * nWCH, nWCH)), 
					cl_blue, 
					2);
			
		}
		// c2) 
		else {
			// c2.1.)  Visualize zero matched prefix
			// Visualize mismatched character by Red border
			Imgproc.rectangle(img, 
					new Rect(
							new Point(nMarginL, nMarginT), 
							new Size(nWCH, nWCH)), 
					cl_red, 
					2);
			Imgproc.rectangle(img, 
					new Rect(
							new Point(nMarginL + ix * nWCH, nMarginT), 
							new Size(nWCH, nWCH)), 
					cl_red, 
					2);
		}
		
		// c3) Highlight updated Z[ix]
		Imgproc.rectangle(img, new Rect(
				 		new Point(nMarginL + ix*nWCH, nMarginT + 3 * nWCH), 
				 		new Size(nWCH, nWCH)), 
						cl_blue, 
						Imgproc.FILLED);
		Imgproc.putText(img, String.format("%d", Z[ix]), 
					new Point(nMarginL + ix*nWCH + nWCH_mgr, 
							  nMarginT + 3 * nWCH + nWCH_mgr), 
					Imgproc.FONT_HERSHEY_SCRIPT_SIMPLEX
					, 1, clyel);			
	}
	
	public void draw_ultilize_z_jumping(Mat img, String str, int[] Z, int l, int r,  int ix, int j1, int j2) {

		int lenbeta = r - ix + 1;
        int i0 = ix - l;

		/*============================================================
		 * Visualize ultilization
		 * v1) Visualize i0 value and Z[i0] value
		 *     (1.1) highlight lenbeta (filled)
		 *     (1.2) highlight i0 in two positions (filled)
		 *         
		 *     (1.3) Highlight Z[i0] by a border
		 * v2) Visualize case 1: Z[i0] < lenbeta 
		 *     (2.1) 
		 *     (2.2)  
		 *     		Character box starting at ix
		 *     		Z table starting at 0
		 * v3) Visualize case 2: visualize matching 
		 *============================================================*/
        
		// v1) 
		// (1.1) Highlight lenbeta 
		Imgproc.rectangle(img, 
				new Rect( new Point( nMarginL + ix * nWCH, nMarginT - nWCH/2), 
						    new Size( lenbeta * nWCH, nWCH / 2 )), 
				cl_lblue,
				Imgproc.FILLED);
		// (1.2) Highlight i0
		Imgproc.rectangle(img, 
				new Rect( new Point( nMarginL + l * nWCH, nMarginT - nWCH/2), 
						    new Size( i0 * nWCH, nWCH / 2 )), 
				cl_lgreen,
				Imgproc.FILLED); // At the top of characters box
		Imgproc.rectangle(img, 
				new Rect( new Point( nMarginL, nMarginT  + 3*nWCH - nWCH/2), 
						    new Size( i0 * nWCH, nWCH/2 )), 
				cl_lgreen,
				2); // At the to of Ztable
		
		// (1.3) Highlight Z[i0] 
		Imgproc.rectangle(img, 
				new Rect( new Point( nMarginL + i0 * nWCH, nMarginT  + 3*nWCH), 
						    new Size(nWCH, nWCH )), 
				cl_green,
				2);
		
		// v2) Case 1
		if (Z[i0] < lenbeta) {
			// Draw updated value 
			Imgproc.rectangle(img, 
					new Rect( new Point( nMarginL + ix * nWCH, nMarginT  + 3*nWCH), 
							    new Size(nWCH, nWCH )), 
					cl_blue,
					Imgproc.FILLED);
			
			Imgproc.putText(img, String.format("%d", Z[i0]), 
    				new Point(nMarginL + ix*nWCH + nWCH_mgr, nMarginT + 3 * nWCH + nWCH_mgr), 
    				Imgproc.FONT_HERSHEY_SCRIPT_SIMPLEX
    				, 1, clyel);
		}
	}
	
	public int ZTable(char[] S, int[] Z) {
	    int i = 1;
	    int i0;
	    int j2 = 0;
	    int j1 = 0;
	    int l = -1; 
	    int r = -1;
	    int lenbeta = 0;
	    int n = S.length;
	    // Visualization 
	    int img = 0; // image  index
	    
	    /*==================================================
	     * Put visualization 
	     * (a1) Visualize transaction selected index
	     * (a2) Visualize (i>r) case; Visualize Prefix match. Both-cases: 
	     * (a3) Visualize (i<=r) case;
	     * 		(3.1) visualize (lenbeta) and i0 and z[i0]
	     * 		(3.2) visualize Z[i0] < lenbeta
	     * 		(3.3) visualize z[i0] >= lenbeta 
	     *==================================================*/

	    while ( i < n ) {
	    	
	        if (i > r) {
	            j2 = i;
	            j1 = 0;
	        
	            /* Compare to the prefix */
	            while (S[j1] == S[j2] && j2 < n) { j1++; j2++;}

	            /* Found */
	            if (j2 > i) {
	                l = i;
	                r = j2 - 1;
	                Z[i] = j2 - i;
	            }
	            else {          
	                Z[i] = 0;
	            }
	        }
	        else {
	            lenbeta = r - i + 1;
	            i0 = i - l;

	            if (Z[i0] < lenbeta) {
	                Z[i] = Z[i0];
	                // l,r unchanged
	            }
	            else {
	                j2 = r + 1;
	                j1 = lenbeta + 1; // (r - l) + 1;
	            
	                while (S[j1] == S[j2] && j2 < n) {
	                    j1 ++;
	                    j2 ++;
	                }

	                l = i;
	                r = j2 - 1;
	                Z[i] = (j2 - i);
	            }
	        }
	        i++;
	    }
	    return 0;
	}
	
	public void ztable_visualize() {
		/*============================================================
         * 	Configuration
         *============================================================*/					
		String str = "abcdef";
		int n = str.length();
		Mat img = new Mat(nW, nH, CvType.CV_8UC3, new Scalar(0, 0, 0));
		int[] Z = new int[str.length()];
		int l = 0;
		int r = 0;
        
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
		
		int ix = 1; // Current step
        while (ix < n) {
        	// Update a scene
        	
        	/*==============================
        	 * Visualize a scene 
        	 * v1) Highlight Current step (hight light selected)
        	 * v1) Draw characters box
        	 * v2) Draw ztable 
        	 * v3) Visualize [l,r] range
        	 * v5) Transition states  
        	 *==============================*/
        	// v1) High light selected 
        	Imgproc.rectangle(img, 
        			new Rect(new Point(nMarginL + ix * nWCH , nMarginT), 
							 new Size(nWCH, nWCH)), 
        			clyel, 
        			Imgproc.FILLED);
        	Imgproc.rectangle(img, 
        			new Rect(new Point(nMarginL + ix*nWCH, nMarginT + 3 * nWCH), 
        					 new Size(nWCH, nWCH)), 
        			clyel, 
        			Imgproc.FILLED);
        	
        	//v1) Character box
        	for (int i=0; i<n; i++) {
        		Imgproc.rectangle(img, new Rect(new Point(nMarginL + i * nWCH , nMarginT), 
        								new Size(nWCH, nWCH)), 
        									clwhite ,1);
        		Imgproc.putText(img, str.substring(i, i+1), 
        						new Point(nMarginL + i* nWCH + nWCH_mgr, nMarginT + nWCH_mgr), 
        						Imgproc.FONT_HERSHEY_SIMPLEX, 1, clyel);
        	}
        	// v2) ztable
        	for (int i=0; i<n;i++) {
        		Imgproc.rectangle(img, new Rect(
        									 new Point(nMarginL + i*nWCH, nMarginT + 3 * nWCH), 
        									 new Size(nWCH, nWCH)), 
        				clwhite, 1);
        		Imgproc.putText(img, String.format("%d", Z[i]), 
        				new Point(nMarginL + i*nWCH + nWCH_mgr, nMarginT + 3 * nWCH + nWCH_mgr), 
        				Imgproc.FONT_HERSHEY_SCRIPT_SIMPLEX
        				, 1, clyel);
        	}
        	
        	// v3) [l,r] range
        	if (l >= 1) {
        		Imgproc.rectangle(img, 
        				new Rect(
        						new Point(nMarginL + l * nWCH, nMarginT +  nWCH), 
        						new Size( (r-l+1) * nWCH, nWCH)), 
        				cl_lgreen,
        				Imgproc.FILLED);

        		Imgproc.putText(img, String.format("[l=%d; r=%d]", l, r), 
        				new Point(nMarginL + l * nWCH + nWCH_mgr, nMarginT + nWCH + nWCH_mgr),
        				Imgproc.FONT_HERSHEY_SIMPLEX
        				, 1, clyel);
        	}

        	// v5) [l,r]
        }
	}
	
	public static void main(String[] args) {
		// CRITICAL: You must load the native library before using OpenCV classes
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        System.out.println("OpenCV Loaded Successfully!");
        
        ztable zt = new ztable();
        zt.ztable_visualize();
	}
}