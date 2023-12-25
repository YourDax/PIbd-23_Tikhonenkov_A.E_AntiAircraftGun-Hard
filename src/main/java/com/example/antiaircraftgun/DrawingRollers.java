package com.example.antiaircraftgun;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class DrawingRollers implements IDrawingAdditionalElement{
    private Color _RollersColor;
    private NumberRollers _numRollers;
    public void SetNumberRollers(int numberRollers)
    {
        _numRollers = NumberRollers.FromInteger(numberRollers);
    }
    public DrawingRollers(Color rollersColor)
    {
        _RollersColor = rollersColor;
    }

    public void DrawRollers(GraphicsContext gc, float startPosX, float startPosY) {
        if (_RollersColor == null) {
            return;
        }

        gc.setFill(_RollersColor);

        switch (_numRollers) {
            case Four:
                DrawRoller1(gc, startPosX, startPosY);
                break;

            case Five:
                DrawRoller1(gc, startPosX, startPosY);
                DrawRoller2(gc, startPosX, startPosY);
                break;

            case Six:
                DrawRoller1(gc, startPosX, startPosY);
                DrawRoller2(gc, startPosX, startPosY);
                DrawRoller3(gc, startPosX, startPosY);
                break;
        }
    }
    void DrawRoller1(GraphicsContext gc, float startPosX, float startPosY) {
        gc.setFill(_RollersColor);
        gc.fillOval(startPosX +1, startPosY + 21, 18, 18);
        gc.fillOval(startPosX + 69, startPosY + 21, 18, 18);
        gc.fillOval(startPosX + 19, startPosY + 30, 11, 11);
        gc.fillOval(startPosX + 58, startPosY + 30, 11, 11);
    }
    void DrawRoller2(GraphicsContext gc, float startPosX, float startPosY) {
        gc.setFill(_RollersColor);
        gc.fillOval(startPosX + 45, startPosY + 30, 11, 11);
    }
    void DrawRoller3(GraphicsContext gc, float startPosX, float startPosY) {
        gc.setFill(_RollersColor);
        gc.fillOval(startPosX + 32, startPosY + 30, 11, 11);
    }
}

