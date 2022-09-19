package org.firstinspires.ftc.teamcode.ancillary_subsystems;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;
import org.openftc.easyopencv.OpenCvPipeline;

public class TestImagePipeline extends OpenCvPipeline {

    int reg_blue;
    int reg_green;
    int reg_red;

    public enum DominantColor{
        BLUE,
        GREEN,
        RED,
        UNKNOWN
    }

    private volatile DominantColor dom_color = DominantColor.UNKNOWN;

    Point region_topLeft = new Point(50,50);
    Point region_botRight = new Point(200,200);

    Mat region = new Mat();

    @Override
    public void init(Mat firstFrame){
        region = firstFrame.submat(new Rect(region_topLeft, region_botRight));
    }

    @Override
    public Mat processFrame(Mat input){
        Imgproc.rectangle(
                input,
                region_topLeft,
                region_botRight,
                new Scalar(255, 0, 0),
                1
        );

        Mat blue_channel = new Mat();
        Mat green_channel = new Mat();
        Mat red_channel = new Mat();

        Core.extractChannel(region, red_channel, 0);
        Core.extractChannel(region, green_channel, 1);
        Core.extractChannel(region, blue_channel, 2);

        double reg_blue = Core.mean(blue_channel).val[0];
        double reg_green = Core.mean(green_channel).val[0];
        double reg_red = Core.mean(red_channel).val[0];

        if(reg_blue >= reg_green && reg_blue >= reg_red){
            dom_color = DominantColor.BLUE;
        }else if(reg_green >= reg_blue && reg_green >= reg_red){
            dom_color = DominantColor.GREEN;
        }else if(reg_red >= reg_blue && reg_red >= reg_green){
            dom_color = DominantColor.RED;
        }else{
            dom_color = DominantColor.UNKNOWN;
        }

        return input;
    }

    public DominantColor get_dom_color(){
        return dom_color;
    }
}
