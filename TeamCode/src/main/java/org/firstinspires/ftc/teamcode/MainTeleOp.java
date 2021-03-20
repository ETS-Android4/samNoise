package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@TeleOp (name="Victoria's TeleOp", group="Mecanum")

public class MainTeleOp  extends LinearOpMode {

    /* Declare OpMode members. */
    RobotConfiguration robot = new RobotConfiguration();   // Using preset robot configuration

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
        robot.stopServo();

        // Wait for the game to start (driver presses PLAY)
        waitForStart();

        // run until the end
        // of the match (driver presses STOP)
        while (opModeIsActive()) {

            robot.setShooter(gamepad2.y);


            robot.drive(gamepad1.right_stick_x, gamepad1.right_stick_y, gamepad1.left_trigger, gamepad1.right_trigger);
            //robot.driveTwo(gamepad1.left_stick_y, gamepad1.right_stick_y, gamepad1.left_stick_x);
            robot.intakeWheels(gamepad2.left_bumper, gamepad2.right_bumper);
          robot.intakeTwo(gamepad2.dpad_up, gamepad2.dpad_down);
           //telemetry.addData(robot.intakeWheels(gamepad2.left_bumper), "hi");

           robot.intakeThree(gamepad2.x);






            telemetry.update();

            // Pace this loop so jaw action is reasonable speed.
            sleep(50);
        }
    }
}
