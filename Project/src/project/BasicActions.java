/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type;
import javafx.scene.control.TextArea;
import java.lang.String;
import java.util.Arrays;
import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.awt.Desktop;
import java.util.logging.Logger;
import java.util.logging.Level;
import javafx.stage.FileChooser;
import java.io.PrintWriter;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Scanner;

/**
 *
 * @author s297163
 */
public class BasicActions {
    
    // Empty constructor
    public BasicActions()
    {
        //TO DO
    }
    
    // Clear all text areas, which are on main scene.
    public void Clear(TextArea matrixTextArea, TextArea vectorTextArea, TextArea resultTextArea)
    {
        matrixTextArea.clear();
        vectorTextArea.clear();
        resultTextArea.clear();
    }
    
    // Display results in Text Area, which is on main scene.
    public void DisplayLUResults(TextArea matrixTextArea, TextArea vectorTextArea, TextArea resultTextArea, Matrix A, Vector b, Matrix L, Matrix U, Vector x, double det)
    {
        String message = "LU Decomposition with scaled partial pivoting \n \n";
        String originalMtx = "Original matrix \n";
        String originalVec = "\nOriginal vector \n";
        String lowMtx = "\nLower matrix \n";
        String upMtx = "\nUpper matrix \n";
        String solution = "\nSolution \n";
        String determinant = "\nDeretminant: ";
        
        String mtxA = Arrays.deepToString(A.matrix);
        String vecb = Arrays.toString(b.vector);
        String mtxL = Arrays.deepToString(L.matrix);
        String mtxU = Arrays.deepToString(U.matrix);
        String vecx = Arrays.toString(x.vector);
        String detString = Double.toString(det);
        
        String finalText = message + originalMtx + mtxA + originalVec + vecb +
                lowMtx + mtxL + upMtx + mtxU + solution + vecx + determinant + detString;
        resultTextArea.setWrapText(true);
        
        String formatted = finalText
                            .replace(",", "")  //remove commas
                            .replace("[", "")  //remove right bracket
                            .replace("]", "\n")  //remove left bracket
                            .trim();           
        resultTextArea.setText(formatted);
    }
    
     public void DisplayInvResults(TextArea matrixTextArea, TextArea vectorTextArea, TextArea resultTextArea, Matrix A, Matrix L, Matrix U, Matrix Inv, double det)
    {
        String message = "Matrix Inversion \n \n";
        String originalMtx = "Original matrix \n";
        String lowMtx = "\nLower matrix \n";
        String upMtx = "\nUpper matrix \n";
        String inverse = "\nInverse matrix \n";
        String determinant = "\nDeretminant: ";
        
        String mtxA = Arrays.deepToString(A.matrix);
        String mtxL = Arrays.deepToString(L.matrix);
        String mtxU = Arrays.deepToString(U.matrix);
        String mtxInv = Arrays.deepToString(Inv.matrix);
        String detString = Double.toString(det);
        
        String finalText = message + originalMtx + mtxA + 
                lowMtx + mtxL + upMtx + mtxU + inverse + mtxInv + determinant + detString;
        resultTextArea.setWrapText(true);
        
        String formatted = finalText
                            .replace(",", "")  //remove commas
                            .replace("[", "")  //remove right bracket
                            .replace("]", "\n")  //remove left bracket
                            .trim();           
        resultTextArea.setText(formatted);
    }
    
    // Allow the user to load a previously saved computation.
    public void Load(File file, TextArea resultTextArea)
    {
        try {
        Scanner s = new Scanner(file).useDelimiter("\n");
        resultTextArea.clear();
        while (s.hasNext()) {
            if (s.hasNextInt()) { // check if next token is an int
                resultTextArea.appendText(s.nextInt() + " "); // display the found integer
            } else {
               resultTextArea.appendText(s.next() + " "); // else read the next token
            }
        }
    } catch (FileNotFoundException ex) {
        System.err.println(ex);
    }
    }
    
    public void FileRestrictions(FileChooser fileChooser)
    {
        fileChooser.setTitle("Select previously saved computation");
        fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));                 
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Text", "*.txt"));
    }
    
    // Save results from Result Text Area to text file.
    public void Save(TextArea resultTextArea, File file)
    {        
        try {
            FileWriter fileWriter = new FileWriter(file);
           // PrintWriter writer;
            PrintStream writer;
            //writer = new PrintWriter(fileWriter);
            writer = new PrintStream(file);
            
            String finalText = resultTextArea.getText();
            writer.printf(String.format("%s", resultTextArea.getText()));
            writer.close();
        } catch (IOException ex) {
            Logger.getLogger(Project.class.getName()).log(Level.SEVERE, null, ex);
        }

   
    }
}
