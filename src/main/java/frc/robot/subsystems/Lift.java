/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
import frc.robot.commands.LiftCommand;
import frc.robot.*;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

/**
 * Add your docs here.
 */
public class Lift extends Subsystem implements MotherSystem {

  private final Encoder lifterEncoder = new Encoder(RobotMap.LIFTER_ENCODER_SOURCE_A, RobotMap.LIFTER_ENCODER_SOURCE_B);

  TalonSRX liftMotor = new TalonSRX(0);
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  public Lift ()
  {
    liftMotor.configFactoryDefault();
    liftMotor.setInverted(true);
  }

  public void setSpeed(double percentSpeed)
  {
    liftMotor.set(ControlMode.PercentOutput, percentSpeed);
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

  public void liftToggleXbox(XboxController joystick)
  {
    double speed = interpretSpeed(-joystick.getRawAxis(1));
    setSpeed(speed);

  }
  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
    setDefaultCommand(new LiftCommand());


  }
}
