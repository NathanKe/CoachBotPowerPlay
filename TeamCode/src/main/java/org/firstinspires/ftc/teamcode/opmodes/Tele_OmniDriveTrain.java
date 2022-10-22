package org.firstinspires.ftc.teamcode.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.ancillary_subsystems.OmniDriveTrain;

@TeleOp(name = "Tele_OmniDriveTrain", group = "")
public class Tele_OmniDriveTrain extends OpMode {
    private OmniDriveTrain driveTrain;
    @Override
    public void init() {
        driveTrain = new OmniDriveTrain(hardwareMap);
    }

    @Override
    public void loop() {
        driveTrain.drive(gamepad1);
    }

    @Override
    public void stop(){
        driveTrain.stop();
    }
}
