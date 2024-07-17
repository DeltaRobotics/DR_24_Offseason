package org.firstinspires.ftc.teamcode;


import com.acmerobotics.dashboard.FtcDashboard;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;


@TeleOp(name="sixLegWalker")
// @Disabled

public class sixLegWalker extends LinearOpMode
{


    public Servo LFH = null;
    public Servo LFV = null;
    public Servo LMH = null;
    public Servo LMV = null;
    public Servo LBH = null;
    public Servo LBV = null;
    public Servo RFH = null;
    public Servo RFV = null;
    public Servo RMH = null;
    public Servo RMV = null;
    public Servo RBH = null;
    public Servo RBV = null;





    boolean a = false;

    double LSV = 0;



    @Override
    public void runOpMode() throws InterruptedException {

        robotHardware robot = new robotHardware(hardwareMap);

        FtcDashboard dashboard = FtcDashboard.getInstance();
        telemetry = dashboard.getTelemetry();

        LFH= hardwareMap.servo.get("LFH");
        LFV= hardwareMap.servo.get("LFV");
        LMH= hardwareMap.servo.get("LMH");
        LMV= hardwareMap.servo.get("LMV");
        LBH= hardwareMap.servo.get("LBH");
        LBV= hardwareMap.servo.get("LBV");
        RFH= hardwareMap.servo.get("RFH");
        RFV= hardwareMap.servo.get("RFV");
        RMH= hardwareMap.servo.get("RMH");
        RMV= hardwareMap.servo.get("RMV");
        RBH= hardwareMap.servo.get("RBH");
        RBV= hardwareMap.servo.get("RBV");



        LFH.setPosition(.5);

        LFV.setPosition(.6);

        LMH.setPosition(.5);

        LMV.setPosition(.6);

        LBH.setPosition(.5);

        LBV.setPosition(.6);

        RFH.setPosition(.5);

        RFV.setPosition(.4);

        RMH.setPosition(.5);

        RMV.setPosition(.4);

        RBH.setPosition(.5);

        RBV.setPosition(.4);






        waitForStart();

        //.6LH makes the leg go backwards
        //.4V is down for walking
        while (opModeIsActive()) {

            if (gamepad1.a) {
                //lift up 3
                LFV.setPosition(.4);

                LMV.setPosition(.6);

                LBV.setPosition(.4);

                RFV.setPosition(.4);

                RMV.setPosition(.6);

                RBV.setPosition(.4);

                sleep(500);
                //3 forward
                RMH.setPosition(.6);

                LFH.setPosition(.4);

                LBH.setPosition(.4);

                sleep(500);

                RMV.setPosition(.4);

                LFV.setPosition(.6);

                LBV.setPosition(.6);

                sleep(500);

                LFH.setPosition(.5);

                LMH.setPosition(.6);

                LBH.setPosition(.5);

                RFH.setPosition(.4);

                RMH.setPosition(.5);

                RBH.setPosition(.4);


                a = false;
            }
            if (!gamepad1.a && !a) {
                a = true;

            }
            if(gamepad1.b) {

                LFH.setPosition(.5);

                LFV.setPosition(.5);

                LMH.setPosition(.5);

                LMV.setPosition(.5);

                LBH.setPosition(.5);

                LBV.setPosition(.5);

                RFH.setPosition(.5);

                RFV.setPosition(.5);

                RMH.setPosition(.5);

                RMV.setPosition(.5);

                RBH.setPosition(.5);

                RBV.setPosition(.5);
            }
            if(gamepad1.x) {

                LFV.setPosition(.4);

                LMV.setPosition(.6);

                LBV.setPosition(.4);

                RFV.setPosition(.4);

                RMV.setPosition(.6);

                RBV.setPosition(.4);

            }
            if(gamepad1.y) {
                LFH.setPosition(.6);

                LMH.setPosition(.6);

                LBH.setPosition(.6);

                RFH.setPosition(.4);

                RMH.setPosition(.4);

                RBH.setPosition(.4);


            }

            if(gamepad1.left_stick_x>0 || gamepad1.left_stick_x<0){
                LSV=gamepad1.left_stick_x * .2;
                LFH.setPosition(.5+LSV);

                LMH.setPosition(.5+LSV);

                LBH.setPosition(.5+LSV);

                RFH.setPosition(.5+LSV);

                RMH.setPosition(.5+LSV);

                RBH.setPosition(.5+LSV);




            }
            if(gamepad1.right_stick_y>0 || gamepad1.right_stick_y<0) {
                LSV = gamepad1.right_stick_y * .2;
                LFV.setPosition(.5 + LSV);

                LMV.setPosition(.5 + LSV);

                LBV.setPosition(.5 + LSV);

                RFV.setPosition(.5 - LSV);

                RMV.setPosition(.5 - LSV);

                RBV.setPosition(.5 - LSV);

            }
        }
    }
}
