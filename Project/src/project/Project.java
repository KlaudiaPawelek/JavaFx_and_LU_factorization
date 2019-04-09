/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.scene.control.ToolBar;
import javafx.scene.control.TextField;
import javafx.scene.control.ScrollBar;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.paint.Color;
import javafx.scene.image.Image;


/**
 *
 * @author s297163
 */
public class Project extends Application {
    
    //Usefull page about layout!
    //https://o7planning.org/en/10625/javafx-hbox-vbox-layout-tutorial
    //https://o7planning.org/en/10857/javafx-scrollpane-tutorial
    ScrollBar xscrollBar;
    ScrollBar yscrollBar;
    double xscrollValue=0;
    double yscrollValue=15;
    int xBarWidth = 393;
    int xBarHeight = 15;
    int yBarWidth = 15;
    int yBarHeight = 393;
    
    @Override
    public void start(Stage primaryStage) {
        
    
        Button btnLU = new Button();
        btnLU.setText("LU pivot");
        btnLU.setStyle("-fx-base: rgb("+(10*1)+","+(20*1)+","+(10*1)+");");
        
        Button btnInverse = new Button();
        btnInverse.setText("Inverse");
        btnInverse.setStyle("-fx-base: rgb("+(10*1)+","+(20*1)+","+(10*1)+");");
        
        Button btnClear = new Button();
        btnClear.setText("Clear");
        btnClear.setStyle("-fx-base: rgb("+(10*1)+","+(20*1)+","+(10*1)+");");
        
        Button btnLoad = new Button();
        btnLoad.setText("Load");
        btnLoad.setStyle("-fx-base: rgb("+(10*1)+","+(20*1)+","+(10*1)+");");
        
        Button btnSave = new Button();
        btnSave.setText("Save");
        btnSave.setStyle("-fx-base: rgb("+(10*1)+","+(20*1)+","+(10*1)+");");
        
        btnLU.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                LUpivot lu = new LUpivot();
                lu.LUpivotFunction();
            }
        });
        
        //For inverse button
        btnInverse.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                Inverse inverse = new Inverse();
                inverse.InverseFunction();
            }
        });
        
        //For clear button
        btnClear.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                BasicActions basic = new BasicActions();
                basic.Clear();
            }
        });
        
        //Toolbar with buttons: Lu pivot, Inverse and Clear
        VBox root = new VBox();
        root.getStylesheets().add
        (Project.class.getResource("mainPage.css").toExternalForm());
        VBox toolbarStackPane = new VBox();
        toolbarStackPane.setAlignment(Pos.CENTER_LEFT);
        ToolBar toolbar = new ToolBar(btnLU, btnInverse, btnClear, btnLoad, btnSave);        
        toolbarStackPane.getChildren().add(toolbar);
        toolbarStackPane.setPadding(new Insets(0,0,20,0));
        root.getChildren().add(toolbarStackPane);
        
        Label matrixLabel = new Label("A = ");
        Label vectorLabel = new Label ("b = ");
        Label errorLabel = new Label(" ");
        errorLabel.setPadding(new Insets(0, xscrollValue, 5, 5));
        
        HBox matrixHBox = new HBox();
        matrixHBox.setAlignment(Pos.CENTER);
        matrixHBox.setSpacing(30);
        TextField matrixTextField = new TextField();
        TextField vectorTextField = new TextField();
        matrixTextField.setPadding(new Insets(10, 0, 0, 20));
        matrixTextField.setPrefWidth(350);
        matrixTextField.setPrefHeight(500);
        vectorTextField.setPadding(new Insets(10, 0, 0, 0)); //t r b l
        vectorTextField.setPrefWidth(300);
        vectorTextField.setPrefHeight(500);
        
        matrixHBox.getChildren().add(matrixLabel);
        matrixHBox.getChildren().add(matrixTextField);
        matrixHBox.getChildren().add(vectorLabel);
        matrixHBox.getChildren().add(vectorTextField);
        
        root.getChildren().add(matrixHBox);
        
        
        HBox resultHBox = new HBox();
        
        resultHBox.setAlignment(Pos.BOTTOM_CENTER);
        resultHBox.setPrefHeight(300);
        resultHBox.setPrefWidth(800);
        resultHBox.setPadding(new Insets(20, 0, 20, 0));
        
        TextField textField = new TextField("Result shall be visible here!");
        textField.setPrefHeight(300);
        textField.setPrefWidth(800);
        resultHBox.getChildren().add(textField);
        root.getChildren().add(resultHBox);
        root.getChildren().add(errorLabel);

        
        Scene scene = new Scene(root, 1024, 800, Color.GRAY);
        primaryStage.getIcons().add(new Image("project/logo.PNG"));
        primaryStage.setResizable(false);
        primaryStage.setTitle("Java project 2019");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
