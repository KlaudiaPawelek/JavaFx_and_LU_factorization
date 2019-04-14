package project;

/**
 *
 * @author Klaudia
 */
public class Vector {

    private final int size;
    public final double [] vector;
    public final int [] vectorInt;
    
    public Vector(int size)
    {
        this.size = size;
        vector = new double[size];
        vectorInt = new int[size];
    }
    
    // Copy constructor
    public Vector(Vector A)
    {
        this.size = A.getSize();
        this.vector = new double[this.size];
        for(int i = 0; i<this.size; i++)
        {
            this.vector[i]=A.vector[i];
        }
        
        this.vectorInt = new int[size];
    }
    
    public Vector(double[] array)
    {
        this.size = array.length;
        this.vector = new double[size];
        for(int i = 0; i<size; i++)
        {
            this.vector[i] = array[i];
        }
        
        this.vectorInt = new int[size]; 
    }
    
    public int getSize()
    {
        return this.size;
    }
    
    public double[] getVector()
    {
        return this.vector;
    }
    
   public Vector multiplication(Matrix A, Vector b)
   {
       
       Vector result = new Vector(b.getSize());
       
       	for (int i = 0;i<A.getNRows();i++)
		for (int j = 0;j<A.getNCols();j++) 
                    result.vector[i] += (A.matrix[i][j] * b.vector[j]);
       
        return result;
   }
    
}
