package com.example.antiaircraftgun;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

import java.util.Random;

public class ControllerArmoredCar {
    @FXML
    private Pane root;
    @FXML
    private Canvas canvas;
    @FXML
    private FlowPane flowPane;
    @FXML
    private Label labelSpeedValue;
    @FXML
    private Label labelWeightValue;
    @FXML
    private Label labelBodyColorValue;
    @FXML
    private ComboBox<String> comboBoxNumOfRollers;
    @FXML
    private ComboBox<String> comboBoxOrnamentType;
    @FXML
    private Button buttonCreate;
    @FXML
    private Button buttonCreateModif;
    @FXML
    private Button buttonLeft;
    @FXML
    private Button buttonRight;
    @FXML
    private Button buttonUp;
    @FXML
    private Button buttonDown;
    private final double rootPadding = 10.0;
    private DrawingArmoredCar _armoredCar;

    @FXML
    public void initialize()
    {
        buttonCreate.setTranslateX(rootPadding);

        root.widthProperty().addListener((obs, oldVal, newVal) ->
        {
            UpdateGUI();
            if (_armoredCar != null)
            {
                _armoredCar.ChangeBorders((int) canvas.getWidth(), (int) canvas.getHeight());
            }
            Draw();
        });
        root.heightProperty().addListener((obs, oldVal, newVal) ->
        {
            UpdateGUI();
            if (_armoredCar != null)
            {
                _armoredCar.ChangeBorders((int) canvas.getWidth(), (int) canvas.getHeight());
            }
            Draw();
        });
    }

    @FXML
    void ButtonCreate_Click()
    {
        Random rnd = new Random();
        _armoredCar = new DrawingArmoredCar(rnd.nextInt(200) + 100, rnd.nextInt(1000) + 1000,
                Color.rgb(rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256)));
        SetData();
        Draw();
    }

    @FXML
    void ButtonCreateModif_Click()
    {
        Random rnd = new Random();
        _armoredCar = new DrawingAntiAircraftGun(rnd.nextInt(200) + 100, rnd.nextInt(1000) + 1000,
                Color.rgb(rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256)),
                Color.rgb(rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256)),
                (rnd.nextInt(2) != 0), (rnd.nextInt(2) != 0));
        SetData();
        Draw();
    }

    @FXML
    void ButtonMove_Click(ActionEvent event)
    {
        if (_armoredCar == null)
        {
            return;
        }
        String buttonName = ((Button) event.getSource()).getId();
        switch (buttonName)
        {
            case "buttonUp" -> _armoredCar.MoveTransport(Direction.Up);
            case "buttonDown" -> _armoredCar.MoveTransport(Direction.Down);
            case "buttonLeft" -> _armoredCar.MoveTransport(Direction.Left);
            case "buttonRight" -> _armoredCar.MoveTransport(Direction.Right);
        }
        Draw();
    }

    @FXML
    private void ComboBoxOrnamentType_Changed()
    {
        if (_armoredCar != null)
        {
            ChangeDrawingRollers();
            Draw();
        }
    }

    @FXML
    private void ComboBoxNumOfRollers_Changed()
    {
        if (_armoredCar != null)
        {
            _armoredCar.GetDrawingRollers().SetNumberRollers(Integer.parseInt(comboBoxNumOfRollers.getValue()));
            Draw();
        }
    }

    private void ChangeDrawingRollers()
    {
        if (_armoredCar != null)
        {
            IDrawingAdditionalElement newDrawingRollers = switch (comboBoxOrnamentType.getValue())
                    {
                        case "None" -> new DrawingRollers(_armoredCar.GetArmoredCar().GetBodyColor());
                        case "Rectangle" -> new DrawingRectangleOrnamentRollers(_armoredCar.GetArmoredCar().GetBodyColor());
                        case "Oval" ->
                                new DrawingOvalOrnamentRollers(_armoredCar.GetArmoredCar().GetBodyColor());
                        default -> null;
                    };
            if (newDrawingRollers != null)
            {
                _armoredCar.SetDrawingRollers(newDrawingRollers);
                _armoredCar.GetDrawingRollers().SetNumberRollers(Integer.parseInt(comboBoxNumOfRollers.getValue()));
            }
        }
    }

    private void UpdateGUI()
    {
        double rootWidth = root.getWidth();
        double rootHeight = root.getHeight();

        double flowPaneHeight = flowPane.getHeight();
        double buttonCreateHeight = buttonCreate.getHeight();

        canvas.setWidth(rootWidth);
        flowPane.setPrefWidth(rootWidth);
        canvas.setHeight(rootHeight - flowPaneHeight);
        flowPane.setTranslateY(rootHeight - flowPaneHeight);

        buttonCreate.setTranslateY(rootHeight - flowPaneHeight - buttonCreateHeight - rootPadding);
        buttonCreateModif.setTranslateY(rootHeight - flowPaneHeight - buttonCreateHeight - rootPadding);

        int buttonMoveSize = 30;
        int distanceBetweenButtons = 5;
        buttonUp.setTranslateY(rootHeight - flowPaneHeight - buttonMoveSize * 2.0 - rootPadding -
                distanceBetweenButtons);
        buttonUp.setTranslateX(rootWidth - rootPadding - buttonMoveSize * 2.0 - distanceBetweenButtons);

        buttonDown.setTranslateY(rootHeight - flowPaneHeight - buttonMoveSize - rootPadding);
        buttonDown.setTranslateX(rootWidth- rootPadding - buttonMoveSize * 2.0 - distanceBetweenButtons);

        buttonLeft.setTranslateY(rootHeight - flowPaneHeight - buttonMoveSize - rootPadding);
        buttonLeft.setTranslateX(rootWidth - rootPadding - buttonMoveSize * 3.0 -
                distanceBetweenButtons * 2.0);

        buttonRight.setTranslateY(rootHeight - flowPaneHeight - buttonMoveSize - rootPadding);
        buttonRight.setTranslateX(rootWidth - rootPadding - buttonMoveSize);
    }

    private void Draw()
    {
        GraphicsContext gc = canvas.getGraphicsContext2D();

        gc.clearRect(0.0, 0.0, canvas.getWidth(), canvas.getHeight());
        gc.setFill(Color.WHITE);
        gc.fillRect(0.0, 0.0, canvas.getWidth(), canvas.getHeight());

        gc.setStroke(Color.BLACK);
        gc.setLineWidth(4);
        gc.strokeRect(0.0, 0.0, canvas.getWidth(), canvas.getHeight());

        if (_armoredCar != null)
        {
            _armoredCar.DrawTransport(gc);
        }
    }

    private void SetData()
    {
        ChangeDrawingRollers();
        Random rnd = new Random();
        _armoredCar.SetPosition(rnd.nextInt(90) + 10, rnd.nextInt(90) + 5, (int) canvas.getWidth(), (int) canvas.getHeight());
        labelSpeedValue.setText(Integer.toString(_armoredCar.GetArmoredCar().GetSpeed()));
        labelWeightValue.setText(Double.toString(_armoredCar.GetArmoredCar().GetWeight()));
        labelBodyColorValue.setText(_armoredCar.GetArmoredCar().GetBodyColor().toString());
    }
}
