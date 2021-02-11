package org.firstinspires.ftc.teamcode;



import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

// Lets please change the name of this, like what???? - Victoria

@TeleOp

public class UGTeleOpAS extends LinearOpMode {

    private ElapsedTime runtime = new ElapsedTime();

    //private DcMotor leftDrive = null;
    //private DcMotor rightDrive = null;

    private DcMotor shooter;


   // @Override

    public void runOpMode() {

        telemetry.addData("Status", "Initialized");

        telemetry.update();
        shooter  = hardwareMap.get(DcMotor.class, "shooter");
        shooter.setDirection(DcMotor.Direction.REVERSE);

        /*leftDrive  = hardwareMap.get(DcMotor.class, "left_drive");

        rightDrive = hardwareMap.get(DcMotor.class, "right_drive");

        leftDrive.setDirection(DcMotor.Direction.FORWARD);

        rightDrive.setDirection(DcMotor.Direction.REVERSE);*/

        waitForStart();

        runtime.reset();


        while (opModeIsActive()) {

           // double shooterPower = .8;
            shooter.setPower(-1.0);


            /*double leftPower;

            double rightPower;

            double drive = -gamepad1.left_stick_y;

            double turn  =  gamepad1.right_stick_x;

            leftPower    = Range.clip(drive + turn, -1.0, 1.0) ;

            rightPower   = Range.clip(drive - turn, -1.0, 1.0) ;


            leftDrive.setPower(leftPower);

            rightDrive.setPower(rightPower);



            // Show the elapsed game time and wheel power.

            telemetry.addData("Status", "Run Time: " + runtime.toString());

            telemetry.addData("Motors", "left (%.2f), right (%.2f)", leftPower, rightPower);*/

            telemetry.update();

        }

    }

}
