package org.firstinspires.ftc.teamcode.ancillary_subsystems;

import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;
import org.openftc.easyopencv.OpenCvPipeline;

import java.util.Arrays;

public class TestImagePipeline extends OpenCvPipeline {


    Mat zero = new Mat();
    Mat raw_blue_channel = new Mat();
    Mat threshold_blue_channel = new Mat();
    Mat blueified = new Mat();

    @Override
    public void init(Mat firstFrame){
        zero = new Mat(firstFrame.size(), CvType.CV_8UC1, new Scalar(0));
        blueified = new Mat(firstFrame.size(), CvType.CV_8UC3);
    }

    @Override
    public Mat processFrame(Mat input){
        Core.extractChannel(input, raw_blue_channel, 0);
        Core.inRange(raw_blue_channel, new Scalar(100), new Scalar(255), threshold_blue_channel);

        Core.merge(Arrays.asList(threshold_blue_channel, zero, zero), blueified);
        return blueified;
    }
}
