package com.example.antiaircraftgun;

import javafx.scene.paint.Color;
import java.util.Random;

// Класс, представляющий бронетранспортное средство
public class EntityArmoredCar {
    // Скорость бронетранспорта
    private int _speed;
    // Вес бронетранспорта
    private float _weight;
    // Цвет брони бронетранспорта
    private Color _bodyColor;

    // Метод для получения скорости бронетранспорта
    public int GetSpeed() {
        return _speed;
    }

    // Метод для получения веса бронетранспорта
    public float GetWeight() {
        return _weight;
    }

    // Метод для получения цвета брони бронетранспорта
    public Color GetBodyColor() {
        return _bodyColor;
    }

    // Метод для вычисления шага бронетранспорта (скорость / вес)
    public float GetStep() {
        return _speed * 100 / _weight;
    }

    // Метод для инициализации параметров бронетранспорта
    public void Init(int speed, float weight, Color bodyColor) {
        // Инициализация случайными значениями, если параметры не переданы или некорректны
        Random random = new Random();
        _speed = speed <= 0 ? random.nextInt(100) + 50 : speed;
        _weight = weight <= 0 ? random.nextInt(30) + 40 : weight;
        _bodyColor = bodyColor;
    }
}

