package com.toy.robot;

import java.util.*;

import static java.lang.Math.abs;

public class Robot {

    private final static byte TABLE_WIDTH = 5;
    private final static byte TABLE_LENGTH = 5;
    private final static String placeCommandChecker = "(PLACE)\\s[0-" + (TABLE_LENGTH - 1) + "],[0-" + (TABLE_WIDTH -1) + "],(?:NORTH|WEST|EAST|SOUTH)";

    private int positionX;
    private int positionY;
    private String reportMessage;
    private boolean robotIsOnTable;
    private String currentDirection;
    private static final LinkedList<String> DIRECTIONS = new LinkedList<>();
    private static final Map<String, int[]> DIRECTION_MAPPING = new HashMap<>();

    static {
        DIRECTIONS.add("WEST");
        DIRECTIONS.add("NORTH");
        DIRECTIONS.add("EAST");
        DIRECTIONS.add("SOUTH");
        DIRECTION_MAPPING.put("WEST", new int[]{-1,0});
        DIRECTION_MAPPING.put("NORTH", new int[]{0,1});
        DIRECTION_MAPPING.put("EAST", new int[]{1,0});
        DIRECTION_MAPPING.put("SOUTH", new int[]{0,-1});
    }

    private void move(){
        int nextPositionX = positionX + DIRECTION_MAPPING.get(currentDirection)[0];
        int nextPositionY = positionY + DIRECTION_MAPPING.get(currentDirection)[1];
        positionX = (nextPositionX >= 0 && nextPositionX <= TABLE_WIDTH -1) ? nextPositionX : positionX;
        positionY = (nextPositionY >= 0 && nextPositionY <= TABLE_LENGTH -1) ? nextPositionY : positionY;
    }

    private void turn(String direction){
        int indexOfCurrentDirection = DIRECTIONS.indexOf(currentDirection);
        if (direction.equals("LEFT"))
            currentDirection = (indexOfCurrentDirection == 0) ? DIRECTIONS.getLast() : DIRECTIONS.get(indexOfCurrentDirection - 1);
        if (direction.equals("RIGHT"))
        currentDirection = (indexOfCurrentDirection == DIRECTIONS.size() - 1) ? DIRECTIONS.getFirst() : DIRECTIONS.get(indexOfCurrentDirection + 1);
    }

    private void report() {
        reportMessage =  positionX + ", " + positionY + ", " + currentDirection;
        System.out.println(reportMessage);
    }

    private void place(String command){
        if (!robotIsOnTable){
            robotIsOnTable = true;
        }
        positionX = Character.getNumericValue(command.charAt(6));
        positionY = Character.getNumericValue(command.charAt(8));
        currentDirection = command.substring(10);
        System.out.println("Done");
    }

    public void executeCommand(String command){
        if (command.matches(placeCommandChecker)){
            place(command);
        }
        else if (robotIsOnTable){
            switch (command) {
                case "LEFT":
                    turn(command);
                    System.out.println("Done");
                    break;
                case "RIGHT":
                    turn(command);
                    System.out.println("Done");
                    break;
                case "MOVE":
                    move();
                    System.out.println("Done");
                    break;
                case "REPORT":
                    report();
                    break;
                default:
                    System.out.println("Invalid command. \nValid commands are: PLACE, MOVE, LEFT, RIGHT, REPORT and EXIT");
            }
        }
        else System.out.println("Firs command should be \"PLACE...\" "  );
    }

    public void startWorking(){
        Scanner scanner = new Scanner(System.in);
        String command;

        System.out.println("--------------------------------------------------------------------------------------------------------------");
        System.out.println("Toy robot simulator is started.");
        System.out.println("Please use the following commands to control the robot: ");
        System.out.println("-- \"PLACE X,Y,F\" - will put the toy robot on the table in position X,Y and facing NORTH, SOUTH, EAST or WEST.");
        System.out.println("    This is the first valid command. After that, any sequence of commands may be issued, in any order, including another PLACE command.");
        System.out.println("    Example:  PLACE 0,0,NORTH");
        System.out.println("-- \"LEFT\" and \"RIGHT\" - will rotate the robot 90 degrees in the specified direction without changing the position of the robot.");
        System.out.println("-- \"MOVE\" - will move the toy robot one unit forward in the direction it is currently facing.");
        System.out.println("-- \"REPORT\" - will announce the X,Y and F of the robot. This can be in any form, but standard output is sufficient.");
        System.out.println("-- \"EXIT\" - will turn off the robot.");
        System.out.println("--------------------------------------------------------------------------------------------------------------");

        while (true){
            System.out.println("Please enter your command");
            command = scanner.nextLine().toUpperCase();
            if (command.equals("EXIT"))
                    break;
            executeCommand(command);
        }
    }

    public String getReportMessage(){
        return reportMessage;
    }
}

