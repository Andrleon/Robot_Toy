package com.toy.robot;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class TestRobot {

    Robot testRobot = new Robot();
    String actualResult;

    @Test
    public void testPlace(){
        testRobot.executeCommand("PLACE 0,0,NORTH");
        testRobot.executeCommand("REPORT");
        actualResult = testRobot.getReportMessage();
        assertEquals("0, 0, NORTH", actualResult);

        testRobot.executeCommand("PLACE 4,4,EAST");
        testRobot.executeCommand("REPORT");
        actualResult = testRobot.getReportMessage();
        assertEquals("4, 4, EAST", actualResult);

        testRobot.executeCommand("PLACE 42,8,EAST");
        testRobot.executeCommand("REPORT");
        actualResult = testRobot.getReportMessage();
        assertEquals("4, 4, EAST", actualResult);

    }

    @Test
    public void testFullTurnLeft(){
        testRobot.executeCommand("PLACE 3,2,SOUTH");

        testRobot.executeCommand("LEFT");
        testRobot.executeCommand("REPORT");
        actualResult = testRobot.getReportMessage();
        assertEquals("3, 2, EAST", actualResult);

        testRobot.executeCommand("LEFT");
        testRobot.executeCommand("REPORT");
        actualResult = testRobot.getReportMessage();
        assertEquals("3, 2, NORTH", actualResult);

        testRobot.executeCommand("LEFT");
        testRobot.executeCommand("REPORT");
        actualResult = testRobot.getReportMessage();
        assertEquals("3, 2, WEST", actualResult);

        testRobot.executeCommand("LEFT");
        testRobot.executeCommand("REPORT");
        actualResult = testRobot.getReportMessage();
        assertEquals("3, 2, SOUTH", actualResult);

    }

    @Test
    public void testFullTurnRight(){
        testRobot.executeCommand("PLACE 4,4,WEST");

        testRobot.executeCommand("RIGHT");
        testRobot.executeCommand("REPORT");
        actualResult = testRobot.getReportMessage();
        assertEquals("4, 4, NORTH", actualResult);

        testRobot.executeCommand("RIGHT");
        testRobot.executeCommand("REPORT");
        actualResult = testRobot.getReportMessage();
        assertEquals("4, 4, EAST", actualResult);

        testRobot.executeCommand("RIGHT");
        testRobot.executeCommand("REPORT");
        actualResult = testRobot.getReportMessage();
        assertEquals("4, 4, SOUTH", actualResult);

        testRobot.executeCommand("RIGHT");
        testRobot.executeCommand("REPORT");
        actualResult = testRobot.getReportMessage();
        assertEquals("4, 4, WEST", actualResult);

    }

    @Test
    public void testMoveAroundPerimeter(){
        testRobot.executeCommand("PLACE 0,0,NORTH");
        repeateCommandNTimes("MOVE", 4);
        testRobot.executeCommand("REPORT");
        actualResult = testRobot.getReportMessage();
        assertEquals("0, 4, NORTH", actualResult);

        testRobot.executeCommand("RIGHT");
        repeateCommandNTimes("MOVE", 4);
        testRobot.executeCommand("REPORT");
        actualResult = testRobot.getReportMessage();
        assertEquals("4, 4, EAST", actualResult);
        testRobot.executeCommand("RIGHT");

        repeateCommandNTimes("MOVE", 4);
        testRobot.executeCommand("REPORT");
        actualResult = testRobot.getReportMessage();
        assertEquals("4, 0, SOUTH", actualResult);

        testRobot.executeCommand("RIGHT");
        repeateCommandNTimes("MOVE", 4);
        testRobot.executeCommand("REPORT");
        actualResult = testRobot.getReportMessage();
        assertEquals("0, 0, WEST", actualResult);

    }

    @Test
    public void testFallProtectionX(){
        testRobot.executeCommand("PLACE 0,0,EAST");
        repeateCommandNTimes("MOVE", 10);
        testRobot.executeCommand("REPORT");
        actualResult = testRobot.getReportMessage();
        assertEquals("4, 0, EAST", actualResult);

        repeateCommandNTimes("RIGHT", 2);
        repeateCommandNTimes("MOVE", 10);
        testRobot.executeCommand("REPORT");
        actualResult = testRobot.getReportMessage();
        assertEquals("0, 0, WEST", actualResult);

    }


    @Test
    public void testFallProtectionY(){
        testRobot.executeCommand("PLACE 2,2,NORTH");
        repeateCommandNTimes("MOVE", 10);
        testRobot.executeCommand("REPORT");
        actualResult = testRobot.getReportMessage();
        assertEquals("2, 4, NORTH", actualResult);

        repeateCommandNTimes("RIGHT", 2);
        repeateCommandNTimes("MOVE", 10);
        testRobot.executeCommand("REPORT");
        actualResult = testRobot.getReportMessage();
        assertEquals("2, 0, SOUTH", actualResult);

    }



    private void repeateCommandNTimes(String command, int nTimes){
        for (int i = 0; i < nTimes; i++){
            testRobot.executeCommand(command);
        }

    }


}
