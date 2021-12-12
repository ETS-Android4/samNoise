package org.firstinspires.ftc.teamcode;

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

/**
 * This is NOT an opmode.
 *
 * This class is used to define all the specific hardware for the Ultimate Goal Season.
 *
 * This hardware class assumes the following device names have been configured on the robot:
 * Note:  All names are camel case with no spaces.
 *
 * Motor channel:  Left Front drive motor:          "leftFrontDrive"
 * Motor channel:  Right Front drive motor:         "rightFrontDrive"
 * Motor channel:  Left Back Front drive motor:     "leftBackDrive"
 * Motor channel:  Right Back drive motor:          "rightBackDrive"
 * Motor channel:  Shooter drive motor:             "Shooter"
 * Servo channel:  Servo to open left claw:  "left_hand"
 * Servo channel:  Servo to open right claw: "right_hand"
 */
@TeleOp

public class TankDrive extends LinearOpMode {

    TDRobotConfig robot = new TDRobotConfig();

    @Override
    public void runOpMode() {

        /* Initialize the hardware variables.
         * The init() method of the hardware class does all the work here
         */
        robot.init(hardwareMap);

        // Send telemetry message to signify robot waiting;
        telemetry.addData("Get Ready", "To Party");    //
        telemetry.update();

        robot.stopDriveTrain();

        // Wait for the game to start (driver presses PLAY)
        waitForStart();

        // run until the end
        // of the match (driver presses STOP)
        while (opModeIsActive()) {

            robot.driveTrain(gamepad1.left_stick_y);


            telemetry.update();

            // Pace this loop so jaw action is reasonable speed.
            //sleep(50);
        }
    }



}



