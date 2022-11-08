package org.firstinspires.ftc.teamcode.ancillary_subsystems;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;

public class Gyro {

    private BNO055IMU imu;
    public Telemetry telemetry;

    public Gyro(Telemetry in_telemetry, HardwareMap in_hwMap) {
        this.initialize(in_hwMap);
        telemetry = in_telemetry;
    }

    void initialize(HardwareMap in_hwMap) {
        BNO055IMU.Parameters parameters = new BNO055IMU.Parameters();
        parameters.angleUnit = BNO055IMU.AngleUnit.DEGREES;
        imu = in_hwMap.get(BNO055IMU.class, "imu");
        imu.initialize(parameters);
    }

    public double getYaw(AngleUnit degreeOrRadian) {
        double yaw = -1 * imu.getAngularOrientation(AxesReference.EXTRINSIC, AxesOrder.XYZ, degreeOrRadian).thirdAngle;
        telemetry.addData("Yaw", Math.round(yaw * 180 / Math.PI));
        return yaw;
    }
}
