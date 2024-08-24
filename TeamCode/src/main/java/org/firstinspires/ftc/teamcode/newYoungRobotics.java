package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.rev.RevBlinkinLedDriver;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.robotHardware;

@TeleOp(name="newYoungRobotics")
//@Disabled

public class newYoungRobotics extends LinearOpMode{

    public CRServo finger = null;
    public Servo shooterAngle = null;
    public Servo wrist = null;
    public Servo shoulderL = null;
    public Servo shoulderR = null;
    public Servo intakeServo = null;
    public Servo shooter = null;
    public Servo pixMover = null;
    public Servo pixScraper = null;

    public DcMotor intake = null;
    public DcMotor slidesL = null;
    public DcMotor slidesR = null;

    public boolean slidesRasied = false;
    public int stickPosition = 0;
    /*
     * 0 - hopper
     * 1 - top - bottom
     * 2 - top left - bottom right
     * 3 - top right - bottom left
     * 4 - first left - second right
     * 5 - first right - second left
     * */

    public int slideEncoder = 0;

    public boolean slidesUp = false;
    public boolean slidesDown = true;
    public boolean servoAdjust = true;

    public double servoVariable = 0;
    public double servoVariable2 = 0;
    public double shoulderPos = 0.5;
    public double shoulderTime = 0;
    public double shoulderGo = 0;

    public boolean stickDropping = false;

    public boolean intakePos = true;
    public boolean outputPos = false;

    public boolean rightBumper = true;
    public boolean leftBumper = true;

    public boolean leftTrigger = false;

    public double speed = .3;

    public double wristPos = .79;
    public double intakePos2 = .53;

    public boolean buttonB = true;
    public boolean buttonA = true;
    public boolean buttonX = true;
    public boolean buttonY = true;
    public boolean buttonRight = true;
    public boolean buttonLeft = true;
    public boolean backButton = true;
    public boolean buttonRight2 = true;
    public boolean buttonLeft2 = true;
    public boolean buttonB2 = true;
    public boolean buttonA2 = true;
    public boolean buttonX2 = true;
    public boolean buttonY2 = true;
    public boolean dpadleft = true;

    public boolean slideToggle = true;

    public double oldOutTime = 0;
    public int newOutTime = 0;

    RevBlinkinLedDriver blinkinLedDriver;


    public void runOpMode() throws InterruptedException {

        robotHardware robot = new robotHardware(hardwareMap);

        blinkinLedDriver = hardwareMap.get(RevBlinkinLedDriver.class, "blinkin");

        robot.resetDriveEncoders();

        ElapsedTime outputTime = new ElapsedTime();



        //0.95 for grabbing from intake

        while(!isStarted() && !isStopRequested()){



            blinkinLedDriver.setPattern(RevBlinkinLedDriver.BlinkinPattern.RED_ORANGE);
        }

        while (opModeIsActive()) {

            robot.refresh(robot.odometers);

            robot.mecanumDrive(gamepad1.left_stick_y, -gamepad1.left_stick_x, -gamepad1.right_stick_x, speed);



            telemetry.addData("x",robot.GlobalX);
            telemetry.addData("y",robot.GlobalY);
            telemetry.addData("heading",Math.toDegrees(robot.GlobalHeading));
            telemetry.addData("",null);
        }
    }
    public void stick_up(){}
    public void stick_mid(){}
    public void stick_bottom(){}
    public void stick_hopper(){}
}