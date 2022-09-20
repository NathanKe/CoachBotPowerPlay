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

        Mat blue_channel = new Mat();
        Mat green_channel = new Mat();
        Mat red_channel = new Mat();

        Core.extractChannel(region, red_channel, 0);
        Core.extractChannel(region, green_channel, 1);
        Core.extractChannel(region, blue_channel, 2);

        double blue_val;
        double[] pixel_val = {0, 0, 0, 0};

        /*
        for(int r = 0; r < input.rows(); r++){
            for(int c = 0; r < input.cols(); c++){
                //blue_val = region.get(r, c)[0];
                pixel_val[0] = 50;
                pixel_val[1] = 100;
                pixel_val[2] = 200;
                pixel_val[2] = 10;
                input.put(r, c, pixel_val);
            }
        }*/

        return region;
    }

    public DominantColor get_dom_color(){
        return DominantColor.RED;
    }
}
