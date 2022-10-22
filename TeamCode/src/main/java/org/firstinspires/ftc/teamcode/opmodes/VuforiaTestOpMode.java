package org.firstinspires.ftc.teamcode.opmodes;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.ancillary_subsystems.VofuriaLocalization;

@TeleOp
public class VuforiaTestOpMode extends LinearOpMode {
    private MultipleTelemetry multi_telemetry;

    public void runOpMode(){
        FtcDashboard dash = FtcDashboard.getInstance();
        multi_telemetry = new MultipleTelemetry(telemetry, dash.getTelemetry());

        VofuriaLocalization vuf_stuff = new VofuriaLocalization();
        vuf_stuff.initialize_localization(hardwareMap, multi_telemetry);

        waitForStart();

        dash.startCameraStream(vuf_stuff.vuforia,0);

        while(opModeIsActive()){
            vuf_stuff.get_localization();
            multi_telemetry.update();
        }
    }
}
