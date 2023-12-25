package com.example.antiaircraftgun;

import javafx.scene.paint.Color;

public class EntityAntiAircraftGun extends EntityArmoredCar{
    private final Color _dopColor;
    private final boolean _gun;
    private final boolean _radar;

    public Color GetDopColor()
    {
        return _dopColor;
    }

    public boolean GetGun()
    {
        return _gun;
    }

    public boolean GetRadar()
    {
        return _radar;
    }

    public EntityAntiAircraftGun(int speed, float weight, Color bodyColor, Color dopColor, boolean gun, boolean radar)
    {
        super(speed, weight, bodyColor);
        _dopColor = dopColor;
        _gun = gun;
        _radar = radar;
    }
}
