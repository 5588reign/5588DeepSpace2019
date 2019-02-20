/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class EncoderDrive extends Command {
  private double rightEncoderDistance = 0;
  private double leftEncoderDistance = 0;
  private double distance;
  private double speed;
  private double correctingLeft = 0;
  private double correctingRight = 0;

  public EncoderDrive(double distance, double speed) {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(Robot.drive);
    this.distance = distance;
    this.speed = speed;
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    Robot.drive.resetEncoders();
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    rightEncoderDistance = Robot.drive.getRightEncoderDistance();
    leftEncoderDistance = Robot.drive.getLeftEncoderDistance();

    /*if (rightEncoderDistance - leftEncoderDistance >= 0.05) {
      correctingLeft = .1;
      System.out.println("Activated Correcting Left:");
    }

    if (leftEncoderDistance - rightEncoderDistance >= 0.05) {
      correctingRight = .1;
      System.out.println("Activated Correcting Right:");
    }*/

    Robot.drive.setSpeed(speed + correctingLeft, speed + correctingRight);

    correctingLeft = 0;
    correctingRight = 0;

    //System.out.println("Right encoder: " + rightEncoderDistance);
    //System.out.println("Left encoder: " + leftEncoderDistance);
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    if(distance > 0 && distance <= rightEncoderDistance && distance <= leftEncoderDistance)
      return true;
    else if(distance < 0 && distance >= leftEncoderDistance) {
      //distance < 0 && distance >= rightEncoderDistance && distance >= leftEncoderDistance
      return true;
    }
 
    else
    return false;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}
