package org.firstinspires.ftc.teamcode.open_cv_pipelines;


import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;
import org.openftc.easyopencv.OpenCvPipeline;

import java.util.ArrayList;
import java.util.List;

public class TestHsvPipeline extends OpenCvPipeline {

    public static double LOW_H = 30;
    public static double HIGH_H = 75;
    public static double LOW_S = 50;
    public static double HIGH_S = 255;
    public static double LOW_V = 100;
    public static double HIGH_V = 255;

    public Scalar low = new Scalar(LOW_H, LOW_S, LOW_V);
    public Scalar high = new Scalar(HIGH_H, HIGH_S, HIGH_V);


    Mat hsv = new Mat();
    Mat mask = new Mat();
    Mat output = new Mat();

    @Override
    public void init(Mat firstFrame){
    }

    @Override
    public Mat processFrame(Mat input){
        Imgproc.cvtColor(input, hsv, Imgproc.COLOR_RGB2HSV);

        output.release();
        Core.inRange(hsv, low, high, mask);

        Core.bitwise_and(input, input, output, mask);

        Imgproc.morphologyEx(output, output, Imgproc.MORPH_OPEN, new Mat());
        Imgproc.morphologyEx(output, output, Imgproc.MORPH_CLOSE, new Mat());

        Imgproc.GaussianBlur(output, output, new Size(5.0, 15.0), 0.00);

        return output;
    }
}
