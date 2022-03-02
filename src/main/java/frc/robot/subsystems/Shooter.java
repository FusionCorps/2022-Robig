package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.TalonFXControlMode;
import com.ctre.phoenix.motorcontrol.TalonFXInvertType;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import static frc.robot.Constants.Shooter.*;

public class Shooter extends SubsystemBase {

    WPI_TalonFX shooter0;

    public double shootK;

    public double target;

    public double min_vel;
    public double max_vel;

    private ShuffleboardTab tab = Shuffleboard.getTab("General");
    public NetworkTableEntry shootKTab =
            tab.add("shootK", 1.0)
                    .getEntry();

    public Shooter() {
        shooter0 = new WPI_TalonFX(SHOOTER_ID);
        shooter0.configSelectedFeedbackSensor(FeedbackDevice.IntegratedSensor);

        shooter0.setInverted(TalonFXInvertType.Clockwise);

        target = SHOOTER_TARGET;

        shootK = 1.0;

        min_vel = SHOOTER_LOWER_VEL;
        max_vel = SHOOTER_UPPER_VEL;

    }

    public void setShooter(double pct) {
        shooter0.set(pct);
    }

    public double getShooterVel() {
        return shooter0.getSelectedSensorVelocity();
    }

    public boolean isTarget() {
        double v = shooter0.getSelectedSensorVelocity();
        return (v > min_vel && v < max_vel);
    }

    public boolean isShooting() {
        double v = shooter0.getSelectedSensorVelocity();
        return (v > (target*20000 - 2000) && v < (target*20000 + 2000));
    }

}
