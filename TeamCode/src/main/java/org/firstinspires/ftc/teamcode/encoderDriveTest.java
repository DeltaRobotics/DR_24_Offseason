package org.firstinspires.ftc.teamcode;


import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;


@Autonomous(name="encoderDriveTest")
//
//
//
// @Disabled

public class encoderDriveTest extends LinearOpMode
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


        motorRF.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        motorLF.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        motorRB.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        motorLB.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        motorRB.setDirection(DcMotorSimple.Direction.REVERSE);
        motorRF.setDirection(DcMotorSimple.Direction.REVERSE);

        motorRF.setMode(DcMotor.RunMode.RUN_USING_ENCODER);




        waitForStart();


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
