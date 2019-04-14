/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project;
import project.Matrix;

/**
 *
 * @author Klaudia Pawelek
 */
public class Inverse extends Matrix{
    
    public Inverse()
    {
        //empty
    }
    
    public Inverse(int m, int n) 
    {
        
        super(m, n);
        
    }
    
    public Matrix inverseMatrix(Matrix L, Matrix U, Matrix P, int n)
    {
	Matrix Uinv = new Matrix(n,n);
	Matrix Linv = new Matrix(n,n);
	for (int k = 0; k < n; k++)
	{
		Vector temp = new Vector(n);
		for (int i = 0; i < n; i++)
		{
			if (i == k)
				temp.vector[i] = 1;
			else
				temp.vector[i] = 0;
		}
		// forward substitution for L y = b.
		for (int i = 1; i < n; i++)
			for (int j = 0; j < i; j++)
				temp.vector[i] -= L.matrix[i][j] * temp.vector[j];

		for (int i = 0; i < n; i++)
		{
			Linv.matrix[i][k] = temp.vector[i];
		}

	}

	for (int k = 0; k < n; k++)
	{
		Vector temp = new Vector(n);
		for (int i = 0; i < n; i++)
		{
			if (i == k)
				temp.vector[i] = 1;
			else
				temp.vector[i] = 0;
		}
		// back substitution for U x = y.  
		for (int i = n - 1; i >= 0; i--) {
			for (int j = i + 1; j < n; j++) 
                            temp.vector[i] -= U.matrix[i][j] * temp.vector[j];
			temp.vector[i] /= U.matrix[i][i];
		}

		for (int i = 0; i < n; i++)
		{
			Uinv.matrix[i][k] = temp.vector[i];
		}

	}
        Matrix LinP = new Matrix();
	LinP = LinP.multiplication(Linv, P);
        Matrix results = new Matrix();
        results = results.multiplication(Uinv, LinP);
	return  results;

    }
    
}
