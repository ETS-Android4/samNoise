package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

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
public class RobotConfiguration {

    /* Public OpMode members. */
    public DcMotor  leftFrontDrive   = null;
    public DcMotor  rightFrontDrive  = null;
    public DcMotor  leftBackDrive     = null;
    public DcMotor  rightBackDrive = null;
    public DcMotor  shooter = null;

    /* local OpMode members. */
    HardwareMap hwMap           =  null;
    private final ElapsedTime period  = new ElapsedTime();

    /* Constructor */
    public RobotConfiguration(){

    }

    /* Initialize standard Hardware interfaces */
    public void init(HardwareMap ahwMap) {
        // Save reference to Hardware map
        hwMap = ahwMap;

        // Define and Initialize Motors
        leftFrontDrive  = hwMap.get(DcMotor.class, "leftFrontDrive");
        rightFrontDrive = hwMap.get(DcMotor.class, "rightFrontDrive");
        leftBackDrive   = hwMap.get(DcMotor.class, "leftBackDrive");
        rightBackDrive = hwMap.get(DcMotor.class, "rightBackDrive");
        shooter   = hwMap.get(DcMotor.class, "shooter");

        //Temporary directions for drive train, change after testing if needed.
        leftFrontDrive.setDirection(DcMotor.Direction.FORWARD);
        leftBackDrive.setDirection(DcMotor.Direction.FORWARD);
        rightFrontDrive.setDirection(DcMotor.Direction.REVERSE);
        rightBackDrive.setDirection(DcMotor.Direction.REVERSE);

        //Temporary directions for shooter based on previous file testing
        shooter.setDirection(DcMotorSimple.Direction.FORWARD);

        // Set all motors to zero power
        leftFrontDrive.setPower(0);
        leftBackDrive.setPower(0);
        rightFrontDrive.setPower(0);
        rightBackDrive.setPower(0);
        shooter.setPower(0);



        // Set all motors to run without encoders.
        // Change to RUN_USING_ENCODERS if encoders are installed.
        leftFrontDrive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        leftBackDrive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        rightFrontDrive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        rightBackDrive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        shooter.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);


    }

    /**
     * Uses x and y vectors to determine and set the motor power.
     *
     * @param xVector The amount to move the robot left or right
     * @param yVector The amount to move the robot forward or backward
     * Precondition: Both the parameters must fall between -1.0 - 1.0
     *
     */
    public void drive(float xVector, float yVector){

    }

    /**
     * Uses left trigger to determine how much to rotate robot.
     *
     * @param leftTrigger Value of leftTrigger
     * Precondition: The parameter must fall between 0 - 1.0
     *
     */
    public void rotateCounterClockwise(float leftTrigger){

    }

    /**
     * Uses right trigger to determine how much to rotate robot.
     *
     * @param rightTrigger Value of rightTrigger
     * Precondition: The parameter must fall between 0 - 1.0
     *
     */
    public void rotateClockwise(float rightTrigger){

    }

    /**
     * Will run the shooter if true
     *
     * @param run Should the shooter run?
     *
     */
    public void setShooter(boolean run){
        if(run){
            shooter.setPower(1.0);
        }else{
            shooter.setPower(0);
        }
    }



}

