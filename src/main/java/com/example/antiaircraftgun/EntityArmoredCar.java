package com.example.antiaircraftgun;

import javafx.scene.paint.Color;
import java.util.Random;

public class EntityArmoredCar
{
    private final int _speed;
    private final float _weight;
    private final Color _bodyColor;
    public int GetSpeed()
    {
        return _speed;
    }

    public float GetWeight()
    {
        return _weight;
    }

    public Color GetBodyColor()
    {
        return _bodyColor;
    }

    public float GetStep()
    {
        return _speed * 100 / _weight;
    }

    public EntityArmoredCar(int speed, float weight, Color bodyColor)
    {
        Random random = new Random();
        _speed = speed <= 0 ? random.nextInt(100) + 50 : speed;
        _weight = weight <= 0 ? random.nextInt(30) + 40 : weight;
        _bodyColor = bodyColor;
    }
}
