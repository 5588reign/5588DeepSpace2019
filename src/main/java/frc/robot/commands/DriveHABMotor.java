/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import javax.lang.model.util.ElementScanner6;

import edu.wpi.first.wpilibj.command.Command;

import frc.robot.Robot;


public class DriveHABMotor extends Command {
  double input;
  public DriveHABMotor(double input) {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    super(3);
    requires(Robot.habDrive);
    this.input = input;
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    Robot.habDrive.setSpeedHABMotor(input);
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return isTimedOut();
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    Robot.habDrive.setSpeedHABMotor(0);
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}
