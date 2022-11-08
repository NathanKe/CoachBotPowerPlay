package org.firstinspires.ftc.teamcode.ancillary_subsystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class OmniDriveTrain {
    private DcMotor motorFL;
    private DcMotor motorFR;
    private DcMotor motorBL;
    private DcMotor motorBR;

    private Telemetry telemetry;

    public OmniDriveTrain(Telemetry in_telemetry, HardwareMap hw) {
        telemetry = in_telemetry;
        initialize(hw);
    }

    public void initialize(HardwareMap hw){
        motorFL = hw.get(DcMotor.class, "motorFL");
        motorFR = hw.get(DcMotor.class, "motorFR");
        motorBL = hw.get(DcMotor.class, "motorBL");
        motorBR = hw.get(DcMotor.class, "motorBR");
    }

    public void calculationDrive(OmniHolonomicCalculation calcRes){
        fourPowerDrive(calcRes.power_fl, calcRes.power_fr, calcRes.power_bl, calcRes.power_br);
    }

    public void fourPowerDrive(double pow_fl, double pow_fr, double pow_bl, double pow_br){
        motorFL.setPower(pow_fl);
        motorFR.setPower(pow_fr);
        motorBL.setPower(pow_bl);
        motorBR.setPower(pow_br);
    }

    public void equalPowerToAllMotors(double pow){
        fourPowerDrive(pow, pow, pow, pow);
    }


    public void stopAll(){
        equalPowerToAllMotors(0.0);
    }
}
