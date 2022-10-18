package org.firstinspires.ftc.teamcode.open_cv_pipelines;


import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;
import org.openftc.easyopencv.OpenCvPipeline;

public class TestYCbCrPipeline extends OpenCvPipeline {

    public Scalar low = new Scalar(0, 0, 0);
    public Scalar high = new Scalar(255, 255, 255);


    Mat hsv = new Mat();
    Mat mask = new Mat();
    Mat output = new Mat();

    @Override
    public void init(Mat firstFrame){
    }

    @Override
    public Mat processFrame(Mat input){
        Imgproc.cvtColor(input, hsv, Imgproc.COLOR_RGB2YCrCb);

        output.release();
        Core.inRange(hsv, low, high, mask);

        Core.bitwise_and(input, input, output, mask);

        return output;
    }
}
