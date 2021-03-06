
package org.usfirst.frc.team2856.robot;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {
    final String defaultAuto = "Default";
    final String customAuto = "My Auto";
    String autoSelected;
    SendableChooser chooser;
    
    
    private Joystick left, right;//xbox
    private Victor[] wheels;/*0,1   left       2,3   right*/
    private Victor roller;
    private Victor arm, winch;
    private DriveTrain dt;
    
    Teleop teleop;//teleop loop
    
    
    
    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {
        /*
    	chooser = new SendableChooser();
        chooser.addDefault("Default Auto", defaultAuto);
        chooser.addObject("My Auto", customAuto);
        SmartDashboard.putData("Auto choices", chooser);
        */
        init();
        
        
    }
    
	/**
	 * This autonomous (along with the chooser code above) shows how to select between different autonomous modes
	 * using the dashboard. The sendable chooser code works with the Java SmartDashboard. If you prefer the LabVIEW
	 * Dashboard, remove all of the chooser code and uncomment the getString line to get the auto name from the text box
	 * below the Gyro
	 *
	 * You can add additional auto modes by adding additional comparisons to the switch structure below with additional strings.
	 * If using the SendableChooser make sure to add them to the chooser code above as well.
	 */
    public void autonomousInit() {
    	//autoSelected = (String) chooser.getSelected();
		autoSelected = SmartDashboard.getString("Auto Selector", defaultAuto);
		System.out.println("Auto selected: " + autoSelected);
    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {
    	switch(autoSelected) {
    	case customAuto:
        //Put custom auto code here   
            break;
    	case defaultAuto:
    	default:
    	//Put default auto code here
            break;
    	}
    }

    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
       teleop.teleopLoop();
        
    }
    
    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
    
    }
    
    /**
     * This helper function initializes variables such as the motors, DriveTrain
     * and the teleop loop
     */
    public void init(){
    	/*SpeedControllers*/
    	//wheels
    	//left
    	wheels[0] = new Victor(RobotMap.fLMotorChannel);
    	wheels[1] = new Victor(RobotMap.rLMotorChannel);
        //right
    	wheels[2] = new Victor(RobotMap.fRMotorChannel);
    	wheels[3] = new Victor(RobotMap.rRMotorChannel);		
    	
        roller = new Victor(RobotMap.rollerChannel);
        arm = new Victor(RobotMap.armChannel);
        winch =  new Victor(RobotMap.winchChannel);
        
        
        /*Joysticks*/
        left  = new Joystick(0);
        right = new Joystick(1);
        
        //DriveTrain takes in the wheels
        dt = new DriveTrain(wheels);
    	
        //teleop loop 
        //note: will later add in the arm and winch
        teleop = new Teleop(dt, left, right, roller);
    }
}
