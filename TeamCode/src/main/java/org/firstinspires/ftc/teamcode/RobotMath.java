package org.firstinspires.ftc.teamcode;
/**
 * This is NOT an opmode.
 *
 * This class is used to define all the methods used for our control calculations.
 *
 */

public class RobotMath {



    /**
     * Converts a distance in centimeters to wheel degrees turned for our wheels.
     *
     * @param m How much distance to travel
     *
     */
    public static int distanceToWheelDegrees(double m){
        int degrees;

        degrees = 0;  //Replace with equation

        return degrees;
    }


    /**
     * Uses the main battery voltage, distance, and pitch from the goal to figure out what
     * power should be used for the shooter.
     *
     * @param batteryVoltage Current voltage of the main battery
     * @param distance The distance from the goal in cm
     *
     */
    public static double shootingPower(double batteryVoltage){

        double power;


        power = 0; //Replace with equation. This will be using physics calculations

        return power;

    }

    /**
     * Converts x and y distance into vector distance in centimeters
     *
     * @param x distance in the x dimension away from the target in inches
     * @param y distance in the x dimension away from the target in inches
     *
     */

    public static double distanceFromTarget(double x, double y){

        return 0; //Return using pythagorean theorem

    }


}
