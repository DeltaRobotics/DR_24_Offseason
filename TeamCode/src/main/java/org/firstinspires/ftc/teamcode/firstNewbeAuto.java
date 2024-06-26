package org.firstinspires.ftc.teamcode;


import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;


@Autonomous(name="firstNewbeAuto")
//
//
//
// @Disabled

public class firstNewbeAuto extends LinearOpMode
{
    public DcMotor motorRF = null;
    public DcMotor motorLF = null;
    public DcMotor motorRB = null;
    public DcMotor motorLB = null;
    public DcMotor shoulderR = null;
    public DcMotor shoulderL = null;

    public Servo wrist = null;
    public Servo clawR = null;
    public Servo clawL = null;

    int Encoder = 6;

    @Override
    public void runOpMode() throws InterruptedException
    {
        motorRF = hardwareMap.dcMotor.get("motorRF");
        motorLF = hardwareMap.dcMotor.get("motorLF");
        motorRB = hardwareMap.dcMotor.get("motorRB");
        motorLB = hardwareMap.dcMotor.get("motorLB");
        shoulderR = hardwareMap.dcMotor.get("shoulderR");
        shoulderL = hardwareMap.dcMotor.get("shoulderL");

        wrist = hardwareMap.servo.get("wrist");
        clawR = hardwareMap.servo.get("clawR");
        clawL = hardwareMap.servo.get("clawL");


        motorRF.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        motorLF.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        motorRB.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        motorLB.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        shoulderL.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        shoulderR.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        motorRB.setDirection(DcMotorSimple.Direction.REVERSE);
        motorRF.setDirection(DcMotorSimple.Direction.REVERSE);
        shoulderL.setDirection(DcMotorSimple.Direction.REVERSE);

        shoulderR.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        shoulderL.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        shoulderR.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        shoulderL.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        shoulderR.setTargetPosition(Encoder);
        shoulderL.setTargetPosition(Encoder);
        shoulderR.setPower(1);
        shoulderL.setPower(1);
        shoulderR.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        shoulderL.setMode(DcMotor.RunMode.RUN_TO_POSITION);


        wrist.setPosition(.4);
        clawL.setPosition(.4);
        clawR.setPosition(.65);

        waitForStart();

        //first forward
        driveForward(.5);
        sleep(760);

        driveForward(0);
        sleep(100);

        //turn to go under
        turn(.5,false);
        sleep(850);

        driveForward(0);
        sleep(100);

        //go under
        driveForward(-.5);
        sleep(1900);

        driveForward(0);
        sleep(100);

        //fix turn angle
        turn(.5,true);
        sleep(25);

        driveForward(0);
        sleep(100);



    }

    public void driveForward(double power){
        //1 is forward -1 is backwards
        motorRF.setPower(power);
        motorLF.setPower(power);
        motorRB.setPower(power);
        motorLB.setPower(power);
    }

    public void turn(double power,boolean right) {
        if (right) {
            motorRF.setPower(-power);
            motorLF.setPower(power);
            motorRB.setPower(-power);
            motorLB.setPower(power);
        } else {
            motorRF.setPower(power);
            motorLF.setPower(-power);
            motorRB.setPower(power);
            motorLB.setPower(-power);
        }
    }

    public void strafe(double power,boolean right){
        if (right) {
            motorRF.setPower(-power);
            motorLF.setPower(power);
            motorRB.setPower(power);
            motorLB.setPower(-power);
        }else {
            motorRF.setPower(power);
            motorLF.setPower(-power);
            motorRB.setPower(-power);
            motorLB.setPower(power);
        }
    }

}
