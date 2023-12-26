package com.example.antiaircraftgun;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

// Класс для отрисовки гусениц бронетранспорта
public class DrawingRollers {
    // Цвет гусениц
    private Color _RollersColor;
    // Количество роликов
    private NumberRollers _numRollers;

    // Метод для установки количества роликов
    public void SetNumberRollers(int numberRollers) {
        _numRollers = NumberRollers.FromInteger(numberRollers);
    }

    // Метод для инициализации цвета гусениц
    public void Init(Color trackRollersColor) {
        _RollersColor = trackRollersColor;
    }

    // Метод для отрисовки гусениц
    public void DrawRollers(GraphicsContext gc, float startPosX, float startPosY) {
        // Проверка на наличие цвета гусениц
        if (_RollersColor == null) {
            return;
        }

        // Установка цвета гусениц
        gc.setFill(_RollersColor);

        // В зависимости от количества роликов рисуем соответствующее количество
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

    // Метод для отрисовки первого ролика
    void DrawRoller1(GraphicsContext gc, float startPosX, float startPosY) {
        gc.setFill(_RollersColor);
        gc.fillOval(startPosX + 1, startPosY + 21, 18, 18);
        gc.fillOval(startPosX + 69, startPosY + 21, 18, 18);
        gc.fillOval(startPosX + 19, startPosY + 30, 11, 11);
        gc.fillOval(startPosX + 58, startPosY + 30, 11, 11);
    }

    // Метод для отрисовки второго ролика
    void DrawRoller2(GraphicsContext gc, float startPosX, float startPosY) {
        gc.setFill(_RollersColor);
        gc.fillOval(startPosX + 45, startPosY + 30, 11, 11);
    }

    // Метод для отрисовки третьего ролика
    void DrawRoller3(GraphicsContext gc, float startPosX, float startPosY) {
        gc.setFill(_RollersColor);
        gc.fillOval(startPosX + 32, startPosY + 30, 11, 11);
    }
}


