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
    public DcMotor arm = null;

    public Servo wrist = null;
    public Servo clawR = null;
    public Servo clawL = null;

    int Encoder = 30;
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

        while (opModeIsActive())
        {
            motorRF.setPower((((-gamepad1.left_stick_y - gamepad1.left_stick_x) * 1) - (gamepad1.right_stick_x * 1)) * speed);
            motorRB.setPower((((-gamepad1.left_stick_y + gamepad1.left_stick_x) * 1) - (gamepad1.right_stick_x * 1)) * speed);
            motorLB.setPower((((-gamepad1.left_stick_y - gamepad1.left_stick_x) * 1) + (gamepad1.right_stick_x * 1)) * speed);
            motorLF.setPower((((-gamepad1.left_stick_y + gamepad1.left_stick_x) * 1) + (gamepad1.right_stick_x * 1)) * speed);
            /*
            if(gamepad1.a && button){
                if(speed==1){ speed=.5;
                }
                else {speed=1;
                }
                button = false;
            }
            if(!gamepad1.a && !button){
                button = true;
            }

             */



            if(gamepad1.dpad_up) {
                //up
                Encoder=100;

                wrist.setPosition(.62);
            }
            if(gamepad1.dpad_down) {
                //down
                Encoder=30;

                wrist.setPosition(.42);
            }
            /*
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

            arm.setTargetPosition(Encoder);
            arm.setPower(1);
            arm.setMode(DcMotor.RunMode.RUN_TO_POSITION);


            telemetry.addData("wrist", wrist.getPosition());
            telemetry.update();
        }
    }
}
