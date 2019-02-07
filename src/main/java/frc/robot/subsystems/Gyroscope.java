/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * Add your docs here.
 */
public class Gyroscope extends Subsystem {
  ADXRS450_Gyro gyro = new ADXRS450_Gyro();
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  public void calibrate() {
    gyro.calibrate();
  }

	public double getAngle() {
		return gyro.getAngle();
  }

	public void reset() {
		gyro.reset();
	}

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
}
