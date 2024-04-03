package org.firstinspires.ftc.teamcode;


import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;


@TeleOp(name="firstNewbeAuto")
@Disabled

public class firstNewbeAuto extends LinearOpMode
{
    public DcMotor motorRF = null;
    public DcMotor motorLF = null;
    public DcMotor motorRB = null;
    public DcMotor motorLB = null;
    public DcMotor arm = null;

    public Servo wrist = null;
    public Servo clawR = null;
    public Servo clawL = null;

    int Encoder = 30;

    @Override
    public void runOpMode() throws InterruptedException
    {
        motorRF = hardwareMap.dcMotor.get("motorRF");
        motorLF = hardwareMap.dcMotor.get("motorLF");
        motorRB = hardwareMap.dcMotor.get("motorRB");
        motorLB = hardwareMap.dcMotor.get("motorLB");
        arm = hardwareMap.dcMotor.get("arm");

        wrist = hardwareMap.servo.get("wrist");
        clawR = hardwareMap.servo.get("clawR");
        clawL = hardwareMap.servo.get("clawL");


        motorRF.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        motorLF.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        motorRB.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        motorLB.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        arm.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        motorRB.setDirection(DcMotorSimple.Direction.REVERSE);
        motorRF.setDirection(DcMotorSimple.Direction.REVERSE);
        arm.setDirection(DcMotorSimple.Direction.REVERSE);

        arm.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        arm.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        arm.setTargetPosition(Encoder);
        arm.setPower(1);
        arm.setMode(DcMotor.RunMode.RUN_TO_POSITION);


        wrist.setPosition(.42);
        clawL.setPosition(.6);
        clawR.setPosition(.5);

        waitForStart();

        driveForward(1);

        sleep(1000);





    }

    public void driveForward(double power){
        //1 is forward -1 is backwards
        motorRF.setPower(power);
        motorLF.setPower(power);
        motorRB.setPower(power);
        motorLB.setPower(power);
    }

    public void turn(double power,boolean right){
        if (right) {
            motorRF.setPower(-power);
            motorLF.setPower(power);
            motorRB.setPower(-power);
            motorLB.setPower(power);
        }else {
            motorRF.setPower(power);
            motorLF.setPower(-power);
            motorRB.setPower(power);
            motorLB.setPower(-power);
        }


    }

}
