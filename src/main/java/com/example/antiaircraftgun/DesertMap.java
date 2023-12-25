package com.example.antiaircraftgun;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class DesertMap extends AbstractMap{
    protected void DrawBarrierPart(GraphicsContext gc, int i, int j)
    {
        gc.setFill(Color.BLACK);
        gc.fillRect(i * _size_x, j * _size_y, _size_x + 0.5, _size_y + 0.5);
    }
    protected void DrawRoadPart(GraphicsContext gc, int i, int j)
    {
        gc.setFill(Color.YELLOW);
        gc.fillRect(i * _size_x, j * _size_y, _size_x + 0.5, _size_y + 0.5);
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
        while (counter < 10)
        {
            int x = _random.nextInt(0, 90);
            int y = _random.nextInt(0, 90);
            int length = _random.nextInt(5, 10);
            if(counter < 5){
                for (int i = 0; i < length; i++) {
                    _map[x][y + i] = _barrier;
                }
            }
            else{
                for (int i = 0; i < length; i++) {
                    _map[x + i][y] = _barrier;
                }
            }
            counter++;
        }
    }
}
