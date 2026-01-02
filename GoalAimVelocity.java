package org.firstinspires.ftc.teamcode.pedroPathing;

import static org.firstinspires.ftc.teamcode.pedroPathing.Tuning.follower;

import com.pedropathing.math.MathFunctions;

public class GoalAimVelocity {
    private final double redGoalPoseY = 144.0;
    private final double redGoalPoseX = 144.0;

    double dis = Math.sqrt
            (Math.pow(redGoalPoseY - follower.getPose().getY(), 2) + Math.pow(redGoalPoseX - follower.getPose().getX(), 2));
    double deg = Math.atan2
            ((redGoalPoseY - follower.getPose().getY()), (redGoalPoseX - follower.getPose().getX()));

    public double AutoAim(){
        return follower.getHeading() - deg;
    }

    public double getTargetVel(){
        return MathFunctions.clamp
                (0.0246153 * Math.pow(dis, 2) + 1.69857 * dis + 747.19611, 0, 1500);
    }
}

