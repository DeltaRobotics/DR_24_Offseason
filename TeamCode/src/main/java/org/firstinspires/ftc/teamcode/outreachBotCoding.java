package org.firstinspires.ftc.teamcode;


import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;


@TeleOp(name="outreachBotCoding")
//@Disabled

public class outreachBotCoding extends LinearOpMode
{

    public DcMotor motorRF = null;
    public DcMotor motorLF = null;
    public DcMotor motorRB = null;
    public DcMotor motorLB = null;

    public DcMotor wheel = null;

    public Servo flicker = null;

    int encoder = 0;
    boolean encoderOn = true;

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

        wheel = hardwareMap.dcMotor.get("wheel");
        wheel.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        flicker = hardwareMap.servo.get("flicker");

        flicker.setPosition(.5);

        waitForStart();

        while (opModeIsActive())
        {
            motorRF.setPower((((-gamepad1.left_stick_y - gamepad1.left_stick_x) * 1) - (gamepad1.right_stick_x * 1)) * .5);
            motorRB.setPower((((-gamepad1.left_stick_y + gamepad1.left_stick_x) * 1) - (gamepad1.right_stick_x * 1)) * .5);
            motorLB.setPower((((-gamepad1.left_stick_y - gamepad1.left_stick_x) * 1) + (gamepad1.right_stick_x * 1)) * .5);
            motorLF.setPower((((-gamepad1.left_stick_y + gamepad1.left_stick_x) * 1) + (gamepad1.right_stick_x * 1)) * .5);

            if (gamepad1.dpad_up) {
                wheel.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                wheel.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
                encoderOn = true;
            }
            if (gamepad1.dpad_down) {
                wheel.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
                encoderOn = false;
            }


            if (gamepad1.a) {
                if (encoderOn){
                    wheel.setTargetPosition(500);
                    wheel.setPower(.1);
                    wheel.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                }
                else{
                    wheel.setPower(.1);
                }
            }
            if (gamepad1.b) {
                if (encoderOn){
                    wheel.setTargetPosition(0);
                    wheel.setPower(.1);
                    wheel.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                }
                else{
                    wheel.setPower(-.1);
                }
            }
            if (gamepad1.x){
                wheel.setPower(0);
            }

            if (gamepad1.dpad_left) {
                flicker.setPosition(1);
            }
            if (gamepad1.dpad_right) {
                flicker.setPosition(0);
            }





        }
    }
}
