package frc.robot.commands.climb;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Climb;

import static frc.robot.Constants.Climb.CLIMB_MIN_POS;

public class ClimbActivate extends CommandBase {

    Climb mClimb;

    public ClimbActivate(Climb climb) {
        mClimb = climb;
        addRequirements(mClimb);
    }

    @Override
    public void execute() {
        int key = mClimb.getClimbPosKey();

        if (key == 0) {
            mClimb.setClimbPos(CLIMB_MIN_POS + 256);
        } else {
            mClimb.setClimb(-0.1);
        }

    }

}