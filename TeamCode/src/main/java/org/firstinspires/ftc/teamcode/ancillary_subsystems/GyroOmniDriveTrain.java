package org.firstinspires.ftc.teamcode.ancillary_subsystems;

import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;

public class GyroOmniDriveTrain {
    private final Telemetry telemetry;
    public OmniDriveTrain omni;
    private Gyro gyro;

    public GyroOmniDriveTrain(Telemetry in_telemetry, HardwareMap in_hwmap){
        this.telemetry = in_telemetry;
        this.omni = new OmniDriveTrain(in_hwmap);
        this.gyro = new Gyro(in_telemetry, in_hwmap);
    }

    public void yaw_corrected_slow_and_turbo_drive(double y, double x, double t, double l_tr, double r_tr, double full_slow_percent, double standard_percent, double max_power_percent){
        double heading = gyro.getYaw(AngleUnit.RADIANS);


        double adj_left_x =  x * Math.cos(heading) - y * Math.sin(heading);
        double adj_left_y = x * Math.sin(heading) + y * Math.cos(heading);

        omni.slow_and_turbo_trigger_drive(adj_left_y, adj_left_x, t, l_tr, r_tr, full_slow_percent, standard_percent, max_power_percent);
    }
}
