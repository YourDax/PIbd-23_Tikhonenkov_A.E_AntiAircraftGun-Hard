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

public class ControllerMap {
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
    private ComboBox<String> comboBoxSelectorMap;
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
    private final double buttonMargin = 10.0;
    private AbstractMap _abstractMap;

    @FXML
    public void initialize()
    {
        buttonCreate.setTranslateX(buttonMargin);

        root.widthProperty().addListener((obs, oldVal, newVal) ->
        {
            UpdateGUI();
            if (_abstractMap != null)
            {
                _abstractMap.DrawMapWithObject();
            }
        });
        root.heightProperty().addListener((obs, oldVal, newVal) ->
        {
            UpdateGUI();
            if (_abstractMap != null)
            {
                _abstractMap.DrawMapWithObject();
            }
        });
    }

    @FXML
    void ButtonCreate_Click()
    {
        Random rnd = new Random();
        DrawingArmoredCar armoredCar = new DrawingArmoredCar(rnd.nextInt(200) + 100, rnd.nextInt(1000) + 1000,
                Color.rgb(rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256)));
        SetData(armoredCar);
    }

    @FXML
    void ButtonCreateModif_Click()
    {
        Random rnd = new Random();
        DrawingAntiAircraftGun armoredCar = new DrawingAntiAircraftGun(rnd.nextInt(200) + 100, rnd.nextInt(1000) + 1000,
                Color.rgb(rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256)),
                Color.rgb(rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256)),
                (rnd.nextInt(2) != 0), (rnd.nextInt(2) != 0));
        SetData(armoredCar);
    }

    @FXML
    void ButtonMove_Click(ActionEvent event)
    {
        if (_abstractMap == null)
        {
            return;
        }
        String buttonName = ((Button) event.getSource()).getId();
        switch (buttonName)
        {
            case "buttonUp" -> _abstractMap.MoveObject(Direction.Up);
            case "buttonDown" -> _abstractMap.MoveObject(Direction.Down);
            case "buttonLeft" -> _abstractMap.MoveObject(Direction.Left);
            case "buttonRight" -> _abstractMap.MoveObject(Direction.Right);
        }
    }

    @FXML
    private void ComboBoxSelectorMap_Changed()
    {
        switch (comboBoxSelectorMap.getValue())
        {
            case "Simple map" -> _abstractMap = new SimpleMap();
            case "Training ground map" -> _abstractMap = new TrainingGroundMap();
            case "Desert map" -> _abstractMap = new DesertMap();
        }
    }

    private void ChangeDrawningTrackRollers(DrawingArmoredCar armoredCar)
    {
        IDrawingAdditionalElement newDrawningTrackRollers = switch (comboBoxOrnamentType.getValue())
                {
                    case "None" -> new DrawingRollers(armoredCar.GetArmoredCar().GetBodyColor());
                    case "Rectangle" -> new DrawingRectangleOrnamentRollers(armoredCar.GetArmoredCar().GetBodyColor());
                    case "Oval" ->
                            new DrawingOvalOrnamentRollers(armoredCar.GetArmoredCar().GetBodyColor());
                    default -> null;
                };
        if (newDrawningTrackRollers != null)
        {
            armoredCar.SetDrawingRollers(newDrawningTrackRollers);
            armoredCar.GetDrawingRollers().SetNumberRollers(Integer.parseInt(comboBoxNumOfRollers.getValue()));
        }
    }

    private void UpdateGUI()
    {
        double sceneWidth = root.getWidth();
        double sceneHeight = root.getHeight();

        double flowPaneHeight = flowPane.getHeight();
        double buttonCreateHeight = buttonCreate.getHeight();

        canvas.setWidth(sceneWidth);
        flowPane.setPrefWidth(sceneWidth);
        canvas.setHeight(sceneHeight - flowPaneHeight);
        flowPane.setTranslateY(sceneHeight - flowPaneHeight);

        buttonCreate.setTranslateY(sceneHeight - flowPaneHeight - buttonCreateHeight - buttonMargin);
        buttonCreateModif.setTranslateY(sceneHeight - flowPaneHeight - buttonCreateHeight - buttonMargin);

        int buttonMoveHeight = 30;
        int distanceBetweenMoveButtons = 5;
        buttonUp.setTranslateY(sceneHeight - flowPaneHeight - buttonMoveHeight * 2.0 - buttonMargin -
                distanceBetweenMoveButtons);
        int buttonMoveWidth = 30;
        buttonUp.setTranslateX(sceneWidth - buttonMargin - buttonMoveWidth * 2.0 - distanceBetweenMoveButtons);

        buttonDown.setTranslateY(sceneHeight - flowPaneHeight - buttonMoveHeight - buttonMargin);
        buttonDown.setTranslateX(sceneWidth- buttonMargin - buttonMoveWidth * 2.0 - distanceBetweenMoveButtons);

        buttonLeft.setTranslateY(sceneHeight - flowPaneHeight - buttonMoveHeight - buttonMargin);
        buttonLeft.setTranslateX(sceneWidth - buttonMargin - buttonMoveWidth * 3.0 -
                distanceBetweenMoveButtons * 2.0);

        buttonRight.setTranslateY(sceneHeight - flowPaneHeight - buttonMoveHeight - buttonMargin);
        buttonRight.setTranslateX(sceneWidth - buttonMargin - buttonMoveWidth);
    }

    private void SetData(DrawingArmoredCar armoredCar)
    {
        ChangeDrawningTrackRollers(armoredCar);
        labelSpeedValue.setText(Integer.toString(armoredCar.GetArmoredCar().GetSpeed()));
        labelWeightValue.setText(Double.toString(armoredCar.GetArmoredCar().GetWeight()));
        labelBodyColorValue.setText(armoredCar.GetArmoredCar().GetBodyColor().toString());
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.clearRect(0.0, 0.0, canvas.getWidth(), canvas.getHeight());
        _abstractMap.CreateMap((int)canvas.getWidth(), (int)canvas.getHeight(), new DrawingObjectArmoredCar(armoredCar), gc);
    }
}
