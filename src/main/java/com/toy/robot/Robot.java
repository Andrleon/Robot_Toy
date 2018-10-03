package com.toy.robot;

import java.util.*;

import static java.lang.Math.abs;

public class Robot {

    private final static byte TABLE_WIDTH = 5;
    private final static byte TABLE_LENGTH = 5;
    private final static String placeCommandChecker = "(PLACE)\\s[0-" + (TABLE_LENGTH - 1) + "],[0-" + (TABLE_WIDTH -1) + "],(?:NORTH|WEST|EAST|SOUTH)";

    private int positionX;
    private int positionY;
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
        System.out.println(positionX + ", " + positionY + ", " + currentDirection);
    }

    private void place(String command){
        if (!robotIsOnTable){
            robotIsOnTable = true;
        }
        positionX = Character.getNumericValue(command.charAt(6));
        positionY = Character.getNumericValue(command.charAt(8));
        currentDirection = command.substring(10);
    }

    public void startWorking(){
        Scanner scanner = new Scanner(System.in);
        String command = "";

        while (true){
            System.out.println("Enter your command");
            command = scanner.nextLine().toUpperCase();
            if (command.matches(placeCommandChecker)){
                place(command);
            }
            else if (robotIsOnTable){
                switch (command) {
                    case "LEFT":
                        turn(command);
                        break;
                    case "RIGHT":
                        turn(command);
                        break;
                    case "MOVE":
                        move();
                        break;
                    case "REPORT":
                        report();
                        break;
                    default:
                        System.out.println("Incorrect command.");
                }
            }
            else System.out.println("Firs command should be \"PLACE...\" "  );
        }
    }
}

