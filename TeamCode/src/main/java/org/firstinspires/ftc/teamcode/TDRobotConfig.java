package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
//import com.qualcomm.robotcore.hardware.VoltageSensor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.VoltageSensor;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

public class TDRobotConfig {

    /* Private OpMode members. */ //May make public later
    DcMotor leftTread = null;
    DcMotor rightTread = null;

    /* local OpMode members. */
    HardwareMap hwMap = null;
    private final ElapsedTime period = new ElapsedTime();
    //VoltageSensor vs = hwMap.voltageSensor.get("DQ16N6NX"); //Change this

    /* Constructor */
    public TDRobotConfig() {

    }

    /* Initialize standard Hardware interfaces */
    public void init(HardwareMap ahwMap) {
        // Save reference to Hardware map
        hwMap = ahwMap;

        // Define and Initialize Motors
        leftTread = hwMap.get(DcMotor.class, "leftTread");
        rightTread = hwMap.get(DcMotor.class, "rightTread");


        //Temporary directions for drive train, change after testing if needed.
        leftTread.setDirection(DcMotor.Direction.REVERSE);
        rightTread.setDirection(DcMotor.Direction.FORWARD);

        // Set all motors to zero power
        stopDriveTrain();


        // Set all motors to run without encoders. To be removed later i am testing something
        // Change to RUN_USING_ENCODERS if encoders are installed.
        leftTread.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        rightTread.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);


    }

    public void stopDriveTrain() {
        leftTread.setPower(0);
        rightTread.setPower(0);
    }

    public void driveTrain(double b){
        if (b>0) {
            leftTread.setPower(1);
            rightTread.setPower(1);
        }
        if (b<0) {
            leftTread.setPower(-1);
            rightTread.setPower(-1);
        }
        else{
            leftTread.setPower(0);
            rightTread.setPower(0);
        }
    }
}
