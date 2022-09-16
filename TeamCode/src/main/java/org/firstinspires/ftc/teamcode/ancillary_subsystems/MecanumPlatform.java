package org.firstinspires.ftc.teamcode.ancillary_subsystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.Collections;

public class MecanumPlatform {

    private final Telemetry telemetry;

    private final DcMotor motorFL;
    private final DcMotor motorFR;
    private final DcMotor motorBL;
    private final DcMotor motorBR;

    private final DecimalFormat twoDecimalPlaces = new DecimalFormat("#.##");

    MecanumPlatform(Telemetry in_telemetry, HardwareMap in_hwMap) {
        this.telemetry = in_telemetry;
        motorFL = in_hwMap.get(DcMotor.class, "motorFL");
        motorFR = in_hwMap.get(DcMotor.class, "motorFR");
        motorBL = in_hwMap.get(DcMotor.class, "motorBL");
        motorBR = in_hwMap.get(DcMotor.class, "motorBR");
        stopDriveMotors();
    }

    void baseDriveTrain(double left_x, double left_y, double right_x, double scale) {

        double raw_fl = left_y + left_x + right_x;
        double raw_fr = -1 * left_y + left_x + right_x;
        double raw_bl = left_y + -1 * left_x + right_x;
        double raw_br = -1 * left_y + -1 * left_x + right_x;

        double max = Collections.max(Arrays.asList(raw_fl, raw_fr, raw_bl, raw_br, 1.0));

        double out_fl = raw_fl / (max * scale);
        double out_fr = raw_fr / (max * scale);
        double out_bl = raw_bl / (max * scale);
        double out_br = raw_br / (max * scale);

        setDrivePower(out_fl, out_fr, out_bl, out_br);
    }

    public void stopDriveMotors() {
        motorFL.setPower(0.0);
        motorFR.setPower(0.0);
        motorBL.setPower(0.0);
        motorBR.setPower(0.0);
    }

    void setDrivePower(double powerFL, double powerFR, double powerBL, double powerBR) {

        String out_fl_st = twoDecimalPlaces.format(powerFL);
        String out_fr_st = twoDecimalPlaces.format(powerFR);
        String out_bl_st = twoDecimalPlaces.format(powerBL);
        String out_br_st = twoDecimalPlaces.format(powerBR);
        String motor_str = out_fl_st + ", " + out_fr_st + ", " + out_bl_st + ", " + out_br_st;

        telemetry.addData("Motors: ", motor_str);

        motorFL.setPower(powerFL);
        motorFR.setPower(powerFR);
        motorBL.setPower(powerBL);
        motorBR.setPower(powerBR);
    }
}
