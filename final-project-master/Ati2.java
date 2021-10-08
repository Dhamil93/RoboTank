package Zaddy;
import robocode.*;
import static robocode.util.Utils.normalRelativeAngleDegrees;
import java.awt.*;

public class Ati2 extends TeamRobot {
	/*
	 Dami's run method
	 */
	public void run() {
		// Set colors
		setBodyColor(Color.red);
		setGunColor(Color.red);
		setRadarColor(Color.red);
		setScanColor(Color.red);
		setBulletColor(Color.red);

		// Loop forever
		while (true) {
			turnGunRight(10); // Scans automatically
		}
	}

	/**
	 * onScannedRobot:  We have a target. Go for it.
	 */
	public void onScannedRobot(ScannedRobotEvent e) {
		if (isTeammate(e.getName())) {
			return; 
			} else {
		
		// Determining the  exact location of the robot
		double absoluteBearing = getHeading() + e.getBearing();
		double bearingFromGun = normalRelativeAngleDegrees(absoluteBearing - getGunHeading());

		// If an enemy`s robot is close enough, fire!
		if (Math.abs(bearingFromGun) <= 3) {
			turnGunRight(bearingFromGun);
			
			if (getGunHeat() == 0) {
				fire(Math.min(3 - Math.abs(bearingFromGun), getEnergy() - .1));
			}
		} // else turn the gun.
		
		else {
			turnGunRight(bearingFromGun);
		}
		// Generates another scan event if we see a robot.
		if (bearingFromGun == 0) {
			scan();
		}
	}
	}

	public void onWin(WinEvent e) {
		// Victory dance
		turnRight(36000);
	}
}				
