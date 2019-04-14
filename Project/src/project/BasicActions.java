package project;
import javafx.scene.control.TextArea;
import java.util.Arrays;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Logger;
import java.util.logging.Level;
import javafx.stage.FileChooser;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Scanner;
import javafx.scene.control.Label;

/**
 * Class extends Project class, it includes implementation of buttons 
 * and text areas, which are visible in GUI like "LU pivot or "Save".
 * @author Klaudia Pawelek
 */
public class BasicActions extends Project{
    
    /**
     * Empty constructor
     */
        public BasicActions()
    {
        //empty
    }
    

    /**
     * Clear all text areas and errorLable.
     * @param matrixTextArea
     * @param vectorTextArea
     * @param resultTextArea
     * @param errorLabel
     */
        public void Clear(TextArea matrixTextArea, TextArea vectorTextArea, TextArea resultTextArea, Label errorLabel)
    {
        matrixTextArea.clear();
        vectorTextArea.clear();
        resultTextArea.clear();
        errorLabel.setText("");
    }
    

    /**
     * Display results of LU in Text Area, which is on main scene.
     * @param matrixTextArea
     * @param vectorTextArea
     * @param resultTextArea
     * @param A
     * @param b
     * @param L
     * @param U
     * @param x
     * @param det
     */
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
                            .replace("]", "\n")//remove left bracket
                            .trim();           
        resultTextArea.setText(formatted);
    }
    
    /**
     * Display results of inversion in Text Area, which is on main scene.
     * @param matrixTextArea
     * @param vectorTextArea
     * @param resultTextArea
     * @param A
     * @param L
     * @param U
     * @param Inv
     * @param det
     */
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
                            .replace("]", "\n")//remove left bracket
                            .trim();           
        resultTextArea.setText(formatted);
    }
    

    /**
     * Allow the user to load a previously saved computation.
     * @param file
     * @param resultTextArea
     * @param errorLabel
     */
        public void Load(File file, TextArea resultTextArea, Label errorLabel)
    {
        try 
        {
            Scanner s = new Scanner(file).useDelimiter("\n");
            resultTextArea.clear();
            while (s.hasNext()) 
            {
                if (s.hasNextInt()) 
                { 
                    resultTextArea.appendText(s.nextInt() + " "); 
                } 
                else 
                {
                   resultTextArea.appendText(s.next() + " "); 
                }
            }
        } 
        catch (FileNotFoundException ex) 
        {
            errorLabel.setText(ex.toString());
        }
    }
    
    /**
     * File restricion for uploading, only txt files.
     * @param fileChooser
     */
    public void FileRestrictions(FileChooser fileChooser)
    {
        fileChooser.setTitle("Select previously saved computation");
        fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));                 
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Text", "*.txt"));
    }
    

    /**
     *  Save results from Result Text Area to text file.
     * @param resultTextArea
     * @param file
     * @param errorLabel
     */
        public void Save(TextArea resultTextArea, File file, Label errorLabel)
    {        
        try 
        {
            PrintStream writer = new PrintStream(file,"UTF-8");
            writer.printf(String.format("%s", resultTextArea.getText()));
            writer.close();
        } 
        catch (IOException ex) 
        {
            Logger.getLogger(Project.class.getName()).log(Level.SEVERE, null, ex);
            errorLabel.setText(ex.toString());
        }

   
    }
}
