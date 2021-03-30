package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.matrices.OpenGLMatrix;
import org.firstinspires.ftc.robotcore.external.matrices.VectorF;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackable;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackableDefaultListener;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackables;

import java.util.ArrayList;
import java.util.List;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.telemetry;
import static org.firstinspires.ftc.robotcore.external.navigation.AngleUnit.DEGREES;
import static org.firstinspires.ftc.robotcore.external.navigation.AxesOrder.XYZ;
import static org.firstinspires.ftc.robotcore.external.navigation.AxesOrder.YZX;
import static org.firstinspires.ftc.robotcore.external.navigation.AxesReference.EXTRINSIC;
import static org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer.CameraDirection.BACK;

@Autonomous
public class AutoA extends LinearOpMode {



    @Override
    public void runOpMode() {
        RobotConfiguration robot = new RobotConfiguration();   // Using preset robot configuration

        robot.init(hardwareMap);

        telemetry.addData("Get Ready", "To Party");    //
        telemetry.update();

        robot.stopDriveTrain();
        robot.stopServo();

        // Wait for the game to start (driver presses PLAY)
        waitForStart();


            robot.leftFrontDrive.setPower(1);
            robot.rightFrontDrive.setPower(1);
            robot.leftBackDrive.setPower(1);
            robot.rightBackDrive.setPower(1);

            sleep(3100);

            robot.leftFrontDrive.setPower(-1);
            robot.rightFrontDrive.setPower(1);
            robot.leftBackDrive.setPower(-1);
            robot.rightBackDrive.setPower(1);

            sleep(1000);

            robot.stopDriveTrain();

            sleep(100);

        robot.leftFrontDrive.setPower(-1);
        robot.rightFrontDrive.setPower(-1);
        robot.leftBackDrive.setPower(-1);
        robot.rightBackDrive.setPower(-1);

        sleep(200);


    }
}
