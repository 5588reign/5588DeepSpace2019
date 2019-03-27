/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.commands.DriveHABMotor;
import frc.robot.commands.JoystickHABDrive;

/**
 * Add your docs here.
 */
public class HABDrive extends Subsystem {
  private final SpeedController drivingHAB = new VictorSP(0);
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  
  public HABDrive(){
    drivingHAB.setInverted(false);
  }
  public void setSpeedHABMotor(double speed) {
    drivingHAB.set(speed);
  }

  public double deadZone(double speed) {
    if (Math.abs(speed) < .05) {
      return 0;
    }
    else {
      return speed;
    }
  }

  public double squareSpeed(double speed) {
    if (speed < 0) {
      speed = -(speed * speed);
    }
    else {
      speed = speed * speed;
    }
    return speed;
  }

  public double interpretSpeed(double speed) {
    speed = squareSpeed(speed);
    speed = deadZone(speed);
    return speed;
  }

  public void HABToggleXbox(XboxController joystick) {
    double speed = interpretSpeed(-joystick.getRawAxis(5));
    setSpeedHABMotor(speed);
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    setDefaultCommand(new JoystickHABDrive());
  }
}
