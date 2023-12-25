package com.example.antiaircraftgun;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class DrawingArmoredCar
{
    private EntityArmoredCar _armoredCar;
    private IDrawingAdditionalElement _drawingRollers;
    protected float _startPosX;
    protected float _startPosY;
    private Integer _pictureWidth;
    private Integer _pictureHeight;
    private int _armoredCarWidth = 90;
    private int _armoredCarHeight = 40;

    public EntityArmoredCar GetArmoredCar()
    {
        return _armoredCar;
    }
    protected void SetArmoredVehicle(EntityArmoredCar armoredCar)
    {
        _armoredCar = armoredCar;
    }
    public IDrawingAdditionalElement GetDrawingRollers()
    {
        return _drawingRollers;
    }
    public void SetDrawingRollers(IDrawingAdditionalElement drawingRollers)
    {
        _drawingRollers = drawingRollers;
    }
    public DrawingArmoredCar(int speed, float weight, Color bodyColor)
    {
        _armoredCar = new EntityArmoredCar(speed, weight, bodyColor);
        _drawingRollers = new DrawingRollers(bodyColor);
    }
    protected DrawingArmoredCar(int speed, float weight, Color bodyColor, int armoredCarWidth, int armoredCarHeight)
    {
        this(speed, weight, bodyColor);
        _armoredCarHeight = armoredCarHeight;
        _armoredCarWidth = armoredCarWidth;
    }
    public void SetPosition(int x, int y, int width, int height)
    {
        if (x < 0 || y < 0) {
            return;
        }

        if (x + _armoredCarWidth > width || y + _armoredCarHeight > height) {
            return;
        }

        _startPosX = x;
        _startPosY = y;

        _pictureWidth = width;
        _pictureHeight = height;
    }

    public void MoveTransport(Direction direction)
    {
        if (_pictureWidth == null || _pictureHeight == null) {
            return;
        }

        switch (direction) {
            case Up:
                if (_startPosY - _armoredCar.GetStep() > 0) {
                    _startPosY -= _armoredCar.GetStep();
                }
                break;

            case Down:
                if (_startPosY + _armoredCarHeight + _armoredCar.GetStep() < _pictureHeight) {
                    _startPosY += _armoredCar.GetStep();
                }
                break;

            case Left:
                if (_startPosX - _armoredCar.GetStep() > 0) {
                    _startPosX -= _armoredCar.GetStep();
                }
                break;

            case Right:
                if (_startPosX + _armoredCarWidth + _armoredCar.GetStep() < _pictureWidth) {
                    _startPosX += _armoredCar.GetStep();
                }
                break;

            default:
                break;
        }
    }

    public void DrawTransport(GraphicsContext gc)
    {
        if (_startPosX < 0 || _startPosY < 0 || _pictureWidth == null || _pictureHeight == null) {
            return;
        }

        // Гусеницы
        gc.setFill(Color.GRAY);
        gc.fillRect(_startPosX + 10, _startPosY + 20, 70, 20);
        gc.fillOval(_startPosX, _startPosY + 20, 20, 20);
        gc.fillOval(_startPosX + 70, _startPosY + 20, 20, 20);
        _drawingRollers.DrawRollers(gc, _startPosX, _startPosY);

        // Броня
        gc.setStroke(Color.BLACK);
        gc.setLineWidth(2);
        gc.setFill(_armoredCar.GetBodyColor());
        gc.fillRect(_startPosX + 25, _startPosY, 40, 10);
        gc.fillRect(_startPosX + 5, _startPosY + 10, 80, 10);
        gc.strokeRect(_startPosX + 25, _startPosY, 40, 10);
        gc.strokeRect(_startPosX + 5, _startPosY + 10, 80, 10);
        gc.fillOval(_startPosX + 27, _startPosY + 16, 8, 8);
        gc.fillOval(_startPosX + 40, _startPosY + 16, 8, 8);
        gc.fillOval(_startPosX + 53, _startPosY + 16, 8, 8);
    }

    public void ChangeBorders(int width, int height)
    {
        _pictureWidth = width;
        _pictureHeight = height;

        if (_pictureWidth <= _armoredCarWidth || _pictureHeight <= _armoredCarHeight) {
            _pictureWidth = null;
            _pictureHeight = null;
            return;
        }

        if (_startPosX + _armoredCarWidth > _pictureWidth) {
            _startPosX = _pictureWidth - _armoredCarWidth;
        }

        if (_startPosY + _armoredCarHeight > _pictureHeight) {
            _startPosY = _pictureHeight - _armoredCarHeight;
        }
    }
    public float[] GetCurrentPosition()
    {
        float[] position = new float[4];
        position[0] = _startPosX;
        position[1] = _startPosY;
        position[2] = _startPosX + _armoredCarWidth;
        position[3] = _startPosY + _armoredCarHeight;
        return position;
    }
}
