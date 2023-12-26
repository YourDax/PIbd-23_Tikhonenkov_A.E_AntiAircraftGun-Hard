package com.example.antiaircraftgun;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class DrawingArmoredCar
{
    // Экземпляр класса бронетранспорта
    private EntityArmoredCar _armoredCar;
    // Экземпляр класса для отрисовки гусениц
    private DrawingRollers _drawingRollers;
    // Начальная позиция по X
    private float _startPosX;
    // Начальная позиция по Y
    private float _startPosY;
    // Ширина изображения
    private Integer _pictureWidth;
    // Высота изображения
    private Integer _pictureHeight;
    // Ширина бронетранспорта
    private int _armoredCarWidth = 90;
    // Высота бронетранспорта
    private int _armoredCarHeight = 40;

    // Метод для получения объекта бронетранспорта
    public EntityArmoredCar GetArmoredCar()
    {
        return _armoredCar;
    }

    // Метод для получения объекта для отрисовки гусениц
    public DrawingRollers GetDrawningRollers()
    {
        return _drawingRollers;
    }

    // Метод для инициализации бронетранспорта
    public void Init(int speed, float weight, Color bodyColor)
    {
        // Создание экземпляра бронетранспорта и его инициализация
        _armoredCar = new EntityArmoredCar();
        _armoredCar.Init(speed, weight, bodyColor);

        // Создание экземпляра для отрисовки гусениц и его инициализация
        _drawingRollers = new DrawingRollers();
        _drawingRollers.Init(bodyColor);
    }

    // Метод для установки начальной позиции и размеров изображения
    public void SetPosition(int x, int y, int width, int height)
    {
        // Проверка на отрицательные координаты
        if (x < 0 || y < 0) {
            return;
        }

        // Проверка, чтобы бронетранспорт не выходил за границы изображения
        if (x + _armoredCarWidth > width || y + _armoredCarHeight > height) {
            return;
        }

        // Установка начальных позиций и размеров изображения
        _startPosX = x;
        _startPosY = y;

        _pictureWidth = width;
        _pictureHeight = height;
    }

    // Метод для перемещения бронетранспорта в указанном направлении
    public void MoveTransport(Direction direction)
    {
        // Проверка на наличие размеров изображения
        if (_pictureWidth == null || _pictureHeight == null) {
            return;
        }

        // Перемещение в зависимости от указанного направления
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

    // Метод для отрисовки бронетранспорта
    public void DrawTransport(GraphicsContext gc)
    {
        // Проверка на некорректные координаты или отсутствие размеров изображения
        if (_startPosX < 0 || _startPosY < 0 || _pictureWidth == null || _pictureHeight == null) {
            return;
        }

        // Отрисовка гусениц
        gc.setFill(Color.GRAY);
        gc.fillRect(_startPosX + 10, _startPosY + 20, 70, 20);
        gc.fillOval(_startPosX, _startPosY + 20, 20, 20);
        gc.fillOval(_startPosX + 70, _startPosY + 20, 20, 20);
        _drawingRollers.DrawRollers(gc, _startPosX, _startPosY);

        // Отрисовка брони
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

    // Метод для изменения границ изображения
    public void ChangeBorders(int width, int height)
    {
        // Обновление размеров изображения
        _pictureWidth = width;
        _pictureHeight = height;

        // Проверка, чтобы размеры изображения не были меньше размеров бронетранспорта
        if (_pictureWidth <= _armoredCarWidth || _pictureHeight <= _armoredCarHeight) {
            _pictureWidth = null;
            _pictureHeight = null;
            return;
        }

        // Проверка, чтобы бронетранспорт не выходил за границы изображения
        if (_startPosX + _armoredCarWidth > _pictureWidth) {
            _startPosX = _pictureWidth - _armoredCarWidth;
        }

        if (_startPosY + _armoredCarHeight > _pictureHeight) {
            _startPosY = _pictureHeight - _armoredCarHeight;
        }
    }
}
