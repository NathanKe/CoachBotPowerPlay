package org.firstinspires.ftc.teamcode.ancillary_subsystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class OmniDriveTrain {
    private DcMotor motorFR;
    private DcMotor motorFL;
    private DcMotor motorBR;
    private DcMotor motorBL;

    public OmniDriveTrain(HardwareMap hw) {
        initialize(hw);
    }

    public void initialize(HardwareMap hw){
        motorFR = hw.get(DcMotor.class, "motorFR");
        motorFL = hw.get(DcMotor.class, "motorFL");
        motorBR = hw.get(DcMotor.class, "motorBR");
        motorBL = hw.get(DcMotor.class, "motorBL");
    }

    public void basic_drive(double y, double x, double t){
        scale_drive(x, y, t, 1.0);
    }

    // 0.0 <= full_slow_percent < standard_percent < max_power_percent <= 1.0
    // if both triggers, default to standard drive
    public void slow_and_turbo_trigger_drive(double y, double x, double t, double l_tr, double r_tr, double full_slow_percent, double standard_percent, double max_power_percent){

        double left_trigger_slope = full_slow_percent - standard_percent;
        double low_scale = left_trigger_slope * l_tr + standard_percent;

        double right_trigger_slope = max_power_percent - standard_percent;
        double high_scale = right_trigger_slope * r_tr + standard_percent;

        if(l_tr > 0.0 && r_tr > 0.0){
            scale_drive(y, x, t, standard_percent);
        }else if(right_trigger_slope > 0.0){
            scale_drive(y, x, t, high_scale);
        }else if(l_tr > 0.0){
            scale_drive(y, x, t, low_scale);
        }else{
            scale_drive(y, x, t, standard_percent);
        }
    }

    public void scale_drive(double y, double x, double t, double scale_factor){

        double in_bounds_factor = Math.max(1, Math.abs(y)+Math.abs(x) + Math.abs(t));

        double powerFrontRight = scale_factor*(-y+x+t)/in_bounds_factor;
        double powerFrontLeft = scale_factor*(y+x+t)/in_bounds_factor;
        double powerBackRight = scale_factor*(-y-x+t)/in_bounds_factor;
        double powerBackLeft = scale_factor*(y-x+t)/in_bounds_factor;

        motorFR.setPower(powerFrontRight);
        motorFL.setPower(powerFrontLeft);
        motorBR.setPower(powerBackRight);
        motorBL.setPower(powerBackLeft);
    }

    public void stop(){
        motorFR.setPower(0.0);
        motorFL.setPower(0.0);
        motorBR.setPower(0.0);
        motorBL.setPower(0.0);
    }
}
