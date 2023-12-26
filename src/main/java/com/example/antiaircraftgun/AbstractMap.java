package com.example.antiaircraftgun;

import javafx.scene.canvas.GraphicsContext;

import java.util.Random;

public abstract class AbstractMap {
    // Интерфейс для рисования объекта
    private IDrawingObject _drawingObject = null;
    // Контекст графики для отрисовки карты
    private GraphicsContext _graphicsContext = null;
    // Двумерный массив, представляющий карту
    protected int[][] _map = null;
    // Ширина и высота карты
    protected int _width;
    protected int _height;
    // Размер ячейки по горизонтали и вертикали
    protected float _size_x;
    protected float _size_y;
    // Генератор случайных чисел
    protected final Random _random = new Random();
    // Константы для обозначения свободного пути и преграды
    protected final int _freeRoad = 0;
    protected final int _barrier = 1;

    // Метод для создания карты
    public void CreateMap(int width, int height, IDrawingObject drawingObject, GraphicsContext gc) {
        _width = width;
        _height = height;
        _drawingObject = drawingObject;
        _graphicsContext = gc;
        
        // Генерируем карту
        GenerateMap();
        
        // Пока не удастся разместить объект на карте, генерируем новую карту
        while (!SetObjectOnMap()) {
            GenerateMap();
        }
        
        // Рисуем карту с объектом
        DrawMapWithObject();
    }

    // Метод для перемещения объекта
    public void MoveObject(Direction direction) {
        // Флаг, показывающий, что дорога свободна
        boolean roadIsClear = true;

        // Получаем текущую позицию объекта
        float[] position = _drawingObject.GetCurrentPosition();
        int xNumOfCells;
        int yNumOfCells;
        int xObjOffset;
        int yObjOffset;

        // В зависимости от направления перемещения выполняем проверки
        switch (direction) {
            case Up:
                // Вычисляем количество ячеек для проверки по горизонтали и вертикали
                xNumOfCells = (int)Math.ceil((position[2] - position[0]) / _size_x);
                yNumOfCells = (int)Math.ceil(_drawingObject.GetStep() / _size_y);
                // Рассчитываем смещение позиции объекта в ячейках
                xObjOffset = (int)(position[0] / _size_x);
                yObjOffset = (int)Math.floor(position[1] / _size_y);

                // Итерируемся по ячейкам для проверки преград (барьеров) вверх
                for (int i = 0; i < yNumOfCells; i++) {
                    if (!roadIsClear) {
                        break;
                    }
                    for (int j = 0; j < xNumOfCells; j++) {
                        // Проверяем, выходит ли ячейка за границы
                        if (yObjOffset - i < 0 || xObjOffset + j >= _map[0].length) {
                            break;
                        }
                        // Проверяем, есть ли барьер в текущей ячейке
                        if (_map[xObjOffset + j][yObjOffset - i] == _barrier) {
                            roadIsClear = false;
                            break;
                        }
                    }
                }
                break;

            case Down:
                // Аналогичные вычисления для направления вниз
                xNumOfCells = (int)Math.ceil((position[2] - position[0]) / _size_x);
                yNumOfCells = (int)Math.ceil(_drawingObject.GetStep() / _size_y);
                xObjOffset = (int)(position[0] / _size_x);
                yObjOffset = (int)Math.ceil(position[3]/ _size_y);

                // Итерируемся по ячейкам для проверки преград (барьеров) вниз
                for (int i = 0; i < yNumOfCells; i++) {
                    if (!roadIsClear) {
                        break;
                    }
                    for (int j = 0; j < xNumOfCells; j++) {
                        // Проверяем, выходит ли ячейка за границы
                        if (yObjOffset + i >= _map.length || xObjOffset + j >= _map[0].length) {
                            break;
                        }
                        // Проверяем, есть ли барьер в текущей ячейке
                        if (_map[xObjOffset + j][yObjOffset + i] == _barrier) {
                            roadIsClear = false;
                            break;
                        }
                    }
                }
                break;

                case Left:
                // Рассчитываем количество ячеек для проверки по горизонтали и вертикали
                xNumOfCells = (int)Math.ceil(_drawingObject.GetStep() / _size_x);
                yNumOfCells = (int)Math.ceil((position[3] - position[1]) / _size_y);
                
                // Рассчитываем смещение позиции объекта в ячейках
                xObjOffset = (int)Math.floor(position[0] / _size_x);
                yObjOffset = (int)(position[1] / _size_y);
            
                // Итерируемся по ячейкам для проверки преград (барьеров) влево
                for (int i = 0; i < yNumOfCells; i++) {
                    if (!roadIsClear) {
                        break;
                    }
                    for (int j = 0; j < xNumOfCells; j++) {
                        // Проверяем, выходит ли ячейка за границы
                        if (yObjOffset + i >= _map.length || xObjOffset - j < 0) {
                            break;
                        }
                        // Проверяем, есть ли барьер в текущей ячейке
                        if (_map[xObjOffset - j][yObjOffset + i] == _barrier) {
                            roadIsClear = false;
                            break;
                        }
                    }
                }
                break;
            
            case Right:
                // Аналогичные вычисления для направления вправо
                xNumOfCells = (int)Math.ceil(_drawingObject.GetStep() / _size_x);
                yNumOfCells = (int)Math.ceil((position[3] - position[1]) / _size_y);
                xObjOffset = (int)(position[2] / _size_x);
                yObjOffset = (int)Math.ceil(position[1] / _size_y);
            
                // Итерируемся по ячейкам для проверки преград (барьеров) вправо
                for (int i = 0; i < yNumOfCells; i++) {
                    if (!roadIsClear) {
                        break;
                    }
                    for (int j = 0; j < xNumOfCells; j++) {
                        // Проверяем, выходит ли ячейка за границы
                        if (yObjOffset + i >= _map.length || xObjOffset + j >= _map[0].length) {
                            break;
                        }
                        // Проверяем, есть ли барьер в текущей ячейке
                        if (_map[xObjOffset + j][yObjOffset + i] == _barrier) {
                            roadIsClear = false;
                            break;
                        }
                    }
                }
                break;
            }
            
            // Если дорога свободна, перемещаем объект в указанном направлении и перерисовываем карту
            if (roadIsClear) {
                _drawingObject.MoveObject(direction);
            }
            DrawMapWithObject();
        }            

