/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;


import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;



public class HatchGrabber extends Command {
  Compressor c = new Compressor(0);
  boolean isHatchGrabber;
  private final DoubleSolenoid hatchGrabber = new DoubleSolenoid(1,0);
  private final DoubleSolenoid hatchPusher = new DoubleSolenoid(2,3);
  public HatchGrabber(boolean isHatchGrabber) {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(Robot.pneumatics); 
    this.isHatchGrabber = isHatchGrabber;
  }


  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    if(isHatchGrabber){
      Robot.pneumatics.changeValueSelectPneumatic(hatchGrabber);
    }
    else{
      Robot.pneumatics.changeValueSelectPneumatic(hatchPusher);
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
