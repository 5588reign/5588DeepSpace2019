/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;

public class GyroscopeTurn extends Command {
  private double degreesTurned = 0;
  private double degreesToTravel;
  private boolean isTurningRight;

  public GyroscopeTurn(double degreesToTravel) {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    this.degreesToTravel = degreesToTravel;
    if (degreesToTravel > 0) {
      isTurningRight = true;
    }
    else {
      isTurningRight = false;
    }
    requires(Robot.gyroscope);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    Robot.gyroscope.reset();
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    degreesTurned = Robot.gyroscope.getAngle();
    if (isTurningRight) {
      Robot.drive.setSpeed(.2, -.2);
    }
    else {
      Robot.drive.setSpeed(-.2, .2);
    }
    System.out.println(degreesTurned);
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    if (isTurningRight && degreesTurned >= degreesToTravel) {
      return true;
    } 
    else if (!isTurningRight && degreesTurned <= degreesToTravel) {
      return true;
    }
    else {
      return false;
    }
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