    private boolean SetObjectOnMap() {
        // Проверяем, не являются ли объект или карта null
        if (_drawingObject == null || _map == null) {
            return false;
        }
        
        // Генерируем случайные координаты для объекта
        int x = _random.nextInt(0, 10);
        int y = _random.nextInt(0, 10);
    
        // Получаем текущую позицию объекта
        float[] position = _drawingObject.GetCurrentPosition();
        
        // Рассчитываем количество занимаемых объектом ячеек и их смещение в ячейках
        int xNumOfCells = (int)Math.ceil(position[2] / _size_x) - (int)Math.floor(position[0] / _size_x);
        int yNumOfCells = (int)Math.ceil(position[3] / _size_y) - (int)Math.floor(position[1] / _size_y);
        int xObjOffset = (int)(x / _size_x);
        int yObjOffset = (int)(y / _size_y);
    
        // Итерируемся по карте, чтобы найти подходящее место для объекта
        while (y < _height - (position[3] - position[1])) {
            while (x < _width - (position[2] - position[0])) {
                // Проверяем, свободна ли область от преград
                if (AreaIsFree(xNumOfCells, yNumOfCells, xObjOffset, yObjOffset)) {
                    // Устанавливаем объект на карту и возвращаем true
                    _drawingObject.SetObject(x, y, _width, _height);
                    return true;
                }
                x += (int)_size_x;
                xObjOffset = (int)(x / _size_x);
            }
            x = 0;
            y += (int)_size_y;
            yObjOffset = (int)(y / _size_y);
        }
    
        // Возвращаем false, если подходящее место не найдено
        return false;
    }
    

    private boolean AreaIsFree(int xNumOfCells, int yNumOfCells, int xObjOffset, int yObjOffset) {
        for (int i = 0; i <= yNumOfCells; i++) {
            for (int j = 0; j <= xNumOfCells; j++) {
                // Проверяем, не выходит ли ячейка за границы карты
                if (yObjOffset + i >= _map.length || xObjOffset + j >= _map[0].length) {
                    return false;
                }
                // Проверяем, есть ли барьер в текущей ячейке
                if (_map[xObjOffset + j][yObjOffset + i] == _barrier) {
                    return false;
                }
            }
        }
    
        // Возвращаем true, если область свободна от преград
        return true;
    }
    

    public void DrawMapWithObject() {
    // Проверяем, не являются ли объект или карта null
    if (_drawingObject == null || _map == null) {
        return;
    }

    // Итерируемся по карте и рисуем соответствующие части дороги и преград
    for (int i = 0; i < _map.length; ++i) {
        for (int j = 0; j < _map[0].length; ++j) {
            if (_map[i][j] == _freeRoad) {
                DrawRoadPart(_graphicsContext, i, j);
            }
            else if (_map[i][j] == _barrier) {
                DrawBarrierPart(_graphicsContext, i, j);
            }
        }
    }
    // Рисуем объект
    _drawingObject.DrawingObject(_graphicsContext);
}

// Абстрактные методы, которые должны быть реализованы в наследниках
protected abstract void GenerateMap();
protected abstract void DrawRoadPart(GraphicsContext gc, int i, int j);
protected abstract void DrawBarrierPart(GraphicsContext gc, int i, int j);
}
