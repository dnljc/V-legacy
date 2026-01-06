package org.firstinspires.ftc.teamcode.pedroPathing;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

public class AutoAimOP extends OpMode {

    DcMotorEx procurador;
    DcMotorEx fly1;
    DcMotorEx fly2;
    GoalAimVelocity goalAimVelocity;


    @Override
    public void init() {
        procurador = hardwareMap.get(DcMotorEx.class, "procurador");
        fly1 = hardwareMap.get(DcMotorEx.class, "lançador1");
        fly2 = hardwareMap.get(DcMotorEx.class, "lançador2");
        goalAimVelocity = new GoalAimVelocity();

        procurador.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        fly1.setDirection(DcMotorSimple.Direction.FORWARD);
        fly2.setDirection(DcMotorSimple.Direction.REVERSE);
    }

    @Override
    public void loop() {
        procurador.setTargetPosition ((int) Math.floor(EncoderAngle(goalAimVelocity.autoAim())));
        procurador.setPower(0.7);

        telemetry.addData("VELOCITY fly1 : ", fly1.getVelocity());
    }
    public double EncoderAngle(double angulo){
        return (2000.0/180.0) * angulo;
    }
}
