/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
import frc.robot.commands.LightSensor;

/**
 * Add your docs here.
 */
public class I2Csubsystem extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  // last three zeros are place holders until we find the rest of the address
  //private static final I2C i2CWriter = new I2C(I2C.Port.kOnboard, 0x20);
  private static final I2C i2CReaderFront = new I2C(I2C.Port.kOnboard, 0x21);
  //CHANGE THIS VALUE
  private static final I2C i2CReaderBack = new I2C(I2C.Port.kOnboard, 5678); 
  private String frontSensors;
  private String backSensors;


  /*public void writeToLight(boolean turnOn){
    
    if(turnOn){
     i2CWriter.write(0, 0);
      }
    else {
      i2CWriter.write(0xff,0xff);
    }
  }
  */

  public void readFromLight() {
    byte[] sensorsByte = new byte[1];
    
    i2CReaderFront.readOnly(sensorsByte, sensorsByte.length);
    frontSensors = String.format("%8s", Integer.toBinaryString(sensorsByte[0] & 0xFF)).replace(' ', '0');
    
    i2CReaderBack.readOnly(sensorsByte, sensorsByte.length);
    backSensors = String.format("%8s", Integer.toBinaryString(sensorsByte[0] & 0xFF)).replace(' ', '0');
       
    //System.out.println(frontSensors);
  }

  //1=left, 2=straight, 3=right, 0= light is not reading
  public int frontOnlyReading() {
    if(frontSensors.charAt(3)=='0') {
      return RobotMap.FORWARD_IS_READING;
    }

    for (int i = 0; i<7; i++) {
      if(frontSensors.charAt(i)=='0') {
        if(i<3) {
          return RobotMap.RIGHT_IS_READING;
        }
        if(i>3) {
          return RobotMap.LEFT_IS_READING;
        }
      }
    }

    return RobotMap.NONE_IS_READING;
  }

  public int backIsReading() {
    if(backSensors.charAt(3)=='0'){
      return RobotMap.BACK_MIDDLE_READING; //change this to indicate moving on to step three: reading from both front and back
    }
    if(backSensors.contains("0")) {
      return RobotMap.FORWARD_IS_READING;
    }

    return RobotMap.NONE_IS_READING;
  }

  public int frontAndBackReading() { 
    String frontAndBackMiddleSensors = frontSensors.substring(2,5) + backSensors.substring(2,5);
    switch(frontAndBackMiddleSensors){
      case "001001" : 
        return RobotMap.FORWARD_IS_READING;
      case "100100" : 
        return RobotMap.FORWARD_IS_READING;
      case "101101" : 
        return RobotMap.FORWARD_IS_READING;
      case "100001" : 
        return RobotMap.RIGHT_IS_READING;
      case "011001" : 
        return RobotMap.RIGHT_IS_READING;
      case "001100" : 
        return RobotMap.LEFT_IS_READING;
      case "110100" :
        return RobotMap.LEFT_IS_READING;
      default :
        return RobotMap.NONE_IS_READING;
    }
  }
  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    setDefaultCommand(new LightSensor());
  }
}
