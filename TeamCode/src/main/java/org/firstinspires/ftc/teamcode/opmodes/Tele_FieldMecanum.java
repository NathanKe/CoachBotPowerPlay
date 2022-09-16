package org.firstinspires.ftc.teamcode.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.ancillary_subsystems.GyroMecanumPlatform;

@TeleOp(name = "Tele_FieldMecanum", group = "")
public class Tele_FieldMecanum extends OpMode {
    public static GyroMecanumPlatform mecBot;

    @Override
    public void init(){
        mecBot = new GyroMecanumPlatform(this.telemetry, this.hardwareMap);
    }

    @Override
    public void loop(){
        mecBot.fieldDriveTrainGamepad(gamepad1);
    }

    @Override
    public void stop(){
        mecBot.mecanumPlatform.stopDriveMotors();
    }

}
