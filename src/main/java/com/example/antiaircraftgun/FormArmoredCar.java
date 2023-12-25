package com.example.antiaircraftgun;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Random;

public class FormArmoredCar extends Application
{
    private Pane _root;
    private Scene _scene;
    private Canvas _canvas;
    private HBox _hBox;
    private Button _buttonCreate;
    private Button _buttonUp;
    private Button _buttonDown;
    private Button _buttonLeft;
    private Button _buttonRight;
    private Label _labelSpeedValue;
    private Label _labelWeightValue;
    private Label _labelBodyColorValue;
    private ComboBox<String> _comboBoxNumRollers;
    private double _hBoxHeight;
    private double _buttonCreateHeight;
    private final int _buttonMoveWidth = 30;
    private final int _buttonMoveHeight = 30;
    private final int _distanceBetweenMoveButtons = 5;
    private final double _buttonMargin = 10.0;
    private DrawingArmoredCar _armoredCar;

    @Override
    public void start(Stage stage) throws IOException
    {
        FXMLLoader fxmlLoader = new FXMLLoader(FormArmoredCar.class.getResource("hello-view.fxml"));
        _scene = new Scene(fxmlLoader.load(), 600, 400);

        _root = (Pane) _scene.lookup("#root");
        _root.setStyle("-fx-background-color: #31374c;");

        _canvas = new Canvas();
        _root.getChildren().add(_canvas);

        InitHBox();
        InitButtonCreate();
        InitMoveButtons();

        stage.setTitle("AntiAircraftGun");
        stage.setScene(_scene);

        _scene.widthProperty().addListener((obs, oldVal, newVal) -> {
            UpdateGUI();
            if (_armoredCar != null)
            {
                _armoredCar.ChangeBorders((int) _canvas.getWidth(), (int) _canvas.getHeight());
            }
            Draw();
        });

        _scene.heightProperty().addListener((obs, oldVal, newVal) -> {
            UpdateGUI();
            if (_armoredCar != null)
            {
                _armoredCar.ChangeBorders((int) _canvas.getWidth(), (int) _canvas.getHeight());
            }
            Draw();
        });

        _root.applyCss();
        _root.layout();

        stage.setOnShowing(event -> {
            _hBoxHeight = _hBox.getHeight();
            _buttonCreateHeight = _buttonCreate.getHeight();
            UpdateGUI();
            Draw();
        });

        stage.show();
    }

