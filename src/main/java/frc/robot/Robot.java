/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import org.opencv.imgproc.Imgproc;

import edu.wpi.cscore.MjpegServer;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.vision.VisionPipeline;
import edu.wpi.first.vision.VisionRunner;
import edu.wpi.first.vision.VisionThread;
import org.opencv.core.Rect;
import frc.robot.subsystems.ClimbingPneumatics;
import frc.robot.subsystems.Drive;
import frc.robot.subsystems.Gyroscope;
import frc.robot.subsystems.HatchPneumatics;
import frc.robot.subsystems.I2Csubsystem;
import frc.robot.subsystems.Lift;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  public static Drive drive = new Drive();
  public static HatchPneumatics hatchPneumatics = new HatchPneumatics();  
  public static I2Csubsystem i2Csub = new I2Csubsystem();
  public static ClimbingPneumatics climbingPneumatics = new ClimbingPneumatics();
  public static Lift lift = new Lift();
  public static Gyroscope gyroscope = new Gyroscope();
  public static OI m_oi;
  public static UsbCamera camera1;
  public static UsbCamera camera2;
  public static UsbCamera camera3;
  public static MjpegServer cameraswitch;
  public static Boolean switchingCameras = true;

  private static final int IMG_WIDTH = 320;
  private static final int IMG_HEIGHT = 240;

  private VisionThread visionThread;
  public double centerX = 0.0;
  public static double turnAwayFromCenter = 0.0;

  private final Object imgLock = new Object();


  Command m_autonomousCommand;
  SendableChooser<Command> m_chooser = new SendableChooser<>();
  

  /**
   * This function is run when the robot is first started up and should be
   * used for any initialization code.
   */
  @Override
  public void robotInit() {
    m_oi = new OI();
    m_chooser.setDefaultOption("Default Auto", new Command(){
    
      @Override
      protected boolean isFinished() {
        return false;
      }
    });
    // chooser.addOption("My Auto", new MyAutoCommand());
    SmartDashboard.putData("Auto mode", m_chooser);
   
    camera1 = CameraServer.getInstance().startAutomaticCapture("camera front", 0);
    camera1.setBrightness(1); 
    camera1.setResolution(IMG_WIDTH, IMG_HEIGHT);

    /*visionThread = new VisionThread(camera1, new VisionGripPipeline(), pipeline -> { 
      if (!pipeline.filterContoursOutput().isEmpty()) {
        Rect r = Imgproc.boundingRect(pipeline.filterContoursOutput().get(0));
        synchronized (imgLock) {
          centerX = r.x + (r.width / 2);
        }
      }
    });
    
  visionThread.start();*/


    camera2 = CameraServer.getInstance().startAutomaticCapture("camera back", 1);
    camera2.setBrightness(1);
    camera3 = CameraServer.getInstance().startAutomaticCapture("camera floor", 2);
    camera3.setBrightness(1);

    cameraswitch = CameraServer.getInstance().addSwitchedCamera("camera Switch");
    cameraswitch.setSource(camera2);
  }

  /**
   * This function is called every robot packet, no matter the mode. Use
   * this for items like diagnostics that you want ran during disabled,
   * autonomous, teleoperated and test.
   *
   * <p>This runs after the mode specific periodic functions, but before
   * LiveWindow and SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {
  }

  /**
   * This function is called once each time the robot enters Disabled mode.
   * You can use it to reset any subsystem information you want to clear when
   * the robot is disabled.
   */
  @Override
  public void disabledInit() {
  }

  @Override
  public void disabledPeriodic() {
    Scheduler.getInstance().run();
  }

  /**
   * This autonomous (along with the chooser code above) shows how to select
   * between different autonomous modes using the dashboard. The sendable
   * chooser code works with the Java SmartDashboard. If you prefer the
   * LabVIEW Dashboard, remove all of the chooser code and uncomment the
   * getString code to get the auto name from the text box below the Gyro
   *
   * <p>You can add additional auto modes by adding additional commands to the
   * chooser code above (like the commented example) or additional comparisons
   * to the switch structure below with additional strings & commands.
   */
  @Override
  public void autonomousInit() {
    m_autonomousCommand = m_chooser.getSelected();

    /*
     * String autoSelected = SmartDashboard.getString("Auto Selector",
     * "Default"); switch(autoSelected) { case "My Auto": autonomousCommand
     * = new MyAutoCommand(); break; case "Default Auto": default:
     * autonomousCommand = new ExampleCommand(); break; }
     */

    // schedule the autonomous command (example)
    if (m_autonomousCommand != null) {
      m_autonomousCommand.start();
    }
  }

  /**
   * This function is called periodically during autonomous.
   */
  @Override
  public void autonomousPeriodic() {
    Scheduler.getInstance().run();
  }

  @Override
  public void teleopInit() {
    // This makes sure that the autonomous stops running when
    // teleop starts running. If you want the autonomous to
    // continue until interrupted by another command, remove
    // this line or comment it out.
    if (m_autonomousCommand != null) {
      m_autonomousCommand.cancel();
    }
  }

  /**
   * This function is called periodically during operator control.
   */
  @Override
  public void teleopPeriodic() {
    /*double centerX;
    synchronized (imgLock) {
      centerX = this.centerX;
    }
    turnAwayFromCenter = centerX - (IMG_WIDTH / 2);
    System.out.println(turnAwayFromCenter);*/

    Scheduler.getInstance().run();
  }

  /**
   * This function is called periodically during test mode.
   */
  @Override
  public void testPeriodic() {
  }
}
