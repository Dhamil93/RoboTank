package Reaper;

import robocode.*;
import java.awt.*;

public class Ken2 extends RateControlRobot {

	int turnCounter;
	public void run() {
	
		setBodyColor(Color.red);
		setGunColor(Color.red);
		setRadarColor(Color.red);
		setBulletColor(Color.red);
		setScanColor(Color.red);

		turnCounter = 0;
		setGunRotationRate(15);
		
		while (true) {
			if (turnCounter % 64 == 0) {
				// Straighten out, if we were hit by a bullet and are turning
				setTurnRate(0);
				// Go forward with a velocity of 4
				setVelocityRate(4);
			}
			if (turnCounter % 64 == 32) {
				// Go backwards, faster
				setVelocityRate(-6);
			}
			turnCounter++;
			execute();
		}
	}

	public void onScannedRobot(ScannedRobotEvent e) {
			if (isTeammate(e.getName())) {
			return;
			} else {
		double distance = e.getDistance();
		if (distance<150){
		fire(3);
		} else if (distance<250){
			fire(2.5);
		} else if (distance<400){
			fire(1.5);
		} else {
			fire(0.5);
		}}
	}

	public void onHitByBullet(HitByBulletEvent e) {
		// Turn to confuse the other robot
		setTurnRate(5);
	}
	
	public void onHitWall(HitWallEvent e) {
		// Move away from the wall
		setVelocityRate(-1 * getVelocityRate());
	}
	
	public void onWin(WinEvent e) {
		for (int i = 0; i < 50; i++) {
			turnRight(60);
			turnLeft(60);
		}
	}
}
