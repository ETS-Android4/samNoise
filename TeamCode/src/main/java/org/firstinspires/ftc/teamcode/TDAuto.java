package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
//import com.qualcomm.robotcore.hardware.VoltageSensor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.VoltageSensor;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.robotcore.external.ClassFactory;

import static java.lang.Thread.sleep;
import static org.firstinspires.ftc.robotcontroller.external.samples.HardwarePushbot.MID_SERVO;
import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.telemetry;

@Autonomous

public class TDAuto extends LinearOpMode {

    @Override
    public void runOpMode() {
        TDRobotConfig robot = new TDRobotConfig();
        // Using preset robot configuration

        robot.init(hardwareMap);

        telemetry.addData("Get Ready", "To Party");    //
        telemetry.update();

        //Stop robot
        robot.stopDriveTrain();


        // Wait for the game to start (driver presses PLAY)
        waitForStart();




    }
}