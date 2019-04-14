package project;
import java.util.ArrayList;

/**
 *
 * @author Klaudia Pawelek
 */

public class Matrix {
    
    // Private fields of Matrix class
    private final int m;            // number of rows
    private final int n;            // number of columns
    public final double [][] matrix; // 2D array
    
    
    public Matrix()
    {
        this.m = 0;
        this.n = 0;
        this.matrix = new double[n][m];
    }
    
    // Constructor, which can create matrix of size m x n
    public Matrix(int m, int n)
    {
        this.m = m;
        this.n = n;
        matrix = new double[m][n];
        
    }
    
    // Copy constructor
    public Matrix(Matrix A)
    {
        this.m = A.getNRows();
        this.n = A.getNCols();
        this.matrix = new double[this.m][this.n];
        for(int i = 0; i<m; i++)
        {
            for(int j = 0; j < n; j++)
            {
                this.matrix[i][j] = A.matrix[i][j];
            }
        }
    }
    
    // Return matrix as 2D array
    public double[][] getMatrix()
    {
        return this.matrix;     
    }
    
    // Return number of rows
    public int getNRows()
    {
        return this.m;
    }
    
    // Return number of columns
    public int getNCols()
    {
        return this.n;
    }
    
   public double [][] copy(Matrix A)
   {
       for(int i = 0; i < A.getNRows(); i++)
       {
           for(int j = 0; j < A.getNCols(); j++)
           {
               this.matrix[i][j] = A.matrix[i][j];
           }
       }
       
       return this.matrix;
   }
   
   public Matrix copy(ArrayList<Double> arrayList, int m, int n)
   {
       Matrix result = new Matrix(m, n);
       for(int i = 0; i < m; i++)
       {
           for(int j = 0; j < n; j++)
           {
               result.matrix[i][j] = arrayList.get(i*m+j);
           }
       }
       
       return result;
   }
   
   public Matrix multiplication(Matrix A, Matrix B)
   {
       Matrix result = new Matrix(A.getNRows(), A.getNCols());
       for(int i = 0; i < A.getNRows(); i++)
       {
           for(int j = 0; j < A.getNCols(); j++)
           {
               for (int k = 0;k<A.getNCols();k++) 
               {
                    result.matrix[i][j] += (A.matrix[i][k] * B.matrix[k][j]);
               }
           }
       }
       
       return result;
   }
   
}
