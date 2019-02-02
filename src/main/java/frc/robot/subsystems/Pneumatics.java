/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;

/**
 * Add your docs here.
 */
public class Pneumatics extends Subsystem {
  private final DoubleSolenoid hatchGrabber = new DoubleSolenoid(1,0);
  private final DoubleSolenoid hatchPusher = new DoubleSolenoid(2,3);
  private final DoubleSolenoid FrontRobotRaise = new DoubleSolenoid(4, 5);
  private final DoubleSolenoid BackRobotRaise = new DoubleSolenoid(6, 7);
  
  private final Compressor c = new Compressor(0); 

  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  public Pneumatics(){
    c.setClosedLoopControl(true);
  }


  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }

  public void changeValueSelectPneumatic(DoubleSolenoid selectedPneumatic) {
    if(selectedPneumatic.get().equals(DoubleSolenoid.Value.kForward)) {
      selectedPneumatic.set(DoubleSolenoid.Value.kReverse);
    }
     
    else if(selectedPneumatic.get().equals(DoubleSolenoid.Value.kReverse)){
      selectedPneumatic.set(DoubleSolenoid.Value.kForward);
    }
  }
}
