package Reaper;

import robocode.*;

import robocode.HitRobotEvent;
import robocode.ScannedRobotEvent;

import java.awt.*;



public class Ken3 extends TeamRobot {

	/**
	 * Ken`s run method - Sphere
	 */
	public void run() {
		// Set colors
		setBodyColor(Color.red);
		setGunColor(Color.red);
		setRadarColor(Color.red);
		setScanColor(Color.red);

		// Loop forever
		while (true) {
			// Tell the game that when we take move,
			
			setTurnRight(10000);
			// Limit our speed to 5
			setMaxVelocity(5);
			// Start moving (and turning)
			ahead(10000);
			// Repeat.
		}
	}

	/**
	 * onScannedRobot: Fire hard!
	 */
	public void onScannedRobot(ScannedRobotEvent e) {
		if (isTeammate(e.getName())) {
			return; 
			} else {
		double distance = e.getDistance();
		
		if (distance<100){
		fire(3);
		} else if (distance<250){
			fire(2.5);
		} else if (distance<400){
			fire(1.5);
		} else {
			fire(0.5);
			fire(0.5);
		}
	}
	}


	
	public void onHitRobot(HitRobotEvent e) {
		if (e.getBearing() > -10 && e.getBearing() < 10) {
			fire(3);
		}
		if (e.isMyFault()) {
			turnRight(10);
		}
	}
		public void onWin(WinEvent e) {
		// Victory dance
		turnRight(36000);
	}
}
