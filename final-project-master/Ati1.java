package Zaddy;
import robocode.*;
//import java.util.*;
import java.awt.*;
import static robocode.util.Utils.normalRelativeAngleDegrees;
public class Ati1 extends TeamRobot
{
    protected boolean direction;
    protected String targetName;
    protected double targetBearing;
    protected double bearingFromGun;
    protected double absoluteBearing;
    protected double bearingFromRadar;

    public void run()
    {
		// Set colors
		setBodyColor(Color.red);
		setGunColor(Color.red);
		setRadarColor(Color.red);
		setScanColor(Color.red);
		setBulletColor(Color.red);
		
        setAdjustGunForRobotTurn( true );
            setAdjustRadarForGunTurn( false );   
        turnRadarRight(360);
        direction=true;     
        while(true)
        {
            if(targetName.equals("")){
            setTurnRadarLeft(10);

        execute();
            }else {
                setTurnGunRight(bearingFromGun);
                setTurnRight(absoluteBearing);
                setAhead(150);
                
                execute();
            }
        }
    }
    public void onScannedRobot(ScannedRobotEvent e)
    {	if (isTeammate(e.getName())) {
			return; 
			} else {
        targetName=e.getName();
        targetBearing=e.getBearing();
        absoluteBearing = getHeading() + e.getBearing();
		bearingFromGun = normalRelativeAngleDegrees(absoluteBearing - getGunHeading());
        bearingFromRadar=normalRelativeAngleDegrees(absoluteBearing-getRadarHeading());
		if (Math.abs(bearingFromGun) <= 3) {
			setTurnGunRight(bearingFromGun);
            setTurnRadarRight(bearingFromRadar);
			if (getGunHeat() == 0) {
				fire(Math.min(2 - Math.abs(bearingFromGun), getEnergy() - .1));
			}
		}
		else {
			setTurnGunRight(bearingFromGun);
		}
		if (bearingFromGun == 0) {
			scan();
		}
		
  }
}
    
    public void onHitWall(HitWallEvent e)
    {
        if(direction)
        {

            setBack(50);
            execute();
            direction =false;
 
        } else{

            setAhead(50);
            execute();
            direction=true;

        }
    }
    public void onHitRobot(HitRobotEvent e)
    {
        if(e.isMyFault())
        {
            if(direction)
        {
            stop();
            setBack(500);
            direction =false;
            setResume();
        } else{
            stop();
            setAhead(500);
            direction=true;
            setResume();
        }
        }
    }
    public void onHitByBullet(HitByBulletEvent e)
    {
        if(direction)
        {

            setBack(50);
            execute();
            direction =false;
 
        } else{

            setAhead(50);
            execute();
            direction=true;

        }
    }
		public void onWin(WinEvent e) {
		// Victory dance
		turnRight(36000);
	}
}