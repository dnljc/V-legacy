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

        PIDFCoefficients pidfCoefficients =
                new PIDFCoefficients(P, 0, 0, F);
        fly1.setPIDFCoefficients(
                DcMotor.RunMode.RUN_USING_ENCODER,
                pidfCoefficients
        );
    }

    @Override
    public void loop() {
        double targetVel = goalAimVelocity.getTargetVel();

        fly1.setVelocity(targetVel);
        fly2.setVelocity(targetVel);

        double curVel = fly1.getVelocity();
        double error = targetVel - curVel;

        telemetry.addData("TARGET VEL", targetVel);
        telemetry.addData("CURRENT VEL", curVel);
        telemetry.addData("ERROR", error);
    }
}
