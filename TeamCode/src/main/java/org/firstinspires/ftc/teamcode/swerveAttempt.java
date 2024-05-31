package org.firstinspires.ftc.teamcode;


import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.Range;


@TeleOp(name="swerveAttempt")
 @Disabled

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
    double turnEncoder = 0;
    double turnPower = 0;
    double currentAngle = 180;
    double newAngle = 0;
    double rotations = 0;
    double distance = 0;
    double opposite = 0;
    double oppositedistance = 0;
    double finalAngle = 0;
    int wheelDirection = 0;
    double encoderTicksPerDegree = 6.40333;

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

            power = Math.abs(gamepad1.left_stick_y) + Math.abs(gamepad1.left_stick_x);

            aTan = Math.toDegrees(-Math.atan2(gamepad1.left_stick_x, -gamepad1.left_stick_y)) + 180;

            if (gamepad1.left_stick_x + gamepad1.left_stick_y == 0){
                aTan = 180;
            }


            //start of complex things
            currentAngle = rightPodPosition/encoderTicksPerDegree;
            newAngle = aTan;

            rotations = Math.floor(currentAngle/360);
            newAngle = newAngle + 360 * rotations;
            opposite = newAngle + 188;

            if(currentAngle < 0 && newAngle > 0){ // normal - dealer
                distance = newAngle - currentAngle;
            } else if (currentAngle > 0 && newAngle < 0){
                distance = currentAngle - newAngle;
            } else {
                distance = Math.abs(Math.abs(currentAngle) - Math.abs(newAngle));
            }

            if(currentAngle < 0 && opposite > 0) { //opo - dealer
                oppositedistance = opposite - currentAngle;
            } else if (currentAngle > 0 && opposite < 0){
                oppositedistance = currentAngle - opposite;
            } else {
                oppositedistance = Math.abs(Math.abs(currentAngle) - Math.abs(opposite));
            }

            //decide what way is shorter. for example if currentAngle is 350 and new =Angle is 370 then back to 10

            if (distance > Math.abs(Math.abs(currentAngle) - Math.abs(newAngle + 360 ))) {
                newAngle = newAngle + 360;
                if(currentAngle < 0 && newAngle > 0){ // normal - dealer
                    distance = newAngle - currentAngle;
                } else if (currentAngle > 0 && newAngle < 0){
                    distance = currentAngle - newAngle;
                } else {
                    distance = Math.abs(Math.abs(currentAngle) - Math.abs(newAngle));
                }
            }
            else if (distance > Math.abs(Math.abs(currentAngle) - Math.abs(newAngle - 360))) {
                newAngle = newAngle - 360;
                if(currentAngle < 0 && newAngle > 0){ // normal - dealer
                    distance = newAngle - currentAngle;
                } else if (currentAngle > 0 && newAngle < 0){
                    distance = currentAngle - newAngle;
                } else {
                    distance = Math.abs(Math.abs(currentAngle) - Math.abs(newAngle));
                }
            }
            else {
                distance = Math.abs(Math.abs(currentAngle) - Math.abs(newAngle));
            }

            if(oppositedistance > Math.abs((opposite + 360) - Math.abs(currentAngle))) {//does the same for the opposite
                opposite += 360;
                if(currentAngle < 0 && opposite > 0) { //opo - dealer
                    oppositedistance = opposite - currentAngle;
                } else if (currentAngle > 0 && opposite < 0){
                    oppositedistance = currentAngle - opposite;
                } else {
                    oppositedistance = Math.abs(Math.abs(currentAngle) - Math.abs(opposite));
                }
            }
            else if (oppositedistance > Math.abs((opposite - 360) - Math.abs(currentAngle))){
                opposite -= 360;
                if(currentAngle < 0 && opposite > 0) { //opo - dealer
                    oppositedistance = opposite - currentAngle;
                } else if (currentAngle > 0 && opposite < 0){
                    oppositedistance = currentAngle - opposite;
                } else {
                    oppositedistance = Math.abs(Math.abs(currentAngle) - Math.abs(opposite));
                }
            }

            if(oppositedistance < distance){
                finalAngle = opposite;
                wheelDirection = -1;
            } else {
                finalAngle = newAngle;
                wheelDirection = 1;
            }
            //end of complex things



            if(Math.abs(gamepad1.left_stick_x) > 0.1){
                turnEncoder = gamepad1.right_stick_x * 45 * encoderTicksPerDegree;
                turnPower = 0;
            }else{
                turnEncoder=0;
                turnPower=gamepad1.right_stick_x;
            }

            turnPowerRight = robot.odoPID(encoderTicksPerDegree *  finalAngle - turnEncoder, rightPodPosition);
            turnPowerLeft = robot.odoPID(encoderTicksPerDegree *  finalAngle + turnEncoder, leftPodPosition);

            right1.setPower(wheelDirection * power + turnPowerRight - turnPower * wheelDirection);
            right2.setPower(wheelDirection * -power + turnPowerRight + turnPower * wheelDirection);
            left1.setPower(wheelDirection * power + turnPowerLeft + turnPower * wheelDirection);
            left2.setPower(wheelDirection * -power + turnPowerLeft - turnPower * wheelDirection);


            telemetry.addData("turnPowerLeft", turnPowerLeft);
            telemetry.addData("aTan degrees", aTan);
            telemetry.addData("stick power", power);
            telemetry.addData("three power", right1.getPower());
            telemetry.addData("four power", right2.getPower());

            //one +     two -
            telemetry.addData("delta1", rightPodPosition);
            telemetry.addData("delta2", leftPodPosition);
            telemetry.addData("real angle",(rightPodPosition / encoderTicksPerDegree));
            telemetry.update();

        }
    }
}
