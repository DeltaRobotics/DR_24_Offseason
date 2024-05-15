package org.firstinspires.ftc.teamcode;


import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.Range;


@TeleOp(name="swerveAttempt")
// @Disabled

public class swerveAttempt extends LinearOpMode
{
    public DcMotor right1 = null;
    public DcMotor right2 = null;
    public DcMotor left1 = null;
    public DcMotor left2 = null;

    double power = 0;
    double rightPodPosition = 0;
    double leftPodPosition = 0;
    double aTan = 0;
    double turnPowerRight = 0; //angle of the right stick
    double turnPowerLeft = 0; //angle of the left stick



    @Override
    public void runOpMode() throws InterruptedException {

        robotHardware robot = new robotHardware(hardwareMap);

        right1 = hardwareMap.dcMotor.get("three");
        right2 = hardwareMap.dcMotor.get("four");
        left1 = hardwareMap.dcMotor.get("one");
        left2 = hardwareMap.dcMotor.get("two");

        right1.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        right2.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        left1.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        left2.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);


        right1.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        right2.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        left1.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        left2.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        right1.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        right2.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        left1.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        left2.setMode(DcMotor.RunMode.RUN_USING_ENCODER);



        waitForStart();

        while (opModeIsActive()) {

            rightPodPosition = right2.getCurrentPosition() + right1.getCurrentPosition();
            leftPodPosition  = left2.getCurrentPosition()  + left1.getCurrentPosition();

            power = (Math.abs(gamepad1.left_stick_y) + Math.abs(gamepad1.left_stick_x));

            aTan = -Math.atan2(gamepad1.left_stick_x, -gamepad1.left_stick_y);
            if (gamepad1.left_stick_x + gamepad1.left_stick_y == 0){
                aTan = 0;
            }

            turnPowerRight = robot.odoPID(6.333 * 57.2958 * aTan, rightPodPosition);
            turnPowerLeft = robot.odoPID(6.333 * 57.2958 * aTan, leftPodPosition);

            right1.setPower(power + turnPowerLeft + gamepad1.right_stick_x);
            right2.setPower(-power + turnPowerLeft - gamepad1.right_stick_x);
            left1.setPower(power + turnPowerLeft - gamepad1.right_stick_x);
            left2.setPower(-power + turnPowerLeft + gamepad1.right_stick_x);


            telemetry.addData("turnPowerLeft", turnPowerLeft);
            telemetry.addData("aTan degrees", 57.2958 * aTan);
            telemetry.addData("stick power", power);
            telemetry.addData("three power", right1.getPower());
            telemetry.addData("four power", right2.getPower());

            //one +     two -
            telemetry.addData("delta1", rightPodPosition);
            telemetry.addData("delta2", leftPodPosition);
            telemetry.addData("real angle",(rightPodPosition / 6.333));
            telemetry.update();

        }


    }

}
