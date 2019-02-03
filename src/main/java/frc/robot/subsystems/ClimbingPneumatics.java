/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * Add your docs here.
 */
public class ClimbingPneumatics extends Subsystem {

  private final DoubleSolenoid FrontRobotRaise = new DoubleSolenoid(4, 5);
  private final DoubleSolenoid BackRobotRaise = new DoubleSolenoid(6, 7);
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  public ClimbingPneumatics() {
    FrontRobotRaise.set(DoubleSolenoid.Value.kReverse);
    BackRobotRaise.set(DoubleSolenoid.Value.kReverse);
  }

  public void setToForward() {
    FrontRobotRaise.set(DoubleSolenoid.Value.kForward);
    BackRobotRaise.set(DoubleSolenoid.Value.kForward);
  }

  public void setFrontToReverse() {
    FrontRobotRaise.set(DoubleSolenoid.Value.kReverse);
  }

  public void setBackToReverse() {
    BackRobotRaise.set(DoubleSolenoid.Value.kReverse);
  }
  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
}
