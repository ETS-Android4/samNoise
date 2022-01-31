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
    DcMotor arm = null;
    CRServo ram = null;

    // Declare Contants  (not variable, can't change in program)
    private final double THRESHOLD = 0.05;
    private final double UPPOS = 0.5; //Maybe Should change to a zero position
    private final double DOWNPOS = 0;
    private final double STOPCRSERVO = 0;
    private final double FORWARDCRSERVO = 1.0; //test direction
    private final double REVERSECRSERVO = -1.0;
    private final double GOALDISTANCE = 1; //Change during testing also add a unit

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
        arm = hwMap.get(DcMotor.class, "arm");
        ram = hwMap.get(CRServo.class, "ram");

        //Temporary directions for drive train, change after testing if needed.
        leftTread.setDirection(DcMotor.Direction.REVERSE);
        rightTread.setDirection(DcMotor.Direction.FORWARD);
        arm.setDirection(DcMotor.Direction.FORWARD);

        // Set all motors to zero power
        stopDriveTrain();


        // Set all motors to run without encoders. To be removed later i am testing something
        // Change to RUN_USING_ENCODERS if encoders are installed.
        leftTread.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        rightTread.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        arm.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);


    }

    //GENERAL METHODS

    public void stopDriveTrain() {
        leftTread.setPower(0);
        rightTread.setPower(0);
    }

    public void stopCRServo() {

        ram.setPower(STOPCRSERVO);
    }

    public void stopArm(){

        arm.setPower(0);
    }

    //TELE-OP METHODS

    public void driveTrain1(double a, double b) {
        if (a > 0){
            leftTread.setPower(1);
            rightTread.setPower(-1);
        }
        if (a < 0){
            leftTread.setPower(-1);
            rightTread.setPower(1);
        }
        if (b > 0) {
            leftTread.setPower(-1);
            rightTread.setPower(-1);
        }
        if (b < 0) {
            leftTread.setPower(1);
            rightTread.setPower(1);
        } else {
            leftTread.setPower(0);
            rightTread.setPower(0);
        }
    }

    public void tankDriveTrain(double e, double f){
        if (e > 0){
            leftTread.setPower(1);
        }
        if (e < 0){
            leftTread.setPower(-1);
        }
        if (f > 0) {
            rightTread.setPower(1);
        }
        if (f < 0) {
            rightTread.setPower(-1);
        } else {
            leftTread.setPower(0);
            rightTread.setPower(0);
        }
    }

    public void runArm(double m){
        if (m > 0){
            arm.setPower(1);
        }
        if (m < 0){
            arm.setPower(-1);
        }
        else{
            arm.setPower(0);
        }
    }

        public void runRam(boolean x, boolean z) {
            if (x) {
                ram.setPower(FORWARDCRSERVO);
            }
            else if(z){
                ram.setPower(REVERSECRSERVO);
            }
            else {
                ram.setPower(STOPCRSERVO);

            }
        }

        //AUTONOMOUS METHODS

    public void autoUnitForward(int seconds) {
        leftTread.setPower(1);
        rightTread.setPower(1);
    }

    public void autoUnitReverse(int seconds) {
        leftTread.setPower(-1);
        rightTread.setPower(-1);

    }

    public void autoUnitClockwiseTurn(int seconds) {
        leftTread.setPower(1);
        rightTread.setPower(-1);

    }

    public void autoUnitCounterClockwiseTurn(int seconds) {
        leftTread.setPower(-1);
        rightTread.setPower(1);

    }
}
