package org.firstinspires.ftc.teamcode.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.ancillary_subsystems.GyroOmniDriveTrain;
import org.firstinspires.ftc.teamcode.ancillary_subsystems.OmniDriveTrain;

@TeleOp(name = "Tele_OmniDriveTrain", group = "")
public class Tele_OmniDriveTrain extends OpMode {
    private GyroOmniDriveTrain gyroOmni;
    @Override
    public void init() {
        gyroOmni = new GyroOmniDriveTrain(telemetry, hardwareMap);
    }

    @Override
    public void loop() {
        gyroOmni.yaw_corrected_slow_and_turbo_drive(
                gamepad1.left_stick_y, gamepad1.left_stick_x, gamepad1.right_stick_x,
                gamepad1.left_trigger, gamepad1.right_trigger,
                0.25, 0.7, 1.0
        );
    }

    @Override
    public void stop(){
        gyroOmni.omni.stop();
    }
}
