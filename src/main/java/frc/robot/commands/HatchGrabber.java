/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;


import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;



public class HatchGrabber extends Command {
  boolean isHatchGrabber;
  public HatchGrabber(boolean isHatchGrabber) {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(Robot.hatchPneumatics); 
    this.isHatchGrabber = isHatchGrabber;
  }


  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    if(isHatchGrabber){
      Robot.hatchPneumatics.changeValueSelectedPneumatic(Robot.hatchPneumatics.hatchGrabber);
    }
    else{
      Robot.hatchPneumatics.changeValueSelectedPneumatic(Robot.hatchPneumatics.hatchPusher);
    }
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return true;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
   //RobotMap.hatchGrabber.set(DoubleSolenoid.Value.kOff);
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}
