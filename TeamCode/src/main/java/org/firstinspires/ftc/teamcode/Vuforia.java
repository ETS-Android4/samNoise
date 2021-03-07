package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

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

public class Vuforia {
    // Constants
    private static final int MAX_TARGETS = 4;
    private static final double ON_AXIS = 10;      // Within 1.0 cm of target center-line
    private static final double CLOSE_ENOUGH = 20;      // Within 2.0 cm of final target standoff

    // Select which camera you want use.  The FRONT camera is the one on the same side as the screen.  Alt. is BACK
    private static final VuforiaLocalizer.CameraDirection CAMERA_CHOICE = VuforiaLocalizer.CameraDirection.FRONT;


    /* Private class members. */
    private LinearOpMode myOpMode;       // Access to the OpMode object
    private RobotConfiguration myRobot;        // Access to the Robot hardware
    private VuforiaTrackables targets;        // List of active targets
    private OpenGLMatrix lastLocation = null;

    VuforiaTrackable redTowerGoalTarget;

    // Navigation data is only valid if targetFound == true;
    private boolean targetFound;    // set to true if Vuforia is currently tracking a target
    private String targetName;     // Name of the currently tracked target
    private double robotX;         // X displacement from target center
    private double robotY;         // Y displacement from target center
    private double robotBearing;   // Robot's rotation around the Z axis (CCW is positive)
    private double targetRange;    // Range from robot's center to target in mm
    private double targetBearing;  // Heading of the target , relative to the robot's unrotated center
    private double relativeBearing;// Heading to the target from the robot's current bearing.
    //   eg: a Positive RelativeBearing means the robot must turn CCW to point at the target image.

    /* Constructor */
    public Vuforia() {

        targetFound = false;
        targetName = null;
        targets = null;

        robotX = 0;
        robotY = 0;
        targetRange = 0;
        targetBearing = 0;
        robotBearing = 0;
        relativeBearing = 0;
    }

    /***
     * Send telemetry data to indicate navigation status
     */
    public void addNavTelemetry() {
        if (targetFound) {
            // Display the current visible target name, robot info, target info, and required robot action.
            myOpMode.telemetry.addData("Visible", targetName);
            myOpMode.telemetry.addData("Robot", "[X]:[Y] (B) [%5.0fmm]:[%5.0fmm] (%4.0f°)",
                    robotX, robotY, robotBearing);
            myOpMode.telemetry.addData("Target", "[R] (B):(RB) [%5.0fmm] (%4.0f°):(%4.0f°)",
                    targetRange, targetBearing, relativeBearing);
            myOpMode.telemetry.addData("- Turn    ", "%s %4.0f°", relativeBearing < 0 ? ">>> CW " : "<<< CCW", Math.abs(relativeBearing));
            myOpMode.telemetry.addData("- Strafe  ", "%s %5.0fmm", robotY < 0 ? "LEFT" : "RIGHT", Math.abs(robotY));
            myOpMode.telemetry.addData("- Distance", "%5.0fmm", Math.abs(robotX));
        } else {
            myOpMode.telemetry.addData("Visible", "- - - -");
        }
    }

    /***
     * Start tracking Vuforia images
     */
    public void activateTracking() {

        // Start tracking any of the defined targets
        if (targets != null)
            targets.activate();
    }


