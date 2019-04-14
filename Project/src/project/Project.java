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
import javafx.scene.control.TextArea;
import javafx.scene.control.ScrollBar;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.image.Image;
import java.util.Scanner;
import project.Matrix;
import java.util.ArrayList;
import java.lang.String;

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
    double xscrollValue=100;
    double yscrollValue=50;
    int xBarWidth = 393;
    int xBarHeight = 15;
    int yBarWidth = 15;
    int yBarHeight = 393;
    
    
    @Override
    public void start(Stage primaryStage) {
        
    
        TextArea matrixTextArea = new TextArea();
        TextArea vectorTextArea = new TextArea();
        TextArea resultTextArea = new TextArea("Result shall be visible here!");
        matrixTextArea.setScrollTop(xscrollValue);
        matrixTextArea.setScrollLeft(yscrollValue);
        vectorTextArea.setScrollTop(xscrollValue);
        vectorTextArea.setScrollLeft(yscrollValue);
        resultTextArea.setScrollTop(xscrollValue);
        resultTextArea.setScrollLeft(yscrollValue);
        
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
                 
        //For LU button
        btnLU.setFocusTraversable(false);
        btnLU.setOnAction(new EventHandler<ActionEvent>() {
           
            @Override
            public void handle(ActionEvent event) {
                
                // Read matrix and vector from txt filed
                String[] rows = matrixTextArea.getText().split("\n");
                ArrayList<Double> array = new ArrayList<Double>();
                int i = 0;
                int j = 0;
                for(String row : rows)
                {
                    j = 0;
                    String[] cols = row.split("\\s+");
                    for(String col : cols)
                    {
                        array.add(Double.parseDouble(col));
                        j++;
                    }
                    i++;  
                }
                int nRow = i;
                int nCol = j;
                Matrix m = new Matrix(nCol, nRow);
                m = m.copy(array, nRow, nCol);
            }
        });
       
        
        //For inverse button
        btnInverse.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                Inverse inverse = new Inverse();
                
            }
        });
        
        btnLoad.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                
                
            }
        });
        
        btnSave.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                String results = new String();
                BasicActions bs = new BasicActions();
                bs.Save();
                
            }
        });
        
        //For clear button
        btnClear.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                matrixTextArea.clear();
                vectorTextArea.clear();
                resultTextArea.clear();
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
        matrixTextArea.setPadding(new Insets(10, 0, 0, 20));
        matrixTextArea.setPrefWidth(350);
        matrixTextArea.setPrefHeight(500);
        vectorTextArea.setPadding(new Insets(10, 0, 0, 0)); //t r b l
        vectorTextArea.setPrefWidth(300);
        vectorTextArea.setPrefHeight(500);
        
        matrixHBox.getChildren().add(matrixLabel);
        matrixHBox.getChildren().add(matrixTextArea);
        matrixHBox.getChildren().add(vectorLabel);
        matrixHBox.getChildren().add(vectorTextArea);
        
        root.getChildren().add(matrixHBox);
        
        
        HBox resultHBox = new HBox();
        
        resultHBox.setAlignment(Pos.BOTTOM_CENTER);
        resultHBox.setPrefHeight(300);
        resultHBox.setPrefWidth(800);
        resultHBox.setPadding(new Insets(20, 0, 20, 0));
        
  
        resultTextArea.setPrefHeight(300);
        resultTextArea.setPrefWidth(800);
        resultHBox.getChildren().add(resultTextArea);
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
    public static void main(String[] args) 
    {
        // Code for testing without GUI ;-)
        /*Scanner in = new Scanner(System.in);
        System.out.println("Input the size of the system");
        int size = in.nextInt();
        
        Matrix A = new Matrix(size,size);
        Vector x = new Vector(size);
        Vector b = new Vector(size);
        
        Matrix L = new Matrix(size,size);
        Matrix U = new Matrix(size,size);
        Matrix P = new Matrix(size,size);
        
        System.out.println("Input matrix: ");
        for(int i = 0; i<size; i++)
            for(int j = 0; j<size;j++)
                A.matrix[i][j] = in.nextDouble();
        
        System.out.println("Input vector: ");
        for(int i = 0; i<size; i++)
            b.vector[i] = in.nextDouble();
        
        LUpivot lu = new LUpivot(size, size);
        lu.reorder(A, size, P);
        
        Matrix M = new Matrix();
        M = M.multiplication(P, A); 
        
        lu.Lu_factorization(M, L, U, size);
        
        Vector PB = new Vector(size);
        PB = PB.multiplication(P, b);
        lu.Lu_solving(L, U, PB, size, x);
        double det = lu.determinant(L, U, P);
        
        //Inverse Matrix
        Inverse i = new Inverse();
        Matrix I = new Matrix();
        I = i.inverseMatrix(L, U, P, size);*/
        
        
        launch(args);
    }
}
