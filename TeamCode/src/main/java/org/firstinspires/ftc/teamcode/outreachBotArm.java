package org.firstinspires.ftc.teamcode;


import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;


@TeleOp(name="outreachBot")
//@Disabled

public class outreachBotArm extends LinearOpMode
{

    public DcMotor motorRF = null;
    public DcMotor motorLF = null;
    public DcMotor motorRB = null;
    public DcMotor motorLB = null;

    public DcMotor arm = null;

    public Servo wrist = null;
    public Servo clawR = null;
    public Servo clawL = null;
    public Servo slides = null;

    int encoder = 0;
    boolean encoderOn = true;
    boolean button1 = false;
    boolean button2 = false;
    int Encoder = 45;

    boolean a = false;
    boolean b = false;

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


        arm = hardwareMap.dcMotor.get("arm");
        arm.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        arm.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        arm.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);


       wrist = hardwareMap.servo.get("wrist");
       clawR = hardwareMap.servo.get("clawR");
       clawL = hardwareMap.servo.get("clawL");
       slides = hardwareMap.servo.get("slides");

        wrist.setPosition(.37);
        clawL.setPosition(.6);
        clawR.setPosition(.5);
        slides.setPosition(.5);

        arm.setTargetPosition(Encoder);
        arm.setPower(1);
        arm.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        waitForStart();

        while (opModeIsActive())
        {
            motorRF.setPower((((gamepad1.left_stick_y - -gamepad1.left_stick_x) * 1) - (gamepad1.right_stick_x * 1)) * .5);
            motorRB.setPower((((gamepad1.left_stick_y + -gamepad1.left_stick_x) * 1) - (gamepad1.right_stick_x * 1)) * .5);
            motorLB.setPower((((gamepad1.left_stick_y - -gamepad1.left_stick_x) * 1) + (gamepad1.right_stick_x * 1)) * .5);
            motorLF.setPower((((gamepad1.left_stick_y + -gamepad1.left_stick_x) * 1) + (gamepad1.right_stick_x * 1)) * .5);


            if (gamepad1.a && a) {
                Encoder += 20;
                a = false;
            }
            if (gamepad1.b && b) {
                Encoder -= 20;
                b = false;
            }
            if (!gamepad1.a && !a) {
                a = true;
            }
            if (!gamepad1.b && !b) {
                b = true;
            }

        if (gamepad1.x) {
            Encoder = 45;
            wrist.setPosition(.37);
        }
        if (gamepad1.y) {
            Encoder = 340;
            wrist.setPosition(.57);
        }

        if (gamepad1.right_bumper){
          clawL.setPosition(.4);
          clawR.setPosition(.65);
        }
        if (gamepad1.left_bumper){
            clawL.setPosition(.6);
            clawR.setPosition(.5);
        }

        //servoFineAdjust(slides,gamepad1.dpad_right,gamepad1.dpad_left,.01);







            arm.setTargetPosition(Encoder);
            arm.setPower(1);
            arm.setMode(DcMotor.RunMode.RUN_TO_POSITION);

            telemetry.addData("slides", slides.getPosition());
            telemetry.addData("wrist", wrist.getPosition());
            telemetry.addData("arm encoder", arm.getCurrentPosition());
            telemetry.update();
        }
    }
    public void servoFineAdjust(Servo s, boolean increase, boolean decrease, double increment){
        if (increase && button1) {
            s.setPosition(s.getPosition() + increment);
            button1 = false;
        } else if (decrease && button2) {
            s.setPosition(s.getPosition() - increment);
            button2 = false;
        } else if (!increase && !button1) {
            button1 = true;
        } else if (!decrease && !button2) {
            button2 = true;
        }
    }
}
