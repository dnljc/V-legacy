package org.firstinspires.ftc.teamcode.pedroPathing;

import com.bylazar.configurables.annotations.Configurable;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.PIDFCoefficients;

@TeleOp
@Configurable
public class PIDFlywheel extends OpMode {

    public DcMotorEx fly1, fly2;

    public double highVel = 1500;
    public double lowVel = 900;

    public double P = 0; // TUNAR
    public double F = 0; // TUNAR
    GoalAimVelocity goalAimVelocity;


    @Override
    public void init() {
        fly1 = hardwareMap.get(DcMotorEx.class, "lançador1");
        fly2 = hardwareMap.get(DcMotorEx.class, "lançador2");
        fly1.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        fly1.setDirection(DcMotorSimple.Direction.FORWARD);
        fly2.setDirection(DcMotorSimple.Direction.REVERSE);
        goalAimVelocity = new GoalAimVelocity();

        PIDFCoefficients pidfCoefficients = new PIDFCoefficients(P, 0, 0, F);
        fly1.setPIDFCoefficients(DcMotor.RunMode.RUN_USING_ENCODER, pidfCoefficients);
    }

    @Override
    public void loop() {
        fly1.setVelocity(goalAimVelocity.getTargetVel());
        fly2.setVelocity(goalAimVelocity.getTargetVel());

        double curVel = fly1.getVelocity();
        double error = goalAimVelocity.getTargetVel() - curVel;
        double tVel = goalAimVelocity.getTargetVel();

        telemetry.addData("TARGET VEL: ", tVel);
        telemetry.addData("CURRENT VEL: ",curVel);
        telemetry.addData("ERROR", error);
        telemetry.addData("DISTANCE", goalAimVelocity.getDistance());
    }
}
