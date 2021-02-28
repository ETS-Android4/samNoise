package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

/**
 * This OpMode uses RobotConfiguration.java file to define the devices on the robot.
 * All device access is managed through the RobotConfiguration class.
 * The code is structured as a LinearOpMode
 *
 * This particular OpMode executes a POV Game style Teleop for a PushBot
 * In this mode the left stick moves the robot FWD and back, the Right stick turns left and right.
 * It raises and lowers the claw using the Gamepad Y and A buttons respectively.
 * It also opens and closes the claws slowly using the left and right Bumper buttons.
 *
 * Use Android Studios to Copy this Class, and Paste it into your team's code folder with a new name.
 * Remove or comment out the @Disabled line to add this opmode to the Driver Station OpMode list
 */

@TeleOp (name="Victoria's TeleOp", group="Mecanum")

public class MainTeleOp extends LinearOpMode {

    /* Declare OpMode members. */
    RobotConfiguration robot = new RobotConfiguration();   // Using preset robot configuration

    @Override
    public void runOpMode() {

        /* Initialize the hardware variables.
         * The init() method of the hardware class does all the work here
         */
       robot.init(hardwareMap);
         //DcMotor leftFrontDrive;
         //DcMotor  rightFrontDrive;
         //  leftBackDrive;
         //DcMotor  rightBackDrive;
        DcMotor rightFrontDrive = hardwareMap.get(DcMotor.class, "rightFrontDrive");
        DcMotor rightBackDrive = hardwareMap.get(DcMotor.class, "rightBackDrive");
        DcMotor leftFrontDrive = hardwareMap.get(DcMotor.class, "leftFrontDrive");
        DcMotor leftBackDrive = hardwareMap.get(DcMotor.class, "leftBackDrive");
        DcMotor shooter = hardwareMap.get(DcMotor.class, "shooter");

        leftFrontDrive.setDirection(DcMotor.Direction.FORWARD);
        leftBackDrive.setDirection(DcMotor.Direction.FORWARD);
        rightFrontDrive.setDirection(DcMotor.Direction.REVERSE);
        rightBackDrive.setDirection(DcMotor.Direction.REVERSE);
        shooter.setDirection(DcMotorSimple.Direction.FORWARD);
        // Send telemetry message to signify robot waiting;
        telemetry.addData("Get Ready", "To Party");    //
        telemetry.update();



        // Wait for the game to start (driver presses PLAY)
        waitForStart();

        // run until the end
        // of the match (driver presses STOP)
        while (opModeIsActive()) {

            //Will run the shooter if the A button is Pressed
           //robot.runShooter(gamepad1.a);

           //robot.rotateCounterClockwise(gamepad1.left_trigger);
           //robot.rotateClockwise(gamepad1.right_trigger);
           //robot.shooterBoi(gamepad1.a);
          // robot.driveTwo(gamepad1.left_stick_y, gamepad1.right_stick_y);
           //robot.getBatteryPower();

            double forward = -gamepad1.right_stick_y;
            double sideways = gamepad1.right_stick_x;
            double twist = gamepad1.left_stick_x;

            double fl = forward + twist + sideways;
            double fr = forward - twist - sideways;
            double bl = forward + twist - sideways;
            double br = forward - twist + sideways;

            double max = Math.max(Math.abs(fl), Math.max(Math.abs(bl), Math.max(Math.abs(br),Math.abs(fr))));
            if (max > 1){
                fl /= max;
                fr /= max;
                br /= max;
                bl /= max;
            }

            leftFrontDrive.setPower(2 * -fl);
            leftBackDrive.setPower(2 * -bl);
            rightFrontDrive.setPower(2 * fr);
            rightBackDrive.setPower(2 * br);

            shooter.setPower(gamepad1.left_stick_y);
            // Send telemetry message to signify robot running;
            //telemetry.addData("Shooter Running",  "%.2f", gamepad1.a);
            telemetry.update();

            // Pace this loop so jaw action is reasonable speed.
            sleep(50);
        }
    }
}
