package org.firstinspires.ftc.teamcode;


import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.ElapsedTime;


@TeleOp(name="introToJavaBot")
@Disabled

public class introToJavaBot extends LinearOpMode
{
    public DcMotor motorRF = null;
    public DcMotor motorLF = null;
    public DcMotor motorRB = null;
    public DcMotor motorLB = null;

    double speed = .5;

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

        waitForStart();

        while (opModeIsActive())
        {
            motorRF.setPower((((-gamepad1.left_stick_y - gamepad1.left_stick_x) * 1) - (gamepad1.right_stick_x * 1)) * speed);
            motorRB.setPower((((-gamepad1.left_stick_y + gamepad1.left_stick_x) * 1) - (gamepad1.right_stick_x * 1)) * speed);
            motorLB.setPower((((-gamepad1.left_stick_y - gamepad1.left_stick_x) * 1) + (gamepad1.right_stick_x * 1)) * speed);
            motorLF.setPower((((-gamepad1.left_stick_y + gamepad1.left_stick_x) * 1) + (gamepad1.right_stick_x * 1)) * speed);

            if(gamepad1.a || gamepad1.b){
                speed = 1;
            }




        }
    }
}
