/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
import frc.robot.commands.JoystickDrive;
import frc.robot.*;

/**
 * An example subsystem.  You can replace me with your own Subsystem.
 */
public class Drive extends Subsystem implements MotherSystem {
  //distance per pulse = pi * the wheel diameter in inches / pulse per revolution * fudge factor
  private static final double DISTANCE_PER_PULSE_INCHES = (Math.PI * 1.25) / 1024 * 1;
  private final SpeedController leftmotor = new VictorSP(RobotMap.LEFT_DRIVE_MOTOR);
  private final SpeedController rightmotor = new VictorSP(RobotMap.RIGHT_DRIVE_MOTOR);

  private final Encoder leftEncoder = new Encoder(RobotMap.LEFT_ENCODER_SOURCE_A, RobotMap.LEFT_ENCODER_SOURCE_B);
  private final Encoder rightEncoder = new Encoder(RobotMap.RIGHT_ENCODER_SOURCE_A, RobotMap.RIGHT_ENCODER_SOURCE_B);


  public Drive() {
    super();
    leftmotor.setInverted(false);
    rightmotor.setInverted(true);
    rightEncoder.setDistancePerPulse(DISTANCE_PER_PULSE_INCHES);
    leftEncoder.setDistancePerPulse(DISTANCE_PER_PULSE_INCHES);
    leftEncoder.setReverseDirection(true);
    rightEncoder.setReverseDirection(false);
  } 

  public void resetEncoders() {
    leftEncoder.reset();
    rightEncoder.reset();
  }

  public double getRightEncoderDistance() {
    return rightEncoder.getDistance();
  }

  public double getLeftEncoderDistance() {
    return leftEncoder.getDistance();
  }
  
  public void setSpeed(double leftSpeed, double rightSpeed) {
    leftmotor.set(leftSpeed);
    rightmotor.set(rightSpeed);
  }

  public void driveJoystick(Joystick joystick) { 
    double leftSpeed = interpretSpeed(-joystick.getRawAxis(1)) + interpretSpeed(joystick.getRawAxis(0));
    double rightSpeed = interpretSpeed(-joystick.getRawAxis(1)) - interpretSpeed(joystick.getRawAxis(0));
    setSpeed(leftSpeed, rightSpeed);
  }

  public double deadZone(double speed) {
    if (Math.abs(speed) < .05) {
      return 0;
    }
    else {
      return speed;
    }
  }

  public double squareSpeed(double speed) {
    if (speed < 0) {
      speed = -(speed * speed);
    }
    else {
      speed = speed * speed;
    }
    return speed;
  }

  public double interpretSpeed(double speed) {
    speed = squareSpeed(speed);
    speed = deadZone(speed);
    return speed;
  }

  public void stop(){
    leftmotor.stopMotor();
    rightmotor.stopMotor();
  }
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
    setDefaultCommand(new JoystickDrive());
  }

}
