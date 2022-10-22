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

    public void drive(Gamepad gamepad){
        double y = -1 * gamepad.left_stick_y;
        double x = gamepad.left_stick_x;
        double t = gamepad.right_stick_x;

        double scaleFactor = Math.max(1, Math.abs(y)+Math.abs(x) + Math.abs(t));

        double powerFrontRight = (-y+x+t)/scaleFactor;
        double powerFrontLeft = (y+x+t)/scaleFactor;
        double powerBackRight = (-y-x+t)/scaleFactor;
        double powerBackLeft = (y-x+t)/scaleFactor;

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
