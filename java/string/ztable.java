import java.util.ArrayList;
import java.util.Arrays;

import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.highgui.HighGui;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

class ztable {
	
	// Configuration==================================
    int nW = 1280;  /// 720p Standard
	int nH = 720;
	int nMarginL = 200;  // Left
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
	Scalar cl_lcyan = new Scalar(200, 200, 100);
	
	Scalar cl_lr_range = clyel;
	Scalar cl_lr_newrange = cl_blue;
	Scalar cl_current_index = cl_lcyan;
	Scalar cltext = clwhite;
	
	boolean FLAG_VIDEO = true;
	String FILE_PREFIX = "ztable_";
	// Configuration END ==============================
	
	void draw_text_multilines(Mat img, int x, int y, 
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
	
	public void draw_overview(Mat img, String str, int[] Z, int l, int r,  int ix) {
		
		int n = str.length();
		
		if (!FLAG_VIDEO) return;
		/*==============================
    	 * Visualize a scene 
    	 * v0) Title 
    	 * v1) Highlight Current step (hight light selected)
    	 * v1) Draw characters box
    	 * v2) Draw ztable 
    	 * v3) Visualize [l,r] range
    	 * v4) Status text: 
    	 *  	Scanning current index
    	 *==============================*/
		
		// v0) Title
		Imgproc.putText(img, "String", 
				new Point(nMarginL - 150, nMarginT + nWCH - nWCH_mgr), 
				Imgproc.FONT_HERSHEY_DUPLEX, 
				1, 
				clwhite);
		Imgproc.putText(img, "ZTable", 
				new Point(nMarginL - 150, nMarginT + 4 * nWCH - nWCH_mgr), 
				Imgproc.FONT_HERSHEY_DUPLEX, 
				1, 
				clwhite);
				
    	// v1) High light selected 
    	Imgproc.rectangle(img, 
    			new Rect(new Point(nMarginL + ix * nWCH , nMarginT), 
						 new Size(nWCH, nWCH)), 
    			cl_current_index, 
    			Imgproc.FILLED);
    	Imgproc.rectangle(img, 
    			new Rect(new Point(nMarginL + ix*nWCH, nMarginT + 3 * nWCH), 
    					 new Size(nWCH, nWCH)), 
    			cl_current_index, 
    			Imgproc.FILLED);
    	
    	//v1) Character box
    	for (int i=0; i<n; i++) {
    		Imgproc.rectangle(img, new Rect(new Point(nMarginL + i * nWCH , nMarginT), 
    								new Size(nWCH, nWCH)), 
    									clwhite ,1);
    		Imgproc.putText(img, str.substring(i, i+1), 
    						new Point(nMarginL + i* nWCH + nWCH_mgr, nMarginT + nWCH - nWCH_mgr), 
    						Imgproc.FONT_HERSHEY_DUPLEX, 1, clyel);
    	}
    	// v2) ztable
    	for (int i=0; i<n;i++) {
    		Imgproc.rectangle(img, new Rect(
    									 new Point(nMarginL + i*nWCH, nMarginT + 3 * nWCH), 
    									 new Size(nWCH, nWCH)), 
    				clwhite, 1);
    		Imgproc.putText(img, String.format("%d", Z[i]), 
    				new Point(nMarginL + i*nWCH + nWCH_mgr, nMarginT + 3 * nWCH + nWCH - nWCH_mgr), 
    				Imgproc.FONT_HERSHEY_DUPLEX
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

    		Imgproc.putText(img, "[l,r]", 
    				new Point(nMarginL + l * nWCH + nWCH_mgr, nMarginT + 2 * nWCH - nWCH_mgr),
    				Imgproc.FONT_HERSHEY_DUPLEX, 
    				1, 
    				clyel);
    	}
    	
    	// v4) Status text 
    	String status = String.format("Scanning index: %02d", ix);

    	Imgproc.putText(img, status, 
				new Point(nMarginL, nMarginT + 5 * nWCH), 
				Imgproc.FONT_HERSHEY_DUPLEX, 
				1, 
				cltext,
				1,
				Imgproc.LINE_8);
	}
	
	public void draw_compare_prefix(Mat img, String str, int[] Z, int l_prev, int r_prev, int l, int r,  int ix, int j2) {
		/*============================================================
		 * Visualize (ix>r) case; 
		 * 	(c1) j2 > ix: Found prefix
		 * 		(1.1) Highlight prefix and prefix-matched (starting at ix)
		 * 		(1.2) Highlight updated Z[ix] 
		 * 	(c2) 
		 * 		(2.1) visualize zero matched prefix. (by red border)
		 * 	(c3) Highlight updated Z[ix]
		 * 	(c4) Status Text 
		 *============================================================*/
		if (!FLAG_VIDEO) return;
		
		String[] status = new String[10]; // Ignore the 1st line
		int nS = 0;
		status[nS++] = ""; // Let create Empty line for a space for status from draw_overview
		
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
			status[nS++] = String.format("    (i=%02d>r=%02d)", ix, r_prev);
			status[nS++] = String.format("    Prefix-Matched: %s", str.substring(ix, j2));
			status[nS++] = String.format("    [l,r] updated = [%02d;%02d]", ix, j2-1);
			status[nS++] = String.format("    Z[%02d]=%02d", ix, j2-ix);
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
			
			status[nS++] = String.format("    (i=%02d>r=%02d)", ix, r);
			status[nS++] = String.format("    Prefix-Mismatch: %c != %c", str.charAt(0),str.charAt(ix));
			status[nS++] = String.format("    Z[%02d]=0", ix);
		}
		
		// c3) Highlight updated Z[ix]
		Imgproc.rectangle(img, new Rect(
				 		new Point(nMarginL + ix*nWCH, nMarginT + 3 * nWCH), 
				 		new Size(nWCH, nWCH)), 
						cl_blue, 
						Imgproc.FILLED);
		Imgproc.putText(img, String.format("%d", Z[ix]), 
					new Point(nMarginL + ix*nWCH + nWCH_mgr, 
							  nMarginT + 4 * nWCH - nWCH_mgr), 
					Imgproc.FONT_HERSHEY_DUPLEX
					, 1, clyel);		
		
		// c4) 
		draw_text_multilines(img, nMarginL, nMarginT + 5*nWCH, status, nS, Imgproc.FONT_HERSHEY_DUPLEX, 1, 1, clwhite);
	}
	
	public void draw_ultilize_z_jumping(Mat img, String str, int[] Z, int l_prev, int r_prev, int l, int r,  int ix, int i0, int j1, int j2, String strbeta) {
		if (!FLAG_VIDEO) return;
		
		int lenbeta = strbeta.length();
        String[] status = new String[10];
        int nS = 0;
        status[nS++] = ""; // Let create Empty line for a space for status from draw_overview

		/*============================================================
		 * Visualize ultilization
		 * v1) Visualize i0 value and Z[i0] value
		 *     (1.1) highlight lenbeta (filled)
		 *     (1.2) highlight i0 in two positions (filled)
		 *         
		 *     (1.3) Highlight Z[i0] by a border
		 * v2) Visualize case 1: Z[i0] < lenbeta 
		 *     (2.1) 
		 *     		Character box starting at ix
		 *     		Z table starting at 0
		 * v3) Visualize case 2: 
		 * 		(3.1)  
		 * 			3.1.1) Visualize matched string [r_prev+1,j2) and [i0+1,j1]
		 * 			3.1.2) Visualize unmatched character [r_prev+1] and [i0+1] 
		 * 		(3.2) Visualize updated [l,r] 
		 * v4) Status text
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
				new Rect( new Point( nMarginL + l_prev * nWCH, nMarginT - nWCH/2), 
						    new Size( i0 * nWCH, nWCH / 2 )), 
				cl_lgreen,
				Imgproc.FILLED); // At the top of characters box
		Imgproc.rectangle(img, 
				new Rect( new Point( nMarginL, nMarginT  + 3*nWCH - nWCH/2), 
						    new Size( i0 * nWCH, nWCH/2 )), 
				cl_lgreen,
				Imgproc.FILLED); // At the to of Ztable
		
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
    				new Point(nMarginL + ix*nWCH + nWCH_mgr, nMarginT + 4 * nWCH - nWCH_mgr), 
    				Imgproc.FONT_HERSHEY_DUPLEX
    				, 1, clyel);
			
			// Visualize Z[i0] next to lenbeta 
			Imgproc.rectangle(img, 
					new Rect( new Point( nMarginL + ix * nWCH, nMarginT - nWCH), 
							    new Size( Z[i0] * nWCH, nWCH / 2 )), 
					cl_lblue,
					Imgproc.FILLED);
			
			// Status text
			status[nS++] = String.format("    Z[%02d]=%02d < length(%s)=%02d",
                    i0, Z[i0], strbeta, lenbeta);
			status[nS++] = String.format("    Assign Z[%02d] to Z[%02d]=%02d", ix, i0, Z[i0]);
		}
		// v3) 
		else {
			
			if (j2 > (r_prev +1) ) {
				// (3.1.1) Visualize matched string [r+1,j2) and [lenbeta+1,j1]
				Imgproc.rectangle(img, 
						new Rect(
								new Point(nMarginL + (r_prev+1) * nWCH, nMarginT), 
								new Size((j2 - r_prev - 1) * nWCH, nWCH)), 
						cl_blue, 
						2);
				Imgproc.rectangle(img, 
						new Rect(
								new Point(nMarginL + (i0+1) * nWCH, nMarginT), 
								new Size((j2 - r_prev - 1) * nWCH, nWCH)), 
						cl_blue, 
						2);
			} 
			else {
				// (3.1.2) Visualize unmatched 
				Imgproc.rectangle(img, 
						new Rect(
								new Point(nMarginL + (r_prev+1) * nWCH, nMarginT), 
								new Size(nWCH, nWCH)), 
						cl_red, 
						2);
				Imgproc.rectangle(img, 
						new Rect(
								new Point(nMarginL + (i0+1) * nWCH, nMarginT), 
								new Size(nWCH, nWCH)), 
						cl_red, 
						2);
			}
			
			// 3.2) New [l,r]
			Imgproc.rectangle(img, 
    				new Rect(
    						new Point(nMarginL + l * nWCH, nMarginT +  nWCH), 
    						new Size( (r-l+1) * nWCH, nWCH)), 
    				cl_lr_newrange,
    				Imgproc.FILLED);

    		Imgproc.putText(img, "[l;r]", 
    				new Point(nMarginL + l * nWCH + nWCH_mgr, nMarginT + 2*nWCH - nWCH_mgr),
    				Imgproc.FONT_HERSHEY_DUPLEX, 
    				1, 
    				clyel);

    		// Status
    		String str_matched = j2 > (r_prev +1) 
    				? String.format("Matched: %s", str.substring(r_prev +1, j2)) 
    				: String.format("Mismatched %c != %c", str.charAt(i0 + 1), str.charAt(r_prev+1));
    		status[nS++] = String.format("    Z[%02d]=%d>=length(%s)=%d",
                    i0, Z[i0], strbeta, lenbeta);
    		status[nS++] = "    " + str_matched;
    		status[nS++] = String.format("    [l;r] updated = [%02d;%02d]" , l, r);
    		status[nS++] = String.format("    Z[%02d]=%02d", ix, Z[ix]);
		}
		// v4) Status update
		draw_text_multilines(img, nMarginL, nMarginT  + 5*nWCH, 
								status, nS , 
								Imgproc.FONT_HERSHEY_DUPLEX, 1, 1, clwhite);
	}
	
	public void SaveImage(Mat img, int imgid) {
		if (!FLAG_VIDEO) return;
		Imgcodecs.imwrite(String.format("%s_%03d.png", FILE_PREFIX, imgid), img);
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
	    int l_prev = l;
	    int r_prev = r;
	    int imgid = 0; // image  index
	    String str = new String(S);
	    
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
	    	
	    	// a1) 
	    	Mat img = new Mat(nH, nW, CvType.CV_8UC3, new Scalar(0, 0, 0));
	    	draw_overview(img, str, Z, l, r, i);
	    	SaveImage(img, imgid++);
	    	// a1) END Visualize==========
	    		
	        if (i > r) {
	            j2 = i;
	            j1 = 0;
	            
	            l_prev = l; // Visualization support
	            r_prev = r; 
	        
	            /* Compare to the prefix */
	            while (j2 < n && S[j1] == S[j2]) { j1++; j2++;}

	            /* Found */
	            if (j2 > i) {
	                l = i;
	                r = j2 - 1;
	                Z[i] = j2 - i;
	            }
	            else {          
	                Z[i] = 0;
	            }
	            
	            // a2) Visualize 2==========================================
	            Mat img1 = new Mat(nH, nW, CvType.CV_8UC3, new Scalar(0, 0, 0));
	            draw_overview(img1, str, Z, l, r, i);
	            draw_compare_prefix(img1, str, Z, l_prev, r_prev, l, r, i, j2);
	            SaveImage(img1, imgid++);
	            // END =====================================================
	        }
	        else {
	            lenbeta = r - i + 1;
	            i0 = i - l;
	            String strbeta = str.substring(i, r+1); // Visualization support
	            l_prev = l; // Visualization support
	            r_prev = r; 

	            if (Z[i0] < lenbeta) {
	                Z[i] = Z[i0];
	                // l,r unchanged
	            }
	            else {
	                j2 = r + 1;
	                j1 = i0 + 1; // (r - l) + 1;
	            
	                while (S[j1] == S[j2] && j2 < n) {
	                    j1 ++;
	                    j2 ++;
	                }

	                l = i;
	                r = j2 - 1;
	                Z[i] = (j2 - i);
	            }
	            
	            // a3) Visualize 3==========================================
	            Mat img2 = new Mat(nH, nW, CvType.CV_8UC3, new Scalar(0, 0, 0));
	            draw_overview(img2, str, Z, l, r, i);
	            draw_ultilize_z_jumping(img2, str, Z, l_prev, r_prev, l, r, i, i0, j1, j2, strbeta);
	            SaveImage(img2, imgid++);
	            // END =====================================================
	        }
	        i++;
	    }
	    return 0;
	}
	
	public void ztable_visualize() {
		/*============================================================
         * 	Configuration
         *============================================================*/	
		String str = "abcaabbacaabcaedebbba";
		int[] Z = new int[str.length()];
		
		ZTable(str.toCharArray(), Z);
		System.out.println(Arrays.toString(Z));
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
	}
	
	public static void main(String[] args) {
		// CRITICAL: You must load the native library before using OpenCV classes
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        System.out.println("OpenCV Loaded Successfully!");
        
        ztable zt = new ztable();
        zt.ztable_visualize();
	}
}