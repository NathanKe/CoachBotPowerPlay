package org.firstinspires.ftc.teamcode.open_cv_pipelines;


import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint;
import org.opencv.core.MatOfPoint2f;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;
import org.openftc.easyopencv.OpenCvPipeline;

import java.util.ArrayList;
import java.util.List;


// largely from
// https://github.com/PinkToTheFuture/OpenCV_FreightFrenzy_2021-2022/blob/main/ContourPipeline.java
public class TestYCrCbPipeline extends OpenCvPipeline {

    // tuned to green
    public static double LOW_Y = 0;
    public static double HIGH_Y = 200;
    public static double LOW_Cb = 0;
    public static double HIGH_Cb = 128;
    public static double LOW_Cr = 0;
    public static double HIGH_Cr = 128;

    public Scalar low = new Scalar(LOW_Y, LOW_Cr, LOW_Cb);
    public Scalar high = new Scalar(HIGH_Y, HIGH_Cr, HIGH_Cb);


    Mat ycrcb = new Mat();
    Mat processed = new Mat();
    Mat mask_apply = new Mat();

    private double maxArea;
    private Rect maxRect;

    @Override
    public void init(Mat firstFrame){
    }

    @Override
    public Mat processFrame(Mat input){
        // convert to YCrCb
        Imgproc.cvtColor(input, ycrcb, Imgproc.COLOR_RGB2YCrCb);

        // get rid of previous output value
        processed.release();
        mask_apply.release();

        // take values from ycrcb within [low, high] and store in processed
        Core.inRange(ycrcb, low, high, processed);

        // remove all but matching pixels from input image, store in mask_apply
        Core.bitwise_and(input, input, mask_apply, processed);

        // de noise-ing.  removes specs
        Imgproc.morphologyEx(processed, processed, Imgproc.MORPH_OPEN, new Mat());
        Imgproc.morphologyEx(processed, processed, Imgproc.MORPH_CLOSE, new Mat());

        // a bit of blurring
        Imgproc.GaussianBlur(processed, processed, new Size(5.0, 15.0), 0.00);

        // find contours, put them into a list
        List<MatOfPoint> contours = new ArrayList<>();
        Imgproc.findContours(processed, contours, new Mat(), Imgproc.RETR_LIST, Imgproc.CHAIN_APPROX_SIMPLE);

        // loop the contours
        if(! contours.isEmpty()) {
            for (MatOfPoint contour : contours) {
                Point[] contourArray = contour.toArray();
                if (contourArray.length >= 15) {
                    MatOfPoint2f areaPoints = new MatOfPoint2f(contourArray);
                    Rect rect = Imgproc.boundingRect(areaPoints);

                    if (rect.area() > maxArea) {
                        maxArea = rect.area();
                        maxRect = rect;
                    }
                }
            }

            Imgproc.rectangle(mask_apply, maxRect, new Scalar(255, 255, 255), 2);
        }

        return mask_apply;
    }
}
