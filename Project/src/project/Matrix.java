package project;

/**
 *
 * @author Klaudia Pawelek
 */

final public class Matrix {
    
    // Private fields of Matrix class
    private final int m;            // number of rows
    private final int n;            // number of columns
    private final double [][] matrix; // 2D array
    
    // Constructor, which can create matrix of size m x n
    public Matrix(int m, int n)
    {
        this.m = m;
        this.n = n;
        matrix = new double[m][n];
    }
    
    //return matrix as 2D array
    public double[][] getMatrix()
    {
        return this.matrix;     
    }
    
}
