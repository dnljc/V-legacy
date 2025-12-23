package org.firstinspires.ftc.teamcode.pedroPathing;

import static org.firstinspires.ftc.teamcode.pedroPathing.Tuning.follower;

public class GoalAimVelocity {
    private final double blueGoalPoseY = 144.0;
    private final double blueGoalPoseX = 144.0;

    double dis = Math.sqrt(
            Math.pow(blueGoalPoseY - follower.getPose().getY(), 2)
          + Math.pow(blueGoalPoseX - follower.getPose().getX(), 2)
    );

    double deg = Math.atan2(
            (blueGoalPoseY - follower.getPose().getY()),
            (blueGoalPoseX - follower.getPose().getX())
    );

    public double AutoAim() {
        return follower.getHeading() - deg;
    }

    public double getTargetVel() {
        if (dis > 134.0) {
            return 1500.0;
        } else {
            return 900.0;
        }
    }
}
