package com.example.antiaircraftgun;

public enum NumberRollers
{
    Four,
    Five,
    Six;

    public static NumberRollers FromInteger(int intValue)
    {
        switch(intValue)
        {
            case 4:
                return Four;

            case 5:
                return Five;

            case 6:
                return Six;

            default:
                System.out.println("Error: incorrect value!");
                return Four;
        }
    }
}
