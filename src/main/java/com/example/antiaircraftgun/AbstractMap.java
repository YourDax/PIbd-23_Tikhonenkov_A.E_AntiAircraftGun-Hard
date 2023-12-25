package com.example.antiaircraftgun;

import javafx.scene.canvas.GraphicsContext;

import java.util.Random;

public abstract class AbstractMap {
    private IDrawingObject _drawingObject = null;
    private GraphicsContext _graphicsContext = null;
    protected int[][] _map = null;
    protected int _width;
    protected int _height;
    protected float _size_x;
    protected float _size_y;
    protected final Random _random = new Random();
    protected final int _freeRoad = 0;
    protected final int _barrier = 1;

    public  void CreateMap(int width, int height, IDrawingObject drawingObject, GraphicsContext gc)
    {
        _width = width;
        _height = height;
        _drawingObject = drawingObject;
        _graphicsContext = gc;
        GenerateMap();
        while (!SetObjectOnMap())
        {
            GenerateMap();
        }
        DrawMapWithObject();
    }

    public void MoveObject(Direction direction)
    {
        boolean roadIsClear = true;

        float[] position = _drawingObject.GetCurrentPosition();
        int xNumOfCells;
        int yNumOfCells;
        int xObjOffset;
        int yObjOffset;

        switch (direction)
        {
            case Up:
                xNumOfCells = (int)Math.ceil((position[2] - position[0]) / _size_x);
                yNumOfCells = (int)Math.ceil(_drawingObject.GetStep() / _size_y);
                xObjOffset = (int)(position[0] / _size_x);
                yObjOffset = (int)Math.floor(position[1] / _size_y);

                for (int i = 0; i < yNumOfCells; i++)
                {
                    if (!roadIsClear)
                    {
                        break;
                    }
                    for (int j = 0; j < xNumOfCells; j++)
                    {
                        if (yObjOffset - i < 0 || xObjOffset + j >= _map[0].length)
                        {
                            break;
                        }
                        if (_map[xObjOffset + j][yObjOffset - i] == _barrier)
                        {
                            roadIsClear = false;
                            break;
                        }
                    }
                }
                break;

            case Down:
                xNumOfCells = (int)Math.ceil((position[2] - position[0]) / _size_x);
                yNumOfCells = (int)Math.ceil(_drawingObject.GetStep() / _size_y);
                xObjOffset = (int)(position[0] / _size_x);
                yObjOffset = (int)Math.ceil(position[3]/ _size_y);

                for (int i = 0; i < yNumOfCells; i++)
                {
                    if (!roadIsClear)
                    {
                        break;
                    }
                    for (int j = 0; j < xNumOfCells; j++)
                    {
                        if (yObjOffset + i >= _map.length || xObjOffset + j >= _map[0].length)
                        {
                            break;
                        }
                        if (_map[xObjOffset + j][yObjOffset + i] == _barrier)
                        {
                            roadIsClear = false;
                            break;
                        }
                    }
                }
                break;

            case Left:
                xNumOfCells = (int)Math.ceil(_drawingObject.GetStep() / _size_x);
                yNumOfCells = (int)Math.ceil((position[3] - position[1]) / _size_y);
                xObjOffset = (int)Math.floor(position[0] / _size_x);
                yObjOffset = (int)(position[1] / _size_y);

                for (int i = 0; i < yNumOfCells; i++)
                {
                    if (!roadIsClear)
                    {
                        break;
                    }
                    for (int j = 0; j < xNumOfCells; j++)
                    {
                        if (yObjOffset + i >= _map.length || xObjOffset - j < 0)
                        {
                            break;
                        }
                        if (_map[xObjOffset - j][yObjOffset + i] == _barrier)
                        {
                            roadIsClear = false;
                            break;
                        }
                    }
                }
                break;

            case Right:
                xNumOfCells = (int)Math.ceil(_drawingObject.GetStep() / _size_x);
                yNumOfCells = (int)Math.ceil((position[3] - position[1]) / _size_y);
                xObjOffset = (int)(position[2] / _size_x);
                yObjOffset = (int)Math.ceil(position[1] / _size_y);

                for (int i = 0; i < yNumOfCells; i++)
                {
                    if (!roadIsClear)
                    {
                        break;
                    }
                    for (int j = 0; j < xNumOfCells; j++)
                    {
                        if (yObjOffset + i >= _map.length || xObjOffset + j >= _map[0].length)
                        {
                            break;
                        }
                        if (_map[xObjOffset + j][yObjOffset + i] == _barrier)
                        {
                            roadIsClear = false;
                            break;
                        }
                    }
                }
                break;
        }
        if (roadIsClear)
        {
            _drawingObject.MoveObject(direction);
        }
        DrawMapWithObject();
    }

    private boolean SetObjectOnMap()
    {
        if (_drawingObject == null || _map == null)
        {
            return false;
        }
        int x = _random.nextInt(0, 10);
        int y = _random.nextInt(0, 10);

        float[] position = _drawingObject.GetCurrentPosition();
        int xNumOfCells = (int)Math.ceil(position[2] / _size_x) - (int)Math.floor(position[0] / _size_x);
        int yNumOfCells = (int)Math.ceil(position[3] / _size_y) - (int)Math.floor(position[1] / _size_y);
        int xObjOffset = (int)(x / _size_x);
        int yObjOffset = (int)(y / _size_y);

        while (y < _height - (position[3] - position[1]))
        {
            while (x < _width - (position[2] - position[0]))
            {
                if (AreaIsFree(xNumOfCells, yNumOfCells, xObjOffset, yObjOffset))
                {
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

        return false;
    }

    private boolean AreaIsFree(int xNumOfCells, int yNumOfCells, int xObjOffset, int yObjOffset)
    {
        for (int i = 0; i <= yNumOfCells; i++)
        {
            for (int j = 0; j <= xNumOfCells; j++)
            {
                if (yObjOffset + i >= _map.length || xObjOffset + j >= _map[0].length)
                {
                    return false;
                }
                if (_map[xObjOffset + j][yObjOffset + i] == _barrier)
                {
                    return false;
                }
            }
        }

        return true;
    }

    public void DrawMapWithObject()
    {
        if (_drawingObject == null || _map == null)
        {
            return;
        }

        for (int i = 0; i < _map.length; ++i)
        {
            for (int j = 0; j < _map[0].length; ++j)
            {
                if (_map[i][j] == _freeRoad)
                {
                    DrawRoadPart(_graphicsContext, i, j);
                }
                else if (_map[i][j] == _barrier)
                {
                    DrawBarrierPart(_graphicsContext, i, j);
                }
            }
        }
        _drawingObject.DrawingObject(_graphicsContext);
    }

    protected abstract void GenerateMap();
    protected abstract void DrawRoadPart(GraphicsContext gc, int i, int j);
    protected abstract void DrawBarrierPart(GraphicsContext gc, int i, int j);
}
