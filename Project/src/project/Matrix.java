package project;
import java.util.ArrayList;

/**
 * Class responsible for storing matrices and methods created specially for this type of storage like multiplication
 * Matrix includes double[][] array to represent data.
 * @author Klaudia Pawelek
 */

public class Matrix implements Storage{
    
    // Private fields of Matrix class
    private final String name = "Matrix";
    private final int m;            // number of rows
    private final int n;            // number of columns

    /**
     * Matrix as 2D array;
     */
    public final double [][] matrix; // 2D array
    
    /**
     * Default constructor, size zero.
     */
    public Matrix()
    {
        this.m = 0;
        this.n = 0;
        this.matrix = new double[n][m];
    }
    

    /**
     * Constructor, which can create matrix of size m x n.
     * @param m
     * @param n
     */
        public Matrix(int m, int n)
    {
        this.m = m;
        this.n = n;
        matrix = new double[m][n];
        
    }
    

    /**
     * Copy constructor
     * @param A
     */
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
    

    /**
     * Return matrix as 2D array.
     * @return double[][]
     */
        public double[][] getMatrix()
    {
        return this.matrix;     
    }
    

    /**
     * Return number of rows
     * @return int
     */
        public int getNRows()
    {
        return this.m;
    }
   

    /**
     * Return number of columns.
     * @return int
     */
        public int getNCols()
    {
        return this.n;
    }
    
    /**
     * Copy array (matrix).
     * @param A
     * @return double[][]
     */
    public double [][] copy(Matrix A)
   {
       for(int i = 0; i < A.getNRows(); i++)
       {
           System.arraycopy(A.matrix[i], 0, this.matrix[i], 0, A.getNCols());
       }
       
       return this.matrix;
   }
   
    /**
     * Copy vector to matrix.
     * @param arrayList
     * @param m
     * @param n
     * @return Matrix
     */
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
   
    /**
     * Multiplication of two matrices.
     * @param A
     * @param B
     * @return Matrix
     */
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

    /**
     * Return size of matrix.
     * @return int
     */
    @Override
    public int getSize() 
    {
        return this.m * this.n;
    }

    /**
     * Return name of storage.
     * @return String
     */
    @Override
    public String getName() 
    {
        return this.name;
    }
   
}
