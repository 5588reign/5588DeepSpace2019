/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;


import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.RobotMap;


public class HatchGrabber extends Command {
  Compressor c = new Compressor(0);
  boolean isHatchGrabber;
  public HatchGrabber(boolean isHatchGrabber) {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    this.isHatchGrabber = isHatchGrabber;
  }


  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    c.setClosedLoopControl(true);
    if (isHatchGrabber) {
      if (RobotMap.hatchGrabber.get().equals(DoubleSolenoid.Value.kForward)) {
        RobotMap.hatchGrabber.set(DoubleSolenoid.Value.kReverse);
      }
      else if(RobotMap.hatchGrabber.get().equals(DoubleSolenoid.Value.kReverse)) {
       RobotMap.hatchGrabber.set(DoubleSolenoid.Value.kForward);
     }
    }
    else {
      if (RobotMap.hatchPusher.get().equals(DoubleSolenoid.Value.kForward)) {
        RobotMap.hatchPusher.set(DoubleSolenoid.Value.kReverse);
      }
      else if(RobotMap.hatchPusher.get().equals(DoubleSolenoid.Value.kReverse)) {
       RobotMap.hatchPusher.set(DoubleSolenoid.Value.kForward);
     }
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
