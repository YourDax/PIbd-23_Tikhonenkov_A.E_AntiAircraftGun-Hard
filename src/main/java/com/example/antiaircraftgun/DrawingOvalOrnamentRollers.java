package com.example.antiaircraftgun;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class DrawingOvalOrnamentRollers implements IDrawingAdditionalElement{
    private Color _rollersColor;
    private NumberRollers _numOfRollers;

    public void SetNumberRollers(int numberOfRollers)
    {
        _numOfRollers = NumberRollers.FromInteger(numberOfRollers);
    }

    public DrawingOvalOrnamentRollers(Color trackRollersColor)
    {
        _rollersColor = trackRollersColor;
    }

    public void DrawRollers(GraphicsContext gc, float startPosX, float startPosY)
    {
        if (_rollersColor == null)
        {
            return;
        }

        gc.setFill(_rollersColor);
        gc.fillOval(startPosX +1, startPosY + 21, 18, 18);
        gc.fillOval(startPosX + 69, startPosY + 21, 18, 18);
        gc.fillOval(startPosX + 19, startPosY + 30, 11, 11);
        gc.fillOval(startPosX + 58, startPosY + 30, 11, 11);

        // Орнамент
        gc.strokeOval(startPosX + 5, startPosY + 25, 10, 10);
        gc.strokeOval(startPosX + 73, startPosY + 25, 10, 10);
        gc.strokeOval(startPosX + 22, startPosY + 33, 5, 5);
        gc.strokeOval(startPosX + 61, startPosY + 33, 5, 5);

        switch (_numOfRollers)
        {
            case Four:
                break;

            case Five:
                gc.setFill(_rollersColor);
                gc.fillOval(startPosX + 45, startPosY + 30, 11, 11);
                // Орнамент
                gc.strokeOval(startPosX + 48, startPosY + 33, 5, 5);
                break;

            case Six:
                gc.setFill(_rollersColor);
                gc.fillOval(startPosX + 45, startPosY + 30, 11, 11);
                gc.fillOval(startPosX + 32, startPosY + 30, 11, 11);
                // Орнамент
                gc.strokeOval(startPosX + 48, startPosY + 33, 5, 5);
                gc.strokeOval(startPosX + 35, startPosY + 33, 5, 5);
                break;
        }
    }
}
