package org.firstinspires.ftc.teamcode.ancillary_subsystems;

import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;

import java.text.DecimalFormat;

public class GyroMecanumPlatform {
    private final Telemetry telemetry;
    private final DecimalFormat twoDecimalPlaces = new DecimalFormat("#.##");
    public MecanumPlatform mecanumPlatform;
    public Gyro gyro;

    public GyroMecanumPlatform(Telemetry in_telemetry, HardwareMap in_hwMap) {
        this.telemetry = in_telemetry;
        mecanumPlatform = new MecanumPlatform(in_telemetry, in_hwMap);
        gyro = new Gyro(in_telemetry, in_hwMap);
    }

    public void fieldDriveTrainGamepad(Gamepad gamepad){
        double left_x = gamepad.left_stick_x;
        double left_y = gamepad.left_stick_y;
        double right_x = gamepad.right_stick_x;
        double scale = 1.0 - gamepad.right_trigger;

        fieldDriveTrain(left_x, left_y, right_x, scale);
    }

    public void fieldDriveTrain(double left_x, double left_y, double right_x, double scale) {
        double heading = gyro.getYaw(AngleUnit.RADIANS);

        double adj_left_x = left_x * Math.cos(heading) - left_y * Math.sin(heading);
        double adj_left_y = left_x * Math.sin(heading) + left_y * Math.cos(heading);

        telemetry.addData("Heading: ", Math.round(heading * 180 / Math.PI));
        telemetry.addData("X", twoDecimalPlaces.format(left_x));
        telemetry.addData("X adj", twoDecimalPlaces.format(adj_left_x));
        telemetry.addData("Y", twoDecimalPlaces.format(left_y));
        telemetry.addData("Y adj", twoDecimalPlaces.format(left_y));

        mecanumPlatform.baseDriveTrain(adj_left_x, adj_left_y, right_x, scale);
    }
}
