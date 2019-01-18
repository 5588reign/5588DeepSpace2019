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
  Value value = DoubleSolenoid.Value.kOff;
  public HatchGrabber(DoubleSolenoid.Value value) {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    this.value = value;
  }


  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    c.setClosedLoopControl(true);
    RobotMap.hatchGrabber.set(value);
    System.out.println(RobotMap.hatchGrabber.get());

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
