package com.example.antiaircraftgun;

public enum Direction {
    None,
    Up,
    Down,
    Left,
    Right;

    public static Direction FromInteger(int intValue)
    {
        switch(intValue)
        {
            case 0:
                return None;
            case 1:
                return Up;
            case 2:
                return Down;
            case 3:
                return Left;
            case 4:
                return Right;
            default:
                System.out.println("Error: incorrect value!");
                return Up;
        }
    }
}

