package com.example.antiaircraftgun;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class FormArmoredCar extends Application
{
    @Override
    public void start(Stage stage) throws IOException
    {
        FXMLLoader fxmlLoader = new FXMLLoader(FormArmoredCar.class.getResource("form-armored-vehicle-view.fxml"));

        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        stage.setTitle("AntiAircraftGun");

        stage.setScene(scene);
        FirstUpdateGUI(scene);

        stage.show();
    }

    private void FirstUpdateGUI(Scene scene)
    {
        Pane root = (Pane)scene.lookup("#root");
        Canvas canvas = (Canvas)scene.lookup("#canvas");
        Button buttonCreate = (Button)scene.lookup("#buttonCreate");
        Button buttonCreateModif = (Button)scene.lookup("#buttonCreateModif");
        Button buttonUp = (Button)scene.lookup("#buttonUp");
        Button buttonLeft = (Button)scene.lookup("#buttonLeft");
        Button buttonRight = (Button)scene.lookup("#buttonRight");
        Button buttonDown = (Button)scene.lookup("#buttonDown");
        FlowPane flowPane = (FlowPane)scene.lookup("#flowPane");

        root.applyCss();
        root.layout();

        double flowPaneHeight = flowPane.getHeight();
        double buttonCreateHeight = buttonCreate.getHeight();
        double buttonCreateWidth = buttonCreate.getWidth();
        double rootWidth = root.getWidth();
        double rootHeight = root.getHeight();
        double rootPadding = 10.0;
        double distanceBetweenButtons = 5.0;
        double buttonMoveSize = 30.0;

        canvas.setWidth(rootWidth);
        flowPane.setPrefWidth(rootWidth);
        canvas.setHeight(rootHeight - flowPaneHeight);
        flowPane.setTranslateY(rootHeight - flowPaneHeight);
        buttonCreateModif.setTranslateX(rootPadding + buttonCreateWidth + distanceBetweenButtons);
        buttonCreate.setTranslateY(rootHeight - flowPaneHeight - buttonCreateHeight - rootPadding);
        buttonCreateModif.setTranslateY(rootHeight - flowPaneHeight - buttonCreateHeight - rootPadding);

        buttonUp.setTranslateY(rootHeight - flowPaneHeight - buttonMoveSize * 2.0 - rootPadding -
                distanceBetweenButtons);
        buttonUp.setTranslateX(rootWidth - rootPadding - buttonMoveSize * 2.0 - distanceBetweenButtons);

        buttonDown.setTranslateY(rootHeight - flowPaneHeight - buttonMoveSize - rootPadding);
        buttonDown.setTranslateX(rootWidth- rootPadding - buttonMoveSize * 2.0 - distanceBetweenButtons);

        buttonLeft.setTranslateY(rootHeight - flowPaneHeight - buttonMoveSize - rootPadding);
        buttonLeft.setTranslateX(rootWidth - rootPadding - buttonMoveSize * 3.0 -  distanceBetweenButtons * 2.0);

        buttonRight.setTranslateY(rootHeight - flowPaneHeight - buttonMoveSize - rootPadding);
        buttonRight.setTranslateX(rootWidth - rootPadding - buttonMoveSize);
    }
    public static void main(String[] args)
    {
        launch();
    }
}
