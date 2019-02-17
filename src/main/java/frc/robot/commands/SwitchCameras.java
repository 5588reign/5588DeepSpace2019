/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.networktables.NetworkTableInstance;
import frc.robot.Robot;

public class SwitchCameras extends Command {
  String cameraToSwitchTo = "";
  public SwitchCameras() {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    if(Robot.switchingCameras){
      //front
      cameraToSwitchTo = Robot.camera1.getName();
    }
    else {
      //floor
      cameraToSwitchTo = Robot.camera3.getName();
    }
    NetworkTableInstance.getDefault().getTable("").getEntry(cameraToSwitchTo);
    //PutString("camera selection", cameraToSwitchTo);
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return true;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    Robot.switchingCameras = false;
    if (Robot.switchingCameras) {
      Robot.switchingCameras = false;
    }
    else {
      Robot.switchingCameras = true;
    }
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}
