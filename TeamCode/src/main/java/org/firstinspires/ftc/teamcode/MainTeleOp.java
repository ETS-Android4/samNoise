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

        // Send telemetry message to signify robot waiting;
        telemetry.addData("Get Ready", "To Party");    //
        telemetry.update();

        // Wait for the game to start (driver presses PLAY)
        waitForStart();

        // run until the end
        // of the match (driver presses STOP)
        while (opModeIsActive()) {

            robot.setShooter(gamepad2.a);


            robot.drive(gamepad1.right_stick_x, gamepad1.right_stick_y, gamepad1.left_trigger, gamepad1.right_trigger);

            robot.toggleIntakeFlip(gamepad2.b);

            robot.intakeWheels(gamepad2.left_bumper, gamepad2.right_bumper);

            if(gamepad2.x) {
                robot.shootHighGoal();
            }

            telemetry.addData("Distance From Target", "= %.1f",
                    robot.getDistance());


            telemetry.update();

            // Pace this loop so jaw action is reasonable speed.
            sleep(50);
        }
    }
}
