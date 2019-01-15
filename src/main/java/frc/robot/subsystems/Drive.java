/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
import frc.robot.commands.JoystickDrive;

/**
 * An example subsystem.  You can replace me with your own Subsystem.
 */
public class Drive extends Subsystem {
  private final SpeedController leftmotor = new VictorSP(RobotMap.leftDriveMotor);
  private final SpeedController rightmotor = new VictorSP(RobotMap.rightDriveMotor);

  public Drive(){
    super();
    leftmotor.setInverted(true);
  } 
  
  public void setSpeed(double leftSpeed, double rightSpeed){
    leftmotor.set(leftSpeed);
    rightmotor.set(rightSpeed);
  }

  public void driveJoystick(Joystick joystick){
    double leftSpeed = interpretSpeed(joystick.getRawAxis(1)) - interpretSpeed(joystick.getRawAxis(0));
    double rightSpeed = interpretSpeed(joystick.getRawAxis(1)) + interpretSpeed(joystick.getRawAxis(0));
    setSpeed(leftSpeed, rightSpeed);
  }

  public double deadZone(double speed)
  {
    if (Math.abs(speed) < .05)
    {
      return 0;
    }
    else
      return speed;
  }

  public double squareSpeed(double speed)
  {
    if (speed < 0)
    {
      speed = -(speed * speed);
    }
    else
    {
      speed = speed * speed;
    }
    return speed;
  }

  public double interpretSpeed(double speed)
  {
    speed = squareSpeed(speed);
    speed = deadZone(speed);
    return speed;
  }

  public void stop(){
    leftmotor.stopMotor();
    rightmotor.stopMotor();
  }
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
    setDefaultCommand(new JoystickDrive());
  }

}
