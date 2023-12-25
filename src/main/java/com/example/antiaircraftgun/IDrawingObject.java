package com.example.antiaircraftgun;

import javafx.scene.canvas.GraphicsContext;

public interface IDrawingObject {
    public float GetStep();
    public void SetObject(int x, int y, int width, int height);
    public void MoveObject(Direction direction);
    public void DrawingObject(GraphicsContext gc);
    public float[] GetCurrentPosition();
}