package org.firstinspires.ftc.teamcode;


import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;


@TeleOp(name="swerveAttempt2_0")
// @Disabled

public class swerveAttempt2_0 extends LinearOpMode
{


    @Override
    public void runOpMode() throws InterruptedException {

        swerveRobotHardware robot = new swerveRobotHardware(hardwareMap);


        waitForStart();

        while (opModeIsActive()) {

            robot.swerveDrive(gamepad1.left_stick_y, gamepad1.left_stick_x, gamepad1.right_stick_x, 1);

            telemetry.addData("turnPowerLeft", robot.turnPowerLeft);
            telemetry.addData("aTan degrees", robot.aTan);
            telemetry.addData("stick power", robot.power);
            telemetry.addData("three power", robot.right1.getPower());
            telemetry.addData("four power", robot.right2.getPower());

            //one +     two -
            telemetry.addData("delta1", robot.rightPodPosition);
            telemetry.addData("delta2", robot.leftPodPosition);
            telemetry.addData("current angle",robot.currentAngle);
            telemetry.addData("final angle",robot.finalAngle);
            telemetry.update();

        }
    }
}
