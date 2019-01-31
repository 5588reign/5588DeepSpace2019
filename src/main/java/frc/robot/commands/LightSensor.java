/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.RobotMap;

public class LightSensor extends Command {

  int readValue = -1;
  public LightSensor() {
    requires(Robot.i2Csub);
    //requires(Robot.drive);
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
    Robot.i2Csub.readFromLight();
    if(Robot.i2Csub.backIsReading()==RobotMap.NONE_IS_READING) {
      readValue = Robot.i2Csub.frontOnlyReading();
    }
    else if(Robot.i2Csub.backIsReading()!=RobotMap.NONE_IS_READING && Robot.i2Csub.frontOnlyReading() != RobotMap.NONE_IS_READING) {
      readValue = Robot.i2Csub.backIsReading();
      if (readValue == RobotMap.BACK_MIDDLE_READING) {
        readValue = Robot.i2Csub.frontAndBackReading();
      }
    }
    if(readValue == RobotMap.RIGHT_IS_READING) {
      Robot.drive.setSpeed(.15,.1);
    }
    if(readValue == RobotMap.LEFT_IS_READING) {
      Robot.drive.setSpeed(.1,.15);
    }
    if(readValue == RobotMap.FORWARD_IS_READING) {
      Robot.drive.setSpeed(.1,.1);
    }
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
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
