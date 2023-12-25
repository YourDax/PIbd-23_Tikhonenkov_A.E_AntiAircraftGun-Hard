package com.example.antiaircraftgun;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class TrainingGroundMap extends AbstractMap{
    protected void DrawBarrierPart(GraphicsContext gc, int i, int j)
    {
        gc.setFill(Color.BLACK);
        gc.fillRect(i * _size_x, j * _size_y, i * (_size_x + 1), j * (_size_y + 1));
    }
    protected void DrawRoadPart(GraphicsContext gc, int i, int j)
    {
        gc.setFill(Color.LIGHTGREEN);
        gc.fillRect(i * _size_x, j * _size_y, i * (_size_x + 1), j * (_size_y + 1));
    }

    protected void GenerateMap()
    {
        _map = new int[100][100];
        _size_x = (float)_width / _map.length;
        _size_y = (float)_height / _map[0].length;
        int counter = 0;
        for (int i = 0; i < _map.length; ++i)
        {
            for (int j = 0; j < _map[0].length; ++j)
            {
                _map[i][j] = _freeRoad;
            }
        }
        while (counter < 25)
        {
            int x = _random.nextInt(0, 100);
            int y = _random.nextInt(0, 100);
            if (_map[x][y] == _freeRoad)
            {
                _map[x][y] = _barrier;
                counter++;
            }
        }
    }
}
