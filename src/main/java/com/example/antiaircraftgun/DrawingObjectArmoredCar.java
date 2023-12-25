package com.example.antiaircraftgun;

import javafx.scene.canvas.GraphicsContext;

public class DrawingObjectArmoredCar implements IDrawingObject{
    private DrawingArmoredCar _armoredCar = null;

    public DrawingObjectArmoredCar(DrawingArmoredCar armoredCar)
    {
        _armoredCar = armoredCar;
    }

    public float GetStep()
    {
        if (_armoredCar != null && _armoredCar.GetArmoredCar() != null)
        {
            return _armoredCar.GetArmoredCar().GetStep();
        }
        return 0F;
    }

    public float[] GetCurrentPosition()
    {
        if (_armoredCar != null)
        {
            return _armoredCar.GetCurrentPosition();
        }
        return new float[4];
    }

    public void MoveObject(Direction direction)
    {
        _armoredCar.MoveTransport(direction);
    }

    public void SetObject(int x, int y, int width, int height)
    {
        _armoredCar.SetPosition(x, y, width, height);
    }

    public void DrawingObject(GraphicsContext gc)
    {
        if (_armoredCar != null)
        {
            _armoredCar.DrawTransport(gc);
        }
    }
}
