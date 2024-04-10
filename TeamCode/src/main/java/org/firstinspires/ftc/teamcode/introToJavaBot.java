package org.firstinspires.ftc.teamcode;


import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;


@TeleOp(name="introToJavaBot")
//@Disabled

public class introToJavaBot extends LinearOpMode
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

    int Encoder = 10;
    double speed = .5;

    boolean button = true;
    boolean buttonx = true;
    boolean buttony = true;

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

        wrist.setPosition(.42);
        clawL.setPosition(.6);
        clawR.setPosition(.5);

        waitForStart();

        while (opModeIsActive())
        {
            motorRF.setPower((((-gamepad1.left_stick_y - gamepad1.left_stick_x) * 1) - (gamepad1.right_stick_x * 1)) * speed);
            motorRB.setPower((((-gamepad1.left_stick_y + gamepad1.left_stick_x) * 1) - (gamepad1.right_stick_x * 1)) * speed);
            motorLB.setPower((((-gamepad1.left_stick_y - gamepad1.left_stick_x) * 1) + (gamepad1.right_stick_x * 1)) * speed);
            motorLF.setPower((((-gamepad1.left_stick_y + gamepad1.left_stick_x) * 1) + (gamepad1.right_stick_x * 1)) * speed);
            /*
            if(gamepad1.a && button){
                if(speed==1){
                    speed=.5;
                }
                else {speed=1;}
                button = false;
            }
            if(!gamepad1.a && !button){
                button = true;
            }

             */



            if(gamepad1.dpad_up) {
                //up
                Encoder=55;

                wrist.setPosition(.55);
            }
            if(gamepad1.dpad_down) {
                //down
                Encoder=6;

                wrist.setPosition(.4);
            }
            /*
            if(gamepad1.dpad_right) {
                // mid position
                Encoder=150;
                wrist.setPosition(.5);
            }
            if(gamepad1.dpad_left) {
                // mid high position
                Encoder=200;
                wrist.setPosition(.6);
            }


            //fine adjust
            if(gamepad1.x&&buttonx){
                wrist.setPosition(wrist.getPosition()-.05);
                buttonx=false;
            }
            else if(gamepad1.y&&buttony){
                buttony=false;
                wrist.setPosition(wrist.getPosition()+.05);
            }
            if(!gamepad1.x&&!buttonx){
                buttonx=true;
            }
            if(!gamepad1.y&&!buttony) {
                buttony = true;
            }

             */


            if (gamepad1.right_bumper){
                clawL.setPosition(.4);
                clawR.setPosition(.65);
            }
            if (gamepad1.left_bumper){
                clawL.setPosition(.7);
                clawR.setPosition(.4);
            }

            shoulderR.setTargetPosition(Encoder);
            shoulderR.setPower(1);
            shoulderR.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            shoulderL.setTargetPosition(Encoder);
            shoulderL.setPower(1);
            shoulderL.setMode(DcMotor.RunMode.RUN_TO_POSITION);


            telemetry.addData("encoder", shoulderL.getCurrentPosition());
            telemetry.addData("wrist", wrist.getPosition());
            telemetry.update();

        }
    }
}
