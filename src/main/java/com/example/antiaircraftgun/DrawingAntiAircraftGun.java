package com.example.antiaircraftgun;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class DrawingAntiAircraftGun extends DrawingArmoredCar{
    public DrawingAntiAircraftGun(int speed, float weight, Color bodyColor, Color dopColor, boolean gun, boolean radar)
    {
        super(speed, weight, bodyColor, 110, 75);
        SetArmoredVehicle(new EntityAntiAircraftGun(speed, weight, bodyColor, dopColor, gun, radar));
    }

    @Override
    public void DrawTransport(GraphicsContext gc)
    {
        if (!(GetArmoredCar() instanceof EntityAntiAircraftGun antiAircraftGun))
        {
            return;
        }

        _startPosY += 35;
        super.DrawTransport(gc);
        _startPosY -= 35;

        gc.setStroke(Color.BLACK);

        if (antiAircraftGun.GetGun())
        {
            gc.setFill(antiAircraftGun.GetDopColor());
            gc.fillRect(_startPosX + 35, _startPosY + 20, 25, 15);
            gc.fillRect(_startPosX + 60, _startPosY + 25, 50, 5);
            gc.fillRect(_startPosX + 60, _startPosY + 30, 20, 5);
        }

        if (antiAircraftGun.GetRadar())
        {
            double[] radarX =
                    {
                            _startPosX + 32,
                            _startPosX + 32,
                            _startPosX + 22,
                            _startPosX + 10,
                    };

            double[] radarY =
                    {
                            _startPosY + 3,
                            _startPosY + 15,
                            _startPosY + 25,
                            _startPosY + 25,
                    };
            gc.setStroke(antiAircraftGun.GetDopColor());
            gc.setFill(antiAircraftGun.GetDopColor());
            gc.strokeLine(_startPosX + 27, _startPosY + 35, _startPosX + 27, _startPosY + 20);
            gc.fillPolygon(radarX, radarY, 4);
            gc.strokeLine(_startPosX + 20, _startPosY + 15, _startPosX + 15, _startPosY + 10);
            gc.fillOval(_startPosX + 10, _startPosY + 5, 10, 10);
        }
    }
}
