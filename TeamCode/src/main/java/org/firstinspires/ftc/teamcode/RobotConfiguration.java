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

import org.firstinspires.ftc.robotcore.external.ClassFactory;

import static org.firstinspires.ftc.robotcontroller.external.samples.HardwarePushbot.MID_SERVO;

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

    /* Private OpMode members. */ //May make public later
    private DcMotor  leftFrontDrive   = null;
    private DcMotor  rightFrontDrive  = null;
    private DcMotor  leftBackDrive     = null;
    private DcMotor  rightBackDrive = null;
    private DcMotor  shooter = null;
    private Servo intakeFlip = null;
    private CRServo intakeWheels = null;

    // Declare Contants  (not variable, can't change in program)
    private final double THRESHOLD = 0.05;
    private final double UPPOS = 0.5; //Maybe Should change to a zero position
    private final double DOWNPOS = 0;
    private final double STOPCRSERVO = 0.5;
    private final double FORWARDCRSERVO = 1.0; //test direction
    private final double REVERSECRSERVO = 0;

    /* local OpMode members. */
    HardwareMap hwMap           =  null;
    private final ElapsedTime period  = new ElapsedTime();
    VoltageSensor vs = hwMap.voltageSensor.get("DQ16N6NX"); //Change this

    private double  driveYaw        = 0 ;   // Positive is CW

    Vuforia vuforia;

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
        leftFrontDrive.setDirection(DcMotor.Direction.REVERSE);
        leftBackDrive.setDirection(DcMotor.Direction.REVERSE);
        rightFrontDrive.setDirection(DcMotor.Direction.FORWARD);
        rightBackDrive.setDirection(DcMotor.Direction.FORWARD);


        //Intake Hardware
        intakeFlip = hwMap.get(Servo.class, "intakeFlip");
        intakeFlip.setPosition(UPPOS);
        intakeWheels = hwMap.crservo.get("intakeWheels");
        intakeWheels.resetDeviceConfigurationForOpMode();
        intakeWheels.setPower(STOPCRSERVO);




        shooter.setDirection(DcMotorSimple.Direction.REVERSE);

        // Set all motors to zero power
        stopDriveTrain();
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
    public void drive(float xVector, float yVector, float leftTrigger, float rightTrigger) {
        double forward = yVector;
        double strafe = xVector;
        double twist = -leftTrigger + rightTrigger;

        double fl = forward + twist - strafe;
        double fr = forward - twist - strafe;
        double bl = forward + twist + strafe;
        double br = forward - twist + strafe;

        double max = Math.max(Math.abs(fl), Math.max(Math.abs(bl), Math.max(Math.abs(br),Math.abs(fr))));
        if (max > 1){
            fl /= max;
            fr /= max;
            br /= max;
            bl /= max;
        }

        leftFrontDrive.setPower(2 * fl);
        leftBackDrive.setPower(2 * bl);
        rightFrontDrive.setPower(2 * fr);
        rightBackDrive.setPower(2 * br);
    }


    public void setShooter(boolean a){
        if(a){
          shooter.setPower(1.0);
        }
        else{
            shooter.setPower(0);
        }
    }

    public void shootingBasedOnDistance(double velocity){

    }

    /**
     * Will run the shooter if true
     *
     *
     */

    public void stopDriveTrain(){
        leftFrontDrive.setPower(0);
        leftBackDrive.setPower(0);
        rightFrontDrive.setPower(0);
        rightBackDrive.setPower(0);
    }

    /**
     * Will reverse position of the intake lever if __ button is pressed
     *
     * @param b gamepad1.__
     */
    public void toggleIntakeFlip(boolean b){
        if(b && intakeFlip.getPosition() == DOWNPOS){
            intakeFlip.setPosition(UPPOS);
        }
        else if(b && intakeFlip.getPosition() == UPPOS){
            intakeFlip.setPosition(DOWNPOS);
        }
    }

    /**
     * TELL BUILDERS intakeWheels NEEDS TO BE A CONT ROTATION SERVO (SPEED ONE FOR IW AND TORQUE FOR IF)
     *
     * @param x
     */
    public void intakeWheels(boolean x, boolean y){
        if(x){
            intakeWheels.setPower(FORWARDCRSERVO);
        }
        else if(y){
            intakeWheels.setPower(REVERSECRSERVO);
        }
        else{
            intakeWheels.setPower(STOPCRSERVO);
        }
    }

    /**
     * Returns the current voltage of the robot's main battery
     */
    public double getBatteryPower(){
        return vs.getVoltage();
    }

    public void setYaw(double yaw) {
        driveYaw = Range.clip(yaw, -1, 1);
    }




}

