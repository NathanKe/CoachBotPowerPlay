package org.firstinspires.ftc.teamcode.ancillary_subsystems;

import com.acmerobotics.dashboard.config.Config;

import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;
import org.openftc.easyopencv.OpenCvPipeline;

@Config
public class TestHsvPipeline extends OpenCvPipeline {

    public static double LOW_H = 0;
    public static double HIGH_H = 180;
    public static double LOW_S = 0;
    public static double HIGH_S = 255;
    public static double LOW_V = 0;
    public static double HIGH_V = 255;

    Scalar low = new Scalar(LOW_H, LOW_S, LOW_V);
    Scalar high = new Scalar(HIGH_H, HIGH_S, HIGH_V);


    Mat zero = new Mat();
    Mat hsv = new Mat();
    Mat output = new Mat();

    @Override
    public void init(Mat firstFrame){
        zero = new Mat(firstFrame.size(), CvType.CV_8UC1, new Scalar(0));
    }

    @Override
    public Mat processFrame(Mat input){
        Imgproc.cvtColor(input, hsv, Imgproc.COLOR_RGB2HSV);

        Core.inRange(hsv, low, high, output);

        return output;
    }
}
