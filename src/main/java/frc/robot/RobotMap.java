/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {

  public static final int FRONT_LEFT_MOTOR = 12;
  public static final int BACK_LEFT_MOTOR = 13;
  public static final int FRONT_RIGHT_MOTOR = 14;
  public static final int BACK_RIGHT_MOTOR = 15;

  public static final int LEFT_ENCODER_SOURCE_A = 0;
  public static final int LEFT_ENCODER_SOURCE_B = 1;

  public static final int RIGHT_ENCODER_SOURCE_A = 2;
  public static final int RIGHT_ENCODER_SOURCE_B = 3;

  public static final int LIFTER_ENCODER_SOURCE_A = 4;
  public static final int LIFTER_ENCODER_SOURCE_B = 5;

  

  public static final int RIGHT_IS_READING = 1;
  public static final int FORWARD_IS_READING = 2;
  public static final int LEFT_IS_READING = 3;
  public static final int NONE_IS_READING = 0;
  public static final int BACK_MIDDLE_READING = 4;


  // For example to map the left and right motors, you could define the
  // following variables to use with your drivetrain subsystem.
  // public static int leftMotor = 1;
  // public static int rightMotor = 2;

  // If you are using multiple modules, make sure to define both the port
  // number and the module. For example you with a rangefinder:
  // public static int rangefinderPort = 1;
  // public static int rangefinderModule = 1;
}
