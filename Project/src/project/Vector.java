package project;

/**
 * Class responsible for storing vectors and methods created specially for this type for storage like multiplication 
 * Vector includes double[] array to represent data.
 * @author Klaudia Pawelek
 */
public class Vector implements Storage{

    private final int size;
    private final String name = "Vector";

    /**
     * Vector as 1D array of doubles.
     */
    public final double [] vector;

    /**
     * Vector as 1D array of integers.
     */
    public final int [] vectorInt;
    
    /**
     * Default constructor, size 0.
     */
    public Vector()
    {
        this.size = 0;
        vector = new double[size];
        vectorInt = new int[size];
    }
    
    /**
     * Constructor with size of vector.
     * @param size
     */
    public Vector(int size)
    {
        this.size = size;
        vector = new double[size];
        vectorInt = new int[size];
    }
    

    /**
     * Copy constructor - Vector as argument.
     * @param A
     */
        public Vector(Vector A)
    {
        this.size = A.getSize();
        this.vector = new double[this.size];
        System.arraycopy(A.vector, 0, this.vector, 0, this.size);
        
        this.vectorInt = new int[size];
    }
    
    /**
     * Copy constructor - array as argument.
     * @param array
     */
    public Vector(double[] array)
    {
        this.size = array.length;
        this.vector = new double[size];
        System.arraycopy(array, 0, this.vector, 0, size);
        
        this.vectorInt = new int[size]; 
    }
    
    /**
     * Return data as double[]
     * @return double[]
     */
    public double[] getVector()
    {
        return this.vector;
    }
    
    /**
     * Vector-matrix multiplication
     * @param A
     * @param b
     * @return Vector
     */
    public Vector multiplication(Matrix A, Vector b)
   {
       
       Vector result = new Vector(b.getSize());
       
       	for (int i = 0;i<A.getNRows();i++)
        {
		for (int j = 0;j<A.getNCols();j++)
                {
                    result.vector[i] += (A.matrix[i][j] * b.vector[j]);
                }
        }
       
        return result;
   }

    @Override
    public String getName() 
    {
        return this.name;
    }
    
    @Override
    public int getSize()
    {
        return this.size;
    }
}
