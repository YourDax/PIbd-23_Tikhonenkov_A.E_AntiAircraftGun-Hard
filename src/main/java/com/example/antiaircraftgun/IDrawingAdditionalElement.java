package com.example.antiaircraftgun;

import javafx.scene.canvas.GraphicsContext;

public interface IDrawingAdditionalElement {
    public void SetNumberRollers(int numberOfRollers);
    public void DrawRollers(GraphicsContext gc, float startPosX, float startPosY);
}