    /***
     * Initialize the Target Tracking and navigation interface
     * @param opMode    pointer to OpMode
     * @param robot     pointer to Robot hardware class
     */
    public void initVuforia(LinearOpMode opMode, RobotConfiguration robot) {

        // Save reference to OpMode and Hardware map
        myOpMode = opMode;
        myRobot = robot;

        /**
         * Start up Vuforia, telling it the id of the view that we wish to use as the parent for
         * the camera monitor.
         * We also indicate which camera on the RC that we wish to use.
         */

        // VuforiaLocalizer.Parameters parameters = new VuforiaLocalizer.Parameters(R.id.cameraMonitorViewId);  // Use this line to see camera display
        VuforiaLocalizer.Parameters parameters = new VuforiaLocalizer.Parameters();                             // OR... Use this line to improve performance


        parameters.vuforiaLicenseKey = "AcYC6AH/////AAABmXCogx/uOEBBlp8PDkvHBr0DziDvQ/Mg5fU35QSxRsITTMw8TkwHevQ+GO2y1o+TU88K+nXwg8kr0x95HuasPwrKKh//mkzAdMiuG6IcCkOiamzyuA7lW6n4Sk8teot+chWz8+N05eHqh7sUzkxLTFxha35HUbAMjyx3kZ6leYBPzM1diBEawxTD9hvEIKQ7TC/bj/JrLe4g7QhRrBf5ihK83cghFo03S+kHZ53e/kJdJ5B8WACBF7904I/OpZF22mKDkdaWm24OzYDkIGCijxHlVOq+ErZqVRzkkfyIK7m6YJAIRzldkQeVCwrUYjuuhl3dOeifo6/0CX5yriRN3wmdp7sHzmAQ3Mj0kDrmM0Cm";

        parameters.cameraDirection = CAMERA_CHOICE;
        parameters.useExtendedTracking = false;
        VuforiaLocalizer vuforia = ClassFactory.getInstance().createVuforia(parameters);
        ;

        // Load the data sets for the trackable objects. These particular data
        // sets are stored in the 'assets' part of our application.
        VuforiaTrackables targetsUltimateGoal = vuforia.loadTrackablesFromAsset("UltimateGoal");
        redTowerGoalTarget = targetsUltimateGoal.get(1);
        redTowerGoalTarget.setName("Red Tower Goal Target"); //Maybe need this one


        // create an image translation/rotation matrix to be used for all images
        // Essentially put all the image centers 6" above the 0:0:0 origin,
        // but rotate them so they along the -X axis.
        OpenGLMatrix targetOrientation = OpenGLMatrix
                .translation(0, 0, 150)
                .multiplied(Orientation.getRotationMatrix(
                        AxesReference.EXTRINSIC, AxesOrder.XYZ,
                        AngleUnit.DEGREES, 90, 0, -90));

        /**
         * Create a transformation matrix describing where the phone is on the robot.
         *
         * The coordinate frame for the robot looks the same as the field.
         * The robot's "forward" direction is facing out along X axis, with the LEFT side facing out along the Y axis.
         * Z is UP on the robot.  This equates to a bearing angle of Zero degrees.
         *
         * The phone starts out lying flat, with the screen facing Up and with the physical top of the phone
         * pointing to the LEFT side of the Robot.  If we consider that the camera and screen will be
         * in "Landscape Mode" the upper portion of the screen is closest to the front of the robot.
         *
         * If using the rear (High Res) camera:
         * We need to rotate the camera around it's long axis to bring the rear camera forward.
         * This requires a negative 90 degree rotation on the Y axis
         *
         * If using the Front (Low Res) camera
         * We need to rotate the camera around it's long axis to bring the FRONT camera forward.
         * This requires a Positive 90 degree rotation on the Y axis
         *
         * Next, translate the camera lens to where it is on the robot.
         * In this example, it is centered (left to right), but 110 mm forward of the middle of the robot, and 200 mm above ground level.
         */

        final int CAMERA_FORWARD_DISPLACEMENT = 110;   // Camera is 110 mm in front of robot center
        final int CAMERA_VERTICAL_DISPLACEMENT = 200;   // Camera is 200 mm above ground
        final int CAMERA_LEFT_DISPLACEMENT = 0;     // Camera is ON the robots center line

        OpenGLMatrix phoneLocationOnRobot = OpenGLMatrix
                .translation(CAMERA_FORWARD_DISPLACEMENT, CAMERA_LEFT_DISPLACEMENT, CAMERA_VERTICAL_DISPLACEMENT)
                .multiplied(Orientation.getRotationMatrix(
                        AxesReference.EXTRINSIC, AxesOrder.YZX,
                        AngleUnit.DEGREES, CAMERA_CHOICE == VuforiaLocalizer.CameraDirection.FRONT ? 90 : -90, 0, 0));


        redTowerGoalTarget.setLocation(targetOrientation);
        ((VuforiaTrackableDefaultListener) redTowerGoalTarget.getListener()).setPhoneInformation(phoneLocationOnRobot, parameters.cameraDirection);

    }


    /***
     * Return the distance in meters the robot is from the target
     *
     * @return distance in meters
     */
    public double distanceFromTarget() {

        double distance;


        // getUpdatedRobotLocation() will return null if no new information is available since
        // the last time that call was made, or if the trackable is not currently visible.
        OpenGLMatrix robotLocationTransform = ((VuforiaTrackableDefaultListener) redTowerGoalTarget.getListener()).getUpdatedRobotLocation();
        VectorF translation = lastLocation.getTranslation();
        distance = translation.get(0);


        return distance;
    }

}

