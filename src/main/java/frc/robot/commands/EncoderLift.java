/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class EncoderLift extends Command {
  double lifterEncoderDistance = 0;
  double speed;
  double distance;
  boolean isGoingDown;
  public EncoderLift(double speed, double distance) {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    this.speed = speed;
    this.distance = distance;
    //requires(Robot.lift);
    }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    /*if(Robot.lift.getLifterEncoderDistance() > distance) {
      isGoingDown = true;
    }
    else {
      isGoingDown = false;
    }*/
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    //lifterEncoderDistance = Robot.lift.getLifterEncoderDistance();
    //Robot.lift.setSpeedWithDirection(speed, distance);
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    if(!isGoingDown && lifterEncoderDistance >= distance) {
      return true;
    }
    else if(isGoingDown && lifterEncoderDistance <= distance) {
      return true;
    }
    else {
      return false;
    }
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    //change this to incorporate limit switches
    if(distance == 0){
      //Robot.lift.resetEncoder();
    }
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}
