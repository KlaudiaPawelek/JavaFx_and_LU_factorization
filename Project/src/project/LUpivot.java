/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project;
import project.Matrix;
import java.lang.Math;
/**
 *
 * @author s297163
 */
public class LUpivot extends Matrix {

    public LUpivot(int m, int n) 
    {
        super(m, n);
    }
    
    public void Lu_factorization(Matrix A, Matrix L, Matrix U, int n)
    {
          double multiplication = 0.0;
          Matrix temp = new Matrix(A);
          
         // LU decomposition without pivoting
	for (int k = 0; k < n - 1; k++) {
		for (int i = k + 1; i < n; i++) {
			if (Math.abs(temp.matrix[k][k]) < 1.e-07) 
                            System.err.println("Zero pivot found!");
                        multiplication = temp.matrix[i][k]/temp.matrix[k][k];
			temp.matrix[i][k] = multiplication;                      // entries of L are saved in temp
			for (int j = k + 1; j < n; j++) { 
				temp.matrix[i][j] -= multiplication*temp.matrix[k][j];      // entries of U are saved in temp
				//if (fabs(temp[i][i]) < 1.e-07) //throw std::out_of_range("zero pivot found");
			}
		}
	}

	// create l and u from temp
	for (int i=0; i<n; i++) 
            L.matrix[i][i] = 1.0;
	for (int i=1; i<n; i++) 
	  for (int j=0; j<i; j++) 
              L.matrix[i][j] = temp.matrix[i][j];

	for (int i=0; i<n; i++)
		for (int j=i; j<n; j++) 
                    U.matrix[i][j] = temp.matrix[i][j];
          
    }
    
    public void Lu_solving(Matrix L, Matrix U, Vector b, int n, Vector x)
    {
        Vector temp = new Vector(b);
        
        // Forward substitution for Ly=b;
        for(int i = 1; i < n; i++)
        {
            for(int j = 0; j< i; j++)
            {
                temp.vector[i]-=L.matrix[i][j]*temp.vector[j];
            }
        }
        
        // Back substitution for Ux = y.  
	for (int i = n - 1; i >= 0; i--) 
        {
		for (int j = i + 1; j < n; j++)
                    temp.vector[i] -= U.matrix[i][j]*temp.vector[j];
		temp.vector[i] /= U.matrix[i][i];
	}

	// copy solution into x
	for (int i=0; i<n; i++) 
            x.vector[i] = temp.vector[i];
    }
    
    public void reorder(Matrix A, int n, Matrix P)
    {
        // Note: pivoting information is stored in temperary vector pvt
	int pvtk,pvti;
	double aet =0.0;
        double tmp=0.0;
        double mult = 0.0;

        Vector pvt = new Vector(n);
	Matrix temp = new Matrix(A);
        
	for (int k = 0; k < n; k++) 
            pvt.vectorInt[k] = k;

	Vector scale = new Vector(n);             // find scale vector
	for (int k = 0; k < n; k++) {
		scale.vector[k] = 0;
		for (int j = 0; j < n; j++) 
			if (Math.abs(scale.vector[k]) < Math.abs(temp.matrix[k][j])) 
                            scale.vector[k] = Math.abs(temp.matrix[k][j]);
	} 

	for (int k = 0; k < n - 1; k++) {            // main elimination loop

	// find the pivot in column k in rows pvt[k], pvt[k+1], ..., pvt[n-1]
		int pc = k; 
		aet = Math.abs((temp.matrix[pvt.vectorInt[k]][k])/scale.vector[k]);
		for (int i = k + 1; i < n; i++) {
			tmp = Math.abs(temp.matrix[pvt.vectorInt[i]][k]/scale.vector[pvt.vectorInt[i]]); 
			if (tmp > aet) {
				aet = tmp; 
				pc = i;
			}
		}
		if (Math.abs(aet) < 1.e-07) 
                    System.err.println("Zero pivot found!");
		if (pc != k) {                      // swap pvt[k] and pvt[pc]
			int ii = pvt.vectorInt[k];
			pvt.vectorInt[k] = pvt.vectorInt[pc];
			pvt.vectorInt[pc] = ii;
		}

		// now eliminate the column entries logically below mx[pvt[k]][k]
		pvtk = pvt.vectorInt[k];                           // pivot row
		for (int i = k + 1; i < n; i++) {
			pvti = pvt.vectorInt[i];
			if (temp.matrix[pvti][k] != 0) {
				mult = temp.matrix[pvti][k]/temp.matrix[pvtk][k]; 
				temp.matrix[pvti][k] = mult;
				for (int j = k + 1; j < n; j++) 
                                    temp.matrix[pvti][j] -= mult*temp.matrix[pvtk][j];
			}
		} 
	}
        
	for (int i=0; i<n; i++) 
            P.matrix[i][pvt.vectorInt[i]]=1.0;
        
    }
    
    public double determinant(Matrix L, Matrix U, Matrix P)
    {
	int parity = 0;
	for (int i = 0; i < P.getNCols(); i++)
	{
		if (P.matrix[i][i] == 0)
		{
			parity++;
		}
	}

	double detL = 1.0;
	double detU = 1.0;
	double det = 0.0;
	for (int i = 0; i < L.getNCols(); i++)
	{
		detL = detL*L.matrix[i][i];
		detU = detU*U.matrix[i][i];
	}
	det = detL * detU;
	if (parity % 2 == 1)
	{
		return det;
	}
	else
	{
		return- det;
	}
	
    }
}
