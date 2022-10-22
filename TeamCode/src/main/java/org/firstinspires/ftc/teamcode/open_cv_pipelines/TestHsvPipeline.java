package org.firstinspires.ftc.teamcode.open_cv_pipelines;


import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;
import org.openftc.easyopencv.OpenCvPipeline;

public class TestHsvPipeline extends OpenCvPipeline {

    public static double LOW_H = 100;
    public static double HIGH_H = 130;
    public static double LOW_S = 50;
    public static double HIGH_S = 255;
    public static double LOW_V = 50;
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

        return output;
    }
}
