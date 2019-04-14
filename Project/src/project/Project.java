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
import javafx.stage.FileChooser;
import java.util.Scanner;
import project.Matrix;
import java.util.ArrayList;
import java.lang.String;
import java.io.File;
import java.io.IOException;

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
        
        Label errorLabel = new Label(" ");
        errorLabel.setPadding(new Insets(0, xscrollValue, 5, 5));
        
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
        
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Load");
        //fileChooser.showOpenDialog(primaryStage);
        
        Button btnSave = new Button();
        btnSave.setText("Save");
        btnSave.setStyle("-fx-base: rgb("+(10*1)+","+(20*1)+","+(10*1)+");");
                 
        //For LU button
        btnLU.setFocusTraversable(false);
        btnLU.setOnAction(new EventHandler<ActionEvent>() {
           
            @Override
            public void handle(ActionEvent event) {
                
                // Read matrix from txt area
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
                // Final Matrix A
                int nRow = i;
                int nCol = j;
                Matrix A = new Matrix(nCol, nRow);
                A = A.copy(array, nRow, nCol);
                
                
                // Read vector from txt area
                String[] vector = vectorTextArea.getText().split("\\s+");
                double[] vector_tmp = new double[nCol];
                int  k = 0;
                for(String vec : vector)
                {
                    vector_tmp[k] = Double.parseDouble(vec);
                    k++;
                }
                // Final vector b
                Vector b = new Vector(vector_tmp);
                
                // -- Compute LU Pivot --
                 
                Vector x = new Vector(nCol);
                Matrix L = new Matrix(nRow,nCol);
                Matrix U = new Matrix(nRow,nCol);
                Matrix P = new Matrix(nRow,nCol);
                
                LUpivot lu = new LUpivot(nRow, nCol);
                lu.reorder(A, nRow, P, errorLabel);
        
                Matrix M = new Matrix();
                M = M.multiplication(P, A); 
        
                lu.Lu_factorization(M, L, U, nRow, errorLabel);
        
                Vector PB = new Vector(nCol);
                PB = PB.multiplication(P, b);
                lu.Lu_solving(L, U, PB, nRow, x);
                double det = lu.determinant(L, U, P);
                    
                //Finaly - display results in Text Area
                BasicActions ba = new BasicActions();
                ba.DisplayLUResults(matrixTextArea, vectorTextArea, resultTextArea, A, b, L, U, x, det);
            }
        });
       
        
        //For inverse button
        btnInverse.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                
                // Read matrix from txt area
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
                // Final Matrix A
                int nRow = i;
                int nCol = j;
                Matrix A = new Matrix(nCol, nRow);
                A = A.copy(array, nRow, nCol);
                
                
                // Read vector from txt area
                String[] vector = vectorTextArea.getText().split("\\s+");
                double[] vector_tmp = new double[nCol];
                int  k = 0;
                for(String vec : vector)
                {
                    vector_tmp[k] = Double.parseDouble(vec);
                    k++;
                }
                // Final vector b
                Vector b = new Vector(vector_tmp);
                
                // -- Compute LU Pivot --
                 
                Vector x = new Vector(nCol);
                Matrix L = new Matrix(nRow,nCol);
                Matrix U = new Matrix(nRow,nCol);
                Matrix P = new Matrix(nRow,nCol);
                
                LUpivot lu = new LUpivot(nRow, nCol);
                lu.reorder(A, nRow, P, errorLabel);
        
                Matrix M = new Matrix();
                M = M.multiplication(P, A); 
        
                lu.Lu_factorization(M, L, U, nRow, errorLabel);
        
                Vector PB = new Vector(nCol);
                PB = PB.multiplication(P, b);
                lu.Lu_solving(L, U, PB, nRow, x);
                double det = lu.determinant(L, U, P);
                
                //Inverse Matrix
                Inverse inv = new Inverse();
                Matrix I = new Matrix();
                I = inv.inverseMatrix(L, U, P, nRow);
                
                //Finaly - display results in Text Area
                BasicActions ba = new BasicActions();
                ba.DisplayInvResults(matrixTextArea, vectorTextArea, resultTextArea, A, L, U, I, det);
            }
        });
        
        btnLoad.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                BasicActions ba = new BasicActions(); 
                 ba.FileRestrictions(fileChooser);
                 File file = fileChooser.showOpenDialog(primaryStage);
                    if (file != null) {
                        ba.Load(file, resultTextArea, errorLabel);
                    }
                
            }
        });
        
        btnSave.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                FileChooser fileChooser = new FileChooser();
                fileChooser.setTitle("Save results");
                
                //Set extension filter for text files
                FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
                fileChooser.getExtensionFilters().add(extFilter);
                
                File file = fileChooser.showSaveDialog(primaryStage);
                if (file != null) 
                {
                    
                    BasicActions ba = new BasicActions();
                    ba.Save(resultTextArea, file, errorLabel);
                    
                }
                
            }
        });
        
        //For clear button
        btnClear.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                BasicActions ba = new BasicActions();
                ba.Clear(matrixTextArea,vectorTextArea,resultTextArea);
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