    private void InitHBox()
    {
        _hBox = new HBox();
        _hBox.setStyle("-fx-background-color: #31374c;");
        _root.getChildren().add(_hBox);

        Label labelSpeed = new Label("Speed:");
        labelSpeed.setStyle("-fx-text-fill: #b8becc; -fx-padding: 5 5 5 5;");
        _hBox.getChildren().add(labelSpeed);

        _labelSpeedValue = new Label("-");
        _labelSpeedValue.setStyle("-fx-text-fill: #ffffff; -fx-padding: 5 5 5 0; -fx-font-weight: bold;");
        _hBox.getChildren().add(_labelSpeedValue);

        Label labelWeight = new Label("Weight:");
        labelWeight.setStyle("-fx-text-fill: #b8becc; -fx-padding: 5 5 5 5;");
        _hBox.getChildren().add(labelWeight);

        _labelWeightValue = new Label("-");
        _labelWeightValue.setStyle("-fx-text-fill: #ffffff; -fx-padding: 5 5 5 0; -fx-font-weight: bold;");
        _hBox.getChildren().add(_labelWeightValue);

        Label labelHullColor = new Label("Color:");
        labelHullColor.setStyle("-fx-text-fill: #b8becc; -fx-padding: 5 5 5 5;");
        _hBox.getChildren().add(labelHullColor);

        _labelBodyColorValue = new Label("-");
        _labelBodyColorValue.setStyle("-fx-text-fill: #ffffff; -fx-padding: 5 5 5 0; -fx-font-weight: bold;");
        _hBox.getChildren().add(_labelBodyColorValue);

        Label labelNumRollers = new Label("Rollers:");
        labelNumRollers.setStyle("-fx-text-fill: #b8becc; -fx-padding: 5 5 5 5;");
        _hBox.getChildren().add(labelNumRollers);

        ObservableList<String> optionsForNumRollers = FXCollections.observableArrayList("4", "5", "6");
        _comboBoxNumRollers = new ComboBox<>(optionsForNumRollers);
        _comboBoxNumRollers.setValue("4");

        _comboBoxNumRollers.setOnAction(e -> {
            if (_armoredCar != null) {
                _armoredCar.GetDrawningRollers().SetNumberRollers(Integer.parseInt(_comboBoxNumRollers.getValue()));
                Draw();
            }
        });

        _hBox.getChildren().add(_comboBoxNumRollers);
    }
    private void InitButtonCreate()
    {
        _buttonCreate = new Button("Create");
        _buttonCreate.setTranslateX(_buttonMargin);
        _root.getChildren().add(_buttonCreate);

        _buttonCreate.setOnAction(e -> {
            Random rnd = new Random();

            _armoredCar = new DrawingArmoredCar();
            _armoredCar.Init(rnd.nextInt(200) + 100, rnd.nextInt(1000) + 1000,
                    Color.rgb(rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256)));
            _armoredCar.SetPosition(rnd.nextInt(90) + 10, rnd.nextInt(90)+10,
                    (int) _canvas.getWidth(), (int) _canvas.getHeight());

            _armoredCar.GetDrawningRollers().SetNumberRollers(Integer.parseInt(_comboBoxNumRollers.getValue()));

            _labelSpeedValue.setText(Integer.toString(_armoredCar.GetArmoredCar().GetSpeed()));
            _labelWeightValue.setText(Double.toString(_armoredCar.GetArmoredCar().GetWeight()));
            _labelBodyColorValue.setText(_armoredCar.GetArmoredCar().GetBodyColor().toString());

            Draw();
        });
    }
    private void InitMoveButtons()
    {
        Image img;
        ImageView view;

        // Button "Up"
        _buttonUp = new Button();

        img = new Image("arrowUp.png");
        view = new ImageView(img);
        view.setFitHeight(14);
        view.setFitWidth(14);
        _buttonUp.setGraphic(view);

        _buttonUp.setPrefWidth(_buttonMoveWidth);
        _buttonUp.setPrefHeight(_buttonMoveHeight);

        _buttonUp.setOnAction(e -> {
            if (_armoredCar != null) {
                _armoredCar.MoveTransport(Direction.Up);
                Draw();
            }
        });

        _root.getChildren().add(_buttonUp);

        // Button "Down"
        _buttonDown = new Button();

        img = new Image("arrowDown.png");
        view = new ImageView(img);
        view.setFitHeight(14);
        view.setFitWidth(14);
        _buttonDown.setGraphic(view);

        _buttonDown.setPrefWidth(_buttonMoveWidth);
        _buttonDown.setPrefHeight(_buttonMoveHeight);

        _buttonDown.setOnAction(e -> {
            if (_armoredCar != null) {
                _armoredCar.MoveTransport(Direction.Down);
                Draw();
            }
        });

        _root.getChildren().add(_buttonDown);

        // Button "Left"
        _buttonLeft = new Button();

        img = new Image("arrowLeft.png");
        view = new ImageView(img);
        view.setFitHeight(14);
        view.setFitWidth(14);
        _buttonLeft.setGraphic(view);

        _buttonLeft.setPrefWidth(_buttonMoveWidth);
        _buttonLeft.setPrefHeight(_buttonMoveHeight);

        _buttonLeft.setOnAction(e -> {
            if (_armoredCar != null) {
                _armoredCar.MoveTransport(Direction.Left);
                Draw();
            }
        });

        _root.getChildren().add(_buttonLeft);

        // Button "Right"
        _buttonRight = new Button();

        img = new Image("arrowRight.png");
        view = new ImageView(img);
        view.setFitHeight(14);
        view.setFitWidth(14);
        _buttonRight.setGraphic(view);

        _buttonRight.setPrefWidth(_buttonMoveWidth);
        _buttonRight.setPrefHeight(_buttonMoveHeight);

        _buttonRight.setOnAction(e -> {
            if (_armoredCar != null) {
                _armoredCar.MoveTransport(Direction.Right);
                Draw();
            }
        });

        _root.getChildren().add(_buttonRight);
    }
    private void UpdateGUI()
    {
        double sceneWidth = _scene.getWidth();
        double sceneHeight = _scene.getHeight();

        _canvas.setWidth(sceneWidth);
        _hBox.setPrefWidth(sceneWidth);
        _canvas.setHeight(sceneHeight - _hBoxHeight);
        _hBox.setTranslateY(sceneHeight - _hBoxHeight);

        _buttonCreate.setTranslateY(sceneHeight - _hBoxHeight - _buttonCreateHeight - _buttonMargin);

        _buttonUp.setTranslateY(sceneHeight - _hBoxHeight - _buttonMoveHeight * 2.0 - _buttonMargin -
                _distanceBetweenMoveButtons);
        _buttonUp.setTranslateX(sceneWidth - _buttonMargin - _buttonMoveWidth * 2.0 - _distanceBetweenMoveButtons);

        _buttonDown.setTranslateY(sceneHeight - _hBoxHeight - _buttonMoveHeight - _buttonMargin);
        _buttonDown.setTranslateX(sceneWidth- _buttonMargin - _buttonMoveWidth * 2.0 - _distanceBetweenMoveButtons);

        _buttonLeft.setTranslateY(sceneHeight - _hBoxHeight - _buttonMoveHeight - _buttonMargin);
        _buttonLeft.setTranslateX(sceneWidth - _buttonMargin - _buttonMoveWidth * 3.0 -
                _distanceBetweenMoveButtons * 2.0);

        _buttonRight.setTranslateY(sceneHeight - _hBoxHeight - _buttonMoveHeight - _buttonMargin);
        _buttonRight.setTranslateX(sceneWidth - _buttonMargin - _buttonMoveWidth);
    }

    private void Draw()
    {
        GraphicsContext gc = _canvas.getGraphicsContext2D();

        gc.clearRect(0.0, 0.0, _canvas.getWidth(), _canvas.getHeight());
        gc.setFill(Color.WHITE);
        gc.fillRect(0.0, 0.0, _canvas.getWidth(), _canvas.getHeight());

        gc.setStroke(Color.BLACK);
        gc.setLineWidth(4);
        gc.strokeRect(0.0, 0.0, _canvas.getWidth(), _canvas.getHeight());

        if (_armoredCar != null)
        {
            _armoredCar.DrawTransport(gc);
        }
    }

    public static void main(String[] args)
    {
        launch();
    }
}
