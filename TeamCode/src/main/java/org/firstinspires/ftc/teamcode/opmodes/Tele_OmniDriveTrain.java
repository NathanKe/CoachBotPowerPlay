package org.firstinspires.ftc.teamcode.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.teamcode.ancillary_subsystems.Gyro;
import org.firstinspires.ftc.teamcode.ancillary_subsystems.OmniDriveTrain;
import org.firstinspires.ftc.teamcode.ancillary_subsystems.OmniHolonomicCalculation;

@TeleOp(name = "Tele_OmniDriveTrain", group = "")
public class Tele_OmniDriveTrain extends OpMode {
    private OmniDriveTrain omni;
    private OmniHolonomicCalculation calc;
    private Gyro gyro;
    @Override
    public void init() {
        omni = new OmniDriveTrain(hardwareMap);
        calc = new OmniHolonomicCalculation(0.25, 0.75, 1.0);
        gyro = new Gyro(telemetry, hardwareMap);
    }

    @Override
    public void loop() {

        double yaw_err = gyro.getYaw(AngleUnit.RADIANS);

        calc.reCalcPower(gamepad1, yaw_err);

        omni.calculationDrive(calc);

        telemetry.addData("Heading: ", Math.round(yaw_err * 180 / Math.PI));
        telemetry.update();
    }

    @Override
    public void stop(){
        omni.stopAll();
    }
}
