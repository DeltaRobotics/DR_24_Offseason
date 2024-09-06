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

@TeleOp(name="robotIn30Hours")
//@Disabled

public class robotIn30Hours extends LinearOpMode {


    public Servo wrist = null;
    public Servo clawL = null;
    public Servo clawR = null;

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
    //public boolean servoAdjust = true;

    //public double servoVariable = 0;
    //public double servoVariable2 = 0;
    //public double shoulderPos = 0.5;
    //public double shoulderTime = 0;
    //public double shoulderGo = 0;

    //public boolean stickDropping = false;

    //public boolean intakePos = true;
    //public boolean outputPos = false;

    public boolean rightBumper = true;
    public boolean leftBumper = true;

    public boolean leftTrigger = false;

    public double speed = 1;

    public double wristPos = .79;
    public double intakePos2 = .53;

    public boolean buttonB = true;
    public boolean buttonA = true;
    public boolean buttonC = true;
    public boolean openClose = true;
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
    public boolean stickDropping = false;

    public boolean intakePos = true;
    public boolean outputPos = false;

    public boolean slideToggle = true;

    public double oldOutTime = 0;
    public int newOutTime = 0;

    RevBlinkinLedDriver blinkinLedDriver;


    public void runOpMode() throws InterruptedException {

        robotHardware robot = new robotHardware(hardwareMap);

        wrist= hardwareMap.servo.get("wrist");
        clawL= hardwareMap.servo.get("clawL");
        clawR= hardwareMap.servo.get("clawR");

        slidesL = hardwareMap.dcMotor.get("slidesL");
        slidesR = hardwareMap.dcMotor.get("slidesR");

        blinkinLedDriver = hardwareMap.get(RevBlinkinLedDriver.class, "blinkin");

        slidesR.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        slidesL.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        slidesR.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        slidesL.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        slidesR.setDirection(DcMotorSimple.Direction.REVERSE);
        //slidesL.setDirection(DcMotorSimple.Direction.REVERSE);


        robot.resetDriveEncoders();

        ElapsedTime outputTime = new ElapsedTime();



        //0.95 for grabbing from intake

        while(!isStarted() && !isStopRequested()){


            wrist.setPosition(.5); //smaller numbers make the wrist go up
            clawL.setPosition(.5);
            clawR.setPosition(.4);// bigger number means it goes to the inside



            blinkinLedDriver.setPattern(RevBlinkinLedDriver.BlinkinPattern.RED_ORANGE);
        }

        while (opModeIsActive()) {

            robot.refresh(robot.odometers);

            robot.mecanumDrive(-gamepad1.left_stick_y, gamepad1.left_stick_x, -gamepad1.right_stick_x, speed);



            //telemetry.addData("x",robot.GlobalX);
            //telemetry.addData("y",robot.GlobalY);
            //telemetry.addData("heading",Math.toDegrees(robot.GlobalHeading));
            //telemetry.addData("",null);

            //if(gamepad1.dpad_up)
            //slideEncoder = 400;

            if (gamepad1.dpad_down && buttonA){
                buttonA = false;
                slideEncoder -= 200;
            }
            else if (gamepad1.dpad_up && buttonB){

                buttonB = false;
                slideEncoder += 200;
            }
            if (!gamepad1.dpad_down && !buttonA){
                buttonA = true;
            }
            else if (!gamepad1.dpad_up && !buttonB){
                buttonB = true;
            }
            if (gamepad1.right_trigger > .5 && buttonC){
                buttonC = false;
                if (openClose) {
                    openClose = false;
                    clawL.setPosition(.5);
                    clawR.setPosition(.4);// bigger number means it closes
                }
                else if (!openClose) {
                    openClose = true;
                    clawL.setPosition(.3);
                    clawR.setPosition(.6);// bigger number means it closes
                }

            }
            else if (gamepad1.right_trigger < .5 && !buttonC) {
                buttonC = true;
            }







            //2200 is the max for the slides
            slidesL.setTargetPosition(slideEncoder);
            slidesL.setPower(0.3);
            slidesL.setMode(DcMotor.RunMode.RUN_TO_POSITION);

            slidesR.setTargetPosition(slideEncoder);
            slidesR.setPower(0.3);
            slidesR.setMode(DcMotor.RunMode.RUN_TO_POSITION);

            telemetry.addData("slide encoder",slideEncoder);
            telemetry.update();
        }
    }
    public void stick_up(){}
    public void stick_mid(){}
    public void stick_bottom(){}
    public void stick_hopper(){}

















































}